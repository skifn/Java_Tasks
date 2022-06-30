package com.company;


import java.util.*;

public class Main {




    public static void main(String[] args) {

        try
        {
            Main programm = new Main();
            programm.run();
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }

    }

    public void run() throws Exception {

        //Задание №1
        List<Box> boxes_1 = new ArrayList<Box>();
        List <Box> boxes_2 = new ArrayList<Box>();

        boxes_1.add(new Box(2, 2, 2));
        boxes_1.add(new Box(4, 4, 4));
        boxes_1.add(new Box(3, 3, 3));
        boxes_1.add(new Box(1, 1, 1));
        boxes_1.add(new Box(1, 2, 3));
        boxes_1.add(new Box(3, 4, 5));



        boxes_2.add(new Box(3, 3, 3));
        boxes_2.add(new Box(4, 5, 5));
        boxes_2.add(new Box(2, 2, 3));
        boxes_2.add(new Box(2, 2, 1));
        boxes_2.add(new Box(1, 2, 3));
        boxes_2.add(new Box(5, 4, 3));


        find_compatible_pairs_boxes(boxes_1, boxes_2);

        //Задание №2
        List<Student> students_1 = new ArrayList<Student>();
        students_1.add(new Student("Elona Reeve Musk 1/5", Student.GENDER.MALE,  1, 5));
        students_1.add(new Student("Khabib Abdulmanapovich Nurmagomedov 1/1", Student.GENDER.MALE,  1, 1));

        List<Student> students_2 = new ArrayList<Student>();
        students_2.add(new Student("Claire Elise Boucher 1/4", Student.GENDER.FEMALE,  1, 4));
        students_2.add(new Student("Mike Ray Tyson 1/1", Student.GENDER.MALE,  1, 1));


        search_the_best_pairs_students(students_1, students_2);

    }


    //
    public class Box
    {
        public int m_long;
        public int m_height;
        public int m_width;

        Box( int p_long, int p_height,  int p_width) {
                 m_long = p_long;
                 m_height = p_height;
                 m_width = p_width;
        }


    };

    static void find_compatible_pairs_boxes(List<Box> first_boxes, List<Box> second_boxes) {
        if (first_boxes.size() != second_boxes.size())
          System.out.print("Неправильные размеры коробок");

        for (int i = 0; i < first_boxes.size(); i++) {

            if (first_boxes.get(i).m_long >= second_boxes.get(i).m_long || first_boxes.get(i).m_height >= second_boxes.get(i).m_height)
                if (first_boxes.get(i).m_height >= second_boxes.get(i).m_height || first_boxes.get(i).m_width >= second_boxes.get(i).m_width)
                    continue;

           System.out.printf( "Коробка :%d %d %d Помещается в коробку: %d %d %d\n" , first_boxes.get(i).m_long, first_boxes.get(i).m_height, first_boxes.get(i).m_width, second_boxes.get(i).m_long, second_boxes.get(i).m_height, second_boxes.get(i).m_width);

        }
    }

    public class Student
    {
        public enum  GENDER {
        MALE,
        FEMALE
    };
        public String m_fio;
        public GENDER m_gender;
        public int m_course_number;
        public int m_gpa;
        boolean is_passed = false;
        Student(String fio, GENDER gender, int course_number, int gpa) {

                    m_fio = fio;
                    m_gender = gender;
                    m_course_number = course_number;
                    m_gpa = gpa;
        }


        }

    static void search_the_best_pairs_students( List<Student> first_students, List <Student> second_students) {
        if (first_students.size() != second_students.size())
            System.out.println("Invalid sizes arrays");

        List<Integer> the_best_pairs = new ArrayList<Integer>();
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);
        the_best_pairs.add(-1);

        for (int i = 0; i < first_students.size(); i++) {
            if (first_students.get(i).m_gender == second_students.get(i).m_gender)
                continue;

            if (the_best_pairs.get(first_students.get(i).m_course_number - 1) == -1)
                the_best_pairs.set(first_students.get(i).m_course_number - 1, i);
            else {
                if ((first_students.get(i).m_gpa + second_students.get(i).m_gpa) == (
                        first_students.get(the_best_pairs.get(first_students.get(i).m_course_number - 1)).m_gpa +
                                second_students.get(the_best_pairs.get(second_students.get(i).m_course_number - 1)).m_gpa)) {
                    if (((Math.random()*((1-0)+1))+1) == 0) //рандом сделаешь на бул, если 0 то Zаходим
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

            System.out.println(first_students.get(the_best_pairs.get(i)).m_fio);
            System.out.println(second_students.get(the_best_pairs.get(i)).m_fio);
            System.out.println(i+1 + " Курс\n");
        }
    }







};





