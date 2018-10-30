package com.subratgupta.diwalistatus;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<MessageType> messageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView message;
        public ImageButton copy_btn;

        public MyViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.message_view);
            copy_btn = (ImageButton) view.findViewById(R.id.copy_btn);
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
        holder.copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.copy(message.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}