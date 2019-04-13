import java.util.*;

public class LCM {
  private static long lcm_naive(long a, long b) {
    long gcd = gcd_naive(a,b);
    return (long)((long)(a*b)/gcd);
  }
  private static long gcd_naive(long a, long b) {
    long remainder = 1;
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
    long a = scanner.nextLong();
    long b = scanner.nextLong();

    System.out.println(lcm_naive(a, b));
  }
}
