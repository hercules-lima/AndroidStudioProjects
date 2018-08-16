package br.com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
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

import br.com.whatsappandroid.cursoandroid.whatsapp.Manifest;
import br.com.whatsappandroid.cursoandroid.whatsapp.R;
import br.com.whatsappandroid.cursoandroid.whatsapp.helper.Permissao;
import br.com.whatsappandroid.cursoandroid.whatsapp.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codArea;
    private EditText codPais;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[]{
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissao.validaPermissoes(1,this,permissoesNecessarias);

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
                String mensagemEnvio = "Olá Thuani Gravidez! NarizApp Código de Confirmação " + token;

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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        for(int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar esse app é necessário aceitar as permissões!");
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
