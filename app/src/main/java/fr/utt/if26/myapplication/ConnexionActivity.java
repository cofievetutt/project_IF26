package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.ClassDb.Utilisateur;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class ConnexionActivity extends AppCompatActivity implements OnClickListener{

    Button button;
    public static Utilisateur utilisateurConnecte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        this.button = findViewById(R.id.buttonConnexion);
        this.button.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UtilMenu.createMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        UtilMenu.selectOptionMenu(item, this, this);
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(this.button))
        {
            EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            EditText editTextPass = (EditText) findViewById(R.id.editTextMotDePasse);
            String email = editTextEmail.getText().toString();
            String pass = editTextPass.getText().toString();

            ConnexionActivity.utilisateurConnecte = Utilisateur.getUtilisateurByIdentifiantAndMotDePasee(email, pass, DataBaseHelper.db);

            if (utilisateurConnecte != null) {
                Toast.makeText(getApplicationContext(), "Bonjour " + utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(ConnexionActivity.this, AccueilActivity.class);
                startActivity(intent);
            } else {

                ConnexionActivity.utilisateurConnecte = Utilisateur.getUtilisateurByIdentifiantAndMotDePasee(email, pass, DataBaseHelper.db);

                if(utilisateurConnecte != null)
                {
                    Toast.makeText(getApplicationContext(), "Bonjour " + utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setClass(ConnexionActivity.this, AccueilActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Email/identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                }
           }
        }
    }
}
