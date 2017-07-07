package com.codepath.apps.restclienttemplate.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codepath.apps.restclienttemplate.TimelineActivity;

/**
 * Created by famakindaniel7 on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {
    private  String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;
    HomeTimelineFragment timelineFragment;
    MentionsTimelineFragment mentionsFragment;
    public TweetsPagerAdapter(FragmentManager fm, TimelineActivity timelineActivity) {
        super(fm);
         timelineFragment = new HomeTimelineFragment();
         mentionsFragment = new MentionsTimelineFragment();
        //this.context = context;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return timelineFragment;
        } else if (position == 1) {
            return mentionsFragment;
        } else {
            return null;
        }
    }
    public CharSequence getPageTitle (int position) {
        return tabTitles[position];
    }
}
