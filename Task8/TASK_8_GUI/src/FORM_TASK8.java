import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


//Считывание из файла происходит правильно. Необходимо реализовать вывод массива в JTable

//Считывание массива из JTable:
//  for (int i=0;i<3;i++){
//                    for(int j=0;j<3;j++){
//                        array[i][j]=Integer.parseInt(table1.getValueAt(i, j).toString());
//                    }
//                }


// Задание №1  Строки, элементы которых не убывают
// (т.е. образуют неубывающую последовательность чисел) переместить в начало (вверх),
// сохранив при этом взаимное расположением перемещаемых строк.

// Задание №2 Создать новый двумерный массив чисел,
// исключив из переданного двумерного массива все строки и столбцы,
// содержащие максимальные и минимальные элементы среди
// всех элементов переданного массива.


public class FORM_TASK8  extends JFrame

{
int size = 3;
    private JTable table2 = new JTable (size, size);
    private JTable table3 = new JTable ();
    private JPanel main_panel;
    private JButton загрузитьМассивИзФайлаButton;
    private JButton ввестиСвойМассивButton;
    private JTextField text_field;
    private JLabel label1;
    private JButton произвестиСортировкуЗадание1Button;
    private JButton button_2;
    private JButton ввестиДанныеДляМассиваButton;
    private JScrollPane scrollPane = new JScrollPane(table2);

    public FORM_TASK8()

    {
        table2.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );

        this.setSize(800, 600);
        int rows = 3, columns = 3;

        JFrame frame = new JFrame("Считанный массив:");
        JPanel panel = new JPanel(new BorderLayout());
        frame.setSize(new Dimension(500,500));
        panel.add(table2, BorderLayout.CENTER);

        JFrame frame_2 = new JFrame("Введенный массив");
        JPanel panel_2 = new JPanel(new BorderLayout());
        frame_2.setSize(new Dimension(500,500));
        table3 = new JTable(size, size);
        JScrollPane scrollPane_2 = new JScrollPane(table3);
        table3.setFillsViewportHeight(true);
        panel_2.add(scrollPane_2, BorderLayout.CENTER);
        frame_2.getContentPane().add(panel_2);

        int[][] array = new int[rows][columns];
        int[][] result_array = new int[rows][columns];


        загрузитьМассивИзФайлаButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String R;
                R = text_field.getText(); //Имя файла либо путь к файлу
                try {
                    file_read_array(array, R);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //Вывод массива на JTable

                for(int i = 0; i < array.length; i++) {
                 for(int j = 0; j < array[0].length; j++) {
                     table2.setValueAt(array[i][j], i, j);


                 }
                }
                frame.add(panel);
                frame.setVisible(true);
            }
        });

        произвестиСортировкуЗадание1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int[][] result_array_2 = new int[rows][columns];
                result_array_2 = customSort(array);

                try {
                    file_write_array(result_array_2, "output_1.txt", rows, columns);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int a = result_array_2.length;
                int b = result_array_2[0].length;



            }
        });

        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[][] result_array_3 = new int[rows] [columns];
                result_array_3 = SOLUTION_2(array, result_array_3.length, result_array_3[0].length);

                try {
                    file_write_array(result_array_3, "output_2.txt", result_array_3.length, result_array_3[0].length);
               } catch (Exception ex) {ex.printStackTrace();
                }





            }
        });

        ввестиСвойМассивButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               int[][] result_array_4 = new int[rows][columns];

            Object[][] res_ar_4 = new Object[rows][columns];

            res_ar_4 = getTableData(table3);

                try {
                    file_write_array(res_ar_4, "input_2.txt", rows, columns);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });

        ввестиДанныеДляМассиваButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                frame_2.pack();
                frame_2.setVisible(true);
            }
        });
    }


    //Сортировка массива
    public int[][] customSort ( int[][] matrix){
        return Arrays.stream(matrix)
                .sorted(Comparator.comparing(this::isDecrease))
                .toArray(int[][]::new);
    }

    //Проверка строк и столбцов
    private boolean isDecrease ( int[] row){
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] > row[i + 1]) return true;
        }
        return false;
    }

    //Вывод массива
    private void showMatrix ( int[][] matrix){
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    //Чтение массива из файла
    static public void file_read_array(int arr[][], String NAME) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(NAME)));
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

    static public void file_write_array (Object arr[][], String Path, int rows, int columns) throws Exception {
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
    public static int[][] SOLUTION_2(int[][] array, int rows,int columns) {
        boolean[] minRows = null, maxRows = null;
        boolean[] minCols = null, maxCols = null;

        Integer min = null;
        Integer max = null;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (null == min || array[i][j] < min) {
                    minRows = new boolean[rows];
                    minRows[i] = true;
                    minCols = new boolean[columns];
                    minCols[j] = true;
                    min = array[i][j];
                } else if (array[i][j] == min) {
                    minRows[i] = true;
                    minCols[j] = true;
                }

                if (null == max || array[i][j] > max) {
                    maxRows = new boolean[rows];
                    maxRows[i] = true;
                    maxCols = new boolean[columns];
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
        for (int i = 0; i < columns; i++) {
            if (minCols[i] || maxCols[i]) {
                colsToDelete++;
            }
        }

        if (rows == rowsToDelete || columns == colsToDelete) {
            return new int[1][0];
        }

        int[][] result = new int[3][3];

        for (int i = 0, r = 0; i < rows; i++) {
            if (minRows[i] || maxRows[i])
                continue; // пропустить строку, содержащую минимум или максимум
            for (int j = 0, c = 0; j < columns; j++) {
                if (minCols[j] || maxCols[j])
                    continue; // пропустить столбец, содержащий минимум или максимум
                result[r][c++] = array[i][j];
            }
            r++;
        }

        return result;
    }

    //Получение данных в массив с клавиатуры
    public Object[][] getTableData (JTable table) {
        TableModel dtm = table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = dtm.getValueAt(i,j);
        return tableData;
    }





    public static void main(String[] args) {
        FORM_TASK8 form = new FORM_TASK8();
        form.setContentPane(new FORM_TASK8().main_panel);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.pack();
        form.setVisible(true);

    }



}
