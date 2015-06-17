package Round2;

//import DealChatLog;

import java.io.*;

public class GetCommunicationLog {
      
      public static void main(String args[]){
    	      String logpath = "E:\\Wangwang记录\\蔡瑞林\\陈玲\\2010-10-01～2011-12-025.txt";
    	      String datacollector = "蔡瑞林";
    	      String realname = "陈玲";
    		  System.out.println("filepath is "+logpath);
    		  //WriteStreamAppend.method1("E:\\Wangwang记录\\蔡瑞林\\陈玲\\2010-10-01～2011-12-025.txt","处理文件：E:\\Wangwang记录\\蔡瑞林\\陈玲\\2010-10-01～2011-12-025.txt \r\n ");
    		  DealChatLog.TransferFile(logpath, datacollector, realname);
      }   
}
