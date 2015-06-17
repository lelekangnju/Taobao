package Backup;
import java.sql.*;

public class MySQLConn {
	
    public static String url = "jdbc:mysql://localhost:3306/wangwang?useUnicode=true&characterEncoding=GB2312";//characterEncoding=GBK
    public static String username = "root";
    public static String password = "";
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
	public static String errorlogfilepath = "E:/Lele/Taobao/errorlog.txt";
   
    public static void connect() {
        // 定位驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载驱动成功!"); 
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败!");
            e.printStackTrace();
        }
        // 建立连接
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            System.out.println("数据库连接成功!"); 
        } catch(SQLException e) {
            System.out.println("数据库连接失败!"); 
        }
    }
    public static void select(String sql) {
        try {
            rs = stmt.executeQuery(sql);
            ResultSetMetaData meta_data = rs.getMetaData();//列名
            for (int i_col = 1; i_col <= meta_data.getColumnCount(); i_col++) {
                System.out.print(meta_data.getColumnLabel(i_col) + "   ");
            }
            System.out.println();
            while (rs.next()) {
                for (int i_col = 1; i_col <= meta_data.getColumnCount(); i_col++) {
                    System.out.print(rs.getString(i_col) + "  ");
                }
                System.out.println();
            }
            rs.close();
        }catch (Exception e) {
            WriteStreamAppend.method1(errorlogfilepath," 数据库查询失败+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
    }
    public static void insert(String sql) {
        try {
        	
            stmt.clearBatch();
            stmt.addBatch(sql);
            stmt.executeBatch();
            
            System.out.println("数据插入成功!");
        }catch (Exception e) {
            System.out.println("数据插入失败!");
            //System.out.println("插入过程出错"+e.toString());
            WriteStreamAppend.method1(errorlogfilepath," 数据库插入失败+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
        
    }
    public static void update(String sql) {
        try {
            stmt.executeUpdate(sql);
            System.out.println("数据更新成功!");
        }catch (Exception e) {
            System.out.println("数据更新失败!");
            WriteStreamAppend.method1(errorlogfilepath," 数据库更新失败+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
    }
}
