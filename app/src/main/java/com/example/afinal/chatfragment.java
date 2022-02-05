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

import android.provider.DocumentsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class chatFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button buttonAddPage;
    TextView textView;
    View rootview;
    MyFragInterface listener;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Button buttonSave;

    ArrayList<Fragment>mFragmentList = new ArrayList<Fragment>();
    ArrayList<String>mFragmentTitleList = new ArrayList<String>();
    ArrayList<Fragment>ar1 = new ArrayList<Fragment>();
    ArrayList<String>ar2 = new ArrayList<String>();
    int back = 1;
    EditText editText;
    String put;
    ArrayList<String>putArr = new ArrayList<String>();
    ArrayList<String>editCon = new ArrayList<String>();
    Boolean check = false;

    public chatFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static chatFragment newInstance(String param1, String param2) {
        chatFragment fragment = new chatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_chat,
                container,
                false);

        buttonAddPage = (Button) rootview.findViewById(R.id.buttonAddPage);
        textView = (TextView) rootview.findViewById(R.id.editTextPageName);
        viewPager = (ViewPager) rootview.findViewById(R.id.my_viewpager);
        tabLayout = (TabLayout) rootview.findViewById(R.id.my_tab_layout);
        adapter = new chatFragment.ViewPagerAdapter(mFragmentList, mFragmentTitleList,
                getFragmentManager(), getActivity(), viewPager, tabLayout, listener, editCon);
        viewPager.setAdapter(adapter);
        buttonSave = (Button) rootview.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                check = true;
                listener.needsHide(adapter.mFragmentList, adapter.mFragmentTitleList, putArr);
            }
        });
            //Toast.makeText(getActivity(), "請按Save保留你的筆記!", Toast.LENGTH_SHORT).show();
        editText = (EditText) rootview.findViewById(R.id.editText);
        editText.setText("");
        setEvents1();
        setEvents2();
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null){
            back = bundle.getInt("back");
            ar1 = (ArrayList<Fragment>) bundle.getSerializable("content");
            ar2 = (ArrayList<String>) bundle.getStringArrayList("title");
            putArr = bundle.getStringArrayList("put");
            adapter.editCon = bundle.getStringArrayList("context");
            adapter.mFragmentList= new ArrayList<Fragment>(ar1.size());
            adapter.mFragmentTitleList = new ArrayList<String>(ar2.size());
        }

        adapter.notifyDataSetChanged();
        int a = ar2.size();

        for (int i=0;i<a;i++){
            Bundle saveBundle = new Bundle();
            saveBundle.putString("data", ar2.get(i)+"");
            put = adapter.editCon.get(i);
            saveBundle.putString("put", put);
            saveBundle.putInt("pos", i);
            childFragment fragmentChild = new childFragment();
            fragmentChild.setArguments(saveBundle);
            adapter.addFrag(fragmentChild, ar2.get(i)+"");
            adapter.notifyDataSetChanged();
            if (adapter.getCount() > 0) tabLayout.setupWithViewPager(viewPager);

            viewPager.setCurrentItem(adapter.getCount() - 1);
            setupTabLayout();
        }
    }

    public interface MyFragInterface {
        public void needsHide(ArrayList<Fragment>mFragment, ArrayList<String>mfragmentstitle,
                              ArrayList<String>putArr);
        public void needsHide2(ArrayList<String>editCon);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyFragInterface) {
            listener = (MyFragInterface) context;
        }
        else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void setEvents1() {
        buttonAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView.getText().toString().equals("")) {
                    //if (editText.getText().toString().equals(""))
                        //Toast.makeText(getActivity(), "Page context is empty", Toast.LENGTH_SHORT).show();
                    addPage(textView.getText() + "");
                    editText.setText("");
                    textView.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Page name is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int selectedTabPosition;

    private void setEvents2() {

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                viewPager.setCurrentItem(tab.getPosition());
                selectedTabPosition = viewPager.getCurrentItem();
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
        put = editText.getText().toString();
        putArr.add(put);
        int pos = putArr.indexOf(put);
        bundle.putInt("pos", pos);
        bundle.putString("put", put);
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

    class ViewPagerAdapter extends FragmentStatePagerAdapter implements Serializable{
        ArrayList<Fragment> mFragmentList;
        ArrayList<String> mFragmentTitleList;
        Context context;
        ViewPager viewPager;
        TabLayout tabLayout;
        MyFragInterface listener;
        ArrayList<String>editCon;

        public ViewPagerAdapter(ArrayList<Fragment> mFragmentList, ArrayList<String> mFragmentTitleList, FragmentManager manager, Context context, ViewPager viewPager,
                                TabLayout tabLayout, MyFragInterface listener, ArrayList<String>con) {
            super(manager);
            this.mFragmentList = mFragmentList;
            this.mFragmentTitleList = mFragmentTitleList;
            this.context = context;
            this.viewPager = viewPager;
            this.tabLayout = tabLayout;
            this.listener = listener;
            this.editCon = con;
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
            Fragment fragment = mFragmentList.get(position);;
            ArrayList<String>arr1 = putArr;
            putArr.remove(position);
            editCon.remove(position);
            mFragmentList.remove(fragment);
            mFragmentTitleList.remove(position);
            destroyFragmentView(viewPager, position, fragment);
            notifyDataSetChanged();

            listener.needsHide2(adapter.editCon);
        }

        public View getTabView(final int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null);
            TextView tabItemName = (TextView) view.findViewById(R.id.textViewTabItemName);
            ImageButton remove = (ImageButton) view.findViewById(R.id.imageButtonRemove);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFrag(position);
                }
            });

            tabItemName.setText(mFragmentTitleList.get(position));
            tabItemName.setTextColor(context.getResources().getColor(android.R.color.background_light));

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
    }
}
