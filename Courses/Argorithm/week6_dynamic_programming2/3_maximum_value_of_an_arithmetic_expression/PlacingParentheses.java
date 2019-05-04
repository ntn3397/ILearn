import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      //write your code here
        int n = exp.length()/2 + 1;
        int[] d = new int[n];
        StringBuilder op = new StringBuilder();
        int count = 0;
        for(int i = 0; i < exp.length(); i++) {

            if(i%2!=0) {
                op.append(exp.charAt(i));
            } else {
                d[count] = Character.getNumericValue(exp.charAt(i));
                count++;
            }
        }

        long M[][] = new long[n+1][n+1];
        long m[][] = new long[n+1][n+1];
        //make matrix start at 1, not 0.
        for(int i = 1; i<=n; i++) {
            M[i][i] = d[i-1];
            m[i][i] = d[i-1];
        }

        for(int s = 1; s <= n-1; s++){
            for(int i = 1; i <= n-s; i++) {
                int j = i + s;
                long[][] minMax = new long[1][2];
                minMax = minAndMax(i,j,m,M,op.toString());
                long min = minMax[0][0];
                long max = minMax[0][1];
                m[i][j] = min;
                M[i][j] = max;
            }
        }
        return M[1][n];
    }

    private static long[][] minAndMax(int i, int j,long m[][], long M[][],String op) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long [][] result = new long[1][2];
        for(int k = i; k <= j-1; k++) {
            long a = eval(M[i][k], M[k+1][j], op.charAt(k-1));
            long b = eval(M[i][k], m[k+1][j], op.charAt(k-1));
            long c = eval(m[i][k], M[k+1][j], op.charAt(k-1));
            long d = eval(m[i][k], m[k+1][j], op.charAt(k-1));
            List<Long> minValue = new ArrayList<Long>();
            List<Long> maxValue = new ArrayList<Long>();
            minValue.add(min);
            minValue.add(a);
            minValue.add(b);
            minValue.add(c);
            minValue.add(d);

            maxValue.add(max);
            maxValue.add(a);
            maxValue.add(b);
            maxValue.add(c);
            maxValue.add(d);

            min = Collections.min(minValue);
            max = Collections.max(maxValue);
            result[0][0] = min;
            result[0][1] = max;
        }
        return result;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

