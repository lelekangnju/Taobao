package Round2;
import java.util.*; 

import Round2.WriteStreamAppend;
public class DealChatLogContent {
	
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
			   int dialogn=0;//��¼��һseller name�����ж��ٲ�ͬ��dialog
			   
			   long temptime =0 ;
			   dialogn=everysession.get(i).sellersession.size();
			   
			   for(int j=0;j<dialogn;j++){
				   System.out.println("Session number is "+j);
				   tempdialogbean=everysession.get(i).sellersession.get(j);
				   CDBJ = DealDialogBeanCalData.calculate(tempdialogbean);//�����communication pattern data�����ⲿ����Ϣ���뵽CDBJ�������
				   /*
				    * Modified the prior method.
				    * Calculate Each Person's Behavior.
				    */
				   CDBJ = DealDialogBeanCalData.calculateCoding(tempdialogbean,CDBJ,datacollector,realname);//����Coding data,�����˲��账��CDBJ��ӵ����ȫ��Ϣ
				   // Print all information to a file
				   System.out.println("���ѭ���������");
				   
				   for(int w=j+1;w<dialogn;w++){
					   tempdialogbean1=everysession.get(i).sellersession.get(w);
					   temptime = General.minustime_second(General.StringToDate(tempdialogbean1.getContentb()[0].getTime())
	                           , General.StringToDate(tempdialogbean.getContentb()[0].getTime()));//���˶Ի�ʱ��ļ��
					   if(Math.abs(temptime)<=86400){//֤������Ի���ǰ���ǶζԻ��Ƕ���һ��sellerһ���ڵĶԻ�
						   if(General.delSpaceChineseBiaodian(tempdialogbean.getTradeId()).equalsIgnoreCase(
							   General.delSpaceChineseBiaodian(tempdialogbean1.getTradeId()))){//����������ͬһ��seller���Ի�������һ���ڣ���ͬtradeid
							   
							   CDBW = DealDialogBeanCalData.calculate(tempdialogbean1);//�����communication pattern data
							   CDBW = DealDialogBeanCalData.calculateCoding(tempdialogbean1,CDBW,datacollector,realname);//����Coding data,�����˲��账��CDBW��ӵ����ȫ��Ϣ
							   
							   //2013-1-9 ��������ڲ�ѭ����ֻ������ս��
							   //General.printCalDataBean(tempdialogbean1,CDBW,"�ڲ�ѭ�����ҵ�ͬһ��trade��Ӧ��communication chat");
							   
							   CDBJ = DealDialogBeanCalData.calculatesum(CDBJ, CDBW);
							   System.out.println("�ڲ�ѭ�����ҵ�ͬһ��trade��Ӧ��communication chat");
							   // 2012-1-5 CDBJ = DealDialogBeanCalData.calculateCodingSum(CDBJ,CDBW)//�ϲ�����
							   
							   
							   w++;
							   j=w;
						   }else{
							   j=w-1;
							   break;
						   }
					   }
				   }
				   System.out.println("���ѭ�����ҵ�ͬһ��trade��Ӧ��communication chat");
				   System.out.println("���ѭ����û�����");
				   
				   
				  General.printCalDataBeanTwoFiles(tempdialogbean,CDBJ,"���ѭ��",datacollector,realname);
				   
				   
				   System.out.println("���ѭ����������");
				   
				   //�������ݼ�¼�ĺϲ����˴�
				   
				   // Print Trade Info DealDialogBeanCalData.writeTradeInfo(CDBJ);
				   //¼��coding data
				  
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
