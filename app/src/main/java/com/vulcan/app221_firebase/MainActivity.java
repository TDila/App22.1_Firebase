package com.vulcan.app221_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.vulcan.app221_firebase.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.i(TAG,db.toString());

//        User user = new User("Shehan","shehan@gmail.com","0771274596");
//
//        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Log.i(TAG,documentReference.getId());
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

//        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
////                    QuerySnapshot result = task.getResult();
////                    List<DocumentSnapshot> documents = result.getDocuments();
////                    documents.forEach(u->{
////                        String name = u.getString("name");
////                        Log.i(TAG,name);
////                    });
//
//                    for (QueryDocumentSnapshot result : task.getResult()){
//                        String name = result.getString("name");
//                        Log.i(TAG,"Name : "+name);
//                    }
//                }
//            }
//        });
//
//        db.document("/users/Dz1kLLfrrnaTYzZFF6My").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    //data retrieving 1st method
//                    DocumentSnapshot result = task.getResult();
//
//                    //data retrieving 2nd method
//                    User user = task.getResult().toObject(User.class);
//                }
//            }
//        });

//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 db.collection("cities").
//                         whereEqualTo("country","Sri Lanka")
//                         .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                             @Override
//                             public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                 if(task.isSuccessful()){
//                                     for (QueryDocumentSnapshot snapshot : task.getResult()){
//                                         Log.i(TAG,snapshot.getString("name"));
//                                         TextView textView = findViewById(R.id.textView);
//                                         textView.setText(snapshot.getString("name"));
//                                     }
//                                 }
//                             }
//                         });
//            }
//        });

//        db.collection("cities").whereEqualTo("country","Sri Lanka").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(!value.isEmpty()){
//                    List<DocumentSnapshot> documents = value.getDocuments();
//                    documents.forEach(d->{
//                        TextView textView = findViewById(R.id.textView);
//                        textView.setText(d.getString("name"));
//                    });
//                }
//            }
//        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listenerRegistration == null){
                    listenerRegistration = db.collection("cities").whereEqualTo("country","Sri Lanka").addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(!value.isEmpty()){
                                List<DocumentSnapshot> documents = value.getDocuments();
                                documents.forEach(d->{
                                    Log.i(TAG,d.getString("name"));
                                    TextView textView = findViewById(R.id.textView);
                                    textView.setText(d.getString("name"));
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}