package Round2;
/*
 * Analyze chat log;
 */
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Round2.WriteStreamAppend;
public class DealChatLogCopy {
	
 
  public static void main(String args[]){
	  String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	  //String filepath = "E://Lele//Taobao//Wangwang记录//丁书彦//刘德春//买家刘德春聊天记录.txt";
	  String filepath = "E://Lele//Taobao//Wangwang记录//丁书彦//丁书彦//买家丁书彦聊天记录.txt";
	  TransferFile(filepath,"丁书彦","刘德春");
  }

  //处理思路，将dialog按照sellername进行分类
  public static void TransferFile(String filepath,String datacollector,String realname){
	  try{
			MySQLConn.connect();
	  }
	  catch(Exception e){
  	   System.out.println("data connection is wrong "+e.toString());
	 }
	  
	  //以下是对文件的处理过程,将用户的初步的file，处理成为一个完善的file，特别是换行问题
	  
	  File file = new File(filepath);
	  String nickname = null; //买家用户名
	  String sellertitle = null;
	  int dialognumber =0;//dialog nummber
	  int flag = 0; //用以判断是否已经拿到了卖家的姓名
	  String[] splitss = new String[50];
      BufferedReader reader = null;
      String sql1= "";
      String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
      try {
    	  
          //System.out.println("以行为单位读取文件内容，一次读一整行：");
          reader = new BufferedReader(new FileReader(file));
          String tempString = "";
          String dialogcontent = "";
          int line = 1;// 一次读入一行，直到读入null为文件结束
          nickname=reader.readLine();//读入首行，买家用户名
          for(int i=0;i<3;i++){
        	  tempString = reader.readLine();//前四行格式文本，先处理掉        	  
          }
          
          line=4;
          int[] newlogline = new int[500];
          int w=0;
          while ((tempString = reader.readLine()) != null) {
        	  line++;
        	  String dateEL = "20[0-1]{2}-[0-9]{2}-[0-9]{2}";
        	  if(tempString.matches(dateEL)){
        		  
        		  newlogline[w]=line;
        		 // System.out.println("This is a new dialog, newlogline["+w+"] is "+line);
        		  w++;
        	  }
           }
        
        
        //补充数据库当中的nickname；
  	    try{
	    	String sql5 = "select nickname from userinfo where datacollector='"+datacollector+"' and realname='"+realname+"'";//查看此人是否已经存在
			MySQLConn.rs=MySQLConn.stmt.executeQuery(sql5);
			if(MySQLConn.rs.next()){
				System.out.println("查到此人");
				String comparenick = MySQLConn.rs.getString(1);
				System.out.println("Comparenick is "+comparenick);
				if(comparenick==null||"".equals(comparenick)){
				   sql5="update userinfo set nickname='"+nickname+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//更新那些缺少nickname的用户
				   System.out.println("update nickname:"+nickname);
				   MySQLConn.update(sql5);
				}
				else{
					if(comparenick.equals(nickname)){
						
					}
					else {
						System.out.println("nickname和数据库中不一致，严重问题");
						WriteStreamAppend.method1(errorlogfilepath,"nickname和数据库中不一致，严重问题"+datacollector+","+realname+"\r\n");
					}
				}
			}
			else{
				System.out.println("没有此人，出错了");
				WriteStreamAppend.method1(errorlogfilepath,"数据库中没有这个用户，严重问题"+datacollector+","+realname+"\r\n");
			}
	    }
	    catch(Exception e){
	    	System.out.println("Exception is "+e.toString());
	    }
	    
	    
	    // 更新数据库当中没有用户有多少条log记录
	    sql1="update userinfo set logamount='"+w+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//更新那些缺少nickname的用户
	    try{
      	  MySQLConn.update(sql1);
        }catch(Exception e){
        	WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
        	System.out.println("exception is :"+e.toString());
        }  
	    
	    
	    
	    
	    
	    //System.out.println("Dialog number is "+w);
          /*
          for(int i=0;i<w;i++){
        	  System.out.println("New Dialog line number is "+newlogline[i]);
          }
          */
          reader = new BufferedReader(new FileReader(file));//重新读文件
          DialogBean[] dialogben = new DialogBean[w];
          DialogContentBean[] contentbean = new DialogContentBean[500];
          for(int i=0;i<4;i++){
        	  reader.readLine();//前四行格式文本，先处理掉        	  
          }
          //第一段数据,放置第一个bean
          //System.out.println("第-"+1+"段-的数据");
          dialogcontent="";
          dialogben[0]=new DialogBean();
          dialogben[0].setNickname(nickname);
          flag=0;
          int ee=0;//用来设置dialogcontentbean
          for(int i=0;i<newlogline[1]-5;i++){
        	  if(i==0){
        		  tempString=reader.readLine();//获得第一行的Date信息
        		  //dialogcontent=dialogcontent+tempString+"\n";//第一行date信息不加入content当中
        		  dialogben[0].setLogdate(tempString);
        	  }else{
        	  tempString=reader.readLine();//获得第一段log的数据
        	  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
			  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
			  if(sellernameMatcher.find()){
			        		  System.out.println("find the name : "+sellernameMatcher.group(1));//get the username
			        		  if(flag==0){
			        			  if(nickname.equals(sellernameMatcher.group(1))){//确保发现的是卖家姓名，而非卖家姓名
				        	      }
				        		  else {
				        			  sellertitle=sellernameMatcher.group(1);
				        			  dialogben[0].setSellertitle(sellertitle);
				        			  flag=1;
				        		  }
			        		  }
			        		//从这里开始处理chatcontent内容，从中提取数据
			        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
			        		  if(splitss.length>=2){
			        			  contentbean[ee] = new DialogContentBean();
			        			  contentbean[ee].setTime(splitss[0]);
			        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//设置这行对话者姓名，不管是卖方还是买方;
			        			  String temp="";
			        			  for(int tt=1;tt<splitss.length;tt++)
			        				  temp=temp+splitss[tt];
			        			  contentbean[ee].setContent(temp);
			        			  ee++;
			        		  }
			        		  else  System.out.println("这里出现严重错误，因为split小于2");
			        		  
        	      			  
			      }
				 else {
					 //System.out.println("cannot find the name;出错了");
					 contentbean[ee-1].setContent(contentbean[ee-1].getContent()+"\n"+tempString);
				 }
	  
        	  dialogcontent=dialogcontent+tempString+"\n";
        	  }
        	  //System.out.println(tempString);
          }
          //contentbean[],sellertitle,nickname
          
          dialogben[0].setDialogcontent(dialogcontent);
          /*
          System.out.println("logdate is "+dialogben[0].getLogdate());
          System.out.println("nickname is "+dialogben[0].getNickname());
          System.out.println("sellertitle is "+dialogben[0].getSellertitle());
          System.out.println("content is "+dialogben[0].getDialogcontent());
          */
         
          sql1 =  "insert into dialog(nickname,sellertitle,logdate,dialogcontent) " +
          		"values('"+dialogben[0].getNickname()+"','"+dialogben[0].getSellertitle()+"','"+dialogben[0].getLogdate()+"','"+dialogben[0].getDialogcontent()+"')";
          System.out.println(sql1);
          try{
        	  MySQLConn.insert(sql1);
          }catch(Exception e){
        	  System.out.println("exception is :"+e.toString());
          }
          
          DealChatLogContent.ContentDataGet(contentbean, nickname, sellertitle,ee);
          
         
          for(int i=1;i<w-1;i++){
        	  //System.out.println("第-"+(i+1)+"段-的数据");
        	  dialogcontent="";
              dialogben[i]=new DialogBean();
              dialogben[i].setNickname(nickname);
              flag=0;
              sellertitle="";
              ee=0;
        	  for(int j=0;j<newlogline[i+1]-newlogline[i];j++){
            	  if(j==0){
            		  tempString=reader.readLine();//获得第一行的Date信息
            		  dialogben[i].setLogdate(tempString);
            		 // dialogcontent=dialogcontent+tempString+"\n";
            	  }else{
            	  tempString=reader.readLine();//获得第一段log的数据
    			  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
    			  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
    			       if(sellernameMatcher.find()){
    			        		  //System.out.println("find the name : "+sellernameMatcher.group(1));//get the username
    			        		  if(flag==0){
    			        			  if(nickname.equals(sellernameMatcher.group(1))){//确保发现的是卖家姓名，而非卖家姓名
    				        	      }
    				        		  else {
    				        			  sellertitle=sellernameMatcher.group(1);
    				        			  dialogben[i].setSellertitle(sellertitle);
    				        			  flag=1;
    				        		  }
    			        		  }
    			        		  
    			        		  //从这里开始处理chatcontent内容，从中提取数据
    			        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
    			        		  if(splitss.length>=2){
    			        			  contentbean[ee] = new DialogContentBean();
    			        			  contentbean[ee].setTime(splitss[0]);
    			        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//设置这行对话者姓名，不管是卖方还是买方;
    			        			  String temp="";
    			        			  for(int tt=1;tt<splitss.length;tt++)
    			        				  temp=temp+splitss[tt];
    			        			  contentbean[ee].setContent(temp);
    			        			  ee++;
    			        		  }
    			        		  else  System.out.println("这里出现严重错误，因为split小于2");
    			        		  
    			        		 
    			        }
    					else {
    						//System.out.println("cannot find the name;");
    						contentbean[ee-1].setContent(contentbean[ee-1].getContent()+"\n"+tempString);
    					}
            	  dialogcontent=dialogcontent+tempString+"\n";
            	  }
        		  //System.out.println(tempString);
        	  }
        	  dialogben[i].setDialogcontent(dialogcontent);
        	  /*
              System.out.println("logdate is "+dialogben[i].getLogdate());
              System.out.println("nickname is "+dialogben[i].getNickname());
              System.out.println("sellertitle is "+dialogben[i].getSellertitle());
              System.out.println("content is "+dialogben[i].getDialogcontent());
              */
        	  
              sql1 =  "insert into dialog(nickname,sellertitle,logdate,dialogcontent) " +
        		"values('"+dialogben[i].getNickname()+"','"+dialogben[i].getSellertitle()+"','"+dialogben[i].getLogdate()+"','"+dialogben[i].getDialogcontent()+"')";
		        System.out.println(sql1);
		        try{
		      	  MySQLConn.insert(sql1);
		        }catch(Exception e){
		      	  System.out.println("exception is :"+e.toString());
		        }
        
              DealChatLogContent.ContentDataGet(contentbean, nickname, sellertitle,ee);

          }
          
          
          
          //最后一段数据

          
          //System.out.println("第-"+1+"段-的数据");
          dialogcontent="";
          dialogben[w-1]=new DialogBean();
          dialogben[w-1].setNickname(nickname);
          flag=0;
          tempString=reader.readLine();//获得第一行的Date信息
		//  dialogcontent=dialogcontent+tempString+"\n";
		  dialogben[w-1].setLogdate(tempString);
		  sellertitle="";
		  ee=0;
          while ((tempString = reader.readLine()) != null) {
	        	  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
	        	  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
	        	  if(sellernameMatcher.find()){
	        		  //System.out.println("find the name : "+sellernameMatcher.group(1));//get the username
	        		  
	        		  if(flag==0){
	        			  if(nickname.equals(sellernameMatcher.group(1))){//确保发现的是卖家姓名，而非卖家姓名
		        	      }
		        		  else {
		        			  sellertitle=sellernameMatcher.group(1);
		        			  dialogben[w-1].setSellertitle(sellertitle);
		        			  flag=1;
		        		  }
	        		  }
	        		  
	        		  
	        		//从这里开始处理chatcontent内容，从中提取数据
	        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
	        		  if(splitss.length>=2){
	        			  contentbean[ee] = new DialogContentBean();
	        			  contentbean[ee].setTime(splitss[0]);
	        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//设置这行对话者姓名，不管是卖方还是买方;
	        			  String temp="";
	        			  for(int tt=1;tt<splitss.length;tt++)
	        				  temp=temp+splitss[tt];
	        			  contentbean[ee].setContent(temp);
	        			  ee++;
	        		  }
	        		  else  
	        			  System.out.println("这里出现严重错误，因为split小于2");

	        	  }
					else {
						//System.out.println("cannot find the name;");
						contentbean[ee-1].setContent(contentbean[ee-1].getContent()+"\n"+tempString);
					}
    	  
        	  dialogcontent=dialogcontent+tempString+"\n";
          }
          
          dialogben[w-1].setDialogcontent(dialogcontent);
          /*
          System.out.println("logdate is "+dialogben[w-1].getLogdate());
          System.out.println("nickname is "+dialogben[w-1].getNickname());
          System.out.println("sellertitle is "+dialogben[w-1].getSellertitle());
          System.out.println("content is "+dialogben[w-1].getDialogcontent());
          */
          
          sql1 =  "insert into dialog(nickname,sellertitle,logdate,dialogcontent) " +
    		"values('"+dialogben[w-1].getNickname()+"','"+dialogben[w-1].getSellertitle()+"','"+dialogben[w-1].getLogdate()+"','"+dialogben[w-1].getDialogcontent()+"')";
		    System.out.println(sql1);
		    try{
		  	  MySQLConn.insert(sql1);
		    }catch(Exception e){
		  	  System.out.println("exception is :"+e.toString());
		    }
    
          DealChatLogContent.ContentDataGet(contentbean, nickname, sellertitle,ee);
          
          reader.close();
          

  		try{
  			MySQLConn.stmt.close();
  			MySQLConn.con.close();
  		}
  			catch(Exception e){
  				System.out.println("Close MySQL is wrong");
  		}
          
          
          
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e1) {
              }
          }
      }
  }
}
