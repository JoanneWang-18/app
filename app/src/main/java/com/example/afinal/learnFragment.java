package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class learnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyAdapter adapter;
    private ArrayList<item> lesson;
    private OnItemSelectedListener listener;

    public learnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment learnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static learnFragment newInstance(String param1, String param2) {
        learnFragment fragment = new learnFragment();
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
        // Inflate the layout for this fragment
        lesson = new ArrayList<item>(DummyData.getBuffer());

        View rootView =
                inflater.inflate(R.layout.fragment_learn,
                        container,
                        false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_shades);
        adapter = new MyAdapter(this, lesson, listener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getParent());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    public interface OnItemSelectedListener {
        public void onVedioItemSelected(item link, int pos);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    learnFragment context;
    ArrayList<item> lesson;
    learnFragment.OnItemSelectedListener listener;

    public MyAdapter(learnFragment context, ArrayList<item> lesson, learnFragment.OnItemSelectedListener listener) {
        this.context = context;
        this.lesson = lesson;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_lesson,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.changePosition(position);
        holder.list_item_lesson_textview.setText(getItem(position).shade_name);
        holder.list_item_lesson_imageview.setImageResource(getItem(position).lesson_image);
    }

    public item getItem(int i) {
        return lesson.get(i);
    }

    @Override
    public int getItemCount() {
        return lesson.size();
    }

    public void updateVedio(item information, int pos) {
        listener.onVedioItemSelected(information, pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item_lesson_textview;
        ImageView list_item_lesson_imageview;
        TextView textView1;
        int position;

        public void changePosition(int position){
            this.position = position;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            list_item_lesson_textview = (TextView) itemView.findViewById(R.id.list_item_lesson_textview);
            list_item_lesson_imageview = (ImageView) itemView.findViewById(R.id.list_item_lesson_imageview);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    updateVedio(getItem(position), position);
                }
            });
        }
    }
}