import java.util.*;

public class DotProduct {
    private static long maxDotProduct(long[] a, long[] b) {
        //write your code here
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            long maxA = a[i];
            int idxMaxA = i;
            long maxB = b[i];
            int idxMaxB = i;
            for(int j = i + 1; j < a.length; j++) {
                if(maxA < a[j]) {
                    maxA = a[j];
                    idxMaxA = j;
                }
                if(maxB < b[j]) {
                    maxB = b[j];
                    idxMaxB = j;
                }
            }
            long tempA = a[i];
            a[i] = a[idxMaxA];
            a[idxMaxA] = tempA;
            long tempB = b[i];
            b[i] = b[idxMaxB];
            b[idxMaxB] = tempB;

            result += maxA*maxB;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextLong();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

