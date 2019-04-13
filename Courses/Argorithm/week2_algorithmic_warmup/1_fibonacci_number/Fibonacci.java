import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(long n) {
    if (n <= 1)
     return n;
    long first = 0;
    long second = 1;
    long result = 0;
    for (long i = 2; i <= n; i++) {
      result = first + second;
      first = second;
      second = result;
    }
    return result;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    long n = in.nextLong();
    System.out.println(calc_fib(n));
  }
}
