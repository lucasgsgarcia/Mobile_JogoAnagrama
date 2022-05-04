package com.example.jogoanagrama;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListagemFrag extends Fragment {
    
    ListView listaAcertos;
    private ArrayList<String> acertos;
    ArrayAdapter adapter;


    public ListagemFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listagem, container, false);
        listaAcertos = ((ListView) v.findViewById(R.id.listaAcertos));
        return v;
    }

    public void setPalavras(ArrayList<String> acertos) {
        this.acertos = acertos;
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, this.acertos);
        listaAcertos.setAdapter(adapter);
    }

    public void addHistorico(String palavra){
        acertos.add(palavra);
        adapter.notifyDataSetChanged();
    }
}