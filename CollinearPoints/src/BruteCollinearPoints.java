import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private List<LineSegment> lineSegmentList;

    public BruteCollinearPoints(Point[] points) {
        checkPoint(points);

        lineSegmentList = new ArrayList<>();
        this.points = points.clone();
        Arrays.sort(this.points);
        for(int i=0;i<this.points.length-3;i++) {
            for(int j=i;j<this.points.length-2;j++) {
                for(int k=j;k<this.points.length-1;k++) {
                    for(int l=j;l<this.points.length;l++) {
                        if(this.points[i].slopeTo(this.points[j]) == this.points[j].slopeTo(this.points[k]) &&
                                this.points[j].slopeTo(this.points[k]) == this.points[k].slopeTo(this.points[l])) {

                            lineSegmentList.add(new LineSegment(this.points[i], this.points[l]));
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments() {
        return lineSegmentList.size();
    }
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }
    private void checkPoint(Point[] points){
        if(points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i ++) {
            for(int j = i; j < points.length; j++) {
                if(points[i] == null) {
                    throw new IllegalArgumentException();
                }
                if(i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}