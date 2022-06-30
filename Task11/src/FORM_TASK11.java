import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FORM_TASK11 extends JFrame {


    private JPanel main_panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField res_1;
    private JTextField res_2;
    private JButton выполнить1Button;
    private JButton выполнить2Button;
    private JTextField get_from_file_1;
    private JTextField get_from_file_2;
    private JButton button_file_1;
    private JButton button_file_2;

/*
Задание №1.	Выбрать из текста все слова без повторений,
содержащие 3 и более одинаковые буквы. Разделителями слов считаются любые символы,
отличные от букв А-Я, A-Z и цифр.

Задание №2.	Выбрать из текста самое длинное предложение.
Длина предложения считается по количеству слов.
Словом считается непрерывная последовательность символов (строчных и прописных) А-Я, A-Z и цифр.
Концом предложения считаются символы точка, '!' и '?'.
Началом предложения – любой символ, отличный от данных трех и пробела, первый после конца предыдущего предложения (или вообще первый в тексте).







 */










    public FORM_TASK11() {






        выполнить1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str;
                str = textField1.getText();

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
                res_1.setText(String.valueOf(secondList));





            }
        });

        button_file_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String str = " ";
                String fname = "";
                fname = get_from_file_1.getText();


                Scanner in = null;
                try {
                    in = new Scanner(new File(fname));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                while(in.hasNext())
                    str += in.nextLine() + "\r\n";
                in.close();

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
                res_1.setText(String.valueOf(secondList));




            }
        });


        выполнить2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text;

                String[] textArray;

                int sentenceCount = 0;
                int wordsCount = 0;
                int wordsCounttmp = 0;
                int indexSentenceWord = 0;
                int lettersCount = 0;
                int lettersCounttmp = 0;
                int indexSentenceLetter = 0;

                text = textField2.getText();

                textArray = text.split("[.?!]");
                sentenceCount = textArray.length;

                for(int i = 0; i < sentenceCount; i++) {
                    wordsCounttmp = textArray[i].split(" ").length;

                    if(wordsCounttmp > wordsCount) {
                        wordsCount = wordsCounttmp;
                        indexSentenceWord = i;
                    }
                    String[] wordsArr = textArray[i].split(" ");
                    for(String word : wordsArr) {
                        lettersCounttmp = lettersCounter(word);

                        if(lettersCounttmp > lettersCount) {
                            lettersCount = lettersCounttmp;
                            indexSentenceLetter = i;
                        }
                    }
                }



                res_2.setText(textArray[indexSentenceWord]);

            }
        });


        button_file_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fname_2 = " ";
                fname_2 = get_from_file_2.getText();

                String text;

                String[] textArray;

                int sentenceCount = 0;
                int wordsCount = 0;
                int wordsCounttmp = 0;
                int indexSentenceWord = 0;
                int lettersCount = 0;
                int lettersCounttmp = 0;
                int indexSentenceLetter = 0;

                text = textField2.getText();

                Scanner scan = null;
                try {
                    scan = new Scanner(new File(fname_2));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                Scanner in = null;
                try {
                    in = new Scanner(new File(fname_2));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                while(in.hasNext())
                    text += in.nextLine() + "\r\n";
                in.close();


                textArray = text.split("[.?!]+");
                sentenceCount = textArray.length;

                for(int i = 0; i < sentenceCount; i++) {
                    wordsCounttmp = textArray[i].split(" ").length;

                    if(wordsCounttmp > wordsCount) {
                        wordsCount = wordsCounttmp;
                        indexSentenceWord = i;
                    }
                    String[] wordsArr = textArray[i].split(" ");
                    for(String word : wordsArr) {
                        lettersCounttmp = lettersCounter(word);

                        if(lettersCounttmp > lettersCount) {
                            lettersCount = lettersCounttmp;
                            indexSentenceLetter = i;
                        }
                    }
                }



                res_2.setText(textArray[indexSentenceWord]);


            }
        });


    }


























    private static int lettersCounter(String word) {


        int num = 0;

        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я]+");
        Matcher m = p.matcher(word);

        while(m.find()) {
            num++;
        }

        return num;
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













    public static void main(String[] args) {
        FORM_TASK11 form = new FORM_TASK11();
        form.setContentPane(new FORM_TASK11().main_panel);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setVisible(true);

    }







}
