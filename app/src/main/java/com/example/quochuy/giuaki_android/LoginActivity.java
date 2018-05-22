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

public class LoginActivity extends AppCompatActivity {

    private String username="tranquochuy";
    private String password="123";

    private EditText edtUsername,edtPassword;
    private Button btnLogin,btnCreate;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        setID();
    }

    protected void setID(){
        edtUsername=findViewById(R.id.id_edt_username);
        edtPassword=findViewById(R.id.id_edt_pass);
        btnLogin=findViewById(R.id.id_btn_login);
        btnCreate=findViewById(R.id.id_btn_create);
        setControl();
    }

    protected void setControl(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFirebase(edtUsername.getText().toString(),edtPassword.getText().toString());
//                if(edtUsername.getText().toString().equals(username) &&
//                        edtPassword.getText().toString().equals(password))
//                {
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(LoginActivity.this, "Wrong pass or account,please check again",
//                            Toast.LENGTH_SHORT).show();
//                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void SignInFirebase(String username,String password){
        firebaseAuth.signInWithEmailAndPassword(username,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("Sign In:",task.isSuccessful()+"");
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Your Account doesn't active", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
