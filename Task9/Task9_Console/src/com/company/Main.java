package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    //Задание 1. Перевернуть числа в списке слева направо:
    // { 2, 7, 3, 5, 100 } → { 100, 5, 3, 7, 2 }
    //   Реализовать в виде функции:
    // public static void process(List<Integer> list)

    //Задание 2.	Реализовать функцию:
    //public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2)
    //, которая создаст новый список элементов из элементов первого списка,
    // каждый из которых повторяется такое кол-во раз,
    // которое данный элемент встречается во втором списке, например:
    //({ 5, 3, 1, 2, 3, 3, 7, 4, 8 }, { 5, 3, 7, 3, 7, 2, 2, 8, 7 }) → { 5, 3, 3, 2, 2, 3, 3, 3, 3, 7, 7, 7, 8 }
    //Для удобства реализовать дополнительную функцию:
    //public static int countOf(List<Integer> list, int value)
    //, которую использовать в реализации функции createNewList.


    public static void main(String[] args) throws Exception {
        String fname_1, fname_2;
        //Файлы для 1 задания
        fname_1 = "input.txt";
        fname_2 = "output.txt";
        //Файлы для 2 задания
        String fname_3, fname_4, fname_5, fname_6;
        fname_3 = "input_2.txt"; //Считывание первого листа
        fname_4 = "input_2_1.txt"; //Считывание второго листа
        fname_5 = "output_2.txt"; //Вывод получившегося листа

        //Листы для 1 задания:
        //Исходный лист
        List<Integer> list = new ArrayList<Integer>();
        //Лист для записи в файл
        List<Integer> result = new ArrayList<Integer>();

        //Листы для 2 задания:
        List<Integer> list_2 = new ArrayList<Integer>(); //1 лист для создания
        List <Integer> list_3 = new ArrayList<Integer>(); //2 лист для создания
        List <Integer> main_list = new ArrayList<Integer>(); // главный лист
        int digit = 0;


        //Считываем в лист данные из файла
        //Вывод листа
        //   for (int i = 0; i < list.size(); i++) {
        //   System.out.println(list.get(i));
        //}

        //Выполнение 1 задания
        file_read_list(list, fname_1); // считывание
        result = (ArrayList<Integer>) process(list); //Реверс
        file_write_list(result, fname_2); // вывод в файл

        //Выполнение 2 задания
        file_read_list(list_2, fname_3); // Читаем первый лист
        file_read_list(list_3, fname_4); //Читаем второй лист

        main_list = createNewList(list_2, list_3); //Создание нового листа
        file_write_list(main_list, fname_5); //Записыуаем в файл новый лист



    }





    public static List<Integer> createNewList(List<Integer> list1, List<Integer> list2){
    int test;
        List<Integer> list = new ArrayList();
        for(int n = 0; n < list1.size(); n++){
            test = countOf(list2, list1.get(n));
            for(int i = 0; i < test; i++){
                list.add(list1.get(n));
            }
        }
        return list;
    }

    public static int countOf(List<Integer> list_sent, int value){
        int count = 0;
        for (int i: list_sent) {
            if(i == value){
                count++;
            }
        }
        return count;
    }





    //Задание 1
    public static List process(List <Integer> list_1) {

        Collections.reverse(list_1);


        return list_1;

    }
    //чтение листа из файла
    static public void file_read_list(List <Integer> list_1, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        try {
            while (scanner.hasNext()) {
                list_1.add(scanner.nextInt());

            }
        }
        catch (Exception exception) {
            System.out.println("Error");
        }
    }

    //Запись листа в файл
    static public void file_write_list (List <Integer> list_2, String fName) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fName));

            for (int i = 0; i < list_2.size(); i++) {
                bw.write(String.valueOf(list_2.get(i)) + " ");
            }
            bw.newLine();

        bw.flush();
        bw.close();
    }


}
