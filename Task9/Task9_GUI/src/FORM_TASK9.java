import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.valueOf;


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





public class FORM_TASK9 extends JFrame {
    private JPanel main_panel;
    String file_name;
    List <Integer> list = new ArrayList();

    String temp, temp_1, temp_2, temp_3, temp_4, temp_5;
    List <Integer> list_second_1 = new ArrayList();
    List <Integer> list_second_2 = new ArrayList();
    List <Integer> list_second_main = new ArrayList();

    List <Integer> list_kb = new ArrayList();
    private JTextField get_file;
    private JButton get_list;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton first_task;
    private JButton second_task;
    private JTextField textField5;
    private JButton загрузитьПервыйЛистДляButton;
    private JButton загрузитьВторойЛистДляButton;
    private JTextField textField6;
    private JButton clear;
    private JTextField list_keyboard;
    private JButton считатьЛистСКлавиатурыButton;
    private JTextField file;
    private JButton загрузитьВInput_2Button;


    public FORM_TASK9() {

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
                textField3.setText(null);
                textField5.setText(null);
                textField6.setText(null);
                temp = (null);
                list.clear();
                list_second_1.clear();
                list_second_2.clear();
                list_second_main.clear();
                list_kb.clear();


            }
        });



        get_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file_name = get_file.getText();
                textField1.setText("");
                try {
                    file_read_list(list, file_name);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (int j=0;j<list.size();j++) {
                    temp = textField1.getText();
                    temp = temp + Integer.toString(list.get(j));
                    textField1.setText(" " + temp + " ");
                }
                temp = "";



            }
        });


        first_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                process(list);
                String temp;
                String fname = "output.txt";
                for (int j=0;j<list.size();j++) {
                    temp = textField2.getText();
                    temp = temp + String.valueOf(list.get(j));
                    textField2.setText(" " + temp + " ");
                }
                //Потом записываем результат в файл
                try {
                    file_write_list(list, fname); //Читаем второй лист
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        загрузитьПервыйЛистДляButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                file_name = get_file.getText();
                try {
                    file_read_list(list_second_1, file_name);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String temp_1;

                for (int j=0;j<list_second_1.size();j++) {
                    temp_1 = textField6.getText();
                    textField6.setText(null);
                    temp_1 = temp_1 + String.valueOf(list_second_1.get(j));
                    textField6.setText(" " + temp_1 + " ");
                }

            }



        });


        загрузитьВторойЛистДляButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file_name = get_file.getText();
                try {
                    file_read_list(list_second_2, file_name);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String temp_2;
                for (int j=0;j<list_second_2.size();j++) {
                    temp_2 = textField5.getText();
                    temp_2 = temp_2 + String.valueOf(list_second_2.get(j));
                    textField5.setText(" " + temp_2 + " ");
                }


            }
        });


        second_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                list_second_main = createNewList(list_second_1, list_second_2); //Создание нового листа
                try {
                    file_write_list(list_second_main, "output_2.txt"); //Записыуаем в файл новый лист
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                String temp_3;

                for (int j=0;j<list_second_main.size();j++) {
                    temp_3 = textField3.getText();
                    temp_3 = temp_3 + String.valueOf(list_second_main.get(j));
                    textField3.setText(" " + temp_3 + " ");
                }


            }
        });




        считатьЛистСКлавиатурыButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String File_Name;
                File_Name = file.getText();
                String arr = new String(list_keyboard.getText());
                String[] arrSplit = arr.split(" ");
                int array[] = new int[arrSplit.length];
                for(int i = 0; i < arrSplit.length; i++) {
                    array[i] = Integer.valueOf(arrSplit[i]);
                    list_kb.add(array[i]);
                }

                try {
                    file_write_list(list_kb, File_Name);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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



    public static void main(String[] args) {
        FORM_TASK9 form = new FORM_TASK9();
        form.setSize(800, 600);
        form.setContentPane(new FORM_TASK9().main_panel);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setVisible(true);

    }


}
