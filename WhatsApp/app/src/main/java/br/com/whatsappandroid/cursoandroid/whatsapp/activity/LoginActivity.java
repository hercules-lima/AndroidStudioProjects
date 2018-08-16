package br.com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

import br.com.whatsappandroid.cursoandroid.whatsapp.R;
import br.com.whatsappandroid.cursoandroid.whatsapp.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codArea;
    private EditText codPais;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = (EditText) findViewById(R.id.edit_nome);
        telefone = (EditText) findViewById(R.id.edit_telefone);
        codArea = (EditText) findViewById(R.id.edit_cod_area);
        codPais = (EditText) findViewById(R.id.edit_cod_pais);
        cadastrar = (Button) findViewById(R.id.bt_cadastrar);

        //definir as mascaras
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        MaskTextWatcher maskCodePais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodeArea = new MaskTextWatcher(codArea, simpleMaskCodArea);

        telefone.addTextChangedListener(maskTelefone);
        codPais.addTextChangedListener(maskCodePais);
        codArea.addTextChangedListener(maskCodeArea);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() +
                        codArea.getText().toString() +
                        telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");

                //gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(9999 - 1000) + 1000;
                String token = String.valueOf(numeroRandomico);
                String mensagemEnvio = "WhatsApp Código de Confirmação" + token;

                //salvar os dados para validação
                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                //Envio de SMS
                boolean enviadoSMS = enviaSMS("+" + telefoneSemFormatacao, mensagemEnvio);


                //HashMap<String,String> usuario = preferencias.getDadosUsuario();
                //Log.i("NOME","N:" + usuario.get("nome"));


            }
        });
}
    private boolean enviaSMS(String telefone, String mensagem) {
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
