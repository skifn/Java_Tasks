package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.xml.internal.serializer.Method.TEXT;


public class Main {




/*
    public static void main(String[] args) {
        System.out.println();
        String str = "ababb ass sex";
        String[] words = str.split(" ");

        List <String> list = new ArrayList<>();

        for( int i = 0; i < words.length; i ++) {list.add(i, words[i]);}


        for(int i = 0; i > list.size(); i ++) {


            if(solution(list.get(i))) {

                System.out.println(list.get(i));
            }

        }

System.out.println(list);

    }
*/

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println();
        String str = "abbba";
        String str_2 = " Этотгородбоитсяменя меня! Я видел его истинное лицо. Улицы продолжение сточных сточных канав, а канавы наполнены кровью.";
        int count_2 = 0;
        int max = 0;

        //Задание №1
        ArrayList<String> firstList = new ArrayList<String>(Arrays.asList(str.split(" ")));
        ArrayList<String> secondList = new ArrayList<String>();

        for (String word : firstList) {
            if (solution(word)) {
                secondList.add(word);
            }
        }

        for (int i = 0; i < secondList.size(); i++) {

            for (int j = i + 1; j < secondList.size(); j++) {
                if (secondList.get(i).equals(secondList.get(j))) {
                    secondList.remove(i);
                }
            }
        }
        System.out.println(secondList);




        //Задание №2
        ArrayList<String> second_task_proposals = new ArrayList<String>(Arrays.asList(str_2.split("[.?!]+")));
        ArrayList<Integer> second_task_counter = new ArrayList<Integer>();
      String second_task_final = " ";



        for (String proposal : second_task_proposals) {


                count_2 = solution_2(proposal);
               second_task_counter.add(count_2);

            }


        second_task_final = getLongestSentence("input.txt");


        System.out.print(second_task_final);







        }








    public static String getLongestSentence(String fname) throws FileNotFoundException {
        String longestSentence = "";
        String currentSentence = "";
        Scanner scan = new Scanner(new File(fname));

        while (scan.hasNext()) {

            currentSentence = getNextSentence(scan);

            if (currentSentence.length() > longestSentence.length()) {
                longestSentence = currentSentence;
            }

        }
        scan.close();
        return longestSentence;
    }

    private static String getNextSentence(Scanner scan) {
        String sentence = " ";
        while(scan.hasNext()){
            sentence += (" "+scan.next());
            if(sentence.contains(".") || sentence.contains("!") || sentence.contains("?")) break;
        }
        return sentence;
    }


    public static boolean solution(String str) {
        for (int i = 0; i < str.length(); i++) {
            int sum = 1;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == str.charAt(i) && j != i) {
                    sum++;
                }
                if (sum >= 3) return true;
            }
        }
        return false;
    }


    public static int solution_2(String str) {


        int num = 0;

        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я]+");
        Matcher m = p.matcher(str);

        while(m.find()) {
            num++;
        }

        return num;







    }














    static String file_read(String fname) {

        String line = null;
        try {
            File file = new File(fname);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }








}