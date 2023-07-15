package com.example.final_project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<List<String>> data;

    public MainViewModel() {
        firebaseRepository = new FirebaseRepository();
        data = firebaseRepository.getData();
    }

    public void addData(String key, String data) {
        firebaseRepository.addData(key, data);
    }

    public LiveData<List<String>> getData() {
        return data;
    }

    public void updateData(String key, String newData) {
        firebaseRepository.updateData(key, newData);
    }

    public void deleteData(String key) {
        firebaseRepository.deleteData(key);
    }

    public void searchData(String searchQuery) {
        firebaseRepository.searchData(searchQuery);
    }

    public LiveData<List<String>> retrieveData() {
        return firebaseRepository.retrieveData();
    }
}