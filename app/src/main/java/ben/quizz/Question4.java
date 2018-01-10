package ben.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Question4 extends AppCompatActivity {
    //Variables
    private int scoreTot = 0;
    private TextView txtMessage = null;
    private Button btnVerifier = null;
    private Button btnSuivant = null;
    private String nom = null;
    private ListView lstCouleurs = null;
    private String[] lesCouleurs = {"Blanc", "Bleu", "Jaune", "Noir", "Rouge", "Vert"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        btnVerifier = (Button)findViewById(R.id.btnVerif);
        btnSuivant = (Button)findViewById(R.id.btnSuivant);
        txtMessage = (TextView)findViewById(R.id.message);
        lstCouleurs = (ListView)findViewById(R.id.listeCouleurs);
        lstCouleurs.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,lesCouleurs));

        Intent i = getIntent();
        nom = i.getStringExtra("NOM");
        scoreTot = i.getIntExtra("score", scoreTot);

        btnVerifier.setOnClickListener(EcouteurBouton);
        btnSuivant.setOnClickListener(EcouteurBouton);
    }
    public View.OnClickListener EcouteurBouton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String message = "";
            String choix = "";
            SparseBooleanArray lesChoix = lstCouleurs.getCheckedItemPositions();
            for (int i=0; i<lesChoix.size();i++) {
                if (lesChoix.valueAt(i))
                    choix += Integer.toString(lesChoix.keyAt(i));
            }
            switch (view.getId()) {
                case R.id.btnVerif:
                    if(choix.equals("235")){
                        message = getString(R.string.debutTQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ4);
                    }
                    else{
                        message = getString(R.string.debutFQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ4);
                    }
                    txtMessage.setText(message);
                    break;
                case R.id.btnSuivant:
                    if(choix.equals("235"))
                        scoreTot +=1;
                    Intent resultats = new Intent(Question4.this, Resultats.class);
                    resultats.putExtra("NOM", nom);
                    resultats.putExtra("score", scoreTot);
                    startActivity(resultats);
                    break;
            }
        }
    };
}
