package cn.solodog.calling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 7 on 2016/6/2.
 */
public class PhoneAdapter extends BaseAdapter {
    private List<PhoneInfo> lists;
    private Context context;

    public PhoneAdapter(List<PhoneInfo> lists,Context context)
    {
        this.lists=lists;
        this.context=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.phoneinfo_list,null);
            viewHolder=new ViewHolder();
            viewHolder.tvName=(TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvNumber=(TextView)convertView.findViewById(R.id.tvNumber);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tvName.setText(lists.get(position).getPhoneName());
        viewHolder.tvNumber.setText(lists.get(position).getPhoneNumber());
        return convertView;
    }
    private class ViewHolder
    {
        TextView tvName;
        TextView tvNumber;
    }
}

