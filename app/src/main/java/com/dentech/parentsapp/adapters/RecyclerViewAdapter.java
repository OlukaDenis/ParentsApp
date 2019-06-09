package com.dentech.parentsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dentech.parentsapp.R;
import com.dentech.parentsapp.model.Marks;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MarksViewHolder> {
    private Context context;
    List<Marks> listMarks;

    public RecyclerViewAdapter(Context context, List<Marks> listMarks) {
        this.listMarks = listMarks;
        this.context = context;
    }

    @NonNull
    @Override
    public MarksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_marks,parent,false);
        MarksViewHolder marksViewHolder = new MarksViewHolder(view);
        return marksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MarksViewHolder marksViewHolder, int position) {
        Marks data = listMarks.get(position);
        marksViewHolder.coursecode.setText(data.getCourseCode());
        marksViewHolder.coursename.setText(data.getCourseUnit());
        marksViewHolder.score.setText(data.getScore());
        marksViewHolder.grade.setText(data.getGrade());

    }


    @Override
    public int getItemCount() {
        return listMarks.size();
    }

    public void clear(){
        listMarks.clear();
        notifyDataSetChanged();

    }
}
