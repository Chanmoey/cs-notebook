package com.moon.interview.search;

/**
 * @author Chanmoey
 * @date 2022年08月05日
 */
public class EQueen {

    /**
     * Each queen occupies a col,
     * queens[0] = 3 means that the queen occupying the 0th col is in the 3rd row.
     */
    int[] queens = new int[8];

    public boolean search(int col) {
        if (col == 8) {
            return true;
        }

        // i -> row
        for (int i = 0; i < 8; i++) {
            queens[col] = i;

            boolean flag = true;

            for (int j = 0; j < col; j++) {
                var rowDiff = Math.abs(queens[j] - i);
                var colDiff = col - j;

                if (queens[j] == i || rowDiff == colDiff) {
                    flag = false;
                    break;
                }
            }

            if (flag && search(col + 1)) {
                return true;
            }
        }

        return false;
    }

    void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (queens[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        var equeen = new EQueen();
        equeen.search(0);
        equeen.print();
    }
}
