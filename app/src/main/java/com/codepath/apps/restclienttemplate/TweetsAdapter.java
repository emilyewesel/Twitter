package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    Context context;
    List<Tweet> tweets;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //Pass in context for list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets= tweets;
        Log.d("the tweets are ", tweets.toString());
    }
    //For each row, inflate the layout
    //Inflate a layout for a tweet
    //Define a new viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvScreenName;
        TextView tvTweetBody;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTweetBody = itemView.findViewById(R.id.tvBody);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);

        }

        public void bind(Tweet tweet) {
            Log.d("this is the tweet!", "tweet :" + tweet.body);
            tvTweetBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.handle);
            Glide.with(context).load(tweet.user.publicImageUrl).into(ivProfileImage);
        }
    }
}
