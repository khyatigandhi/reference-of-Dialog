package com.knoxpo.khyati.refofdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();
    public static final String ARGS_INITIAL_COUNTER = TAG + ".ARGS_INITIAL_COUNTER";
    public static final String STATE_COUNTER = TAG + ".STATE_COUNTER";

    public static MainFragment newInstance(int initialCounter) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INITIAL_COUNTER, initialCounter);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface Callback{
        void onCounterUpdated(int updatedCounterValue);
    }

    private Callback mCallback;
    private Button mClickMeBtn;
    private int mCounter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) getActivity();
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCounter = getArguments().getInt(ARGS_INITIAL_COUNTER);
        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_COUNTER)){
            mCounter = savedInstanceState.getInt(STATE_COUNTER, mCounter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        init(v);

        mClickMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                mCallback.onCounterUpdated(mCounter);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCallback.onCounterUpdated(mCounter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_COUNTER, mCounter);
        super.onSaveInstanceState(outState);
    }

    private void init(View v){
        mClickMeBtn = v.findViewById(R.id.btn_click_me);
    }
}
