package com.example.telecommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText etlname, etaddress, etphone, etclinic, etspeciality, etexperience, etemail, etpassword;
    private EditText etfname;
    private ImageView profile;
    Spinner gender,usertype;
    private String imagePath;
    private String Mygender,Myuser;
    private Button signup;
    private String downloadUrl;
    private ProgressDialog progressDialog;

    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        etfname = findViewById( R.id.dfname );
        etaddress = findViewById( R.id.daddress );
        etclinic = findViewById( R.id.dclinic );
        etspeciality = findViewById( R.id.dsepeciality );
        etexperience = findViewById( R.id.dexperience );
        etemail = findViewById( R.id.demail );
        etpassword = findViewById( R.id.dpassword );
        gender = findViewById( R.id.spinnergender );
        usertype=findViewById( R.id.spinnerusertype );
        Myuser=usertype.getSelectedItem().toString();
        Mygender= gender.getSelectedItem().toString();
        signup = findViewById( R.id.btnsignup );
        progressDialog=new ProgressDialog( this );

        auth = FirebaseAuth.getInstance();

        usertype.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals( "Patient" )){
                    etclinic.setVisibility(View.GONE );
                    etspeciality.setVisibility( View.GONE );
                    etexperience.setVisibility( View.GONE );


                }
                else{
                    etclinic.setVisibility(View.VISIBLE );
                    etspeciality.setVisibility( View.VISIBLE );
                    etexperience.setVisibility( View.VISIBLE );
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = Myuser;
                String txt_fullname = etfname.getText().toString();
                String txt_address = etaddress.getText().toString();
                String txt_gender = Mygender;
                String txt_clinic = etclinic.getText().toString();
                String txt_speciality = etspeciality.getText().toString();
                String txt_experience= etexperience.getText().toString();
                String txt_email = etemail.getText().toString();
                String txt_password = etpassword.getText().toString();

                if(Myuser.equals( "Doctor" )){
                    if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_fullname) || TextUtils.isEmpty(txt_address )  || TextUtils.isEmpty(txt_gender)
                            || TextUtils.isEmpty(txt_clinic) || TextUtils.isEmpty(txt_speciality) || TextUtils.isEmpty(txt_experience) || TextUtils.isEmpty(txt_email)
                            || TextUtils.isEmpty(txt_password))

                    {
                        Toast.makeText(RegisterActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                    } else if (txt_password.length() < 6 ){
                        Toast.makeText(RegisterActivity.this, "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    } else {
                        register(txt_username,txt_fullname, txt_address ,txt_gender,txt_clinic,txt_speciality, txt_experience,txt_email, txt_password);
                    }
                }

                else{


                    Toast.makeText( RegisterActivity.this, "user not found", Toast.LENGTH_SHORT ).show();

                }
            }
        } );



    }


    private void register(final String usertype, final String fullname, final String address, final String gender, final String clinic,
                          final String speciality, final String experience, String email, String password) {

        auth.createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference( "Users" ).child( userid );

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put( "id", userid );
                            hashMap.put( "fullname", fullname );
                            hashMap.put( "address", address );
                            hashMap.put( "gender", gender );
                            hashMap.put( "clinic", clinic );
                            hashMap.put( "speciality", speciality );
                            hashMap.put( "experience", experience );
                            hashMap.put( "fullname", fullname );
                            hashMap.put( "imageURL", "default" );
                            hashMap.put( "status", "offline" );
                            hashMap.put( "search", fullname.toLowerCase() );

                            reference.setValue( hashMap ).addOnCompleteListener( new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent( RegisterActivity.this, LoginActivity.class );
                                        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                        startActivity( intent );
                                        finish();
                                    }
                                }
                            } );
                        } else {
                            Toast.makeText( RegisterActivity.this, "You can't register woth this email or password", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
    }
}
