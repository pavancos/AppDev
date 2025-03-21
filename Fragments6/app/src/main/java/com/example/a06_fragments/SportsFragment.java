package com.example.a06_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SportsFragment extends Fragment implements AdapterView.OnItemClickListener {

    public SportsFragment() {
        // Required empty public constructor
    }

    ListView lv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView)view.findViewById(R.id.lvsports);

        // Get the sports list from resources
        String[] sportsList = getResources().getStringArray(R.array.sports_list);

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sportsList);

        // Set the adapter to the ListView
        lv.setAdapter(adapter);

        // Set the OnItemClickListener
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle b = new Bundle();
        b.putInt("si",i);
        getParentFragmentManager().setFragmentResult("si",b);
    }
}