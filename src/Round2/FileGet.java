package Round2;

//import DealChatLog;

import java.io.*;

public class FileGet {
	public static int tradenumber =0;
	public static int itemnumber = 0;
      public static void main(String[] args){
    	  String logfilepath = "E:/Lele/Taobao/insertlog.txt";
    	  /*
    	   * String fileplace = "D:\\李冬\\已买到的宝贝1.htm";
    	   * Crawler.CrawlerBoughtProduct(fileplace);
    	   */
    	 try{
    		 String path = "E:\\Lele\\Taobao\\DataProcess\\Wangwang";
    		 String filepath ="";
    		 String filename="";
    		 int datasamplenumber=0;
    		 File file = new File(path);
    		 
    		 if(!file.isDirectory()){
    			 System.out.println("This is not a dictionary");
    		 }
    		 
    		 else if(file.isDirectory()){
    			 //System.out.println("This is a dictionary");
    			 String[] filelist = file.list();
    			 int foldernumber=0;
    			 foldernumber=filelist.length;
    			 //foldernumber=4;
    			 for(int i=0; i<foldernumber;i++){
    				 
    				 System.out.println("The collector is"+filelist[i].toString());
    				 File mycontacter = new File(path + "\\" + filelist[i]);
    				 if(!mycontacter.isDirectory()){
    	    			 //System.out.println(mycontacter.getName()+"This is not a dictionary");
    	    		 }
    				 else if(mycontacter.isDirectory()){// 这里就进入了第二层，就是进入到了我的每个数据收集者的文件夹下
    					 //System.out.println("This is a dictionary");
    	    			 String[] everyone = mycontacter.list();//得到每个用户了
    	    			 WriteStreamAppend.method1(logfilepath,"My datacollector:"+mycontacter.getName()+"\r\n ");
    	  			 
    	    			 for(int j=0;j<everyone.length;j++){
    	    				 datasamplenumber++;
    	    				// System.out.println("用户为:"+mycontacter.getName()+"----"+everyone[j].toString());
    	    				 	 File lastfiles = new File(path+"\\"+mycontacter.getName()+"\\"+everyone[j].toString());
    	    				 	 WriteStreamAppend.method1(logfilepath,"Everyone Realname:"+everyone[j].toString()+"\r\n ");
    	    				 	 if(lastfiles.isDirectory()){
    	       	    			     String[] everyonefile = lastfiles.list();
    	       	    			     for(int w=0;w<everyonefile.length;w++){//得到每个用户的文件
    	       	    			    	 //System.out.println("文件为:"+mycontacter.getName()+"-----"+everyone[j].toString()+"------"+everyonefile[w].toString());
    	       	    			    	 File endfile = new File(path+"\\"+mycontacter.getName()+"\\"+everyone[j].toString()+"\\"+everyonefile[w].toString());
    	       	    			    	 if(!endfile.isDirectory()){
    	       	    			    		 //System.out.println(endfile.toString());
    	       	    			    	  filepath= path+"\\"+mycontacter.getName()+"\\"+everyone[j].toString()+"\\"+everyonefile[w].toString();
    	       	    			    	  
    	       	    			    	  filename=everyonefile[w].toString();
    	       	    			    	  FileGet.seperateFile(filepath, filename,mycontacter.getName(),everyone[j].toString(),logfilepath);
    	       	    			    	 }
    	       	    			    	 else{
    	       	    			    		 //文件夹处理意见
    	       	    			    	 }
    	       	    			    	 }
    	       	    		     }
    	    				 	 else {
    	    				 		 
    	    				 		 System.out.println("everyone is not a dictionary");
    	    				 	 }
    	    				 	
    	    			 }
    				 }
    			 }
    		 }
    		 System.out.println("data sample number is"+datasamplenumber);
    		 System.out.println("Trade number is"+tradenumber);
    		 System.out.println("Item number is"+itemnumber);
    	 }
    	 catch(Exception e){
    		 System.out.println("Exception is "+e.toString());
    	 }
      }
      
      public static void seperateFile(String filepath,String filename,String datacollector,String realname,String logfilepath){
    	  /*
    	  if(filename.indexOf("已买到的宝贝")!=-1){
    		  System.out.println("filepath is "+filepath+" and filename is "+filename);
    		  WriteStreamAppend.method1(logfilepath,"处理文件："+"filepath is "+filepath+" and filename is "+filename+"\r\n ");
    		  Crawler.CrawlerBoughtProduct(filepath,datacollector,realname);
    	  }
    	  */
    	  if(filename.indexOf("Coding,")!=-1){
    		  System.out.println("filepath is "+filepath+" and filename is "+filename);
    		  WriteStreamAppend.method1(logfilepath,"处理文件："+"filepath is "+filepath+" and filename is "+filename+"\r\n ");
    		  DealChatLog.TransferFile(filepath, datacollector, realname);
    		  
    		  //Crawler.CrawlerBoughtProduct(filepath,datacollector,realname);
    	  }
      }
      
}
