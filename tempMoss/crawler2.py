import requests
from bs4 import BeautifulSoup
import re
import time
import os
import urllib.request

# keyword_matches returns True if one of the following is True:
# - Anchor text starts with the keyword
# - Anchor text contains a space followed by the keyword followed by a space
# - Anchor text ends with a space followed by the keyword
# - Text within the URL starts with the keyword
# - Text within the URL contains an underscore followed by the keyword followed by an underscore
# - Text within the URL ends with an underscore followed by the keyword

def crawler2(seed):

	# Wiki Main Page, which needs to be excluded while crawling
	wiki_main = 'https://en.wikipedia.org/wiki/Main_Page'
	# Count of number of URLs crawled
	cr_ct = 0
	# Maximum depth of crawling reached
	max_d = 1

	# Frontier - A list of URLS depicting a queue containing next URLs to be crawled
	#          - will contain the seed URL initially
	furls = [seed]
	# Seen - A list of URLs containing URLs crawled, empty initially
	surls = []

	# creation of Logs directory, if not present
	npath = r'Logs' 
	if not os.path.exists(npath):
		os.makedirs(npath)

	# creation of HTML_Downloads directory, if not present (commented below)

	npath = r'HTML_Downloads'
	if not os.path.exists(npath):
		os.makedirs(npath)

	# Mentioning the seed URL in clog.txt
	clog = open("Logs/clog.txt","w")
	clog.write("Seed : "+seed+"\n\n")

	# Writing into log file starting with Depth 1
	clog.write("Depth 1 :\n\n")
	cr_ct+=1
	clog.write(str(cr_ct)+") "+seed+"\n\n")

	# flag is True iff the limit of 1000 URLS has not been reached
	flag = True
	print("\n----------------------------------------- At depth 1--------------------------------------------------------")
	print(str(cr_ct)+") "+seed)
	
	# get the soup
	scode = requests.get(seed)
	plain_text = scode.text
	soup = BeautifulSoup(plain_text,"html.parser")

	# # download seed (commented below)

	# url_name = seed[seed.rfind('/')+1:]
	url_name = seed[30:]
	url_name = open("HTML_Downloads/"+url_name+".txt","w")
	# url_name.write(urllib2.urlopen("http://www.doctorisin.net/").read())
	url_name.write(str(soup.prettify(encoding='utf-8')))
	url_name.close()

	# assuming maximum depth to crawl as Depth 6
	for depth in range (2, 7):
		if flag:
			print("\n----------------------------------------- At depth "+str(depth)+"--------------------------------------------------------")
			clog.write("Depth "+str(depth)+" :\n\n")
			e_urls = []

			# Traversing through all the URLs to be crawled as pointed by the Frontier
			for frontier in furls:

				# Enter only if limit of 1000 URLs not reached
				if flag:

					# get the soup
					scode = requests.get(frontier)
					plain_text = scode.text
					soup = BeautifulSoup(plain_text,"html.parser")

					# Filter URLs, choose only the ones starting with '/wiki/'
					for link in soup.find_all('a', href=re.compile('^/wiki/')):

						# check if limit reached or not
						if cr_ct < 1000 and flag:

							# retrieve the Anchor text and Text of the URL
							url_text = link.text
							href_url = link.get('href')

							# Ignore all the Administrative URLs
							if ':' not in href_url:

								# Handle URLs with '#' seperately
								if '#' not in href_url:
									url = 'https://en.wikipedia.org'+href_url

									# URL should not be in either of Frontier, Extracted or Seen lists and should not be Wiki Main Page too
									if url not in furls and url not in e_urls and url not in surls and url != wiki_main and (link.get('class') == None or not 'mw-redirect' in link.get('class')):
										
										# Respecting the Politeness Policy
										# time.sleep(1)

										# get the soup
										scode = requests.get(url)
										plain_text = scode.text
										soup = BeautifulSoup(plain_text,"html.parser")

										# download this url (commented below)

										url_name = url[30:]
										url_name = url_name.replace("/", "_")
										url_name = open("HTML_Downloads/"+url_name+".txt","w")
										url_name.write(str(soup.prettify(encoding='utf-8')))
										url_name.close()
										
										e_urls.append(url)
										cr_ct+=1
										clog.write(str(cr_ct)+") "+url+"\n")
										print(str(cr_ct)+") "+url)

								else:
									# Handle URLs with '#'
									hash_pos = href_url.index('#')

									# Trim the URL from the start till index before '#'
									url = 'https://en.wikipedia.org'+href_url[:hash_pos]

									# URL should not be in either of Frontier, Extracted or Seen lists and should not be Wiki Main Page too
									if url not in furls and url not in e_urls and url not in surls and url != wiki_main and (link.get('class') == None or not 'mw-redirect' in link.get('class')):
										
										# Respecting the Politeness Policy
										# time.sleep(1)

										# get the soup
										scode = requests.get(url)
										plain_text = scode.text
										soup = BeautifulSoup(plain_text,"html.parser")

										# download this url (commented below)

										url_name = url[30:]
										url_name = url_name.replace("/", "_")
										url_name = open("HTML_Downloads/"+url_name+".txt","w")
										url_name.write(str(soup.prettify(encoding='utf-8')))
										url_name.close()

										e_urls.append(url)
										cr_ct+=1
										clog.write(str(cr_ct)+") "+url+"\n")
										print(str(cr_ct)+") "+url)


						else:
							# limit of 1000 URLs reached
							flag = False
							print("Limit of 1000 URLs reached")
							max_d = depth
							break

					# Copy all the URLs from Frontier to Seen
					surls.append(frontier)

			# Case when no URLs found on the depth
			if len(e_urls) == 0:
				print("No matching URLs at Depth "+str(depth)+"\n")
				clog.write("No matching URLs at Depth "+str(depth)+"\n\n")
				flag = False
				max_d = depth
				break
			# Copy all the Extracted URLs to the Frontier
			furls = e_urls
			clog.write("\n")

	# Maximum depth of Depth 6 reached		
	if flag:
		print("Searched till max depth 6")
		max_d = 6

	clog.write("------------------------------------------------------------------------------------\n")
	clog.write("Logistics :\n\n")
	# clog.write("Number of matching searches : "+str(cr_ct)+"\n")
	clog.write("Maximum depth reached : Depth "+str(max_d)+"\n")

seed = 'https://en.wikipedia.org/wiki/Tropical_cyclone'
crawler2(seed)