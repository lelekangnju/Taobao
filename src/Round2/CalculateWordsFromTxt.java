package Round2;

import java.io.BufferedReader;
import java.io.FileReader;

public class CalculateWordsFromTxt {
	public static void main(String args[]) {
		
		String filePath = "D:\\Dropbox\\Taobao Research\\JMIS\\Experiment Study\\服务器上网页\\16.txt";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line = null;
			int maijiaWords = 0;
			int maijiaHangshu = 0;
			int woWords = 0;
			int woHangshu = 0;
			String tt="";
			while ((line = in.readLine()) != null) {
				//Decide Who is speaker
				String temps[] = line.split(": ");
				if(temps.length!=2){
					System.out.println("Error;Error;Error;ErrorError;ErrorError;Error");
				}else{
					System.out.println(line);
					if(temps[0].equals("我")){
						woHangshu++;
						tt=temps[1].replaceAll("\\pP", "").trim();
						System.out.println(tt);
						woWords = woWords + tt.length(); 
					}else if(temps[0].equals("卖家")){
						maijiaHangshu++;
						tt=temps[1].replaceAll("\\pP", "").trim();
						System.out.println(tt);
						maijiaWords = maijiaWords + tt.length();
					}else{
						System.out.println("没有买家卖家 Error Error Error Error Error Error Error");
					}
				}
				
			}
			System.out.println("卖家行数: "+maijiaHangshu+"; 卖家字符数:" + maijiaWords +"; 买家行数: "+woHangshu + "; 买家字符数: "+woWords);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
