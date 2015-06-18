package Round2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Round2.MySQLConn;

public class ReadTxtEachLine {
	
	
	public static void main(String[] args){
		  //以下是对文件的处理过程,将用户的初步的file，处理成为一个完善的file，特别是换行问题
		
		 try{
				MySQLConn.connect();
		  }
		  catch(Exception e){
	  	   System.out.println("data connection is wrong "+e.toString());
		 }
		  
		  File file = new File("E:/Lele/Taobao/pathEachLine.txt");
		  String errorlogfilepath = "e:/Lele/Taobao/pathEachLineError.txt";
		  String tempString = "";
	      BufferedReader reader = null;
	      BufferedReader reader2 = null;
	      String sql1= "";
	      int w=0;//w用来表示一共有多少个dialog
	      int startrecord[] = new int[5000] ;
	      String printpath = "E:/Lele/Taobao/CombinedEachLine.txt";
	      
	      try {
	          //System.out.println("以行为单位读取文件内容，一次读一整行：");
	          reader = new BufferedReader(new FileReader(file));
	          int line = 1;// 一次读入一行，直到读入null为文件结束
	          int xyz = 0;
	          while ((tempString = reader.readLine()) != null) {
	        	  
	        	  if("20150617".equalsIgnoreCase(tempString.split("_")[0])){
	        		  System.out.println(tempString);
	        		  startrecord[xyz]=line;
	        		  xyz++;
	        	  }
	        	  line++;
	           }
	          startrecord[xyz]=line;
	          
	          for(int ppp=0;ppp<xyz;ppp++){
	        	  System.out.println("Start Line " + startrecord[ppp]);
	          }
	          
	          
  		  String variablenames[] = new String[1000];
  		  int variablenumber =0;
  		  
	          for(int j=0;j<xyz;j++){
	        	  
	        	  //System.out.println(startrecord[j]);
	        	  reader2 = new BufferedReader(new FileReader(file));
      		  for(int iii=0;iii<startrecord[j]-1;iii++){
      			  reader2.readLine();
      		  }
      		  int rrr=0;
      		  String onerecord[] = new String[startrecord[j+1]-startrecord[j]];//每个记录不超过1000行
      		  for(;rrr<startrecord[j+1]-startrecord[j];rrr++){
      			  onerecord[rrr] = reader2.readLine();
      		  }
      		  //Deal with one record
      		String randomstring = onerecord[0];// RandomString
      		String results[][] = Analyzeonerecord(onerecord);
      		WriteStreamAppend.method1(printpath,randomstring+"\r\n");
      		for(int i=0; i<results.length; i++){
     			String temp = "";
     			WriteStreamAppend.method1(printpath, results[i][0]+", "+ results[i][1]+", "+results[i][2]+", "+results[i][3]+
     					", "+results[i][4]+", "+results[i][5]+", "+results[i][6]+", "+results[i][7]+", "+results[i][8]+", "
     					+results[i][9]+", "+results[i][10]+", "+results[i][11]+"\r\n");
     		}
      		WriteStreamAppend.method1(printpath,"\r\n\r\n\r\n");
      		  
      		  
	         }//end one specific record
	         
	        
		    reader.close();
	        try{
	  			MySQLConn.stmt.close();
	  			MySQLConn.con.close();
	  		}
	  			catch(Exception e){
	  				WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	  				System.out.println("Close MySQL is wrong");
	  		}
	          
	          
	      
			  } catch (IOException e) {
				  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e.toString()+"\r\n");
	              e.printStackTrace();
	      } finally {
	          if (reader != null) {
	              try {
	                  reader.close();
	              } catch (IOException e1) {
	            	  WriteStreamAppend.method1(errorlogfilepath,"exception is :"+e1.toString()+"\r\n");
	              }
	          }
	      }
	  }
	
	
       public static String[][] Analyzeonerecord(String onerecord[]){
		
		String result[]=onerecord;
		String temp = "";
 		int dynamiclength = onerecord.length;
 		String arrangedrecord[][] = new String[dynamiclength][12];
 		int arrangeline =0;
 		String pirorWho = "";
 		for(int i=1;i<dynamiclength;i++){
 			if("".equals(onerecord[i])|"\r\n".equals(onerecord[i]))break;
			temp = onerecord[i];
			String allinfo[] = SplitOneLine(temp);
			if(!pirorWho.equals(allinfo[2])){
				arrangedrecord[arrangeline++]=allinfo;
				pirorWho = allinfo[2];
				System.out.println(allinfo[2]);
			}else{
				System.out.println("Runs");
				pirorWho = allinfo[2];
				arrangedrecord[arrangeline-1]=CombineTwoLines(arrangedrecord[arrangeline-1],allinfo);
				//Combine Two Lines
			}
		}
 		String returnValue[][] = new String[arrangeline][12];
 		for(int w=0;w<arrangeline;w++){
 			returnValue[w] = arrangedrecord[w];
 		}
 			
		return returnValue;
	}
	
	
	public static String[] SplitOneLine(String oneline){
		String allinfo[] = new String[12];
		//LineCombine, LineNumber,Who,WordNumber,then 8 variables
		allinfo[0] = "1";
		allinfo[1] = oneline.split(", ")[0].split(":")[1].trim();
		allinfo[2] = oneline.split(", ")[1].split(": ")[0].trim();
		allinfo[3] = oneline.split("\\[")[1].split("\\]")[0].trim();
		allinfo[4] = oneline.split("Emotional Words\\[")[1].split("\\,")[0].trim();
		allinfo[5] = oneline.split("Emotional Words\\[")[1].split("\\,")[1].trim();
		allinfo[6] = oneline.split("Emotional Words\\[")[1].split("\\,")[2].trim();
		allinfo[7] = oneline.split("Emotional Words\\[")[1].split("\\,")[3].trim();
		allinfo[8] = oneline.split("Emotional Words\\[")[1].split("\\,")[4].trim();
		allinfo[9] = oneline.split("Emotional Words\\[")[1].split("\\,")[5].trim();
		allinfo[10] = oneline.split("Emotional Words\\[")[1].split("\\,")[6].trim();
		allinfo[11] = oneline.split("Emotional Words\\[")[1].split("\\,")[7].split("\\]")[0].trim();
		return allinfo;
	}
	
	public static String[] CombineTwoLines(String priorVector[], String thisVector[]){
		String combined[] = new String[12];
		//LineCombine, LineNumber,Who,WordNumber,then 8 variables
		
		combined[0] = Integer.toString(Integer.parseInt(priorVector[0])+1);
		combined[1] = priorVector[1];
		combined[2] = priorVector[2];
		combined[3] = Integer.toString(Integer.parseInt(priorVector[3])+Integer.parseInt(thisVector[3]));
		combined[4] = Integer.toString(Integer.parseInt(priorVector[4])+Integer.parseInt(thisVector[4]));
		combined[5] = Integer.toString(Integer.parseInt(priorVector[5])+Integer.parseInt(thisVector[5]));
		combined[6] = Integer.toString(Integer.parseInt(priorVector[6])+Integer.parseInt(thisVector[6]));
		combined[7] = Integer.toString(Integer.parseInt(priorVector[7])+Integer.parseInt(thisVector[7]));
		combined[8] = Integer.toString(Integer.parseInt(priorVector[8])+Integer.parseInt(thisVector[8]));
		combined[9] = Integer.toString(Integer.parseInt(priorVector[9])+Integer.parseInt(thisVector[9]));
		combined[10] = Integer.toString(Integer.parseInt(priorVector[10])+Integer.parseInt(thisVector[10]));
		combined[11] = Integer.toString(Integer.parseInt(priorVector[11])+Integer.parseInt(thisVector[11]));
		
		return combined;
		
	}
	
}
