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
	  //String filepath = "E://Lele//Taobao//Wangwang��¼//������//���´�//������´������¼.txt";
	  String filepath = "E://Lele//Taobao//Wangwang��¼//������//������//��Ҷ����������¼.txt";
	  TransferFile(filepath,"������","���´�");
  }

  //����˼·����dialog����sellername���з���
  public static void TransferFile(String filepath,String datacollector,String realname){
	  try{
			MySQLConn.connect();
	  }
	  catch(Exception e){
  	   System.out.println("data connection is wrong "+e.toString());
	 }
	  
	  //�����Ƕ��ļ��Ĵ������,���û��ĳ�����file�������Ϊһ�����Ƶ�file���ر��ǻ�������
	  
	  File file = new File(filepath);
	  String nickname = null; //����û���
	  String sellertitle = null;
	  int dialognumber =0;//dialog nummber
	  int flag = 0; //�����ж��Ƿ��Ѿ��õ������ҵ�����
	  String[] splitss = new String[50];
      BufferedReader reader = null;
      String sql1= "";
      String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
      try {
    	  
          //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
          reader = new BufferedReader(new FileReader(file));
          String tempString = "";
          String dialogcontent = "";
          int line = 1;// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
          nickname=reader.readLine();//�������У�����û���
          for(int i=0;i<3;i++){
        	  tempString = reader.readLine();//ǰ���и�ʽ�ı����ȴ����        	  
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
        
        
        //�������ݿ⵱�е�nickname��
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
						System.out.println("nickname�����ݿ��в�һ�£���������");
						WriteStreamAppend.method1(errorlogfilepath,"nickname�����ݿ��в�һ�£���������"+datacollector+","+realname+"\r\n");
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
	    }
	    
	    
	    // �������ݿ⵱��û���û��ж�����log��¼
	    sql1="update userinfo set logamount='"+w+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//������Щȱ��nickname���û�
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
          reader = new BufferedReader(new FileReader(file));//���¶��ļ�
          DialogBean[] dialogben = new DialogBean[w];
          DialogContentBean[] contentbean = new DialogContentBean[500];
          for(int i=0;i<4;i++){
        	  reader.readLine();//ǰ���и�ʽ�ı����ȴ����        	  
          }
          //��һ������,���õ�һ��bean
          //System.out.println("��-"+1+"��-������");
          dialogcontent="";
          dialogben[0]=new DialogBean();
          dialogben[0].setNickname(nickname);
          flag=0;
          int ee=0;//��������dialogcontentbean
          for(int i=0;i<newlogline[1]-5;i++){
        	  if(i==0){
        		  tempString=reader.readLine();//��õ�һ�е�Date��Ϣ
        		  //dialogcontent=dialogcontent+tempString+"\n";//��һ��date��Ϣ������content����
        		  dialogben[0].setLogdate(tempString);
        	  }else{
        	  tempString=reader.readLine();//��õ�һ��log������
        	  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
			  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
			  if(sellernameMatcher.find()){
			        		  System.out.println("find the name : "+sellernameMatcher.group(1));//get the username
			        		  if(flag==0){
			        			  if(nickname.equals(sellernameMatcher.group(1))){//ȷ�����ֵ�������������������������
				        	      }
				        		  else {
				        			  sellertitle=sellernameMatcher.group(1);
				        			  dialogben[0].setSellertitle(sellertitle);
				        			  flag=1;
				        		  }
			        		  }
			        		//�����￪ʼ����chatcontent���ݣ�������ȡ����
			        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
			        		  if(splitss.length>=2){
			        			  contentbean[ee] = new DialogContentBean();
			        			  contentbean[ee].setTime(splitss[0]);
			        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//�������жԻ�������������������������;
			        			  String temp="";
			        			  for(int tt=1;tt<splitss.length;tt++)
			        				  temp=temp+splitss[tt];
			        			  contentbean[ee].setContent(temp);
			        			  ee++;
			        		  }
			        		  else  System.out.println("����������ش�����ΪsplitС��2");
			        		  
        	      			  
			      }
				 else {
					 //System.out.println("cannot find the name;������");
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
        	  //System.out.println("��-"+(i+1)+"��-������");
        	  dialogcontent="";
              dialogben[i]=new DialogBean();
              dialogben[i].setNickname(nickname);
              flag=0;
              sellertitle="";
              ee=0;
        	  for(int j=0;j<newlogline[i+1]-newlogline[i];j++){
            	  if(j==0){
            		  tempString=reader.readLine();//��õ�һ�е�Date��Ϣ
            		  dialogben[i].setLogdate(tempString);
            		 // dialogcontent=dialogcontent+tempString+"\n";
            	  }else{
            	  tempString=reader.readLine();//��õ�һ��log������
    			  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
    			  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
    			       if(sellernameMatcher.find()){
    			        		  //System.out.println("find the name : "+sellernameMatcher.group(1));//get the username
    			        		  if(flag==0){
    			        			  if(nickname.equals(sellernameMatcher.group(1))){//ȷ�����ֵ�������������������������
    				        	      }
    				        		  else {
    				        			  sellertitle=sellernameMatcher.group(1);
    				        			  dialogben[i].setSellertitle(sellertitle);
    				        			  flag=1;
    				        		  }
    			        		  }
    			        		  
    			        		  //�����￪ʼ����chatcontent���ݣ�������ȡ����
    			        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
    			        		  if(splitss.length>=2){
    			        			  contentbean[ee] = new DialogContentBean();
    			        			  contentbean[ee].setTime(splitss[0]);
    			        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//�������жԻ�������������������������;
    			        			  String temp="";
    			        			  for(int tt=1;tt<splitss.length;tt++)
    			        				  temp=temp+splitss[tt];
    			        			  contentbean[ee].setContent(temp);
    			        			  ee++;
    			        		  }
    			        		  else  System.out.println("����������ش�����ΪsplitС��2");
    			        		  
    			        		 
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
          
          
          
          //���һ������

          
          //System.out.println("��-"+1+"��-������");
          dialogcontent="";
          dialogben[w-1]=new DialogBean();
          dialogben[w-1].setNickname(nickname);
          flag=0;
          tempString=reader.readLine();//��õ�һ�е�Date��Ϣ
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
	        			  if(nickname.equals(sellernameMatcher.group(1))){//ȷ�����ֵ�������������������������
		        	      }
		        		  else {
		        			  sellertitle=sellernameMatcher.group(1);
		        			  dialogben[w-1].setSellertitle(sellertitle);
		        			  flag=1;
		        		  }
	        		  }
	        		  
	        		  
	        		//�����￪ʼ����chatcontent���ݣ�������ȡ����
	        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
	        		  if(splitss.length>=2){
	        			  contentbean[ee] = new DialogContentBean();
	        			  contentbean[ee].setTime(splitss[0]);
	        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//�������жԻ�������������������������;
	        			  String temp="";
	        			  for(int tt=1;tt<splitss.length;tt++)
	        				  temp=temp+splitss[tt];
	        			  contentbean[ee].setContent(temp);
	        			  ee++;
	        		  }
	        		  else  
	        			  System.out.println("����������ش�����ΪsplitС��2");

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
