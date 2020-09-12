package com.maniraghu.flashchatnewfirebase.ui.dashboard.JobSecurity;

import android.app.job.JobInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maniraghu.flashchatnewfirebase.BaseFragment;
import com.maniraghu.flashchatnewfirebase.R;
import com.maniraghu.flashchatnewfirebase.ui.dashboard.FeelingLonely.Doctor;
import com.maniraghu.flashchatnewfirebase.ui.dashboard.FeelingLonely.DoctorListViewModel;
import com.maniraghu.flashchatnewfirebase.ui.dashboard.FeelingLonely.DoctorRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobSecurity extends BaseFragment {

    private JobSecurityViewModel mViewModel;
    private JobRecyclerView jobView;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private List<Job> mDoctor;
    private ProgressBar mProgressCircle;
    public static JobSecurity newInstance() {
        return new JobSecurity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_security_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JobSecurityViewModel.class);
        // TODO: Use the ViewModel
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.doctor_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("jobInfo");
        mAuth= FirebaseAuth.getInstance();
        mDoctor=new ArrayList<>();
        mProgressCircle=getActivity().findViewById(R.id.doctor_list_progress_circle);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Job d=ds.getValue(Job.class);
                    mDoctor.add(d);
                }
                Collections.reverse(mDoctor);
                jobView=new JobRecyclerView(getContext(),mDoctor);
                recyclerView.setAdapter(jobView);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

}
