package com.touchteach.touchteach.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.touchteach.touchteach.Adapters.CreatedClassRVAdapter;
import com.touchteach.touchteach.Decorations.GridSpacingItemDecoration;
import com.touchteach.touchteach.R;
import com.touchteach.touchteach.coustomViews.Utiles;
import com.touchteach.touchteach.tools.Class;
import com.touchteach.touchteach.tools.Users;

/**
 * Created by sazgar on 11/6/2017.
 */

public class CMCreatedClassHolder extends Fragment{
    //todo complete
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_manager_created_class, container, false);
        Context context = getContext();
        progressBar = root.findViewById(R.id.frag_cm_created_class_pb);
        progressBar.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.frag_cm_created_class_rcv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utiles.dpToPx(10, context), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new CreatedClassRVAdapter());

        Class.getTeacherClasses(Users.sharePreferenceLoad(root.getContext()));

        return root;
    }


}
