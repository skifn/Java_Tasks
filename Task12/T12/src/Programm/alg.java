package Programm;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


public class alg {
    static int  maxDepth=0;
    public static ArrayList<String> sheets = new ArrayList<>();
    public static void recursion(File file, int depth){
        File[] files = file.listFiles();
        if(files == null)
        {
            if(depth>=maxDepth) {
                maxDepth = depth;
                System.out.println(maxDepth);
                 sheets.add(file.getPath());
            }
            return;
        } else {for (File f : files) {
            System.out.println(f);
        }
        System.out.println();
        }
        for(int i = 0; i < files.length;i++)
        {
            recursion(files[i], depth+1);
        }
    }
    public static void findMaxPath(File file, int depth){
        File[] files = file.listFiles();
        if(files == null)
        {
            if(depth>=maxDepth) {
                maxDepth = depth;
            }
            return;
        }
        for(int i = 0; i < files.length;i++)
        {
            findMaxPath(files[i], depth+1);
        }
    }

}
