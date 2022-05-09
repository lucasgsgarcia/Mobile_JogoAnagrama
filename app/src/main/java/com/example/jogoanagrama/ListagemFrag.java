package com.example.jogoanagrama;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListagemFrag extends Fragment {
    
    ListView listaAcertos;
    private ArrayList<String> acertos;
    ArrayAdapter adapter;
    TextView contadorAcertos;
    TextView contadorErros;


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
        contadorAcertos = ((TextView) v.findViewById(R.id.tvContadorAcertos));
        contadorErros = ((TextView) v.findViewById(R.id.tvContadorErros));
        if (acertos == null){
            acertos = new ArrayList<String>();
        }
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, this.acertos);
        listaAcertos.setAdapter(adapter);
        return v;
    }

    public void addHistorico(String palavra){
        acertos.add(palavra);
        adapter.notifyDataSetChanged();
    }
}