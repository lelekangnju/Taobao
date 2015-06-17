package Round2;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;


public class General {
	/*
    public static void main(String args[]){
    	
    	//String ss = "如果您iahgthttp://bangpai.taobao.com/group/thread/58557-268151677.htm的店铺1天没有http://s.taobao.com/search?q=%CB%E6%CA%B1%B5%C4%F6%A6%C0%F6+%B9%D9%B7%BD%CA%DA%C8%A8+%D7%A8%B9%F1%D5%FD%C6%B7+%D3%F1%C0%BC%D3%CD%B6%E0%D0%A7%D0%DE%BB%A4%CB%AA+%C2%F22%CB%CD&rt=1321528432078 300-1000元收入？不妨看http://item.taobao.com/item.htm?id=10011968064看这小小的四钻钻卖家为何能一天近百单，月入十万元。http://item.taobao.com/item.htm?id=12919116801";
    	String ss = " 23:25:33 Stevehttp://item.taobao.com/item.htm?id=5384139306&spm=1100033413848.0000000430051682.000000113267149656.22 Steve";
    	ss = replaceURL(ss);
    	ss = replaceTimeString(ss);
    	
    	System.out.println(ss);
    }
   */
	/*
	public static void main(String args[]){
		try{
			MySQLConn.connect();
		   String[] id = new String[6000];
		   String[] sellerid = new String[6000];
		   String[] sellertitle = new String[6000];
		   String tempid;
		   String tempsellerid;
		   String tempsellertitle;
		   String sql5 = "select * from sellerinfo ";//查看此人是否已经存在
		   int flag=0;
		   int i=0;
		   MySQLConn.rs=MySQLConn.stmt.executeQuery(sql5);
		   while(MySQLConn.rs.next()){
			   System.out.println("i is "+i);
				tempid = MySQLConn.rs.getString(1);
				tempsellerid = MySQLConn.rs.getString(2);
				tempsellertitle = MySQLConn.rs.getString(3);
				if(flag==0){
					id[i]=tempid;
					sellerid[i]=tempsellerid;
					sellertitle[i]=tempsellertitle;
					flag=1;
					i++;
				}
				else{
					for(int j=0;j<i;j++){
						if(tempsellerid.equals(sellerid[j])&&!tempsellertitle.equals(sellertitle[j])){
							System.out.println("找到了，是第"+j+"和第"+i+"有问题");
						}
					}
					id[i]=tempid;
					sellerid[i]=tempsellerid;
					sellertitle[i]=tempsellertitle;
					i++;
				}
		   }	     
	   }
	   catch(Exception e){
 	    System.out.println("data connection is wrong "+e.toString());
 	    //WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	   }
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
	public static void main(String args[]){
		/*
		System.out.println(TradeIdJudge("1111111111111111"));
		System.out.println(occurTimes("亲爱的亲爱的亲爱的", "亲"));
		
		String tradeid="/:^_^;/:Q";
		for(String singtradeid:tradeid.split(";")){
			System.out.println(singtradeid);
		}
		System.out.println("符号的个数是"+tradeid.split(";").length);
		String tradeid1="1111111111111111";
		String tradeid2="Yes;这个书的质量怎么样？;;";
		String trades[]=tradeid2.split(";");
		System.out.println("trade length is "+trades.length);
		 for(String singletradeid:trades){
			  System.out.println(singletradeid);
		  }
		 System.out.println(tradeid1.split(";").length);
		 System.out.println(delSpaceChineseBiaodian("Not-related products,,/"));
		 */
		System.out.println(judgeOr(0,0));
		String Lele="";
		System.out.println("Lele Length "+Lele.length());
		
