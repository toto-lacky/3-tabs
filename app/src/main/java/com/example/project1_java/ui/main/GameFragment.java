package com.example.project1_java.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1_java.FixableViewPager;
import com.example.project1_java.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameFragment extends Fragment {

    //private static final String ARG_SECTION_NUMBER = "section_number";

    //private PageViewModel pageViewModel;

    public static GameFragment newInstance(int index) {
        /*GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;*/
        return new GameFragment();
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        //pageViewModel.setIndex(index);
    }*/

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);


        //final TextView textView = root.findViewById(R.id.section_label);
        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        final View view = getView();
        assert view != null;
        Button startButton = view.findViewById(R.id.game_start_button);
        //Log.d("startButton",""+startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FixableViewPager viewPager;
                try{
                    viewPager = getActivity().findViewById(R.id.view_pager);
                    viewPager.setPageFixed(!viewPager.getPageFixed());
                }
                catch(Exception e){
                    Log.d("onStart","Error");
                }
            }
        });
    }
}