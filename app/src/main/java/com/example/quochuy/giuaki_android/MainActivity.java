package com.example.quochuy.giuaki_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ListView listView;
    protected ArrayList<FilmClass> arrayList=new ArrayList<>();
    protected FilmAdapter adapter;
    protected DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.id_listview);
        adapter=new FilmAdapter(MainActivity.this,R.layout.layout_custom,arrayList);
        listView.setAdapter(adapter);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

//------------------------------------------------------------------------------------------------------
//        FilmClass filmClass= new FilmClass("Avenger:Infinity War",120,"content",
//                "url","url");
//        databaseReference.child("FilmInformation").push().setValue(filmClass);
//
//        filmClass=new FilmClass("Black Panther",120,"content","url","url");
//        databaseReference.child("FilmInformation").push().setValue(filmClass);
//
//        filmClass= new FilmClass("Doctor Strange",120,"content","url","url");
//        databaseReference.child("FilmInformation").push().setValue(filmClass);
//
//        filmClass= new FilmClass("Avenger: Age of Ultron",120,"content","url","url");
//        databaseReference.child("FilmInformation").push().setValue(filmClass);
//        databaseReference =database.getReference("message");
//        databaseReference.setValue("hello");
        setList();

    }

    protected void UpImageToFirebase(){

    }

    protected void setList(){
        databaseReference.child("FilmInformation").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FilmClass filmClass= dataSnapshot.getValue(FilmClass.class);
//                Toast.makeText(MainActivity.this,filmClass.getNAME(),Toast.LENGTH_SHORT).show();
                arrayList.add(filmClass);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
//        arrayList.add(new FilmClass("Avenger:Infinity War",120,"content","url","url"));
//        arrayList.add(new FilmClass("Black Pandther",120,"content","url","url"));
//        arrayList.add(new FilmClass("Doctor Strange",120,"content","url","url"));
//        arrayList.add(new FilmClass("Avenger: Age of Ultron",120,"content","url","url"));
//        arrayList.add(new FilmClass("Spider-man: Home Coming",120,"content","url","url"));
//        arrayList.add(new FilmClass("Captain America: Civil War",120,"content","url","url"));
//        arrayList.add(new FilmClass("Thor:Ragnarog",120,"content","url","url"));

        setControlListView();
    }

    protected void setControlListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilmClass filmClass=arrayList.get(position);
                Intent intent=new Intent(MainActivity.this,FilmDetail.class);
                intent.putExtra("film_name",filmClass.getNAME());
                intent.putExtra("film_time",filmClass.getTIME());
                intent.putExtra("film_content",filmClass.getCONTENT());
                intent.putExtra("film_url",filmClass.getURL());
                intent.putExtra("image_url",filmClass.getURL_IMAGE());
                startActivity(intent);
            }
        });
    }



}
