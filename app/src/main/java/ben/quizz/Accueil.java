package ben.quizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Accueil extends AppCompatActivity {

    private EditText editNom = null;
    private Button btnOK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        editNom = (EditText)findViewById(R.id.editNom);
        btnOK = (Button)findViewById(R.id.boutonOK);

        btnOK.setOnClickListener(EcouteurBouton);
    }

    public View.OnClickListener EcouteurBouton = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            if (editNom.getText().length()>0) {
                Intent question1 = new Intent(Accueil.this, Question1.class);
                question1.putExtra("NOM", editNom.getText().toString());
                startActivity(question1);
            }
            else{
                AlertDialog.Builder boite = new AlertDialog.Builder(Accueil.this);
                boite.setTitle(getString(R.string.infoMess));
                boite.setMessage(getString(R.string.erreurNomMess));
                boite.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        editNom.requestFocus();
                    }
                });
                boite.show();
            }

        }
    };
}