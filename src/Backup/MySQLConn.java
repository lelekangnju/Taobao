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
        // ��λ����
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("���������ɹ�!"); 
        } catch (ClassNotFoundException e) {
            System.out.println("��������ʧ��!");
            e.printStackTrace();
        }
        // ��������
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            System.out.println("���ݿ����ӳɹ�!"); 
        } catch(SQLException e) {
            System.out.println("���ݿ�����ʧ��!"); 
        }
    }
    public static void select(String sql) {
        try {
            rs = stmt.executeQuery(sql);
            ResultSetMetaData meta_data = rs.getMetaData();//����
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
            WriteStreamAppend.method1(errorlogfilepath," ���ݿ��ѯʧ��+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
    }
    public static void insert(String sql) {
        try {
        	
            stmt.clearBatch();
            stmt.addBatch(sql);
            stmt.executeBatch();
            
            System.out.println("���ݲ���ɹ�!");
        }catch (Exception e) {
            System.out.println("���ݲ���ʧ��!");
            //System.out.println("������̳���"+e.toString());
            WriteStreamAppend.method1(errorlogfilepath," ���ݿ����ʧ��+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
        
    }
    public static void update(String sql) {
        try {
            stmt.executeUpdate(sql);
            System.out.println("���ݸ��³ɹ�!");
        }catch (Exception e) {
            System.out.println("���ݸ���ʧ��!");
            WriteStreamAppend.method1(errorlogfilepath," ���ݿ����ʧ��+sql is: "+sql+"\r\n"+e.toString()+"\r\n");
        }
    }
}
