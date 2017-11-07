package com.touchteach.touchteach.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.touchteach.touchteach.R;

/**
 * Created by sazgar on 11/6/2017.
 */

public class CMCreatedClassHolder extends Fragment{
    //todo complete
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_manager_created_class, container, false);
        progressBar = root.findViewById(R.id.frag_cm_crated_class_pb);
        return root;
    }


}
