package jone.helper.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jone.helper.R;

/**
 * Created by jone.sun on 2016/9/8.
 */

public class GalleryTabFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static GalleryTabFragment newInstance() {
        GalleryTabFragment fragment = new GalleryTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_tab, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.gankIoTypes));

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return rootView;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private String[] gankIoTypes;
        public SectionsPagerAdapter(FragmentManager fm, String[] gankIoTypes){
            super(fm);
            this.gankIoTypes = gankIoTypes;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return GalleryTabContentFragment.newInstance(getPageTitle(position).toString());
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return gankIoTypes.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return gankIoTypes[position];
        }
    }
}
