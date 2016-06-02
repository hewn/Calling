package cn.solodog.calling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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
            Toast.makeText(MainActivity.this,""+GetNumber.getnum(position),Toast.LENGTH_LONG).show();
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
