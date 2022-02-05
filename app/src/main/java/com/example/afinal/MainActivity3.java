package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity{
    Button buttonAddPage;
    parentFragment parentFragment;
    TextView textView;
    Serializable fragments;
    ArrayList<String>fragmentstitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getIDs();
        setEvents();
    }

    private void getIDs() {
        buttonAddPage = (Button) findViewById(R.id.buttonAddPage);
        //buttonGoBack = (Button) findViewById(R.id.buttonGoBack);
        parentFragment = (parentFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
        textView = (TextView) findViewById(R.id.editTextPageName);
    }

    private void setEvents() {
        buttonAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView.getText().toString().equals("")) {
                    parentFragment.addPage(textView.getText() + "");
                    textView.setText("");
                } else {
                    Toast.makeText(MainActivity3.this, "Page name is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    /*@Override
    public void onColorItemSelected(ArrayList<Fragment> mFragment, ArrayList<String> mFragmentTitle) {
        fragments = mFragment;
        fragmentstitle = mFragmentTitle;

        Intent showMain = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putSerializable("fragments",(Serializable)mFragment);
        showMain.putStringArrayListExtra("fragmentstitle", fragmentstitle);
        showMain.putExtras(bundle);
        startActivity(showMain);
    }*/

    /*@Override
    public parentFragment needsHide() {
        parentFragment = (parentFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
        return parentFragment;
    }*/


}