package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealPreCommunicationContent {
	
	//Get every communication session
	public static boolean ContentDataGet(DialogContentBean contentbean[],String nickname,String sellertitle,int ee){
		//返回值代表这条对话是否是有效对话，有效和无效的区别在于，对话中是否卖家和买家的姓名都有
	   String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	   boolean judge = false;
	   //System.out.println("sellertitle is "+sellertitle);
	   //System.out.println("对话有几行："+ee);
	   if(sellertitle==null||ee==1||"".equals(sellertitle)){
		   //System.out.println("没有卖家姓名");
		   return judge;//没有卖家姓名，直接return false
	   }else{
		   //System.out.println("sellertitle is :"+sellertitle);
		   for(int i=0;i<ee;i++){
			   if(nickname.equals(contentbean[i].getTitle()))judge=true;
	   }
		   //WriteStreamAppend.method1(errorlogfilepath,"---------------------------------------------------\r\n");
		   return judge;
		
	   } 
   }
	 //DealPreCommunicationContent.contentanalysis(dialogben, presession, preamount, datacollector, realname);
   public static void contentanalysis(DialogBean dialogben[],int usefulsession[],int usefulw,String datacollector,String realname){//已经完全将相关的信息处理出来了，现在只要直接分析这些数据就行
	   //在现在代码当中，因为使用人工进行编码判断是post 还是pre，因此，并不需要去查，是post还是pre。本方法，只处理pre的communication
	   
	   String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	   String datalogNofilepath  = "E:/Lele/Taobao/datalogNo.txt";
	   String datalogYesfilepath  = "E:/Lele/Taobao/datalogYes.txt";
	   WriteStreamAppend.method1(datalogNofilepath,"处理文件："+"datacollector is "+datacollector+" and realname is "+realname+"\r\n ");
	   WriteStreamAppend.method1(datalogYesfilepath,"处理文件："+"datacollector is "+datacollector+" and realname is "+realname+"\r\n ");
	   //usefulsession当中记录着哪些对话是有效的。usefulw记录着一共有多少段有效对话
	   int length = dialogben.length;
	   int flag = 0;//用以标识sellertitle是不是已经存在
	   ArrayList<SellerAllSessionBean> everysession = new ArrayList<SellerAllSessionBean>();//所有的session的分类
	   //结构是这样的，所有的session按照不同的sellertitle被分类
	   
	   System.out.println("Dilog ben length is "+length);
	   
	   for(int j=0;j<usefulw;j++){
		   System.out.println("有效的对话是："+usefulsession[j]);//表示第几段session是pre的session
		   flag=0;
		   if(everysession.isEmpty()){//first bean
			   System.out.println("ISEmpty 执行");
			   SellerAllSessionBean onebean = new SellerAllSessionBean();
			   onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
			   onebean.sellersession.add(dialogben[usefulsession[j]]);
			   everysession.add(onebean);
		   }else{
			   //查询现有的everysession当中的所有sellertitle当中是否有相同的，如果有，就直接存入到那一个SellerAllSessionBean当中去
		        for(int i = 0;i < everysession.size(); i ++){
		        	//史上最长无敌代码，我自己都看不明白了。意思反正是，如果那个sellertitle已经存在于list中了
		        	if(dialogben[usefulsession[j]].getSellertitle().equals(everysession.get(i).getSellertitle())){
		        		everysession.get(i).sellersession.add(dialogben[usefulsession[j]]);
		        		flag=1;
		        	}		        		
		        }
		        if(flag==0){//之前没有这个用户的名称
		        	SellerAllSessionBean onebean = new SellerAllSessionBean();
					onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
					onebean.sellersession.add(dialogben[usefulsession[j]]);
					everysession.add(onebean);
		        }
		   }
          /*
		   System.out.println("有效的对话是："+usefulsession[j]);
		   System.out.println("logdate is "+dialogben[usefulsession[j]].getLogdate());
	       System.out.println("nickname is "+dialogben[usefulsession[j]].getNickname());
	       System.out.println("sellertitle is "+dialogben[usefulsession[j]].getSellertitle());
	       System.out.println("content is \n"+dialogben[usefulsession[j]].getDialogcontent());
	       dialogben[usefulsession[j]].getContentb();
	      */
	   }
	   //至此，完成所有seller的session分类
	   ArrayList<SellerAllSessionBean> everysession_new = new ArrayList<SellerAllSessionBean>();//所有的session的分类
	   
	   
	   //对everysession中每一个sellertitle下的commmunication dialog按照时间进行逆序排列，时间最大的排在最前面
	   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
		     SellerAllSessionBean sasb = new SellerAllSessionBean();
		     sasb.setSellertitle(everysession.get(i).getSellertitle());
		     int tempdialogn = everysession.get(i).sellersession.size();//对它里面的对话进行排序
		     int tempposition = 0; //设置日期最大的那个DialogBean的位置
		     for(int w=0;w<tempdialogn;w++){
		         tempposition =0;
			     for(int j=1;j<everysession.get(i).sellersession.size();j++){
			    		 if(General.minustime_second(General.StringToDate(everysession.get(i).sellersession.get(tempposition).getContentb()[0].getTime())
	                                            , General.StringToDate(everysession.get(i).sellersession.get(j).getContentb()[0].getTime()))<0){
			    			 					tempposition=j;
			    		}
			     }
		     sasb.sellersession.add(everysession.get(i).sellersession.get(tempposition));
		     everysession.get(i).sellersession.remove(tempposition);
		     }
		     everysession_new.add(sasb);
	   }
	   everysession = everysession_new;//倒序完成
	   
	   /* Print all sessions
	   for(int i=0;i<everysession.size();i++){
		   for(int j=0 ; j<everysession.get(i).sellersession.size();j++){
			   DealDialogBeanCalData.writeAllTempBeanDialog(everysession.get(i).sellersession.get(j));
		   }
	   }
	   */
	   
	   //Finish排序
	   
	   System.out.println("-----------------------------------------------共有有这么多个不同的seller："+everysession.size());
	   try{
	   MySQLConn.connect();
	   int transactionn=0;
	   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
		   String temptitle = everysession.get(i).getSellertitle();   
		   /*
		   String sql5 = "select * from tradeinfo,sellerinfo where sellerinfo.sellertitle='"+temptitle+"' and sellerinfo.id=tradeinfo.id and tradeinfo.datacollector='"+datacollector+"' and tradeinfo.realname='"+realname+"'";//查看此人是否已经存在
		   System.out.println("sql is :"+sql5);
		   String id,tradeid,tradestatus,dealtime,amountmoney,posttype;
		   */
		   int dialogn=0;
		   long temptime =0;
		   long temptime1 =0;
		   DialogBean tempdialogbean = new DialogBean();
		   DialogBean tempdialogbean1= new DialogBean();
		   CalDataBean CDBJ = new CalDataBean();
		   CalDataBean CDBW = new CalDataBean();
		   String WriteFile = "";
		   int writeflag = -1;//0 means don't lead to purchase; 1 means lead to purchase
		 
		   String sql = "";
		   
		   dialogn=everysession.get(i).sellersession.size();//这个seller的底下有多少个dialogbean,并且合并那些在一天之内的dialog，将他们认为是正在探讨一件事儿
		  // WriteStreamAppend.method1(datalogNofilepath,"Seller-"+temptitle+"-共有"+dialogn+"-段交易记录"+"\r\n\r\n");
		   for(int j=0;j<dialogn;j++){//处理每个dialogbean
			   //WriteStreamAppend.method1(datalogNofilepath,"这是---"+temptitle+"---这一新seller的第:"+j+"段交易记录"+"\r\n");
			   tempdialogbean=everysession.get(i).sellersession.get(j);
			   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
		
			   /*
			   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean);
			   */
			   if("0".equals(tempdialogbean.getTradeid())){
				   WriteFile = datalogNofilepath;
				   writeflag=0;
			   }else{
				   WriteFile = datalogYesfilepath;
				   writeflag=1;
			   }
			   
			   if(writeflag==0){
				   //DealDialogBeanCalData.writecalNo(CDBJ, ",一段的第0条,"+"SellerTitle is "+tempdialogbean.getSellertitle()+"trade id is "+tempdialogbean.getTradeid()+"\r\n");
			   }else{
				   //DealDialogBeanCalData.writecalYes(CDBJ, ",一段的第0条,"+"SellerTitle is "+tempdialogbean.getSellertitle()+"trade id is "+tempdialogbean.getTradeid()+"\r\n");
			   }
			   
			   for(int w=j+1;w<dialogn;){
				   //WriteStreamAppend.method1(WriteFile,"执行查询匹配循环，证明有多余一条交易记录\r\n");
				   tempdialogbean1=everysession.get(i).sellersession.get(w);
				   //WriteStreamAppend.method1(WriteFile,"两次的交易ID分别为: "+tempdialogbean.getTradeid()+" , "+tempdialogbean1.getTradeid() );
				   if(tempdialogbean.getTradeid().equals(tempdialogbean1.getTradeid())){
					   //WriteStreamAppend.method1(WriteFile,"交易ID相等了" );
					   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
	                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//交易时间 - 首个对话时间
					   if(Math.abs(temptime)<=86400){//证明这个对话和前面那段对话是对于一个seller一天内的对话，我将dialog进行合并
						   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);
						   /*
						   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean1);
						   */
						   if(writeflag==0){
							   //DealDialogBeanCalData.writecalNo(CDBW, ",同一段中的,"+"SellerTitle is "+tempdialogbean1.getSellertitle()+"trade id is "+tempdialogbean1.getTradeid()+"\r\n");
						   }else{
							   //DealDialogBeanCalData.writecalYes(CDBW, ",同一段中的,"+"SellerTitle is "+tempdialogbean1.getSellertitle()+"trade id is "+tempdialogbean1.getTradeid()+"\r\n");
						   }
						   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ,CDBW);
						   w++;
						   j=w;
					   }else{
						   j=w-1;
						   break;
					   }  
				   }else{
					   w++;
				   }
			   }//End W
			   
			   if(writeflag==0){
				   //加入代码，处理购买记录，将TradeId传入函数中;使用新的函数writecalSumNo
				   
				   DealDialogBeanCalData.writecalSumNo(CDBJ,",总计, j is "+j+". ",tempdialogbean);
			   }else{
				   DealDialogBeanCalData.writecalSumYes(CDBJ,",总计, j is "+j+". ",tempdialogbean);
			   }
		   }//End J
		   
	     }//end I
	   }
	   catch(Exception e){
 	    System.out.println("data connection is wrong "+e.toString());
 	    WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	   }
	   
	   
	   
	   
   }
}
