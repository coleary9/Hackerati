import java.lang.Math;

public class HackeratiRuns {
	
	public static boolean isStartOfrun(int[] dataset, int index) {
		if(Math.abs(dataset[index]-dataset[index+1])==1) {
			if((dataset[index+2] + dataset[index])==dataset[index+1]*2){
				return true;
			}
		}
		return false;
	}

	public static int[] cutArray(int[] consecutiveStarts, int pos) {
		int[] copy=new int[pos];
		for (int i=0;i<pos;i++) {
			copy[i]=consecutiveStarts[i];
		}
		return copy;
	}

	public static int[] findConsecutiveRuns(int[] dataset) {
		int [] consecutiveStarts=new int[dataset.length-2];
		int pos=0;
		for (int i=0;i<dataset.length-2;i++) {
			if(isStartOfrun(dataset, i)) {
				consecutiveStarts[pos]=i;
				pos++;
			}
		}
		if(pos==0) {
			return null;
		}
		return cutArray(consecutiveStarts,pos);
	}
	
	public static void main(String[] args) {
		int[] dataset={0,1,2,0,0};
		int [] forPrint=findConsecutiveRuns(dataset);
		for (int i=0; i<forPrint.length;i++) {
			System.out.println(forPrint[i]);
		}

	}
}