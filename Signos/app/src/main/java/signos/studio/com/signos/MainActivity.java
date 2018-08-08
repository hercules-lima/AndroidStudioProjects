package signos.studio.com.signos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaSignos;
    private String[] signos = {"Áries","Touro","Gêmeos","Câncer","Leão","Virgem",
            "Libra","Escorpião","Sagitário","Capricórnio","Aquário","Peixes"};

    private String[] perfis = {"De 21 março a 19 abril","De 20 abril a 20 maio","De 21 maio a 21 junho","De 22 junho a 22 julho",
            "De 23 julho a 22 agosto","De 23 agosto a 22 setembro",
            "De 23 setembro a 22 outubro","De 23 outubro a 21 novembro","De 22 novembro a 21 dezembro",
            "De 22 dezembro a 19 janeiro","De 20 janeiro a 18 fevereiro","De 19 fevereiro a 20 março"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = (ListView) findViewById(R.id.listViewId);
        ArrayAdapter<String> adapt= new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        listaSignos.setAdapter(adapt);
        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                Toast.makeText(getApplicationContext(),perfis[codigoPosicao],Toast.LENGTH_SHORT).show();
            }
        });
    }
}

