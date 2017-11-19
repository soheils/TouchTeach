package com.touchteach.touchteach.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.R;
import com.touchteach.touchteach.tools.Subject;

import java.util.List;
import java.util.Map;

/**
 * Created by sazgar on 10/3/2017.
 */

public final class ClassSubjectSpinnerAdapter implements SpinnerAdapter {

    private final Context context;
    private int count = 0;

    public ClassSubjectSpinnerAdapter(Context context) {
        this.context = context;
        Subject.load(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                count = response.size();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo handle it
            }
        });
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return Subject.getSubject(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View result = inflater.inflate(R.layout.item_create_class_spinner, viewGroup, false);
        TextView subjectTv = result.findViewById(R.id.create_class_spinner_tv);
        subjectTv.setText(((Subject)getItem(i)).toString());
        return result;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup);
    }

}
