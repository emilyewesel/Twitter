package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public String imageUrl;

    //empty constrcutor for Parcel
    public void Tweet (){

    }
    public static Tweet fromJson(JSONObject JsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = JsonObject.getString("text");
        Log.i("the object is ", " " +JsonObject.toString());
        tweet.createdAt = JsonObject.getString("created_at");
        tweet.user = User.fromJson(JsonObject.getJSONObject("user"));
        JSONObject entities = JsonObject.getJSONObject("entities");

        if (entities.has("media")) {
            tweet.imageUrl = entities.getJSONArray("media").getJSONObject(0).getString("media_url_https");
            //JSONArray array = entities.getJSONArray("media");
            //tweet.imageUrl = array.getJSONObject(0).getString("media_url_https");

        }
        else{
            tweet.imageUrl = "";
        }
        Log.i("POPULATED ", tweet.imageUrl  + " and that was the image url");
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
