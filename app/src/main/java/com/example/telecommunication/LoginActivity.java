package com.example.telecommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText etemail,etpassword;
    Button btn_login;

    FrameLayout frameLayout;
    FirebaseAuth auth;
    private ProgressDialog loadingbar;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        frameLayout=findViewById( R.id.framelayout );
        auth = FirebaseAuth.getInstance();

        etemail=findViewById( R.id.etEmail );
        etpassword=findViewById( R.id.etPassword );
        btn_login=findViewById( R.id.btnUserLogin );

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = etemail.getText().toString();
                String txt_password = etpassword.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        reference = FirebaseDatabase.getInstance().getReference( "Users" ).child( currentUser.getUid() );

                                        reference.addValueEventListener( new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                String userType = dataSnapshot.child( "usertype" ).getValue().toString();
                                                if (userType.equals( "Patient" )) {



                                                    frameLayout.setVisibility(View.GONE);
                                                    AllDoctorFragment doctorfragment=new AllDoctorFragment();
                                                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.container, doctorfragment);
                                                    transaction.commit();


                                                } else if (userType.equals( "Doctor" )) {
                                                   Intent intent=new Intent( LoginActivity.this, DoctorActivity.class );
                                                   startActivity( intent );
                                                   finish();
                                                } else {
                                                    Toast.makeText( LoginActivity.this, "Failed Login. Please Try Again", Toast.LENGTH_SHORT ).show();
                                                    return;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        } );


                                    }
                                }
                            });
                }
            }
        });
    }



}
