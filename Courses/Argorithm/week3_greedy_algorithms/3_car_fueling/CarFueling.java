import java.util.*;
import java.io.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
	int numRefills = 0;
	int currentRefill = 0;
	int[] allPoints = new int[stops.length+2];
	int n = allPoints.length;
	allPoints[0] = 0;
	allPoints[n-1] = dist;
	System.arraycopy(stops,0,allPoints,1,stops.length);
	while (currentRefill <= n - 2) {
		int lastRefill = currentRefill;
		while ((currentRefill <= n - 2) && ((allPoints[currentRefill + 1] - allPoints[lastRefill]) <= tank)) {
			currentRefill = currentRefill + 1;		
		}
		if (currentRefill == lastRefill) {
			return -1;		
		}	
		if(currentRefill <= n - 2) {
			numRefills = numRefills + 1;		
		}
	}
        return numRefills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
