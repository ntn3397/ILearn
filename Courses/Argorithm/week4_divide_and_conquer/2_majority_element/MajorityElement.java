import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return a[left];
        }
        //write your code here
	int mid = left + (right - left) / 2;
	int majorityLeft = getMajorityElement(a,left,mid);
	int majorityRight = getMajorityElement(a, mid + 1, right);
	int countMajorityLeft = 0;
	int countMajorityRight = 0;
	int n = (right - left + 1)/2;
	if(majorityLeft != -1) {
	    for(int i = left; i <= right; i++){
		if(a[i] == majorityLeft) countMajorityLeft++;	
	    }
	}
	
	if(majorityRight != -1) {
	    for(int i = left ; i <= right ; i++){
		if(a[i] == majorityRight) countMajorityRight++;	 
	    }	
	}
	
	if(countMajorityLeft > countMajorityRight) {
	    if(countMajorityLeft > n) return majorityLeft;	
	}
	else if(countMajorityRight > countMajorityLeft) {
	    if(countMajorityRight > n) return majorityRight;	
	}
	else {
	    if(majorityRight == majorityLeft) return majorityLeft;
	    return -1;
	} 
	
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length -1) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

