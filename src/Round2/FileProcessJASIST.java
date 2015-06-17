package Round2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Backup.MySQLConn;

public class FileProcessJASIST {
	public static void TransferFile(String filepath,String datacollector,String realname){
		  String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
		  try{
				MySQLConn.connect();
		  }
		  catch(Exception e){
	  	   System.out.println("data connection is wrong "+e.toString());
	  	   WriteStreamAppend.method1(errorlogfilepath,"Transfer File exception is :"+e.toString()+datacollector+","+realname+"\r\n");
		 }
		  
		  //�����Ƕ��ļ��Ĵ������,���û��ĳ�����file�������Ϊһ�����Ƶ�file���ر��ǻ�������
		  
		  File file = new File(filepath);
		  String nickname = null; //����û���
		  String tempString = "";
	      BufferedReader reader = null;
	      String sql1= "";
	      int w=0;//w������ʾһ���ж��ٸ�dialog
	      
	      try {
	          //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	          reader = new BufferedReader(new FileReader(file));
	          
	          int line = 1;// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	          nickname=reader.readLine();//�������У�����û���
	          for(int i=0;i<3;i++){
	        	  tempString = reader.readLine();//ǰ���и�ʽ�ı����ȴ����        	  
	          }
	          
	          line=4;
	          int[] newlogline = new int[10000];//�����ĵ����ܳ���10000������
	          w=0;//���һ���ж��ٶ�session
	          while ((tempString = reader.readLine()) != null) {
	        	  line++;
	        	  String dateEL = "20[0-1]{2}-[0-9]{2}-[0-9]{2}";
	        	  if(tempString.matches(dateEL)){
	        		  newlogline[w]=line;
	        		 // System.out.println("This is a new dialog, newlogline["+w+"] is "+line);
	        		  w++;
	        	  }
	           }
	          newlogline[w]=line+1;
	        
	        	    
		    DealChatLogWithoutTime.DealChatLogWithoutTimeD(filepath, nickname, w, newlogline,datacollector,realname);
		    //W��һ���ж��ٶμ�¼��newlogline��¼��ÿ�ο�ʼ���к�
	        
		    reader.close();
	        try{
	  			MySQLConn.stmt.close();
	  			MySQLConn.con.close();
	  		}
	  			catch(Exception e){
	  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	  				System.out.println("Close MySQL is wrong");
	  		}
	          
	          
	      
			  } catch (IOException e) {
				  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	              e.printStackTrace();
	      } finally {
	          if (reader != null) {
	              try {
	                  reader.close();
	              } catch (IOException e1) {
	            	  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e1.toString()+datacollector+","+realname+"\r\n");
	              }
	          }
	      }
	  }
}
