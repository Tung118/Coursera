import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        checkPoint(points);
        ArrayList<LineSegment> Segments = new ArrayList<LineSegment>();
        Point[] cpy = Arrays.copyOf(points, points.length);
        for (Point point : points) {
            Arrays.sort(cpy, point.slopeOrder());
            double slope = point.slopeTo(cpy[0]);
            int count = 1, i;
            for (i = 1; i < cpy.length; i++) {
                if (point.slopeTo(cpy[i]) == slope) {
                    count++;
                    continue;
                }
                else {
                    if (count >= 3) {
                        Arrays.sort(cpy, i - count, i);
                        if (point.compareTo(cpy[i - count]) < 0) {
                            Segments.add(new LineSegment(point, cpy[i - 1]));
                        }
                    }
                    slope = point.slopeTo(cpy[i]);
                    count = 1;
                }
            }
            if (count >= 3) {
                Arrays.sort(cpy, i - count, i);
                if (point.compareTo(cpy[i - count]) < 0)
                    Segments.add(new LineSegment(point, cpy[i - 1]));
            }
        }
        lineSegments = Segments.toArray(new LineSegment[Segments.size()]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
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