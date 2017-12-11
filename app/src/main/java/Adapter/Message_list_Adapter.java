package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.mywx.R;

import java.util.ArrayList;

import Bean.Messages;

/**
 * Created by hasee on 2017/11/23.
 */

public class Message_list_Adapter extends BaseAdapter{
    ArrayList<Messages> list ;
    Context mContext;

    public Message_list_Adapter(ArrayList<Messages> list, Context mContext){
        this.list=list;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_list,parent,false);
            holder.headImage=(ImageView)convertView.findViewById(R.id.image_message);
            holder.userName=(TextView)convertView.findViewById(R.id.user_name);
            holder.Meaasge=(TextView)convertView.findViewById(R.id.message);
            convertView.setTag(holder);   //将Holder存储到convertView中
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.headImage.setText("订单ID："+mData.get(position).getOrders_id());
        holder.userName.setText("用户ID："+list.get(position).getM_FromUserID());
        holder.Meaasge.setText(""+list.get(position).getM_PostMessages());
        return convertView;
    }
    static class ViewHolder{
        ImageView headImage;
        TextView userName;
        TextView Meaasge;
    }
    public void add(Messages data) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(data);
        notifyDataSetChanged();
    }
}
