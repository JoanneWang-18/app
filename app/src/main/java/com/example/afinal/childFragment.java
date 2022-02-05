package com.example.afinal;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class childFragment extends Fragment {
    String childname;
    String a;
    TextView textViewChildName;
    EditText editText;
    childFragment.ChildFragInterface listener;
    String put;
    ImageButton saveInside;
    int pos;
    String prev;
    Boolean change = false;
    Boolean check = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        Bundle bundle = getArguments();
        childname = bundle.getString("data");
        put = bundle.getString("put");
        pos = bundle.getInt("pos");
        getIDs(view);
        setEvents();
        return view;
    }

    private void getIDs(View view) {
        textViewChildName = (TextView) view.findViewById(R.id.textViewChild);
        textViewChildName.setText(childname);
        editText = (EditText) view.findViewById(R.id.editText);
        editText.setText(put);
        prev = put;
        saveInside = (ImageButton) view.findViewById(R.id.saveInside);
        saveInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if ((!s.equals(prev)))
                    change = true;
                else
                    change = false;
                listener.getEdit(s, pos, change);
                prev = s;
            }
        });
    }

    public interface ChildFragInterface {
        public void getEdit(String s, int pos, Boolean change);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof childFragment.ChildFragInterface) {
            listener = (childFragment.ChildFragInterface) context;
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

    private void setEvents() {

    }
}