import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point pt : s.getPoints()) {
            count ++;
        }
        return count;
    }
    
    public double getAverageLength(Shape s) {
        double totalPerim = getPerimeter(s);
        int numPoints = getNumPoints(s);
        return totalPerim / numPoints;
    }
    
    public double getLargestSide (Shape s) {
        double maxDist = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > maxDist) maxDist = currDist;
            prevPt = currPt;
        }
        return maxDist;
    }
    
    public double getLargestX (Shape s) {
        double maxX = 0;
        Point prevPt = s.getLastPoint();
        for(Point Pt : s.getPoints()){
            if(Pt.getX() > maxX) maxX = Pt.getX();
        }
        return maxX;
    }
    

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("numbers of points = " + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("average length = " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }
    

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
