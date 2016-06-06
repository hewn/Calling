package cn.solodog.calling;

/**
 * Created by 7 on 2016/6/3.
 */
public class ConvertFirstletter {
//转换电话本首字母变成数字，如qwe换成793
    public String convert(String Fristletter)
    {
        String numletter ="";
        for(int i=0;i<Fristletter.length();i++)
        {
            switch (Fristletter.charAt(i))
            {
                case 'a':
                case 'b':
                case 'c':
                    numletter+="2";
                    break;
                case 'd':
                case 'e':
                case 'f':
                    numletter+="3";
                    break;
                case 'g':
                case 'h':
                case 'i':
                    numletter+="4";
                    break;
                case 'j':
                case 'k':
                case 'l':
                    numletter+="5";
                    break;
                case 'm':
                case 'n':
                case 'o':
                    numletter+="6";
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    numletter+="7";
                    break;
                case 't':
                case 'u':
                case 'v':
                    numletter+="8";
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    numletter+="9";
                    break;
            }
        }

        return numletter;
    }
    //转换每个phoneinfo首字母的第一个字母为数字，为了排序
    public static int convertF(String Fristletter)
    {
        int numletter=0;

            switch (Fristletter.charAt(0))
            {
                case 'a':
                    numletter=0;
                    break;
                case 'b':
                    numletter=1;
                    break;
                case 'c':
                    numletter=2;
                    break;
                case 'd':
                    numletter=3;
                    break;
                case 'e':
                    numletter=4;
                    break;
                case 'f':
                    numletter=5;
                    break;
                case 'g':
                    numletter=6;
                    break;
                case 'h':
                    numletter=7;
                    break;
                case 'i':
                    numletter=8;
                    break;
                case 'j':
                    numletter=9;
                    break;
                case 'k':
                    numletter=10;
                    break;
                case 'l':
                    numletter=11;
                    break;
                case 'm':
                    numletter=12;
                    break;
                case 'n':
                    numletter=13;
                    break;
                case 'o':
                    numletter=14;
                    break;
                case 'p':
                    numletter=15;
                    break;
                case 'q':
                    numletter=16;
                    break;
                case 'r':
                    numletter=17;
                    break;
                case 's':
                    numletter=18;
                    break;
                case 't':
                    numletter=19;
                    break;
                case 'u':
                    numletter=20;
                    break;
                case 'v':
                    numletter=21;
                    break;
                case 'w':
                    numletter=22;
                    break;
                case 'x':
                    numletter=23;
                    break;
                case 'y':
                    numletter=24;
                    break;
                case 'z':
                    numletter=25;
                    break;

            }


        return numletter;
    }
}
