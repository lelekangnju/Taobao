package Round2;

//import DealChatLog;

import java.io.*;

public class GetCommunicationLog {
      
      public static void main(String args[]){
    	      String logpath = "E:\\Wangwang��¼\\������\\����\\2010-10-01��2011-12-025.txt";
    	      String datacollector = "������";
    	      String realname = "����";
    		  System.out.println("filepath is "+logpath);
    		  //WriteStreamAppend.method1("E:\\Wangwang��¼\\������\\����\\2010-10-01��2011-12-025.txt","�����ļ���E:\\Wangwang��¼\\������\\����\\2010-10-01��2011-12-025.txt \r\n ");
    		  DealChatLog.TransferFile(logpath, datacollector, realname);
      }   
}
