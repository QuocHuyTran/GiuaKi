package com.example.quochuy.giuaki_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FilmDetail extends AppCompatActivity {

    ImageView Poster;
    TextView Name,Time,Year,Rate,Describe;
    Button Watching;
    protected ArrayList<FilmClass> arrayList=new ArrayList<>();
    protected FilmAdapter adapter;

    FirebaseStorage firebaseStorage;
    StorageReference storageRef;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK && data!=null)
        {
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            Poster.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        setID();
        Intent intent=getIntent();
        FilmClass filmClass=new FilmClass(
                intent.getStringExtra("film_name"),
                intent.getIntExtra("film_time",0),
                intent.getStringExtra("film_content"),
                intent.getStringExtra("film_url"),
                intent.getStringExtra("image_url"));

        filmClass.setRATE(5);
        filmClass.setYEAR("1996");

        getValueFromFilm(filmClass);

        firebaseStorage=FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference();
        firebaseAuth=FirebaseAuth.getInstance();
//        setAuthentication();
//        UploadImageOnFirebase();
        SignInFirebase();
    }

    protected void setAuthentication(){
        firebaseAuth.createUserWithEmailAndPassword("romandra9x@gmail.com","ngox1996")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("User",task.isSuccessful()+"");
                        Toast.makeText(FilmDetail.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void SignInFirebase(){
        firebaseAuth.signInWithEmailAndPassword("romandra9x@gmail.com","ngox1996").
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("Sign In:",task.isSuccessful()+"");

                    }
                });
    }


    protected void setID(){
        Poster=findViewById(R.id.id_img_poster);
//        Poster.setImageResource(R.drawable.vaithieu);
        Poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        Name=findViewById(R.id.id_tv_name);
        Time=findViewById(R.id.id_tv_time);
        Year=findViewById(R.id.id_tv_nsx);
        Rate=findViewById(R.id.id_tv_rate);
        Describe=findViewById(R.id.id_tv_describe);
        Watching=findViewById(R.id.id_btn_watch);
        setControl();
    }

    protected void getValueFromFilm(FilmClass filmClass)
    {
        Name.setText(filmClass.getNAME().toString());
        Time.setText(filmClass.getTIME()+"");
        Year.setText(filmClass.getYEAR().toString());
        Rate.setText(filmClass.getRATE()+"");
        Describe.setText(filmClass.getCONTENT());
        Picasso.with(getApplicationContext())
                .load(filmClass.getURL_IMAGE())
                .into(Poster);
    }

    protected void setControl(){
        Watching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImageOnFirebase();
            }
        });
    }

    protected void UploadImageOnFirebase(){
        
        StorageReference mountainsRef = storageRef.child("avenger_one.png");
        
        Poster.setDrawingCacheEnabled(true);
        Poster.buildDrawingCache();
        Bitmap bitmap = Poster.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        
        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(FilmDetail.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(FilmDetail.this, "Success", Toast.LENGTH_SHORT).show();
                Log.e("Link",downloadUrl+"");
            }
        });
    }
}
