package com.codepath.apps.restclienttemplate.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweets;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by famakindaniel7 on 7/3/17.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class HomeTimelineFragment extends TweetsListFragment {
    //private SwipeRefreshLayout swipeContainer;
    TwitterClient client;
    //ArrayList<Tweets> tweets;
    //TweetAdapter tweetAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();


        //swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
         //Setup refresh listener which triggers new data loading
        //swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        // @Override
        // public void onRefresh() {
        // tweetAdapter.clear();
        //populateTimeline();
         //swipeContainer.setRefreshing(false);
         //}
        //});
        // Configure the refreshing colors
        //swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        //android.R.color.holo_green_light,
        //android.R.color.holo_orange_light,
        //android.R.color.holo_red_light);
    }
    public void addTweet(Tweets tweet){
        tweets.add(0, tweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.scrollToPosition(0);
    }
    @Override
    public void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("TwitterClient", response.toString());
                //for (int i = 0; i < response.length(); i++) {
                // try {
                // Tweets tweet = Tweets.fromJSON(response.getJSONObject(i));
                //  tweets.add(tweet);
                //  tweetAdapter.notifyItemInserted(tweets.size() - 1);
                // } catch (JSONException e) {
                //     e.printStackTrace();
                // }
                // }
                addItems(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}
