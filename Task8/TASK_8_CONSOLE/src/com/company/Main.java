package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


// Задание №1  Строки, элементы которых не убывают
// (т.е. образуют неубывающую последовательность чисел) переместить в начало (вверх),
// сохранив при этом взаимное расположением перемещаемых строк.

// Задание №2 Создать новый двумерный массив чисел,
// исключив из переданного двумерного массива все строки и столбцы,
// содержащие максимальные и минимальные элементы среди
// всех элементов переданного массива.

public class Main {

    public static void main(String[] args)  throws Exception {
        Main matrix = new Main();
        int rows = 3, columns = 3; //размер двумерного массива
        //System.out.print("Введите размеры массива:\nКол-во строк:");
        //rows = in.nextInt();
        //System.out.print("\nКол-во столбцов:");
        //columns = in.nextInt();
        //System.out.print("\n");
        int[][] array = new int[rows][columns];
        int[][] result_array = new int[rows][columns];
        //Чтение и запись в файл
        file_read_array(array); //Функция чтения массива из файла
        file_write_array(matrix.customSort(array), "output_1.txt", rows, columns); // Записываем в файл первое задание

        result_array = SOLUTION_2(array);
        file_write_array(SOLUTION_2(array), "output_2.txt", result_array.length, result_array[0].length); // Записываем в файл второе задание




        matrix.showMatrix(array); // Вывод массивов в консоль
        matrix.showMatrix(matrix.customSort(array)); //Сортировка (строки с неубывающими элементами наверх)

    }



            public int[][] customSort ( int[][] matrix){
                return Arrays.stream(matrix)
                        .sorted(Comparator.comparing(this::isDecrease))
                        .toArray(int[][]::new);
            }

            private boolean isDecrease ( int[] row){
                for (int i = 0; i < row.length - 1; i++) {
                    if (row[i] > row[i + 1]) return true;
                }
                return false;
            }

            private void showMatrix ( int[][] matrix){
                for (int[] row : matrix) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.println("++++++++++++++++++++++++++++++++++");
            }

            //Инициализация массива через консоль
            static public void creation ( int[][] array, int r, int c){
                Scanner in = new Scanner(System.in);
                for (int i = 0; i < r; i++) {

                    for (int j = 0; j < c; j++) {
                        System.out.printf("Введите %d.%d элемент массива: ", i + 1, j + 1);
                        array[i][j] = in.nextInt();
                    }

                }

            }

            //Чтение массива из файла
            static public void file_read_array(int arr[][]) throws Exception {
    Scanner sc = new Scanner(new BufferedReader(new FileReader("input.txt")));
    while (sc.hasNextLine()) {
        for (int i = 0; i < arr.length; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

}

            //Запись массива в файл
            static public void file_write_array (int arr[][], String Path, int rows, int columns) throws Exception {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Path));
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        bw.write(String.valueOf(arr[i][j]) + " ");
                    }
                    bw.newLine();
                }
                bw.flush();
                bw.close();
}

            //Удаление строк и столбцов с min и max значениями
            public static int[][] SOLUTION_2(int[][] array) {
        int rows = 3;
        int cols = 3;
        boolean[] minRows = null, maxRows = null;
        boolean[] minCols = null, maxCols = null;

        Integer min = null;
        Integer max = null;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (null == min || array[i][j] < min) {
                    minRows = new boolean[rows];
                    minRows[i] = true;
                    minCols = new boolean[cols];
                    minCols[j] = true;
                    min = array[i][j];
                } else if (array[i][j] == min) {
                    minRows[i] = true;
                    minCols[j] = true;
                }

                if (null == max || array[i][j] > max) {
                    maxRows = new boolean[rows];
                    maxRows[i] = true;
                    maxCols = new boolean[cols];
                    maxCols[j] = true;
                    max = array[i][j];
                } else if (array[i][j] == max) {
                    maxRows[i] = true;
                    maxCols[j] = true;
                }
            }
        }
        int rowsToDelete = 0, colsToDelete = 0;
        for (int i = 0; i < rows; i++) {
            if (minRows[i] || maxRows[i]) {
                rowsToDelete++;
            }
        }
        for (int i = 0; i < cols; i++) {
            if (minCols[i] || maxCols[i]) {
                colsToDelete++;
            }
        }

        if (rows == rowsToDelete || cols == colsToDelete) {
            return new int[1][0];
        }

        int[][] result = new int[rows - rowsToDelete][cols - colsToDelete];

        for (int i = 0, r = 0; i < rows; i++) {
            if (minRows[i] || maxRows[i])
                continue; // пропустить строку, содержащую минимум или максимум
            for (int j = 0, c = 0; j < cols; j++) {
                if (minCols[j] || maxCols[j])
                    continue; // пропустить столбец, содержащий минимум или максимум
                result[r][c++] = array[i][j];
            }
            r++;
        }

        return result;
    }
}
