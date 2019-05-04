import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int money) {
        //write your code here
        double[] minNumCoins = new double[money+1];
        int[] coins = {4,3,1};
        minNumCoins[0] = 0;
        for (int m = 1; m <= money; m++) {
        	minNumCoins[m] = Double.POSITIVE_INFINITY;
        	for(int i : coins) {
        		if(m >= i) {
        			double numCoins = minNumCoins[m - i] + 1;
        			if(numCoins < minNumCoins[m]) {
        				minNumCoins[m] = numCoins;
        			}
        		}
        	}
        }
        return (int)minNumCoins[money];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

