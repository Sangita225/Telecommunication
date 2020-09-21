package com.example.telecommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
private Button btncreate,btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        btncreate=findViewById( R.id.btnhomesignup );
        btnlogin=findViewById( R.id.btnlogin);

        btncreate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( HomeActivity.this,RegisterActivity.class );
                startActivity( intent );
                finish();
            }
        } );


        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( HomeActivity.this,LoginActivity.class );
                startActivity( intent );
                finish();



            }
        } );
    }
}
