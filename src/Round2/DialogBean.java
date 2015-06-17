package Round2;

public class DialogBean {
		  String nickname;
		  String sellertitle;
		  String dialogcontent;
		  String logdate;
		  int sessioniii;//用于存储这个session是第几个session
		  String tradeid;
		  String CommunicationProduct;//存储谈论的产品的名字
		  String CommunicationContent;//存储主要谈论的内容（这是通过reading获得的）
		  String AutomaticReply;//是否开启了自动回复功能能
		  DialogContentBean contentb[];//用于存储每行的信息。这样一个完全可以处理的session就被完全获取了
		  /*
		   * 加入每个log所编码出来的属性，一共是17个属性+“亲“+”表情符号“
		   * 
		   */
		  String CommunicationType;
		  String ProductName;
		  //String CommunicationContent;
		  String TradeId;
		  //String AutomaticReply;
		  String ProductAvailableInquire;
		  String ProductQualityInquire;
		  String DeliveryInquire;
		  String ReturnInquiry;
		  String ConfirmPromisedService;
		  String ProductFeature;
		  String ProductSelectionBehavior;
		  String Bargaining;
		  String FlexiblePolicy;
		  String SellerEmotionalIcons;
		  String BuyerEmotionalIcons;
		  String GiveUpReason;
		  //两个处理亲的一个是买家，一个是卖家
		  String BuyerQinNumber;
		  String SellerQinNumber;
		  
		  //SB, you should use ArrayList.
		  
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
		public String getTradeId() {
			return TradeId;
		}
		public void setTradeId(String tradeId) {
			TradeId = tradeId;
		}
		public String getProductAvailableInquire() {
			return ProductAvailableInquire;
		}
		public void setProductAvailableInquire(String productAvailableInquire) {
			ProductAvailableInquire = productAvailableInquire;
		}
		public String getProductQualityInquire() {
			return ProductQualityInquire;
		}
		public void setProductQualityInquire(String productQualityInquire) {
			ProductQualityInquire = productQualityInquire;
		}
		public String getDeliveryInquire() {
			return DeliveryInquire;
		}
		public void setDeliveryInquire(String deliveryInquire) {
			DeliveryInquire = deliveryInquire;
		}
		public String getReturnInquiry() {
			return ReturnInquiry;
		}
		public void setReturnInquiry(String returnInquiry) {
			ReturnInquiry = returnInquiry;
		}
		public String getConfirmPromisedService() {
			return ConfirmPromisedService;
		}
		public void setConfirmPromisedService(String confirmPromisedService) {
			ConfirmPromisedService = confirmPromisedService;
		}
		public String getProductFeature() {
			return ProductFeature;
		}
		public void setProductFeature(String productFeature) {
			ProductFeature = productFeature;
		}
		public String getProductSelectionBehavior() {
			return ProductSelectionBehavior;
		}
		public void setProductSelectionBehavior(String productSelectionBehavior) {
			ProductSelectionBehavior = productSelectionBehavior;
		}
		public String getBargaining() {
			return Bargaining;
		}
		public void setBargaining(String bargaining) {
			Bargaining = bargaining;
		}
		public String getFlexiblePolicy() {
			return FlexiblePolicy;
		}
		public void setFlexiblePolicy(String flexiblePolicy) {
			FlexiblePolicy = flexiblePolicy;
		}
		public String getSellerEmotionalIcons() {
			return SellerEmotionalIcons;
		}
		public void setSellerEmotionalIcons(String sellerEmotionalIcons) {
			SellerEmotionalIcons = sellerEmotionalIcons;
		}
		public String getBuyerEmotionalIcons() {
			return BuyerEmotionalIcons;
		}
		public void setBuyerEmotionalIcons(String buyerEmotionalIcons) {
			BuyerEmotionalIcons = buyerEmotionalIcons;
		}
		public String getGiveUpReason() {
			return GiveUpReason;
		}
		public void setGiveUpReason(String giveUpReason) {
			GiveUpReason = giveUpReason;
		}
		
		
		public String getBuyerQinNumber() {
			return BuyerQinNumber;
		}
		public void setBuyerQinNumber(String buyerQinNumber) {
			BuyerQinNumber = buyerQinNumber;
		}
		public String getSellerQinNumber() {
			return SellerQinNumber;
		}
		public void setSellerQinNumber(String sellerQinNumber) {
			SellerQinNumber = sellerQinNumber;
		}
		public void setContentb(DialogContentBean[] contentb) {
			this.contentb = contentb;
		}
		public DialogContentBean[] getContentb(){
			  /*
			  for(int i=0;i<contentb.length;i++){
				  System.out.println("This is Bean Time   :"+contentb[i].getTime());
				  System.out.println("This is Bean Title  :"+contentb[i].getTitle());
				  System.out.println("This is Bean Content:"+contentb[i].getContent());
			  }
			  */
			  return contentb;
		  }
		  public void setContentb(DialogContentBean contentbean[],int ee){
			  contentb = new DialogContentBean[ee];
			  for(int i=0;i<ee;i++){
				   contentb[i] = new DialogContentBean();
				   contentb[i] = contentbean[i];
		   }			  
		  }
		  public String getCommunicationProduct() {
			return CommunicationProduct;
		  }
		  public void setCommunicationProduct(String communicationProduct) {
				CommunicationProduct = communicationProduct;
		  }
		  public String getCommunicationContent() {
				return CommunicationContent;
		  }
		  public void setCommunicationContent(String communicationContent) {
				CommunicationContent = communicationContent;
		  }
	      public String getAutomaticReply() {
				return AutomaticReply;
		  }
		  public void setAutomaticReply(String automaticReply) {
				AutomaticReply = automaticReply;
			}
			
			
		
		  public void setNickname(String nickname){
			  this.nickname=nickname;
		  }
		  public String getNickname(){
			  return nickname;
		  }
		  public void setSellertitle(String sellertitle){
			  this.sellertitle=sellertitle;
		  }
		  public String getSellertitle(){
			  return sellertitle;
		  }
		  public void setDialogcontent(String dialogcontent){
			  this.dialogcontent=dialogcontent;
		  }
		  public String getDialogcontent(){
			  return dialogcontent;
		  }
		  public void setLogdate(String logdate){
			  this.logdate=logdate;
		  }
		  public String getLogdate(){
			  return logdate;
		  }
		  public void setTradeid(String tradeid){
			  this.tradeid=tradeid;
		  }
		  public String getTradeid(){
			  return tradeid;
		  }
		  public void setSessioniii(int sessioniii){
			  this.sessioniii=sessioniii;
		  }
		  public int getSessioniii(){
			  return sessioniii;
		  }
}
