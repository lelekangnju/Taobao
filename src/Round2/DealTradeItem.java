package Round2;
import java.sql.*;

public class DealTradeItem {

	public static int SearchItems(ItemBean ibsearch[],String tradeid,int ee){//这里有些可能有多个tradeid，需要处理
		
		String sql= "";
		int i=0;
		ResultSet rsitem = null;
		try{
			MySQLConn.connect();
		}
	    catch(Exception e){	
	    }
		try{
		    	sql = "select * from tradeitem where tradeid='"+tradeid+"' group by itemname,itemid";//查看此人是否已经存在
				rsitem=MySQLConn.stmt.executeQuery(sql);
				while(rsitem.next()){
					//System.out.println("搜索到交易中的产品信息；");
					ibsearch[i] = new ItemBean();
					ibsearch[i].setTradeid(rsitem.getString(2));
					ibsearch[i].setItemid(rsitem.getString(3));
					ibsearch[i].setItemname(rsitem.getString(4));
					ibsearch[i].setItemprice(rsitem.getDouble(5));
					ibsearch[i].setItemquatity(rsitem.getInt(6));
					i++;
				}
				
		  }
		  catch(Exception e){
		    	System.out.println("Exception is "+e.toString());
		  }
		ee=i;
		
		System.out.println("Trade Item ee is "+ee);
		/*
		for(int j=0;j<i;j++){
			System.out.println("j is "+j);
			System.out.println(ibsearch[j].getTradeid()+","+ibsearch[j].getItemname());
		}
		*/
		 try{
	  			//MySQLConn.stmt.close();
	  			//MySQLConn.con.close();
	  			rsitem.close();
	  		}
	  			catch(Exception e){
	  			System.out.println("Close MySQL is wrong");
	  		}	  

  		return ee;
	}
}
