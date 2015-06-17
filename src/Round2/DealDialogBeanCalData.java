package Round2;
import Round2.WriteStreamAppend;

import java.text.SimpleDateFormat;
import java.util.*;

//这个类用来计算那些data，每个dialogbean当中包含的data
public class DealDialogBeanCalData {
      public static CalDataBean calculate(DialogBean tempdialogbean){
    	  
    	  CalDataBean cdb = new CalDataBean();
    	  
    	  //处理communication pattern
    	  long total_response_time   = 0;   //总回复时间，所谓总回复时间是指，连续的某人对话，当成一个整体对待，讲的是整段和整段之间的时间差
    	  int  total_response_number = 0;   //总回复数目，讲的是整段卖家，整段买家一共有多少段
    	  ArrayList<Long> total_response_time_individual = new ArrayList<Long>();//将每次回答的时间，作为一个数组输出 20130823
    	  ArrayList<Integer> total_response_line_words_individual = new ArrayList<Integer>(); //跟随的卖家回复的那一行的字数
    	  int  total_response_line_words  = 0;   //回复时候，卖家第一行回复的字数的总和
    	  long first_response_time   = 0;   //第一条回复时间
    	  int  first_response_line_words  = 0;   //卖家第一条回复的字数
    	  int  c_asks_number         = 0;   //买家对话条数
    	  int  s_answers_number      = 0;   //卖家对话条数
    	  int  c_words_number        = 0;   //买家对话中的字数
    	  int  s_words_number        = 0;   //卖家对话中的字数
    	  int  first_s               = -1;   //第一条卖家消息记录
    	  int  first_c               = -1;   //第一条买家消息记录
    	  long duration_time         = 0;    //整段对话持续的总时长
   	      int s_qin_number           = 0;    //卖家谈话中“亲”的次数
	      int b_qin_number           = 0;    //买家谈话中“亲”的次数
	      int combined_c_asks_number = 0;    //买家多少次急切地再问问题
	      int combined_c_asks_line_words  = 0;    //买家再次急切问问题时的第一行问题字数
	      long total_asks_time = 0;            //买家有多急切的提问，卖家回复后，买家多久提出问题
	      ArrayList<Long> total_ask_time_individual = new ArrayList<Long>();//将每次提问的时间，作为一个数组输出 20130823
	      ArrayList<Integer> combined_c_asks_line_words_individual = new  ArrayList<Integer>(); //提问时，第一行的字数
	      int c_asks__number_paragraph = 0; //一共有多少段c asks
	      int s_answers_number_paragraph = 0; //一共有多少段 s answers
	      
	      //2013-08-13
	      int selleremotionalwords[]={0,0,0,0,0,0,0,0}; //记录其中emotional words 分类的，每类出现的额次数; 前面是七类emotional words，最后一个是 分词后一共有多少个词
	      int buyeremotionalwords[]={0,0,0,0,0,0,0,0};
	      
    	  
	      String nickname,sellertitle;
    	  nickname    =  tempdialogbean.getNickname();
    	  sellertitle =  tempdialogbean.getSellertitle();
    	  DialogContentBean[] tempdb = tempdialogbean.getContentb();
    	  //calculate the first response time;
    	  
    	  /*
    	  System.out.println("Nickname is "+nickname);
    	  System.out.println("sellertitle is "+sellertitle);
    	  for(int i=0;i<tempdb.length;i++){
    		  System.out.println(tempdb[i].getTitle());
    		  System.out.println(tempdb[i].getTime());
    		  System.out.println(tempdb[i].getContent());
    	  }
    	  */
		  for(int i=0;i<tempdb.length;i++){
			  System.out.println(tempdb[i].getTitle());
			  if(nickname.equals(tempdb[i].getTitle())){
				  first_c=i;
				  //System.out.println("找到nickname了,行数为 ："+i);
				  break;
			  }
		  }
		  for(int i=0;i<tempdb.length;i++){
			  System.out.println(tempdb[i].getTitle());
			  if(sellertitle.equals(tempdb[i].getTitle())){
				  first_s=i;
				  //System.out.println("找到sellertitle了,行数为 ："+i);
				  break;
			  }
		  }
		  if(first_s==-1||first_c==-1){
			  System.out.println("出错啦，竟然没有找到两个角色");
		  }
		  if(first_s<first_c){//如果第一条卖家信息竟然比第一条买家信息早
			  first_response_time = -1;
			  cdb.setFirst_response_time(first_response_time);
			  first_response_line_words = -1;
			  cdb.setFirst_response_line_words(first_response_line_words);
		  }else{
			  first_response_time = General.minustime_second(General.StringToDate(tempdb[first_s].getTime())
                                                           , General.StringToDate(tempdb[first_c].getTime()));
			  first_response_line_words = tempdb[first_s].getContent().length();
			  cdb.setFirst_response_time(first_response_time);
			  cdb.setFirst_response_line_words(first_response_line_words);
		  }
		  //calculate the total response time,total response number
		  for(int i=0;i<tempdb.length;i++){
			  if(nickname.equals(tempdb[i].getTitle())){
				  for(int j=i;j<tempdb.length;j++){
					  if(sellertitle.equals(tempdb[j].getTitle())){//找到卖家对买家的第一条回复了
						  total_response_number++;
						  total_response_line_words_individual.add(tempdb[j].getContent().length());
						  total_response_line_words = total_response_line_words + tempdb[j].getContent().length();
						  total_response_time_individual.add(General.minustime_second(General.StringToDate(tempdb[j].getTime())
					                                                 , General.StringToDate(tempdb[i].getTime())));//将一次回复时间加入
						  total_response_time=total_response_time+
											  General.minustime_second(General.StringToDate(tempdb[j].getTime())
					                                                 , General.StringToDate(tempdb[i].getTime()));
						  
						  i=j;
						  break;
					  }
				  }
			  }
		  }
		  cdb.setTotal_response_number(total_response_number);
		  cdb.setTotal_response_time(total_response_time);
		  cdb.setTotal_response_line_words(total_response_line_words);
		  cdb.setTotal_response_line_words_individual(total_response_line_words_individual);
		  cdb.setTotal_response_time_individual(total_response_time_individual);
		  //calculate the total_asks_time combined_c_asks_number
		  for(int i=0;i<tempdb.length;i++){
			  if(sellertitle.equals(tempdb[i].getTitle())){
				  for(int j=i;j<tempdb.length;j++){
					  if(nickname.equals(tempdb[j].getTitle())){//找到卖家对买家的第一条回复了
						  combined_c_asks_number++;
						  combined_c_asks_line_words_individual.add(tempdb[j].getContent().length());
						  combined_c_asks_line_words = combined_c_asks_line_words + tempdb[j].getContent().length();
						  total_ask_time_individual.add(General.minustime_second(General.StringToDate(tempdb[j].getTime())
                                  , General.StringToDate(tempdb[j-1].getTime())));
						  total_asks_time=total_asks_time+
											  General.minustime_second(General.StringToDate(tempdb[j].getTime())
					                                                 , General.StringToDate(tempdb[j-1].getTime()));
						  i=j;
						  break;
					  }
				  }
				  
			  }
		  }
		  cdb.setCombined_c_asks_number(combined_c_asks_number);
		  cdb.setTotal_asks_time(total_asks_time);
		  cdb.setCombined_c_asks_line_words(combined_c_asks_line_words);
		  cdb.setCombined_c_asks_line_words_individual(combined_c_asks_line_words_individual);
		  cdb.setTotal_ask_time_individual(total_ask_time_individual);
		  
		  if(sellertitle.equals(tempdb[0].getTitle())){
			  s_answers_number_paragraph = total_response_number +1;
			  c_asks__number_paragraph = combined_c_asks_number;
		  }else if(nickname.equals(tempdb[0].getTitle())){
			  c_asks__number_paragraph = combined_c_asks_number +1;
			  s_answers_number_paragraph = total_response_number;
		  }
		  cdb.setC_asks__number_paragraph(c_asks__number_paragraph);
		  cdb.setS_answers_number_paragraph(s_answers_number_paragraph);


		  //calculate duration time;
		  duration_time = General.minustime_second(General.StringToDate(tempdb[tempdb.length-1].getTime())
                                                 , General.StringToDate(tempdb[0].getTime()));
		  cdb.setDuration_time(duration_time);
		  //买家对话条数c_asks_number，买家对话中的字数c_words_number
		  for(int i=0;i<tempdb.length;i++){
			  if(nickname.equals(tempdb[i].getTitle())){
				  c_asks_number++;
				  c_words_number=c_words_number+General.delSignBlank(tempdb[i].getContent()).length();
				  b_qin_number = b_qin_number + General.occurTimes(tempdb[i].getContent(), "亲");
				  
				  //计算 emotional words出现的频率
				  //2013-08-13
				  int temp[]=TestCidian.CalculateEmotionalWords(tempdb[i].getContent());;
				  buyeremotionalwords[0]=buyeremotionalwords[0]+temp[0];
				  buyeremotionalwords[1]=buyeremotionalwords[1]+temp[1];
				  buyeremotionalwords[2]=buyeremotionalwords[2]+temp[2];                   
				  buyeremotionalwords[3]=buyeremotionalwords[3]+temp[3];
				  buyeremotionalwords[4]=buyeremotionalwords[4]+temp[4];
				  buyeremotionalwords[5]=buyeremotionalwords[5]+temp[5];
				  buyeremotionalwords[6]=buyeremotionalwords[6]+temp[6];
				  buyeremotionalwords[7]=buyeremotionalwords[7]+temp[7];
			  }
		  }
		  cdb.setC_asks_number(c_asks_number);
		  cdb.setC_words_number(c_words_number);
		  cdb.setB_qin_number(b_qin_number);
		  cdb.setBuyerEmotionalWords(buyeremotionalwords);
		  //卖家对话条数s_answers_number，卖家对话中的字数s_words_number
		  for(int i=0;i<tempdb.length;i++){
			  if(sellertitle.equals(tempdb[i].getTitle())){
				  s_answers_number++;
				  s_words_number=s_words_number+General.delSignBlank(tempdb[i].getContent()).length();
				  s_qin_number = s_qin_number+General.occurTimes(tempdb[i].getContent(),"亲");
				  //计算 emotional words出现的频率
				  //2013-08-13
				  int tempp[]=TestCidian.CalculateEmotionalWords(tempdb[i].getContent());;
				  selleremotionalwords[0]=selleremotionalwords[0]+tempp[0];
				  selleremotionalwords[1]=selleremotionalwords[1]+tempp[1];
				  selleremotionalwords[2]=selleremotionalwords[2]+tempp[2];                   
				  selleremotionalwords[3]=selleremotionalwords[3]+tempp[3];
				  selleremotionalwords[4]=selleremotionalwords[4]+tempp[4];
				  selleremotionalwords[5]=selleremotionalwords[5]+tempp[5];
				  selleremotionalwords[6]=selleremotionalwords[6]+tempp[6];
				  selleremotionalwords[7]=selleremotionalwords[7]+tempp[7]; 
			  }
		  }
		  cdb.setS_answers_number(s_answers_number);
		  cdb.setS_words_number(s_words_number);
		  cdb.setS_qin_number(s_qin_number);
		  cdb.setSellerEmotionalWords(selleremotionalwords);
		  /*
    	  System.out.println("total_response_time is "+total_response_time+", total_response_number is "+total_response_number
    			  +", first_response_time is "+first_response_time+", c_asks_number is "+c_asks_number+", s_answers_number is "+
    			  s_answers_number+", c_words_number is "+c_words_number+", s_words_number is "+s_words_number+", first_s is "+
    			  first_s+", first_c is "+first_c);
    	  */
    	  //处理coding data
    	  cdb.setCommunicationType(tempdialogbean.getCommunicationType());
    	  cdb.setProductName(tempdialogbean.getProductName());
    	  cdb.setCommunicationcontent(tempdialogbean.getCommunicationContent());
    	  cdb.setTradeID(tempdialogbean.getTradeId());
    	  cdb.setAutomaticReply(tempdialogbean.getAutomaticReply());
    	  cdb.setProductAvailableInquire(tempdialogbean.getProductAvailableInquire());
    	  cdb.setProductQualityInquire(tempdialogbean.getProductQualityInquire());
    	  cdb.setDeliveryInquire(tempdialogbean.getDeliveryInquire());
    	  cdb.setReturnInquiry(tempdialogbean.getReturnInquiry());
    	  cdb.setConfirmPromisedService(tempdialogbean.getConfirmPromisedService());
    	  cdb.setProductFeature(tempdialogbean.getProductFeature());
    	  cdb.setProductSelectionBehavior(tempdialogbean.getProductSelectionBehavior());
    	  cdb.setBargaining(tempdialogbean.getBargaining());
    	  cdb.setFlexiblePolicy(tempdialogbean.getFlexiblePolicy());
    	  cdb.setSellerEmotionalIcons(tempdialogbean.getSellerEmotionalIcons());
    	  cdb.setBuyerEmotionalIcons(tempdialogbean.getBuyerEmotionalIcons());
    	  cdb.setGiveUpReason(tempdialogbean.getGiveUpReason());
		   //Enquire database to get more data;
    	  /*
		   System.out.println("CDBJ Content: \n"+
				   cdb.getCommunicationType()+"\n"+
				   cdb.getProductName()+"\n"+
				   cdb.getCommunicationcontent()+"\n"+
				   cdb.getTradeID()+"\n"+
				   cdb.getAutomaticReply()+"\n"+
				   cdb.getProductAvailableInquire()+"\n"+
				   cdb.getProductQualityInquire()+"\n"+
				   cdb.getDeliveryInquire()+"\n"+
				   cdb.getReturnInquiry()+"\n"+
				   cdb.getConfirmPromisedService()+"\n"+
				   cdb.getProductFeature()+"\n"+
				   cdb.getProductSelectionBehavior()+"\n"+
				   cdb.getBargaining()+"\n"+
				   cdb.getFlexiblePolicy()+"\n"+
				   cdb.getSellerEmotionalIcons()+"\n"+
				   cdb.getBuyerEmotionalIcons()+"\n"+
				   cdb.getGiveUpReason()+"---  \n");
		  */
    	  return cdb;
      }
      
