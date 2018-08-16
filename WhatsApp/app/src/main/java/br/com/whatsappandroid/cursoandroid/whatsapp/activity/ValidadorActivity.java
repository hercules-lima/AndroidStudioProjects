package br.com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import br.com.whatsappandroid.cursoandroid.whatsapp.R;
import br.com.whatsappandroid.cursoandroid.whatsapp.helper.Preferencias;

public class ValidadorActivity extends AppCompatActivity {

    private EditText codigoValidacao;
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        codigoValidacao = (EditText) findViewById(R.id.edit_cod_validacao);
        validar = (Button) findViewById(R.id.bt_validar);

        //criando mascara
        SimpleMaskFormatter simpleMaskCodigoValidacao = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskCodigoValidacao = new MaskTextWatcher(codigoValidacao, simpleMaskCodigoValidacao);
        codigoValidacao.addTextChangedListener(maskCodigoValidacao);

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperar dados das preferencias do usuario
                Preferencias preferencias = new Preferencias(ValidadorActivity.this);
                HashMap<String,String> usuario = preferencias.getDadosUsuario();

                String tokenGerado = usuario.get("token");
                String tokenDigitado = codigoValidacao.getText().toString();

                if(tokenDigitado.equals(tokenGerado)){
                    Toast.makeText(ValidadorActivity.this,"TOKEN VALIDADO COM SUCESSO!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ValidadorActivity.this,"FALHA NA VALIDAÇÃO DO TOKEN!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
