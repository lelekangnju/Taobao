package Round2;

import java.io.*;
import java.util.*;

public class ReadFileFromTxt {

	public void readFile(String filePath) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> readFile3(String filePath,int start, int end) {//读取中间这么多行
		ArrayList<String> outputString = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));
			String line = null;
			for (int i=0;i<start;i++){
				//System.out.println(line=in.readLine()+"===========");
			}
			for(int i=start;i<end;i++){
				line = new String(in.readLine().trim());
				outputString.add(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputString;
	}
	
	public void readFile2(String filePath) {
        BufferedReader br;
        String read = "";
        try {
            File file = new File(filePath);
            FileReader fileread = new FileReader(file);
            br = new BufferedReader(fileread);
            while ((read = br.readLine()) != null) {
            	System.out.print(read + "\t\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    static ArrayList<String> ZhuzhangGanzhi;
    static ArrayList<String> ZhuzhangRenwei;
    static ArrayList<String> ZhengmianQinggan;
    static ArrayList<String> ZhengmianPingjia;
    static ArrayList<String> Chengdu;
    static ArrayList<String> FumianQinggan;
    static ArrayList<String> FumianPingjia;
    
	public static void GenerateChineseEmotionalWordsList() {
		String filePath0 = "E:\\Lele\\Taobao\\sentiment\\主张词语(中文),感知.txt";
		ZhuzhangGanzhi = new ReadFileFromTxt().readFile3(filePath0,0,22);
		
		String filePath1 = "E:\\Lele\\Taobao\\sentiment\\主张词语(中文),认为.txt";
		ZhuzhangRenwei = new ReadFileFromTxt().readFile3(filePath1,0,16);
		
		String filePath2 = "E:\\Lele\\Taobao\\sentiment\\正面情感词语（中文）.txt";
		ZhengmianQinggan = new ReadFileFromTxt().readFile3(filePath2,0,828);
		
		String filePath3 = "E:\\Lele\\Taobao\\sentiment\\正面评价词语（中文）.txt";
		ZhengmianPingjia = new ReadFileFromTxt().readFile3(filePath3,0,3716);
		
		String filePath4 = "E:\\Lele\\Taobao\\sentiment\\程度级别词语（中文）.txt";
		Chengdu = new ReadFileFromTxt().readFile3(filePath4,0,219);
		
		String filePath5 = "E:\\Lele\\Taobao\\sentiment\\负面情感词语（中文）.txt";
		FumianQinggan = new ReadFileFromTxt().readFile3(filePath5,0,1245);

		String filePath6 = "E:\\Lele\\Taobao\\sentiment\\负面评价词语（中文）.txt";
		FumianPingjia = new ReadFileFromTxt().readFile3(filePath6,0,3089);
		/*
		for(String temp:FumianPingjia)System.out.println(temp);
		System.out.println(FumianPingjia.size());
		*/
	}
	
	
}