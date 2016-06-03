package cn.solodog.calling;

/**
 * Created by 7 on 2016/6/3.
 */
public class ConvertFristletter {

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
}
