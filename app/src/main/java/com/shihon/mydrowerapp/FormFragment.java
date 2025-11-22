package com.shihon.mydrowerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewTreeViewModelKt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormFragment extends Fragment {
    EditText userName,userPass;
    Button btnGoToMessage,btnSubmit;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view =inflater.inflate(R.layout.fragment_from, container, false);

         userName = view.findViewById(R.id.edUserName);
         userPass = view.findViewById(R.id.edUserPassword);
         btnSubmit = view.findViewById(R.id.btnSub);

         btnSubmit.setOnClickListener(v -> {
             Toast.makeText(getContext(),"Button Clicked from FormFragment",Toast.LENGTH_LONG).show();

         });

         return  view;
    }
}