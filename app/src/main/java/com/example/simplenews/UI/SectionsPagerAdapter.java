package com.example.simplenews.UI;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.simplenews.Business.BusinessFragment;
import com.example.simplenews.Entertainment.EntertainmentFragment;
import com.example.simplenews.Headlines.HeadlinesFragment;
import com.example.simplenews.Health.HealthFragment;
import com.example.simplenews.R;
import com.example.simplenews.Saved.SavedFragment;
import com.example.simplenews.Science.ScienceFragment;
import com.example.simplenews.Sports.SportsFragment;
import com.example.simplenews.Technology.TechnologyFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,
            R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5, R.string.tab_text_6, R.string.tab_text_7, R.string.tab_text_8};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch(position){
            case 0:
                fragment = new HeadlinesFragment();
                break;
            case 1:
                fragment = new BusinessFragment();
                break;
            case 2:
                fragment = new TechnologyFragment();
                break;
            case 3:
                fragment = new ScienceFragment();
                break;
            case 4:
                fragment = new HealthFragment();
                break;
            case 5:
                fragment = new EntertainmentFragment();
                break;
            case 6:
                fragment = new SportsFragment();
                break;
            case 7:
                fragment = new SavedFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 8;
    }

}