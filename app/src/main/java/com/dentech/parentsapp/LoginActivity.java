package com.dentech.parentsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dentech.parentsapp.common.Common;
import com.dentech.parentsapp.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.BitSet;

import mehdi.sakout.fancybuttons.FancyButton;
import mehdi.sakout.fancybuttons.Utils;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference student_table;
    private MaterialEditText studentNumber, password;
    FancyButton btnSignin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setTitle("Logging in");
        progressDialog.setCancelable(false);

        studentNumber = (MaterialEditText) findViewById(R.id.etStudentNumber);
        password = (MaterialEditText) findViewById(R.id.etPass);
        btnSignin = (FancyButton) findViewById(R.id.sign_in_button);

        //init firebase
        //FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        student_table =database.getReference("Student");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (studentNumber.getText().toString().isEmpty()) {
                    studentNumber.setError("You must fill in the student number!");
                    studentNumber.requestFocus();
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("You must fill in the password!");
                    password.requestFocus();
                }
                else {
                    progressDialog.show();
                    final String mPass = password.getText().toString();
                    final String mStudentNo = studentNumber.getText().toString();
                    student_table.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(mStudentNo).exists()){
                                Student student = dataSnapshot.child(mStudentNo).getValue(Student.class);
                                student.setStudent_number(mStudentNo);
                                if(student.getPassword().equals(mPass)){
                                    Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                    Common.current_student = student;
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    finish();
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
    }


}