      public static CalDataBean calculateCoding(DialogBean tempdialogbean, CalDataBean CDBJCoding,String datacollector,String realname){
    	  String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
    	  if("pre".equalsIgnoreCase(General.delSpaceChineseBiaodian(CDBJCoding.getCommunicationType())))//忽略大小写对比字符
    	    {
    		   System.out.println("获得一个Pre communication session");
    		}
    	  
    	  
    	  
    	  //不处理ProductName和communicationcontent这两个字段
    	  //处理TradeId，首先判断tradeID是否为0，然后从数据库中提取这一tradeID对应的交易记录
    	  String tradeid = General.delSpaceChineseBiaodian(CDBJCoding.getTradeID());//格式化处理
    	  TradeBean tb[] = new TradeBean[20];//购置tradebean，最多只能导致10次购买
		  int ee =0;//用于记录trade的数量是多少
    	  if("0".equals(tradeid)){
    		  CDBJCoding.setPurchaseornot(false);//表示此条聊天记录没有相应的交易
    		  CDBJCoding.setTradeBean(null, 0);
    		  CDBJCoding.setHowmanytrade(0);
    		  CDBJCoding.setSumtradeid("0");
    		  CDBJCoding.setSumtradestatus("0");
    		  CDBJCoding.setSumdealtime("0");
    		  CDBJCoding.setSumamountmoney(0);
    		  CDBJCoding.setSumposttype("0");
    		  CDBJCoding.setHowmanyitem(0);
    		  CDBJCoding.setSumitemid("0");
    		  CDBJCoding.setSumitemname("0");
    		  CDBJCoding.setSumitemprice("0");
    		  CDBJCoding.setSumitemquatity("0");
    	  }else{
    		  CDBJCoding.setPurchaseornot(true);//表示词条聊天记录有相应的购买
    		  
    		  String trades[] = tradeid.split(";");
    		  for(String testsingletradeid:trades){
    			  System.out.println("testsingletradeid is "+testsingletradeid);
    		  }
    		  String sql = null;
    		  try{
    			 MySQLConn.connect();
    			  for(String singletradeid:trades){//通过for循环，对每条交易记录进行处理
    				  //System.out.println("SingleTradeID is "+singletradeid);//singletradeid表示每条记录的ID
    				  if(General.TradeIdJudge(singletradeid)){
    					      TradeBean temptradebean = new TradeBean();
    		    			  sql = "select distinct tradeid,tradestatus,dealtime,amountmoney,posttype from tradeinfo where tradeid='"+singletradeid+"'";
    		    			  System.out.println("SQL === LeleKang is "+sql);
    		    			  MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
    		    			  MySQLConn.rs.last();
    		    			  if(MySQLConn.rs.getRow()==0){//在数据库中找不到
    		    				  System.out.println("找不到这一tradeid对应的交易记录 trade id is "+singletradeid);
    		    		  	 	  WriteStreamAppend.method1(errorlogfilepath,"找不到这一tradeid对应的交易记, trade id is "+singletradeid+", datacollector is "+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    		    			  }else{//数据库中找到了tradeid，处理出trade information，通过build tradebean来处理
    		    				  MySQLConn.rs.first();
    		    				  temptradebean.setTradeid(MySQLConn.rs.getString("tradeid"));
    		    				  temptradebean.setTradestatus(MySQLConn.rs.getString("tradestatus"));
    		    				  temptradebean.setDealtime(MySQLConn.rs.getString("dealtime"));
    		    				  temptradebean.setAmountmoney(Double.parseDouble(MySQLConn.rs.getString("amountmoney")));
    		    				  temptradebean.setPosttype(MySQLConn.rs.getString("posttype"));
    		    				  
    		    				  CDBJCoding.setSumtradeid(CDBJCoding.getSumtradeid()+"$"+temptradebean.getTradeid());
    		    				  CDBJCoding.setSumtradestatus(CDBJCoding.getSumtradestatus()+"$"+temptradebean.getTradestatus());
    		    				  CDBJCoding.setSumdealtime(CDBJCoding.getSumdealtime()+"$"+temptradebean.getDealtime());
    		    				  CDBJCoding.setSumamountmoney(CDBJCoding.getSumamountmoney()+temptradebean.getAmountmoney());
    		    				  CDBJCoding.setSumposttype(CDBJCoding.getSumposttype()+"$"+temptradebean.getPosttype());
    		    				  
    		    				  
    		    				  //从数据库中获得trade item的数据
    		    				  sql = "select itemid, itemname,itemprice,itemquatity from tradeitem where tradeid='"+singletradeid+"'";
    		    				  //System.out.println("查询item information, SQL: "+sql);
    		    				  int itemnumber =0;
    		    				  MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
    		    				     ItemBean ib[]=new ItemBean[100];//每个订单不超过100个商品
    		    				     itemnumber = 0;
    		    				     
    		    				     while(MySQLConn.rs.next()){
    		    				    	 ItemBean tempitembean = new ItemBean();
    		    				    	 tempitembean.setItemid(MySQLConn.rs.getString("itemid"));
    		    				    	 tempitembean.setItemname(MySQLConn.rs.getString("itemname"));
    		    				    	 tempitembean.setItemprice(Double.parseDouble(MySQLConn.rs.getString("itemprice")));
    		    				    	 tempitembean.setItemquatity(Integer.parseInt(MySQLConn.rs.getString("itemquatity")));
    		    				    	 //System.out.println("Item Information====== "+tempitembean.getItemid()+";"+tempitembean.getItemname());
    		    				    	 
    		    				    	 CDBJCoding.setSumitemid(CDBJCoding.getSumitemid()+"$"+tempitembean.getItemid());
    		    				    	 CDBJCoding.setSumitemname(CDBJCoding.getSumitemname()+"$"+tempitembean.getItemname());
    		    				    	 CDBJCoding.setSumitemprice(CDBJCoding.getSumitemprice()+"$"+tempitembean.getItemprice());
    		    				    	 CDBJCoding.setSumitemquatity(CDBJCoding.getSumitemquatity()+"$"+tempitembean.getItemquatity());
    		    				    	 CDBJCoding.setHowmanyitem(CDBJCoding.getHowmanyitem()+tempitembean.getItemquatity());
    		    				    	 
    		    				    	 ib[itemnumber]=tempitembean;
    		    				    	 itemnumber++;
    		    				     }
    		    				     if(itemnumber==0){
    		    				    	 //出错了，这个tradeid下面找不到任何item
    		    				    	 System.out.println("这一交易记录下找不到任何的item "+singletradeid);
    	    		    		  	 	 WriteStreamAppend.method1(errorlogfilepath,"这一交易记录下找不到任何的item, trade id is "+singletradeid+", datacollector is "+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    		    				     }
    		    				     temptradebean.setItemBean(ib, itemnumber);
    		    				     tb[ee]=temptradebean;
    		    				  ee++;
    		    			  }

    				  }else{
    			    		System.out.println("tradeID出现问题，不符合规范，不是0，也不是N位整数");
    			  	 	    WriteStreamAppend.method1(errorlogfilepath,"tradeID出现问题，不符合规范，不是0，也不是整数. Tradeid is : "+singletradeid+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    			    }  
    			  }//end for 循环
    	     MySQLConn.stmt.close();
    		 MySQLConn.con.close();
    		  }//end try
    		  catch(Exception e){
	    		   System.out.println("data connection is wrong DealDialogBeanCalData.jave "+e.toString());
	    		   WriteStreamAppend.method1(errorlogfilepath,"DealDialogBeanCalData.java exception is :"+e.toString()+"; SQL is "+sql+"\r\n");
	    	  }
    		  CDBJCoding.setTradeBean(tb, ee);//完成tradeID的设置
    		  
    		  //补充sum trade information
    		  CDBJCoding.setHowmanytrade(CDBJCoding.getTradeBean().length);
    		  
    		  
    		  
    	  }// end "0" else; end tradeid,补充完需要的相应的trade information了
    	  
    	  //writeTradeInfo(CDBJCoding);//写入到文件
    	  //查看CDBJ当中的TradeInformation
    	  
    	  String autor=General.delallbiaodian(CDBJCoding.getAutomaticReply());//处理自动回复
    	  if("yes".equalsIgnoreCase(autor)){
    		  CDBJCoding.setAutomaticReply_judge(1);
    	  }else if("no".equalsIgnoreCase(autor)){
    		  CDBJCoding.setAutomaticReply_judge(0);
    	  }else{
    		  System.out.println("AutomaticReply编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			  tempdialogbean.getContentb()[0].getTime()+
	  	 			  "_AutomaticReply编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+
	  	 			  "; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  
    	  String pavailablei=General.delSpaceChineseBiaodian(CDBJCoding.getProductAvailableInquire());//处理Product Available Inquire
    	  String pavailablein[] = pavailablei.split(";");
    	  if("no".equalsIgnoreCase(pavailablein[0])){
    		  CDBJCoding.setProductAvailableInquire_judge(0);
    		  CDBJCoding.setProductAvailableInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(pavailablein[0])){
    		  CDBJCoding.setProductAvailableInquire_judge(1);
    		  CDBJCoding.setProductAvailableInquire_number(pavailablein.length-1);
    	  }else{
    		  System.out.println("ProductAvailableInquire编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductAvailableInquire编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  
    	  String pqualityi=General.delSpaceChineseBiaodian(CDBJCoding.getProductQualityInquire());//处理Product Quality Inquire
    	  String pqualityin[] = pqualityi.split(";");
    	  if("no".equalsIgnoreCase(pqualityin[0])){
    		  CDBJCoding.setProductQualityInquire_judge(0);
    		  CDBJCoding.setProductQualityInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(pqualityin[0])){
    		  CDBJCoding.setProductQualityInquire_judge(1);
    		  CDBJCoding.setProductQualityInquire_number(pqualityin.length-1);
    	  }else{
    		  System.out.println("ProductQualityInquire编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductQualityInquire编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }

    	  String dinquire=General.delSpaceChineseBiaodian(CDBJCoding.getDeliveryInquire());//处理Delievery Inquire
    	  String dinquiren[] = dinquire.split(";");
    	  if("no".equalsIgnoreCase(dinquiren[0])){
    		  CDBJCoding.setDeliveryInquire_judge(0);
    		  CDBJCoding.setDeliveryInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(dinquiren[0])){
    		  CDBJCoding.setDeliveryInquire_judge(1);
    		  CDBJCoding.setDeliveryInquire_number(dinquiren.length-1);
    	  }else{
    		  System.out.println("DeliveryInquire编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_DeliveryInquire编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  
    	  String rinquiry=General.delSpaceChineseBiaodian(CDBJCoding.getReturnInquiry());//处理Return Inquire
    	  String rinquiryn[] = rinquiry.split(";");
    	  if("no".equalsIgnoreCase(rinquiryn[0])){
    		  CDBJCoding.setReturnInquiry_judge(0);
    		  CDBJCoding.setReturnInquiry_number(0);
    	  }else if("yes".equalsIgnoreCase(rinquiryn[0])){
    		  CDBJCoding.setReturnInquiry_judge(1);
    		  CDBJCoding.setReturnInquiry_number(rinquiryn.length-1);
    	  }else{
    		  System.out.println("ReturnInquiry编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ReturnInquiry编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  
    	  String confirmps=General.delSpaceChineseBiaodian(CDBJCoding.getConfirmPromisedService());//处理Confirm Promise Service
    	  String confirmpsn[] = confirmps.split(";");
    	  if("no".equalsIgnoreCase(confirmpsn[0])){
    		  CDBJCoding.setConfirmPromisedService_judge(0);
    		  CDBJCoding.setConfirmPromisedService_number(0);
    	  }else if("yes".equalsIgnoreCase(confirmpsn[0])){
    		  CDBJCoding.setConfirmPromisedService_judge(1);
    		  CDBJCoding.setConfirmPromisedService_number(confirmpsn.length-1);
    	  }else{
    		  System.out.println("ConfirmPromisedService编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ConfirmPromisedService编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }

    	  
    	  
    	  String productf=General.delSpaceChineseBiaodian(CDBJCoding.getProductFeature());//处理Product Feature
    	  String productfn[] = productf.split(";");
    	  if("no".equalsIgnoreCase(productfn[0])){
    		  CDBJCoding.setProductFeature_judge(0);
    		  CDBJCoding.setProductFeature_number(0);
    	  }else if("yes".equalsIgnoreCase(productfn[0])){
    		  CDBJCoding.setProductFeature_judge(1);
    		  CDBJCoding.setProductFeature_number(productfn.length-1);
    	  }else{
    		  System.out.println("CProductFeature编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductFeature编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  
    	  String pselectionb=General.delSpaceChineseBiaodian(CDBJCoding.getProductSelectionBehavior());//处理Product Selection
    	  String pselectionbn[] = pselectionb.split(";");
    	  if("no".equalsIgnoreCase(pselectionbn[0])){
    		  CDBJCoding.setProductSelectionBehavior_judge(0);
    		  CDBJCoding.setProductSelectionBehavior_number(0);
    	  }else if("yes".equalsIgnoreCase(pselectionbn[0])){
    		  CDBJCoding.setProductSelectionBehavior_judge(1);
    		  if("Substituteproducts".equalsIgnoreCase(pselectionbn[1]))CDBJCoding.setProductSelectionBehavior_number(1);
    		  else if("ComplementaryProducts".equalsIgnoreCase(pselectionbn[1]))CDBJCoding.setProductSelectionBehavior_number(2);
    		  else if("Not-relatedproducts".equalsIgnoreCase(pselectionbn[1]))CDBJCoding.setProductSelectionBehavior_number(3);
    		  else {
    			  System.out.println("Product Selection 编码有问题");
    	  	 	  WriteStreamAppend.method1(errorlogfilepath,
    	  	 			tempdialogbean.getContentb()[0].getTime()+"_Product Selection 编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    		  }
    	  }else{
    		  System.out.println("Product Selection 编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,"Product Selection 编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  String bargaining=General.delSpaceChineseBiaodian(CDBJCoding.getBargaining());//处理bargaining
    	  String bargainingn[] = bargaining.split(";");
    	  if("no".equalsIgnoreCase(bargainingn[0])){
    		  CDBJCoding.setBargaining_judge(0);
    		  CDBJCoding.setBargaining_number(0);
    	  }else if("yes".equalsIgnoreCase(bargainingn[0])){
    		  CDBJCoding.setBargaining_judge(1);
    		  CDBJCoding.setBargaining_number(bargainingn.length-1);
    	  }else{
    		  System.out.println("CProductFeature编码有问题");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_Bargaining 编码有问题"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//报错，因为tradeID不符合规范。
    	  }
    	  
    	  //FlexiblePolicy由于编码问题，暂时不做处理
    	  
    	  
    	  //底下开始处理emotional icons，具体编码再议，而且，还需要处理，“亲“的编码
    	  String sellerei=General.delSpaceChineseBiaodian(CDBJCoding.getSellerEmotionalIcons());//处理bargaining
    	  String sellerein[] = sellerei.split(";");
    	  if("no".equalsIgnoreCase(sellerein[0])){
    		  CDBJCoding.setSellerEmotionalIcons_judge(0);
    		  CDBJCoding.setSellerEmotionalIcons_number(0);
    	  }else{
    		  CDBJCoding.setSellerEmotionalIcons_judge(1);
    		  CDBJCoding.setSellerEmotionalIcons_number(sellerein.length);
    	  }
    	  
    	  //处理buyer emotional icons
    	  String buyerei=General.delSpaceChineseBiaodian(CDBJCoding.getBuyerEmotionalIcons());//处理bargaining
    	  String buyerein[] = buyerei.split(";");
    	  if("no".equalsIgnoreCase(buyerein[0])){
    		  CDBJCoding.setBuyerEmotionalIcons_judge(0);
    		  CDBJCoding.setBuyerEmotionalIcons_number(0);
    	  }else{
    		  CDBJCoding.setBuyerEmotionalIcons_judge(1);
    		  CDBJCoding.setBuyerEmotionalIcons_number(buyerein.length);  
    	  }
    	  
    	  //处理give up reasons，并无具体的处理动作
    	  String giveupr=General.delSpaceChineseBiaodian(CDBJCoding.getGiveUpReason());//处理bargaining
           
    	  
    	  return CDBJCoding;
      }
      //重建一个新的
      public static CalDataBean calculatesum(CalDataBean CDBJ,CalDataBean CDBW){
    	  CalDataBean cdbsum = new CalDataBean();
    	  //处理communication pattern的数据
    	  cdbsum.setC_asks_number(CDBJ.getC_asks_number()+CDBW.getC_asks_number());
    	  cdbsum.setC_words_number(CDBJ.getC_words_number()+CDBW.getC_words_number());
    	  cdbsum.setFirst_response_time(CDBJ.getFirst_response_time());//只取第一段回复的时间
    	  cdbsum.setS_answers_number(CDBJ.getS_answers_number()+CDBW.getS_answers_number());
    	  cdbsum.setS_words_number(CDBJ.getS_words_number()+CDBW.getS_words_number());
    	  cdbsum.setTotal_response_number(CDBJ.getTotal_response_number()+CDBW.getTotal_response_number());
    	  cdbsum.setTotal_response_time(CDBJ.getTotal_response_time()+CDBW.getTotal_response_time());
    	  cdbsum.setDuration_time(CDBJ.getDuration_time()+CDBW.getDuration_time());
    	  cdbsum.setS_qin_number(CDBJ.getS_qin_number()+CDBW.getS_qin_number());
    	  cdbsum.setB_qin_number(CDBJ.getB_qin_number()+CDBW.getB_qin_number());
    	  cdbsum.setCombined_c_asks_number(CDBJ.getCombined_c_asks_number()+CDBW.getCombined_c_asks_number());
    	  cdbsum.setTotal_asks_time(CDBJ.getTotal_asks_time()+CDBW.getTotal_asks_time());
    	  cdbsum.setC_asks__number_paragraph(CDBJ.getC_asks__number_paragraph()+CDBW.getC_asks__number_paragraph());
    	  cdbsum.setS_answers_number_paragraph(CDBJ.getS_answers_number_paragraph()+CDBW.getS_answers_number_paragraph());
    	  cdbsum.setTotal_response_line_words(CDBJ.getTotal_response_line_words()+CDBW.getTotal_response_line_words());
    	  cdbsum.setFirst_response_line_words(CDBJ.getFirst_response_line_words());
    	  cdbsum.setCombined_c_asks_line_words(CDBJ.getCombined_c_asks_line_words()+CDBW.getCombined_c_asks_line_words());
    	  
    	  ArrayList<Long> TRTI = new ArrayList<Long>();
    	  TRTI.addAll(CDBJ.getTotal_response_time_individual());
    	  TRTI.addAll(CDBW.getTotal_response_time_individual());
    	  cdbsum.setTotal_response_time_individual(TRTI);
    	  
    	  ArrayList<Integer> TRLWI = new  ArrayList<Integer>();
    	  TRLWI.addAll(CDBJ.getTotal_response_line_words_individual());
    	  TRLWI.addAll(CDBW.getTotal_response_line_words_individual());
    	  cdbsum.setTotal_response_line_words_individual(TRLWI);
    	  
    	  ArrayList<Long> TATI = new ArrayList<Long>();
    	  TATI.addAll(CDBJ.getTotal_ask_time_individual());
    	  TATI.addAll(CDBW.getTotal_ask_time_individual());
    	  cdbsum.setTotal_ask_time_individual(TATI);
    	  
    	  
    	  ArrayList<Integer> CCALWI = new ArrayList<Integer>();
    	  CCALWI.addAll(CDBJ.getCombined_c_asks_line_words_individual());
    	  CCALWI.addAll(CDBW.getCombined_c_asks_line_words_individual());
    	  cdbsum.setCombined_c_asks_line_words_individual(CCALWI);
    	  
    	  //2013-08-13 start
    	  int temp[]= new int[8];
    	  for(int i=0;i<8;i++){
    		  temp[i]=CDBJ.getBuyerEmotionalWords()[i]+CDBW.getBuyerEmotionalWords()[i];
    	  }
    	  cdbsum.setBuyerEmotionalWords(temp);
    	  
    	  int tempp[]=new int[8];
    	  for(int i=0;i<8;i++){
    		  tempp[i]=CDBJ.getSellerEmotionalWords()[i]+CDBW.getSellerEmotionalWords()[i];
    	  }
    	  cdbsum.setSellerEmotionalWords(tempp);
          
    	  //2013-08-13 end
    	  
    	  //处理coding的数据
    	  cdbsum.setCommunicationType(CDBJ.getCommunicationType()+"$"+CDBW.getCommunicationType());
    	  cdbsum.setProductName(CDBJ.getProductName()+"$"+CDBW.getProductName());
    	  cdbsum.setCommunicationcontent(CDBJ.getCommunicationcontent()+"$"+CDBW.getCommunicationcontent());
    	  cdbsum.setTradeID(CDBJ.getTradeID()+"$"+CDBW.getTradeID());
    	  cdbsum.setPurchaseornot(CDBJ.isPurchaseornot()||CDBW.isPurchaseornot());
    	  cdbsum.setAutomaticReply(CDBJ.getAutomaticReply()+"$"+CDBW.getAutomaticReply());
    	  cdbsum.setAutomaticReply_judge(General.judgeOr(CDBJ.getAutomaticReply_judge(), CDBW.getAutomaticReply_judge()));
    	  cdbsum.setProductAvailableInquire(CDBJ.getProductAvailableInquire()+"$"+CDBW.getProductAvailableInquire());
    	  cdbsum.setProductAvailableInquire_judge(General.judgeOr(CDBJ.getProductAvailableInquire_judge(),CDBW.getProductAvailableInquire_judge()));
    	  cdbsum.setProductAvailableInquire_number(CDBJ.getProductAvailableInquire_number()+CDBW.getProductAvailableInquire_number());
    	  cdbsum.setProductQualityInquire(CDBJ.getProductQualityInquire()+"$"+CDBW.getProductQualityInquire());
    	  cdbsum.setProductQualityInquire_judge(General.judgeOr(CDBJ.getProductQualityInquire_judge(),CDBW.getProductQualityInquire_judge()));
    	  cdbsum.setProductQualityInquire_number(CDBJ.getProductQualityInquire_number()+CDBW.getProductQualityInquire_number());
    	  cdbsum.setDeliveryInquire(CDBJ.getDeliveryInquire()+"$"+CDBW.getDeliveryInquire());
    	  cdbsum.setDeliveryInquire_judge(General.judgeOr(CDBJ.getDeliveryInquire_judge(),CDBW.getDeliveryInquire_judge()));
    	  cdbsum.setDeliveryInquire_number(CDBJ.getDeliveryInquire_number()+CDBW.getDeliveryInquire_number());
    	  cdbsum.setReturnInquiry(CDBJ.getReturnInquiry()+"$"+CDBW.getReturnInquiry());
    	  cdbsum.setReturnInquiry_judge(General.judgeOr(CDBJ.getReturnInquiry_judge(),CDBW.getReturnInquiry_judge()));
    	  cdbsum.setReturnInquiry_number(CDBJ.getReturnInquiry_number()+CDBW.getReturnInquiry_number());
    	  cdbsum.setConfirmPromisedService(CDBJ.getConfirmPromisedService()+"$"+CDBW.getConfirmPromisedService());
    	  cdbsum.setConfirmPromisedService_judge(General.judgeOr(CDBJ.getConfirmPromisedService_judge(),CDBW.getConfirmPromisedService_judge()));
    	  cdbsum.setConfirmPromisedService_number(CDBJ.getConfirmPromisedService_number()+CDBW.getConfirmPromisedService_number());
    	  cdbsum.setProductFeature(CDBJ.getProductFeature()+"$"+CDBW.getProductFeature());
    	  cdbsum.setProductFeature_judge(General.judgeOr(CDBJ.getProductFeature_judge(),CDBW.getProductFeature_judge()));
    	  cdbsum.setProductFeature_number(CDBJ.getProductFeature_number()+CDBW.getProductFeature_number());
    	  cdbsum.setProductSelectionBehavior(CDBJ.getProductSelectionBehavior()+"$"+CDBW.getProductSelectionBehavior());
    	  cdbsum.setProductSelectionBehavior_judge(General.judgeOr(CDBJ.getProductSelectionBehavior_judge(),CDBW.getProductSelectionBehavior_judge()));
    	  cdbsum.setProductSelectionBehavior_number(CDBJ.getProductSelectionBehavior_number());
    	  cdbsum.setBargaining(CDBJ.getBargaining()+"$"+CDBW.getBargaining());
    	  cdbsum.setBargaining_judge(General.judgeOr(CDBJ.getBargaining_judge(),CDBW.getBargaining_judge()));
    	  cdbsum.setBargaining_number(CDBJ.getBargaining_number()+CDBW.getBargaining_number());
    	  cdbsum.setSellerEmotionalIcons(CDBJ.getSellerEmotionalIcons()+"$"+CDBW.getSellerEmotionalIcons());
    	  cdbsum.setSellerEmotionalIcons_judge(General.judgeOr(CDBJ.getSellerEmotionalIcons_judge(),CDBW.getSellerEmotionalIcons_judge()));
    	  cdbsum.setSellerEmotionalIcons_number(CDBJ.getSellerEmotionalIcons_number()+CDBW.getSellerEmotionalIcons_number());
    	  cdbsum.setBuyerEmotionalIcons(CDBJ.getBuyerEmotionalIcons()+"$"+CDBW.getBuyerEmotionalIcons());
    	  cdbsum.setBuyerEmotionalIcons_judge(General.judgeOr(CDBJ.getBuyerEmotionalIcons_judge(),CDBW.getBuyerEmotionalIcons_judge()));
    	  cdbsum.setBuyerEmotionalIcons_number(CDBJ.getBuyerEmotionalIcons_number()+CDBW.getBuyerEmotionalIcons_number());
    	  cdbsum.setGiveUpReason(CDBJ.getGiveUpReason()+"$"+CDBW.getGiveUpReason());
    	  

    	  //把交易数据传递过去
    	  
    	  cdbsum.setTradeBean(CDBJ.getTradeBean(), CDBJ.getTradeBean().length);
    	  cdbsum.setHowmanytrade(CDBJ.getHowmanytrade());
    	  cdbsum.setSumtradeid(CDBJ.getSumtradeid());
    	  cdbsum.setSumtradestatus(CDBJ.getSumtradestatus());
    	  cdbsum.setSumdealtime(CDBJ.getSumdealtime());
    	  cdbsum.setSumamountmoney(CDBJ.getSumamountmoney());
    	  cdbsum.setSumposttype(CDBJ.getSumposttype());
    	  cdbsum.setHowmanyitem(CDBJ.getHowmanyitem());
    	  cdbsum.setSumitemid(CDBJ.getSumitemid());
    	  cdbsum.setSumitemname(CDBJ.getSumitemname());
    	  cdbsum.setSumitemprice(CDBJ.getSumitemprice());
    	  cdbsum.setSumitemquatity(CDBJ.getSumitemquatity());
    	  return cdbsum;
      }
      
      
      
      
      //
      
      
      
      //向文件中写出trade information
      public static void writeTradeInfo(CalDataBean CDBJCoding){
    	  String dataTradeInforpath = "E:/Lele/Taobao/datalogTradeInfor.txt";
    	  for(TradeBean singletradebean:CDBJCoding.getTradeBean()){
    		  WriteStreamAppend.method1(dataTradeInforpath,"TradeID is "+ singletradebean.getTradeid()+
    				  " Trade Status is "+singletradebean.getTradestatus()+
    				  " Deal Time is "+singletradebean.getDealtime()+
    				  " Amount Money is "+singletradebean.getAmountmoney()+
    				  " Post Type is "+singletradebean.getPosttype()+
    				  "\r\n");
    		  for(ItemBean singleitembean:singletradebean.getIteamBean()){
    			  WriteStreamAppend.method1(dataTradeInforpath,
    			  "The following Trade Item:  ItemID is"+singleitembean.getItemid()+
    			  " Item Name is "+singleitembean.getItemname()+
    			  " Item Price is "+singleitembean.getItemprice()+
    			  " Item Quatity is "+singleitembean.getItemquatity()+
    			  "\r\n");
    		  }
    		  WriteStreamAppend.method1(dataTradeInforpath,"\r\n\r\n\r\n\r\n\r\n");
    	  }
      }

      //一下两个方法用以处理，每一个session
      public static void writecalNo(CalDataBean CDB,String biaoji){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogNo.txt";
    	  WriteStreamAppend.method1(datalogNofilepath,"0," +
			   		CDB.getTotal_response_time()+"," +
			   		CDB.getTotal_response_number()+"," +
			   		CDB.getFirst_response_time()+"," +
			   		CDB.getC_asks_number()+"," +
			   		CDB.getS_answers_number()+"," +
			   		CDB.getC_words_number()+","+
			   		CDB.getS_words_number()+","+
			   		CDB.getDuration_time()+biaoji+
			   		"\r\n");
      }
      
      public static void writecalYes(CalDataBean CDB,String biaoji){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogYes.txt";
    	  WriteStreamAppend.method1(datalogNofilepath,"1," +
			   		CDB.getTotal_response_time()+"," +
			   		CDB.getTotal_response_number()+"," +
			   		CDB.getFirst_response_time()+"," +
			   		CDB.getC_asks_number()+"," +
			   		CDB.getS_answers_number()+"," +
			   		CDB.getC_words_number()+","+
			   		CDB.getS_words_number()+","+
			   		CDB.getDuration_time()+biaoji+
			   		"\r\n");
      }
      //一下两个方法用以处理，最后的几个session的总计（就是几个session如果针对一个同一个trade）
      public static void writecalSumNo(CalDataBean CDB,String biaoji,DialogBean tempdialogbean){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogNo.txt";
    	  WriteStreamAppend.method1(datalogNofilepath,"0," +
			   		CDB.getTotal_response_time()+"," +
			   		CDB.getTotal_response_number()+"," +
			   		CDB.getFirst_response_time()+"," +
			   		CDB.getC_asks_number()+"," +
			   		CDB.getS_answers_number()+"," +
			   		CDB.getC_words_number()+","+
			   		CDB.getS_words_number()+","+
			   		CDB.getDuration_time()+biaoji+", 没有购买相应产品. "
			   		+" Communication Product is "+tempdialogbean.getCommunicationProduct()+
			   		", Communication Content is "+tempdialogbean.getCommunicationContent()+", AutomaticReply is "+
			   		tempdialogbean.getAutomaticReply()+"."+
			   		"\r\n");
      }
      public static void writecalSumYes(CalDataBean CDB,String biaoji,DialogBean tempdialogbean){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogYes.txt";
    	  //增加代码，输出相应的购买记录
    	  
    	  WriteStreamAppend.method1(datalogNofilepath,"1," +
			   		CDB.getTotal_response_time()+"," +
			   		CDB.getTotal_response_number()+"," +
			   		CDB.getFirst_response_time()+"," +
			   		CDB.getC_asks_number()+"," +
			   		CDB.getS_answers_number()+"," +
			   		CDB.getC_words_number()+","+
			   		CDB.getS_words_number()+","+
			   		CDB.getDuration_time()+biaoji+" Communication Product is "+tempdialogbean.getCommunicationProduct()+
			   		", Communication Content is "+tempdialogbean.getCommunicationContent()+", AutomaticReply is "+
			   		tempdialogbean.getAutomaticReply()+". ");
			   		//"\r\n");
    	            DealTrade.SearchTradeid(tempdialogbean.getTradeid());
      }
      public static void writeNoTempBeanDialog(DialogBean tempdialogbean){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogNo.txt";
    	  WriteStreamAppend.method1(datalogNofilepath,tempdialogbean.getSellertitle()+"\r\n"
    			  +tempdialogbean.getLogdate()+"\r\n");
    	  for(int i=0;i<tempdialogbean.getContentb().length;i++){
    		  WriteStreamAppend.method1(datalogNofilepath,tempdialogbean.getContentb()[i].getTime()+": "+
    				  tempdialogbean.getContentb()[i].getTitle()+"  "+
    				  tempdialogbean.getContentb()[i].getContent()+"\r\n");
    	  }
      }
      public static void writeAllTempBeanDialog(DialogBean tempdialogbean){
    	  String datalogAllfilepath  = "E:/Lele/Taobao/datalogAll.txt";
    	  WriteStreamAppend.method1(datalogAllfilepath,tempdialogbean.getSellertitle()+"\r\n"
    			  +tempdialogbean.getLogdate()+"\r\n");
    	  for(int i=0;i<tempdialogbean.getContentb().length;i++){
    		  WriteStreamAppend.method1(datalogAllfilepath,tempdialogbean.getContentb()[i].getTime()+": "+
    				  tempdialogbean.getContentb()[i].getTitle()+"  "+
    				  tempdialogbean.getContentb()[i].getContent()+"\r\n");
    	  }
    	  WriteStreamAppend.method1(datalogAllfilepath,"TradeID is "+tempdialogbean.getTradeid()+"\r\n"
    			  +tempdialogbean.getLogdate()+"\r\n");
      }
      public static void writeYesTempBeanDialog(DialogBean tempdialogbean){
    	  String datalogYesfilepath  = "E:/Lele/Taobao/datalogYes.txt";
    	  WriteStreamAppend.method1(datalogYesfilepath,tempdialogbean.getSellertitle()+"\r\n"
    			  +tempdialogbean.getLogdate()+"\r\n");
    	  for(int i=0;i<tempdialogbean.getContentb().length;i++){
    		  WriteStreamAppend.method1(datalogYesfilepath,tempdialogbean.getContentb()[i].getTime()+": "+
    				  tempdialogbean.getContentb()[i].getTitle()+"  "+
    				  tempdialogbean.getContentb()[i].getContent()+"\r\n");
      }
      }
}

class CalDataBean {
	//Part 1: Communication Pattern
	
	
	String RandomString;
	  
	CalDataBean(){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String nono = String.valueOf(System.nanoTime());
		String tmp = timeStamp + nono;
		setRandomString(tmp);
	  }
	  

  public String getRandomString() {
		return RandomString;
	}

	public void setRandomString(String randomString) {
		RandomString = randomString;
	}
	
		public long getTotal_response_time() {
			return total_response_time;
		}
		public void setTotal_response_time(long total_response_time) {
			this.total_response_time = total_response_time;
		}
		public int getTotal_response_number() {
			return total_response_number;
		}
		public void setTotal_response_number(int total_response_number) {
			this.total_response_number = total_response_number;
		}	
		public long getFirst_response_time() {
			return first_response_time;
		}
		public void setFirst_response_time(long first_response_time) {
			this.first_response_time = first_response_time;
		}
		public int getC_asks_number() {
			return c_asks_number;
		}
		public void setC_asks_number(int c_asks_number) {
			this.c_asks_number = c_asks_number;
		}
		public int getS_answers_number() {
			return s_answers_number;
		}
		public void setS_answers_number(int s_answers_number) {
			this.s_answers_number = s_answers_number;
		}
		public int getC_words_number() {
			return c_words_number;
		}
		public void setC_words_number(int c_words_number) {
			this.c_words_number = c_words_number;
		}
		public int getS_words_number() {
			return s_words_number;
		}
		public void setS_words_number(int s_words_number) {
			this.s_words_number = s_words_number;
		}
		public long getDuration_time() {
			return duration_time;
		}
		public void setDuration_time(long duration_time) {
			this.duration_time = duration_time;
		}
		
	   public int getS_qin_number() {
			return s_qin_number;
		}
		public void setS_qin_number(int s_qin_number) {
			this.s_qin_number = s_qin_number;
		}
		public int getB_qin_number() {
			return b_qin_number;
		}
		public void setB_qin_number(int b_qin_number) {
			this.b_qin_number = b_qin_number;
		}

		public int getCombined_c_asks_number() {
			return combined_c_asks_number;
		}
		public void setCombined_c_asks_number(int combined_c_asks_number) {
			this.combined_c_asks_number = combined_c_asks_number;
		}
		public long getTotal_asks_time() {
			return total_asks_time;
		}
		public void setTotal_asks_time(long total_asks_time) {
			this.total_asks_time = total_asks_time;
		}
		public int getC_asks__number_paragraph() {
			return c_asks__number_paragraph;
		}
		public void setC_asks__number_paragraph(int c_asks__number_paragraph) {
			this.c_asks__number_paragraph = c_asks__number_paragraph;
		}
		public int getS_answers_number_paragraph() {
			return s_answers_number_paragraph;
		}
		public void setS_answers_number_paragraph(int s_answers_number_paragraph) {
			this.s_answers_number_paragraph = s_answers_number_paragraph;
		}



		public int getTotal_response_line_words() {
			return total_response_line_words;
		}
		public void setTotal_response_line_words(int total_response_line_words) {
			this.total_response_line_words = total_response_line_words;
		}
		public int getFirst_response_line_words() {
			return first_response_line_words;
		}
		public void setFirst_response_line_words(int first_response_line_words) {
			this.first_response_line_words = first_response_line_words;
		}
		public int getCombined_c_asks_line_words() {
			return combined_c_asks_line_words;
		}
		public void setCombined_c_asks_line_words(int combined_c_asks_line_words) {
			this.combined_c_asks_line_words = combined_c_asks_line_words;
		}
		
		
	   long total_response_time;     //总回复时间，所谓总回复时间是指，连续的某人对话，当成一个整体对待，讲的是整段和整段之间的时间差
	   int  total_response_number;	 //总回复数目，讲的是整段卖家，整段买家一共有多少段
	   long first_response_time;     //第一条回复时间
	   int  c_asks_number;           //买家对话条数
	   int  s_answers_number;        //卖家对话条数
	   int  c_words_number;          //买家对话中的字数
	   int  s_words_number;          //卖家对话中的字数
	   long duration_time;
	   
	   int s_qin_number;             //卖家谈话中“亲”的次数
	   int b_qin_number;             //买家谈话中“亲”的次数
	   
	   int combined_c_asks_number;
	   long total_asks_time;
	   int c_asks__number_paragraph;
	   int s_answers_number_paragraph;
	   
	   int  total_response_line_words;   //回复时候，卖家第一行回复的字数的总和
	   int  first_response_line_words;   //卖家第一条回复的字数
	   int combined_c_asks_line_words;    //买家再次急切问问题时的第一行问题字数
	   
	//Part 2: Coding data   
	   String CommunicationType;//Commmunication Type content
	   String ProductName;//Further code to exprience and search product
	   String ProductNameFromDatabase;//如果是购买了的产品，可以从数据库中提取产品名称，NO表示没有购买数据库中没有
	   String communicationcontent;
	   String TradeID;
	   boolean purchaseornot;//用于判断这一对话是引起了购买还是没有，如果是false就是没有购买，如果是true就是购买了
	   String AutomaticReply;//用于存储具体的编码内容
	   int AutomaticReply_judge;//用于存储处理后的结果0表示No，1表示Yes
	   String ProductAvailableInquire;//用于存储具体的编码内容
	   int ProductAvailableInquire_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ProductAvailableInquire_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String ProductQualityInquire;//用于存储具体的编码内容
	   int ProductQualityInquire_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ProductQualityInquire_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String DeliveryInquire;//用于存储具体的编码内容
	   int DeliveryInquire_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int DeliveryInquire_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String ReturnInquiry;//用于存储具体的编码内容
	   int ReturnInquiry_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ReturnInquiry_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String ConfirmPromisedService;//用于存储具体的编码内容
	   int ConfirmPromisedService_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ConfirmPromisedService_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String ProductFeature;//用于存储具体的编码内容
	   int ProductFeature_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ProductFeature_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String ProductSelectionBehavior;//用于存储具体的编码内容
	   int ProductSelectionBehavior_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int ProductSelectionBehavior_number;//Yes后面的内容数目，1表示 Substitute products;2表示 Complementary Products；3表示Not-related products
	   String Bargaining;//用于存储具体的编码内容
	   int Bargaining_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int Bargaining_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   
	   //FlexiblePolicy由于编码不规范，暂不处理
	   
	   String FlexiblePolicy;//用于存储具体的编码内容
	   int FlexiblePolicy_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int FlexiblePolicy_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String SellerEmotionalIcons;//用于存储具体的编码内容
	   int SellerEmotionalIcons_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int SellerEmotionalIcons_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String BuyerEmotionalIcons;//用于存储具体的编码内容
	   int BuyerEmotionalIcons_judge;//用于存储处理后的结果0表示No，1表示Yes
	   int BuyerEmotionalIcons_number;//Yes后面的内容数目，比如说问了1次，两次，3次？
	   String GiveUpReason;//用于存储具体的编码内容
	   
	   
	   //2013-08-13;
	   //重新计算Emotional Words
	   int SellerEmotionalWords[];
	   int BuyerEmotionalWords[];
	public int[] getSellerEmotionalWords() {
		return SellerEmotionalWords;
	}
	public void setSellerEmotionalWords(int[] sellerEmotionalWords) {
		SellerEmotionalWords = sellerEmotionalWords;
	}
	public int[] getBuyerEmotionalWords() {
		return BuyerEmotionalWords;
	}
	public void setBuyerEmotionalWords(int[] buyerEmotionalWords) {
		BuyerEmotionalWords = buyerEmotionalWords;
	}
	
	
	//2013-08-13
	public String getGiveUpReason() {
		return GiveUpReason;
	}
	public void setGiveUpReason(String giveUpReason) {
		GiveUpReason = giveUpReason;
	}
	public String getCommunicationType() {
		return CommunicationType;
	}
	public void setCommunicationType(String communicationType) {
		CommunicationType = communicationType;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductNameFromDatabase() {
		return ProductNameFromDatabase;
	}
	public void setProductNameFromDatabase(String productNameFromDatabase) {
		ProductNameFromDatabase = productNameFromDatabase;
	}
	public String getCommunicationcontent() {
		return communicationcontent;
	}
	public void setCommunicationcontent(String communicationcontent) {
		this.communicationcontent = communicationcontent;
	}
	public String getTradeID() {
		return TradeID;
	}
	public void setTradeID(String tradeID) {
		TradeID = tradeID;
	}
	
	public boolean isPurchaseornot() {
		return purchaseornot;
	}
	public void setPurchaseornot(boolean purchaseornot) {
		this.purchaseornot = purchaseornot;
	}
	
	public String getAutomaticReply() {
		return AutomaticReply;
	}
	public void setAutomaticReply(String automaticReply) {
		AutomaticReply = automaticReply;
	}
	public int getAutomaticReply_judge() {
		return AutomaticReply_judge;
	}
	public void setAutomaticReply_judge(int automaticReply_judge) {
		AutomaticReply_judge = automaticReply_judge;
	}
	public String getProductAvailableInquire() {
		return ProductAvailableInquire;
	}
	public void setProductAvailableInquire(String productAvailableInquire) {
		ProductAvailableInquire = productAvailableInquire;
	}
	public int getProductAvailableInquire_judge() {
		return ProductAvailableInquire_judge;
	}
	public void setProductAvailableInquire_judge(int productAvailableInquire_judge) {
		ProductAvailableInquire_judge = productAvailableInquire_judge;
	}
	public int getProductAvailableInquire_number() {
		return ProductAvailableInquire_number;
	}
	public void setProductAvailableInquire_number(int productAvailableInquire_number) {
		ProductAvailableInquire_number = productAvailableInquire_number;
	}
	public String getProductQualityInquire() {
		return ProductQualityInquire;
	}
	public void setProductQualityInquire(String productQualityInquire) {
		ProductQualityInquire = productQualityInquire;
	}
	public int getProductQualityInquire_judge() {
		return ProductQualityInquire_judge;
	}
	public void setProductQualityInquire_judge(int productQualityInquire_judge) {
		ProductQualityInquire_judge = productQualityInquire_judge;
	}
	public int getProductQualityInquire_number() {
		return ProductQualityInquire_number;
	}
	public void setProductQualityInquire_number(int productQualityInquire_number) {
		ProductQualityInquire_number = productQualityInquire_number;
	}
	public String getDeliveryInquire() {
		return DeliveryInquire;
	}
	public void setDeliveryInquire(String deliveryInquire) {
		DeliveryInquire = deliveryInquire;
	}
	public int getDeliveryInquire_judge() {
		return DeliveryInquire_judge;
	}
	public void setDeliveryInquire_judge(int deliveryInquire_judge) {
		DeliveryInquire_judge = deliveryInquire_judge;
	}
	public int getDeliveryInquire_number() {
		return DeliveryInquire_number;
	}
	public void setDeliveryInquire_number(int deliveryInquire_number) {
		DeliveryInquire_number = deliveryInquire_number;
	}
	public String getReturnInquiry() {
		return ReturnInquiry;
	}
	public void setReturnInquiry(String returnInquiry) {
		ReturnInquiry = returnInquiry;
	}
	public int getReturnInquiry_judge() {
		return ReturnInquiry_judge;
	}
	public void setReturnInquiry_judge(int returnInquiry_judge) {
		ReturnInquiry_judge = returnInquiry_judge;
	}
	public int getReturnInquiry_number() {
		return ReturnInquiry_number;
	}
	public void setReturnInquiry_number(int returnInquiry_number) {
		ReturnInquiry_number = returnInquiry_number;
	}
	public String getConfirmPromisedService() {
		return ConfirmPromisedService;
	}
	public void setConfirmPromisedService(String confirmPromisedService) {
		ConfirmPromisedService = confirmPromisedService;
	}
	public int getConfirmPromisedService_judge() {
		return ConfirmPromisedService_judge;
	}
	public void setConfirmPromisedService_judge(int confirmPromisedService_judge) {
		ConfirmPromisedService_judge = confirmPromisedService_judge;
	}
	public int getConfirmPromisedService_number() {
		return ConfirmPromisedService_number;
	}
	public void setConfirmPromisedService_number(int confirmPromisedService_number) {
		ConfirmPromisedService_number = confirmPromisedService_number;
	}
	public String getProductFeature() {
		return ProductFeature;
	}
	public void setProductFeature(String productFeature) {
		ProductFeature = productFeature;
	}
	public int getProductFeature_judge() {
		return ProductFeature_judge;
	}
	public void setProductFeature_judge(int productFeature_judge) {
		ProductFeature_judge = productFeature_judge;
	}
	public int getProductFeature_number() {
		return ProductFeature_number;
	}
	public void setProductFeature_number(int productFeature_number) {
		ProductFeature_number = productFeature_number;
	}
	public String getProductSelectionBehavior() {
		return ProductSelectionBehavior;
	}
	public void setProductSelectionBehavior(String productSelectionBehavior) {
		ProductSelectionBehavior = productSelectionBehavior;
	}
	public int getProductSelectionBehavior_judge() {
		return ProductSelectionBehavior_judge;
	}
	public void setProductSelectionBehavior_judge(int productSelectionBehavior_judge) {
		ProductSelectionBehavior_judge = productSelectionBehavior_judge;
	}
	public int getProductSelectionBehavior_number() {
		return ProductSelectionBehavior_number;
	}
	public void setProductSelectionBehavior_number(
			int productSelectionBehavior_number) {
		ProductSelectionBehavior_number = productSelectionBehavior_number;
	}
	public String getBargaining() {
		return Bargaining;
	}
	public void setBargaining(String bargaining) {
		Bargaining = bargaining;
	}
	public int getBargaining_judge() {
		return Bargaining_judge;
	}
	public void setBargaining_judge(int bargaining_judge) {
		Bargaining_judge = bargaining_judge;
	}
	public int getBargaining_number() {
		return Bargaining_number;
	}
	public void setBargaining_number(int bargaining_number) {
		Bargaining_number = bargaining_number;
	}
	public String getFlexiblePolicy() {
		return FlexiblePolicy;
	}
	public void setFlexiblePolicy(String flexiblePolicy) {
		FlexiblePolicy = flexiblePolicy;
	}
	public int getFlexiblePolicy_judge() {
		return FlexiblePolicy_judge;
	}
	public void setFlexiblePolicy_judge(int flexiblePolicy_judge) {
		FlexiblePolicy_judge = flexiblePolicy_judge;
	}
	public int getFlexiblePolicy_number() {
		return FlexiblePolicy_number;
	}
	public void setFlexiblePolicy_number(int flexiblePolicy_number) {
		FlexiblePolicy_number = flexiblePolicy_number;
	}
	public String getSellerEmotionalIcons() {
		return SellerEmotionalIcons;
	}
	public void setSellerEmotionalIcons(String sellerEmotionalIcons) {
		SellerEmotionalIcons = sellerEmotionalIcons;
	}
	public int getSellerEmotionalIcons_judge() {
		return SellerEmotionalIcons_judge;
	}
	public void setSellerEmotionalIcons_judge(int sellerEmotionalIcons_judge) {
		SellerEmotionalIcons_judge = sellerEmotionalIcons_judge;
	}
	public int getSellerEmotionalIcons_number() {
		return SellerEmotionalIcons_number;
	}
	public void setSellerEmotionalIcons_number(int sellerEmotionalIcons_number) {
		SellerEmotionalIcons_number = sellerEmotionalIcons_number;
	}
	public String getBuyerEmotionalIcons() {
		return BuyerEmotionalIcons;
	}
	public void setBuyerEmotionalIcons(String buyerEmotionalIcons) {
		BuyerEmotionalIcons = buyerEmotionalIcons;
	}
	public int getBuyerEmotionalIcons_judge() {
		return BuyerEmotionalIcons_judge;
	}
	public void setBuyerEmotionalIcons_judge(int buyerEmotionalIcons_judge) {
		BuyerEmotionalIcons_judge = buyerEmotionalIcons_judge;
	}
	public int getBuyerEmotionalIcons_number() {
		return BuyerEmotionalIcons_number;
	}
	public void setBuyerEmotionalIcons_number(int buyerEmotionalIcons_number) {
		BuyerEmotionalIcons_number = buyerEmotionalIcons_number;
	}
	//设置trade bean
	TradeBean tradebean[];
	public TradeBean[] getTradeBean(){
		  /*
		  for(int i=0;i<itembean.length;i++){
			  System.out.println("Item ID is     :"+itembean[i].getItemid());
			  System.out.println("Item name is   :"+itembean[i].getItemname());
		  }
		  */
		  return tradebean;
	  }
	  public void setTradeBean(TradeBean tb[],int ee){
		  tradebean = new TradeBean[ee];
		  for(int i=0;i<ee;i++){
			   tradebean[i] = new TradeBean();
			   tradebean[i] = tb[i];
	   }
	 }
	  ArrayList<Long> total_response_time_individual;
	  ArrayList<Integer> total_response_line_words_individual; 
	  ArrayList<Long> total_ask_time_individual;
	  ArrayList<Integer> combined_c_asks_line_words_individual;
	  
	  public ArrayList<Long> getTotal_response_time_individual(){
		  return total_response_time_individual;
	  }
	  public void setTotal_response_time_individual(ArrayList<Long> trti){
		  total_response_time_individual = new ArrayList<Long>();
		  for(Long temp:trti){
			  total_response_time_individual.add(temp);
		  }
	  }
	  
	  public ArrayList<Integer> getTotal_response_line_words_individual(){
		  return total_response_line_words_individual;
	  }
	  public void setTotal_response_line_words_individual(ArrayList<Integer> trlwi){
		  total_response_line_words_individual = new ArrayList<Integer>();
		  for(Integer temp:trlwi){
			  total_response_line_words_individual.add(temp);
		  }
	  }
	  
	  public ArrayList<Long> getTotal_ask_time_individual(){
		  return total_ask_time_individual;
	  }
	  public void setTotal_ask_time_individual(ArrayList<Long> tati){
		  total_ask_time_individual = new ArrayList<Long>();
		  for(Long temp:tati){
			  total_ask_time_individual.add(temp);
		  }
	  }
	  
	  public ArrayList<Integer> getCombined_c_asks_line_words_individual(){
		  return combined_c_asks_line_words_individual;
	  }
	  public void setCombined_c_asks_line_words_individual(ArrayList<Integer> ccalwi){
		  combined_c_asks_line_words_individual = new ArrayList<Integer>();
		  for(Integer temp:ccalwi){
			  combined_c_asks_line_words_individual.add(temp);
		  }
	  }
	  
	  //用于存储sum trade information
	  int howmanytrade=0;
	  String sumtradeid="";
	  String sumtradestatus="";
	  String sumdealtime="";
	  double sumamountmoney=0;
	  String sumposttype="";
	  int howmanyitem=0;
	  String sumitemid="";
	  String sumitemname="";
	  String sumitemprice="";
	  String sumitemquatity="";


	public int getHowmanytrade() {
		return howmanytrade;
	}
	public void setHowmanytrade(int howmanytrade) {
		this.howmanytrade = howmanytrade;
	}
	public String getSumtradeid() {
		return sumtradeid;
	}
	public void setSumtradeid(String sumtradeid) {
		this.sumtradeid = sumtradeid;
	}
	public String getSumtradestatus() {
		return sumtradestatus;
	}
	public void setSumtradestatus(String sumtradestatus) {
		this.sumtradestatus = sumtradestatus;
	}
	public String getSumdealtime() {
		return sumdealtime;
	}
	public void setSumdealtime(String sumdealtime) {
		this.sumdealtime = sumdealtime;
	}
	public double getSumamountmoney() {
		return sumamountmoney;
	}
	public void setSumamountmoney(double sumamountmoney) {
		this.sumamountmoney = sumamountmoney;
	}
	public String getSumposttype() {
		return sumposttype;
	}
	public void setSumposttype(String sumposttype) {
		this.sumposttype = sumposttype;
	}
	public int getHowmanyitem() {
		return howmanyitem;
	}
	public void setHowmanyitem(int howmanyitem) {
		this.howmanyitem = howmanyitem;
	}
	public String getSumitemid() {
		return sumitemid;
	}
	public void setSumitemid(String sumitemid) {
		this.sumitemid = sumitemid;
	}
	public String getSumitemname() {
		return sumitemname;
	}
	public void setSumitemname(String sumitemname) {
		this.sumitemname = sumitemname;
	}
	public String getSumitemprice() {
		return sumitemprice;
	}
	public void setSumitemprice(String sumitemprice) {
		this.sumitemprice = sumitemprice;
	}
	public String getSumitemquatity() {
		return sumitemquatity;
	}
	public void setSumitemquatity(String sumitemquatity) {
		this.sumitemquatity = sumitemquatity;
	}
	  

}
