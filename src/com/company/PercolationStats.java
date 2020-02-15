package com.company;

import java.util.Random;

public class PercolationStats {
    public Percolation[] percolations;
    private int nTrials;
    private int nSize;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        nSize = n;
        percolations = new Percolation[trials];
        Random gen = new Random();
        nTrials = trials;

        for (int i = 0 ; i < trials; i++){
            percolations[i] = new Percolation(n);
            while (!percolations[i].percolates()) {
                int x, y;
                do {
                    x = gen.nextInt(n);
                    y = gen.nextInt(n);
                } while (percolations[i].isOpen(x, y));
                percolations[i].open(x, y);
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < nTrials; i++) {
            sum += (percolations[i].numberOfOpenSites() / (double)(nSize * nSize));
        }
        return sum / nTrials;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double stddev = 0;
        for (int i = 0; i < nTrials; i++) {
            stddev = stddev + (percolations[i].numberOfOpenSites() / (double)(nSize * nSize) - mean) * (percolations[i].numberOfOpenSites() / (double)(nSize * nSize) - mean);
        }
        return stddev / (nTrials - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * Math.sqrt(stddev() / nTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * Math.sqrt(stddev() / nTrials));
    }
}
