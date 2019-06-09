package com.dentech.parentsapp.navigationview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dentech.parentsapp.R;
import com.dentech.parentsapp.common.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView name, student_no, course, status, cgpa;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle(Common.current_student.getName());

        name = (TextView) view.findViewById(R.id.tv_user_name);
        student_no = (TextView) view.findViewById(R.id.tv_student_number);
        status = (TextView) view.findViewById(R.id.tv_user_status);
        course = (TextView) view.findViewById(R.id.tv_user_course);
        cgpa = (TextView) view.findViewById(R.id.tv_user_cgpa);

        name.setText(Common.current_student.getName());
        course.setText(Common.current_student.getCourse());
        student_no.setText(Common.current_student.getStudent_number());
        status.setText("Resident");
        cgpa.setText(Common.student_cgpa);

        return view;
    }

}
