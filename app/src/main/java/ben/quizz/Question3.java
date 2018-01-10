package ben.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Question3 extends AppCompatActivity {
    // Variables
    private int scoreTot = 0;
    private TextView txtMessage = null;
    private Button btnVerifier = null;
    private Button btnSuivant = null;
    private String nom = null;
    private ListView lstContinents = null;
    private String[] lesContinents = {"Afrique", "Amérique", "Asie", "Europe", "Océanie"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        btnVerifier = (Button)findViewById(R.id.btnVerif);
        btnSuivant = (Button)findViewById(R.id.btnSuivant);
        txtMessage = (TextView)findViewById(R.id.message);
        lstContinents = (ListView)findViewById(R.id.listeContinent);
        lstContinents.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, lesContinents));

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
            switch (view.getId()) {
                case R.id.btnVerif:
                    if (lesContinents[lstContinents.getCheckedItemPosition()].equals("Amérique")) {
                        message = getString(R.string.debutTQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ3);
                    } else {
                        message = getString(R.string.debutFQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ3);
                    }
                    txtMessage.setText(message);
                    break;
                case R.id.btnSuivant:
                    if (lesContinents[lstContinents.getCheckedItemPosition()].equals("Amérique"))
                        scoreTot += 1;
                    Intent question4 = new Intent(Question3.this, Question4.class);
                    question4.putExtra("NOM", nom);
                    question4.putExtra("score", scoreTot);
                    startActivity(question4);
                    break;
            }
        }
    };
}
