package com.app.tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.tutorial.R;
import com.app.tutorial.model.Topics;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JSAdapter extends RecyclerView.Adapter<JSAdapter.JSViewHolder> {
    Context mContext;
    List<Topics> topicsList;
    JSClickListener listener;

    public JSAdapter(Context mContext, List<Topics> topicsList,JSClickListener listener) {
        this.mContext = mContext;
        this.topicsList = topicsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public JSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.topics_item,viewGroup,false);
        return new JSViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull JSViewHolder jsViewHolder, final int position) {
        jsViewHolder.topics.setText(topicsList.get(position).getType());
        jsViewHolder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(topicsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

  /*  @Override
    public void onBindViewHolder(@NonNull JSViewHolder jsViewHolder, int position, @NonNull List payloads) {
       // super.onBindViewHolder(holder, position, payloads);

        jsViewHolder.topics.setText(topicsList.get(position).getType());

    }*/

    public class JSViewHolder extends RecyclerView.ViewHolder{

        TextView topics;
        LinearLayout linearlayout;

       public JSViewHolder(@NonNull View itemView){
           super(itemView);
            topics = itemView.findViewById(R.id.tv_topics);
           linearlayout =(LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }

    public interface JSClickListener{
       public void onItemClick(Topics topics);
    }
}
