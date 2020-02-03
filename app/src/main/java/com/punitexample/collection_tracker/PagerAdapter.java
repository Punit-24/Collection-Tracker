package com.punitexample.collection_tracker;

import android.app.Notification;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    @NonNull
    int noOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.noOfTabs = NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                Notifications notifications = new Notifications();
                return notifications;
            case 1:
                Items items = new Items();
                return items;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
