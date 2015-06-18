package Round2;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestAll {
	public static void main(String[] args){

		String temp = "2, 0, Buyer, 23, 0, 0, 0, 1, 0, 0, 0, 17";
		String allinfo[] = new String[12];
		allinfo = temp.split("\\, ");
		for(String i:allinfo){
			System.out.println(i);
		}
	}
	
}
