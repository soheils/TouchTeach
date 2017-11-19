package com.touchteach.touchteach.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.touchteach.touchteach.R;

/**
 * Created by sazgar on 11/8/2017.
 */

public class CreatedClassRVAdapter extends RecyclerView.Adapter<CreatedClassRVAdapter.CreatedClassRVHolder> {

    @Override
    public CreatedClassRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_class_manager_class_created, parent, false);
        return new CreatedClassRVHolder(view);
    }

    @Override
    public void onBindViewHolder(CreatedClassRVHolder holder, int position) {
        holder.capacity.setText("200");
        holder.students.setText(""+position);
        if (position%2 == 0)
            holder.progressBar.setVisibility(View.INVISIBLE);

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return 110;
    }

    class CreatedClassRVHolder extends RecyclerView.ViewHolder{
        TextView capacity;
        TextView title;
        TextView students;
        ImageView image;
        ProgressBar progressBar;

        public CreatedClassRVHolder(View itemView) {
            super(itemView);
            capacity = itemView.findViewById(R.id.item_cm_class_created_tv_capacity);
            title = itemView.findViewById(R.id.item_cm_class_created_tv_title);
            students = itemView.findViewById(R.id.item_cm_class_created_tv_students);
            image = itemView.findViewById(R.id.item_cm_class_created_im);
            progressBar = itemView.findViewById(R.id.item_cm_class_created_pr);
        }


    }
}


