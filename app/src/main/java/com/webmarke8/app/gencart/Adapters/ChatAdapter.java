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

import java.util.List;

/**
 * Created by Manzoor Hussain on 9/27/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHoder> {
    List<Chat_Object> MyMessageList;
    Context context;
    TextView senderMessage, ReceiverMessage;
    LinearLayout linearLayout, linearLayoutMessageSide;
    ImageView imageView;
    TextView textViewTime;

    public ChatAdapter(List<Chat_Object> list, Context context) {
        this.MyMessageList = list;
        this.context = context;
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
        String sender = mylist.getSendermailid().toString();

        String email = "hussain@gmail.com";


        if (sender.equals(email)) {


            // senderMessage.setBackgroundResource(R.drawable.messagebgsend);
            linearLayoutMessageSide.setBackgroundResource(R.drawable.send_message_background);
            senderMessage.setPadding(25, 5, 40, 15);
            senderMessage.setTextColor(Color.parseColor("#FDFEFC"));

            linearLayout.setGravity(Gravity.RIGHT);
        } else {
            senderMessage.setPadding(45, 5, 25, 15);
            senderMessage.setTextColor(Color.parseColor("#6A6A6A"));
            linearLayoutMessageSide.setBackgroundResource(R.drawable.send_message_background);
            linearLayout.setGravity(Gravity.LEFT);

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
            linearLayout = itemView.findViewById(R.id.messageLayout);
            linearLayoutMessageSide = itemView.findViewById(R.id.messageSideChange);
            imageView = itemView.findViewById(R.id.profile_image);
            textViewTime = itemView.findViewById(R.id.Txttime);


        }
    }

}