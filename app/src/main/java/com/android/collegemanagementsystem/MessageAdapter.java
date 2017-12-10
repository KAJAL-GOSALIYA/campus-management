package com.android.collegemanagementsystem;

/**
 * Created by Dell on 02-07-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageModelHolder> {

    ArrayList<MessageModel> messageModelArrayList = new ArrayList<>();
    Context c;
    @Override
    public MessageModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_contacts,parent,false);
        MessageModelHolder messageModelHolder = new MessageModelHolder(view);
        return messageModelHolder;
    }

    @Override
    public void onBindViewHolder(final MessageModelHolder holder, int position) {

        Log.d("onBVH", "reached");
        holder.tvName.setText(messageModelArrayList.get(position).getTextName());
        holder.tvNumber.setText(messageModelArrayList.get(position).getTextNumber());
        holder.tvEmail.setText(messageModelArrayList.get(position).getTextEmail());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Log.d("onBVH ", "Pos: " + position);
            }
        });
/*        holder.tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=((TextView) v).getText().toString();
                String uri = "tel:" + num;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                c.startActivity(intent);
            }
        });*/
        holder.tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return messageModelArrayList.size();
    }

    public class MessageModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName;
        TextView tvNumber;
        TextView tvEmail;
        private ItemClickListener clickListener;
        public MessageModelHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvNumber = (TextView)itemView.findViewById(R.id.tvContact);
            tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            itemView.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getLayoutPosition(), false);
        }

    }

    public MessageAdapter(Context c, ArrayList<MessageModel> messageModelArrayList) {
        this.c = c;
        this.messageModelArrayList = messageModelArrayList;
    }
}