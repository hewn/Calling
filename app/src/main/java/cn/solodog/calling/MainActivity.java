package cn.solodog.calling;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText typonumberedit;
    private ListView listView;
    private ArrayList<Map<String, Object>> listall = new ArrayList<Map<String, Object>>();
    SimpleAdapter adapter;
    int padflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typonumberedit = (EditText) findViewById(R.id.typonumber);
        listView = (ListView) findViewById(R.id.contactList);
        GetNumber.getNumber(this);
        setAdapter();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
              final Handler myhandler = new Handler() {
            @Override
            //handleMessage处理不同情况下的按钮动画
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    Animation fabcall = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fabcallgo);
                    fabcall.setDuration(350);
                    fabcall.setFillAfter(true);
                    fab.startAnimation(fabcall);
                    //在edittext无焦点（键盘未弹出）下缩小动画
                }
                if (msg.what == 0x234) {
                    fab.setImageResource(R.drawable.phone);
                    Animation fabcall = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fabcallcome);
                    fabcall.setDuration(500);
                    fabcall.setFillAfter(true);
                    fab.startAnimation(fabcall);
                    //在edittext无焦点（键盘未弹出）下放大动画
                }
                if (msg.what == 0x345) {
                    fab.setImageResource(R.drawable.pad);
                    Animation fabcall = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fabcallback);
                    fabcall.setDuration(600);
                    fabcall.setFillAfter(true);
                    fab.startAnimation(fabcall);
                    //在edittext有焦点（键盘弹出）下回归动画
                }
            }
        };
        final InputMethodManager inputManager =
                (InputMethodManager) typonumberedit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //生命输入法管理，用来根据padflag的不同情况显示或隐藏输入法

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (padflag == 0) {
                    typonumberedit.setFocusable(true);
                    typonumberedit.setFocusableInTouchMode(true);
                    typonumberedit.requestFocus();
                    inputManager.showSoftInput(typonumberedit, 0);
                    padflag = 1;
                    myhandler.sendEmptyMessage(0x123);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            myhandler.sendEmptyMessage(0x234);

                        }
                    }, 300);
                    //让edittext获取焦点，显示输入法，修改padflag为1，开启动画效果

                } else if (typonumberedit.getText().length() != 0) {
                    String num = typonumberedit.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                    Toast.makeText(MainActivity.this, "拨号辣！！", Toast.LENGTH_SHORT).show();
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                    typonumberedit.setText("");
                    padflag = 0;
                    //在有内容输入情况下拨号，重置输入框内容，修改padflag为0
                } else {
                    typonumberedit.setFocusable(false);
                    typonumberedit.setFocusableInTouchMode(false);
                    typonumberedit.requestFocus();
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0, null);
                    padflag = 0;
                    myhandler.sendEmptyMessage(0x345);
                    //取消edittext焦点，隐藏输入法，开始回归动画
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fri = GetNumber.lists.get(position).getphoneFirstl() + GetNumber.lists.get(position).getPhoneFirstn();
// /               Toast.makeText(MainActivity.this,fri, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "拨号辣！！", Toast.LENGTH_SHORT).show();
                String num = GetNumber.lists.get(position).getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });
        //点击listview内容拨号
        typonumberedit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN && typonumberedit.getText().length() != 0) {
                    String num = typonumberedit.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                    Toast.makeText(MainActivity.this, "拨号辣！！", Toast.LENGTH_SHORT).show();
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "没权限", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        //回车拨号
        typonumberedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    myhandler.post(eReturn);
                } else {
                    myhandler.post(eSearched);
                }
            }
            //after内监控edittext内容，根据是否有内容选择不同的数据更新到adapter
        });
    }


    private void setAdapter() {

        begindata(listall);
        adapter = new SimpleAdapter(MainActivity.this, listall, R.layout.phoneinfo_list, new String[]{"names", "numbers"}, new int[]{R.id.tvName, R.id.tvNumber});
        listView.setAdapter(adapter);
    }
    //给listview设置adapter

    Runnable eSearched = new Runnable() {

        @Override
        public void run() {

            String data = typonumberedit.getText().toString();

            listall.clear();

            searchdata(listall, data);

            adapter.notifyDataSetChanged();

        }
    };
    //edittext有内容情况下的数据更新
    Runnable eReturn = new Runnable() {

        @Override
        public void run() {

            String data = typonumberedit.getText().toString();

            listall.clear();

            begindata(listall);

            adapter.notifyDataSetChanged();

        }
        //editext无内容情况下的数据更新
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
    //初始电话本内容获取

    private void searchdata(ArrayList<Map<String, Object>> list, String keys) {
        for (int i = 0; i < GetNumber.lists.size(); i++) {
            Map<String, Object> tmpp = new HashMap<String, Object>();
            String first = GetNumber.lists.get(i).getPhoneFirstn();
            String number = GetNumber.lists.get(i).getPhoneNumber();
            if (first.contains(keys) || number.contains(keys)) {
                tmpp.put("names", GetNumber.lists.get(i).getPhoneName());
                tmpp.put("numbers", GetNumber.lists.get(i).getPhoneNumber());
                list.add(tmpp);
            }
        }
    }
    //关键字搜索电话本内容获取


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
