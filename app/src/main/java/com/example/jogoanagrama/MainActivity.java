 package com.example.jogoanagrama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {

    ListagemFrag fragListagem;
    TentativaFrag fragTentativa;
    AnagramaFrag anagramaFrag;
    ArrayList<String> historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragListagem = (ListagemFrag) getFragmentManager().findFragmentByTag("fragLista");
        fragTentativa = (TentativaFrag) getFragmentManager().findFragmentByTag("fragTentativa");
        anagramaFrag = ((AnagramaFrag) getFragmentManager().findFragmentByTag("fragAnagrama"));
        if(historico == null){
            historico = new ArrayList<String>();
        }

    }

    public void tentar(View v){
        if(anagramaFrag.anagrama.getText().toString().equalsIgnoreCase(retornaStringTentativa())){
            fragListagem.addHistorico(retornaStringTentativa());
        }
    }

    public String retornaStringTentativa(){
        return fragTentativa.edTentativa.getText().toString();
    }
}