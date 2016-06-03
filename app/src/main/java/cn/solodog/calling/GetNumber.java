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
        String phoneFirstl;
        String phoneFirstn=null;
        FirstLetterUtil firstLetterUtil=new FirstLetterUtil();
        ConvertFristletter convertFristletter=new ConvertFristletter();
        while (cursor.moveToNext())
        {
            phoneName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            phoneNumber=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            phoneFirstl=firstLetterUtil.getFirstLetter(phoneName);
            phoneFirstn=convertFristletter.convert(phoneFirstl);
            PhoneInfo info=new PhoneInfo(phoneName,phoneNumber,phoneFirstl,phoneFirstn);

            lists.add(info);

        }
        return null;
    }


}
