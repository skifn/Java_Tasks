package com.company;
import java.util.Random;
import java.util.Scanner;






public class Main {


    public static void main(String[] args) {
        System.out.print("Введите размер массива: ");
        Scanner in = new Scanner(System.in);
        int result_2;
        int size_valuable;
        size_valuable = in.nextInt();
        int array[] = new int[size_valuable];

        creation(array, size_valuable);
        solution(array, size_valuable);
        solution_2(array, size_valuable);
        //System.out.printf("\nПоследовательность начинается с индекса: %d", result_2);
    }

    //Функция инициализации массива
    public static void creation(int[] arr, int size) {

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            System.out.printf("Введите %d элемент массива: ", i + 1);
            arr[i] = in.nextInt();
        }

        System.out.print("\nВы ввели массив:\n");
        for (int i = 0; i < size; i++) {
            System.out.printf("array[%d] = %d\n", i + 1, arr[i]);
        }
    }

    //Функция определения убывающих промежутков
    public static void solution(int[] a, int size) {

        int k = 0;
        int count = 0;
        int newPosition = 0;
        for (int i = 1; i < a.length; i++) {
            for (int j = newPosition; j < i; j++) {
                if (a[j] > a[i]) {
                    k++;
                } else if (a[j] <= a[i]) {
                    if (k > 0) {
                        count++;
                        k = 0;
                    }
                    newPosition = i;
                }
                if (i == a.length - 1 && j == i - 1 && a[j] > a[i]) {
                    count++;
                }
            }
        }


        System.out.printf("\nКоличество монотонно убывающих промежутков\nРезультат: %d\n", count);
    }


    //Функция определения последней самой длинной подпоследовательности

    public static void solution_2(int[] arr, int size) {
        //System.out.printf("Количество элементов подпоследовательности: %d ", count);
        //return index;
        int curLength = 0;
        int maxLength = 0;
        int indexBegin = -1;
        boolean isOpenSeq = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (isTrueSeq(arr[i], arr[i + 1])) {
                curLength++;
                isOpenSeq = true;
            } else {
                if (curLength >= maxLength) {
                    maxLength = curLength;
                    indexBegin = i - curLength;
                    curLength = 0;
                    isOpenSeq = false;
                }
            }
        }
        if (isOpenSeq && curLength >= maxLength + 1) {
            indexBegin = arr.length - curLength - 1;
            maxLength = curLength;
        }
        System.out.print("\nЭлементы подпоследовательности: {");
        for (int i = indexBegin; i < indexBegin + maxLength + 1; i++) {
            System.out.printf("%d,", arr[i]);
        }
        System.out.print("}");
    }





    private static boolean isTrueSeq(int a, int b) {

        if(a != b) {return true;};
        return false;
    }
}


