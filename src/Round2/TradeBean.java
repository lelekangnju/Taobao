package Round2;
import Round2.ItemBean;

public class TradeBean {

	  public ItemBean[] getIteamBean(){
		  /*
		  for(int i=0;i<itembean.length;i++){
			  System.out.println("Item ID is     :"+itembean[i].getItemid());
			  System.out.println("Item name is   :"+itembean[i].getItemname());
		  }
		  */
		  return itembean;
	  }
	  public void setItemBean(ItemBean ib[],int ee){
		  itembean = new ItemBean[ee];
		  for(int i=0;i<ee;i++){
			   itembean[i] = new ItemBean();
			   itembean[i] = ib[i];
	   }			  
	  }
	  
	public String getTradeid() {
		return tradeid;
	}
	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	public String getTradestatus() {
		return tradestatus;
	}
	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public double getAmountmoney() {
		return amountmoney;
	}
	public void setAmountmoney(double amountmoney) {
		this.amountmoney = amountmoney;
	}
	public String getPosttype() {
		return posttype;
	}
	public void setPosttype(String posttype) {
		this.posttype = posttype;
	}
	String tradeid;
	String tradestatus;
	String dealtime;
	double amountmoney;
	String posttype;
	ItemBean itembean[];
}
