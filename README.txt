README
Cameron O'Leary coleary9@jhu.edu

The auction library I have designed runs most operatons in log n time, 
due to the use of a tree map. I have included in the library a main function
that allows you to interact with the system.  and allows the the input of a bids file. 
I have also created a create bids java class to create a couple 1000 entries to the system.
The functions auction has are:
addItem
endAuction
itemHistory
newBid
all of the above throw an exception if you give them a faulty item name, either by
it already existing for addItem or not existing for the others.
save 
Load
save and load save the auction to a file but are not controlled for the file being corrupted.

JavaDocs give the full picture

As for the consecutive runs I have written a main program along with the function that 
runs the program 100 times for a million random numbers between 0 and 100, 
and returns the number of consecutive runs for the whole program. 
In a way this also tests it as you should get an output that is roughly 19600
which it does. This is becuase the odds of a number being the start of a consecutive run
is  2/100*1/100=2/10000 but becuase you cannot have a consecutive decreasing run 
from 0 or 1 and you cannot have an increasing run from 98 or 99 the odds are
acutally 2/10000 for 96 numbers and 1/10000 for 4 numbers. This gives you average odds
of 1.96/10000. Perfect. 