		testbean lll = new testbean();
		lll.setNumbertest(6);
		lll.setStringtest("diyihang");
		lll.setNumbertest(lll.getNumbertest()+10);
		lll.setStringtest(lll.getStringtest()+"dierhang");
		System.out.println(lll.getNumbertest());
		System.out.println(lll.getStringtest());
		
	}
	
	
	

	
	
	public static void printCalDataBeanTwoFiles(DialogBean tempdialogbean, CalDataBean CDBJCoding, String otherinfo, String datacollector, String realname, String tmprandomstring){
		  String printpathYes = "E:/Lele/Taobao/datalogCalDataBeanYes.txt";
	  	  String printpathNo  = "E:/Lele/Taobao/datalogCalDataBeanNo.txt";
	  	  
	  	  //获得相应的demographic infromation
	  	 DemographicInfoBean d = new DemographicInfoBean();
	  	 String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	  	 try{
			MySQLConn.connect();
			String sql = "select * from userinfo where datacollector='"+datacollector+"' and realname ='"+realname+"'";
			MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
			if(MySQLConn.rs.next()){
				d.setCollector(MySQLConn.rs.getString("datacollector"));
				d.setRealName(MySQLConn.rs.getString("realname"));
				d.setSex(MySQLConn.rs.getString("Sex"));
				d.setAge(MySQLConn.rs.getString("Age"));
				d.setEducation(MySQLConn.rs.getString("Education"));
				d.setIncome(MySQLConn.rs.getString("Income"));
				d.setJob(MySQLConn.rs.getString("Job"));
				d.setOnlineTime(MySQLConn.rs.getString("OnlineTime"));
				d.setOPT1(MySQLConn.rs.getString("OPT1"));
				d.setOPT2(MySQLConn.rs.getString("OPT2"));
				d.setOPT3(MySQLConn.rs.getString("OPT3"));
				d.setOPT4(MySQLConn.rs.getString("OPT4"));
				d.setOPT5(MySQLConn.rs.getString("OPT5"));
				d.setOPT6(MySQLConn.rs.getString("OPT6"));
				d.setOPT7(MySQLConn.rs.getString("OPT7"));
			}
		  }
		  catch(Exception e){
	  	   System.out.println("data connection is wrong "+e.toString());
	  	   WriteStreamAppend.method1(errorlogfilepath,"Transfer File exception is :"+e.toString()+"\r\n");
		 }
		  
		  try{
	  			MySQLConn.stmt.close();
	  			MySQLConn.con.close();
	  		}
	  			catch(Exception e){
	  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	  				System.out.println("Close MySQL is wrong");
	  		}
	  	  
	  	  //输出
		  if(CDBJCoding.isPurchaseornot()){//有相应的购买记录
		  //打印demographicinformation
		  WriteStreamAppend.method1(printpathYes,tmprandomstring);
		  WriteStreamAppend.method1(printpathYes,"#"+d.getCollector());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getRealName());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getSex());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getAge());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getEducation());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getIncome());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getJob());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOnlineTime());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT1());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT2());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT3());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT4());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT5());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT6());
		  WriteStreamAppend.method1(printpathYes,"#"+d.getOPT7());
	  	  WriteStreamAppend.method1(printpathYes,"#"+Long.toString(CDBJCoding.getTotal_response_time()));
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getTotal_response_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getFirst_response_time());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getC_asks_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getS_answers_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getC_words_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getS_words_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getDuration_time());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getS_qin_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getB_qin_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getCombined_c_asks_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getTotal_asks_time());	  	  
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getC_asks__number_paragraph());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getS_answers_number_paragraph());
   	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getTotal_response_line_words());
   	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getFirst_response_line_words());
   	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getCombined_c_asks_line_words());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getCommunicationType());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductName());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getCommunicationcontent());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getTradeID());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.isPurchaseornot());///
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getAutomaticReply());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getAutomaticReply_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductAvailableInquire());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductAvailableInquire_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductAvailableInquire_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductQualityInquire());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductQualityInquire_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductQualityInquire_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getDeliveryInquire());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getDeliveryInquire_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getDeliveryInquire_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getReturnInquiry());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getReturnInquiry_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getReturnInquiry_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getConfirmPromisedService());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getConfirmPromisedService_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getConfirmPromisedService_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductFeature());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductFeature_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductFeature_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductSelectionBehavior());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductSelectionBehavior_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getProductSelectionBehavior_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBargaining());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBargaining_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBargaining_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalIcons());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalIcons_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalIcons_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalIcons());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalIcons_judge());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalIcons_number());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getGiveUpReason());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getHowmanytrade());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumtradeid());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumtradestatus());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumdealtime());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumamountmoney());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumposttype());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getHowmanyitem());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumitemid());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumitemname());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumitemprice());
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSumitemquatity());
	  	  WriteStreamAppend.method1(printpathYes,"#Seller Emotioanl Words#"+CDBJCoding.getSellerEmotionalWords()[0]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[1]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[2]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[3]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[4]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[5]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[6]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getSellerEmotionalWords()[7]);
	  	  WriteStreamAppend.method1(printpathYes,"#Buyer Emotioanl Words#"+CDBJCoding.getBuyerEmotionalWords()[0]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[1]);
	      WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[2]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[3]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[4]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[5]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[6]);
	  	  WriteStreamAppend.method1(printpathYes,"#"+CDBJCoding.getBuyerEmotionalWords()[7]);
	  	  WriteStreamAppend.method1(printpathYes,"#Total_response_time_individual[");
	  	  for(Long TRTI:CDBJCoding.getTotal_response_time_individual()){
	  	    WriteStreamAppend.method1(printpathYes,TRTI+",");  
	  	  }
	  	  WriteStreamAppend.method1(printpathYes,"]#");
	  	  WriteStreamAppend.method1(printpathYes,"#Total_response_line_words_individual[");
	  	  for(Integer TRLWI:CDBJCoding.getTotal_response_line_words_individual()){
	  	    WriteStreamAppend.method1(printpathYes,TRLWI+",");  
	  	  }
	  	  WriteStreamAppend.method1(printpathYes,"]#");
	  	  WriteStreamAppend.method1(printpathYes,"#Total_ask_time_individual[");
	  	  for(Long TATI:CDBJCoding.getTotal_ask_time_individual()){
	  	    WriteStreamAppend.method1(printpathYes,TATI+",");  
	  	  }
	  	  WriteStreamAppend.method1(printpathYes,"]#");
	  	  WriteStreamAppend.method1(printpathYes,"#Combined_c_asks_line_words_individual[");
	  	  for(Integer CCALWI:CDBJCoding.getCombined_c_asks_line_words_individual()){
	  	    WriteStreamAppend.method1(printpathYes,CCALWI+",");  
	  	  }
	  	  WriteStreamAppend.method1(printpathYes,"]#");
	  	  
	  	  /*
	  	  WriteStreamAppend.method1(printpathYes,"######有相应的购买记录######");
	  	  for(TradeBean singletradebean:CDBJCoding.getTradeBean()){
	  		  WriteStreamAppend.method1(printpathYes,"TradeID is "+ singletradebean.getTradeid()+
	  				  " Trade Status is "+singletradebean.getTradestatus()+
	  				  " Deal Time is "+singletradebean.getDealtime()+
	  				  " Amount Money is "+singletradebean.getAmountmoney()+
	  				  " Post Type is "+singletradebean.getPosttype()+
	  				  "#");
			  		  for(ItemBean singleitembean:singletradebean.getIteamBean()){
			  			  WriteStreamAppend.method1(printpathYes,
			  			  "The following Trade Item:  ItemID is"+singleitembean.getItemid()+
			  			  " Item Name is "+singleitembean.getItemname()+
			  			  " Item Price is "+singleitembean.getItemprice()+
			  			  " Item Quatity is "+singleitembean.getItemquatity()+
			  			  "#");
			  		  }
	  		  WriteStreamAppend.method1(printpathYes,"#");
	  	    }
	  	  */
	  		 WriteStreamAppend.method1(printpathYes,"\r\n");
		  }else{
			  WriteStreamAppend.method1(printpathNo,tmprandomstring);
			  WriteStreamAppend.method1(printpathNo,"#"+d.getCollector());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getRealName());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getSex());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getAge());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getEducation());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getIncome());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getJob());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOnlineTime());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT1());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT2());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT3());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT4());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT5());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT6());
			  WriteStreamAppend.method1(printpathNo,"#"+d.getOPT7());
		  	  WriteStreamAppend.method1(printpathNo,"#"+Long.toString(CDBJCoding.getTotal_response_time()));
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getTotal_response_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getFirst_response_time());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getC_asks_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getS_answers_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getC_words_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getS_words_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getDuration_time());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getS_qin_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getB_qin_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getCombined_c_asks_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getTotal_asks_time());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getC_asks__number_paragraph());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getS_answers_number_paragraph());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getTotal_response_line_words());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getFirst_response_line_words());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getCombined_c_asks_line_words());		  	  
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getCommunicationType());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductName());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getCommunicationcontent());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getTradeID());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.isPurchaseornot());///
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getAutomaticReply());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getAutomaticReply_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductAvailableInquire());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductAvailableInquire_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductAvailableInquire_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductQualityInquire());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductQualityInquire_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductQualityInquire_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getDeliveryInquire());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getDeliveryInquire_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getDeliveryInquire_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getReturnInquiry());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getReturnInquiry_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getReturnInquiry_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getConfirmPromisedService());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getConfirmPromisedService_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getConfirmPromisedService_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductFeature());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductFeature_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductFeature_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductSelectionBehavior());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductSelectionBehavior_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getProductSelectionBehavior_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBargaining());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBargaining_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBargaining_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalIcons());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalIcons_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalIcons_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalIcons());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalIcons_judge());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalIcons_number());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getGiveUpReason());
		 	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getHowmanytrade());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumtradeid());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumtradestatus());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumdealtime());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumamountmoney());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumposttype());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getHowmanyitem());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumitemid());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumitemname());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumitemprice());
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSumitemquatity());
		      WriteStreamAppend.method1(printpathNo,"#Seller Emotioanl Words#"+CDBJCoding.getSellerEmotionalWords()[0]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[1]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[2]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[3]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[4]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[5]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[6]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getSellerEmotionalWords()[7]);
		  	  WriteStreamAppend.method1(printpathNo,"#Buyer Emotioanl Words#"+CDBJCoding.getBuyerEmotionalWords()[0]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[1]);
		      WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[2]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[3]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[4]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[5]);
		  	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[6]);
		   	  WriteStreamAppend.method1(printpathNo,"#"+CDBJCoding.getBuyerEmotionalWords()[7]);
		  	  
		  	  
		  	  WriteStreamAppend.method1(printpathNo,"#Total_response_time_individual[");
		  	  for(Long TRTI:CDBJCoding.getTotal_response_time_individual()){
		  	    WriteStreamAppend.method1(printpathNo,TRTI+",");  
		  	  }
		  	  WriteStreamAppend.method1(printpathNo,"]#");
		  	  WriteStreamAppend.method1(printpathNo,"#Total_response_line_words_individual[");
		  	  for(Integer TRLWI:CDBJCoding.getTotal_response_line_words_individual()){
		  	    WriteStreamAppend.method1(printpathNo,TRLWI+",");  
		  	  }
		  	  WriteStreamAppend.method1(printpathNo,"]#");
		  	  WriteStreamAppend.method1(printpathNo,"#Total_ask_time_individual[");
		  	  for(Long TATI:CDBJCoding.getTotal_ask_time_individual()){
		  	    WriteStreamAppend.method1(printpathNo,TATI+",");  
		  	  }
		  	  WriteStreamAppend.method1(printpathNo,"]#");
		  	  WriteStreamAppend.method1(printpathNo,"#Combined_c_asks_line_words_individual[");
		  	  for(Integer CCALWI:CDBJCoding.getCombined_c_asks_line_words_individual()){
		  	    WriteStreamAppend.method1(printpathNo,CCALWI+",");  
		  	  }
		  	  WriteStreamAppend.method1(printpathNo,"]#");
		  	  
		  	  WriteStreamAppend.method1(printpathNo,"\r\n");
	  	 }
	  	  
	    }

	//Print CalDataBean, Seperate into two files
	public static void printCalDataBeanTwoFilesAllInformation(DialogBean tempdialogbean, CalDataBean CDBJCoding, String otherinfo){
	  String printpathYes = "E:/Lele/Taobao/datalogCalDataBeanYes.txt";
  	  String printpathNo  = "E:/Lele/Taobao/datalogCalDataBeanNo.txt";
	  if(CDBJCoding.isPurchaseornot()){//有相应的购买记录
	  WriteStreamAppend.method1(printpathYes,"*******"+otherinfo+"*******\r\n");
  	  WriteStreamAppend.method1(printpathYes,tempdialogbean.getDialogcontent()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"total_response_time is "+CDBJCoding.getTotal_response_time()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"total_response_number is "+CDBJCoding.getTotal_response_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"first_response_time is "+CDBJCoding.getFirst_response_time()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"c_asks_number is "+CDBJCoding.getC_asks_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"s_answers_number is "+CDBJCoding.getS_answers_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"c_words_number is "+CDBJCoding.getC_words_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"s_words_number is "+CDBJCoding.getS_words_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"duration_time is "+CDBJCoding.getDuration_time()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"s_qin_number is "+CDBJCoding.getS_qin_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"b_qin_number is "+CDBJCoding.getB_qin_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"CommunicationType is "+CDBJCoding.getCommunicationType()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductName is "+CDBJCoding.getProductName()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"communicationcontent is "+CDBJCoding.getCommunicationcontent()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"TradeID is "+CDBJCoding.getTradeID()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"purchaseornot is "+CDBJCoding.isPurchaseornot()+"\r\n");///
  	  WriteStreamAppend.method1(printpathYes,"AutomaticReply is "+CDBJCoding.getAutomaticReply()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"AutomaticReply_judge is "+CDBJCoding.getAutomaticReply_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductAvailableInquire is "+CDBJCoding.getProductAvailableInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductAvailableInquire_judge is "+CDBJCoding.getProductAvailableInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductAvailableInquire_number is "+CDBJCoding.getProductAvailableInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductQualityInquire is "+CDBJCoding.getProductQualityInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductQualityInquire_judge is "+CDBJCoding.getProductQualityInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductQualityInquire_number is "+CDBJCoding.getProductQualityInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"DeliveryInquire is "+CDBJCoding.getDeliveryInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"DeliveryInquire_judge is "+CDBJCoding.getDeliveryInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"DeliveryInquire_number is "+CDBJCoding.getDeliveryInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ReturnInquiry is "+CDBJCoding.getReturnInquiry()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ReturnInquiry_judge is "+CDBJCoding.getReturnInquiry_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ReturnInquiry_number is "+CDBJCoding.getReturnInquiry_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ConfirmPromisedService is "+CDBJCoding.getConfirmPromisedService()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ConfirmPromisedService_judge is "+CDBJCoding.getConfirmPromisedService_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ConfirmPromisedService_number is "+CDBJCoding.getConfirmPromisedService_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductFeature is "+CDBJCoding.getProductFeature()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductFeature_judge is "+CDBJCoding.getProductFeature_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductFeature_number is "+CDBJCoding.getProductFeature_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductSelectionBehavior is "+CDBJCoding.getProductSelectionBehavior()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductSelectionBehavior_judge is "+CDBJCoding.getProductSelectionBehavior_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"ProductSelectionBehavior_number is "+CDBJCoding.getProductSelectionBehavior_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"Bargaining is "+CDBJCoding.getBargaining()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"Bargaining_judge is "+CDBJCoding.getBargaining_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"Bargaining_number is "+CDBJCoding.getBargaining_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"SellerEmotionalIcons is "+CDBJCoding.getSellerEmotionalIcons()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"SellerEmotionalIcons_judge is "+CDBJCoding.getSellerEmotionalIcons_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"SellerEmotionalIcons_number is "+CDBJCoding.getSellerEmotionalIcons_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"BuyerEmotionalIcons is "+CDBJCoding.getBuyerEmotionalIcons()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"BuyerEmotionalIcons_judge is "+CDBJCoding.getBuyerEmotionalIcons_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"BuyerEmotionalIcons_number is "+CDBJCoding.getBuyerEmotionalIcons_number()+"\r\n");
  	  WriteStreamAppend.method1(printpathYes,"GiveUpReason is "+CDBJCoding.getGiveUpReason()+"\r\n");
      WriteStreamAppend.method1(printpathYes,"=========,有相应的购买记录.==============\r\n");
  	  	 if(CDBJCoding.getTradeBean()==null)System.out.println("虽然现实purchase，但是在tradebean当中并没有");
  		 System.out.println("TradeBean number is "+CDBJCoding.getTradeBean().length);
  		 
  	  	 for(TradeBean singletradebean:CDBJCoding.getTradeBean()){
  		  WriteStreamAppend.method1(printpathYes,"TradeID is "+ singletradebean.getTradeid()+
  				  " Trade Status is "+singletradebean.getTradestatus()+
  				  " Deal Time is "+singletradebean.getDealtime()+
  				  " Amount Money is "+singletradebean.getAmountmoney()+
  				  " Post Type is "+singletradebean.getPosttype()+
  				  "\r\n");
		  		  for(ItemBean singleitembean:singletradebean.getIteamBean()){
		  			  WriteStreamAppend.method1(printpathYes,
		  			  "The following Trade Item:  ItemID is"+singleitembean.getItemid()+
		  			  " Item Name is "+singleitembean.getItemname()+
		  			  " Item Price is "+singleitembean.getItemprice()+
		  			  " Item Quatity is "+singleitembean.getItemquatity()+
		  			  "\r\n");
		  		  }
  		  WriteStreamAppend.method1(printpathYes,"\r\n");
  	    }
  		 WriteStreamAppend.method1(printpathYes,"howmanytrade is "+CDBJCoding.getHowmanytrade()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumtradeid is "+CDBJCoding.getSumtradeid()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumtradestatus is "+CDBJCoding.getSumtradestatus()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumdealtime is "+CDBJCoding.getSumdealtime()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumamountmoney is "+CDBJCoding.getSumamountmoney()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumposttype is "+CDBJCoding.getSumposttype()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"howmanyitem is "+CDBJCoding.getHowmanyitem()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumitemid is "+CDBJCoding.getSumitemid()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumitemname is "+CDBJCoding.getSumitemname()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumitemprice is "+CDBJCoding.getSumitemprice()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"sumitemquatity is "+CDBJCoding.getSumitemquatity()+"\r\n");
  		 WriteStreamAppend.method1(printpathYes,"\r\n\r\n\r\n\r\n\r\n\r\n");
	  }else{
	  	  WriteStreamAppend.method1(printpathNo,"*******"+otherinfo+"*******\r\n");
	  	  WriteStreamAppend.method1(printpathNo,tempdialogbean.getDialogcontent()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"total_response_time is "+CDBJCoding.getTotal_response_time()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"total_response_number is "+CDBJCoding.getTotal_response_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"first_response_time is "+CDBJCoding.getFirst_response_time()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"c_asks_number is "+CDBJCoding.getC_asks_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"s_answers_number is "+CDBJCoding.getS_answers_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"c_words_number is "+CDBJCoding.getC_words_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"s_words_number is "+CDBJCoding.getS_words_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"duration_time is "+CDBJCoding.getDuration_time()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"s_qin_number is "+CDBJCoding.getS_qin_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"b_qin_number is "+CDBJCoding.getB_qin_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"CommunicationType is "+CDBJCoding.getCommunicationType()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductName is "+CDBJCoding.getProductName()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"communicationcontent is "+CDBJCoding.getCommunicationcontent()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"TradeID is "+CDBJCoding.getTradeID()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"purchaseornot is "+CDBJCoding.isPurchaseornot()+"\r\n");///
	  	  WriteStreamAppend.method1(printpathNo,"AutomaticReply is "+CDBJCoding.getAutomaticReply()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"AutomaticReply_judge is "+CDBJCoding.getAutomaticReply_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductAvailableInquire is "+CDBJCoding.getProductAvailableInquire()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductAvailableInquire_judge is "+CDBJCoding.getProductAvailableInquire_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductAvailableInquire_number is "+CDBJCoding.getProductAvailableInquire_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductQualityInquire is "+CDBJCoding.getProductQualityInquire()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductQualityInquire_judge is "+CDBJCoding.getProductQualityInquire_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductQualityInquire_number is "+CDBJCoding.getProductQualityInquire_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"DeliveryInquire is "+CDBJCoding.getDeliveryInquire()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"DeliveryInquire_judge is "+CDBJCoding.getDeliveryInquire_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"DeliveryInquire_number is "+CDBJCoding.getDeliveryInquire_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ReturnInquiry is "+CDBJCoding.getReturnInquiry()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ReturnInquiry_judge is "+CDBJCoding.getReturnInquiry_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ReturnInquiry_number is "+CDBJCoding.getReturnInquiry_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ConfirmPromisedService is "+CDBJCoding.getConfirmPromisedService()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ConfirmPromisedService_judge is "+CDBJCoding.getConfirmPromisedService_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ConfirmPromisedService_number is "+CDBJCoding.getConfirmPromisedService_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductFeature is "+CDBJCoding.getProductFeature()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductFeature_judge is "+CDBJCoding.getProductFeature_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductFeature_number is "+CDBJCoding.getProductFeature_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductSelectionBehavior is "+CDBJCoding.getProductSelectionBehavior()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductSelectionBehavior_judge is "+CDBJCoding.getProductSelectionBehavior_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"ProductSelectionBehavior_number is "+CDBJCoding.getProductSelectionBehavior_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"Bargaining is "+CDBJCoding.getBargaining()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"Bargaining_judge is "+CDBJCoding.getBargaining_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"Bargaining_number is "+CDBJCoding.getBargaining_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"SellerEmotionalIcons is "+CDBJCoding.getSellerEmotionalIcons()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"SellerEmotionalIcons_judge is "+CDBJCoding.getSellerEmotionalIcons_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"SellerEmotionalIcons_number is "+CDBJCoding.getSellerEmotionalIcons_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"BuyerEmotionalIcons is "+CDBJCoding.getBuyerEmotionalIcons()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"BuyerEmotionalIcons_judge is "+CDBJCoding.getBuyerEmotionalIcons_judge()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"BuyerEmotionalIcons_number is "+CDBJCoding.getBuyerEmotionalIcons_number()+"\r\n");
	  	  WriteStreamAppend.method1(printpathNo,"GiveUpReason is "+CDBJCoding.getGiveUpReason()+"\r\n");
	  	  
	  		WriteStreamAppend.method1(printpathNo,"=========,没有没有没有相应的购买记录.==============\r\n");
	 		 WriteStreamAppend.method1(printpathNo,"howmanytrade is "+CDBJCoding.getHowmanytrade()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumtradeid is "+CDBJCoding.getSumtradeid()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumtradestatus is "+CDBJCoding.getSumtradestatus()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumdealtime is "+CDBJCoding.getSumdealtime()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumamountmoney is "+CDBJCoding.getSumamountmoney()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumposttype is "+CDBJCoding.getSumposttype()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"howmanyitem is "+CDBJCoding.getHowmanyitem()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumitemid is "+CDBJCoding.getSumitemid()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumitemname is "+CDBJCoding.getSumitemname()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumitemprice is "+CDBJCoding.getSumitemprice()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"sumitemquatity is "+CDBJCoding.getSumitemquatity()+"\r\n");
	  		 WriteStreamAppend.method1(printpathNo,"\r\n\r\n\r\n\r\n\r\n\r\n");
  	 }
  	  
    }
	//Print CalDataBean
	public static void printCalDataBean(DialogBean tempdialogbean, CalDataBean CDBJCoding, String otherinfo){
  	  String printpath = "E:/Lele/Taobao/datalogCalDataBean.txt";
  	  WriteStreamAppend.method1(printpath,"*******"+otherinfo+"*******\r\n");
  	  WriteStreamAppend.method1(printpath,tempdialogbean.getDialogcontent()+"\r\n");
  	  System.out.println("Output Content File");
  	  WriteStreamAppend.method1(printpath,"total_response_time is "+CDBJCoding.getTotal_response_time()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"total_response_number is "+CDBJCoding.getTotal_response_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"first_response_time is "+CDBJCoding.getFirst_response_time()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"c_asks_number is "+CDBJCoding.getC_asks_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"s_answers_number is "+CDBJCoding.getS_answers_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"c_words_number is "+CDBJCoding.getC_words_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"s_words_number is "+CDBJCoding.getS_words_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"duration_time is "+CDBJCoding.getDuration_time()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"s_qin_number is "+CDBJCoding.getS_qin_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"b_qin_number is "+CDBJCoding.getB_qin_number()+"\r\n");
  	  System.out.println("Output Communication Pattern");
  	  WriteStreamAppend.method1(printpath,"CommunicationType is "+CDBJCoding.getCommunicationType()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductName is "+CDBJCoding.getProductName()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"communicationcontent is "+CDBJCoding.getCommunicationcontent()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"TradeID is "+CDBJCoding.getTradeID()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"purchaseornot is "+CDBJCoding.isPurchaseornot()+"\r\n");///
  	  WriteStreamAppend.method1(printpath,"AutomaticReply is "+CDBJCoding.getAutomaticReply()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"AutomaticReply_judge is "+CDBJCoding.getAutomaticReply_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductAvailableInquire is "+CDBJCoding.getProductAvailableInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductAvailableInquire_judge is "+CDBJCoding.getProductAvailableInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductAvailableInquire_number is "+CDBJCoding.getProductAvailableInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductQualityInquire is "+CDBJCoding.getProductQualityInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductQualityInquire_judge is "+CDBJCoding.getProductQualityInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductQualityInquire_number is "+CDBJCoding.getProductQualityInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"DeliveryInquire is "+CDBJCoding.getDeliveryInquire()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"DeliveryInquire_judge is "+CDBJCoding.getDeliveryInquire_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"DeliveryInquire_number is "+CDBJCoding.getDeliveryInquire_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ReturnInquiry is "+CDBJCoding.getReturnInquiry()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ReturnInquiry_judge is "+CDBJCoding.getReturnInquiry_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ReturnInquiry_number is "+CDBJCoding.getReturnInquiry_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ConfirmPromisedService is "+CDBJCoding.getConfirmPromisedService()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ConfirmPromisedService_judge is "+CDBJCoding.getConfirmPromisedService_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ConfirmPromisedService_number is "+CDBJCoding.getConfirmPromisedService_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductFeature is "+CDBJCoding.getProductFeature()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductFeature_judge is "+CDBJCoding.getProductFeature_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductFeature_number is "+CDBJCoding.getProductFeature_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductSelectionBehavior is "+CDBJCoding.getProductSelectionBehavior()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductSelectionBehavior_judge is "+CDBJCoding.getProductSelectionBehavior_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"ProductSelectionBehavior_number is "+CDBJCoding.getProductSelectionBehavior_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"Bargaining is "+CDBJCoding.getBargaining()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"Bargaining_judge is "+CDBJCoding.getBargaining_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"Bargaining_number is "+CDBJCoding.getBargaining_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"SellerEmotionalIcons is "+CDBJCoding.getSellerEmotionalIcons()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"SellerEmotionalIcons_judge is "+CDBJCoding.getSellerEmotionalIcons_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"SellerEmotionalIcons_number is "+CDBJCoding.getSellerEmotionalIcons_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"BuyerEmotionalIcons is "+CDBJCoding.getBuyerEmotionalIcons()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"BuyerEmotionalIcons_judge is "+CDBJCoding.getBuyerEmotionalIcons_judge()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"BuyerEmotionalIcons_number is "+CDBJCoding.getBuyerEmotionalIcons_number()+"\r\n");
  	  WriteStreamAppend.method1(printpath,"GiveUpReason is "+CDBJCoding.getGiveUpReason()+"\r\n");
  	  System.out.println("Output Coding");
  	  
  	  
  	  if(CDBJCoding.isPurchaseornot()){//有相应的购买记录
  		  
  		 WriteStreamAppend.method1(printpath,"=========,有相应的购买记录.==============\r\n");
  	  	 if(CDBJCoding.getTradeBean()==null)System.out.println("虽然现实purchase，但是在tradebean当中并没有");
  		 System.out.println("TradeBean number is "+CDBJCoding.getTradeBean().length);
  		 
  	  	 for(TradeBean singletradebean:CDBJCoding.getTradeBean()){
  		  WriteStreamAppend.method1(printpath,"TradeID is "+ singletradebean.getTradeid()+
  				  " Trade Status is "+singletradebean.getTradestatus()+
  				  " Deal Time is "+singletradebean.getDealtime()+
  				  " Amount Money is "+singletradebean.getAmountmoney()+
  				  " Post Type is "+singletradebean.getPosttype()+
  				  "\r\n");
		  		  for(ItemBean singleitembean:singletradebean.getIteamBean()){
		  			  WriteStreamAppend.method1(printpath,
		  			  "The following Trade Item:  ItemID is"+singleitembean.getItemid()+
		  			  " Item Name is "+singleitembean.getItemname()+
		  			  " Item Price is "+singleitembean.getItemprice()+
		  			  " Item Quatity is "+singleitembean.getItemquatity()+
		  			  "\r\n");
		  		  }
		  		
  		  WriteStreamAppend.method1(printpath,"\r\n");
  	    }
  		 WriteStreamAppend.method1(printpath,"howmanytrade is "+CDBJCoding.getHowmanytrade()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumtradeid is "+CDBJCoding.getSumtradeid()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumtradestatus is "+CDBJCoding.getSumtradestatus()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumdealtime is "+CDBJCoding.getSumdealtime()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumamountmoney is "+CDBJCoding.getSumamountmoney()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumposttype is "+CDBJCoding.getSumposttype()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"howmanyitem is "+CDBJCoding.getHowmanyitem()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemid is "+CDBJCoding.getSumitemid()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemname is "+CDBJCoding.getSumitemname()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemprice is "+CDBJCoding.getSumitemprice()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemquatity is "+CDBJCoding.getSumitemquatity()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"\r\n\r\n\r\n\r\n\r\n\r\n");
  	  }else{
  		WriteStreamAppend.method1(printpath,"=========,没有没有没有相应的购买记录.==============\r\n");
 		 WriteStreamAppend.method1(printpath,"howmanytrade is "+CDBJCoding.getHowmanytrade()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumtradeid is "+CDBJCoding.getSumtradeid()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumtradestatus is "+CDBJCoding.getSumtradestatus()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumdealtime is "+CDBJCoding.getSumdealtime()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumamountmoney is "+CDBJCoding.getSumamountmoney()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumposttype is "+CDBJCoding.getSumposttype()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"howmanyitem is "+CDBJCoding.getHowmanyitem()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemid is "+CDBJCoding.getSumitemid()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemname is "+CDBJCoding.getSumitemname()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemprice is "+CDBJCoding.getSumitemprice()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"sumitemquatity is "+CDBJCoding.getSumitemquatity()+"\r\n");
  		 WriteStreamAppend.method1(printpath,"\r\n\r\n\r\n\r\n\r\n\r\n");
  	  }
  	  
  	  
    }
	
	//Int to Boolean将0和1转换成true和false,1为true，0为false
	public static boolean inttoboolean(int a){
		boolean judge = false;
		if(a==1)judge = true;
		
		return judge;
	}
	//两个0，1 int型模拟进行或预算
	public static int judgeOr(int a, int b){
		int judge = 0;
		if(a==1||b==1)judge=1;
		return judge;
	}
	
	// 去除符号和空格（中英文） 
	public static String replaceURL(String includeURL)
    {
           Pattern pattern = Pattern.compile("((http://)?([a-z]+[.])|(www.))\\w+[.]([a-z]{2,4})?[[.]([a-z]{2,4})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z]{2,4}+|/?)");
           Matcher matcher = pattern.matcher(includeURL);
           while(matcher.find()){
        	   System.out.println("Find it");
        	   includeURL = includeURL.replace(matcher.group(), "U");
           }   
           return includeURL;
    }
	public static String replaceTimeString(String includeTime)
    {
		   
           Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");
           Matcher matcher = pattern.matcher(includeTime);
           while(matcher.find()){
        	   System.out.println("Find it");
        	   includeTime = includeTime.replace(matcher.group(), "");
           }   
           return includeTime;
    }
	public static String delSignBlank( String str){
		str = replaceURL(str);
		str = replaceTimeString(str);
		str = str.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");	
		//str =  str.replace('-', ' ').replace(':', ' ').replaceAll(" ","");  只是去掉空格  
		return str; 
	}
	
	public static String delSpaceChineseBiaodian(String S){
		S=S.replace(" ", "");//去除空格
		S=S.replace("；", ";");//中文的；转变成英文的;
		return S;
	}
	
	public static String delallbiaodian(String S){
		S=S.replaceAll("[\\p{Punct}\\p{Space}]+", "");
		return S;
	}
	
	//判断tradeID是不是16位置数字
	public static boolean TradeIdJudge(String TradeId){
		//String expression="\\d{15}$"; //15位整数的正则表达式
		String expression = "[0-9]+";
		Pattern pattern = Pattern.compile(expression);
		return pattern.matcher(TradeId).matches();
	}
	
	
	public static StringBuffer readToBuffer(InputStream is){
		StringBuffer buffer = new StringBuffer("");
		try{
			String line ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			line = reader.readLine();
			while(line != null){
				buffer.append(line);
				buffer.append("\n");
				line = reader.readLine();
			}
		}catch(Exception e){
			
		}
		return buffer;
	}
	//精确到分钟
    public static long minustime_second(Date former,Date later){
    	long difftime = 0;
    	difftime = (former.getTime()-later.getTime())/(1000);
    	return difftime;
    }
	public static Date StringToDate(String dealtime){
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = null;                            
		try {   
		    date = format.parse(dealtime);
		} catch (ParseException e) {   
		    e.printStackTrace();   
		}   
		return date;
	}
	
	public static String cleanHtml(String buffer){
		buffer = buffer.replace("\r", "").replace("\n", "").replace("\t", "").toLowerCase();
		for(int i=1;i<30;i++){
			buffer = buffer.replace(" <","<");
		}
		for(int i=1;i<30;i++){
			buffer = buffer.replace("> ",">");
		}
		buffer = buffer.replaceAll(" +", " "); //把多个空格替换为单个空格，" +"表示连续的多个空格
		return buffer;
	}
	public static String ItemnameTransfer(String itemname){
		itemname=itemname.replace("'", "''");
		return itemname;
	}
	public static String DotTransfer(String temps){
		temps=temps.replace("'", "''");
		return temps;
	}
	public static String SelleridTransfer(String sellerid){//去掉sellerid最后的"
		sellerid=sellerid.replace("\"", "");
		return sellerid;
	}
	public static String LineStringTransfer(String linestring){//去掉sellerid最后的"
		linestring=linestring.replace("(", ": ");
		linestring=linestring.replace("):", "");
		return linestring;
	}
	
	 // 读取文件指定行。
    public static void readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
            System.out.println("不在文件的行数范围之内。");
        }
        while (s != null) {
            System.out.println("当前行号为:"
                            + reader.getLineNumber());
                    reader.setLineNumber(20);
                    System.out.println("更改后行号为:"
                            + reader.getLineNumber());
                    System.out.println(s);
                    System.exit(0);     
                    s = reader.readLine();
            }
        
        reader.close();
        in.close();
    }
    // 文件内容的总行数。
    public static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }
    
    public static int occurTimes(String string, String a) {//计算某个字符串中某个字符出现的次数
        int pos = -2;
        int n = 0;
     
        while (pos != -1) {
            if (pos == -2) {
                pos = -1;
            }
            pos = string.indexOf(a, pos + 1);
            if (pos != -1) {
                n++;
            }
        }
        return n;
    }
	/*
	public static void main(String args[]){
		Test();
	}
	*/
}
class testbean{
	int numbertest;
	String stringtest;
	public int getNumbertest() {
		return numbertest;
	}
	public void setNumbertest(int numbertest) {
		this.numbertest = numbertest;
	}
	public String getStringtest() {
		return stringtest;
	}
	public void setStringtest(String stringtest) {
		this.stringtest = stringtest;
	}
	
}
