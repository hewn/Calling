package cn.solodog.calling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity {
    private EditText typonumberedit;
    private ListView listView;
    private List<Map<String,Object>> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typonumberedit = (EditText) findViewById(R.id.typonumber);
        listView=(ListView)findViewById(R.id.contactList);
        GetNumber.getNumber(this);
        //listview设置adapter
        List<Map<String, Object>> listss = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < GetNumber.lists.size();i++) {
            Map<String, Object> list = new HashMap<String, Object>();
            list.put("names",GetNumber.getname(i));
            list.put("numbers",GetNumber.getnum(i));
            listss.add(list);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,listss,R.layout.phoneinfo_list,new String[]{"names","numbers"}, new int[]{R.id.tvName,R.id.tvNumber});
        listView.setAdapter(adapter);
        //listview设置adapter

        //点击listview内容---------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"拨号啦！！", Toast.LENGTH_LONG).show();
                String num = GetNumber.getnum(position);
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
    }




}
