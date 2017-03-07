aList = [1, 2, 3, 4, 5];
print(type(aList))
print(aList)
print(aList[4])
aList.append(6)
aList += [7, 8]
print(aList)
aSlice = aList[1:4]
print(aSlice)

class MyList(list):
	def getReversed(self):
		reversed = self[:]
		reversed.reverse()
		return reversed

list2 = MyList(aList)
print(type(list2))
print(list2.getReversed())