 package com.example.jogoanagrama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 public class MainActivity extends AppCompatActivity {

    ListagemFrag fragListagem;
    TentativaFrag fragTentativa;
    AnagramaFrag anagramaFrag;
    ArrayList<String> historico;
    ArrayList<String> palavras = new ArrayList<String>(Arrays.asList (new String[]{"PALAVRA", "TESTE", "TECNOLOGIA", "JAVA"}));
    String palavraCorreta;
    int numeroAleatorio;
    int contadorAcertos = 0;
    int contadorErros = 0;
    Handler hdl;
    ContadorTempo contadorTempo;
    ExecutorService exc = Executors.newSingleThreadExecutor();

     public static String embaralhaPalavra(String s) {
         List<String> letters = Arrays.asList(s.split(""));
         Collections.shuffle(letters);
         StringBuilder t = new StringBuilder(s.length());
         for (String k : letters) {
             t.append(k);
         }
         return t.toString();
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragListagem = (ListagemFrag) getFragmentManager().findFragmentByTag("fragListagem");
        fragTentativa = (TentativaFrag) getFragmentManager().findFragmentByTag("fragTentativa");
        anagramaFrag = ((AnagramaFrag) getFragmentManager().findFragmentByTag("fragAnagrama"));
        if(historico == null){
            historico = new ArrayList<String>();
        }
        geraPalavraNovaAnagrama();
        setaContadoresCorretamente();
        hdl = new Handler( Looper.getMainLooper() );
        iniciarContador();
    }


    public void iniciarContador(){
        try {
        contadorTempo = new ContadorTempo(5);
        exc.execute(contadorTempo);
        } catch (Exception e) {
            Toast.makeText(this, "Deu ruim.",
                    Toast.LENGTH_SHORT).show();
        }
    }

     class ContadorTempo implements Runnable {
         int tempoTotal;

         boolean reiniciar = false;

         public ContadorTempo(int tempo) {
             tempoTotal = tempo;
         }

         public void reiniciar() {
             reiniciar = true;
         }

         public void run() {
             try {
                 for (int i = 1; i < tempoTotal + 2; i++) {
                     if ( reiniciar ) { i = 1; reiniciar = false;}
                     Thread.sleep(1000);
                     final int tempoAtual = i - 1;
                     hdl.post( new Runnable() {
                         public void run() {
                             anagramaFrag.tempoContador.setText( String.valueOf(tempoAtual) );
                         }
                     });
                 }
                 reiniciar = true;
                 contadorTempo = new ContadorTempo(5);
                 exc.execute(contadorTempo);
                 incrementarErro();
             } catch(Throwable t) {
                 t.printStackTrace();
             }
         }
     }


    public void tentar(View v){
        if(palavraCorreta.equalsIgnoreCase(retornaStringTentativa())){
            contadorTempo.reiniciar();
            fragListagem.addHistorico(retornaStringTentativa().toUpperCase());
            geraPalavraNovaAnagrama();
            incrementarAcerto();
        } else {
            contadorTempo.reiniciar();
            incrementarErro();
        }
    }

    public String retornaStringTentativa(){
        return fragTentativa.edTentativa.getText().toString();
    }

    public void geraPalavraNovaAnagrama(){
         geraNumeroAleatorio();
        anagramaFrag.anagrama.setText(embaralhaPalavra(palavras.get(numeroAleatorio)));
        palavraCorreta = palavras.get(numeroAleatorio);
    }

     public void geraNumeroAleatorio(){
         int min = 0;
         int max = palavras.size();
         numeroAleatorio = (int) ((Math.random() * (max - min)) + min);
     }

     public void setaContadoresCorretamente(){
        fragListagem.contadorAcertos.setText("Acertos: " + contadorAcertos);
        fragListagem.contadorErros.setText("Erros: " + contadorErros);
     }

     public void incrementarAcerto(){
         contadorAcertos += 1;
         fragListagem.contadorAcertos.setText("Acertos: " + contadorAcertos);
     }

     public void incrementarErro(){
         contadorErros += 1;
         fragListagem.contadorErros.setText("Erros: " + contadorErros);
     }
}
