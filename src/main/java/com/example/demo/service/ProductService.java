package com.example.demo.service;

import com.example.demo.entity.Field;
import com.example.demo.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private static final String COLLECTION_NAME = "products";
    public String saveProduct(Product product) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> clWriteResultApiFuture = dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return clWriteResultApiFuture.get().getUpdateTime().toString();
    }
    public String insert(Product product){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user");
        final String[] result = {""};
        Product product1 = new Product();
        product1.setDecription(product.getDecription());
        ref.child(product.getName()).setValue(product1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    // Xử lý lỗi nếu có
                    result[0] = databaseError.getMessage();
                } else {
                    // Ghi dữ liệu thành công
                    result[0] = "Data saved successfully.";
                }
            }
        }); // Thay "key" và "value" bằng dữ liệu của bạn
        return result[0];
    }
    public Product getProductDetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        Product product = null;
        if(documentSnapshot.exists()) {
            product = documentSnapshot.toObject(Product.class);
            return product;
        } else {
            return null;
        }

    }

    public String updateProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> clWriteResultApiFuture = dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return clWriteResultApiFuture.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> clWriteResultApiFuture = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
        return "success";
    }

    public List<Product> getProductDetails() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Product> productList = new ArrayList<>();
        Product product = null;
        while (iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();
            product = document.toObject(Product.class);
            productList.add(product);
        }
        return productList;
    }



}
