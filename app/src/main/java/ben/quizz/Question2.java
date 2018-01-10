package ben.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Question2 extends AppCompatActivity {
    // Déclaration variables
    private int scoreTot = 0;
    private Button btnVerifier = null;
    private Button btnSuivant = null;
    private TextView txtMessage = null;
    private String nom = null;
    private Spinner lstCapitales = null;
    private String[] lesCapitales = {"Bogota", "Buenos Aires", "Caracas", "Rio de Janeiro", "Santiago"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        btnVerifier = (Button)findViewById(R.id.btnVerif);
        btnSuivant = (Button)findViewById(R.id.btnSuivant);
        txtMessage = (TextView)findViewById(R.id.message);
        lstCapitales = (Spinner) findViewById(R.id.listeVille);
        lstCapitales.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesCapitales));

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
                    if (lstCapitales.getSelectedItem().toString().equals("Buenos Aires")) {
                        message = getString(R.string.debutTQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ2);
                    } else {
                        message = getString(R.string.debutFQ)+ " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ2);
                    }
                    txtMessage.setText(message);
                    break;
                case R.id.btnSuivant:
                    if (lstCapitales.getSelectedItem().toString().equals("Buenos Aires"))
                        scoreTot += 1;
                    Intent question3 = new Intent(Question2.this, Question3.class);
                    question3.putExtra("NOM", nom);
                    question3.putExtra("score", scoreTot);
                    startActivity(question3);
                    break;
            }
        }
    };
}

