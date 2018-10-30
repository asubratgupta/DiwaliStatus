package com.subratgupta.diwalistatus;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<MessageType> messageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView message;
        public ImageButton copy_btn,wa_btn,share_btn;

        public MyViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.message_view);
            wa_btn = (ImageButton) view.findViewById(R.id.wa_btn);
            copy_btn = (ImageButton) view.findViewById(R.id.copy_btn);
            share_btn = (ImageButton) view.findViewById(R.id.share_btn);
        }
    }


    public MessageAdapter(List<MessageType> messageList) {
        this.messageList = messageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MessageType message = messageList.get(position);
        holder.message.setTextColor(Color.parseColor(MainActivity.randomColor()));
        holder.message.setText(message.getMessage());
        holder.wa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity.wa_share(message.getMessage()+"\nhttps://goo.gl/BLGm4e");
            }
        });
        holder.copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.copy(message.getMessage()+"\nhttps://goo.gl/BLGm4e");
            }
        });
        holder.share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.share(message.getMessage()+"\nhttps://goo.gl/BLGm4e");
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}