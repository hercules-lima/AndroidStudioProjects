package br.com.whatsappandroid.cursoandroid.whatsapp.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import br.com.whatsappandroid.cursoandroid.whatsapp.R;

public final class ConfiguracaoFirebase extends AppCompatActivity {

    private static DatabaseReference referenciaFirebase;

    public static DatabaseReference getFirebase(){
        if(referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
}
