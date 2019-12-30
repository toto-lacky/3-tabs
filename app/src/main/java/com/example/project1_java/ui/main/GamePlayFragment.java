package com.example.project1_java.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1_java.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GamePlayFragment extends Fragment {



    static GamePlayFragment newInstance() {
        return new GamePlayFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_play, container, false);
    }



}