import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] itemW) {
        //write you code here
        // int result = 0;
        // for (int i = 0; i < w.length; i++) {
        //   if (result + w[i] <= W) {
        //     result += w[i];
        //   }
        // }
        // return result;
        //
        int[][] value = new int[itemW.length+1][W+1];

        for(int i = 0; i <= itemW.length; i++) {
            value[i][0] = 0;
        }
        for(int w = 1; w <= W; w++) {
            value[0][w] = 0;
        }
        for(int i = 1; i <= itemW.length; i++) {
            for (int w = 1; w <= W; w++) {
                value[i][w] = value[i-1][w];
                if(itemW[i-1] <= w) {
                    int V = value[i-1][w-itemW[i-1]] + itemW[i-1];
                    if(V > value[i][w]){
                        value[i][w] = V;
                    }
                }
            }
        }
        return value[itemW.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

