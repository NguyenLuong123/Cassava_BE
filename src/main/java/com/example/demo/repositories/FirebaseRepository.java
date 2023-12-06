package com.example.demo.repositories;

import com.example.demo.entity.FieldDTO;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class FirebaseRepository {
    private DatabaseReference databaseReference;
    public FirebaseRepository() {
    }

    public CompletableFuture<String> getData(String path) {
        CompletableFuture<String> future = new CompletableFuture<>();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(path);
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                future.complete(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
}
