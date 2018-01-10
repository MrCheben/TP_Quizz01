package ben.quizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Question1 extends AppCompatActivity {
    // Variables
    private RadioGroup grpReponse = null;
    private TextView txtMessage = null;
    private Button btnVerifier = null;
    private Button btnSuivant = null;
    private String nom = null;
    private int scoreTot = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        grpReponse = (RadioGroup)findViewById(R.id.grpReponse);
        txtMessage = (TextView)findViewById(R.id.message);
        btnVerifier = (Button)findViewById(R.id.btnVerif);
        btnSuivant = (Button)findViewById(R.id.btnSuivant);
        //R.string.app_name = "Question 1";
        // Récupération intent qui a lancé l'activité
        Intent i = getIntent();
        // Récupération nom saisi dans l'autre activité
        nom = i.getStringExtra("NOM");

        btnVerifier.setOnClickListener(EcouteurBouton);
        btnSuivant.setOnClickListener(EcouteurBouton);
    }

    public View.OnClickListener EcouteurBouton = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String message = "";
            // Récupération du bouton qui a été cliqué
            switch (view.getId()){
                case R.id.btnVerif :
                    if(grpReponse.getCheckedRadioButtonId() == R.id.rdbFaux){
                        message = getString(R.string.debutTQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ1);
                    }
                    else{
                        message = getString(R.string.debutFQ) + " " + nom.toUpperCase() + " !\n" + getString(R.string.finQ1);
                    }
                    txtMessage.setText(message);
                    break;
                case R.id.btnSuivant :
                    if(grpReponse.getCheckedRadioButtonId() == R.id.rdbFaux || grpReponse.getCheckedRadioButtonId() == R.id.rdbVrai ){
                        if (grpReponse.getCheckedRadioButtonId() == R.id.rdbFaux)
                            scoreTot += 1;
                        Intent question2 = new Intent(Question1.this, Question2.class);
                        question2.putExtra("NOM", nom);
                        question2.putExtra("score", scoreTot);
                        startActivity(question2);
                        break;
                    }
                    else{
                        AlertDialog.Builder boite = new AlertDialog.Builder(Question1.this);
                        boite.setTitle(getString(R.string.infoMess));
                        boite.setMessage(getString(R.string.erreurSelect));
                        boite.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        boite.show();
                    }
            }
        }
    };
}
