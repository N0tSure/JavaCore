package com.artemsirosh.tij.arrays;

import java.util.Arrays;

/**
 * Created by cresh on 24.01.17.
 */
public class Dimension {
    static double[][] twoDimension(int n, int m, double lo, double high) {
        double[][] result = new double[n][m];
        double tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                do {
                    tmp = Math.random();
                } while (!(tmp >= lo && tmp <= high));
                result[i][j] = tmp;
            }
        }
        return result;
    }

    private static double[][][] threeDimensional(int x, int y, int z, double low, double high) {
        double[][][] result = new double[x][y][z];
        double tmp;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    do {
                        tmp = Math.random();
                    } while (!(tmp >= low && tmp <= high));
                    result[i][j][k] = tmp;
                }
            }
        }
        return result;
    }

    private static void print2D(double[][] a) {
        System.out.println(Arrays.deepToString(a));
    }

    private static void print3D(double[][][] a) {
        StringBuilder builder = new StringBuilder("\n");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < a[i][j].length; k++) {
                    builder.append(String.format(" %.4f", a[i][j][k]));
                }
                builder.append('\n');
            }
            builder.append('\n');
        }

        System.out.println(builder);
    }

    public static void main(String[] args) {
        print2D(twoDimension(4, 4, 0.5, 0.8));
        print3D(threeDimensional(3, 3, 3, 0.4, 0.9));
        System.out.println(Arrays.deepToString(new BerylliumSphere[10][10][10]));
    }
}
