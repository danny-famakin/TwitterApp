package com.codepath.apps.restclienttemplate.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by famakindaniel7 on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {
    private  String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;
    public TweetsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeTimelineFragment();
        } else if (position == 1) {
            return new MentionsTimelineFragment();
        } else {
            return null;
        }
    }
    public CharSequence getPageTitle (int position) {
        return tabTitles[position];
    }
}