package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweets;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText et_simple;
    Button btTweet;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getSupportActionBar().setTitle("Compose");

        //Toast.makeText(ComposeActivity.this, "Test", Toast.LENGTH_SHORT).show();
        et_simple = (EditText) findViewById(R.id.et_simple);
        btTweet = (Button) findViewById(R.id.btTweet);
        client = TwitterApplication.getRestClient();
    }
    //EditText simpleEditText = (EditText) findViewById(R.id.et_simple);
    //String strValue = simpleEditText.getText().toString();

    public void onSubmit(View v) {
        final EditText etName = (EditText) findViewById(R.id.et_simple);
        final String body = etName.getText().toString();
        client.postTweet(body, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("log", "Success");
                try {
                    Tweets tweet = Tweets.fromJSON(response);

                        Intent data = new Intent();
                        // Pass relevant data back as a result
                        data.putExtra("tweet", Parcels.wrap(tweet));
                        // ints work too
                        // Activity finished ok, return the data
                        setResult(RESULT_OK, data); // set result code and bundle data for response
                        finish(); // closes the activity, pass data to parent
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("msg", "fail");
            }
        });
        // Prepare data intent

    }
}
