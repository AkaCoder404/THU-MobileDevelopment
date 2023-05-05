package com.example.homework9;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    private Fragment newPostsFragment = new HomeNewPostsFragment();
    private Fragment newRepliesFragment = new HomeNewRepliesFragment();
    private Fragment topFragment = new HomeTopFragment();
    private Fragment followedFragment = new HomeFollowedFragment();


    public HomeViewPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return newPostsFragment;
            case 1: return newRepliesFragment;
            case 2: return topFragment;
            case 3: return followedFragment;
            default: return newPostsFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
