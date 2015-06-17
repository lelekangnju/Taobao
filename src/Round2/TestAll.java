package Round2;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestAll {
	public static void main(String[] args){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String nono = String.valueOf(System.nanoTime());
	   System.out.println(timeStamp+nono);
  }
}
