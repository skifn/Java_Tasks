package Programm;

import com.company.SimpleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import static Programm.alg.maxDepth;
import static Programm.alg.sheets;


//Задание №1.
// Сгенерировать треугольник Серпинского с помощью псевдографики в следующем виде:
//Глубина рекурсии, N
//
// Если 1:
// /\
///__\
//
//////////
//
//   /\
//  /__\
// /\  /\
///__\/__\
//
////////////////
//
//       /\
//      /__\
//     /\  /\
//    /__\/__\
//   /\      /\
//  /__\    /__\
// /\  /\  /\  /\
///__\/__\/__\/__\






//Задание №2
// На вход передаётся путь к папке
// (в виде строки, например "Z:\\ВвП").
// Напишите функцию, которая находит наиболее
// "глубоко" лежащие элементы ("листья") - это могут быть и файлы и папки.









public class Forms extends JFrame implements ActionListener, WindowListener// 13 4
{

    public static JButton task1 = new JButton();
    public static TextField rec = new TextField();
    public static TextField mask = new TextField();
    public static TextArea Task2_output = new TextArea();
    public static TextArea Task1_output = new TextArea();
    public static JButton task2 = new JButton();
    public static TextField path = new TextField();

    public Forms() {
        super("Task 12");
        addWindowListener(this);
        File file = new File(path.getText());
        int button_width = 100;
        setBounds(100, 100, 730, 320);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        Task2_output.setBounds(230, 10, 480, 270);
        Task2_output.setVisible(true);
        Task2_output.setText("");
        Task2_output.setFont(new Font("Monospaced", Font.PLAIN, 11));
        add(Task2_output);

        task1.setBounds(10, 10, 100, 20);
        task1.setVisible(true);
        task1.setText("Задание №1");
        add(task1);
        task1.addActionListener(this);

        task2.setBounds(10, 70, 100, 20);
        task2.setVisible(true);
        task2.setText("Задание №2 \nОтрисовать фигуру");
        add(task2);
        task2.addActionListener(this);

        rec.setBounds(10, 100, 50, 20);
        rec.setVisible(true);
        rec.setText("1");
        add(rec);



        path.setText("C:\\Users\\User\\IdeaProjects");
        path.setBounds(10, 40, 210, 20);
        path.setVisible(true);
        add(path);
        add(path);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == task1) {
            File file = new File(path.getText());
            JTree tree = new JTree(new SimpleModel(file));
            alg.findMaxPath(file, 0);
            alg.recursion(file, 0);
            JList list = new JList(sheets.toArray());
            String text = "";
            for (int i = 0; i < sheets.size(); i++) {
                if (i == sheets.size() - 1)
                    text += (i + 1) + "     " + sheets.get(i);
                else text += (i + 1) + "     " + sheets.get(i) + ", \n";


            }
            System.out.println(text);
            Task2_output.append(text);
        }


        if (e.getSource() == task2) {
            Task2_output.setText("");

            String[] out = Task2.Task2(Integer.parseInt(rec.getText()));
            for (int i = 0; i < out.length; i++) {
                if (out[i] != null)
                    Task2_output.append(out[i] + "\n");
            }
        }
    }


    public static void recursion(File file, int depth) {
        File[] files = file.listFiles();
        if (files == null) {
            if (depth >= maxDepth) {
                maxDepth = depth;
                System.out.println(maxDepth);
                sheets.add(file.getPath());
            }
        } else {
            for (File f : files) {
                System.out.println(f);
            }
            System.out.println();
        }
        for (int i = 0; i < files.length; i++) {
            recursion(files[i], depth + 1);
        }


    }




    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    public static void findMaxPath(File file, int depth) {
        File[] files = file.listFiles();
        if (files == null) {
            if (depth >= maxDepth) {
                maxDepth = depth;
            }
            return;
        }
        for (int i = 0; i < files.length; i++) {
            findMaxPath(files[i], depth + 1);
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
