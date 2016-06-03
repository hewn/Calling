package cn.solodog.calling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText typonumberedit;
    private ListView listView;
    private ArrayList<Map<String, Object>> listall=new ArrayList<Map<String, Object>>();
    SimpleAdapter adapter;
    Handler myhandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typonumberedit = (EditText) findViewById(R.id.typonumber);
        listView = (ListView) findViewById(R.id.contactList);
        GetNumber.getNumber(this);
        setAdapter();

        //listview设置adapter

        //listview设置adapter

        //点击listview内容---------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fri = GetNumber.lists.get(position).getphoneFirstl() + GetNumber.lists.get(position).getPhoneFirstn();
//                Toast.makeText(MainActivity.this,fri, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "拨号啦！！", Toast.LENGTH_LONG).show();
                String num = GetNumber.lists.get(position).getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });

        //点击listview内容拨号-----------
        //回车拨号------
        typonumberedit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String num = typonumberedit.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        //回车拨号-------
        typonumberedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


//                    SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, listsall, R.layout.phoneinfo_list, new String[]{"names", "numbers"}, new int[]{R.id.tvName, R.id.tvNumber});
//                    listView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0)
                {
                    myhandler.post(eReturn);
                }
                else
                {
                    myhandler.post(eSearched);
                }
            }
        });
    }

    private void setAdapter() {

        begindata(listall);
        adapter = new SimpleAdapter(MainActivity.this, listall, R.layout.phoneinfo_list, new String[]{"names", "numbers"}, new int[]{R.id.tvName, R.id.tvNumber});
        listView.setAdapter(adapter);
    }

    Runnable eSearched = new Runnable() {

        @Override
        public void run() {

            String data = typonumberedit.getText().toString();

            listall.clear();

            searchdata(listall, data);

            adapter.notifyDataSetChanged();

        }
    };
    Runnable eReturn = new Runnable() {

        @Override
        public void run() {

            String data = typonumberedit.getText().toString();

            listall.clear();

            begindata(listall);

            adapter.notifyDataSetChanged();

        }
    };


    private void begindata(ArrayList<Map<String, Object>> lists) {
        List<Map<String, Object>> listsall = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < GetNumber.lists.size(); i++) {
            Map<String, Object> list = new HashMap<String, Object>();
            list.put("names", GetNumber.lists.get(i).getPhoneName());
            list.put("numbers", GetNumber.lists.get(i).getPhoneNumber());
            lists.add(list);
        }
    }

    private void searchdata(ArrayList<Map<String, Object>> list, String keys) {
        for(int i=0;i<GetNumber.lists.size();i++)
        {
            Map<String,Object> tmpp=new HashMap<String,Object>();
            String first=GetNumber.lists.get(i).getPhoneFirstn();
            String number=GetNumber.lists.get(i).getPhoneNumber();
            if (first.contains(keys)||number.contains(keys))
            {
                tmpp.put("names",GetNumber.lists.get(i).getPhoneName());
                tmpp.put("numbers",GetNumber.lists.get(i).getPhoneNumber());
                list.add(tmpp);
            }
        }
    }


}
