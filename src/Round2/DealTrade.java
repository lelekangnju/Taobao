package Round2;

import Round2.MySQLConn;

public class DealTrade {
		  
	public static void main(String args[]){
		//86093869709877
		//110658920730977;110657765290977
		SearchTradeid("86093869709877");
		
	}
	
	
	public static void SearchTradeid(String tradeid){//这里有些可能有多个tradeid，需要处理
	 
		String everytradeid[] = tradeid.split(";");
		String sql= "";
		boolean flag=false;
		TradeBean[] tb = new TradeBean[everytradeid.length];//获得有多少个tradeid
		
		try{
			MySQLConn.connect();
		}
	    catch(Exception e){	
	    }
	    
		for(int i=0;i<everytradeid.length;i++){//处理每个TradeId
			//System.out.println("TradeID is "+everytradeid[i]);
			//从数据库中提取相应trade和item信息
			flag=false;
		    try{
		    	sql = "select * from tradeinfo where tradeid='"+everytradeid[i]+"'";//查看此人是否已经存在
				MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
				if(MySQLConn.rs.next()){
					//System.out.println("搜索到此交易记录");
					tb[i] = new TradeBean();
					tb[i].setTradeid(MySQLConn.rs.getString(2));
					
					ItemBean[] ibsearch = new ItemBean[100];
					int ee = 0;
					ee=DealTradeItem.SearchItems(ibsearch,tb[i].getTradeid(),ee);//返回有多少条记录
					tb[i].setItemBean(ibsearch, ee);
					//System.out.println("Second Line ItemId is "+tb[i].getIteamBean().length);
					tb[i].setTradestatus(MySQLConn.rs.getString(3));
					tb[i].setDealtime(MySQLConn.rs.getString(4));
					tb[i].setAmountmoney(MySQLConn.rs.getDouble(5));
					tb[i].setPosttype(MySQLConn.rs.getString(6));
				}
				else{
					System.out.println("没有找到相应的交易记录，出错了");
				}
		    }
		    catch(Exception e){
		    	System.out.println("Deal Trade, Exception is "+e.toString());
		    }
		}//end I loop
		/*
		for(int j=0;j<everytradeid.length;j++){
			System.out.println("j is "+j);
			System.out.println(tb[j].getTradeid()+","+tb[j].getTradestatus());
		}
		*/
		
		  String datalogNofilepath  = "E:/Lele/Taobao/datalogYes.txt";
    	  //增加代码，输出相应的购买记录
		  for(int j=0;j<everytradeid.length;j++){
    	  WriteStreamAppend.method1(datalogNofilepath,"TheTrade information is, " +
    			  "TradeID is "+tb[j].getTradeid()+"; Tradestatus is "+tb[j].getTradestatus()+"; Dealtime is "+tb[j].getDealtime()+
					"; Amountmoney is "+tb[j].getAmountmoney()+"; PostType is "+tb[j].getPosttype()+";" +
			   		"\r\n");
    	  for(int w=0;w<tb[j].getIteamBean().length;w++){
    	  /*
    	  WriteStreamAppend.method1(datalogNofilepath,"ItemID is "+tb[j].getIteamBean()[w].getItemid()+
				"; ItemName is"+tb[j].getIteamBean()[w].getItemname()+
				"; ItemPrice is"+tb[j].getIteamBean()[w].getItemprice()+
				"; ItemQuatity is"+tb[j].getIteamBean()[w].getItemquatity()+
			   		"\r\n");
		  */
    	  }
		  }
		  WriteStreamAppend.method1(datalogNofilepath,"\r\n\r\n");
		/*
		for(int j=0;j<everytradeid.length;j++){
			System.out.println(
					"TradeID is "+tb[j].getTradeid()+"; Tradestatus is "+tb[j].getTradestatus()+"; Dealtime is "+tb[j].getTradestatus()+
					"; Amountmoney is "+tb[j].getAmountmoney()+"; PostType is "+tb[j].getPosttype()+";" 
			);
			//System.out.println("IteamBean amount is "+tb[j].getIteamBean().length);
			for(int w=0;w<tb[j].getIteamBean().length;w++){
				System.out.println(
				"ItemID is "+tb[j].getIteamBean()[w].getItemid()+
				"; ItemName is"+tb[j].getIteamBean()[w].getItemname()+
				"; ItemPrice is"+tb[j].getIteamBean()[w].getItemprice()+
				"; ItemQuatity is"+tb[j].getIteamBean()[w].getItemquatity()
				);
			}
		}
        */
        try{
  			MySQLConn.stmt.close();
  			MySQLConn.con.close();
  		}
  			catch(Exception e){
  				System.out.println("Close MySQL is wrong");
  		}	  
	}
	
	//search trade items
	

	
}
