package com.app.tutorial.activities;

import android.content.Intent;
import android.os.Bundle;

import com.app.tutorial.R;
import com.app.tutorial.adapter.JSAdapter;
import com.app.tutorial.model.Chapter;
import com.app.tutorial.model.Questions;
import com.app.tutorial.model.Topics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JSQuestionActivity extends AppCompatActivity {

    //@BindView(R.id.js_recyclerview)
    RecyclerView jsrecycleview;

    private List<Topics> topicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsquestion_activity);
       // ButterKnife.bind(this);
        jsrecycleview = findViewById(R.id.js_recyclerview);
        getJsonData();
        JSAdapter jsAdapter=new JSAdapter(getApplicationContext(),topicsList,listener);
       jsrecycleview.setAdapter(jsAdapter);
       jsrecycleview.setLayoutManager(new LinearLayoutManager(this));

    }
    JSAdapter.JSClickListener listener = new JSAdapter.JSClickListener() {
        @Override
        public void onItemClick(Topics items) {
            Intent intent = new Intent(getApplicationContext(),JSQuestionDetails.class);
            intent.putExtra("topics",items);
            startActivity(intent);
        }
    };
    public Chapter getJsonData(){
        topicsList = new ArrayList<>();
        Chapter chapter = new Chapter();
        String jsonString;
        JSONObject obj,objTopic,objQuestions;
        Topics topics = null;
        Topics[] topicsArray;
        Questions questions;
        Questions[] questionsArray;

        try{
            InputStream is = getAssets().open("jsinterview.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");

            //      String jsonLocation = AssetJSONFile("formules.json", getApplicationContext());


            JSONArray jasonChapterArray = new JSONArray(jsonString);
            for(int r = 0; r<jasonChapterArray.length(); r++) {
                obj =jasonChapterArray.getJSONObject(r);
                chapter.setId(obj.getInt("id"));
                chapter.setType(obj.getString("type"));
                chapter.setName(obj.getString("name"));
                chapter.setQuestionimage(obj.getString("questionimage"));
                chapter.setAnswerimage(obj.getString("answerimage"));

                JSONArray jasonTopicArray = obj.getJSONArray("data");
                // JSONArray jasonTopicArray = new JSONArray(obj.get("data").toString());
                topicsArray = new Topics[jasonTopicArray.length()];
                for (int i = 0; i < jasonTopicArray.length(); i++) {
                    objTopic = jasonTopicArray.getJSONObject(i);
                    topics = new Topics();
                    topics.setId(objTopic.getString("id"));
                    topics.setType(objTopic.getString("type"));
                    topics.setName(objTopic.getString("name"));

                    JSONArray jasonQuestionArray = objTopic.getJSONArray("data");
                    // JSONArray jasonQuestionArray = new JSONArray(obj.get("data").toString());
                    questionsArray = new Questions[jasonQuestionArray.length()];
                    for (int j = 0; j < jasonQuestionArray.length(); j++) {
                        objQuestions = jasonQuestionArray.getJSONObject(j);
                        questions = new Questions();
                        questions.setId(objQuestions.getString("id"));
                        questions.setQid(objQuestions.getString("qid"));
                        questions.setQuestion(objQuestions.getString("question"));
                        questions.setAnswer(objQuestions.getString("answer"));
                        //topics.setQuestions(questions);
                        questionsArray[j] = questions;
                    }
                    topics.setQuestions(questionsArray);
                    topicsArray[i] = topics;
                    Topics t = new Topics();
                    t.setType("tttt");
                    topicsList.add(topics);//topicsList.add(t);
                }

                chapter.setTopics(topicsArray);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  chapter;
    }
}
