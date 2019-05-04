import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int number) {
        List<Integer> sequence = new ArrayList<Integer>();

        double[] minOperations = new double[number+1];

        minOperations[1] = 0;
        for(int n = 2; n <= number; n++) {
            minOperations[n] = Double.POSITIVE_INFINITY;
            if(n%3==0){
                double numOperations = minOperations[n/3] + 1;
                if (numOperations < minOperations[n]) {
                    minOperations[n] = numOperations;
                } 
            }
            if (n%2 == 0) {
                double numOperations = minOperations[n/2] + 1;
                if (numOperations < minOperations[n]) {
                    minOperations[n] = numOperations;
                }
            }
            double numOperations = minOperations[n-1] + 1;
            if (numOperations < minOperations[n]) {
                minOperations[n] = numOperations;
            }
            
        }
        // for(int i = 1; i <= number; i++) {
        //     System.out.print(i+" ");        }
        // System.out.println();
        // for(int i = 1; i <= number; i++) {
        //     System.out.print((int)minOperations[i]+ " ");
        // }

        while (number >=1) {
            sequence.add(number);
            if(number%3==0 && minOperations[number] == minOperations[number/3]+1 ) {
                number/=3;
            } else if ( number%2==0 && minOperations[number] == minOperations[number/2]+1 ) {
                number/=2;
            } else  {
                number-=1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

