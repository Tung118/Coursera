import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        checkPoint(points);
        lineSegments = new ArrayList<LineSegment>();
        Point[] cpy = Arrays.copyOf(points, points.length);
        for (int i=0;i<points.length;i++) {
            Arrays.sort(cpy, points[i].slopeOrder());
            double slope = points[i].slopeTo(cpy[0]);
            int count = 1, j;
            for (j = 1; j < cpy.length; j++) {
                if (points[i].slopeTo(cpy[j]) == slope) {
                    count++;
                }
                else {
                    if (count >= 3) {
                        Arrays.sort(cpy, j - count, j);
                        if (points[i].compareTo(cpy[j - count]) < 0) {
                            lineSegments.add(new LineSegment(points[i], cpy[j - 1]));
                        }
                    }
                    slope = points[i].slopeTo(cpy[j]);
                    count = 1;
                }
            }
            if (count >= 3) {
                Arrays.sort(cpy, j - count, j);
                if (points[i].compareTo(cpy[j - count]) < 0)
                    lineSegments.add(new LineSegment(points[i], cpy[j - 1]));
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    private void checkPoint(Point[] points){
        if(points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i ++) {
            if(points[i] == null) {
                throw new IllegalArgumentException();
            }
            for(int j = i+1; j < points.length; j++) {
                if(points[j] == null) throw new IllegalArgumentException();
                if(points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}