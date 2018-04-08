import math, operator, sys, os

# given: graph, node
# returns: number of outlinks
def number_of_outlinks(g, node):
	count = 0
	for key, value in g.items():
		if node in value:
			count += 1
	return count

# given: page rank dictionary
# returns: perplexity value
def calculate_perplexity(PR):
	return 2 ** entropy(PR)

# given: page rank dictionary
# returns: entropy value
def entropy(PR):
	initial = 0
	for key, value in PR.items():
		initial += (value*math.log(value,2))
	return -1 * initial

# given: graph file name
# returns: page rank value
def calculate_page_rank(file_name):

	# dictionary containing the graph
	g = {}

	# file path containing graph dictionary
	path = "Logs/"+str(file_name)+".txt"

	# read content of the file
	content = open(path, 'r').read()

	# new line split
	each_line = content.split("\n")

	for i in each_line:

		# split by space
		nodes = i.split(" ",1)

		# set dictionary value
		g[nodes[0]] = nodes[1].split(" ")
	
	# damping/teleportation factor is 0.85
	d = 0.85

	# length of set of pages
	N = len(g)

	# copy of g
	M = g

	# set of all pages initialization
	P = []

	# number of outlinks dictionary
	L = {}

	# S is the set of sink nodes
	S = []

	# page rank score dictionary
	PR = {}

	for key, value in g.items():

		P.append(key)
		L[key] = number_of_outlinks(g, key)

		# sink or not check
		if L[key] == 0 or (L[key] == 1 and key == value[0]):
			S.append(key)

		# initializing page rank
		PR[key] = 1/N

	# calculate initial perplexity
	previous_perplexity = calculate_perplexity(PR)

	perplexity_log.write("\n------------------------------------------------------------------------------------------")
	perplexity_log.write("\nGraph : "+file_name)
	perplexity_log.write("\n------------------------------------------------------------------------------------------")

	# writing initial perplexity
	perplexity_log.write("\n\nInitial perplexity : "+str(previous_perplexity)+"\n")
	
	# number of times change in perplexity less than 1 consecutively, 0 initially
	skewed_count = 0

	# initializing round number
	round_number = 0

	while True:

		# increment round, as next round
		round_number += 1
		
		# new PR scores dictionary
		newPR = {}

		# initial sinkPR
		sinkPR = 0

		# calculate total sink PR
		for p in S:
			sinkPR += PR[p]

		for p in P:

			# teleportation
			newPR[p] = (1-d)/N

			# spread remaining sink PR evenly
			newPR[p] += d*sinkPR/N

			# pages pointing to p
			for q in M[p]:

				# add share of PageRank from in-links
				newPR[p] += d*PR[q]/L[q]

		for p in P:

			# assign new PR scores
			PR[p] = newPR[p]

		# calculate new perplexity score
		current_perplexity = calculate_perplexity(newPR)

		# writing round number and perplexity for this round
		perplexity_log.write("\nRound "+str(round_number)+":")
		perplexity_log.write("\nPerplexity : "+str(current_perplexity))

		# calculate absolute value of change in perplexity
		change_in_perplexity = abs(current_perplexity - previous_perplexity)

		# writing change in perplexity
		perplexity_log.write("\nChange in Perplexity : "+str(change_in_perplexity)+"\n")

		if change_in_perplexity < 1:

			# increment skewed_count
			skewed_count += 1

			# check if perplexity less than 1 for 4 consecutive iterations
			if skewed_count == 4:

				# come out of while loop
				break

		else:

			# change in perplexity greater than 1, reset skewed_count
			skewed_count = 0

		# assign current perplexity to previous perplexity
		previous_perplexity = current_perplexity

	# writing number of rounds for the graph to converge
	perplexity_log.write("\nGraph "+file_name+" converged after "+str(round_number)+" rounds\n")

	# sort PR dictionary based on PR scores
	sorted_PR = sorted(PR.items(), key=operator.itemgetter(1), reverse=True)
	# reverse = True for sorting in decreasing order of PR scores

	# creating file containing top 50 pages of this graph
	top_50_path = "Logs/Top_50_in_"+file_name+".txt"
	top_50_log = open(top_50_path,"w")

	top_50_log.write("Format:\nRank) docID : PageRank score\n\n") 

	# writing docID and PageRank of top 50 pages
	page_rank_number = 1
	for i in sorted_PR[:50]:
		top_50_log.write(str(page_rank_number)+") "+i[0]+" : "+str(i[1])+"\n")
		page_rank_number += 1

	###################### Statistics Generation #########################

	# creating file containing graph statistics
	stats_path = "Logs/"+file_name+"_statistics.txt"
	stats_log = open(stats_path,"w")

	# writing total number of pages
	stats_log.write("Total number of pages : "+str(N))

	# writing number and proportion of pages with no in-links
	stats_log.write("\nNumber of pages with no in-links : "+str(len(S)))
	stats_log.write("\nProportion of pages with no in-links : "+str(len(S)/N))

	# calculation number of pages with no out-links
	pages_with_out_links = []
	for key,value in g.items():
		for v in value:
			if v not in pages_with_out_links:
				pages_with_out_links.append(v)

	no_outs = N - len(pages_with_out_links)

	# writing number and proportion of pages with no out-links
	stats_log.write("\nNumber of pages with no out-links : "+str(no_outs))
	stats_log.write("\nProportion of pages with no out-links : "+str(no_outs/N))

	# calculating top 10 pages based on number of in-links
	arr = {}
	for key,value in g.items():
		arr[key] = len(value)
	sorted_arr = sorted(arr.items(), key=operator.itemgetter(1), reverse=True)

	# writing top 10 pages based on number of in-links
	stats_log.write("\n\nTop 10 pages based on number of in-links :\n")
	n = 1
	for i in sorted_arr[:10]:
		stats_log.write(str(n)+") "+i[0]+" : "+str(i[1])+"\n")
		n += 1

	# writing top 10 pages based on PageRank
	stats_log.write("\nTop 10 pages based on PageRank :\n")
	m = 1
	for i in sorted_PR[:10]:
		stats_log.write(str(m)+") "+i[0]+" : "+str(i[1])+"\n")
		m += 1

# end of function calculate_page_rank


# creation of Logs directory, if not present
newpath = r'Logs'
if not os.path.exists(newpath):
	os.makedirs(newpath)

# creating file perplexity_log.txt to write perplexity values
# obtained in each round until convergence for all graphs
perplexity_log = open("Logs/perplexity_log.txt","w")

# pass file_names containing graphs as command line arguments whose page rank needs to be calculated
# run the program as:
# python page_rank.py file_name_1 file_name_2 .... file_name_n
# example : python page_rank.py sample G1 G2

file_names = sys.argv[1:] # file_names is the value of command line arguments array starting from index 1
for file_name in file_names:
	calculate_page_rank(file_name)