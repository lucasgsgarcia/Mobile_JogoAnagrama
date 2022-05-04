package com.example.jogoanagrama;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TentativaFrag extends Fragment {

    EditText edTentativa;

    public TentativaFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tentativa, container, false);
        edTentativa = ((EditText) v.findViewById(R.id.edTentativa));
        return v;
    }
}