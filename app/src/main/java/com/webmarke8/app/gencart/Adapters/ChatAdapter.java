package com.webmarke8.app.gencart.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webmarke8.app.gencart.Objects.Chat_Object;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHoder> {
    List<Chat_Object> MyMessageList;
    Context context;
    TextView senderMessage;
    TextView SenderName, ReciverName, SenderTime, ReciverTime;
    ImageView Sender, Reciver;
    LinearLayout messageLayout;
    MyApplication myApplication;

    public ChatAdapter(List<Chat_Object> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
        myApplication = (MyApplication) this.context.getApplicationContext();
    }

    @Override
    public ChatAdapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_sender, parent, false);
        ChatAdapter.MyHoder myHoder = new ChatAdapter.MyHoder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(ChatAdapter.MyHoder holder, final int position) {


        final Chat_Object mylist = MyMessageList.get(position);
        senderMessage.setText(mylist.getMessage().toString());
        String sender = mylist.getSenderEmail().toString();
        String email = myApplication.getLoginSessionCustomer().getSuccess().getUser().getEmail();

        ReciverName.setText(mylist.getReciverName());
        SenderName.setText(mylist.getSenderName());
        SenderTime.setText(mylist.getSendTime());
        ReciverTime.setText(mylist.getSendTime());

        if (sender.equals(email)) {

            senderMessage.setBackgroundResource(R.drawable.sender_layout);
            senderMessage.setTextColor(Color.parseColor("#FDFEFC"));
            messageLayout.setGravity(Gravity.RIGHT);
            Reciver.setVisibility(View.INVISIBLE);
            ReciverName.setVisibility(View.INVISIBLE);
            ReciverTime.setVisibility(View.INVISIBLE);
        } else {
            senderMessage.setTextColor(Color.parseColor("#6A6A6A"));
            senderMessage.setBackgroundResource(R.drawable.reciver_layout);
            messageLayout.setGravity(Gravity.LEFT);
            Sender.setVisibility(View.INVISIBLE);
            SenderName.setVisibility(View.INVISIBLE);
            SenderTime.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try {
            if (MyMessageList.size() == 0) {

                arr = 0;

            } else {

                arr = MyMessageList.size();
            }


        } catch (Exception e) {


        }

        return arr;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyHoder extends RecyclerView.ViewHolder {


        public MyHoder(View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.txtsenderMessage);
            SenderName = (TextView) itemView.findViewById(R.id.NameSender);
            ReciverName = (TextView) itemView.findViewById(R.id.NameReciver);
            SenderTime = (TextView) itemView.findViewById(R.id.TimeSender);
            ReciverTime = (TextView) itemView.findViewById(R.id.TimeReciver);
            Sender = (ImageView) itemView.findViewById(R.id.ImageSender);
            Reciver = (ImageView) itemView.findViewById(R.id.ImageReciver);
            messageLayout = (LinearLayout) itemView.findViewById(R.id.messageLayout);


        }
    }

}