package com.example.project1_java.ui.main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project1_java.FixableViewPager;
import com.example.project1_java.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameFragment extends Fragment {

    static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        final View view = getView();
        assert view != null;

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                draw();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        Button startButton = view.findViewById(R.id.game_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Block swipe */
                Activity activity = getActivity();
                assert activity != null;
                FixableViewPager viewPager = activity.findViewById(R.id.view_pager);
                viewPager.setPageFixed(true);

                /* Switch to gamePlayFragment */
                assert getFragmentManager() != null;
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                GamePlayFragment new_gameplay_fragment = new GamePlayFragment();
                trans.replace(R.id.game_fragment, new_gameplay_fragment);
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                //trans.addToBackStack(null);
                trans.commit();

                SectionsPagerAdapter adapter = viewPager.getAdapter();
                adapter.replaceFragment(2,new_gameplay_fragment);
            }
        });
    }

    public void draw() {
        View view = getView();
        assert view != null;
        View parent = (View) view.getParent();
        int width = parent.getWidth();
        int height = parent.getHeight();

        int blockSize = width / 4;
        int titleHeight = blockSize * 2;
        int buttonSize = (int) (blockSize * 1.5);

        //제목 크기 및 위치 조정
        int title_top = height / 2 - titleHeight * 6 / 5;
        RelativeLayout.LayoutParams title_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, titleHeight);
        //RelativeLayout.LayoutParams title_params = new RelativeLayout.LayoutParams(, 100);
        title_params.setMargins(0, title_top, 0, 10);
        View title = view.findViewById(R.id.game_title);
        title.setLayoutParams(title_params);

        //버튼 크기 및 위치 조정
        int button_top = (height * 3 / 2 - titleHeight / 5 - buttonSize) / 2;
        int button_left = width / 2 - buttonSize / 2;
        RelativeLayout.LayoutParams button_params = new RelativeLayout.LayoutParams(buttonSize, buttonSize);
        //RelativeLayout.LayoutParams button_params = new RelativeLayout.LayoutParams(100, 100);
        button_params.setMargins(button_left, button_top, 0, 10);
        //button_params.setMargins(0,button_top,0,0);
        View button = view.findViewById(R.id.game_start_button);
        button.setLayoutParams(button_params);
    }
}