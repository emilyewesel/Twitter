package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String name;
    public String handle;
    public String publicImageUrl;

    public static User fromJson (JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.handle = jsonObject.getString("screenName");
        user.publicImageUrl = jsonObject.getString("profileImageUrl");
        return user;


    }
}
