import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        // int num = 1;
        // while(n!=0) {
        //     if(n-num < num) {
        //         summands.add(n);
        //         n-=n;
        //     } else {
        //         summands.add(num);
        //         n-=num;
        //         num++;
        //     }
        // }
        for(int num = 1; num <= n; num++) {
            if(n-num <= num) {
                summands.add(n);
                n-=n;
                break;
            }
            summands.add(num);
            n-=num;
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

// 8 is n
//1 2 5
//num = 1;
// 8 - num = 7
// sumNum = 1 + 2;
// 7 - num = 5
// 5 is n
// num is 3
//
