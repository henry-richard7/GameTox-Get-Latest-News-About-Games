package com.henry.gametox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull

    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 0: {
                return UpcomingGamesFragment.newInstance("Hello", "Hi");
            }
            case 1: {
                return FreeGamesFragment.newInstance("vanakam", "tv");
            }
            case 2: {
                return GameTrailers.newInstance("Anime", "2");
            }
            default:
                return null;
        }


    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
