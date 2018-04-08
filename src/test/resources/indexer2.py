import os

# function to return index of key value pair if present, -1 if not present
def find(lst, key, value):
    for i, dic in enumerate(lst):
        if dic[key] == value:
            return i
    return -1

def indexer():
	path = r'Tokenization_Outputs'
	# dictionary to store unigrams' docid and tf
	tokens_dict = {}
	# dictionary to store bigrams' docid and tf
	bigram_tokens_dict = {}
	# dictionary to store trigrams' docid and tf
	trigram_tokens_dict = {}

	for file in os.listdir(path):
		current_file = os.path.join(path, file)
		print(current_file)
		content = open(current_file, 'r').read()
		# split content by space to get list of tokens
		tokens = content.split(' ')
		# remove all empty tokens
		tokens = []
		for i in range(0,len(w)):
			if w != "":
				tokens.append(w[i])
		# trim .txt from the end of file to get just the file_name
		file_name = file[:-4]

		for i in range(0,len(tokens)):
			# if tokens[i] already in dict
			if tokens[i] in tokens_dict:
				# find index
				f_index = find(tokens_dict[tokens[i]],"docid",file_name)
				if f_index == -1: # term present, key value pair not found
					tokens_dict[tokens[i]].append({"docid":file_name,"tf":1})
				else: # key value pair already present, increment tf
					tokens_dict[tokens[i]][f_index]['tf']+=1
			else:
				# create new term as key
				tokens_dict[tokens[i]] = [{"docid":file_name,"tf":1}]

		bigrams = []
		# generate bigrams
		content_list = content.split()
		for i in range(len(content_list)-1):
			bigrams.append(content_list[i]+" "+content_list[i+1])

		i = 0
		while i != bigrams.length:
			# if bigrams[i] already in dict
			if bigrams[i] in bigram_tokens_dict:
				# find index
				f_index = find(bigram_tokens_dict[bigrams[i]],"docid",file_name)
				if f_index == -1: # term present, key value pair not found
					bigram_tokens_dict[bigrams[i]].append({"docid":file_name,"tf":1})
				else: # key value pair already present, increment tf
					bigram_tokens_dict[bigrams[i]][f_index]['tf']+=1
			else:
				# create new term as key
				bigram_tokens_dict[bigrams[i]] = [{"docid":file_name,"tf":1}]
			i += 1

		trigrams = []
		# generate trigrams
		for i in range(len(content_list)-2):
			trigrams.append(content_list[i]+" "+content_list[i+1]+" "+content_list[i+2])

		j = 0
		while j != trigrams.length:
			# if trigrams[j] already in dict
			if trigrams[j] in trigram_tokens_dict:
				# find index
				f_index = find(trigram_tokens_dict[trigrams[j]],"docid",file_name)
				if f_index == -1: # term present, key value pair not found
					trigram_tokens_dict[trigrams[j]].append({"docid":file_name,"tf":1})
				else: # key value pair already present, increment tf
					trigram_tokens_dict[trigrams[j]][f_index]['tf']+=1
			else:
				# create new term as key
				trigram_tokens_dict[trigrams[j]] = [{"docid":file_name,"tf":1}]

	# file for storing total number of unique unigrams, bigrams, trigrams
	global_name = open("Global_statistics.txt","w")

	# writing unigrams
	name = open("Indexing_Outputs/Unigrams.txt","w")
	for key,value in sorted(tokens_dict.items()):
		name.write(key+" ->")
		for dic in value:
			flag = True
			for k,v in dic.items():
				if flag:
					name.write(" ("+str(v)+",")
					flag = False
				else:
					name.write(str(v)+")")
					flag = True
		name.write("\n")
	name.close()
	# writing number of unigrams
	global_name.write("Total number of unique unigrams : "+str(len(tokens_dict)))

	# writing bigrams
	bi_name = open("Indexing_Outputs/Bigrams.txt","w")
	for key,value in sorted(bigram_tokens_dict.items()):
		bi_name.write(key+" ->")
		for dic in value:
			flag = True
			for k,v in dic.items():
				if flag:
					bi_name.write(" ("+str(v)+",")
					flag = False
				else:
					bi_name.write(str(v)+")")
					flag = True
		bi_name.write("\n")
	bi_name.close()
	# writing number of bigrams
	global_name.write("\nTotal number of unique bigrams : "+str(len(bigram_tokens_dict)))

	# for writing document frequency table for trigrams
	tri_dft = open("Frequency_Tables/Trigrams_Document_Frequency_Table.txt","w")
	tri_dft.write("Format : Term -> List of Document ID(s) -> Document Frequency\n")

	# writing trigrams
	tri_name = open("Indexing_Outputs/Trigrams.txt","w")
	for key,value in sorted(trigram_tokens_dict.items()):
		tri_name.write(key+" ->")
		rhs = []
		rhs.append([]) # docid(s)
		rhs.append(len(value))

		for dic in value:
			flag = True
			for k,v in dic.items():
				if flag:
					tri_name.write(" ("+str(v)+",")
					rhs[0].append(v)
					flag = False
				else:
					tri_name.write(str(v)+")")
					flag = True

		# write document frequency table for trigrams
		tri_dft.write("\n"+key+" -> "+str(rhs[0])+" -> "+str(rhs[1]))
		tri_name.write("\n")
	tri_dft.close()
	tri_name.close()
	# writing number of trigrams
	global_name.write("\nTotal number of unique trigrams : "+str(len(trigram_tokens_dict)))
	global_name.close()


# run the program as:
# python task2.py

newpath = r'Indexing_Outputs'
if not os.path.exists(newpath):
	os.makedirs(newpath)

newpath = r'Frequency_Tables'
if not os.path.exists(newpath):
	os.makedirs(newpath)

indexer()