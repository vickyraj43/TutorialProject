package com.app.tutorial.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.app.tutorial.R;
import com.app.tutorial.adapter.JSAdapter;
import com.app.tutorial.adapter.JSQuestionAdapter;
import com.app.tutorial.model.Questions;
import com.app.tutorial.model.Topics;
import com.app.tutorial.utils.CommonUtils;

import java.util.ArrayList;

public class JSQuestionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsquestion_details);
      //  Intent intent = getIntent();
        Topics  topics= CommonUtils.getTopics();
        if(topics!=null){
           RecyclerView recycleview = (RecyclerView)findViewById(R.id.js_question_recyclerview);
            ArrayList<Questions> questions = new ArrayList<>();

           for(Questions q: topics.getQuestions()){
               questions.add(q);
           }
            JSQuestionAdapter jsAdapter=new JSQuestionAdapter(getApplicationContext(),questions);
            recycleview.setAdapter(jsAdapter);
            recycleview.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
