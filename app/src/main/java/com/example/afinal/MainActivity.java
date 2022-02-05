package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements chatFragment.MyFragInterface
        , childFragment.ChildFragInterface, videoFragment.BackVideo, learnFragment.OnItemSelectedListener, learnpdfFragment.learnPdf
{
    FragmentManager fragmentManager = getSupportFragmentManager();
    Serializable fragments;
    ArrayList<String> fragmentstitle;
    parentFragment parentFragment;
    Boolean back= false;
    ArrayList<String>putArr;
    ArrayList<String>con = new ArrayList<String>();
    Boolean BigSave = false;
    Boolean SmallSave = false;
    Fragment choice = null;
    Fragment vedio;
    Fragment learn;
    Fragment test;
    Fragment learnPdf;
    int condition = 0;
    int position = 0;

    ArrayList<Item3> ary_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vedio = new videoFragment();
        learn = new learnFragment();
        test = new testFragment();
        learnPdf = new learnpdfFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigatin_view);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        learnFragment fragment = new learnFragment();
        fragmentTransaction.add(R.id.nav_fragment, fragment);
        fragmentTransaction.commit();
        parentFragment = (parentFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle bundle = new Bundle();
                switch (item.getItemId()) {
                    case R.id.learn:
                        if (back){
                            bundle = new Bundle();
                            bundle.putSerializable("content", (Serializable) fragments);
                            bundle.putStringArrayList("title", fragmentstitle);
                            bundle.putStringArrayList("put", putArr);
                            bundle.putStringArrayList("context", con);
                            bundle.putInt("back", 1);
                        }

                        if(condition == 0){
                            choice = learn;
                        }
                        else if(condition == 1){
                            choice = vedio;
                        }else if(condition == 2){
                            choice = learnPdf;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment,choice).commit();
                        break;
                    case R.id.chat:
                        if (back){
                            bundle = new Bundle();
                            bundle.putSerializable("content", (Serializable) fragments);
                            bundle.putStringArrayList("title", fragmentstitle);
                            bundle.putStringArrayList("put", putArr);
                            bundle.putStringArrayList("context", con);
                            bundle.putInt("back", 1);
                        }

                        FragmentTransaction chatTransaction = fragmentManager.beginTransaction();
                        chatFragment C = (chatFragment) fragmentManager.findFragmentById(R.id.chatFragment);
                        chatFragment cf = new chatFragment();
                        if (back)
                            cf.setArguments(bundle);
                        chatTransaction.replace(R.id.nav_fragment, cf);
                        chatTransaction.addToBackStack(null);
                        chatTransaction.commit();

                        break;
                    case R.id.test:
                        if (back){
                            bundle = new Bundle();
                            bundle.putSerializable("content", (Serializable) fragments);
                            bundle.putStringArrayList("title", fragmentstitle);
                            bundle.putStringArrayList("put", putArr);
                            bundle.putStringArrayList("context", con);
                            bundle.putInt("back", 1);
                        }

                        FragmentTransaction plotTransaction = fragmentManager.beginTransaction();
                        //testFragment tf = new testFragment();
                        if (back)
                            test.setArguments(bundle);
                        plotTransaction.replace(R.id.nav_fragment, test);
                        plotTransaction.addToBackStack(null);
                        plotTransaction.commit();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void needsHide(ArrayList<Fragment>mFragment, ArrayList<String>mfragmentstitle,
                          ArrayList<String>Arr) {
        fragments = mFragment;
        fragmentstitle = mfragmentstitle;
        putArr = Arr;
        back = true;
        //BigSave = check;
    }

    @Override
    public void getEdit(String context, int pos, Boolean change) {
        //SmallSave = check;
        if (change == false)
            con.add(context);
        else
            con.set(pos, context);
    }

    @Override
    public void needsHide2(ArrayList<String> editCon) {
        con = editCon;
    }

    @Override
    public void onVedioItemSelected(item link, int pos) {
        //position = pos;
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        bundle.putString("lesson_name", link.shade_name);
        bundle.putString("url", link.url);
        vedio.setArguments(bundle);
        condition = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment,vedio).commit();
    }

    @Override
    public void onBackVideoItemSelected(){
        condition = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment,learn).commit();
    }

    @Override
    public void GoToPdf(int pos){
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        learnPdf.setArguments(bundle);
        condition = 2;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment,learnPdf).commit();
    }

    @Override
    public void onBackPdfItemSelected(){
        condition = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment,vedio).commit();
    }

}

