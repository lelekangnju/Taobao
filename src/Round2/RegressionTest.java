package Round2;
import org.apache.commons.math3.stat.regression.*;
import org.apache.commons.math3.stat.descriptive.*;
public class RegressionTest {
     public static void main(String argus[]){
    	 
    	 /*
    	 double[][] data = { { 1, 3 }, {2, 5 }, {3, 7 }, {4, 14 }, {5, 11 }};
    	 double[][] data2 = {{1,2},{2,4},{3,5},{4,8}};
    	 
    	 SimpleRegression regression = new SimpleRegression();
    	 regression.addData(data2);
    	 
    	 System.out.println(regression.getIntercept());
    	 System.out.println(regression.getSlope()); //Slope
    	 System.out.println(regression.getSignificance());//Returns the significance level of the slope (equiv) correlation.
         */
    	 
    	 double aa[] = {2,4,6,8,9,12,14,16};
    	 TimeResults tr = CalculateRegression(aa);
    	 
    	 System.out.println(tr.getStd());
    	 System.out.println(tr.getIntercept_result());
    	 System.out.println(tr.getSlop_sig());
    	 System.out.println(tr.getSlope_result());
     
     }
     
     public static TimeResults CalculateRegression(double time[]){
    	 TimeResults saveresults = new TimeResults();
    	 
    	 int col = time.length;
    	 double[][] twocolData = new double[col][2];
    	 SimpleRegression regression = new SimpleRegression();
    	 DescriptiveStatistics descriptivestat = new DescriptiveStatistics();
    	 
    	 for(int i=0;i<time.length;i++){
    		 twocolData[i][0]=i+1;
    		 twocolData[i][1]=time[i];
    		 
    		 descriptivestat.addValue(time[i]);
    	 }
    	 /*  	 
    	 for(int i=0;i<time.length;i++){
    		 System.out.println(twocolData[i][0]+","+twocolData[i][1]);
    	 }
    	 */
    	 //Run regression
    	 regression.addData(twocolData);
    	 saveresults.setIntercept_result(regression.getIntercept());
    	 saveresults.setSlope_result(regression.getSlope());
    	 saveresults.setSlop_sig(regression.getSignificance());
         
    	 //Get Descriptive results
    	 saveresults.setStd(descriptivestat.getStandardDeviation());
    	 saveresults.setMean_value(descriptivestat.getMean());
         
    	 
    	 return saveresults;
     }
}

class TimeResults {
	
	double Intercept_result;// Intercept
	double Slope_result;//Slope 
	double Slop_sig;//Returns the significance level of the slope (equiv) correlation.
	double std; //Standard Deviation of the set of raw values
	double mean_value; //mean of the set of raw values
	
	
	
	
	public double getMean_value() {
		return mean_value;
	}
	public void setMean_value(double mean_value) {
		this.mean_value = mean_value;
	}
	public double getStd() {
		return std;
	}
	public void setStd(double std) {
		this.std = std;
	}
	public double getIntercept_result() {
		return Intercept_result;
	}
	public void setIntercept_result(double intercept_result) {
		Intercept_result = intercept_result;
	}
	public double getSlope_result() {
		return Slope_result;
	}
	public void setSlope_result(double slope_result) {
		Slope_result = slope_result;
	}
	public double getSlop_sig() {
		return Slop_sig;
	}
	public void setSlop_sig(double slop_sig) {
		Slop_sig = slop_sig;
	}
	
	
	
}
