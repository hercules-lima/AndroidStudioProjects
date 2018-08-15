package com.cursoandroid.meuprojeto.meuprojeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cursoandroid.meuprojeto.meuprojeto.Usuario;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarioReferencia = databaseReferencia.child("usuarios");
    private DatabaseReference produtoReferencia = databaseReferencia.child("produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //caso não existir cria um nó, se existir sobreescreve
        Produto produto = new Produto();
        produto.setCor("Azul");
        produto.setDescricao("Rato");
        produto.setFabricante("Samsung");

        produtoReferencia.child("001").setValue(produto);
    }
}
