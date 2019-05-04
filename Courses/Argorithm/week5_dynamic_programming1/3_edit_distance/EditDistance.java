import java.util.*;

class EditDistance {
  // public static void OutputAlignment(int i, int j, int[][] d,String s, String t) {
  //   if (i == 0 && j == 0) return;
  //   int diff;
  //   if(s.charAt(i-1) == t.charAt(j-1)){
  //     diff = 0;
  //   } else {
  //     diff = 1;
  //   }
  //   if(i > 0 && d[i][j] == d[i-1][j]+1) {
  //     OutputAlignment(i-1,j,d,s,t);
  //     System.out.println(s.charAt(i-1));
  //     System.out.println("-");
  //   } else if (j > 0 && d[i][j] == d[i][j-1]+1) {
  //     OutputAlignment(i,j-1,d,s,t);
  //     System.out.println("-");
  //     System.out.println(t.charAt(j-1));
  //   } else if (j > 0 &&  i > 0 && d[i][j] == d[i-1][j-1] + diff){
  //     OutputAlignment(i-1,j-1,d,s,t);
  //     System.out.println(s.charAt(i-1));
  //     System.out.println(t.charAt(j-1));
  //   }
  // }
  
  public static int EditDistance(String s, String t) {
    //write your code here
    int n = s.length();
    int m = t.length();
    int[][] d = new int [n+1][m+1];

    for(int i = 0; i <= n; i++)
    {
      d[i][0] = i;
    }
    for(int j = 0; j <= m; j++)
    {
      d[0][j] = j;
    }
    for(int j = 1; j <=m; j++) {
      for(int i = 1; i <=n; i++) {
        int insertion = d[i][j-1] + 1;
        int deletion = d[i-1][j] + 1;
        int match = d[i-1][j-1];
        int mismatch = d[i-1][j-1]+1;
        if (s.charAt(i-1)==t.charAt(j-1)) {
          int result = Math.min(insertion, deletion);
          result = Math.min(result,match);
          d[i][j] = result;
        } else {
          int result = Math.min(insertion,deletion);
          result = Math.min(result,mismatch);
          d[i][j] = result;
        }
      }
    }

    // for(int i = 0; i <=n; i++) {
    //   for(int j = 0; j <=m; j++) {
    //     System.out.print(d[i][j]+ " ");
    //   }
    //   System.out.println();
    // }
    //OutputAlignment(n,m,d,s,t);
    return d[n][m];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
