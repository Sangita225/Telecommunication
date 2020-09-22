package com.example.telecommunication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.telecommunication.Adapter.DoctorAdapter;
import com.example.telecommunication.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllDoctorFragment extends Fragment {

    RecyclerView recyclerView;
    private List<User> mUsers;
    EditText search_users;
    private DoctorAdapter doctorAdapter;
    private Button btnprofile;
    private FrameLayout frameLayout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_doctor_fragment, container, false);

        btnprofile=view.findViewById( R.id.btnprofile );
        frameLayout=view.findViewById( R.id.framelayout );
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsers = new ArrayList<>();

        btnprofile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.GONE);
               ProfileFragment profileFragment=new ProfileFragment();
               FragmentManager fragmentManager= getFragmentManager();
              FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
              fragmentTransaction.replace( R.id.container,profileFragment );
              fragmentTransaction.commit();

            }
        } );


        doctorAdapter=new DoctorAdapter( getContext(),mUsers);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                User user = dataSnapshot.getValue(User.class);
                doctorAdapter.notifyDataSetChanged();
                if(user.getUsertype().equals( "Doctor" )){
                    mUsers.add( user );
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        doctorAdapter.notifyDataSetChanged();
        recyclerView.setAdapter( doctorAdapter );

        return view;
    }




}
