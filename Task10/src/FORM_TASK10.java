import com.company.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



//Задание №1.
// Коробки описываются длиной, шириной и высотой.
// Задан набор коробок.
// Найти пары коробок, где первая коробка может быть вложена во вторую коробку.

//Задание №2
//Задан набор студентов в виде (ФИО, пол, номер курса, средний балл).
// Необходимо на новогодний бал отправить лучших студентов – по одной паре (парень и девушка) с каждого курса с лучшей успеваемостью.
// Если есть несколько студентов с максимальной успеваемостью – выбрать случайным образом (именно случайным, т.е. при следующем выборе
// вполне возможно отобраны будут другие студенты).
// Если какой-то курс полностью состоит из представителей одного пола, то с такого курса на балл никого не отправлять.












public class FORM_TASK10  extends JFrame{
    private JPanel main_panel;
    private JTextField text_length;
    private JTextField text_length_2;
    private JButton button_task_1;
    private JButton res_1;
    private JTextField text_height;
    private JTextField text_width;
    private JTextField text_height_2;
    private JTextField text_width_2;
    private JTextArea output_res_1;
    private JTextField fio;
    private JTextField gender;
    private JTextField course;
    private JTextField cpd;
    private JButton button_task_2;
    private JTextArea output_res_2;
    private JButton res_2;
    private JTextField fio_2;
    private JTextField gender_2;
    private JTextField course_2;
    private JTextField cpd_2;


    class Box
    {
        int m_long;
        int m_height;
        int m_width;

        Box( int p_long, int p_height,  int p_width) {
            m_long = p_long;
            m_height = p_height;
            m_width = p_width;
        }


    };

