import math
import random
def consecutiveRuns(data):
	startsOfRuns=list()
	for i in range(len(data)-2):
		if math.fabs(data[i]-data[i+1])==1:
			if data[i+2]+data[i]==data[i+1]*2:
				startsOfRuns.append(i)
	return startsOfRuns
def main():
	total=0
	for i in range(100):
		data=list()
		for i in range(1000000):
			data.append(random.randint(0,99))
		total+=len(consecutiveRuns(data))
	print(total)


if __name__ == "__main__":
    main()
