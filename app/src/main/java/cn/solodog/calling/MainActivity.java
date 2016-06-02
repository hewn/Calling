package cn.solodog.calling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText typonumberedit;
    private ListView listView;
    private PhoneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetNumber.getNumber(this);
        initview();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + GetNumber.getnum(position), Toast.LENGTH_LONG).show();
            }
        });
        typonumberedit = (EditText) findViewById(R.id.typonumber);
        typonumberedit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String num = typonumberedit.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                      Toast.makeText(MainActivity.this,"没权限",Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }




private void initview()
    {
        listView=(ListView)findViewById(R.id.contactList);
        adapter=new PhoneAdapter(GetNumber.lists,this);
        listView.setAdapter(adapter);
    }
}
