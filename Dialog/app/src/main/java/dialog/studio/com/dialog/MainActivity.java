package dialog.studio.com.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criar alert dialog
                dialog = new AlertDialog.Builder(MainActivity.this);
                //configurar o titulo
                dialog.setTitle("Pesquisa");
                //configurar a mensagem
                dialog.setMessage("Você gosta de Java?");

                dialog.setCancelable(false);
                dialog.setIcon(android.R.drawable.stat_notify_call_mute);

                //botao negativo
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Java é ruim!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Java é muito ruim!",Toast
                        .LENGTH_SHORT).show();
                    }
                });

                dialog.create();
                dialog.show();

            }
        });
    }
}
