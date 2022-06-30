import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FORM extends JFrame {
    private JButton button;
    private JTextField input_1;
    private JTextArea solution;
    private JPanel main_panel;
    private JTextArea result1;
    private JTextArea result2;
    private JButton button_clear;
    private JTextField output;

    //В методе ниже события. В этих событиях прописать СЧИТЫВАНИЕ элементов массива
    public FORM() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Hello World!");
                //ТУТ РЕАЛИЗОВАНЫ РАСЧЕТЫ. НИЧЕ НЕ ТРОГАЙ КОГДА ЗАРАБОТАЕТ
                String s;
                s = result2.getText();
                //Ввод массива
                String arr = new String(input_1.getText());
                String[] arrSplit = arr.split(",");
                int array[] = new int[arrSplit.length];
                for(int i = 0; i < arrSplit.length; i++) {
                    array[i] = Integer.valueOf(arrSplit[i]);
                }
                //Реализация функций
                int res_1 = solution(array, 5);
               result1.setText(Integer.toString(res_1));
               result1.setText("Кол-во монотонно \n Убывающих промежутков: " + result1.getText());
               //result2.setText(solution_2(array, 5));
                //Вывод массива
                String ARRAY;
                ARRAY = output.getText();

                for(int i = 0; i < arrSplit.length; i++) {
                    ARRAY = ARRAY + array[i];
                    output.setText("{ " + ARRAY + " }");
                }
                //Вывод второй функции
                int curLength = 0;
                int maxLength = 0;
                int indexBegin = -1;
                boolean isOpenSeq = false;
                for (int i = 0; i < array.length - 1; i++) {
                    if (isTrueSeq(array[i], array[i + 1])) {
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
                    indexBegin = array.length - curLength - 1;
                    maxLength = curLength;
                }
              //  System.out.print("\nЭлементы подпоследовательности: {");

                for (int i = indexBegin; i < indexBegin + maxLength + 1; i++) {
                    s = s + array[i];
                }

                //Вот тут именно вывод
                result2.setText(s);
result2.setText("Элементы подпоследовательности \n различных элементов:  {" + result2.getText() + "}");

            }
        });
        button_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result1.setText(" ");
                result2.setText(" ");
                output.setText(" ");
            }
        });
    }

    //Функция определения убывающих промежутков
    public static int solution(int[] a, int size) {

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
        return count;
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

    public static void main(String[] args) {
        FORM form = new FORM();
        form.setContentPane(new FORM().main_panel);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setVisible(true);

    }

}


