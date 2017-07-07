package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweets;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by famakindaniel7 on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweets> mTweet;
    Context context;
    private TweetAdapterListener mListener;
    User user;




    //CircleImageView imageView = (CircleImageView) findViewById(R.id.ivProfilePic);

    public interface  TweetAdapterListener{
        public void onItemSelected(View view,  int position);
    }
    public TweetAdapter(List<Tweets> tweet, TweetAdapterListener listener) {
        mTweet = tweet;
        this.mListener = listener;
    }
    private TwitterClient client;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_tweets, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       final Tweets tweet = mTweet.get(position);
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvDate.setText(getRelativeTimeAgo(tweet.createdAt));
        holder.tvHandle.setText("@" + tweet.user.screenName);
        holder.tvRetweet.setText(Long.toString(tweet.retweetCount));
        holder.tvFave.setText(Long.toString(tweet.likes));
        client = TwitterApplication.getRestClient();

        //final String profile = context.getResources().getString(R.string.profile);
        holder.ivProfilePic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent (context, TwitterUserActivity.class);
                i.putExtra("id", tweet.user.uid);
                i.putExtra("screen_name", tweet.user.screenName);
                //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((TimelineActivity) context, view, profile);
                context.startActivity(i /*,options.toBundle()*/);
            }
        });
        if (tweet.mediaUrl != null && !tweet.mediaUrl.isEmpty()){
            holder.ivMedia.setVisibility(View.VISIBLE);
            Glide.with(context).load(tweet.mediaUrl).into(holder.ivMedia);
        } else {
            holder.ivMedia.setVisibility(View.GONE);
        }
        //Glide.with(context).load(tweet.user.profileBannerUrl).into(holder.ivProfileBackgroundImage);

        holder.fave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!tweet.liked){
                client.likeTweet(Long.toString(tweet.uid), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                        holder.tvFave.setText(Long.toString(tweet.likes + 1));
                        tweet.liked = true;
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });}
                else{
                client.unLike(Long.toString(tweet.uid), new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(context, "Unliked", Toast.LENGTH_SHORT).show();
                        holder.tvFave.setText(Long.toString(tweet.likes));
                        tweet.liked = false;
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                }
            }
        });
        holder.retweet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (!tweet.retweeted){
                client.reTweet(Long.toString(tweet.uid), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(context, "Retweeted", Toast.LENGTH_SHORT).show();
                        holder.tvRetweet.setText(Long.toString(tweet.retweetCount + 1));
                        tweet.retweeted = true;
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });}
                else{
                    client.unRetweet(Long.toString(tweet.uid), new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(context, "Unretweeted", Toast.LENGTH_SHORT).show();
                            holder.tvRetweet.setText(Long.toString(tweet.retweetCount));
                            tweet.retweeted = false;
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
            }
        });

        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfilePic);
    }

    @Override
    public int getItemCount() {
        return mTweet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfilePic;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvDate;
        public TextView tvHandle;
        public ImageButton retweet;
        public ImageButton fave;
        public TextView tvRetweet;
        public TextView tvFave;
        public ImageView ivMedia;
        public ImageView ivProfileBackgroundImage;

        public ViewHolder(View itemView) {
            super (itemView);

            ivProfilePic = (CircleImageView) itemView.findViewById(R.id.ivProfilePic);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvHandle = (TextView) itemView.findViewById(R.id.tvHandle);
            retweet = (ImageButton) itemView.findViewById(R.id.retweet);
            fave = (ImageButton) itemView.findViewById(R.id.fave);
            tvRetweet = (TextView) itemView.findViewById(R.id.tvRetweet);
            tvFave = (TextView) itemView.findViewById(R.id.tvFave);
            ivMedia = (ImageView) itemView.findViewById(R.id.ivMedia);
            ivProfileBackgroundImage = (ImageView) itemView.findViewById(R.id.ivProfileBackgroundImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        mListener.onItemSelected(view, position);
                    }
                }
            });
        }
    }
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
    public void clear() {
        int size = this.mTweet.size();
        this.mTweet.clear();
        notifyItemRangeRemoved(0, size);
    }
    public void addAll(List<Tweets> list) {
        mTweet.addAll(list);
        notifyDataSetChanged();
    }
}
