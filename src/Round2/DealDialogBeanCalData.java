package Round2;
import Round2.WriteStreamAppend;

import java.text.SimpleDateFormat;
import java.util.*;

//���������������Щdata��ÿ��dialogbean���а�����data
public class DealDialogBeanCalData {
      public static CalDataBean calculate(DialogBean tempdialogbean){
    	  
    	  CalDataBean cdb = new CalDataBean();
    	  
    	  //����communication pattern
    	  long total_response_time   = 0;   //�ܻظ�ʱ�䣬��ν�ܻظ�ʱ����ָ��������ĳ�˶Ի�������һ������Դ������������κ�����֮���ʱ���
    	  int  total_response_number = 0;   //�ܻظ���Ŀ���������������ң��������һ���ж��ٶ�
    	  ArrayList<Long> total_response_time_individual = new ArrayList<Long>();//��ÿ�λش��ʱ�䣬��Ϊһ��������� 20130823
    	  ArrayList<Integer> total_response_line_words_individual = new ArrayList<Integer>(); //��������һظ�����һ�е�����
    	  int  total_response_line_words  = 0;   //�ظ�ʱ�����ҵ�һ�лظ����������ܺ�
    	  long first_response_time   = 0;   //��һ���ظ�ʱ��
    	  int  first_response_line_words  = 0;   //���ҵ�һ���ظ�������
    	  int  c_asks_number         = 0;   //��ҶԻ�����
    	  int  s_answers_number      = 0;   //���ҶԻ�����
    	  int  c_words_number        = 0;   //��ҶԻ��е�����
    	  int  s_words_number        = 0;   //���ҶԻ��е�����
    	  int  first_s               = -1;   //��һ��������Ϣ��¼
    	  int  first_c               = -1;   //��һ�������Ϣ��¼
    	  long duration_time         = 0;    //���ζԻ���������ʱ��
   	      int s_qin_number           = 0;    //����̸���С��ס��Ĵ���
	      int b_qin_number           = 0;    //���̸���С��ס��Ĵ���
	      int combined_c_asks_number = 0;    //��Ҷ��ٴμ��е���������
	      int combined_c_asks_line_words  = 0;    //����ٴμ���������ʱ�ĵ�һ����������
	      long total_asks_time = 0;            //����ж༱�е����ʣ����һظ�����Ҷ���������
	      ArrayList<Long> total_ask_time_individual = new ArrayList<Long>();//��ÿ�����ʵ�ʱ�䣬��Ϊһ��������� 20130823
	      ArrayList<Integer> combined_c_asks_line_words_individual = new  ArrayList<Integer>(); //����ʱ����һ�е�����
	      int c_asks__number_paragraph = 0; //һ���ж��ٶ�c asks
	      int s_answers_number_paragraph = 0; //һ���ж��ٶ� s answers
	      
	      //2013-08-13
	      int selleremotionalwords[]={0,0,0,0,0,0,0,0}; //��¼����emotional words ����ģ�ÿ����ֵĶ����; ǰ��������emotional words�����һ���� �ִʺ�һ���ж��ٸ���
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
				  //System.out.println("�ҵ�nickname��,����Ϊ ��"+i);
				  break;
			  }
		  }
		  for(int i=0;i<tempdb.length;i++){
			  System.out.println(tempdb[i].getTitle());
			  if(sellertitle.equals(tempdb[i].getTitle())){
				  first_s=i;
				  //System.out.println("�ҵ�sellertitle��,����Ϊ ��"+i);
				  break;
			  }
		  }
		  if(first_s==-1||first_c==-1){
			  System.out.println("����������Ȼû���ҵ�������ɫ");
		  }
		  if(first_s<first_c){//�����һ��������Ϣ��Ȼ�ȵ�һ�������Ϣ��
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
					  if(sellertitle.equals(tempdb[j].getTitle())){//�ҵ����Ҷ���ҵĵ�һ���ظ���
						  total_response_number++;
						  total_response_line_words_individual.add(tempdb[j].getContent().length());
						  total_response_line_words = total_response_line_words + tempdb[j].getContent().length();
						  total_response_time_individual.add(General.minustime_second(General.StringToDate(tempdb[j].getTime())
					                                                 , General.StringToDate(tempdb[i].getTime())));//��һ�λظ�ʱ�����
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
					  if(nickname.equals(tempdb[j].getTitle())){//�ҵ����Ҷ���ҵĵ�һ���ظ���
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
		  //��ҶԻ�����c_asks_number����ҶԻ��е�����c_words_number
		  for(int i=0;i<tempdb.length;i++){
			  if(nickname.equals(tempdb[i].getTitle())){
				  c_asks_number++;
				  c_words_number=c_words_number+General.delSignBlank(tempdb[i].getContent()).length();
				  b_qin_number = b_qin_number + General.occurTimes(tempdb[i].getContent(), "��");
				  
				  //���� emotional words���ֵ�Ƶ��
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
		  //���ҶԻ�����s_answers_number�����ҶԻ��е�����s_words_number
		  for(int i=0;i<tempdb.length;i++){
			  if(sellertitle.equals(tempdb[i].getTitle())){
				  s_answers_number++;
				  s_words_number=s_words_number+General.delSignBlank(tempdb[i].getContent()).length();
				  s_qin_number = s_qin_number+General.occurTimes(tempdb[i].getContent(),"��");
				  //���� emotional words���ֵ�Ƶ��
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
    	  //����coding data
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
    	  if("pre".equalsIgnoreCase(General.delSpaceChineseBiaodian(CDBJCoding.getCommunicationType())))//���Դ�Сд�Ա��ַ�
    	    {
    		   System.out.println("���һ��Pre communication session");
    		}
    	  
    	  
    	  
    	  //������ProductName��communicationcontent�������ֶ�
    	  //����TradeId�������ж�tradeID�Ƿ�Ϊ0��Ȼ������ݿ�����ȡ��һtradeID��Ӧ�Ľ��׼�¼
    	  String tradeid = General.delSpaceChineseBiaodian(CDBJCoding.getTradeID());//��ʽ������
    	  TradeBean tb[] = new TradeBean[20];//����tradebean�����ֻ�ܵ���10�ι���
		  int ee =0;//���ڼ�¼trade�������Ƕ���
    	  if("0".equals(tradeid)){
    		  CDBJCoding.setPurchaseornot(false);//��ʾ���������¼û����Ӧ�Ľ���
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
    		  CDBJCoding.setPurchaseornot(true);//��ʾ���������¼����Ӧ�Ĺ���
    		  
    		  String trades[] = tradeid.split(";");
    		  for(String testsingletradeid:trades){
    			  System.out.println("testsingletradeid is "+testsingletradeid);
    		  }
    		  String sql = null;
    		  try{
    			 MySQLConn.connect();
    			  for(String singletradeid:trades){//ͨ��forѭ������ÿ�����׼�¼���д���
    				  //System.out.println("SingleTradeID is "+singletradeid);//singletradeid��ʾÿ����¼��ID
    				  if(General.TradeIdJudge(singletradeid)){
    					      TradeBean temptradebean = new TradeBean();
    		    			  sql = "select distinct tradeid,tradestatus,dealtime,amountmoney,posttype from tradeinfo where tradeid='"+singletradeid+"'";
    		    			  System.out.println("SQL === LeleKang is "+sql);
    		    			  MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
    		    			  MySQLConn.rs.last();
    		    			  if(MySQLConn.rs.getRow()==0){//�����ݿ����Ҳ���
    		    				  System.out.println("�Ҳ�����һtradeid��Ӧ�Ľ��׼�¼ trade id is "+singletradeid);
    		    		  	 	  WriteStreamAppend.method1(errorlogfilepath,"�Ҳ�����һtradeid��Ӧ�Ľ��׼�, trade id is "+singletradeid+", datacollector is "+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    		    			  }else{//���ݿ����ҵ���tradeid�������trade information��ͨ��build tradebean������
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
    		    				  
    		    				  
    		    				  //�����ݿ��л��trade item������
    		    				  sql = "select itemid, itemname,itemprice,itemquatity from tradeitem where tradeid='"+singletradeid+"'";
    		    				  //System.out.println("��ѯitem information, SQL: "+sql);
    		    				  int itemnumber =0;
    		    				  MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
    		    				     ItemBean ib[]=new ItemBean[100];//ÿ������������100����Ʒ
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
    		    				    	 //�����ˣ����tradeid�����Ҳ����κ�item
    		    				    	 System.out.println("��һ���׼�¼���Ҳ����κε�item "+singletradeid);
    	    		    		  	 	 WriteStreamAppend.method1(errorlogfilepath,"��һ���׼�¼���Ҳ����κε�item, trade id is "+singletradeid+", datacollector is "+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    		    				     }
    		    				     temptradebean.setItemBean(ib, itemnumber);
    		    				     tb[ee]=temptradebean;
    		    				  ee++;
    		    			  }

    				  }else{
    			    		System.out.println("tradeID�������⣬�����Ϲ淶������0��Ҳ����Nλ����");
    			  	 	    WriteStreamAppend.method1(errorlogfilepath,"tradeID�������⣬�����Ϲ淶������0��Ҳ��������. Tradeid is : "+singletradeid+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    			    }  
    			  }//end for ѭ��
    	     MySQLConn.stmt.close();
    		 MySQLConn.con.close();
    		  }//end try
    		  catch(Exception e){
	    		   System.out.println("data connection is wrong DealDialogBeanCalData.jave "+e.toString());
	    		   WriteStreamAppend.method1(errorlogfilepath,"DealDialogBeanCalData.java exception is :"+e.toString()+"; SQL is "+sql+"\r\n");
	    	  }
    		  CDBJCoding.setTradeBean(tb, ee);//���tradeID������
    		  
    		  //����sum trade information
    		  CDBJCoding.setHowmanytrade(CDBJCoding.getTradeBean().length);
    		  
    		  
    		  
    	  }// end "0" else; end tradeid,��������Ҫ����Ӧ��trade information��
    	  
    	  //writeTradeInfo(CDBJCoding);//д�뵽�ļ�
    	  //�鿴CDBJ���е�TradeInformation
    	  
    	  String autor=General.delallbiaodian(CDBJCoding.getAutomaticReply());//�����Զ��ظ�
    	  if("yes".equalsIgnoreCase(autor)){
    		  CDBJCoding.setAutomaticReply_judge(1);
    	  }else if("no".equalsIgnoreCase(autor)){
    		  CDBJCoding.setAutomaticReply_judge(0);
    	  }else{
    		  System.out.println("AutomaticReply����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			  tempdialogbean.getContentb()[0].getTime()+
	  	 			  "_AutomaticReply����������"+"; CDBJProductName"+CDBJCoding.getProductName()+
	  	 			  "; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  
    	  String pavailablei=General.delSpaceChineseBiaodian(CDBJCoding.getProductAvailableInquire());//����Product Available Inquire
    	  String pavailablein[] = pavailablei.split(";");
    	  if("no".equalsIgnoreCase(pavailablein[0])){
    		  CDBJCoding.setProductAvailableInquire_judge(0);
    		  CDBJCoding.setProductAvailableInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(pavailablein[0])){
    		  CDBJCoding.setProductAvailableInquire_judge(1);
    		  CDBJCoding.setProductAvailableInquire_number(pavailablein.length-1);
    	  }else{
    		  System.out.println("ProductAvailableInquire����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductAvailableInquire����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  
    	  String pqualityi=General.delSpaceChineseBiaodian(CDBJCoding.getProductQualityInquire());//����Product Quality Inquire
    	  String pqualityin[] = pqualityi.split(";");
    	  if("no".equalsIgnoreCase(pqualityin[0])){
    		  CDBJCoding.setProductQualityInquire_judge(0);
    		  CDBJCoding.setProductQualityInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(pqualityin[0])){
    		  CDBJCoding.setProductQualityInquire_judge(1);
    		  CDBJCoding.setProductQualityInquire_number(pqualityin.length-1);
    	  }else{
    		  System.out.println("ProductQualityInquire����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductQualityInquire����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }

    	  String dinquire=General.delSpaceChineseBiaodian(CDBJCoding.getDeliveryInquire());//����Delievery Inquire
    	  String dinquiren[] = dinquire.split(";");
    	  if("no".equalsIgnoreCase(dinquiren[0])){
    		  CDBJCoding.setDeliveryInquire_judge(0);
    		  CDBJCoding.setDeliveryInquire_number(0);
    	  }else if("yes".equalsIgnoreCase(dinquiren[0])){
    		  CDBJCoding.setDeliveryInquire_judge(1);
    		  CDBJCoding.setDeliveryInquire_number(dinquiren.length-1);
    	  }else{
    		  System.out.println("DeliveryInquire����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_DeliveryInquire����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  
    	  String rinquiry=General.delSpaceChineseBiaodian(CDBJCoding.getReturnInquiry());//����Return Inquire
    	  String rinquiryn[] = rinquiry.split(";");
    	  if("no".equalsIgnoreCase(rinquiryn[0])){
    		  CDBJCoding.setReturnInquiry_judge(0);
    		  CDBJCoding.setReturnInquiry_number(0);
    	  }else if("yes".equalsIgnoreCase(rinquiryn[0])){
    		  CDBJCoding.setReturnInquiry_judge(1);
    		  CDBJCoding.setReturnInquiry_number(rinquiryn.length-1);
    	  }else{
    		  System.out.println("ReturnInquiry����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ReturnInquiry����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  
    	  String confirmps=General.delSpaceChineseBiaodian(CDBJCoding.getConfirmPromisedService());//����Confirm Promise Service
    	  String confirmpsn[] = confirmps.split(";");
    	  if("no".equalsIgnoreCase(confirmpsn[0])){
    		  CDBJCoding.setConfirmPromisedService_judge(0);
    		  CDBJCoding.setConfirmPromisedService_number(0);
    	  }else if("yes".equalsIgnoreCase(confirmpsn[0])){
    		  CDBJCoding.setConfirmPromisedService_judge(1);
    		  CDBJCoding.setConfirmPromisedService_number(confirmpsn.length-1);
    	  }else{
    		  System.out.println("ConfirmPromisedService����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ConfirmPromisedService����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }

    	  
    	  
    	  String productf=General.delSpaceChineseBiaodian(CDBJCoding.getProductFeature());//����Product Feature
    	  String productfn[] = productf.split(";");
    	  if("no".equalsIgnoreCase(productfn[0])){
    		  CDBJCoding.setProductFeature_judge(0);
    		  CDBJCoding.setProductFeature_number(0);
    	  }else if("yes".equalsIgnoreCase(productfn[0])){
    		  CDBJCoding.setProductFeature_judge(1);
    		  CDBJCoding.setProductFeature_number(productfn.length-1);
    	  }else{
    		  System.out.println("CProductFeature����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_ProductFeature����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  
    	  String pselectionb=General.delSpaceChineseBiaodian(CDBJCoding.getProductSelectionBehavior());//����Product Selection
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
    			  System.out.println("Product Selection ����������");
    	  	 	  WriteStreamAppend.method1(errorlogfilepath,
    	  	 			tempdialogbean.getContentb()[0].getTime()+"_Product Selection ����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    		  }
    	  }else{
    		  System.out.println("Product Selection ����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,"Product Selection ����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  String bargaining=General.delSpaceChineseBiaodian(CDBJCoding.getBargaining());//����bargaining
    	  String bargainingn[] = bargaining.split(";");
    	  if("no".equalsIgnoreCase(bargainingn[0])){
    		  CDBJCoding.setBargaining_judge(0);
    		  CDBJCoding.setBargaining_number(0);
    	  }else if("yes".equalsIgnoreCase(bargainingn[0])){
    		  CDBJCoding.setBargaining_judge(1);
    		  CDBJCoding.setBargaining_number(bargainingn.length-1);
    	  }else{
    		  System.out.println("CProductFeature����������");
	  	 	  WriteStreamAppend.method1(errorlogfilepath,
	  	 			tempdialogbean.getContentb()[0].getTime()+
	  	 			"_Bargaining ����������"+"; CDBJProductName"+CDBJCoding.getProductName()+"; datacollector is"+datacollector+"; realname is "+realname+"; \r\n");//������ΪtradeID�����Ϲ淶��
    	  }
    	  
    	  //FlexiblePolicy���ڱ������⣬��ʱ��������
    	  
    	  
    	  //���¿�ʼ����emotional icons������������飬���ң�����Ҫ�������ס��ı���
    	  String sellerei=General.delSpaceChineseBiaodian(CDBJCoding.getSellerEmotionalIcons());//����bargaining
    	  String sellerein[] = sellerei.split(";");
    	  if("no".equalsIgnoreCase(sellerein[0])){
    		  CDBJCoding.setSellerEmotionalIcons_judge(0);
    		  CDBJCoding.setSellerEmotionalIcons_number(0);
    	  }else{
    		  CDBJCoding.setSellerEmotionalIcons_judge(1);
    		  CDBJCoding.setSellerEmotionalIcons_number(sellerein.length);
    	  }
    	  
    	  //����buyer emotional icons
    	  String buyerei=General.delSpaceChineseBiaodian(CDBJCoding.getBuyerEmotionalIcons());//����bargaining
    	  String buyerein[] = buyerei.split(";");
    	  if("no".equalsIgnoreCase(buyerein[0])){
    		  CDBJCoding.setBuyerEmotionalIcons_judge(0);
    		  CDBJCoding.setBuyerEmotionalIcons_number(0);
    	  }else{
    		  CDBJCoding.setBuyerEmotionalIcons_judge(1);
    		  CDBJCoding.setBuyerEmotionalIcons_number(buyerein.length);  
    	  }
    	  
    	  //����give up reasons�����޾���Ĵ�����
    	  String giveupr=General.delSpaceChineseBiaodian(CDBJCoding.getGiveUpReason());//����bargaining
           
    	  
    	  return CDBJCoding;
      }
      //�ؽ�һ���µ�
      public static CalDataBean calculatesum(CalDataBean CDBJ,CalDataBean CDBW){
    	  CalDataBean cdbsum = new CalDataBean();
    	  //����communication pattern������
    	  cdbsum.setC_asks_number(CDBJ.getC_asks_number()+CDBW.getC_asks_number());
    	  cdbsum.setC_words_number(CDBJ.getC_words_number()+CDBW.getC_words_number());
    	  cdbsum.setFirst_response_time(CDBJ.getFirst_response_time());//ֻȡ��һ�λظ���ʱ��
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
    	  
    	  //����coding������
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
    	  

    	  //�ѽ������ݴ��ݹ�ȥ
    	  
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
      
      
      
      //���ļ���д��trade information
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

      //һ�������������Դ���ÿһ��session
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
      //һ�������������Դ������ļ���session���ܼƣ����Ǽ���session������һ��ͬһ��trade��
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
			   		CDB.getDuration_time()+biaoji+", û�й�����Ӧ��Ʒ. "
			   		+" Communication Product is "+tempdialogbean.getCommunicationProduct()+
			   		", Communication Content is "+tempdialogbean.getCommunicationContent()+", AutomaticReply is "+
			   		tempdialogbean.getAutomaticReply()+"."+
			   		"\r\n");
      }
      public static void writecalSumYes(CalDataBean CDB,String biaoji,DialogBean tempdialogbean){
    	  String datalogNofilepath  = "E:/Lele/Taobao/datalogYes.txt";
    	  //���Ӵ��룬�����Ӧ�Ĺ����¼
    	  
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
		
		
	   long total_response_time;     //�ܻظ�ʱ�䣬��ν�ܻظ�ʱ����ָ��������ĳ�˶Ի�������һ������Դ������������κ�����֮���ʱ���
	   int  total_response_number;	 //�ܻظ���Ŀ���������������ң��������һ���ж��ٶ�
	   long first_response_time;     //��һ���ظ�ʱ��
	   int  c_asks_number;           //��ҶԻ�����
	   int  s_answers_number;        //���ҶԻ�����
	   int  c_words_number;          //��ҶԻ��е�����
	   int  s_words_number;          //���ҶԻ��е�����
	   long duration_time;
	   
	   int s_qin_number;             //����̸���С��ס��Ĵ���
	   int b_qin_number;             //���̸���С��ס��Ĵ���
	   
	   int combined_c_asks_number;
	   long total_asks_time;
	   int c_asks__number_paragraph;
	   int s_answers_number_paragraph;
	   
	   int  total_response_line_words;   //�ظ�ʱ�����ҵ�һ�лظ����������ܺ�
	   int  first_response_line_words;   //���ҵ�һ���ظ�������
	   int combined_c_asks_line_words;    //����ٴμ���������ʱ�ĵ�һ����������
	   
	//Part 2: Coding data   
	   String CommunicationType;//Commmunication Type content
	   String ProductName;//Further code to exprience and search product
	   String ProductNameFromDatabase;//����ǹ����˵Ĳ�Ʒ�����Դ����ݿ�����ȡ��Ʒ���ƣ�NO��ʾû�й������ݿ���û��
	   String communicationcontent;
	   String TradeID;
	   boolean purchaseornot;//�����ж���һ�Ի��������˹�����û�У������false����û�й��������true���ǹ�����
	   String AutomaticReply;//���ڴ洢����ı�������
	   int AutomaticReply_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   String ProductAvailableInquire;//���ڴ洢����ı�������
	   int ProductAvailableInquire_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ProductAvailableInquire_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String ProductQualityInquire;//���ڴ洢����ı�������
	   int ProductQualityInquire_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ProductQualityInquire_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String DeliveryInquire;//���ڴ洢����ı�������
	   int DeliveryInquire_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int DeliveryInquire_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String ReturnInquiry;//���ڴ洢����ı�������
	   int ReturnInquiry_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ReturnInquiry_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String ConfirmPromisedService;//���ڴ洢����ı�������
	   int ConfirmPromisedService_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ConfirmPromisedService_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String ProductFeature;//���ڴ洢����ı�������
	   int ProductFeature_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ProductFeature_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String ProductSelectionBehavior;//���ڴ洢����ı�������
	   int ProductSelectionBehavior_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int ProductSelectionBehavior_number;//Yes�����������Ŀ��1��ʾ Substitute products;2��ʾ Complementary Products��3��ʾNot-related products
	   String Bargaining;//���ڴ洢����ı�������
	   int Bargaining_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int Bargaining_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   
	   //FlexiblePolicy���ڱ��벻�淶���ݲ�����
	   
	   String FlexiblePolicy;//���ڴ洢����ı�������
	   int FlexiblePolicy_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int FlexiblePolicy_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String SellerEmotionalIcons;//���ڴ洢����ı�������
	   int SellerEmotionalIcons_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int SellerEmotionalIcons_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String BuyerEmotionalIcons;//���ڴ洢����ı�������
	   int BuyerEmotionalIcons_judge;//���ڴ洢�����Ľ��0��ʾNo��1��ʾYes
	   int BuyerEmotionalIcons_number;//Yes�����������Ŀ������˵����1�Σ����Σ�3�Σ�
	   String GiveUpReason;//���ڴ洢����ı�������
	   
	   
	   //2013-08-13;
	   //���¼���Emotional Words
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
	//����trade bean
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
	  
	  //���ڴ洢sum trade information
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
