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
	  //String filepath = "E://Lele//Taobao//Wangwang��¼//����//lwaiqq999//2010-10-01��2011-12-05.txt";
	  //String filepath = "E://Wangwang��¼//������//����//2010-10-01��2011-12-025.txt";
	  String filepath = "E://Lele//Taobao//DataProcess//Wangwang//Ԭ��Ȫ//saiquan//Coding,saiquan.txt";
	  //String filepath = "E://Lele//Taobao//Wangwang��¼//������//������//��Ҷ����������¼.txt";
	  //TransferFile(filepath,"������","����");
	  TransferFile(filepath,"Ԭ��Ȫ","saiquan");
  }

  //����˼·����dialog����sellername���з���
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
        
        //�������ݿ⵱�е�nickname;
        /*start - 
  	    try{
	    	String sql5 = "select nickname from userinfo where datacollector='"+datacollector+"' and realname='"+realname+"'";//�鿴�����Ƿ��Ѿ�����
			MySQLConn.rs=MySQLConn.stmt.executeQuery(sql5);
			if(MySQLConn.rs.next()){
				System.out.println("�鵽����");
				String comparenick = MySQLConn.rs.getString(1);
				System.out.println("Comparenick is "+comparenick);
				if(comparenick==null||"".equals(comparenick)){
				   sql5="update userinfo set nickname='"+nickname+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//������Щȱ��nickname���û�
				   System.out.println("update nickname:"+nickname);
				   MySQLConn.update(sql5);
				}
				else{
					if(comparenick.equals(nickname)){
					}
					else {
						sql5="update userinfo set nickname='"+nickname+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//������Щȱ��nickname���û�
						   System.out.println("update nickname:"+nickname);
						   MySQLConn.update(sql5);
						//WriteStreamAppend.method1(errorlogfilepath,"nickname�����ݿ��в�һ�£���������"+datacollector+","+realname+"\r\n");
					}
				}
			}
			else{
				System.out.println("û�д��ˣ�������");
				WriteStreamAppend.method1(errorlogfilepath,"���ݿ���û������û�����������"+datacollector+","+realname+"\r\n");
			}
	    }
	    catch(Exception e){
	    	System.out.println("Exception is "+e.toString());
	    	 WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	    }
	    - end */
        
        
	    // �������ݿ⵱��û���û��ж�����log��¼
        /*start-
	    sql1="update userinfo set logamount='"+w+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//������Щȱ��nickname���û�
	    try{
      	  MySQLConn.update(sql1);
        }catch(Exception e){
        	WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
        	System.out.println("exception is :"+e.toString());
        }
        -end*/
	    
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
