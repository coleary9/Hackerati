import java.util.*;
import java.io.*;
public class CreateBids {
	public static void main(String[] args){
		try {
		PrintWriter out = new PrintWriter(new FileWriter("biddingFile.txt")); 
		out.println("add joe 150 ");
		out.println("add bob 2000 ");
		out.println("add leslie 100000 ");
		for (int i=0;i<10000;i++) {
		out.println("bid"+ " joe " + Integer.toString(i) + " darren ");
		if(i%2==1) {
		out.println("bid"+ " bob " + Integer.toString(i) + " men ");
	    }
	    if(i%5==1) {
		out.println("bid"+ " leslie " + Integer.toString(i) + " red ");
	    }
		}
		out.print("save ");
		out.close();
		}catch(IOException e){
			System.out.println("saving broke");
		}
	}

}