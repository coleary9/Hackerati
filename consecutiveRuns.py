import math
def consecutiveRuns(data):
	startsOfRuns=list()
	for i in range(len(data)-2):
		if math.fabs(data[i]-data[i+1])==1:
			if data[i+2]+data[i]==data[i+1]*2:
				startsOfRuns.append(i)
	return startsOfRuns
def main():
    print(consecutiveRuns([1,2,3]))

if __name__ == "__main__":
    main()
