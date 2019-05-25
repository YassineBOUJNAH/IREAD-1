package com.example.iread.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.iread.Fragment.BlankFragment;
import com.example.iread.Fragment.NewsPageFragment;
import com.example.iread.Fragment.ParamPageFragment;

public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return NewsPageFragment.newInstance();
            case 1: //Page number 3
                return ParamPageFragment.newInstance();
            case 2: //Page number 4
                return BlankFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Fil d'actualité";
            case 1: //Page number 3
                return "Paramètre";
            case 2: //Page number 4
                return "PBlank";
            default:
                return null;
        }
    }

}
