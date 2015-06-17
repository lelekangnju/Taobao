package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealChatLogContent {
	
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
	   
	   try{
		   MySQLConn.connect();
		   int transactionn=0;
		   
		   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
			   System.out.println("Seller Session, Seller number is "+i);			      
			   String temptitle = everysession.get(i).getSellertitle();//Get the seller name
			   DialogBean tempdialogbean = new DialogBean();
			   DialogBean tempdialogbean1 = new DialogBean();
			 //Used to print., Get CDBJ's Random String.
			   CalDataBean CDBJ = new CalDataBean();
			   CalDataBean CDBW = new CalDataBean();
			   int dialogn=0;//记录这一seller name下面有多少不同的dialog
			   
			   long temptime =0 ;
			   dialogn=everysession.get(i).sellersession.size();
			   
			   for(int j=0;j<dialogn;j++){
				   System.out.println("Session number is "+j);
				   tempdialogbean=everysession.get(i).sellersession.get(j);
				   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);//计算出communication pattern data，将这部分信息加入到CDBJ这个当中
				   /*
				    * Modified the prior method.
				    * Calculate Each Person's Behavior.
				    */
				   CDBJ = DealDialogBeanCalData.calculateCoding(tempdialogbean,CDBJ,datacollector,realname);//计算Coding data,经过此步骤处理，CDBJ中拥有完全信息
				   // Print all information to a file
				   System.out.println("外层循环计算完毕");
				   
				   for(int w=j+1;w<dialogn;w++){
					   tempdialogbean1=everysession.get(i).sellersession.get(w);
					   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
	                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//两端对话时间的间隔
					   if(Math.abs(temptime)<=86400){//证明这个对话和前面那段对话是对于一个seller一天内的对话
						   if(General.delSpaceChineseBiaodian(tempdialogbean.getTradeId()).equalsIgnoreCase(
							   General.delSpaceChineseBiaodian(tempdialogbean1.getTradeId()))){//三个条件，同一个seller，对话发生在一天内，相同tradeid
							   
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);//计算出communication pattern data
							   CDBW = DealDialogBeanCalData.calculateCoding(tempdialogbean1,CDBW,datacollector,realname);//计算Coding data,经过此步骤处理，CDBW中拥有完全信息
							   
							   //2013-1-9 不再输出内层循环，只输出最终结果
							   //General.printCalDataBean(tempdialogbean1,CDBW,"内层循环，找到同一个trade对应的communication chat");
							   
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
							   System.out.println("内层循环，找到同一个trade对应的communication chat");
							   // 2012-1-5 CDBJ = DealDialogBeanCalData.calculateCodingSum(CDBJ,CDBW)//合并计算
							   
							   
							   w++;
							   j=w;
						   }else{
							   j=w-1;
							   break;
						   }
					   }
				   }
				   System.out.println("外层循环，找到同一个trade对应的communication chat");
				   System.out.println("外层循环，没有输出");
				   
				   
				  General.printCalDataBeanTwoFiles(tempdialogbean,CDBJ,"外层循环",datacollector,realname);
				   
				   
				   System.out.println("外层循环，输出完毕");
				   
				   //多条数据记录的合并，此处
				   
				   // Print Trade Info DealDialogBeanCalData.writeTradeInfo(CDBJ);
				   //录入coding data
				  
			   }
			   
			   //<<Here is the content of file code.txt>>
		     }//end for
		   }//end try
		
		   catch(Exception e){
	 	    System.out.println("data connection is wrong "+e.toString());
	 	    WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
		   }
	   
	   
	   
   }
}
