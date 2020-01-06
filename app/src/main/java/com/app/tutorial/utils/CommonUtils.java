package com.app.tutorial.utils;

import com.app.tutorial.model.Topics;

public class CommonUtils {


    private static Topics topics;

    public static Topics getTopics() {
        return topics;
    }

    public static void setTopics(Topics topics) {
        CommonUtils.topics = topics;
    }
}
