package frasedodia.studio.com.frasedodia;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button botaoNovaFrase;
    private TextView textoNovaFrase;

    private String[] frases = {"Sua luta não termina quando sentir cansaço, mas sim quando atingir o sucesso tão merecido.",
            "Encare o que fez de errado com motivação, pois é isso que o ajudará a fazer certo da próxima vez.",
            "A motivação não acontece por acaso, como tudo na vida você tem de batalhar para a alcançar."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(frases.length);
                textoNovaFrase.setText(frases[numeroAleatorio]);
            }
        });


    }
}
