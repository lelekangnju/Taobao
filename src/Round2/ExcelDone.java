package Round2;
import java.io.File;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.Vector;   

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Round2.DemographicInfoBean;
import jxl.Cell;   
import jxl.Sheet;   
import jxl.Workbook;   
import jxl.format.Alignment;   
import jxl.format.Colour;   
import jxl.format.UnderlineStyle;   
import jxl.format.VerticalAlignment;   
import jxl.write.Label;   
import jxl.write.WritableCellFormat;   
import jxl.write.WritableFont;   
import jxl.write.WritableSheet;   
import jxl.write.WritableWorkbook;   
import jxl.write.WriteException;   
import jxl.write.biff.RowsExceededException;  
public class ExcelDone { 
	    /** *//**  
	     * ��������ΪXLS��ʽ  
	     * @param fileName        �ļ������ƣ�������Ϊ����·����Ҳ������Ϊ���·��  
	     * @param content        ���ݵ�����  
	     */

	    public static void exportExcel(String fileName, Vector<Person> content) {
	    	System.out.println("File name is: "+fileName);
	        WritableWorkbook wwb;   
	        FileOutputStream fos;   
	        try {   
	            fos = new FileOutputStream(fileName);   
	            wwb = Workbook.createWorkbook(fos);   
	            WritableSheet ws = wwb.createSheet("����־�佫�б�", 10);        // ����һ��������   
	   
	            //    ���õ�Ԫ������ָ�ʽ   
	            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,   
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);   
	            WritableCellFormat wcf = new WritableCellFormat(wf);   
	            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);    
	            wcf.setAlignment(Alignment.CENTRE);    
	            ws.setRowView(1, 500);   
	   
	            //    ������ݵ�����   
	            Person[] p = new Person[content.size()];   
	            for (int i = 0; i < content.size(); i++){   
	                p[i] = (Person)content.get(i);   
	                ws.addCell(new Label(1, i + 1, p[i].getGroup_number(), wcf));   
	                ws.addCell(new Label(2, i + 1, p[i].getStudent_id(), wcf));   
	                ws.addCell(new Label(3, i + 1, p[i].getStudent_id(), wcf));   
	                ws.addCell(new Label(4, i + 1, p[i].getPerformance(), wcf));    
	                if(i == 0)   
	                    wcf = new WritableCellFormat();   
	            }   
	   
