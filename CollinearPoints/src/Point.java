import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw() {
        StdDraw.point(x,y);
    }
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public int compareTo(Point that) {
        if (y > that.y) return 1;
        if (y == that.y && x > that.x) return 1;
        if (y==that.y && x == that.x) return 0;
        return -1;
    }
    public double slopeTo(Point that) {
        if (this.x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        else if(x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if(y == that.y) return 0;
        return (double) (that.y - this.y) / (that.x - this.x);
    }
    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();
    }
    private class SlopeComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
        }

    }
}
