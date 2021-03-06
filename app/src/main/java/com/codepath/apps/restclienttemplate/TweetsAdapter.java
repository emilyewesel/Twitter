package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Build;
import android.text.Layout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clear all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
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
        TextView time_stamp;
        ImageView ivMedia;

        //hooking up variables to xml elements
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTweetBody = itemView.findViewById(R.id.tvBody);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            time_stamp = itemView.findViewById(R.id.timeStamp);
            ivMedia = itemView.findViewById(R.id.media);

        }
        //This function converts an absolute timestamp (12:56pm) into a relative timestamp (5m ago)
        @RequiresApi(api = Build.VERSION_CODES.N)
        public String getRelativeTimeAgo(String rawJsonDate) {
            String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
            SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
            sf.setLenient(true);

            String relativeDate = "";
            try {
                long dateMillis = 0;
                try {
                    dateMillis = sf.parse(rawJsonDate).getTime();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return relativeDate;
        }

        String TAG = "TweetsAdapter";
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Tweet tweet) {
            Log.d(TAG, "this is the tweet!"+ "tweet :" + tweet.body);
            tvTweetBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.handle);
            String timeSince = getRelativeTimeAgo(tweet.createdAt);
            time_stamp.setText(timeSince);
            Glide.with(context).load(tweet.user.publicImageUrl).into(ivProfileImage);

            //This is the case where the tweet does not include the image
            if(tweet.imageUrl.equals("")){
                Log.d(TAG, "We should not see an image"+ tweet.imageUrl);
                ivMedia.setVisibility(View.GONE);

            }
            //This is the case where there is an embedded image to display
            else{
                Log.d("IMAGE", "We should be seeing an image!!" + tweet.imageUrl);
                ivMedia.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.imageUrl).into(ivMedia);
            }

        }
    }
}
