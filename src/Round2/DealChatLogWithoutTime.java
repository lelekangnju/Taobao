package Round2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Round2.WriteStreamAppend;


public class DealChatLogWithoutTime {
  
	
	public static void DealChatLogWithoutTimeD(String filepath,String nickname,int w,int[] newlogline,String datacollector,String realname){
		//nickname�����������wһ���ж��ٶ�log, newloglineÿ����logline�ĵ�ַ
		//W��һ���ж��ٶμ�¼��newlogline��¼��ÿ�ο�ʼ���к�
		String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
		try{
			MySQLConn.connect();
			System.out.println("Here we are, Deal Chat Log Without Time");
	    }
	    catch(Exception e){
  	        System.out.println("data connection is wrong "+e.toString());
  	        WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	    }
	    
	    
	    int usefulw = w;//������¼�ж��ٶ�log��������Ч�ģ���Ч��ָ������Щ��ֻ�����һ���ֻ����ҵĶԻ���¼
	    int usefulsession[] = new int[500];
	    int presession[] = new int[500];
	    int postsession[] = new int[500];
	    int usefulamount=0;
	    int preamount=0;
	    int postamount=0;
	    
	    File file = new File(filepath);
	    BufferedReader reader = null;
	    String sellertitle = null;
		int flag = 0; //�����ж��Ƿ��Ѿ��õ������ҵ�����
		String[] splitss = new String[500];
		String dialogcontent = "";
		String tempString = "";
		String sql1= "";
		boolean judge = false;
		boolean judgeCommunicationTypeOccor = false;
		boolean giveUpReasonOccor = false;
		
		String CommunicationType="";
		String Product="";
		String CommunicationContent="";
		String TradeId="";
		String AutomaticReply="";
		
		
		String ProductName="";
		//String CommunicationContent;
		//String AutomaticReply;
		String ProductAvailableInquire="";
		String ProductQualityInquire="";
		String DeliveryInquire="";
		String ReturnInquiry="";
		String ConfirmPromisedService="";
		String ProductFeature="";
		String ProductSelectionBehavior="";
		String Bargaining="";
		String FlexiblePolicy="";
		String SellerEmotionalIcons="";
		String BuyerEmotionalIcons="";
		String GiveUpReason="";
		//���������׵�һ������ң�һ��������
		int BuyerQinNumber=0;
		int SellerQinNumber=0;
		
		
	    //System.out.println("Dialog number is "+w);
          /*
          for(int i=0;i<w;i++){
        	  System.out.println("New Dialog line number is "+newlogline[i]);
          }
            */
		try{
          reader = new BufferedReader(new FileReader(file));//���¶��ļ�
          DialogBean[] dialogben = new DialogBean[w];//W��dialogbean
          DialogContentBean[] contentbean = new DialogContentBean[500];//ÿ��dialog���ܹ�����500�������¼
          for(int i=0;i<4;i++){
        	  reader.readLine();//ǰ���и�ʽ�ı����ȴ����        	  
          }
          //��һ������,���õ�һ��bean
          System.out.println("��-"+1+"��-������");
          
          dialogcontent="";
          dialogben[0]=new DialogBean();
          dialogben[0].setNickname(nickname);
          flag=0;
          int ee=0;//��������dialogcontentbean
          
          
         
          for(int i=0;i<w;i++){
        	  System.out.println("��-"+(i+1)+"��-������");
        	  dialogcontent="";
              dialogben[i]=new DialogBean();
              dialogben[i].setNickname(nickname);//set buyer name
              flag=0;
              sellertitle="";
              ee=0;
              judge=false;
              CommunicationType="";
	      	  Product="";
	      	  TradeId="";
	      	  AutomaticReply="";
	      	judgeCommunicationTypeOccor=false;
	      	giveUpReasonOccor = false;
	      	  
	      	  System.out.println("newlogline[i+1] is "+newlogline[i+1]+" ; newlogline[i] is "+newlogline[i]);
        	  for(int j=0;j<newlogline[i+1]-newlogline[i];j++){//����communicationType�Զ��ֿ�
//        		  System.out.println("�����ǳ���17�б���");
        		  //�ҳ���һ�У��ҳ����ڣ�Ȼ��ʼһ�´���
        		  if(j==0){
        		  while ((tempString = reader.readLine()) != null) {
                	  String dateEL = "20[0-1]{2}-[0-9]{2}-[0-9]{2}";
                	  if(tempString.matches(dateEL)){
                		  dialogben[i].setLogdate(tempString);
                		  BuyerQinNumber=0;
                		  SellerQinNumber=0;
                		  break;
                	  }
                   }
            	  }
            	  else{
                  
            	  tempString=reader.readLine();//��õ�һ��log������
            	  tempString = General.LineStringTransfer(tempString);//���������()
            	  System.out.println(tempString);
            	  
    			  Pattern sellernamePattern = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):[\\s\\S]*?");
    			  Matcher sellernameMatcher = sellernamePattern.matcher(tempString);
    			  
    			  Pattern sellernamePattern2 = Pattern.compile("20[0-1]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} ([\\s\\S]*?):([\\s\\S]*?): [\\s\\S]*?");
    			  Matcher sellernameMatcher2 = sellernamePattern2.matcher(tempString);
    			  
 //   			  System.out.println("Two methods are defined");

    			  if(sellernameMatcher2.find()){
//	        		  System.out.println("find the name : matcher2 "+sellernameMatcher2.group(1));//get the username
	        		  if(flag==0){
	        			  if(nickname.equals(sellernameMatcher2.group(1))){//ȷ�����ֵ������������������������
//	        				  System.out.println("Count qin, matcher2, buyer ");
		        				  BuyerQinNumber = BuyerQinNumber+General.occurTimes(tempString.split(sellernameMatcher2.group(2)+": ")[1], "��");//������������̵��У����������ʳ��ֵĴ���
	        				 
		        	      }
		        		  else {
		        			  sellertitle=sellernameMatcher2.group(1);
		        			  dialogben[i].setSellertitle(sellertitle);
		        			  flag=1;
//		        			  System.out.println("Count qin, matcher, seller ");
//		        			  System.out.println("Seller name after count is "+sellernameMatcher2.group(1));
			        			  SellerQinNumber = SellerQinNumber+General.occurTimes(tempString.split(sellernameMatcher2.group(2)+": ")[1], "��");//������������̵��У����������ʳ��ֵĴ���
//			        		  System.out.println("After seller qin number count");
		        		  }
	        		  }
	        		  //--����˻��seller��name
	        		  //�����￪ʼ����chatcontent���ݣ�������ȡ����
	        		  splitss=tempString.split(" "+sellernameMatcher2.group(1)+":"+sellernameMatcher2.group(2)+": ");
	        		  if(splitss.length>=2){
	        			  contentbean[ee] = new DialogContentBean();
	        			  contentbean[ee].setTime(splitss[0]);
	        			  contentbean[ee].setTitle(sellernameMatcher2.group(1));//�������жԻ�������������������������;
	        			  String temp="";
	        			  for(int tt=1;tt<splitss.length;tt++)
	        				  temp=temp+splitss[tt];
	        			  contentbean[ee].setContent(temp);
	        			  ee++;
	        		  }
	        		  else  {
	        			  System.out.println("����������ش�����ΪsplitС��2:  "+tempString);
	        			  WriteStreamAppend.method1(errorlogfilepath,tempString+";-DWT-"+"����������ش�����ΪsplitС��2;��������Ϊ��һ����Ȼ���ж�Ϊ�������������������Ǵ��������"+""+datacollector+","+realname+"\r\n");
	        		  }
	        		  
	        		  dialogcontent=dialogcontent+tempString+"\n";
	               }
    			  else if(sellernameMatcher.find()){
//		        		  System.out.println("find the name : matcher "+sellernameMatcher.group(1));//get the username
		        		  if(flag==0){
//		        			  System.out.println("Matcher, flag ==0");
		        			  if(nickname.equals(sellernameMatcher.group(1))){//ȷ�����ֵ�������������������������
//		        		          System.out.println("Nickname : "+sellernameMatcher.group(1));//get the username
		        				  
//		        					  System.out.println("Count qin, matcher, buyer ");
			        				  BuyerQinNumber = BuyerQinNumber+General.occurTimes(tempString.split(sellernameMatcher.group(1)+": ")[1], "��");//������������̵��У����������ʳ��ֵĴ���
		        				  
			        	      }
			        		  else {
			        			  sellertitle=sellernameMatcher.group(1);
			        			  dialogben[i].setSellertitle(sellertitle);
			        			  flag=1;
//			        			       System.out.println("Count qin, matcher, eller ");
				        			  SellerQinNumber = SellerQinNumber+General.occurTimes(tempString.split(sellernameMatcher.group(1)+": ")[1], "��");//������������̵��У����������ʳ��ֵĴ���
		        				  
			        		  }
		        		  }
		        		  //--����˻��seller��name
		        		  //�����￪ʼ����chatcontent���ݣ�������ȡ����
		        		  splitss=tempString.split(" "+sellernameMatcher.group(1));
		        		  if(splitss.length>=2){
		        			  contentbean[ee] = new DialogContentBean();
		        			  contentbean[ee].setTime(splitss[0]);
		        			  contentbean[ee].setTitle(sellernameMatcher.group(1));//�������жԻ�������������������������;
		        			  String temp="";
		        			  for(int tt=1;tt<splitss.length;tt++)
		        				  temp=temp+splitss[tt];
		        			  contentbean[ee].setContent(temp);
		        			  ee++;
		        		  }
		        		  else  {
		        			  System.out.println("����������ش�����ΪsplitС��2:  "+tempString);
		        			  WriteStreamAppend.method1(errorlogfilepath,contentbean[ee].getContent()+";-----"+"����������ش�����ΪsplitС��2;��������Ϊ��һ����Ȼ���ж�Ϊֻ��һ���������������Ǵ��������"+""+datacollector+","+realname+"\r\n");
		        		  }
		        		  dialogcontent=dialogcontent+tempString+"\n";
		           }
    			  else if(General.occurTimes(tempString, "CommunicationType")>0){//�ҵ���CommunicationType��һ�У��ͶϿ�ѭ��
 //   				  System.out.println("Communication Type is found, break");
    				  judgeCommunicationTypeOccor=true;
    				  break;//�Ѿ��ﵽcommunicationType��һ�У��Զ��Ͽ�������ʼ���������16�б��루ע�⣬����17����ΪcommunicationType������������
    			  }
    			  else {
    				  System.out.println("����������ش�����Ϊ�޷��ҵ����е���������");
        			  WriteStreamAppend.method1(errorlogfilepath,tempString+"����������ش�����Ϊ�޷��ҵ����е���������,"+""+datacollector+","+realname+"\r\n");
    			  }
            	 }//end j equals 0
        		  //System.out.println(tempString);
        	  }
    			  
        	  for(int j=0;j<20;j++){//�������17�б���Ĵ������ڽ���ʣ��16�У���ΪcommunicationType��������
        		  
        		  tempString=reader.readLine();//����17��
            	  tempString = General.LineStringTransfer(tempString);//����17��
//            	  System.out.println("������봦��׶�, J is "+j+"; String is"+tempString);
            	  /*
        		  Pattern CommunicationTypePattern = Pattern.compile("CommunicationType:([\\s\\S]*?)");
    			  Matcher CommunicationTypeMatcher = CommunicationTypePattern.matcher(tempString);//get communication Type
    			  */
    			  
    			  Pattern ProductNamePattern = Pattern.compile("ProductName:([\\s\\S]*?)");
    			  Matcher ProductNameMatcher = ProductNamePattern.matcher(tempString);//get product name in 17 lines
    			  
    			  Pattern CommunicationContentPattern = Pattern.compile("CommunicationContent:([\\s\\S]*?)");
    			  Matcher CommunicationContentMatcher = CommunicationContentPattern.matcher(tempString);//get product name
    			  
    			  Pattern TradeIdPattern = Pattern.compile("TradeId:([\\s\\S]*?)");
    			  Matcher TradeIdMatcher = TradeIdPattern.matcher(tempString);
    			  
    			  Pattern AutomaticReplyPattern = Pattern.compile("AutomaticReply:([\\s\\S]*?)");
    			  Matcher AutomaticReplyMatcher = AutomaticReplyPattern.matcher(tempString);
    			  
    			  Pattern ProductAvailableInquirePattern = Pattern.compile("ProductAvailableInquire:([\\s\\S]*?)");
    			  Matcher ProductAvailableInquireMatcher = ProductAvailableInquirePattern.matcher(tempString);
    			  
    			  Pattern ProductQualityInquirePattern = Pattern.compile("ProductQualityInquire:([\\s\\S]*?)");
    			  Matcher ProductQualityInquireMatcher = ProductQualityInquirePattern.matcher(tempString);
    			  
    			  Pattern DeliveryInquirePattern = Pattern.compile("DeliveryInquire:([\\s\\S]*?)");
    			  Matcher DeliveryInquireMatcher = DeliveryInquirePattern.matcher(tempString);
    			  
    			  Pattern ReturnInquiryPattern = Pattern.compile("ReturnInquiry:([\\s\\S]*?)");
    			  Matcher ReturnInquiryMatcher = ReturnInquiryPattern.matcher(tempString);
    			  
    			  Pattern ConfirmPromisedServicePattern = Pattern.compile("ConfirmPromisedService:([\\s\\S]*?)");
    			  Matcher ConfirmPromisedServiceMatcher = ConfirmPromisedServicePattern.matcher(tempString);
    			  
    			  Pattern ProductFeaturePattern = Pattern.compile("ProductFeature:([\\s\\S]*?)");
    			  Matcher ProductFeatureMatcher = ProductFeaturePattern.matcher(tempString);
    			  
    			  Pattern ProductSelectionBehaviorPattern = Pattern.compile("ProductSelectionBehavior:([\\s\\S]*?)");
    			  Matcher ProductSelectionBehaviorMatcher = ProductSelectionBehaviorPattern.matcher(tempString);
    			  
    			  Pattern BargainingPattern = Pattern.compile("Bargaining:([\\s\\S]*?)");
    			  Matcher BargainingMatcher = BargainingPattern.matcher(tempString);
    			  
    			  Pattern FlexiblePolicyPattern = Pattern.compile("FlexiblePolicy:([\\s\\S]*?)");
    			  Matcher FlexiblePolicyMatcher = FlexiblePolicyPattern.matcher(tempString);
    			  
    			  Pattern SellerEmotionalIconsPattern = Pattern.compile("SellerEmotionalIcons:([\\s\\S]*?)");
    			  Matcher SellerEmotionalIconsMatcher = SellerEmotionalIconsPattern.matcher(tempString);
    			  
    			  Pattern BuyerEmotionalIconsPattern = Pattern.compile("BuyerEmotionalIcons:([\\s\\S]*?)");
    			  Matcher BuyerEmotionalIconsMatcher = BuyerEmotionalIconsPattern.matcher(tempString);
    			  
    			  Pattern GiveUpReasonPattern = Pattern.compile("GiveUpReason:([\\s\\S]*?)");
    			  Matcher GiveUpReasonMatcher = GiveUpReasonPattern.matcher(tempString);
    			  
    			  /*
    			  if (CommunicationTypeMatcher.find()){
			    	   CommunicationType=tempString.split(CommunicationTypeMatcher.group(0))[1];
			       }
			       else
			       */ 
			       if(ProductNameMatcher.find()){
			    	   ProductName=tempString.split(ProductNameMatcher.group(0))[1];
			       }//
			       else if(CommunicationContentMatcher.find()){
			    	   CommunicationContent=tempString.split(CommunicationContentMatcher.group(0))[1];
			       }
			       else if(TradeIdMatcher.find()){
			    	   TradeId=tempString.split(TradeIdMatcher.group(0))[1];
			    	   TradeId=TradeId.trim();//ȥ������Ҫ�Ŀո�
			       }
			       else if(AutomaticReplyMatcher.find()){
			    	   AutomaticReply=tempString.split(AutomaticReplyMatcher.group(0))[1];   
			       }
			       else if(ProductAvailableInquireMatcher.find()){
			    	   ProductAvailableInquire=tempString.split(ProductAvailableInquireMatcher.group(0))[1];   
			       }
			       else if(ProductQualityInquireMatcher.find()){
			    	   ProductQualityInquire=tempString.split(ProductQualityInquireMatcher.group(0))[1];   
			       }
			       else if(DeliveryInquireMatcher.find()){
			    	   DeliveryInquire=tempString.split(DeliveryInquireMatcher.group(0))[1];   
			       }
			       else if(ReturnInquiryMatcher.find()){
			    	   ReturnInquiry=tempString.split(ReturnInquiryMatcher.group(0))[1];   
			       }
			       else if(ConfirmPromisedServiceMatcher.find()){
			    	   ConfirmPromisedService=tempString.split(ConfirmPromisedServiceMatcher.group(0))[1];   
			       }
			       else if(ProductFeatureMatcher.find()){
			    	   ProductFeature=tempString.split(ProductFeatureMatcher.group(0))[1];   
			       }
			       else if(ProductSelectionBehaviorMatcher.find()){
			    	   ProductSelectionBehavior=tempString.split(ProductSelectionBehaviorMatcher.group(0))[1];   
			       }
			       else if(BargainingMatcher.find()){
			    	   Bargaining=tempString.split(BargainingMatcher.group(0))[1];   
			       }
			       else if(FlexiblePolicyMatcher.find()){
			    	   FlexiblePolicy=tempString.split(FlexiblePolicyMatcher.group(0))[1];   
			       }
			       else if(SellerEmotionalIconsMatcher.find()){
			    	   SellerEmotionalIcons=tempString.split(SellerEmotionalIconsMatcher.group(0))[1];   
			       }
			       else if(BuyerEmotionalIconsMatcher.find()){
			    	   BuyerEmotionalIcons=tempString.split(BuyerEmotionalIconsMatcher.group(0))[1];   
			       }
			       else if(GiveUpReasonMatcher.find()){
			    	   GiveUpReason=tempString.split(GiveUpReasonMatcher.group(0))[1]; 
			    	   System.out.println("GiveUpReason is found, break");
					   giveUpReasonOccor=true;
					   break;
			       }
				   else
				   {
					/*
						//System.out.println("cannot find the name;");
						contentbean[ee-1].setContent(contentbean[ee-1].getContent()+"\n"+tempString);
						dialogcontent=dialogcontent+tempString+"\n";
					*/
					System.out.println("����������ش�����17���ڳ�����");
	        		WriteStreamAppend.method1(errorlogfilepath,tempString+"����������ش�����17���ڳ�����"+""+datacollector+","+realname+"\r\n");
				}
        		  
        	  }
        	  dialogben[i].setCommunicationType("Pre");
        	  dialogben[i].setProductName(ProductName);
        	  dialogben[i].setCommunicationContent(CommunicationContent);
        	  dialogben[i].setTradeId(TradeId);
        	  dialogben[i].setTradeid(TradeId);
        	  dialogben[i].setAutomaticReply(AutomaticReply);
        	  dialogben[i].setProductAvailableInquire(ProductAvailableInquire);
        	  dialogben[i].setProductQualityInquire(ProductQualityInquire);
        	  dialogben[i].setDeliveryInquire(DeliveryInquire);
        	  dialogben[i].setReturnInquiry(ReturnInquiry);
        	  dialogben[i].setConfirmPromisedService(ConfirmPromisedService);
        	  dialogben[i].setProductFeature(ProductFeature);
        	  dialogben[i].setProductSelectionBehavior(ProductSelectionBehavior);
        	  dialogben[i].setBargaining(Bargaining);
        	  dialogben[i].setFlexiblePolicy(FlexiblePolicy);
        	  dialogben[i].setSellerEmotionalIcons(SellerEmotionalIcons);
        	  dialogben[i].setBuyerEmotionalIcons(BuyerEmotionalIcons);
        	  dialogben[i].setGiveUpReason(GiveUpReason);
        	  dialogben[i].setBuyerQinNumber(Integer.toString(BuyerQinNumber));
        	  dialogben[i].setSellerQinNumber(Integer.toString(SellerQinNumber));

        	  dialogben[i].setCommunicationProduct(Product);
        	  dialogben[i].setDialogcontent(dialogcontent);
        	  
        	  if(dialogben[i].getLogdate()==null||dialogben[i].getNickname()==null){
        		  
            	  WriteStreamAppend.method1(errorlogfilepath,
            			  "������ȡ����:"+datacollector+","+realname+"logdate is "+dialogben[i].getLogdate()+"nickname is "+dialogben[i].getNickname()+
            			  "sellertitle is "+dialogben[i].getSellertitle()+"content is "+dialogben[i].getDialogcontent()+"\r\n");
            	  
        		  System.out.println("������ȡ����:"+datacollector+","+realname+"logdate is "+dialogben[i].getLogdate()+"nickname is "+dialogben[i].getNickname()+
            			  "sellertitle is "+dialogben[i].getSellertitle()+"content is "+dialogben[i].getDialogcontent()+"\r\n");
              }
        	  /*
              System.out.println("logdate is "+dialogben[i].getLogdate());
              System.out.println("nickname is "+dialogben[i].getNickname());
              System.out.println("sellertitle is "+dialogben[i].getSellertitle());
              System.out.println("content is "+dialogben[i].getDialogcontent());
              */
        	  dialogben[i].setDialogcontent(General.DotTransfer(dialogben[i].getDialogcontent()));
        	  dialogben[i].setContentb(contentbean, ee);
        	  dialogben[i].setSessioniii(i);
        	
        	  judge=DealChatLogContent.ContentDataGet(contentbean, nickname, sellertitle,ee);
        	  if(judge){
        		  System.out.println("Judge is true");
        		  //Old Version from Dec. 20. 2012
        		  /*
                  sql1 =  "insert into dialog(nickname,datacollector,realname,sellertitle,logdate,dialogcontent) " +
          			"values('"+dialogben[i].getNickname()+"','"+datacollector+"','"+realname+"','"+dialogben[i].getSellertitle()+"','"+dialogben[i].getLogdate()+"','"+dialogben[i].getDialogcontent()+"')";
        		  if("Post".equals(CommunicationType)){
        			//Insert into Post Table
        			//session��һ�ֶν�sessioniii����������ݿ⣬��������session�ǵڼ���
        			sql1 = "insert into postcommunication(nickname,datacollector,realname,sellertitle,sessioniii,logdate,dialogcontent,tradeid,productname,communicationcontent)"+
        			"values('"+dialogben[i].getNickname()+"','"+datacollector+"','"+realname+"','"+dialogben[i].getSellertitle()+"',"+dialogben[i].getSessioniii()+",'"+dialogben[i].getLogdate()+"','"+dialogben[i].getDialogcontent()+
        			"','"+TradeId+"','"+Product+"','"+CommunicationContent+"')";
        			postsession[postamount++]=i;
        		  }
        		  else if("Pre".equals(CommunicationType)){
        			sql1 = "insert into precommunication(nickname,datacollector,realname,sellertitle,sessioniii,logdate,dialogcontent,tradeid,productname,communicationcontent)"+
          			"values('"+dialogben[i].getNickname()+"','"+datacollector+"','"+realname+"','"+dialogben[i].getSellertitle()+"',"+dialogben[i].getSessioniii()+",'"+dialogben[i].getLogdate()+"','"+dialogben[i].getDialogcontent()+
          			"','"+TradeId+"','"+Product+"','"+CommunicationContent+"')";     		
        			presession[preamount++]=i;
        			System.out.println(sql1);
        			
        		  }
        		  else{
        			  System.out.println("�����ˣ����communication session����û��type");
        		  }
        		  */
        		  //End Old Version from Dec. 20. 2012
        		  if("Pre".equals("Pre")){
          			sql1 = "insert into precommunication20121220(nickname,datacollector,realname,sellertitle," +
          					"sessioniii,logdate,dialogcontent,productname,communicationcontent,tradeid,automaticreply,ProductAvailableInquire" +
          					",ProductQualityInquire,DeliveryInquire,ReturnInquiry,ConfirmPromisedService,ProductFeature,ProductSelectionBehavior" +
          					"Bargaining,FlexiblePolicy,SellerEmotionalIcons,BuyerEmotionalIcons,BuyerQinNumber,SellerQinNumber)"+
            			"values('"+dialogben[i].getNickname()+"','"+datacollector+"','"+realname+"','"+dialogben[i].getSellertitle()+"',"
            			+dialogben[i].getSessioniii()+",'"+dialogben[i].getLogdate()+"','"+dialogben[i].getDialogcontent()+
            			"','"+dialogben[i].getProductName()+"','"+dialogben[i].getCommunicationContent()+"','"+dialogben[i].getTradeid()+
            			"','"+dialogben[i].getAutomaticReply()+"','"+dialogben[i].getProductAvailableInquire()
            			+"','"+dialogben[i].getProductQualityInquire()+"','"+dialogben[i].getDeliveryInquire()+"','"
            			+dialogben[i].getReturnInquiry()+"','"+dialogben[i].getConfirmPromisedService()+
            			"','"+dialogben[i].getProductFeature()+"','"+dialogben[i].getProductSelectionBehavior()+"','"
            			+dialogben[i].getBargaining()+"','"+dialogben[i].getFlexiblePolicy()+"','"+dialogben[i].getSellerEmotionalIcons()+
            			"','"+dialogben[i].getBuyerEmotionalIcons()+"','"+dialogben[i].getBuyerQinNumber()+"','"+dialogben[i].getSellerQinNumber()
            			+"')";     		
          			presession[preamount++]=i;
          			System.out.println(sql1);
          			
          		  }
        		  
                 //data  System.out.println(sql1);
	  		        try{
	  		      	  //MySQLConn.insert(sql1);//2012-7-13
	  		        }catch(Exception e){
	  		        	
	  		             WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
	  		            
	  		           System.out.println("exception is :"+e.toString());
	  		        }
	  		      usefulsession[usefulamount++]=i;
        	  }else{
        		  usefulw--;
        		  //System.out.println("Useful W is "+usefulw);
        	  }
 //       	  System.out.println("CommunicationType is "+CommunicationType+"\n Product name is"+Product+"\n CommunicationContent is"+CommunicationContent+"\n TradeId is "+TradeId+"\n AutomaticReply is "+AutomaticReply);//2012-7-13
          }//end for loop ,(i loop)
           
        /*2012-7-13 
        sql1="update userinfo set usefullogamount='"+usefulw+"' where datacollector='"+datacollector+"' and realname='"+realname+"'";//������Щȱ��nickname���û�
  	    try{
        	  MySQLConn.update(sql1);
          }catch(Exception e){
          	WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
          	System.out.println("exception is :"+e.toString());
          }
        */
//          System.out.println("Usefulamount is "+usefulamount+",usefulw is "+usefulw);
//          System.out.println("Postamount is "+ postamount);
//          System.out.println("Preamount is "+preamount);
          
  DealChatLogContent.contentanalysis(dialogben,usefulsession,usefulw,datacollector,realname);//Get Data From Communication Sessions.
         
          //Start Txt data building
          /*
          DealPreCommunicationContent.contentanalysis(dialogben, presession, preamount, datacollector, realname);
          
          */
          //End txt
          reader.close();

  			MySQLConn.stmt.close();
  			MySQLConn.con.close();
  		}
  			catch(Exception e){
  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+datacollector+","+realname+"\r\n");
  				System.out.println("��������exception�׳����쳣"+e.toString());
  		}
        
		//���봦������
	}
}
