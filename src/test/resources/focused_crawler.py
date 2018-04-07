import requests
from bs4 import BeautifulSoup
import re
import time
import os

# keyword_matches returns True if one of the following is True:
# - Anchor text starts with the keyword
# - Anchor text contains a space followed by the keyword followed by a space
# - Anchor text contains a space followed by the keyword
# - Anchor text ends with a space followed by the keyword
# - Text within the URL starts with the keyword
# - Text within the URL contains an underscore followed by the keyword followed by an underscore
# - Text within the URL contains an underscore followed by the keyword
# - Text within the URL ends with an underscore followed by the keyword

def keyword_matches(keyword, url_text, href_url):
	return url_text.startswith(keyword) or url_text.endswith(' '+keyword) or ' '+keyword+' ' in url_text or ' '+keyword in url_text or href_url.startswith(keyword) or href_url.endswith('_'+keyword) or '_'+keyword+'_' in href_url or '_'+keyword in href_url


def spider(seed_url, keyword):

	# Wiki Main Page, which needs to be excluded while crawling
	main_page = 'https://en.wikipedia.org/wiki/Main_Page'
	# Count of number of URLs crawled
	crawled_count = 0
	# Maximum depth of crawling reached
	max_depth = 1

	# Frontier - A list of URLS depicting a queue containing next URLs to be crawled
	#          - will contain the seed URL initially
	frontier_urls = [seed_url]
	# Seen - A list of URLs containing URLs crawled, empty initially
	seen_urls = []

	# creation of Logs directory, if not present
	newpath = r'Logs' 
	if not os.path.exists(newpath):
		os.makedirs(newpath)

	# creation of Raw_HTML_Downloads directory, if not present (commented below)

	# newpath = r'Raw_HTML_Downloads'
	# if not os.path.exists(newpath):
	# 	os.makedirs(newpath)

	# Mentioning the seed URL and keyword in focused_crawler_log.txt
	focused_crawler_log = open("Logs/focused_crawler_log.txt","w")
	focused_crawler_log.write("Seed : "+seed_url+"\n")
	focused_crawler_log.write("Keyword : "+keyword+"\n\n")

	# Writing into log file starting with Depth 1
	focused_crawler_log.write("Depth 1 :\n\n")
	crawled_count+=1
	focused_crawler_log.write(str(crawled_count)+") "+seed_url+"\n\n")

	# flag is True iff the limit of 1000 URLS has not been reached
	flag = True
	print("\n----------------------------------------- At depth 1--------------------------------------------------------")
	print(str(crawled_count)+") "+seed_url)
	
	# get the soup
	source_code = requests.get(seed_url)
	plain_text = source_code.text
	soup = BeautifulSoup(plain_text,"html.parser")

	# download seed_url (commented below)

	# name = seed_url[seed_url.rfind('/')+1:]
	# name = open("Raw_HTML_Downloads/"+(str(crawled_count))+") "+name+".txt","w")
	# name.write(str(soup.prettify(encoding='utf-8')))
	# name.close()

	# assuming maximum depth to crawl as Depth 6
	for depth in range (2, 7):
		if flag:
			print("\n----------------------------------------- At depth "+str(depth)+"--------------------------------------------------------")
			focused_crawler_log.write("Depth "+str(depth)+" :\n\n")
			extracted_urls = []

			# Traversing through all the URLs to be crawled as pointed by the Frontier
			for frontier_url in frontier_urls:

				# Enter only if limit of 1000 URLs not reached
				if flag:

					# get the soup
					source_code = requests.get(frontier_url)
					plain_text = source_code.text
					soup = BeautifulSoup(plain_text,"html.parser")

					# Filter URLs, choose only the ones starting with '/wiki/'
					for link in soup.find_all('a', href=re.compile('^/wiki/')):

						# check if limit reached or not
						if crawled_count < 1000 and flag:

							# retrieve the Anchor text and Text of the URL
							url_text = link.text
							href_url = link.get('href')
							truncated_href_url = href_url[6:]

							# Call helper function to check if keyword matches or not
							if keyword_matches(keyword, url_text, truncated_href_url):

								# Ignore all the Administrative URLs
								if ':' not in href_url:

									# Handle URLs with '#' seperately
									if '#' not in href_url:
										url = 'https://en.wikipedia.org'+href_url

										# URL should not be in either of Frontier, Extracted or Seen lists and should not be Wiki Main Page too
										if url not in frontier_urls and url not in extracted_urls and url not in seen_urls and url != main_page:
											
											# Respecting the Politeness Policy
											time.sleep(1)

											# get the soup
											source_code = requests.get(url)
											plain_text = source_code.text
											soup = BeautifulSoup(plain_text,"html.parser")

											# download this url (commented below)

											# name = url[url.rfind('/')+1:]
											# name = open("Raw_HTML_Downloads/"+(str(crawled_count+1))+") "+name+".txt","w")
											# name.write(str(soup.prettify(encoding='utf-8')))
											# name.close()
											
											extracted_urls.append(url)
											crawled_count+=1
											focused_crawler_log.write(str(crawled_count)+") "+url+"\n")
											print(str(crawled_count)+") "+url)

									else:
										# Handle URLs with '#'
										hash_pos = href_url.index('#')

										# Trim the URL from the start till index before '#'
										url = 'https://en.wikipedia.org'+href_url[:hash_pos]

										# URL should not be in either of Frontier, Extracted or Seen lists and should not be Wiki Main Page too
										if url not in frontier_urls and url not in extracted_urls and url not in seen_urls and url != main_page:
											
											# Respecting the Politeness Policy
											time.sleep(1)

											# get the soup
											source_code = requests.get(url)
											plain_text = source_code.text
											soup = BeautifulSoup(plain_text,"html.parser")

											# download this url (commented below)

											# name = url[url.rfind('/')+1:]
											# name = open("Raw_HTML_Downloads/"+(str(crawled_count+1))+") "+name+".txt","w")
											# name.write(str(soup.prettify(encoding='utf-8')))
											# name.close()

											extracted_urls.append(url)
											crawled_count+=1
											focused_crawler_log.write(str(crawled_count)+") "+url+"\n")
											print(str(crawled_count)+") "+url)


						else:
							# limit of 1000 URLs reached
							flag = False
							print("Limit of 1000 URLs reached")
							max_depth = depth
							break

					# Copy all the URLs from Frontier to Seen
					seen_urls.append(frontier_url)

			# Case when no URLs found on the depth
			if len(extracted_urls) == 0:
				print("No matching URLs at Depth "+str(depth)+"\n")
				focused_crawler_log.write("No matching URLs at Depth "+str(depth)+"\n\n")
				flag = False
				max_depth = depth
				break
			# Copy all the Extracted URLs to the Frontier
			frontier_urls = extracted_urls
			focused_crawler_log.write("\n")

	# Maximum depth of Depth 6 reached		
	if flag:
		print("Searched till max depth 6")
		max_depth = 6

	focused_crawler_log.write("------------------------------------------------------------------------------------\n")
	focused_crawler_log.write("Logistics :\n\n")
	focused_crawler_log.write("Number of matching searches : "+str(crawled_count)+"\n")
	focused_crawler_log.write("Maximum depth reached : Depth "+str(max_depth)+"\n")

seed_url = 'https://en.wikipedia.org/wiki/Tropical_cyclone'
keyword = 'rain'
spider(seed_url, keyword)