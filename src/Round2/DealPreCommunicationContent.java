package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealPreCommunicationContent {
	
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
	 //DealPreCommunicationContent.contentanalysis(dialogben, presession, preamount, datacollector, realname);
   public static void contentanalysis(DialogBean dialogben[],int usefulsession[],int usefulw,String datacollector,String realname){//�Ѿ���ȫ����ص���Ϣ��������ˣ�����ֻҪֱ�ӷ�����Щ���ݾ���
	   //�����ڴ��뵱�У���Ϊʹ���˹����б����ж���post ����pre����ˣ�������Ҫȥ�飬��post����pre����������ֻ����pre��communication
	   
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
		   System.out.println("��Ч�ĶԻ��ǣ�"+usefulsession[j]);//��ʾ�ڼ���session��pre��session
		   flag=0;
		   if(everysession.isEmpty()){//first bean
			   System.out.println("ISEmpty ִ��");
			   SellerAllSessionBean onebean = new SellerAllSessionBean();
			   onebean.setSellertitle(dialogben[usefulsession[j]].getSellertitle());
			   onebean.sellersession.add(dialogben[usefulsession[j]]);
			   everysession.add(onebean);
		   }else{
			   //��ѯ���е�everysession���е�����sellertitle�����Ƿ�����ͬ�ģ�����У���ֱ�Ӵ��뵽��һ��SellerAllSessionBean����ȥ
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
	   //���ˣ��������seller��session����
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
	   
	   /* Print all sessions
	   for(int i=0;i<everysession.size();i++){
		   for(int j=0 ; j<everysession.get(i).sellersession.size();j++){
			   DealDialogBeanCalData.writeAllTempBeanDialog(everysession.get(i).sellersession.get(j));
		   }
	   }
	   */
	   
	   //Finish����
	   
	   System.out.println("-----------------------------------------------��������ô�����ͬ��seller��"+everysession.size());
	   try{
	   MySQLConn.connect();
	   int transactionn=0;
	   for(int i = 0;i < everysession.size(); i++){//every seller communication log;
		   String temptitle = everysession.get(i).getSellertitle();   
		   /*
		   String sql5 = "select * from tradeinfo,sellerinfo where sellerinfo.sellertitle='"+temptitle+"' and sellerinfo.id=tradeinfo.id and tradeinfo.datacollector='"+datacollector+"' and tradeinfo.realname='"+realname+"'";//�鿴�����Ƿ��Ѿ�����
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
		   
		   dialogn=everysession.get(i).sellersession.size();//���seller�ĵ����ж��ٸ�dialogbean,���Һϲ���Щ��һ��֮�ڵ�dialog����������Ϊ������̽��һ���¶�
		  // WriteStreamAppend.method1(datalogNofilepath,"Seller-"+temptitle+"-����"+dialogn+"-�ν��׼�¼"+"\r\n\r\n");
		   for(int j=0;j<dialogn;j++){//����ÿ��dialogbean
			   //WriteStreamAppend.method1(datalogNofilepath,"����---"+temptitle+"---��һ��seller�ĵ�:"+j+"�ν��׼�¼"+"\r\n");
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
				   //DealDialogBeanCalData.writecalNo(CDBJ, ",һ�εĵ�0��,"+"SellerTitle is "+tempdialogbean.getSellertitle()+"trade id is "+tempdialogbean.getTradeid()+"\r\n");
			   }else{
				   //DealDialogBeanCalData.writecalYes(CDBJ, ",һ�εĵ�0��,"+"SellerTitle is "+tempdialogbean.getSellertitle()+"trade id is "+tempdialogbean.getTradeid()+"\r\n");
			   }
			   
			   for(int w=j+1;w<dialogn;){
				   //WriteStreamAppend.method1(WriteFile,"ִ�в�ѯƥ��ѭ����֤���ж���һ�����׼�¼\r\n");
				   tempdialogbean1=everysession.get(i).sellersession.get(w);
				   //WriteStreamAppend.method1(WriteFile,"���εĽ���ID�ֱ�Ϊ: "+tempdialogbean.getTradeid()+" , "+tempdialogbean1.getTradeid() );
				   if(tempdialogbean.getTradeid().equals(tempdialogbean1.getTradeid())){
					   //WriteStreamAppend.method1(WriteFile,"����ID�����" );
					   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
	                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//����ʱ�� - �׸��Ի�ʱ��
					   if(Math.abs(temptime)<=86400){//֤������Ի���ǰ���ǶζԻ��Ƕ���һ��sellerһ���ڵĶԻ����ҽ�dialog���кϲ�
						   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);
						   /*
						   DealDialogBeanCalData.writeNoTempBeanDialog(tempdialogbean1);
						   */
						   if(writeflag==0){
							   //DealDialogBeanCalData.writecalNo(CDBW, ",ͬһ���е�,"+"SellerTitle is "+tempdialogbean1.getSellertitle()+"trade id is "+tempdialogbean1.getTradeid()+"\r\n");
						   }else{
							   //DealDialogBeanCalData.writecalYes(CDBW, ",ͬһ���е�,"+"SellerTitle is "+tempdialogbean1.getSellertitle()+"trade id is "+tempdialogbean1.getTradeid()+"\r\n");
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
				   //������룬�������¼����TradeId���뺯����;ʹ���µĺ���writecalSumNo
				   
				   DealDialogBeanCalData.writecalSumNo(CDBJ,",�ܼ�, j is "+j+". ",tempdialogbean);
			   }else{
				   DealDialogBeanCalData.writecalSumYes(CDBJ,",�ܼ�, j is "+j+". ",tempdialogbean);
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
