package com.dentech.parentsapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dentech.parentsapp.R;

public class MarksViewHolder extends RecyclerView.ViewHolder {
    public TextView coursecode, coursename, score, grade;

    public MarksViewHolder(@NonNull View itemView) {
        super(itemView);
        coursecode = (TextView) itemView.findViewById(R.id.et_coursecode);
        coursename = (TextView) itemView.findViewById(R.id.et_coursename);
        score = (TextView) itemView.findViewById(R.id.et_score);
        grade = (TextView) itemView.findViewById(R.id.et_grade);
    }
}
