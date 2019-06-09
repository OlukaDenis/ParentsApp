package com.dentech.parentsapp.navigationview;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dentech.parentsapp.R;
import com.dentech.parentsapp.adapters.MarksViewHolder;
import com.dentech.parentsapp.adapters.RecyclerViewAdapter;
import com.dentech.parentsapp.common.Common;
import com.dentech.parentsapp.model.Marks;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarksFragment extends Fragment {
    private RecyclerView sem1Recyclerview, sem2Recyclerview;
    private RecyclerView.LayoutManager layoutManager1, layoutManager2;
    FirebaseRecyclerAdapter<Marks, MarksViewHolder> marksAdapter;
    FirebaseDatabase database;
    DatabaseReference sem1Marks, sem2Marks;
    Button btnSem1, btnSem2;
    LinearLayout sem1Layout, sem2Layout;
    List<Marks> sem1MarksList, sem2MarksList;
    ProgressDialog dialog;
    RecyclerViewAdapter adapter1, adapter2;


    public MarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marks, container, false);
        getActivity().setTitle("Marks");

        sem1Recyclerview = (RecyclerView) view.findViewById(R.id.y2s1_recyclerview);
        sem2Recyclerview = (RecyclerView) view.findViewById(R.id.y2s2_recyclerview);
        layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager2 = new LinearLayoutManager(getActivity());
        sem1Recyclerview.setLayoutManager(layoutManager1);
        sem2Recyclerview.setLayoutManager(layoutManager2);

        sem1MarksList = new ArrayList<>();
        sem2MarksList = new ArrayList<>();
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Wait");
        dialog.setMessage("Loading marks....");


        //init firebase
        database = FirebaseDatabase.getInstance();
        sem1Marks = database.getReference().child("Marks")
                .child(Common.current_student.getStudent_number())
                .child("year1")
                .child("sem1");
        sem2Marks = database.getReference().child("Marks")
                .child(Common.current_student.getStudent_number())
                .child("year1")
                .child("sem2");

        adapter1 = new RecyclerViewAdapter(getActivity(), sem1MarksList);
        adapter2 = new RecyclerViewAdapter(getActivity(), sem2MarksList);

        btnSem1 = (Button) view.findViewById(R.id.btn_sem1);
        btnSem2 = (Button) view.findViewById(R.id.btn_sem2);
        sem1Layout = (LinearLayout) view.findViewById(R.id.sem_one_layout);
        sem2Layout = (LinearLayout) view.findViewById(R.id.sem_two_layout);
        btnSem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem1Layout.setVisibility(View.VISIBLE);
                loadYearOneSemOneMarks();
                sem2Layout.setVisibility(View.GONE);
                btnSem1.setBackgroundColor(getResources().getColor(R.color.white));
                btnSem1.setTextColor(getResources().getColor(R.color.black));
                btnSem2.setBackgroundColor(getResources().getColor(R.color.black));
                btnSem2.setTextColor(getResources().getColor(R.color.white));

                adapter2.clear();
                adapter1.clear();
            }
        });

        btnSem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem2Layout.setVisibility(View.VISIBLE);
                sem1Layout.setVisibility(View.GONE);
                loadYearOneSemTwoMarks();
                btnSem2.setBackgroundColor(getResources().getColor(R.color.white));
                btnSem1.setBackgroundColor(getResources().getColor(R.color.black));
                btnSem1.setTextColor(getResources().getColor(R.color.white));
                btnSem2.setTextColor(getResources().getColor(R.color.black));

                adapter1.clear();
                adapter2.clear();
            }
        });

        return view;
    }

    private void loadYearOneSemOneMarks() {
        dialog.show();
        sem1Marks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Marks marks = snapshot.getValue(Marks.class);
                        sem1MarksList.add(marks);

                        sem1Recyclerview.setAdapter(adapter1);
                        dialog.dismiss();
                    }

                    // String message = dataSnapshot.getValue().toString();
                    //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Catch block:", Log.getStackTraceString(e));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadYearOneSemTwoMarks() {
        dialog.show();
        sem2Marks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Marks marks = snapshot.getValue(Marks.class);
                        sem2MarksList.add(marks);

                        sem2Recyclerview.setAdapter(adapter2);
                        dialog.dismiss();
                    }

                    // String message = dataSnapshot.getValue().toString();
                    //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Catch block:", Log.getStackTraceString(e));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
