package com.company;

public class Percolation {
    private boolean[][] sites;
    private int n;
    private int openSites = 0;
    private UnionFind union;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        union = new UnionFind(n * n + 2);
        sites = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            sites[row][col] = true;
            openSites++;
            int[][] t = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };

            for (int i = 0; i < t.length; i++) {
                if (
                        row + t[i][0] >= 0 && row + t[i][0] < n &&
                        col + t[i][1] >= 0 && col + t[i][1] < n &&
                        isOpen(row + t[i][0], col + t[i][1])
                ) union.Union(row * n + col,(row + t[i][0]) * n + col + t[i][1]);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (union.find(row * n + col) == union.find(n * n) &&
            union.find(n * n) == union.find(n * n + 1)
        )
            return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (isOpen(0, i) && union.find(i) != union.find(n * n))
                union.Union(i, n * n);
        }

        for (int i = 0; i < n; i++) {
            if (isOpen(n - 1, i) && union.find((n - 1) * n + i) != union.find(n * n + 1))
                union.Union((n - 1) * n + i, n * n + 1);
        }

        if (union.find(n * n) == union.find(n * n + 1))
            return true;
        return false;
    }

}
