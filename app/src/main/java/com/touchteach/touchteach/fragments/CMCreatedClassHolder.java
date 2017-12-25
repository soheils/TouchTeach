package com.touchteach.touchteach.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.touchteach.touchteach.Adapters.CreatedClassRVAdapter;
import com.touchteach.touchteach.Decorations.GridSpacingItemDecoration;
import com.touchteach.touchteach.R;
import com.touchteach.touchteach.coustomViews.Utiles;
import com.touchteach.touchteach.tools.Class;
import com.touchteach.touchteach.tools.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sazgar on 11/6/2017.
 */

public class CMCreatedClassHolder extends Fragment{
    //todo complete
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
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

        final CreatedClassRVAdapter adapter = new CreatedClassRVAdapter();

        new AsyncTask<Void, Void, List<Class>>() {
            @Override
            protected List<Class> doInBackground(Void... voids) {
                return Users.sharePreferenceLoad(CMCreatedClassHolder.this.getContext()).CreatedClasses();
            }

            @Override
            protected void onPostExecute(List<Class> classes) {
                super.onPostExecute(classes);
                adapter.addClasses(classes);
                adapter.notifyDataSetChanged();
            }
        }.execute();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                adapter.addClasses(Users.sharePreferenceLoad(CMCreatedClassHolder.this.getContext()).CreatedClasses());
//            }
//        }).start();
        recyclerView.setAdapter(adapter);
        return root;
    }



}
