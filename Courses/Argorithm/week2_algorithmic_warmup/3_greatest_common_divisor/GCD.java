import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int remainder = 1;
    while (true) {
      if(a>b){
        remainder = a%b;
        if(remainder == 0) return b;
        a = b;
        b = remainder;
      } else {
        remainder = b%a;
        if(remainder == 0) return a;
        b = remainder;
      }
    }
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd_naive(a, b));
  }
}
