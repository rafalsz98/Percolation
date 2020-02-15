package com.company;

public class Main {

    public static void main(String[] args) {

	    PercolationStats stats = new PercolationStats(10, 100);
	    System.out.println("Mean: " + stats.mean());
	    System.out.println("Standard dev: " + stats.stddev());
	    System.out.println(stats.confidenceLo() + " to " + stats.confidenceHi());
    }
}
