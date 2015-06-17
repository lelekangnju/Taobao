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
		  
		  //以下是对文件的处理过程,将用户的初步的file，处理成为一个完善的file，特别是换行问题
		  
		  File file = new File(filepath);
		  String nickname = null; //买家用户名
		  String tempString = "";
	      BufferedReader reader = null;
	      String sql1= "";
	      int w=0;//w用来表示一共有多少个dialog
	      
	      try {
	          //System.out.println("以行为单位读取文件内容，一次读一整行：");
	          reader = new BufferedReader(new FileReader(file));
	          
	          int line = 1;// 一次读入一行，直到读入null为文件结束
	          nickname=reader.readLine();//读入首行，买家用户名
	          for(int i=0;i<3;i++){
	        	  tempString = reader.readLine();//前四行格式文本，先处理掉        	  
	          }
	          
	          line=4;
	          int[] newlogline = new int[10000];//整个文档不能超过10000段数据
	          w=0;//获得一共有多少段session
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
		    //W是一共有多少段记录；newlogline记录了每段开始的行号
	        
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
