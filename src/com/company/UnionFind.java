package com.company;

public class UnionFind {
    private int[] id;
    private int[] sz;

    UnionFind(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    int find(int a) {
        int i = id[a];
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    void Union(int a, int b) {
        int i = find(a);
        int j = find(b);
        if (i == j) return;
        if (sz[i] > sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
        }
        else {
            id[i] = j;
            sz[j] += sz[i];
        }
    }
}
