package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.Fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.Fragments.MentionsTimelineFragment;
import com.codepath.apps.restclienttemplate.Fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.Fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.Tweets;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener{

    //private SwipeRefreshLayout swipeContainer;
    private TwitterClient client;
    //TweetsListFragment fragmentTweetList;
    private final int REQUEST_CODE = 10;
    private TweetsPagerAdapter pagerAdapter;

    private MentionsTimelineFragment mentionsTimelineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), this);
        vpPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
        //client = TwitterApplication.getRestClient();
        //fragmentTweetList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);

        //populateTimeline();
        //swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        //swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           // @Override
           // public void onRefresh() {
               // tweetAdapter.clear();
               //populateTimeline();
               // swipeContainer.setRefreshing(false);
           // }
        //});
        // Configure the refreshing colors
        //swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                //android.R.color.holo_green_light,
                //android.R.color.holo_orange_light,
                //android.R.color.holo_red_light);
    }


    //public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.

       // client.getHomeTimeline(new JsonHttpResponseHandler() {

            //@Override
            //public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                //tweetAdapter.clear();
                //for (int i = 0; i < response.length(); i++) {
                   // try {
                       // Tweets tweet = Tweets.fromJSON(response.getJSONObject(i));
                      //  tweets.add(tweet);
                   // } catch (JSONException e) {
                    //    e.printStackTrace();
                   // }
                    // Remember to CLEAR OUT old items before appending in the new ones

                    // ...the data has come back, add new items to your adapter...
                    // Now we call setRefreshing(false) to signal refresh has finished
                  //  tweetAdapter.notifyItemInserted(tweets.size() - 1);
               // }
               // tweetAdapter.addAll(tweets);
                //swipeContainer.setRefreshing(false);
            //}

            //public void onFailure(Throwable e) {
               // Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            //}
        //});
    //}

    public void fetchTweets(String query){
        client.getSearchResults(query, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //try {
                    //progress.setVisibility(ProgressBar.GONE);
                    //JSONArray docs;
                    //if(response != null) {
                        // Get the docs json array
                        //docs = response.getJSONArray("docs");
                        // Parse json array into array of model objects
                        //final ArrayList<Book> books = Book.fromJson(docs);
                        // Remove all books from the adapter
                        //abooks.clear();
                        // Load model objects into the adapter
                        //for (Book book : books) {
                       //     abooks.add(book); // add book through the adapter
                       // }
                       // bookAdapter.notifyDataSetChanged();
                   // }
               // } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    //e.printStackTrace();
                }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchTweets(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.miCompose) {
            Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
            startActivityForResult(i, REQUEST_CODE);
        }
        return true;
    }


    //private void populateTimeline(){
        //client.getHomeTimeline(new JsonHttpResponseHandler(){
           // @Override
           // public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
             //   Log.d("TwitterClient", response.toString());
            //}

           // @Override
            //public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
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
              //  fragmentTweetList.addItems(response);
           // }

           // @Override
            //public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
               // Log.d("TwitterClient", responseString);
             //   throwable.printStackTrace();
          //  }

            //@Override
           // public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
           //     Log.d("TwitterClient", errorResponse.toString());
           //     throwable.printStackTrace();
           // }

           // @Override
            //public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            //    Log.d("TwitterClient", errorResponse.toString());
             //   throwable.printStackTrace();
          //  }
       // });
  /// }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Tweets tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            // tweets.add(0, tweet);
            // tweetAdapter.notifyItemInserted(0);
            // rvTweets.scrollToPosition(0);
            // Toast the name to display temporarily on screen
            //Toast.makeText(this, "Posted New Tweet!", Toast.LENGTH_SHORT).show();
             HomeTimelineFragment tf = (HomeTimelineFragment) pagerAdapter.getItem(0);
            tf.addTweet(tweet);

        }
    }
    public void onProfileView(MenuItem item) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void onTweetSelected(Tweets tweets) {
        Toast.makeText(this, tweets.body, Toast.LENGTH_SHORT).show();
    }

    //public void onProfileClick(View view) {
        //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, TwitterUserActivity.class);
        //startActivity(intent);
    //}
}

