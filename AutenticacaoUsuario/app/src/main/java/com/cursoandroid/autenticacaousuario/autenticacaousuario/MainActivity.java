package com.cursoandroid.autenticacaousuario.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword("hercules@gmail.com","jamilton123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //cadastrado com sucesso
                            Log.i("signIn","Sucesso ao fazer login do Usuário!");
                        }else{
                            //erro ao cadastrar
                            Log.i("signIn","Erro ao fazer login do Usuário!");
                        }
                    }
                });


        //cadastro de usuario
        firebaseAuth.createUserWithEmailAndPassword("hercules@gmail.com","jamilton123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //cadastrado com sucesso
                            Log.i("createUser","Sucesso ao cadastrar Usuário!");
                        }else{
                            //erro ao cadastrar
                            Log.i("createUser","Falha ao cadastrar Usuário!");
                        }
                    }
                });


    }
}
