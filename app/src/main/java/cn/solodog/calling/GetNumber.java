package cn.solodog.calling;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 7 on 2016/6/2.
 */
public class GetNumber {

    public static List<PhoneInfo> lists=new ArrayList<PhoneInfo>();

    public static String getNumber(Context context)
    {
        Cursor cursor=context.getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
        String phoneNumber;
        String phoneName;
        String phoneFirst;
        FirstLetterUtil firstLetterUtil=new FirstLetterUtil();
        while (cursor.moveToNext())
        {
            phoneName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            phoneNumber=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            phoneFirst=firstLetterUtil.getFirstLetter(phoneName);
            PhoneInfo info=new PhoneInfo(phoneName,phoneNumber,phoneFirst);

            lists.add(info);

        }
        return null;
    }
    public static String getnum(int position)
    {
        return lists.get(position).getPhoneNumber();
    }
    public static String getname(int position)
    {
        return lists.get(position).getPhoneName();
    }
}
