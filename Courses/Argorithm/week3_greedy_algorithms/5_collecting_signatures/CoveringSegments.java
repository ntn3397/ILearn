import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        
        for(int i = 0; i < segments. length - 1; i++) {
            int min = segments[i].end;
            int minIdx = i;
            for(int j = i+1; j < segments.length; j++){
                if(min > segments[j].end) {
                    min = segments[j].end;
                    minIdx = j;
                }
            }
            Segment temp = segments[i];
            segments[i] = segments[minIdx];
            segments[minIdx] = temp;
        }
        // for (int i = 0; i < segments.length; i++) {
        //     points[2 * i] = segments[i].start;
        //     points[2 * i + 1] = segments[i].end;
        // }

        // return points;

        Vector<Integer> pointsVector = new Vector<Integer>();
        int curr = 0;
        int count = 0;
        while(curr < segments.length) {
            Segment currSegment = segments[curr];
            int next = curr + 1;
            //points[count] = currSegment.end;
            pointsVector.add(currSegment.end);
            count++;
            while (next < segments.length){
                if(currSegment.end >= segments[next].start) {
                    next++;
                } else {
                    break;
                }
            }
            curr = next;
            
        }

        int size = pointsVector.size();
        int[] points = new int[size];
        Integer[] pointsArray = pointsVector.toArray(new Integer[size]);
        for(int i = 0; i < size; i++){
            points[i] = pointsArray[i];
        }
        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
