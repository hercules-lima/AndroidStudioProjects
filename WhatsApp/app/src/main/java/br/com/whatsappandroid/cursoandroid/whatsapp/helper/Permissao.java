package br.com.whatsappandroid.cursoandroid.whatsapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.whatsappandroid.cursoandroid.whatsapp.R;

public class Permissao extends AppCompatActivity {

    public static boolean validaPermissoes(int requestCode ,Activity activity , String[] permissoes ){

        if(Build.VERSION.SDK_INT >= 23 ){

            List<String> listaPermissoes = new ArrayList<String>();

            //percorre as posições passadas verifica uma a uma se ja tem permissao liberada
            for(String permissao : permissoes){
                boolean validaPermissao = ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao) listaPermissoes.add(permissao);
            }

            //caso a lista esteja vazia não é necessario solicitar permissao
            if(listaPermissoes.isEmpty()) return true;

            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            //solicitar permissao
            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }

        return true;

    }
}
