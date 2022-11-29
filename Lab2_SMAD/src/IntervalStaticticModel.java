import java.util.ArrayList;
import java.util.Arrays;

public class IntervalStaticticModel {

	private double[][] intervals;
	private double intervalWidth =0;
	private int r;
	private ArrayList<Double> discretArrTotal = new ArrayList<>();
	private double[][]discretArr;
	private double max,min;
	double [][] accumulatedFrequency;
	public IntervalStaticticModel(ArrayList<Double> arr) {
		int arrN = arr.size();
		ArrayList<Double> tempArr= new ArrayList<>();
		for(Double n : arr) {
			if(!tempArr.contains(n)) {
				tempArr.add(n);
			}
		}
		arr.sort(null);
		 min = arr.get(0);
		 max = arr.get(arrN-1);
		r=(int) Math.floor(1+3.322*Math.log10(arr.size()));
		System.out.println(r);
		intervalWidth = (max-min)/r;
		intervals = new double[r][3];
		for(int i=0; i<r;i++) {
			intervals[i][0]=min+intervalWidth*i;
			intervals[i][1]=min+intervalWidth*(i+1);
			intervals[i][2]=0;
			//System.out.println(intervals[i][0]+" "+intervals[i][1]);
		}
		for(Double n: arr) {
			for(int i=0; i<r;i++) {
				if(((n>intervals[i][0])||(i==0&&n>=intervals[i][0]))&&n<=intervals[i][1]) {
					intervals[i][2]++;
				}
			}
		}
		for(int i=0; i<r;i++) {
			System.out.println(intervals[i][0]+" "+intervals[i][1]+" "+intervals[i][2]);
		}
		discretArr = new double[r][2];
		for(int i=0; i<r;i++) {
			discretArr[i][0]=(intervals[i][0]+intervals[i][1])/2;
			discretArr[i][1]=intervals[i][2];
			for(int j=0; j<discretArr[i][1];j++) {
				discretArrTotal.add(discretArr[i][0]);
			}
			System.out.println(discretArr[i][0]+" "+discretArr[i][1]);
		}
		for(Double d:discretArrTotal) {
			System.out.print(d+" ");
		}
		accumulatedFrequency = new double[intervals.length][3];
    	for(int i=0; i<intervals.length;i++) {
    		accumulatedFrequency[i][0]=intervals[i][0];
    		accumulatedFrequency[i][1]=intervals[i][1];
    		if(i!=0)
    			accumulatedFrequency[i][2]= accumulatedFrequency[i-1][2]+this.getDiscretArr()[i][1];
    		else
    			accumulatedFrequency[i][2]= this.getDiscretArr()[i][1];
    	}
	}
	public double[][] getIntervals() {
		return intervals;
	}
	public void setIntervals(double[][] intervals) {
		this.intervals = intervals;
	}
	public double getIntervalWidth() {
		return intervalWidth;
	}
	public void setIntervalWidth(double intervalWidth) {
		this.intervalWidth = intervalWidth;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public ArrayList<Double> getDiscretArrTotal() {
		return discretArrTotal;
	}
	public void setDiscretArrTotal(ArrayList<Double> discretArrTotal) {
		this.discretArrTotal = discretArrTotal;
	}
	public double[][] getDiscretArr() {
		return discretArr;
	}
	public void setDiscretArr(double[][] discretArr) {
		this.discretArr = discretArr;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
}
