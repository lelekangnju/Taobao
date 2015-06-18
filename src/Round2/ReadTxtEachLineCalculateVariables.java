package Round2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTxtEachLineCalculateVariables {
	public static void main(String[] args){
		  //�����Ƕ��ļ��Ĵ������,���û��ĳ�����file�������Ϊһ�����Ƶ�file���ر��ǻ�������
		
		 try{
				MySQLConn.connect();
		  }
		  catch(Exception e){
	  	   System.out.println("data connection is wrong "+e.toString());
		 }
		  
		  File file = new File("E:/Lele/Taobao/CombinedEachLine.txt");
		  String errorlogfilepath = "e:/Lele/Taobao/pathEachLineError.txt";
		  String tempString = "";
	      BufferedReader reader = null;
	      BufferedReader reader2 = null;
	      String sql1= "";
	      int w=0;//w������ʾһ���ж��ٸ�dialog
	      int startrecord[] = new int[5000] ;
	      
	      try {
	          //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	          reader = new BufferedReader(new FileReader(file));
	          int line = 1;// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	          int xyz = 0;
	          while ((tempString = reader.readLine()) != null) {
	        	  
	        	  if("20150617".equalsIgnoreCase(tempString.split("_")[0])){
	        		  System.out.println(tempString);
	        		  startrecord[xyz]=line;
	        		  xyz++;
	        	  }
	        	  line++;
	           }
	          startrecord[xyz]=line;
	          
	          for(int ppp=0;ppp<xyz;ppp++){
	        	  System.out.println("Start Line " + startrecord[ppp]);
	          }
	          
	          
		  String variablenames[] = new String[1000];
		  int variablenumber =0;
		  
	          for(int j=0;j<xyz;j++){
	        	  
	        	  //System.out.println(startrecord[j]);
	        	  reader2 = new BufferedReader(new FileReader(file));
    		  for(int iii=0;iii<startrecord[j]-1;iii++){
    			  reader2.readLine();
    		  }
    		  int rrr=0;
    		  String onerecord[] = new String[startrecord[j+1]-startrecord[j]];//ÿ����¼������1000��
    		  for(;rrr<startrecord[j+1]-startrecord[j];rrr++){
    			  onerecord[rrr] = reader2.readLine();
    		  }
    		  //Deal with one record
    		String randomstring = onerecord[0];// RandomString
    		String results[][] = Analyzeonerecord(onerecord);
    		    		
    		//Here Results can be used to calculate variables  
    		  
	         }//end one specific record
	         
	        
		    reader.close();
	        try{
	  			MySQLConn.stmt.close();
	  			MySQLConn.con.close();
	  		}
	  			catch(Exception e){
	  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	  				System.out.println("Close MySQL is wrong");
	  		}
	          
	          
	      
			  } catch (IOException e) {
				  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	              e.printStackTrace();
	      } finally {
	          if (reader != null) {
	              try {
	                  reader.close();
	              } catch (IOException e1) {
	            	  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e1.toString()+"\r\n");
	              }
	          }
	      }
	  }
	
	
     public static String[][] Analyzeonerecord(String onerecord[]){
		
		String result[]=onerecord;
		String temp = "";
		int dynamiclength = onerecord.length;
		String arrangedrecord[][] = new String[dynamiclength][12];
		int arrangeline =0;
		String pirorWho = "";
		for(int i=1;i<dynamiclength;i++){
			if("".equals(onerecord[i])|"\r\n".equals(onerecord[i]))break;
			temp = onerecord[i];
			String allinfo[] = SplitOneLine(temp);
			arrangedrecord[arrangeline++]=allinfo;
		}
		String returnValue[][] = new String[arrangeline][12];
		for(int w=0;w<arrangeline;w++){
			returnValue[w] = arrangedrecord[w];
		}
		return returnValue;
	}
	
	
	public static String[] SplitOneLine(String oneline){
		String allinfo[] = new String[12];
		allinfo =oneline.split("\\, ");
		//LineCombine, LineNumber,Who,WordNumber,then 8 variables
		return allinfo;
	}
	
	
}
