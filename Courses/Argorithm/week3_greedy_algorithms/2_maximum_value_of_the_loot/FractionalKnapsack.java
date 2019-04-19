import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;
import java.text.DecimalFormat;
public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights, int n) {
        double value = 0;
        //write your code here
	double[] sortedUnitValue = sort(values, weights,n);
	for(int i = 0; i < n; i++)
	{
		if(capacity==0) return (double) value;
		int a = Math.min(capacity,weights[i]);
		value += a*sortedUnitValue[i];
		weights[i] = weights[i] - a;
		capacity = capacity - a;
	}
	
        return (double) value;
    }

    private static double[] sort(int[] values, 	int[] weights, int n){
    	double[] result = new double[n];
	for(int i = 0; i < n; i++ ){
		result[i] = (double) values[i]/weights[i];
	}
	for(int i = 0; i < n-1; i++){
		int max = i;
		double maxValue = result[i];
		for(int j = i + 1; j < n; j++) {
			if(maxValue < result[j]) {
				max = j;
				maxValue = result[j];			
			}
		}
		double temp = result[i];
		result[i] = maxValue;
		result[max] = temp;
		int temp1 = values[i];
		values[i] = values[max];
		values[max] = temp1;
		temp1 = weights[i];
		weights[i] = weights[max];
		weights[max] = temp1;		
	}
	return result;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
	DecimalFormat df = new DecimalFormat("#.####");
        System.out.println(df.format(getOptimalValue(capacity, values, weights,n)));
    }
} 
