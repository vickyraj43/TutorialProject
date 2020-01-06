package com.app.tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.tutorial.R;
import com.app.tutorial.model.Questions;
import com.app.tutorial.model.Topics;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JSQuestionAdapter extends RecyclerView.Adapter<JSQuestionAdapter.JSQuestionViewHolder> {
    Context mContext;
    List<Questions> questionsList;

    public JSQuestionAdapter(Context mContext, List<Questions> questionsList) {
        this.mContext = mContext;
        this.questionsList = questionsList;
    }

    @NonNull
    @Override
    public JSQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.question_item,viewGroup,false);
        return new JSQuestionViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull JSQuestionViewHolder jsQuestionViewHolder, final int position) {
        Questions questions =  questionsList.get(position);
        jsQuestionViewHolder.question.setText(questions.getQid()+"> "+questions.getQuestion());
        jsQuestionViewHolder.answer.setText(questions.getAnswer());

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

  /*  @Override
    public void onBindViewHolder(@NonNull JSViewHolder jsViewHolder, int position, @NonNull List payloads) {
       // super.onBindViewHolder(holder, position, payloads);

        jsViewHolder.topics.setText(topicsList.get(position).getType());

    }*/

    public class JSQuestionViewHolder extends RecyclerView.ViewHolder{

        TextView question,answer;


       public JSQuestionViewHolder(@NonNull View itemView){
           super(itemView);
           question = itemView.findViewById(R.id.tv_question);
           answer =itemView.findViewById(R.id.tv_answer);
        }
    }
}
