package Round2;
import java.sql.*;

public class DealTradeItem {

	public static int SearchItems(ItemBean ibsearch[],String tradeid,int ee){//������Щ�����ж��tradeid����Ҫ����
		
		String sql= "";
		int i=0;
		ResultSet rsitem = null;
		try{
			MySQLConn.connect();
		}
	    catch(Exception e){	
	    }
		try{
		    	sql = "select * from tradeitem where tradeid='"+tradeid+"' group by itemname,itemid";//�鿴�����Ƿ��Ѿ�����
				rsitem=MySQLConn.stmt.executeQuery(sql);
				while(rsitem.next()){
					//System.out.println("�����������еĲ�Ʒ��Ϣ��");
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
