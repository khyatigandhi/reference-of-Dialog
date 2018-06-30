package com.knoxpo.khyati.refofdialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private TextView mCounterTV;
    private int mCounter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        FragmentManager fm = getSupportFragmentManager();
        Fragment existingFragment = fm.findFragmentById(R.id.fragment_container);

        if(existingFragment == null){
            fm.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance(mCounter))
                    .commit();
        }

        updateUI();
    }

    private void init(){
        mCounterTV = findViewById(R.id.tv_counter);
    }

    private void updateUI(){
        mCounterTV.setText(String.valueOf(mCounter));
    }

    @Override
    public void onCounterUpdated(int updatedCounterValue){
        mCounter = updatedCounterValue;
        updateUI();
    }
}
