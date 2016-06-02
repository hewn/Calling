package cn.solodog.calling;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7 on 2016/6/2.
 */
public class Quicksearch {
    private String keys;
    public List<PhoneInfo> Quicksearch(String keys)
    {
        List<PhoneInfo> tmp=new ArrayList<PhoneInfo>();
        for(int i=0;i<GetNumber.lists.size();i++)
        {
            if (keys==GetNumber.getFrist(i))
            {
                tmp.add(GetNumber.getinfo(i));
            }
        }
        return tmp;
    }
}

