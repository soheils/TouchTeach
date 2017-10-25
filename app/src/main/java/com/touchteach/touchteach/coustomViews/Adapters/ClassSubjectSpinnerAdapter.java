package com.touchteach.touchteach.coustomViews.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.R;
import com.touchteach.touchteach.tools.Subject;

import java.util.List;
import java.util.Map;

/**
 * Created by sazgar on 10/3/2017.
 */

//todo complete this class with right supper class
public final class ClassSubjectSpinnerAdapter extends ArrayAdapter<String> {
    public ClassSubjectSpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.create_class_spinner_view, R.id.create_class_spinner_tv);
        Subject.load(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                addAll(Subject.getSubjects());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo handel it
                Log.d("subject","fail");
            }
        });
    }

    @Override
    public void add(@Nullable String object) {
        //todo set text for image view
        super.add(object);
    }
}
