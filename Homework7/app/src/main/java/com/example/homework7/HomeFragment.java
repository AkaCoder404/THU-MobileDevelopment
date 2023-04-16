package com.example.homework7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private TabLayout homeTabLayout;
    private ViewPager2 homeViewPager2;
    private HomeViewPagerAdapter homeViewPagerAdapter;
    private FrameLayout homeFrameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the home fragment tab system
        homeTabLayout = view.findViewById(R.id.home_tabLayout);
        homeFrameLayout = view.findViewById(R.id.home_frameLayout);
        homeViewPager2 = view.findViewById(R.id.home_viewPager);

        homeViewPagerAdapter = new HomeViewPagerAdapter(this);
        homeViewPager2.setAdapter(homeViewPagerAdapter);

        homeTabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        homeViewPager2.setVisibility(View.VISIBLE);
                        homeFrameLayout.setVisibility(View.GONE);
                        homeViewPager2.setCurrentItem(tab.getPosition());
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // No action needed
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        // No action needed
                        homeViewPager2.setVisibility(View.VISIBLE);
                        homeFrameLayout.setVisibility(View.GONE);
                    }
                }
        );
        homeViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    default:
//                        Toast.makeText(view.getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                        homeTabLayout.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }

        });

        return view;
    }
}