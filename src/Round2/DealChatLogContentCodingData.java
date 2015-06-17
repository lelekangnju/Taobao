package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealChatLogContentCodingData {
	
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
   public static void contentanalysis(DialogBean dialogben[],int usefulsession[],int usefulw,String datacollector,String realname){//已经完全将相关的信息处理出来了，现在只要直接分析这些数据就行
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
		   System.out.println("有效的对话是："+usefulsession[j]);
		   flag=0;
		   if(everysession.isEmpty()){//first bean
			   System.out.println("ISEmpty 执行");
			   SellerAllSessionBean onebean = new SellerAllSessionBean();
			   onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
			   onebean.sellersession.add(dialogben[usefulsession[j]]);
			   everysession.add(onebean);
		   }else{
			   //查询现有的verysession当中的所有sellertitle当中是否有相同的，如果有，就直接存入到那一个SellerAllSessionBean当中去
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
	   
	   /*//Print the data
	   for(int i=0;i<everysession.size();i++){
		   for(int j=0 ; j<everysession.get(i).sellersession.size();j++){
			   DealDialogBeanCalData.writeAllTempBeanDialog(everysession.get(i).sellersession.get(j));
		   }
	   }
	   */
	   
	 //Finish排序
	   
	   System.out.println("-----------------------------------------------共有有这么多个不同的seller："+everysession.size());
	   
	   //add the content in File code here<<>>
	   try{
		   MySQLConn.connect();
		   int transactionn=0;
		   
		   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
			   
			   String temptitle = everysession.get(i).getSellertitle();
			   String sql5 = "select * from tradeinfo,sellerinfo where sellerinfo.sellertitle='"+temptitle+"' and sellerinfo.id=tradeinfo.id and tradeinfo.datacollector='"+datacollector+"' and tradeinfo.realname='"+realname+"'";//查看此人是否已经存在
			   System.out.println("sql is :"+sql5);
			   String id,tradeid,tradestatus,dealtime,amountmoney,posttype;
			   int dialogn=0;
			   long temptime =0;
			   long temptime1 =0;
			   DialogBean tempdialogbean = new DialogBean();
			   DialogBean tempdialogbean1= new DialogBean();
			   CalDataBean CDBJ = new CalDataBean();
			   CalDataBean CDBW = new CalDataBean();
			   MySQLConn.rs=MySQLConn.stmt.executeQuery(sql5);
			   MySQLConn.rs.last();
			   transactionn = MySQLConn.rs.getRow();//共有多少条数据
			   
			   //System.out.println("共有多少行数据，行数是 "+transactionn);
			   //处理那些没有transaction record的sellerinfo
			   if(transactionn==0){
				   /*
				   WriteStreamAppend.method1(datalogNofilepath,"\r\n\r\n"+"Sellertitle is "+temptitle+" "+"\r\n\r\n\r\n");
				   */
				   dialogn=everysession.get(i).sellersession.size();//这个seller的底下有多少个dialogbean,并且合并那些在一天之内的dialog，将他们认为是正在探讨一件事儿
				   for(int j=0;j<dialogn;j++){//处理每个dialogbean
					   tempdialogbean=everysession.get(i).sellersession.get(j);
					   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
					   /*
					   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean);
					   */
					   DealDialogBeanCalData.writecalNo(CDBJ, ",一段的第1条");
					   for(int w=j+1;w<dialogn;){
						   tempdialogbean1=everysession.get(i).sellersession.get(w);
						   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
		                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//交易时间 - 首个对话时间
						   if(Math.abs(temptime)<=86400){//证明这个对话和前面那段对话是对于一个seller一天内的对话，我将dialog进行合并
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);
							   /*
							   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean1);
							   */
							   DealDialogBeanCalData.writecalNo(CDBW, ",同一段中的");
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ,CDBW);
							   w++;
							   j=w;
						   }else{
							   j=w-1;
							   break;
						   }
					   }
					   DealDialogBeanCalData.writecalNo(CDBJ, ",总计, j is"+j);
				   }
				   //System.out.println("这个seller对话没有能够形成购买，记录为无购买的communication log ");
			   }
			   
			   else if(transactionn>=1){
				   CDBJ = null;
				   int j =0;
				   MySQLConn.rs.first();
				   id          = MySQLConn.rs.getString(1);
				   dealtime    = MySQLConn.rs.getString(4);
				   dialogn=everysession.get(i).sellersession.size();//这个seller的底下有多少个dialogbean
				   //
				   WriteStreamAppend.method1(datalogYesfilepath,"\r\n\r\n"+"Sellertitle is "+temptitle+" "+"\r\n\r\n\r\n");
				   //
				   //处理第一条记忆记录
				   for(j=0;j<dialogn;j++){//处理每个dialogbean
					   tempdialogbean=everysession.get(i).sellersession.get(j);
					   temptime = General.minustime_second(General.StringToDate(dealtime)
	                                                      ,General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//交易时间 - 首个对话时间
					   //System.out.println("Temptime is "+temptime);
					   if(temptime>0&&temptime<=86400){//一天之内的对话，这种对话才可能影响交易，其他的不管
						   if(CDBJ==null){
							   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
							   DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",一段的第1条\r\n");
							   /*
							   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
							   */
						   }else{
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean);
							   DealDialogBeanCalData.writecalYes(CDBW, ",Transaction id is,"+id+",同一段中\r\n");
							   /*
							   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
							   */
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
						   }
					   }
				   }
				   if(CDBJ!=null)
					   {
					    DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",总计\r\n");//如果等于null，这说明找到的对话记录实际上全是售后的对话
					   }
				   while(MySQLConn.rs.next()){
					   WriteStreamAppend.method1(datalogYesfilepath,"\r\n\r\n超过一条交易记录，分析随后的每条交易记录对应的chatlog\r\n\r\n");
					   CDBJ        = null;
					   id          = MySQLConn.rs.getString(1);
					   dealtime    = MySQLConn.rs.getString(4);
					   for(j=0;j<dialogn;j++){//处理每个dialogbean
						   tempdialogbean=everysession.get(i).sellersession.get(j);
						   temptime = General.minustime_second(General.StringToDate(dealtime)
	                                                          ,General.StringToDate(tempdialogbean.getContentb()[0].getTime()));
						   if(temptime>0&&temptime<=86400){//一天之内的对话，这种对话才可能影响交易，其他的不管
							   if(CDBJ==null){
								   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
								   DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",一段的第1条\r\n");
								   /*
								   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
								   */
							   }else{
								   CDBW = DealDialogBeanCalData.calculate(tempdialogbean);
								   DealDialogBeanCalData.writecalYes(CDBW, ",Transaction id is,"+id+",同一段中\r\n");
								   /*
								   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
								   */
								   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
							   }
						   }
					   }
					if(CDBJ!=null)DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",总计\r\n");
				   }
				   System.out.println("这个seller对话形成了一条购买记录 ");
			   }
			   
			   
			   System.out.println("查到此人,共有: "+transactionn+"个交易记录");
		     }//end for
		   }//end try
		
		   catch(Exception e){
	 	    System.out.println("data connection is wrong "+e.toString());
	 	    WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
		   }
	   
	   
	   
   }
}
