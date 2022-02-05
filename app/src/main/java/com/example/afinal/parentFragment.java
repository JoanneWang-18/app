package com.example.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class parentFragment extends Fragment{
    private OnItemSelectedListener listener;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    ImageButton buttonGoback;
    //private FragmentToActivity mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parent, container, false);
        getIDs(view);
        setEvents();

        return view;
    }

    public interface OnItemSelectedListener {
        public void onColorItemSelected(ArrayList<Fragment> mFragment,
                                        ArrayList<String> mFragmentTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void getIDs(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.my_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.my_tab_layout);
        adapter = new ViewPagerAdapter(getFragmentManager(), getActivity(), viewPager, tabLayout, listener);
        viewPager.setAdapter(adapter);
    }

    int selectedTabPosition;

    private void setEvents() {

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                viewPager.setCurrentItem(tab.getPosition());
                selectedTabPosition = viewPager.getCurrentItem();
                Log.d("Selected", "Selected " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                Log.d("Unselected", "Unselected " + tab.getPosition());
            }
        });
    }

    public void addPage(String pagename) {
        Bundle bundle = new Bundle();
        bundle.putString("data", pagename);
        childFragment fragmentChild = new childFragment();
        fragmentChild.setArguments(bundle);
        adapter.addFrag(fragmentChild, pagename);
        adapter.notifyDataSetChanged();
        if (adapter.getCount() > 0) tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(adapter.getCount() - 1);
        setupTabLayout();
    }

    public void setupTabLayout() {
        selectedTabPosition = viewPager.getCurrentItem();
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter{
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
        Context context;
        ViewPager viewPager;
        TabLayout tabLayout;
        parentFragment.OnItemSelectedListener listener;

        public ViewPagerAdapter(FragmentManager manager, Context context, ViewPager viewPager,
                                TabLayout tabLayout, parentFragment.OnItemSelectedListener listener) {
            super(manager);
            this.context = context;
            this.viewPager = viewPager;
            this.tabLayout = tabLayout;
            this.listener = listener;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public void removeFrag(int position) {
            removeTab(position);
            Fragment fragment = mFragmentList.get(position);
            mFragmentList.remove(fragment);
            mFragmentTitleList.remove(position);
            destroyFragmentView(viewPager, position, fragment);
            notifyDataSetChanged();
        }

        public View getTabView(final int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null);
            TextView tabItemName = (TextView) view.findViewById(R.id.textViewTabItemName);
            CircleImageView tabItemAvatar =
                    (CircleImageView) view.findViewById(R.id.imageViewTabItemAvatar);
            ImageButton remove = (ImageButton) view.findViewById(R.id.imageButtonRemove);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFrag(position);
                }
            });

            tabItemName.setText(mFragmentTitleList.get(position));
            tabItemName.setTextColor(context.getResources().getColor(android.R.color.background_light));

            switch (mFragmentTitleList.get(position)) {

            }

            return view;
        }

        public void destroyFragmentView(ViewGroup container, int position, Object object) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }

        public void removeTab(int position) {
            if (tabLayout.getChildCount() > 0) {
                tabLayout.removeTabAt(position);
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public ArrayList<Fragment> returnmFragmnt() {
            return mFragmentList;
        }

        public ArrayList<String> returnmFragmentTitleList() {
            return mFragmentTitleList;
        }
    }
}