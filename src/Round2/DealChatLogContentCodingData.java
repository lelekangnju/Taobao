package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealChatLogContentCodingData {
	
	//Get every communication session
	public static boolean ContentDataGet(DialogContentBean contentbean[],String nickname,String sellertitle,int ee){
		//����ֵ���������Ի��Ƿ�����Ч�Ի�����Ч����Ч���������ڣ��Ի����Ƿ����Һ���ҵ���������
	   String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	   boolean judge = false;
	   //System.out.println("sellertitle is "+sellertitle);
	   //System.out.println("�Ի��м��У�"+ee);
	   if(sellertitle==null||ee==1||"".equals(sellertitle)){
		   //System.out.println("û����������");
		   return judge;//û������������ֱ��return false
	   }else{
		   //System.out.println("sellertitle is :"+sellertitle);
		   for(int i=0;i<ee;i++){
			   if(nickname.equals(contentbean[i].getTitle()))judge=true;
	   }
		   //WriteStreamAppend.method1(errorlogfilepath,"---------------------------------------------------\r\n");
		   return judge;
		
	   } 
   }
   public static void contentanalysis(DialogBean dialogben[],int usefulsession[],int usefulw,String datacollector,String realname){//�Ѿ���ȫ����ص���Ϣ��������ˣ�����ֻҪֱ�ӷ�����Щ���ݾ���
	   String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
	   String datalogNofilepath  = "E:/Lele/Taobao/datalogNo.txt";
	   String datalogYesfilepath  = "E:/Lele/Taobao/datalogYes.txt";
	   WriteStreamAppend.method1(datalogNofilepath,"�����ļ���"+"datacollector is "+datacollector+" and realname is "+realname+"\r\n ");
	   WriteStreamAppend.method1(datalogYesfilepath,"�����ļ���"+"datacollector is "+datacollector+" and realname is "+realname+"\r\n ");
	   //usefulsession���м�¼����Щ�Ի�����Ч�ġ�usefulw��¼��һ���ж��ٶ���Ч�Ի�
	   int length = dialogben.length;
	   int flag = 0;//���Ա�ʶsellertitle�ǲ����Ѿ�����
	   ArrayList<SellerAllSessionBean> everysession = new ArrayList<SellerAllSessionBean>();//���е�session�ķ���
	   //�ṹ�������ģ����е�session���ղ�ͬ��sellertitle������
	   
	   System.out.println("Dilog ben length is "+length);
	   for(int j=0;j<usefulw;j++){
		   System.out.println("��Ч�ĶԻ��ǣ�"+usefulsession[j]);
		   flag=0;
		   if(everysession.isEmpty()){//first bean
			   System.out.println("ISEmpty ִ��");
			   SellerAllSessionBean onebean = new SellerAllSessionBean();
			   onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
			   onebean.sellersession.add(dialogben[usefulsession[j]]);
			   everysession.add(onebean);
		   }else{
			   //��ѯ���е�verysession���е�����sellertitle�����Ƿ�����ͬ�ģ�����У���ֱ�Ӵ��뵽��һ��SellerAllSessionBean����ȥ
		        for(int i = 0;i < everysession.size(); i ++){
		        	//ʷ����޵д��룬���Լ������������ˡ���˼�����ǣ�����Ǹ�sellertitle�Ѿ�������list����
		        	if(dialogben[usefulsession[j]].getSellertitle().equals(everysession.get(i).getSellertitle())){
		        		everysession.get(i).sellersession.add(dialogben[usefulsession[j]]);
		        		flag=1;
		        	}		        		
		        }
		        if(flag==0){//֮ǰû������û�������
		        	SellerAllSessionBean onebean = new SellerAllSessionBean();
					onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
					onebean.sellersession.add(dialogben[usefulsession[j]]);
					everysession.add(onebean);
		        }
		   }
          /*
		   System.out.println("��Ч�ĶԻ��ǣ�"+usefulsession[j]);
		   System.out.println("logdate is "+dialogben[usefulsession[j]].getLogdate());
	       System.out.println("nickname is "+dialogben[usefulsession[j]].getNickname());
	       System.out.println("sellertitle is "+dialogben[usefulsession[j]].getSellertitle());
	       System.out.println("content is \n"+dialogben[usefulsession[j]].getDialogcontent());
	       dialogben[usefulsession[j]].getContentb();
	      */
	   }
	   ArrayList<SellerAllSessionBean> everysession_new = new ArrayList<SellerAllSessionBean>();//���е�session�ķ���
	   
	   
	   //��everysession��ÿһ��sellertitle�µ�commmunication dialog����ʱ������������У�ʱ������������ǰ��
	   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
		     SellerAllSessionBean sasb = new SellerAllSessionBean();
		     sasb.setSellertitle(everysession.get(i).getSellertitle());
		     int tempdialogn = everysession.get(i).sellersession.size();//��������ĶԻ���������
		     int tempposition = 0; //�������������Ǹ�DialogBean��λ��
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
	   everysession = everysession_new;//�������
	   
	   /*//Print the data
	   for(int i=0;i<everysession.size();i++){
		   for(int j=0 ; j<everysession.get(i).sellersession.size();j++){
			   DealDialogBeanCalData.writeAllTempBeanDialog(everysession.get(i).sellersession.get(j));
		   }
	   }
	   */
	   
	 //Finish����
	   
	   System.out.println("-----------------------------------------------��������ô�����ͬ��seller��"+everysession.size());
	   
	   //add the content in File code here<<>>
	   try{
		   MySQLConn.connect();
		   int transactionn=0;
		   
		   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
			   
			   String temptitle = everysession.get(i).getSellertitle();
			   String sql5 = "select * from tradeinfo,sellerinfo where sellerinfo.sellertitle='"+temptitle+"' and sellerinfo.id=tradeinfo.id and tradeinfo.datacollector='"+datacollector+"' and tradeinfo.realname='"+realname+"'";//�鿴�����Ƿ��Ѿ�����
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
			   transactionn = MySQLConn.rs.getRow();//���ж���������
			   
			   //System.out.println("���ж��������ݣ������� "+transactionn);
			   //������Щû��transaction record��sellerinfo
			   if(transactionn==0){
				   /*
				   WriteStreamAppend.method1(datalogNofilepath,"\r\n\r\n"+"Sellertitle is "+temptitle+" "+"\r\n\r\n\r\n");
				   */
				   dialogn=everysession.get(i).sellersession.size();//���seller�ĵ����ж��ٸ�dialogbean,���Һϲ���Щ��һ��֮�ڵ�dialog����������Ϊ������̽��һ���¶�
				   for(int j=0;j<dialogn;j++){//����ÿ��dialogbean
					   tempdialogbean=everysession.get(i).sellersession.get(j);
					   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
					   /*
					   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean);
					   */
					   DealDialogBeanCalData.writecalNo(CDBJ, ",һ�εĵ�1��");
					   for(int w=j+1;w<dialogn;){
						   tempdialogbean1=everysession.get(i).sellersession.get(w);
						   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
		                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//����ʱ�� - �׸��Ի�ʱ��
						   if(Math.abs(temptime)<=86400){//֤������Ի���ǰ���ǶζԻ��Ƕ���һ��sellerһ���ڵĶԻ����ҽ�dialog���кϲ�
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);
							   /*
							   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean1);
							   */
							   DealDialogBeanCalData.writecalNo(CDBW, ",ͬһ���е�");
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ,CDBW);
							   w++;
							   j=w;
						   }else{
							   j=w-1;
							   break;
						   }
					   }
					   DealDialogBeanCalData.writecalNo(CDBJ, ",�ܼ�, j is"+j);
				   }
				   //System.out.println("���seller�Ի�û���ܹ��γɹ��򣬼�¼Ϊ�޹����communication log ");
			   }
			   
			   else if(transactionn>=1){
				   CDBJ = null;
				   int j =0;
				   MySQLConn.rs.first();
				   id          = MySQLConn.rs.getString(1);
				   dealtime    = MySQLConn.rs.getString(4);
				   dialogn=everysession.get(i).sellersession.size();//���seller�ĵ����ж��ٸ�dialogbean
				   //
				   WriteStreamAppend.method1(datalogYesfilepath,"\r\n\r\n"+"Sellertitle is "+temptitle+" "+"\r\n\r\n\r\n");
				   //
				   //�����һ�������¼
				   for(j=0;j<dialogn;j++){//����ÿ��dialogbean
					   tempdialogbean=everysession.get(i).sellersession.get(j);
					   temptime = General.minustime_second(General.StringToDate(dealtime)
	                                                      ,General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//����ʱ�� - �׸��Ի�ʱ��
					   //System.out.println("Temptime is "+temptime);
					   if(temptime>0&&temptime<=86400){//һ��֮�ڵĶԻ������ֶԻ��ſ���Ӱ�콻�ף������Ĳ���
						   if(CDBJ==null){
							   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
							   DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",һ�εĵ�1��\r\n");
							   /*
							   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
							   */
						   }else{
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean);
							   DealDialogBeanCalData.writecalYes(CDBW, ",Transaction id is,"+id+",ͬһ����\r\n");
							   /*
							   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
							   */
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
						   }
					   }
				   }
				   if(CDBJ!=null)
					   {
					    DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",�ܼ�\r\n");//�������null����˵���ҵ��ĶԻ���¼ʵ����ȫ���ۺ�ĶԻ�
					   }
				   while(MySQLConn.rs.next()){
					   WriteStreamAppend.method1(datalogYesfilepath,"\r\n\r\n����һ�����׼�¼����������ÿ�����׼�¼��Ӧ��chatlog\r\n\r\n");
					   CDBJ        = null;
					   id          = MySQLConn.rs.getString(1);
					   dealtime    = MySQLConn.rs.getString(4);
					   for(j=0;j<dialogn;j++){//����ÿ��dialogbean
						   tempdialogbean=everysession.get(i).sellersession.get(j);
						   temptime = General.minustime_second(General.StringToDate(dealtime)
	                                                          ,General.StringToDate(tempdialogbean.getContentb()[0].getTime()));
						   if(temptime>0&&temptime<=86400){//һ��֮�ڵĶԻ������ֶԻ��ſ���Ӱ�콻�ף������Ĳ���
							   if(CDBJ==null){
								   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);
								   DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",һ�εĵ�1��\r\n");
								   /*
								   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
								   */
							   }else{
								   CDBW = DealDialogBeanCalData.calculate(tempdialogbean);
								   DealDialogBeanCalData.writecalYes(CDBW, ",Transaction id is,"+id+",ͬһ����\r\n");
								   /*
								   DealDialogBeanCalData.writeYesTempBeanDialog(tempdialogbean);
								   */
								   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
							   }
						   }
					   }
					if(CDBJ!=null)DealDialogBeanCalData.writecalYes(CDBJ, ",Transaction id is,"+id+",�ܼ�\r\n");
				   }
				   System.out.println("���seller�Ի��γ���һ�������¼ ");
			   }
			   
			   
			   System.out.println("�鵽����,����: "+transactionn+"�����׼�¼");
		     }//end for
		   }//end try
		
		   catch(Exception e){
	 	    System.out.println("data connection is wrong "+e.toString());
	 	    WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
		   }
	   
	   
	   
   }
}
