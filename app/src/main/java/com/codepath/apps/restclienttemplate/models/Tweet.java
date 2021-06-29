package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    public String body;
    public String createdAt;
    public User user;

    public static Tweet fromJson(JSONObject JsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = JsonObject.getString("text");
        Log.i("the object is ", " " +JsonObject.toString());
        tweet.createdAt = JsonObject.getString("created_at");
        tweet.user = User.fromJson(JsonObject.getJSONObject("user"));
        return tweet;
    }

    public static List<Tweet> fromJSONArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i =0; i < jsonArray.length(); i ++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
            }

        return tweets;
    }
}
