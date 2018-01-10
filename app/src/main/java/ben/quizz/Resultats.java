package ben.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Resultats extends AppCompatActivity {
    private String nom = null;
    private TextView msgFin = null;
    private TextView msgScore = null;
    private CheckBox chkVoir = null;
    private int scoreTot = 0;
    private ListView lstReponses = null;
    private HashMap<String, String> lesReponses = null;
    private ArrayList<HashMap<String, String>> itemsListe = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);
        Log.i("testdebug", "I'M HERE");
        msgFin = (TextView)findViewById(R.id.libFin);
        msgScore = (TextView)findViewById(R.id.libScore);
        Intent i = getIntent();
        nom = i.getStringExtra("NOM");
        scoreTot = i.getIntExtra("score", scoreTot);

        msgFin.setText(nom.toUpperCase() + getString(R.string.quizzTermine));
        msgScore.setText(getString(R.string.debutScore) + " " + scoreTot + " " + getString(R.string.finScore));

        chkVoir = (CheckBox)findViewById(R.id.voirReponses);
        chkVoir.setOnClickListener(EcouteurChk);
        Log.i("testdebug", "I'M HERE AGAIN");
        lstReponses = (ListView)findViewById(R.id.listReponses);
        lesReponses = (HashMap<String, String>) i.getSerializableExtra("REPONSES");

        Log.i("testdebug", "I'M HERE AGAIN AGAIN");

        for(Map.Entry<String, String> el : lesReponses.entrySet()){
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("Q", el.getKey());
            item.put("R", el.getValue());
            itemsListe.add(item);
        }
        lstReponses.setAdapter(new SimpleAdapter(this, itemsListe, R.layout.layout_ligne_lstreponses, new String[] {"Q", "R"}, new int[] {R.id.question, R.id.reponse}));
    }

    private View.OnClickListener EcouteurChk = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (chkVoir.isChecked())
                lstReponses.setVisibility(View.VISIBLE);
            else
                lstReponses.setVisibility(View.INVISIBLE);
        }
    };
}