    public FORM_TASK10() {

        List<Box> boxes_1 = new ArrayList<>();
        List<Box> boxes_2 = new ArrayList<>();
        List<Student> students_1 = new ArrayList<>();
        List<Student> students_2 = new ArrayList<>();



        button_task_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boxes_1.add(new Box(
                        Integer.parseInt(text_length.getText()),
                        Integer.parseInt(text_height.getText()),
                        Integer.parseInt(text_width.getText()))
                );

                boxes_2.add(new Box(
                        Integer.parseInt(text_length_2.getText()),
                        Integer.parseInt(text_height_2.getText()),
                        Integer.parseInt(text_width_2.getText()))
                );

                text_length.setText("");
                text_height.setText("");
                text_width.setText("");
                text_length_2.setText("");
                text_height_2.setText("");
                text_width_2.setText("");


            }
        });



        res_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            output_res_1.setText("");
                find_compatible_pairs_boxes(boxes_1, boxes_2);

            }
        });



        button_task_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main.Student.GENDER l_gender = Main.Student.GENDER.MALE;
                if (gender.getText().equals("Мужской") || gender.getText().equals("Мale") || gender.getText().equals("М") || gender.getText().equals("M")) {
                    l_gender = Main.Student.GENDER.MALE;
                }
                else if (gender_2.getText().equals("Женский") || gender.getText().equals("Female") || gender.getText().equals("Ж") || gender.getText().equals("F")) {
                    l_gender = Main.Student.GENDER.FEMALE;
                }
                else {
                    return;
                }
                students_1.add(new Student( fio.getText(), l_gender, Integer.parseInt(course.getText()),Integer.parseInt(cpd.getText())));

                Main.Student.GENDER l_gender_2 = Main.Student.GENDER.MALE;
                if (gender_2.getText().equals("Мужской") || gender_2.getText().equals("Мale") || gender_2.getText().equals("М") || gender_2.getText().equals("M")) {
                    l_gender_2 = Main.Student.GENDER.MALE;
                }
                else if (gender_2.getText().equals("Женский") || gender_2.getText().equals("Female") || gender_2.getText().equals("Ж") || gender_2.getText().equals("F")) {
                    l_gender_2 = Main.Student.GENDER.FEMALE;
                }
                else {
                    return;
                }

                students_2.add(new Student( fio_2.getText(),  l_gender_2, Integer.parseInt(course_2.getText()),Integer.parseInt(cpd_2.getText())));

                fio.setText("");
                gender.setText("");
                course.setText("");
                cpd.setText("");

                fio_2.setText("");
                gender_2.setText("");
                course_2.setText("");
                cpd_2.setText("");


            }
        });


        res_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                output_res_2.setText("");
                search_the_best_pairs_students(students_1, students_2);
            }
        });
    }

    private void search_the_best_pairs_students(List<Student> first_students, List<Student> second_students) {
        if (first_students.size() != second_students.size())
            System.out.println("Invalid sizes arrays");

        List<Integer> the_best_pairs = new ArrayList<Integer>();
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);

        for (int i = 0; i < first_students.size(); i++) {
            if (first_students.get(i).m_gender == second_students.get(i).m_gender || first_students.get(i).m_course_number != second_students.get(i).m_course_number) {
                continue;
            }

            if (the_best_pairs.get(first_students.get(i).m_course_number - 1) == -1)
                the_best_pairs.set(first_students.get(i).m_course_number - 1, i);
            else {
                if ((first_students.get(i).m_gpa + second_students.get(i).m_gpa) == (
                       first_students.get(the_best_pairs.get(first_students.get(i).m_course_number - 1)).m_gpa +
                       second_students.get(the_best_pairs.get(second_students.get(i).m_course_number - 1)).m_gpa)) {
                    if (Math.round(Math.random() * 1) == 0) //рандом сделаешь на бул, если 0 то Zаходим
                        the_best_pairs.set(first_students.get(i).m_course_number - 1, i);
                }

                if ((first_students.get(i).m_gpa + second_students.get(i).m_gpa) > (
                   first_students.get(the_best_pairs.get(first_students.get(i).m_course_number - 1)).m_gpa +
                   second_students.get(the_best_pairs.get(second_students.get(i).m_course_number - 1)).m_gpa))
                    the_best_pairs.set(first_students.get(i).m_course_number - 1, i);
            }
        }

        for (int i = 0; i < the_best_pairs.size(); i++) {
            if (the_best_pairs.get(i) == -1)
                continue;


            output_res_2.append("Пара студентов на новогодний бал:\n" +
            "Первый студент - " +first_students.get(the_best_pairs.get(i)).m_fio +" " +
            "\nВторой студент - " +second_students.get(the_best_pairs.get(i)).m_fio +
            " " + Integer.toString(i + 1) + " Курс\n" + "\n ");
        }

        first_students.clear();
        second_students.clear();
    }

    private void find_compatible_pairs_boxes(List<Box> first_boxes, List<Box> second_boxes) {
        if (first_boxes.size() != second_boxes.size())
            System.out.print("Неправильные размеры коробок");

        for (int i = 0; i < first_boxes.size(); i++) {

            if (first_boxes.get(i).m_long >= second_boxes.get(i).m_long || first_boxes.get(i).m_height >= second_boxes.get(i).m_height)
                if (first_boxes.get(i).m_height >= second_boxes.get(i).m_height || first_boxes.get(i).m_width >= second_boxes.get(i).m_width)
                    continue;

            System.out.printf( "Коробка :%d %d %d Помещается в коробку: %d %d %d\n" ,
                    first_boxes.get(i).m_long, first_boxes.get(i).m_height, first_boxes.get(i).m_width,
                    second_boxes.get(i).m_long, second_boxes.get(i).m_height, second_boxes.get(i).m_width);

            output_res_1.append(
                    "Коробка с размерами: "+ Integer.toString(first_boxes.get(i).m_long) +
                    Integer.toString(first_boxes.get(i).m_height) +
                    Integer.toString(first_boxes.get(i).m_width)
                    + "\nПомещается в коробку с размерами: " +
                    Integer.toString(second_boxes.get(i).m_long) +
                    Integer.toString(second_boxes.get(i).m_height) +
                    Integer.toString(second_boxes.get(i).m_width) +
                    '\n' + '\n');

        }

        first_boxes.clear();
        second_boxes.clear();

    }



    class Student
    {
        enum  GENDER {
            MALE,
            FEMALE
        };
        String m_fio;
        Main.Student.GENDER m_gender;
        int m_course_number;
        int m_gpa;
        boolean is_passed = false;
        Student(String fio, Main.Student.GENDER gender, int course_number, int gpa) {

            m_fio = fio;
            m_gender = gender;
            m_course_number = course_number;
            m_gpa = gpa;
        }


    }
















    public static void main(String[] args) {
        FORM_TASK10 form = new FORM_TASK10();
        form.setContentPane(new FORM_TASK10().main_panel);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setVisible(true);

    }


}
