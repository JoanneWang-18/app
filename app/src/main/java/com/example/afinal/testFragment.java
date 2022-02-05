package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link testFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class testFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GridView gv;
    Adapter adapter;  // 將會繼承baseadapter
    ArrayList<Item3> ary_items = new ArrayList<Item3>();
    ImageButton button1;
    boolean condition;
    ArrayList<uva> uva_temp = new ArrayList<uva>();
    Button button_pdf;
    Button button_finished;
    TextView textview_uvaname;
    TextView textview_uvatype;
    FrameLayout frameLayout;
    int num_pdf;
    String []uva_pdf;

    public testFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment testFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static testFragment newInstance(String param1, String param2) {
        testFragment fragment = new testFragment();
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
        ary_items = new ArrayList<Item3>(0);  // 建立一個容器
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =
                inflater.inflate(R.layout.fragment_test,
                        container,
                        false);
        button1 = (ImageButton) rootView.findViewById(R.id.button1);
        gv = (GridView) rootView.findViewById(R.id.gv);
        button_pdf = (Button) rootView.findViewById(R.id.pdf);
        button_finished = (Button) rootView.findViewById(R.id.finished);
        textview_uvaname = (TextView) rootView.findViewById(R.id.textview1);
        textview_uvatype = (TextView) rootView.findViewById(R.id.textview2);
        uva_temp = new ArrayList<>(DummyData.getBuffer1());
        frameLayout = (FrameLayout) rootView.findViewById(R.id.frame);

        uva_pdf = DummyData.getUVApdf();

        button_pdf.setTextColor(Color.parseColor("#000000"));
        button_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfurl = uva_pdf[adapter.num_pdf];
                Bundle bundle = new Bundle();
                bundle.putString("pdfurl", pdfurl);
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        button_finished.setTextColor(Color.parseColor("#000000"));

        if(ary_items.size() == 0){
            addItems(25); // 自設類別方法處理建立Item物件, 日後在更新GridView時將會大派用場。
        }

        adapter = new Adapter(inflater,ary_items,button_pdf,button_finished,textview_uvaname,textview_uvatype);
        gv.setAdapter(adapter);
        questionFragment questionfragment = new questionFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.frame,questionfragment).hide(questionfragment);
        transaction.addToBackStack(null);
        transaction.commit();
        condition = false;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.bringToFront();
                if(!condition){
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.show(questionfragment);
                    transaction.commit();
                    condition = true;
                }else{
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.hide(questionfragment);
                    transaction.commit();
                    condition = false;
                }
            }
        });

        button_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textview_uvaname.getText().toString().equals("")){
                    if(button_finished.getText().toString().equals("未完成")){
                        button_finished.setText("已完成");
                        adapter.chage_finish(false);
                    }else{
                        button_finished.setText("未完成");
                        adapter.chage_finish(true);
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });

        return rootView;
    }

    public void addItems(int total){    // 自設方法

        int a = 4;
        int count = 0;
        for(int i=0; i<total; i++){  // loop total=42 times

            Item3 item = new Item3(); // 每次建立一個item物件
            item.row = a;     // 告訴 item 物件中 day 這個變量的值是 i
            item.column = count;
            item.uva_name = uva_temp.get(i).uva_name;
            item.uva_type = uva_temp.get(i).uva_type;
            item.uva_pdf = uva_temp.get(i).pdf;

            if(item.row == 4)
                item.color = 0;
            else if(item.row == 3 && item.column == 0)
                item.color = 0;
            else if(item.row == 3 && item.column > 0)
                item.color = 1;
            else if(item.row == 2 && item.column == 4)
                item.color = 1;
            else if(item.row == 2 && item.column > 1 && item.column <= 3)
                item.color = 2;
            else if(item.row == 2 && item.column <= 1)
                item.color = 3;
            else if(item.row == 1)
                item.color = 3;
            else if(item.row == 0 && item.column == 0)
                item.color = 3;
            else if(item.row == 0 && item.column == 1)
                item.color = 4;
            else
                item.color = 5;

            ary_items.add(item);   // 把item物件扔進容器ary_items

            if(count == 4){
                a--;
                count = -1;
            }
            count++;
        }
    }
}


