package jone.helper.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jone.helper.R;
import jone.helper.ui.fragment.GalleryTabFragment;
import jone.helper.ui.fragment.HomeFragment;
import jone.helper.ui.fragment.PersonalCenterFragment;

/**
 * Created by jone.sun on 2016/9/7.
 */

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments;
    private MenuItem lastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        lastItem = bottomNavigationView.getMenu().getItem(0);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (lastItem != item) {
                lastItem = item;
                switch (item.getItemId()) {
                    case R.id.item_home:
                        onTabSelected(0);
                        break;
                    case R.id.item_gallery:
                        onTabSelected(1);
                        break;
                    case R.id.item_slideshow:
                        onTabSelected(2);
                        break;
                    case R.id.item_tools:
                        onTabSelected(3);
                        break;
                    case R.id.item_share:
                        onTabSelected(4);
                        break;

                }
                return true;
            }
            return false;
        });

        fragments = getFragments();
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.ll_root, fragments.get(0));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(new GalleryTabFragment());
        fragments.add(PersonalCenterFragment.newInstance());
        fragments.add(PlaceholderFragment.newInstance("Tools"));
        fragments.add(PlaceholderFragment.newInstance("Share"));
        return fragments;
    }

    public void onTabSelected(int position) {
        Log.e("sss", "选择onTabSelected: " + position);
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.replace(R.id.ll_root, fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_test, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.text);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));
            return rootView;
        }

    }
}
