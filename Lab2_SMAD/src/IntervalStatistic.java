import java.util.List;

public class IntervalStatistic {
	static double sum(List<Double> arr) {
		double sum=0;
		for(double n:arr) {
			sum+=n;
		}
		return sum;
	}
	static double averStat(List<Double> arr) {
		double aver=sum(arr)/arr.size();
		return aver;
	}
	static double mode(List<Double> arr) {
		Double maxN=1.0, maxEl=-1.0;
		int n=0;
		for(int i=0; i<arr.size();i++) {
			for(int j=0; j<arr.size();j++) {
				if(arr.get(j)==arr.get(i)) {
					n++;
				}
			}
			if(n>maxN) {
				maxN=(double)n;
				maxEl=arr.get(i);
			}
			n=0;
		}
		return maxEl;
	}
	static double intervalMode(double[][] arr) {
		int interval=0;
		double max=0, mode;
		for(int i=0; i<arr.length; i++) {
			if(arr[i][2]>max) {
				max=arr[i][2];
				interval = i;
			}
				
		}
		
		mode = arr[interval][0]+(((arr[interval][2]-arr[interval-1][2])/(2*arr[interval][2]-arr[interval-1][2]-arr[interval+1][2]))*(arr[interval][1]-arr[interval][0]));
		return mode;
	}
	static double median(double[][] arr, double[][] accumulativeArr) {
		double median=0;
		int interval = (int)arr.length/2;
		median = arr[interval][0]+(arr[interval][1]-arr[interval][0])*((accumulativeArr[accumulativeArr.length-1][2]/2-accumulativeArr[interval-1][2])/(arr[interval][2]));
	//	median = arr[interval][0]+(arr[interval][1]-arr[interval][0])*((0.5-accumulativeArr[interval-1][2])/(accumulativeArr[interval][2]-accumulativeArr[interval-1][2]));

		return median;
	}
	static double range(double[][] arr) {
		double range=0;
		range = arr[arr.length-1][1]- arr[0][0];
		return range;
	}
	static double dispersia(List<Double> arr) {
		double aver = averStat(arr);
		double dispersia=0;
		for(double n:arr) {
			dispersia+=Math.pow(n-aver, 2);
		}
		dispersia/=arr.size();
		return dispersia;
	}
	static double standardError(List<Double> arr) {
		double standardError=Math.sqrt(dispersia(arr));
		return standardError;
	}
	static double vyprDispersia(List<Double> arr) {
		double vyprDispersia = dispersia(arr)*arr.size()/(arr.size()-1);
		return vyprDispersia;
	}
	static double vyprStandardError(List<Double> arr) {
		return Math.sqrt(vyprDispersia(arr));
	}
	static double variation(List<Double> arr) {
		return standardError(arr)/averStat(arr);
	}
	static double startMoment(List<Double> arr, int order) {
		double startMoment=0;
		for(var i=0; i<arr.size(); i++){
			startMoment+=Math.pow(arr.get(i),order);
			 }
		startMoment/=arr.size();
		return startMoment;
	}
	static double centralMoment(List<Double> arr, int order) {
		double centralMoment=0;
		double aver = averStat(arr);
		for(var i=0; i<arr.size(); i++){
			centralMoment+=Math.pow(arr.get(i)-aver,order);
			 }
		centralMoment/=arr.size();
		return centralMoment;
	}
	static double assymetry(List<Double> arr){
		double assymetry = centralMoment(arr,3)/Math.pow(standardError(arr),3);
		if(assymetry<0) {
			System.out.println("Крива розподілу є зсунутою праворуч"); 
		}else {
			System.out.println("Крива розподілу є зсунутою ліворуч"); 
		}
		 return assymetry;
	}
	static double excess(List<Double> arr){
		double excess = centralMoment(arr,4)/Math.pow(standardError(arr),4)-3;

		 return excess;
	}
}
