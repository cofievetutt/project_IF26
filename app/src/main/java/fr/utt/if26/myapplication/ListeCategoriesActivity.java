package fr.utt.if26.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.ArrayList;

import fr.utt.if26.myapplication.Class.ClassDb.Categorie;
import fr.utt.if26.myapplication.Class.ClassDb.Recette;

public class ListeCategoriesActivity extends AppCompatActivity implements OnClickListener {

    ListView listeView;
    Spinner comboboxCat;
    Button button;

    ArrayList<Categorie> listeCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_categories);

        this.button = (Button) findViewById(R.id.button2);
        this.listeView = (ListView) findViewById(R.id.listViewCat);
        this.comboboxCat = (Spinner) findViewById(R.id.spinner2);

        this.listeCategories = new ArrayList<>();
        try {
            listeCategories = Categorie.getAllCategorie();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Categorie> adapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_spinner_item , listeCategories);
        this.comboboxCat.setAdapter(adapter);

        this.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(this.button)) {
            int positionCat = this.comboboxCat.getSelectedItemPosition();
            Categorie categorie = this.listeCategories.get(positionCat);

            try {
                ArrayList<Recette> liste = Recette.getRecetteByCat(categorie.getIdCategorie());
                ArrayAdapter<Recette> adapter = new ArrayAdapter<Recette>(this, android.R.layout.simple_spinner_item , liste);
                this.listeView.setAdapter(adapter);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }
}
