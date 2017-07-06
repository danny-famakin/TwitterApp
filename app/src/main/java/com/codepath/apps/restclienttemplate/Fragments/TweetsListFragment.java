package com.codepath.apps.restclienttemplate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.DividerItemDecoration;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweets;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by famakindaniel7 on 7/3/17.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener{
    public interface TweetSelectedListener {
        public void onTweetSelected(Tweets tweets);
    }
    TweetAdapter tweetAdapter;
    ArrayList<Tweets> tweets;
    RecyclerView rvTweets;
    //private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);
        //swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);

        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(tweets, this);
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTweets.addItemDecoration(new DividerItemDecoration(getContext()));
        rvTweets.setAdapter(tweetAdapter);
        return v;

        //Setup refresh listener which triggers new data loading
        //swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           // @Override
            //public void onRefresh() {
               // tweetAdapter.clear();
               // populateTimeline();
                //swipeContainer.setRefreshing(false);
            //}
        //});
        // Configure the refreshing colors
        //swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
               // android.R.color.holo_green_light,
                //android.R.color.holo_orange_light,
                //android.R.color.holo_red_light);

    }

    public void addItems(JSONArray response){
        for (int i = 0; i < response.length(); i++) {
            try {
                Tweets tweet = Tweets.fromJSON(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(tweets.size() - 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void clearAll() {
        int size = this.tweets.size();
        this.tweets.clear();
        //notifyItemRangeRemoved(0, size);
    }
    public void addAll(List<Tweets> list) {
        tweets.addAll(list);
        //notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweets tweet = tweets.get(position);
        //Toast.makeText(getContext(), tweet.body, Toast.LENGTH_SHORT).show();
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }
}
