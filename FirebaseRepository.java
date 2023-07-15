package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {
    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    public FirebaseRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FirebaseRepository");
        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public void addData(String key, String data) {
        databaseReference.child(key).setValue(data);
    }

    public LiveData<List<String>> getData() {
        MutableLiveData<List<String>> liveData = new MutableLiveData<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> dataList = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    dataList.add(data);
                }

                liveData.setValue(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        return liveData;
    }

    public void updateData(String key, String newData) {
        databaseReference.child(key).setValue(newData);
    }

    public void deleteData(String key) {
        databaseReference.child(key).removeValue();
    }

    public void searchData(String searchQuery) {
        databaseReference.orderByChild("fieldName").startAt(searchQuery).endAt(searchQuery + "\uf8ff");
    }

        public void deleteItem(String key, String imageUrl) {
        storageReference.child(imageUrl).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                databaseReference.child(key).removeValue();
            }
        });
    }
    public LiveData<List<String>> retrieveData() {
        MutableLiveData<List<String>> data = new MutableLiveData<>();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("your_data_path");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> dataList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);
                    dataList.add(value);
                }
                data.setValue(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // معالجة أي خطأ هنا إذا لزم الأمر
            }
        });
        return data;
    }


}
