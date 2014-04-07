import java.util.*;
import java.io.*;
public class auction  {
private static class defaultComparison implements Comparator<String> {
        private defaultComparison() {

        }
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

static TreeMap<String, Item> forSale=new TreeMap<String, Item>(new defaultComparison());
private static class Item {
		int reserve=0;
		String bidder;
		int currentBid=0;
		boolean live=true;
		boolean success;
		int numBids;
		public Item(int newReserve) {
			reserve=newReserve;
			numBids=0;
		}
	}

	/* adds an item, throws an Invalid ID exception if the item exists already
	@param newName                the name of the item being bid on
	@param newReserve			  the reserve on the item
	@throws InvalidIdException    thrown if item doesn't exist
 */
	public static void addItem(String newName, int newReserve)  throws InvalidIdException {
		if(forSale.get(newName)!=null) {
			throw new InvalidIdException();
		}
		forSale.put(newName, new Item(newReserve));
	}

/*	private static Item findItem(String name) {
		for (Item item: forSale) {
			if (item.name.equals(name))
			{
				return item;
			}
		}
		return null;
	}*/

	/* ends an items auction, throws an Invalid ID exception if the item doesn't exist. 
	Sets success to true if the bid was above reserve,
	@param name               the name of the item being ended
	@return 				  true if the auction is ended, false if it's already been ended
	@throws InvalidIdException    thrown if item doesn't exist
    */
	public static boolean endAuction(String name)  throws InvalidIdException {
		Item inQuestion=forSale.get(name);
		if (inQuestion==null) {
			throw new InvalidIdException();
		}
		if (!inQuestion.live) {
			return false;
		}
		
		inQuestion.live=false;
		if(inQuestion.currentBid>=inQuestion.reserve) {
		inQuestion.success=true;

		return true;
		}
		else {
		inQuestion.success=false;
		
		return true;
		}
	}

	public static String itemHistory(String name) throws InvalidIdException {
		Item inQuestion=forSale.get(name);
		if (inQuestion==null) {
			throw new InvalidIdException();
		}
		String buffer;
		if (inQuestion.live) {
			if (inQuestion.bidder==null) {
				return " the auction is open but no bids have been recieved, the reserve is " + Integer.toString(inQuestion.reserve);
			}
			return "the auction is live the reserve price is " + Integer.toString(inQuestion.reserve) + 
					" the current highest bid is " + Integer.toString(inQuestion.currentBid) +" placed by " + inQuestion.bidder;
		}
		else if(inQuestion.success) {
			return "the item was sold to " + inQuestion.bidder + " for $ " + Integer.toString(inQuestion.currentBid);
		}
		else {
			return "the item was not sold but the auction completed";
		}
		 

	}
/* adds a bid to an item, throws an Invalid ID exception if the item does not exist returns true if bid is accepted, false if it isn't
	@param name                   the name of the item being bid on
	@param bid                    the integer value of the bid
	@param bidder                 the name of the bidder
	@return                       true if the bid is higher than the current one and the item is live
	@throws InvalidIdException    thrown if item doesn't exist
 */

	public static boolean newBid(String name, int bid, String bidder) throws InvalidIdException
	{
		Item inQuestion=forSale.get(name);
		if (inQuestion==null) {
			throw new InvalidIdException();
		}
		if (bid<=inQuestion.currentBid || !inQuestion.live) {
		return false;
		}
		inQuestion.currentBid=bid;
		inQuestion.bidder=bidder;
		inQuestion.numBids++;
		return true;
	}

/* saves the auction to a file(saveAuction.txt) for latter use  */
	public static void save() {
		try {
		PrintWriter out = new PrintWriter(new FileWriter("saveAuction.txt")); 
		
		for (Map.Entry<String, Item> entry: forSale.entrySet()) {
		Item item=entry.getValue();
		out.println(entry.getKey()+ " " + Integer.toString(item.reserve) + " " +item.bidder + " " + 
			Integer.toString(item.currentBid) + " " + String.valueOf(item.live) + " " + 
			String.valueOf(item.success) +" " + Integer.toString(item.numBids));
		}
		out.close();
		}catch(IOException e){
			System.out.println("saving broke");
		}
	}

/* loads from a saveAuction.txt file, will do nothing if doesn't exist. 
   IS NOT CAPABLE OF IGNORING A WRONGLY FORMATTED FILE please only use with file created by save function
*/
	public static void load() {
		try {
		BufferedReader in = new BufferedReader(new FileReader("saveAuction.txt"));
		String line;
		while ((line = in.readLine())!=null) {
		String [] inputs=line.split("\\s");
		if((forSale.get(inputs[0]))!=null) {
			continue;
		}
		else {
		Item temp=new Item(Integer.parseInt(inputs[1]));
		temp.bidder=inputs[2];
		temp.currentBid=Integer.parseInt(inputs[3]);
		temp.live=Boolean.parseBoolean(inputs[4]);
		temp.success=Boolean.parseBoolean(inputs[5]);
		temp.numBids=Integer.parseInt(inputs[6]);
		forSale.put(inputs[0],temp);
		}
		}}catch(IOException e){
			//nothing
		}
	}

/* a main method that interacts with the functions declared in the library, not idiot proof. Will make sure things are formatted
correctly but does not catch the invalid ID exception(to show that it is thrown when an invalid id is input). Gets input from
standard in. Read System.out prints for instructions */
	public static void main(String[] args) {
		System.out.println("Welcome to the auction house: type 'bid' to bid, then an item name and price and bidderName");
		System.out.println("'add' to add an item, with an item name and reserve price after");
	 	System.out.println("'enquire' for item history with it's name, and 'end' to end an auction, with the name of the auction");
		System.out.println("calling 'finish' will finish the program 'save' and 'load' will store the auctions");
		Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] words = s.split("\\s");
            switch (words[0]) {
            	case "bid": 
            		if(words.length!=4) { 
            			System.out.println("not enough arguments");
            		}
            		else {
            			try {
            			newBid(words[1], Integer.parseInt(words[2]), words[3]);
            			}
            			catch(NumberFormatException e) {
            				System.out.println("the second word must be an int");
            			}
            		}
            		break;
            	case "add": 
            		if(words.length!=3) { 
            			System.out.println("not enough arguments");
            		}
            		else {
            			try {
            			addItem(words[1], Integer.parseInt(words[2]));
            			}
            			catch(NumberFormatException e) {
            				System.out.println("the second word must be an int");
            			}
            		}
            		break;
            	case "enquire": 
            		if(words.length!=2) { 
            			System.out.println("not enough arguments");
            		}
            		else {
            			System.out.println(itemHistory(words[1]));
            		}
            		break;
            	case "end": 
            		if(words.length!=2) { 
            			System.out.println("not enough arguments");
            		}
            		else {
            			endAuction(words[1]);
            		}
            		break;
            	case "save": 
            			save();
            		break;
            	case "load": 
            			load();
            		break;
            	case "finish":
            			System.exit(0);
            	default:
            		System.out.println("not correct input");
            		break;

            }
        }
	}
}