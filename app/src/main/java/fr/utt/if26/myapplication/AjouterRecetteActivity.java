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
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class AjouterRecetteActivity extends AppCompatActivity implements OnClickListener{

    Button ButtonAjouterRecette;
    EditText EditTextMontant;
    EditText EditTextCommentaire;

    RadioButton rbDepense;
    RadioButton rbRecette;

    RadioButton rbEnAttente;
    RadioButton rbValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_recette);

        this.ButtonAjouterRecette = (Button) findViewById(R.id.buttonAjouterRecette);
        this.ButtonAjouterRecette.setOnClickListener(this);

        this.EditTextMontant = (EditText) findViewById(R.id.editTextMontant);
        this.EditTextMontant.setOnClickListener(this);

        this.EditTextCommentaire = (EditText) findViewById(R.id.editTextCommentaire);
        this.EditTextCommentaire.setOnClickListener(this);

        this.rbDepense = (RadioButton) findViewById(R.id.radioButtonDepense);
        this.rbDepense.setOnClickListener(this);

        this.rbRecette = (RadioButton) findViewById(R.id.radioButtonRecette);
        this.rbRecette.setOnClickListener(this);

        this.rbEnAttente = (RadioButton) findViewById(R.id.radioButtonEnAttente);
        this.rbEnAttente.setOnClickListener(this);

        this.rbValider = (RadioButton) findViewById(R.id.radioButtonValider);
        this.rbValider.setOnClickListener(this);
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
        if (v.equals(this.ButtonAjouterRecette))
        {
            double montant = Double.parseDouble(this.EditTextMontant.getText().toString());
            String commentaire = this.EditTextCommentaire.getText().toString();

            if(this.rbDepense.isChecked())
            {
                montant = - montant;
            }

            try {
                Compte compte = Compte.getCompteById(CompteActivity.idCompte);
                compte.updateCapitalCompte(montant);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Recette recette = new Recette(new Date(), montant, CompteActivity.idCompte, 1, commentaire);
            recette.insertNouvelRecette(DataBaseHelper.db);

            Toast.makeText(getApplicationContext(), "La recette a bien été ajouté", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(this, RecetteActivity.class);
            startActivity(intent);
        }

        if (v.equals(this.rbDepense)) {
            this.rbDepense.setChecked(true);
            this.rbRecette.setChecked(false);
        }

        if (v.equals(this.rbRecette)) {
            this.rbRecette.setChecked(true);
            this.rbDepense.setChecked(false);
        }

        if (v.equals(this.rbEnAttente)) {
            this.rbEnAttente.setChecked(true);
            this.rbValider.setChecked(false);
        }

        if (v.equals(this.rbValider)) {
            this.rbEnAttente.setChecked(true);
            this.rbValider.setChecked(false);
        }
    }
}
