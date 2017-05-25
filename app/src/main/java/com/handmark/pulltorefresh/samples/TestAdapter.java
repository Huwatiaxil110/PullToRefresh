package com.handmark.pulltorefresh.samples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/10.
 */

public class TestAdapter extends BaseAdapter{
    String[] contents = {"Hello0", "Hello1", "Hello2", "Hello3"};
    Context mContext;

    public TestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TestAdapter(String[] contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return contents.length;
    }

    @Override
    public String getItem(int position) {
        return contents[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_one, null);

            mHolder = new ViewHolder();
            mHolder.tvTxt = (TextView) convertView.findViewById(R.id.tv_txt);

            convertView.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.tvTxt.setText(contents[position]);

        return convertView;
    }

    class ViewHolder{
        TextView tvTxt;
    }

    /** add one more*/
    public void addOne(){
        String[] temps = new String[contents.length + 4];
        for(int i=0;i<temps.length;i++){
            temps[i] = contents[i%contents.length];
        }
        contents = temps;

        notifyDataSetChanged();
    }
}

































