package Programm;

public class Task2 {
    public static int maxScale;
    public static String[] out;

    public static String[] Task2(int scale)
    {
        out = new String[220];
        maxScale = scale;

        String square[] = new String[2];
        square[0] = (" /\\ ");
        square[1] = ("/__\\");
        out[0] = out[1] = "";
        for(int i = 0; i < Math.pow(2,maxScale)-2;i++)
        {
            out[0]+=" ";
        }
        out[0]+=square[0];
        for(int i = 0; i < Math.pow(2,maxScale)-2;i++)
        {
            out[1]+=" ";
        }
        out[1]+=square[1];

        Task2_rec(1, square,2);
        return out;
    }

    public static void Task2_rec(int scale, String square[],int number)
    {
        if(scale == maxScale)
            return;

        for(int i = 0; i < Math.pow(2,scale);i++)
        {
            out[i+number] = "";
            int c = (int) (2*(Math.pow(2,maxScale-scale)-2)*scale);

            if(maxScale == 5 && scale ==3) c+=4;
            if(maxScale == 6 && scale ==3) c+=12;
            if(maxScale == 6 && scale ==4) c+=16;
            if(maxScale == 7 && scale ==3) c+=28;
            if(maxScale == 7 && scale ==4) c+=48;
            if(maxScale == 7 && scale ==5) c+=44;

            for(int j  =0; j < c;j++)
                    out[i+number]+=" ";
            out[i+number]+=square[i]+square[i];
        }

        int sqLen = square.length;
        String[] newSq = new String[sqLen*2];

        for(int i = 0; i<square.length;i++)
        {
            newSq[i]= "";
            for(int j = 0 ;j < square.length;j++)
            {
                  newSq[i]+=" ";
            }
            newSq[i]+=square[i];
            for(int j = 0 ;j < square.length;j++)
            {
                newSq[i]+=" ";
            }
        }
        for(int i = 0; i<square.length;i++)
        {
            newSq[i+square.length]= "";
            newSq[i+square.length]+=square[i]+square[i];
        }

        Task2_rec(scale+1,newSq, number*2);
    }
}
