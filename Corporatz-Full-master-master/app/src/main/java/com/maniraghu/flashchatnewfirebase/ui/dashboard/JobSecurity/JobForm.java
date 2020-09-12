package com.maniraghu.flashchatnewfirebase.ui.dashboard.JobSecurity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maniraghu.flashchatnewfirebase.R;
import com.maniraghu.flashchatnewfirebase.ui.dashboard.FeelingLonely.Doctor;
import com.maniraghu.flashchatnewfirebase.ui.dashboard.FeelingLonely.DoctorFormViewModel;

public class JobForm extends Fragment {

    private JobFormViewModel mViewModel;
    private AutoCompleteTextView jobname,company,salary,joindate,email,mobile;
    Button submit;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public FirebaseUser currUser;
    public static JobForm newInstance() {
        return new JobForm();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_form_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JobFormViewModel.class);
        // TODO: Use the ViewModel

        jobname=getActivity().findViewById(R.id.DoctorName);
        company=getActivity().findViewById(R.id.aeo);
        salary=getActivity().findViewById(R.id.qua);
        joindate=getActivity().findViewById(R.id.hospi);
        email=getActivity().findViewById(R.id.times);
        mobile=getActivity().findViewById(R.id.mb);
        submit=getActivity().findViewById(R.id.doctor_submit_button);
        mDatabase = FirebaseDatabase.getInstance().getReference("jobInfo");
        mAuth= FirebaseAuth.getInstance();
        currUser=mAuth.getCurrentUser();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Job d=new Job(currUser.getUid(),jobname.getText().toString(),company.getText().toString(),salary.getText().toString(),joindate.getText().toString(),email.getText().toString(),mobile.getText().toString());
                String id=mDatabase.push().getKey();
                mDatabase.child(id).setValue(d);
            }
        });

    }

}