	            wwb.write();
	            wwb.close();   
	        } catch (IOException e){   
	        } catch (RowsExceededException e){   
	        } catch (WriteException e){}   
	    }   
	   
	    /** *//**  
	     * ��Excel�ļ����ȡ���ݱ��浽Vector��  
	     * @param fileName        Excel�ļ�������  
	     * @return                Vector����,���������Excel�ļ����ȡ��������  
	     */   
	    public static Vector<DemographicInfoBean> importExcel(String fileName){
	    	/*
	        Vector<Person> v = new Vector<Person>();   
	        try {   
	            Workbook book = Workbook.getWorkbook(new File(fileName));   
	            Sheet sheet = book.getSheet(0);        // ��õ�һ�����������    
	            int rows = sheet.getRows();   
	               
	            for(int i = 0; i < rows; i++) {   
	                Cell [] cell = sheet.getRow(i);   
	                if(cell.length == 0)   
	                    continue;   
	                   
	                Person p = new Person();
	                p.setGroup_number(sheet.getCell(1, i).getContents());   
	                p.setStudent_name(sheet.getCell(2, i).getContents());   
	                p.setStudent_id(sheet.getCell(3, i).getContents());   
	                p.setPerformance(sheet.getCell(4, i).getContents());   

	                v.add(p);   
	            }   
	   
	            book.close();   
	        }catch(Exception e) {}    
	        return v;   
	        */
	      Vector<DemographicInfoBean> v = new Vector<DemographicInfoBean>();
	      try{
	    	  System.out.println("File Name is "+fileName);
	    	  Workbook book = Workbook.getWorkbook(new File(fileName));
	    	  
	    	  System.out.println("--��ù�����");
	    	  Sheet sheet = book.getSheet(0);        // ��õ�һ�����������    
	    	  System.out.println("��ù�����");
	    	  int rows = sheet.getRows();   
	    	  for(int i = 0; i < rows; i++) {   
	    		  Cell [] cell = sheet.getRow(i);
	    		  if(cell.length == 0)   continue;   
	    		  DemographicInfoBean d = new DemographicInfoBean();
	    		  d.setNo(sheet.getCell(0, i).getContents());
	    		  d.setCollector(sheet.getCell(1, i).getContents());
	    		  d.setRealName(sheet.getCell(2, i).getContents());
	    		  d.setSex(sheet.getCell(6, i).getContents());
	    		  d.setAge(sheet.getCell(7, i).getContents());
	    		  d.setEducation(sheet.getCell(8, i).getContents());
	    		  d.setIncome(sheet.getCell(9, i).getContents());
	    		  d.setJob(sheet.getCell(10, i).getContents());
	    		  d.setOnlineTime(sheet.getCell(11, i).getContents());
	    		  d.setOPT1(sheet.getCell(14, i).getContents());
	    		  d.setOPT2(sheet.getCell(15, i).getContents());
	    		  d.setOPT3(sheet.getCell(16, i).getContents());
	    		  d.setOPT4(sheet.getCell(17, i).getContents());
	    		  d.setOPT5(sheet.getCell(18, i).getContents());
	    		  d.setOPT6(sheet.getCell(19, i).getContents());
	    		  d.setOPT7(sheet.getCell(20, i).getContents());
	    		  v.add(d);
	    	  }
	    	  book.close();   
	      }
	      catch(Exception e){
	    	  System.out.println(e.toString());
	      }
	      return v;
	    }   
	    
	    
	    
	    public static void importExcel2(String fileName){
	      
	      try{
	    	  //System.out.println("File Name is "+fileName);
	    	  Workbook book = Workbook.getWorkbook(new File(fileName));
	    	  
	    	  //System.out.println("--��ù�����");
	    	  Sheet sheet = book.getSheet(0);        // ��õ�һ�����������    
	    	  //System.out.println("��ù�����");
	    	  CnToSpell cts = new CnToSpell();//ת��Ϊƴ��
	    	  int rows = sheet.getRows();  
	    	  String [] names = new String[100];
	    	  boolean judge = false;
	    	  String singlename = null;
	    	  for(int i = 0,j=0,k=0,w=0; i < rows; i++) {   //k ����ж��ٸ���ͬ������
	    		  
	    		  Cell [] cell = sheet.getRow(i);
	    		  if(cell.length == 0)   continue;
	    		  singlename = sheet.getCell(3, i).getContents();
	    		  judge = false;
	    		  if(k==0){
	    			  names[k]=singlename;
	    			  System.out.println(singlename + " is "+k);
	    			  k++;
	    		  }else{
	    		  for(w=0;w<k;w++){
	    			  if(singlename.equalsIgnoreCase(names[w])){
	    				  judge = true;
	    				  break;
	    			  }
	    		  }
	    		  if(w==k){
	    			  names[k]=singlename;
	    			  System.out.println(k);
	    			  k++;
	    		  }else{
	    			  System.out.println(w);
	    		  }
	    		  }
	    		  
	    		  //System.out.println(PingYinUtil.getPingYin(sheet.getCell(4, i).getContents()));
	    		  
	    	  }
	    	  book.close();   
	      }
	      catch(Exception e){
	    	  System.out.println(e.toString());
	      }
	      
	    }
	    
	    
	    // For Mengyue's Method
	    //��һ������������ͬ�����
	    public static void importExcel3(String fileName){
		      
		      try{
		    	  //System.out.println("File Name is "+fileName);
		    	  Workbook book = Workbook.getWorkbook(new File(fileName));
		    	  
		    	  //System.out.println("--��ù�����");
		    	  Sheet sheet = book.getSheet(0);        // ��õ�һ�����������    
		    	  //System.out.println("��ù�����");
		    	  
		    	  int rows = sheet.getRows();  
		    	  String [] names = new String[100];//��ദ��100����ͬ������
		    	  
		    	  boolean judge = false;
		    	  String singlename = null;
		    	  
		    	  System.out.println("category");
		    	  for(int i = 1,j=0,k=0,w=0; i < rows; i++) {   //k ����ж��ٸ���ͬ������
		    		  
		    		  Cell [] cell = sheet.getRow(i);
		    		  if(cell.length == 0)   continue;
		    		  
		    		  singlename = sheet.getCell(11, i).getContents();//��������ڵڼ���
		    		  judge = false;
		    		  
		    		  if(k==0){
		    			  names[k]=singlename;
		    			  System.out.println(k);//System.out.println(singlename + " is "+k);
		    			  k++;
		    		  }else{
		    		  for(w=0;w<k;w++){
		    			  if(singlename.equalsIgnoreCase(names[w])){
		    				  judge = true;
		    				  break;
		    			  }
		    		  }
		    		  if(w==k){
		    			  names[k]=singlename;
		    			  System.out.println(k);
		    			  k++;
		    		  }else{
		    			  System.out.println(w);
		    		  }
		    		  }
		    		  
		    		  //System.out.println(PingYinUtil.getPingYin(sheet.getCell(4, i).getContents()));  
		    	  }
		    	  for(int i=0;i<names.length;i++){
		    		  System.out.println(names[i] + " is "+ i);
		    	  }
		    	  book.close();   
		      }
		      catch(Exception e){
		    	  System.out.println(e.toString());
		      }
		      
		    }
	    
	    
	    
	    
	    
	    
	    public static void importExcelTimeRegressoin(String fileName){
		      
		      try{
		    	  Workbook book = Workbook.getWorkbook(new File(fileName));
		    	  Sheet sheet = book.getSheet(0);        // ��õ�һ�����������    
		    	  int rows = sheet.getRows();
		    	  int column = sheet.getColumns();
		    	  //System.out.println(rows);
		    	  String Total_response_time_individual;
		    	  String Total_response_line_words_individual;
		    	  String Total_ask_time_individual;
		    	  String Combined_c_asks_line_words_individual;

		    	  /*
		    	  for(int i = 0; i < column; i++) {   //��ȡ�����е�����
		    		  
		    		  System.out.println(i+","+sheet.getCell(i, 0).getContents());//(�У���)
		    	  }
		    	  */
		    	  double response_time_everyTiny[] = new double[200000];
		    	  int howmanytinyreply = 0;
		    	  int howmanysig = 0;
		    	  for(int i = 1; i < rows; i++) {   //��ȡ�����е�����
		    		  double timeShuzu[];//���1000
		    		  
		    		  Total_response_time_individual = sheet.getCell(75, i).getContents();
		    		  //System.out.println(Total_response_time_individual);
		    		  //����String�����г���������
		    		  //System.out.println("Test");
		    		  String Temp1[] = Total_response_time_individual.split("\\[");
		    		  String Temp[] =Temp1[1].split(",");
		    		  timeShuzu = new double[Temp.length-1];
		    		  
		    		  for(int j=0;j<Temp.length-1;j++){
		    			  timeShuzu[j]=Double.parseDouble(Temp[j]);
		    			  response_time_everyTiny[howmanytinyreply++]=timeShuzu[j];//���е�������ģ�ÿ���ظ�ʱ�䣬���ӽ�ȥ
		    		  }
		    		  
		    		  /*
		    		  for(double temptest:timeShuzu){
		    			  System.out.println(temptest);
		    		  }
		    		  //������Ѿ�����˷��кõ�double ��������
		    		  */
		    		  TimeResults ts = RegressionTest.CalculateRegression(timeShuzu);
		    		  if(ts.getSlop_sig()<0.1)howmanysig++;
		    		  
		    		  //System.out.println(ts.getIntercept_result()+","+ts.getSlope_result()+","+ts.getSlop_sig()+";");
		    		  System.out.println(ts.getMean_value()+","+ts.getStd());
		    		  
		    		  //System.out.println(Math.abs(ts.getStd()/ts.getMean_value()));
		    		  //System.out.println(ts.getStd()+"#"+ts.getMean_value());
		    		  
		    		  
		    		  //��20��Ϊ�ֽ��ߣ��������������20�룬Ϊ��ģ�����Ϊ-1������20�룬Ϊ���ģ�����Ϊ1��Ȼ����Щֵ�����ܣ�ȡ��ֵ
		    		  /*
		    		  double responsespeed = 0;
		    		  for(int w=0;w<Temp.length-1;w++){
		    			  if(timeShuzu[w]<20)responsespeed=responsespeed-1;
		    			  else if(timeShuzu[w]>20)responsespeed = responsespeed +1;
		    		  }
		    		  //responsespeed = responsespeed/(Temp.length-1);
		    		  System.out.println(responsespeed);
		    		  */
		    	  }
		    	  //System.out.println("Sig Number "+howmanysig);
		    	  //���е�����session��������лظ�ʱ��
		    	  //System.out.println("Total Tiny Reply: "+howmanytinyreply);
		    	  
		    	  DescriptiveStatistics descriptivestat = new DescriptiveStatistics();
		    	  for(int k=0;k<howmanytinyreply;k++){
		    		  descriptivestat.addValue(response_time_everyTiny[k]);
		    		  //System.out.println(response_time_everyTiny[k]);
		    	  }
		    	  
		    	  //System.out.println(descriptivestat.getPercentile(50));//�����λ��,ʵ���ϣ����session���� 20����λ���������20Ϊ���ޣ����ֿ���
		    	  
		    	  
		    	  
		    	  
		    	  book.close();   
		      }
		      catch(Exception e){
		    	  System.out.println(e.toString());
		      }
		      
		 }
	    
	    
	    public static void importExcel5(String fileName){
		      
		      try{
		    	  //System.out.println("File Name is "+fileName);
		    	  Workbook book = Workbook.getWorkbook(new File(fileName));
		    	  //D:\\Dropbox\\CI Technology Database\\IW 500 2013.xls
		    	  //System.out.println("--��ù�����");
		    	  Sheet sheet = book.getSheet(1);        // ��õڶ������������    
		    	  //System.out.println("��ù�����");
		    	  
		    	  int rows = sheet.getRows();  
		    	  
		    	  for(int i = 450; i < 500; i++) {   //k ����ж��ٸ���ͬ������
		    		  
		    		  String corpName = sheet.getCell(0, i).getContents();
		    		  System.out.print("\""+corpName+"\""+" OR ");
		       }
		    	  book.close();   
		      }
		      catch(Exception e){
		    	  System.out.println(e.toString());
		      }
		      
		    }
	    
	    
	    public static void main(String [] args){
	    	String getFile = "D:\\Dropbox\\Apps Data\\Table_VersionOther5WordTopReviews5.xls";
	    	/*
	    	importExcel5(getFile);
	    	importExcelTimeRegressoin(getFile);
	    	*/
	    	importExcel3(getFile);
	    }
	    
	    
	    

	   /* 
	   
	    public static void main(String [] args){
	    	  String No;
	    	  String Collector;
	    	  String RealName;
	    	  String Sex;
	    	  String Age;
	    	  String Education;
	    	  String Income;
	    	  String Job;
	    	  String OnlineTime;
	    	  String OPT1;
	    	  String OPT2;
	    	  String OPT3;
	    	  String OPT4;
	    	  String OPT5;
	    	  String OPT6;
	    	  String OPT7;
	       
	        System.out.println("��ʼ");
	        String errorlogfilepath = "d:/errorlog.txt";
	        String getFile = "D:\\Get.xls";
	        try{
				MySQLConn.connect();
				
				
		  }
		  catch(Exception e){
	  	   System.out.println("data connection is wrong "+e.toString());
	  	   WriteStreamAppend.method1(errorlogfilepath,"Transfer File exception is :"+e.toString()+"\r\n");
		 }
		  
	        Vector<DemographicInfoBean> vector = importExcel(getFile);
	        for(int i=0;i<vector.size();i++){
	        	No=vector.get(i).getNo();
	        	Collector=vector.get(i).getCollector();
	        	RealName = vector.get(i).getRealName();
	        	Sex = vector.get(i).getSex();
	        	Age = vector.get(i).getAge();
	        	Education = vector.get(i).getEducation();
	        	Income = vector.get(i).getIncome();
	        	Job = vector.get(i).getJob();
	        	OnlineTime = vector.get(i).getOnlineTime();
	        	OPT1 = vector.get(i).getOPT1(); 
	  	    	OPT2= vector.get(i).getOPT2();
		    	OPT3= vector.get(i).getOPT3();
		    	OPT4= vector.get(i).getOPT4();
		    	OPT5= vector.get(i).getOPT5();
		    	OPT6= vector.get(i).getOPT6();
		    	OPT7= vector.get(i).getOPT7();
	        	System.out.println(No+","+Collector+","+RealName+","+Sex+","+Age+","+Education+","+Income+","+Job+","+OnlineTime
	        			+","+OPT1+","+OPT2+","+OPT3+","+OPT4+","+OPT5+","+OPT6+","+OPT7);
	        	
	        	String sql = "update userinfo set Sex = '"+Sex+"', Age='"+Age+"', Education='"+Education+"', Income='"+Income+"', Job='"+Job+"', OnlineTime='"+OnlineTime+"', OPT1='"+OPT1+"', OPT2='"+OPT2+"', OPT3='"
	        	+OPT3+"', OPT4='"+OPT4+"', OPT5='"+OPT5+"', OPT6='"+OPT6+"', OPT7='"+OPT7+"' where datacollector='"+Collector+"' and realname='"+RealName+"'";
				System.out.println(sql);
	        	MySQLConn.update(sql);
	        	
	        }
	        try{
	  			MySQLConn.stmt.close();
	  			MySQLConn.con.close();
	  		}
	  			catch(Exception e){
	  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	  				System.out.println("Close MySQL is wrong");
	  		}
	  			
	  			
	  			
	  	
	  		  	 DemographicInfoBean d = new DemographicInfoBean();

	  		  	 try{
	  				MySQLConn.connect();
	  				String sql = "select * from userinfo where datacollector='������' and realname ='����L��'";
	  				MySQLConn.rs=MySQLConn.stmt.executeQuery(sql);
	  				if(MySQLConn.rs.next()){
	  					d.setCollector(MySQLConn.rs.getString("datacollector"));
	  					d.setRealName(MySQLConn.rs.getString("realname"));
	  					d.setSex(MySQLConn.rs.getString("Sex"));
	  					d.setAge(MySQLConn.rs.getString("Age"));
	  					d.setEducation(MySQLConn.rs.getString("Education"));
	  					d.setIncome(MySQLConn.rs.getString("Income"));
	  					d.setJob(MySQLConn.rs.getString("Job"));
	  					d.setOnlineTime(MySQLConn.rs.getString("OnlineTime"));
	  					d.setOPT1(MySQLConn.rs.getString("OPT1"));
	  					d.setOPT2(MySQLConn.rs.getString("OPT2"));
	  					d.setOPT3(MySQLConn.rs.getString("OPT3"));
	  					d.setOPT4(MySQLConn.rs.getString("OPT4"));
	  					d.setOPT5(MySQLConn.rs.getString("OPT5"));
	  					d.setOPT6(MySQLConn.rs.getString("OPT6"));
	  					d.setOPT7(MySQLConn.rs.getString("OPT7"));
	  				}
	  			  }
	  			  catch(Exception e){
	  		  	   System.out.println("data connection is wrong "+e.toString());
	  		  	   WriteStreamAppend.method1(errorlogfilepath,"Transfer File exception is :"+e.toString()+"\r\n");
	  			 }
	  			  
	  			  try{
	  		  			MySQLConn.stmt.close();
	  		  			MySQLConn.con.close();
	  		  		}
	  		  			catch(Exception e){
	  		  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	  		  				System.out.println("Close MySQL is wrong");
	  		  		}
	  		  			System.out.println("---------DDDDDDDDDDD-----------");
	  		  			System.out.println(d.getCollector()+d.getRealName()+d.getSex()+d.getAge());
     	    }
	    */
 
	       
}

class Person{
	String group_number;
	String student_name;
	String student_id;
	String performance;
	public Person(){
		
	}
	public Person(String number,String name, String id, String performance){
		this.setGroup_number(number);
		this.setStudent_name(name);
		this.setStudent_id(id);
		this.setPerformance(performance);
	}
	
	public String getGroup_number() {
		return group_number;
	}
	public void setGroup_number(String group_number) {
		this.group_number = group_number;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	} 
	
}
	   
