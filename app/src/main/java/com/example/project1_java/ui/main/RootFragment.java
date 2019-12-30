package com.example.project1_java.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project1_java.FixableViewPager;
import com.example.project1_java.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RootFragment extends Fragment {

    static RootFragment newInstance() {
        return new RootFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.root_frame, container, false);

        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.root_frame, new GameFragment());
        transaction.commit();

        return view;
    }

}