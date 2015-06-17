package Round2;
/*
 * Analyze chat log;
 */
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Backup.MySQLConn;
import Round2.WriteStreamAppend;
public class DealChatLog {
	

  public static void main(String args[]){
	  String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	  //String filepath = "E://Lele//Taobao//Wangwang记录//唐琳//lwaiqq999//2010-10-01～2011-12-05.txt";
	  //String filepath = "E://Wangwang记录//蔡瑞林//陈玲//2010-10-01～2011-12-025.txt";
	  String filepath = "E://Lele//Taobao//DataProcess//Wangwang//袁赛泉//saiquan//Coding,saiquan.txt";
	  //String filepath = "E://Lele//Taobao//Wangwang记录//丁书彦//丁书彦//买家丁书彦聊天记录.txt";
	  //TransferFile(filepath,"蔡瑞林","陈玲");
	  TransferFile(filepath,"袁赛泉","saiquan");
  }

  //处理思路，将dialog按照sellername进行分类
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
        
        //补充数据库当中的nickname;
        /*start - 
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
						sql5="update userinfo set nickname='"+nickname+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//更新那些缺少nickname的用户
						   System.out.println("update nickname:"+nickname);
						   MySQLConn.update(sql5);
						//WriteStreamAppend.method1(errorlogfilepath,"nickname和数据库中不一致，严重问题"+datacollector+","+realname+"\r\n");
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
	    	 WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	    }
	    - end */
        
        
	    // 更新数据库当中没有用户有多少条log记录
        /*start-
	    sql1="update userinfo set logamount='"+w+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//更新那些缺少nickname的用户
	    try{
      	  MySQLConn.update(sql1);
        }catch(Exception e){
        	WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
        	System.out.println("exception is :"+e.toString());
        }
        -end*/
	    
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
