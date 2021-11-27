package com.chat.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chat.messenger.R;
import com.chat.messenger.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setListeners() {
        binding.textSignIn.setOnClickListener(view -> onBackPressed());
    }
}