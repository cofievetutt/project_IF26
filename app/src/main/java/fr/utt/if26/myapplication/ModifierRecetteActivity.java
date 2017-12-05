package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import fr.utt.if26.myapplication.Class.ClassDb.Categorie;
import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class ModifierRecetteActivity extends AppCompatActivity implements OnClickListener{

    RadioButton rbAttente;
    RadioButton rbValider;
    RadioButton rbDepense;
    RadioButton rbRecette;

    EditText editTextMontant;
    EditText editTextCommentaire;
    Button boutonValider;

    Recette recette;

    Spinner comboboxCat;

    ArrayList<Categorie> listeCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_recette);

        this.recette = RecetteActivity.recetteSelect;

        this.rbAttente = (RadioButton)findViewById(R.id.radioButtonEnAttente);
        this.rbAttente.setOnClickListener(this);

        this.rbValider = (RadioButton)findViewById(R.id.radioButtonValider);
        this.rbValider.setOnClickListener(this);

        this.rbDepense = (RadioButton)findViewById(R.id.radioButtonDepense);
        this.rbDepense.setOnClickListener(this);

        this.rbRecette = (RadioButton)findViewById(R.id.radioButtonRecette);
        this.rbRecette.setOnClickListener(this);

        this.editTextMontant = (EditText)findViewById(R.id.editTextMontant);
        if(recette.getMontant() < 0)
            this.editTextMontant.setText(String.valueOf(-(recette.getMontant())));
        else
            this.editTextMontant.setText(String.valueOf((recette.getMontant())));


        this.editTextCommentaire = (EditText)findViewById(R.id.editTextCommentaire);
        this.editTextCommentaire.setText("");
        this.editTextCommentaire.append(recette.getCommentaire());

        this.comboboxCat = (Spinner) findViewById(R.id.comboboxCat);

        this.listeCategories = new ArrayList<>();
        try {
            listeCategories = Categorie.getAllCategorie();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int position = -1;

        for(int i = 0; i < listeCategories.size(); i++)
        {
            if(recette.getIdCategorie() == listeCategories.get(i).getIdCategorie()) {
                position = i;
                break;
            }
        }

        ArrayAdapter<Categorie> adapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_spinner_item , listeCategories);
        this.comboboxCat.setAdapter(adapter);

        if(position != -1)
            this.comboboxCat.setSelection(position);

        this.boutonValider = (Button)findViewById(R.id.buttonModifierRecette);
        this.boutonValider.setOnClickListener(this);

        if(this.recette.getIdEtat() == 1)
            this.rbAttente.setChecked(true);
        else
            this.rbValider.setChecked(true);

        if(this.recette.getMontant() < 0)
            this.rbDepense.setChecked(true);
        else
            this.rbRecette.setChecked(true);
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
        if(v.equals(this.boutonValider))
        {
            Compte compte = null;
            try {
                compte = Compte.getCompteById(CompteActivity.idCompte);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int positionCat = this.comboboxCat.getSelectedItemPosition();
            Categorie categorie = this.listeCategories.get(positionCat);

            int idEtat = 2;
            if(this.rbAttente.isChecked())
                idEtat = 1;

            double montant = 0;

            if(rbDepense.isChecked())
                montant = - Double.parseDouble(this.editTextMontant.getText().toString());
            else if(rbRecette.isChecked())
                montant = Double.parseDouble(this.editTextMontant.getText().toString());

            String commentaire = this.editTextCommentaire.getText().toString();

            this.recette.updateRecette(compte, montant, idEtat, commentaire, categorie.getIdCategorie());

            Intent intent = new Intent();
            intent.setClass(this, RecetteActivity.class);
            startActivity(intent);
        }

        if(v.equals(rbAttente))
        {
            this.rbAttente.setChecked(true);
            this.rbValider.setChecked(false);
        }

        if(v.equals(rbValider))
        {
            this.rbAttente.setChecked(false);
            this.rbValider.setChecked(true);
        }

        if(v.equals(rbDepense))
        {
            this.rbDepense.setChecked(true);
            this.rbRecette.setChecked(false);
        }

        if(v.equals(rbRecette))
        {
            this.rbDepense.setChecked(false);
            this.rbRecette.setChecked(true);
        }
    }
}
