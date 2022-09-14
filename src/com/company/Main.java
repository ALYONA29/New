package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        int[][] matrix;
        int[][] transposedMatrix;
        int[][] result;
	    matrix = readMatrix();
	    transposedMatrix = transpose(matrix);
	    result = multiply(matrix, transposedMatrix);
        out(matrix, transposedMatrix, result);
    }

    public static int[][] readMatrix() {
        int n = 0;
        int[] buffer;
        int[][] matrix = new int[0][];
        int i = 0;
        System.out.println("Введите матрицу:");
        do {
            buffer = readMatrixLine();
            if(matrix.length == 0) {
                n = buffer.length;
                matrix = new int[n][];
            }
            checkLength(buffer.length, n);
            matrix[i] = buffer;
            i++;
        } while (i < n);
        return matrix;
    }
    
    public static int[] readMatrixLine() {
        int[] result = new int[0];
        int n;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer;
        try {
            buffer = console.readLine().split(" ");
            n = buffer.length;
            result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = Integer.parseInt(buffer[i]);
            }
        }
        catch (Exception e) {
            System.out.println("Элементами матрицы могут быть только целые числа");
            System.exit(1);
        }
        return result;
    }
    

    public static void checkLength(int length, int n) {
        if(length != n) {
            String s = String.format("Введено %d элементов в строке при вводе матрицы %dx%d", length, n, n);
            System.out.println(s);
            System.exit(1);
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int[][] transposedMatrix = copyMatrix(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = transposedMatrix[i][j];
                transposedMatrix[i][j] = transposedMatrix[j][i];
                transposedMatrix[j][i] = temp;
            }
        }
        return transposedMatrix;
    }

    public static int[][] copyMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] copyMatrix = new int[n][n];
        for ( int i = 0 ; i < n; ++i)
        {
            System.arraycopy(matrix[i], 0, copyMatrix[i], 0, n);
        }
        return copyMatrix;
    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static void writeMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.format("%6d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void out(int[][] matrix, int[][] transposedMatrix, int[][] result) {
        System.out.println("Исходная матрица:");
        writeMatrix(matrix);
        System.out.println("Транспонированная матрица:");
        writeMatrix(transposedMatrix);
        System.out.println("Результат умножения:");
        writeMatrix(result);
    }
}