class Item3 implements Serializable {   // 自設item類別
    int color;  //false -> blue , true -> white
    int column;
    int row;
    boolean alreadyPass;
    String uva_name;
    String uva_type;
    String uva_pdf;

    public Item3(){
        this.alreadyPass = false;
    }
}

class Adapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;  // context 會指向mainactivity
    ArrayList<Item3> ary_items;   // 容器，已經裝滿42個item物件
    Boolean[] clicked;
    Button button_pdf;
    Button button_finished;
    TextView textview_uvaname;
    TextView textview_uvatype;
    int man_row=6;
    int man_column=6;
    int num_pdf;

    // 自設Default Constructor，用以接收activity和容器
    public Adapter(LayoutInflater inflater, ArrayList<Item3> ary_items,Button button_pdf,Button button_finished, TextView textview_uvaname, TextView textview_uvatype){
        this.inflater = inflater;
        this.ary_items = ary_items;
        this.clicked = new Boolean[25];
        this.button_pdf = button_pdf;
        this.button_finished = button_finished;
        this.textview_uvaname = textview_uvaname;
        this.textview_uvatype = textview_uvatype;

        Arrays.fill(clicked, Boolean.FALSE);
    }

    // Overriding BaseAdapter中的內建方法
    @Override
    public int getCount() {   // item總數量
        return ary_items.size();
        //return 0;
    }
    @Override
    public Object getItem(int arg0) {  // 無須理會，在處理點擊事件時才會調用
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public long getItemId(int arg0) {  // 無須理會，在處理點擊事件時才會調用
        // TODO Auto-generated method stub
        return 0;
    }

    public void chage_finish(boolean alreadyPass){
        ary_items.get(5*(4-man_row)+man_column).alreadyPass = alreadyPass;
    }

    public ArrayList<Item3> getAry_items() {
        return ary_items;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {  // arg0 = 每格的ID
        if(arg1==null){
            arg1 = inflater.inflate(R.layout.grid_uva, null);          //  listitem中的佈局FIT返每一格的SIZE
        }

        Button tv = (Button) arg1.findViewById(R.id.tv);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview_uvaname.setText(ary_items.get(arg0).uva_name);
                textview_uvatype.setText(ary_items.get(arg0).uva_type);
                man_row = ary_items.get(arg0).row;
                man_column = ary_items.get(arg0).column;
                if(ary_items.get(arg0).alreadyPass)
                    button_finished.setText("未完成");
                else
                    button_finished.setText("已完成");
                notifyDataSetChanged();
                num_pdf = arg0;
            }
        });

        //tv.setText(ary_items.get(arg0).row+","+ ary_items.get(arg0).column);
        if(ary_items.get(arg0).color == 0) tv.setBackgroundColor(Color.parseColor("#FFE66F"));
        else if(ary_items.get(arg0).color == 1) tv.setBackgroundColor(Color.parseColor(	"#99CCFF"));
        else if(ary_items.get(arg0).color == 2) tv.setBackgroundColor(Color.parseColor(	"#D3A4FF"));
        else if(ary_items.get(arg0).color == 3) tv.setBackgroundColor(Color.parseColor(	"#FFB5B5"));
        else if(ary_items.get(arg0).color == 4) tv.setBackgroundColor(Color.parseColor(	"#96FED1"));
        else if(ary_items.get(arg0).color == 5) tv.setBackgroundColor(Color.parseColor(	"#FFBB77"));

        if(arg0 == 5*(4-man_row)+man_column)
            tv.setBackgroundColor(Color.parseColor(	"#363636"));

        if(ary_items.get(arg0).alreadyPass){
            tv.setText("X");
        }else{
            tv.setText("");
        }

        return arg1;  // arg1 指該格仔，android中視每一格為一個view
    }
}