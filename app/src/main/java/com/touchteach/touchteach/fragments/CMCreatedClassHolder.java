package com.touchteach.touchteach.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class CMCreatedClassHolder extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    //todo complete
    private SwipeRefreshLayout mRefreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CreatedClassRVAdapter mRvAdapter = new CreatedClassRVAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_manager_created_class, container, false);
        Context context = getContext();

//        progressBar = root.findViewById(R.id.frag_cm_created_class_pb);
//        progressBar.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.frag_cm_created_class_rcv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utiles.dpToPx(10, context), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mRvAdapter);

        mRefreshLayout = root.findViewById(R.id.frag_cm_created_class_refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);

        onRefresh();

        return root;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);

        new AsyncTask<Void, Void, List<Class>>() {
            @Override
            protected List<Class> doInBackground(Void... voids) {
                return Users.sharePreferenceLoad(CMCreatedClassHolder.this.getContext()).CreatedClasses();
            }

            @Override
            protected void onPostExecute(List<Class> classes) {
                mRvAdapter.removeClasses();
                mRvAdapter.addClasses(classes);
                mRefreshLayout.setRefreshing(false);
            }
        }.execute();
    }
}
