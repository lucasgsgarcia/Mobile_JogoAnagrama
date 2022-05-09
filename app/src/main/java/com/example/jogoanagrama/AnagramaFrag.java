package com.example.jogoanagrama;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AnagramaFrag extends Fragment {

    TextView anagrama;
    TextView tempoContador;


    public AnagramaFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anagrama, container, false);
        anagrama = ((TextView) v.findViewById(R.id.txPalavra));
        tempoContador = ((TextView) v.findViewById(R.id.tempoContador));
        return v;
    }

}