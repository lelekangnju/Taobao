package Round2;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.*;
import Round2.MySQLConn;
public class Test {
 public static void main(String[] args){
	 CnToSpell cts = new CnToSpell();
	 System.out.println(PingYinUtil.getPingYin("康乐乐"));
	 
	 
	 
	 /*
	 String tempString ="AutomaticReply:Yesfadafds";
	 Pattern AutomaticReplyPattern = Pattern.compile("AutomaticReply:([\\s\\S]*?)");
	 Matcher AutomaticReplyMatcher = AutomaticReplyPattern.matcher(tempString);
	 System.out.println(AutomaticReplyMatcher.group(0));
	 
	 	
	 Pattern AutomaticReplyPattern = Pattern.compile("AutomaticReply:([\\s\\S]*?)");
	  Matcher AutomaticReplyMatcher = AutomaticReplyPattern.matcher("AutomaticReply:0fasdfasd");
	  if(AutomaticReplyMatcher.find()){
		  System.out.println(AutomaticReplyMatcher.group(0));
		  System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[0]);
		  System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[1]);
		  
	  }
	  
	 */
//	  System.out.println(tempString.split(AutomaticReplyMatcher.group(0))[1]);
	  
	 /*
	 List list = new ArrayList();
	 for(int i=1;i<59;i++)
		 list.add(i);
	 Collections.shuffle(list);
	 for(int j=0; j<58;j++){
		 System.out.println(list.get(j));
		 System.out.println("");
	 }
	*/
	 /*
	 double x = Math.random();
	 for(int i=0;i<100000000;i++){
		 x = Math.random();
		 System.out.println(1/x);
	 }
	 */
	 
	/*
	 Pattern AutomaticReplyPattern = Pattern.compile("AutomaticReply:([\\s\\S]*?)");
	  Matcher AutomaticReplyMatcher = AutomaticReplyPattern.matcher("AutomaticReply:0fasdfasd");
	  if(AutomaticReplyMatcher.find()){
		  System.out.println(AutomaticReplyMatcher.group(0));
		  //System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[0]);
		  //System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[1]);
		  
	  }
	 */
	 
	 

	  Pattern sellernamePattern2 = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):([\\s\\S]*?): [\\s\\S]*?");
	  Matcher sellernameMatcher2 = sellernamePattern2.matcher("2011-10-09 12:31:38 kingston金士顿旗舰店:i: 存在问题的情况下是可以的");
	  if(sellernameMatcher2.find()){
		  System.out.println("Group 0 "+sellernameMatcher2.group(0));
		  System.out.println("Group 1 "+sellernameMatcher2.group(1));
		  System.out.println("Group 2 "+sellernameMatcher2.group(2));
	  }

	  
	  System.out.println(General.occurTimes("GiveUpReason:No", "GiveUpReason"));
	  /*
	  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
	  Matcher sellernameMatcher = sellernamePattern.matcher("2011-10-12 22:27:48 hgj0794: 其它一模一样,就是土黄有点小色差");
	  */
	  /*
	  if(sellernameMatcher2.find()){
		  System.out.println(sellernameMatcher2.group(1));
		  //System.out.println("2011-10-12 22:27:48 hgj0794: 其它一模一样,就是土黄有点小色差".split(sellernameMatcher.group(1)+": ")[1]);
		  //System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[0]);
		  //System.out.println("AutomaticReply:0fasdfasd".split(AutomaticReplyMatcher.group(0))[1]);
		  
	  }
	  System.out.println("出现次数为"+General.occurTimes("fasdfjlasjkdofiaweuflja亲爱的亲爱的亲爱的skldjf", "亲"));
	 /*

		try{
			MySQLConn.connect();
		}
	    catch(Exception e){	
	    }
		try{
		    	String sql = "select * from tradeinfo";//查看此人是否已经存在
				MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
				String[] tradeid = new String[10000];
				int i=0;
				
				
				while(MySQLConn.rs.next()){
					//System.out.println("Trade ID is: "+MySQLConn.rs.getString(2));
					tradeid[i++]=MySQLConn.rs.getString(2);
		         }//end While loop
				int morethan1 = 0;
				for(int j=0;j<i;j++){
					//System.out.println(tradeid[j]);
					for(int w=j+1;w<i;w++){
						if(tradeid[j].equals(tradeid[w])){
							System.out.println(tradeid[j]);
							morethan1++;
						}
					}
				}
				System.out.println("有"+morethan1);
				
				
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
       finally{
    	   try{
    		   MySQLConn.stmt.close();
   			MySQLConn.con.close();
    	   }
    	   catch(Exception e){
				//WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
				System.out.println("Close MySQL is wrong");
		}
			
		}
			
	 */
 }
}
