package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by famakindaniel7 on 6/26/17.
 */
@Parcel
public class Tweets {
    public String body;
    public long uid;
    public User user;
    public String createdAt;
    public long retweetCount;
    public long likes;
    public boolean liked;
    public boolean retweeted;
    public String mediaUrl;

    public Tweets() {
    }

    public static Tweets fromJSON(JSONObject jsonObject) throws JSONException {
        Tweets tweet = new Tweets();

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.retweetCount = jsonObject.getLong("retweet_count");
        tweet.likes = jsonObject.getLong("favorite_count");
        tweet.retweeted = jsonObject.getBoolean("retweeted");
        tweet.liked = jsonObject.getBoolean("favorited");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.mediaUrl = null;
        JSONObject json  = jsonObject.optJSONObject("entities");
        if (json != null) {
            JSONArray jsonArray = json.optJSONArray("media");
            if (jsonArray != null && !jsonArray.isNull(0)){
                tweet.mediaUrl = jsonArray.getJSONObject(0).optString("media_url");
            }
        }
        return tweet;
    }
}
