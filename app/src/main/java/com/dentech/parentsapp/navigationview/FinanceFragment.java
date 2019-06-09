package com.dentech.parentsapp.navigationview;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dentech.parentsapp.LoginActivity;
import com.dentech.parentsapp.R;
import com.dentech.parentsapp.common.Common;
import com.dentech.parentsapp.common.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanceFragment extends Fragment {
    private TextView sem1_tuition, sem1_paid, sem1_balance;
    private TextView sem2_tuition, sem2_paid, sem2_balance;
    String currentStudent = Common.current_student.getStudent_number();

    private FirebaseDatabase database;
    private DatabaseReference studentFinance;

    ProgressDialog progressDialog;

    public FinanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_finance, container, false);
        getActivity().setTitle("Financial Statement");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading Finances");
        progressDialog.setMessage("Please wait...");

        //init firebase
        database = FirebaseDatabase.getInstance();
        studentFinance = database.getReference()
                .child("Finance")
                .child(currentStudent);

        //init views
        sem1_tuition = (TextView) view.findViewById(R.id.tv_tuition_sem1);
        sem1_paid = (TextView) view.findViewById(R.id.tv_paid_sem1);
        sem1_balance = (TextView) view.findViewById(R.id.tv_balance_sem1);

        sem2_tuition = (TextView) view.findViewById(R.id.tv_tuition_sem2);
        sem2_paid = (TextView) view.findViewById(R.id.tv_paid_sem2);
        sem2_balance = (TextView) view.findViewById(R.id.tv_balance_sem2);

        if(Common.isNetworkAvailable(getActivity())){
            loadSemOneFinances();
            loadSemTwoFinances();
        }
        else {
            buildNoNetworkDialog();
        }


        return view;
    }

    private void buildNoNetworkDialog() {
        progressDialog.dismiss();
        final AlertDialog noNetworkDialog = new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setTitle("Connection failed")
                .setMessage("Please check your internet connection")
                .setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadSemOneFinances();
                        loadSemTwoFinances();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        noNetworkDialog.show();
    }


    private void loadSemOneFinances() {
        if (!Common.isNetworkAvailable(getActivity())){
            buildNoNetworkDialog();
        }
        progressDialog.show();
        studentFinance.child("year1").child("sem1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String total = dataSnapshot.child("total").getValue().toString();
                final String paid = dataSnapshot.child("paid").getValue().toString();
                final String  balance = dataSnapshot.child("balance").getValue().toString();

                sem1_tuition.setText(Util.formatNumber(total));
                sem1_paid.setText(Util.formatNumber(paid));
                sem1_balance.setText(Util.formatNumber(balance));
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void loadSemTwoFinances() {
        if (!Common.isNetworkAvailable(getActivity())){
            buildNoNetworkDialog();
        }
        progressDialog.show();
        studentFinance.child("year1").child("sem2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String total = dataSnapshot.child("total").getValue().toString();
                final String paid = dataSnapshot.child("paid").getValue().toString();
                final String  balance = dataSnapshot.child("balance").getValue().toString();

                sem2_tuition.setText(Util.formatNumber(total));
                sem2_paid.setText(Util.formatNumber(paid));
                sem2_balance.setText(Util.formatNumber(balance));
                progressDialog.dismiss();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

}
