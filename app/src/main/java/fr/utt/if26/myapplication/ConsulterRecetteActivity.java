package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;

import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class ConsulterRecetteActivity extends AppCompatActivity implements OnClickListener{

    TextView textViewMontant;
    TextView textViewCommentaire;
    TextView textViewEtat;

    Button buttonModifier;
    Button buttonSupprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_recette);

        this.textViewMontant = (TextView) findViewById(R.id.textViewMontant);
        this.textViewMontant.setOnClickListener(this);

        this.textViewCommentaire = (TextView) findViewById(R.id.textViewCommantaire);
        this.textViewCommentaire.setOnClickListener(this);

        this.textViewEtat = (TextView) findViewById(R.id.textViewEtat);
        this.textViewEtat.setOnClickListener(this);

        this.buttonModifier = (Button) findViewById(R.id.buttonModifier);
        this.buttonModifier.setOnClickListener(this);

        this.buttonSupprimer = (Button) findViewById(R.id.buttonSupprimer);
        this.buttonSupprimer.setOnClickListener(this);

        Recette recette = RecetteActivity.recetteSelect;
        String etat = "En Attente";
        if(recette.getIdEtat() == 2)
            etat = "Valider";

        this.textViewMontant.setText("Montant : " + recette.getMontant());
        this.textViewCommentaire.setText("Commentaire : " + recette.getCommentaire());
        this.textViewEtat.setText(etat);
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
        if(v.equals(this.buttonModifier))
        {
            Intent intent = new Intent();
            intent.setClass(this, ModifierRecetteActivity.class);
            startActivity(intent);
        }

        if(v.equals(this.buttonSupprimer))
        {
            try {
                Recette.deleteRecette(RecetteActivity.recetteSelect.getIdRecette());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent();
            intent.setClass(this, AccueilActivity.class);
            startActivity(intent);
        }
    }
}
