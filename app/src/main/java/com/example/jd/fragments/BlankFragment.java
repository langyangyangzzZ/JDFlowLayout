package com.example.jd.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jd.R;
import com.example.jd.adapter.RelTemplate;


public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rel = view.findViewById(R.id.rel);

        rel.setLayoutManager(new LinearLayoutManager(getActivity()));


        RelTemplate relTemplate = new RelTemplate();

        relTemplate.template(getContext(), rel);


        return view;
    }
}