package br.com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.com.whatsappandroid.cursoandroid.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codArea;
    private EditText codPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = (EditText) findViewById(R.id.edit_nome);
        telefone = (EditText) findViewById(R.id.edit_telefone);
        codArea = (EditText) findViewById(R.id.edit_cod_area);
        codPais = (EditText) findViewById(R.id.edit_cod_pais);

        //definir as mascaras
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone,simpleMaskTelefone);
        MaskTextWatcher maskCodePais = new MaskTextWatcher(codPais,simpleMaskCodPais);
        MaskTextWatcher maskCodeArea = new MaskTextWatcher(codArea,simpleMaskCodArea);

        telefone.addTextChangedListener(maskTelefone);
        codPais.addTextChangedListener(maskCodePais);
        codArea.addTextChangedListener(maskCodeArea);

    }
}
