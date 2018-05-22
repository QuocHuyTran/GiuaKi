package com.example.quochuy.giuaki_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword;
    Button btnCreate;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        setID();
    }

    protected void setID(){
        editTextPassword=findViewById(R.id.edt_password);
        editTextUsername=findViewById(R.id.edt_username);
        btnCreate=findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAuthentication(editTextUsername.getText().toString(),
                        editTextPassword.getText().toString());
                User user=new User(editTextUsername.getText().toString(),
                        editTextPassword.getText().toString());
                databaseReference.child("User_Information").push().setValue(user);
                Intent intent=new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void setAuthentication(String username,String password){
        firebaseAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("User",task.isSuccessful()+"");
                        Toast.makeText(CreateActivity.this, task.isSuccessful()+"", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
