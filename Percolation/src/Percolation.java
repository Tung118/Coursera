import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int TOP = 0;
    private final int BOT;
    private final int n;
    private int numOpenSite;
    private final boolean[][] Node;
    private WeightedQuickUnionUF qu;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        numOpenSite = 0;
        BOT = n*n + 1;
        Node = new boolean[n][n];
        qu = new WeightedQuickUnionUF(n*n +2);
    }
    public int index(int row,int col) {
        return n*(row-1) +col;
    }
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        Node[row-1][col-1] = true;// o mau trang
        numOpenSite++; // Dem so luong o mau trang

        if(row == 1) qu.union(index(row,col),TOP);
        if(row == n) qu.union(index(row,col),BOT);
        if(row > 1 && isOpen(row - 1, col)) {
            qu.union(index(row,col),index(row-1,col));
        }
        if(row < n && isOpen(row+1,col)) {
            qu.union(index(row,col),index(row+1,col));
        }
        if(col > 1 && isOpen(row,col-1)) {
            qu.union(index(row,col),index(row,col-1));
        }
        if(col < n && isOpen(row,col+1)) {
            qu.union(index(row,col),index(row,col+1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        return Node[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        return qu.find(TOP) == qu.find(index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return qu.find(TOP) == qu.find(BOT);
    }
}