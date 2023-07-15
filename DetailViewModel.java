package com.example.final_project;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    private FirebaseRepository firebaseRepository;

    public DetailViewModel() {
        firebaseRepository = new FirebaseRepository();
    }

    public void deleteItem(String key, String imageUrl) {
        firebaseRepository.deleteItem(key, imageUrl);
    }
}
