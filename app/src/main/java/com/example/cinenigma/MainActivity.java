package com.example.cinenigma;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText titreFilm, dateProjection, heureProjection, noteScenario, noteRealisation, noteMusique, critique;
    Button btnEnregistrer, btnEnvoyerEmail, btnVoirDonnees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de la base de données locale et des vues
        myDb = new DatabaseHelper(this);

        titreFilm = findViewById(R.id.titreFilm);
        dateProjection = findViewById(R.id.dateProjection);
        heureProjection = findViewById(R.id.heureProjection);
        noteScenario = findViewById(R.id.noteScenario);
        noteRealisation = findViewById(R.id.noteRealisation);
        noteMusique = findViewById(R.id.noteMusique);
        critique = findViewById(R.id.critique);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        btnEnvoyerEmail = findViewById(R.id.btnEnvoyerEmail);
        btnVoirDonnees = findViewById(R.id.btnVoirDonnees);

        // Configuration du bouton Enregistrer
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insertion dans la base de données SQLite locale
                boolean isInserted = myDb.insertData(
                        titreFilm.getText().toString(),
                        dateProjection.getText().toString(),
                        heureProjection.getText().toString(),
                        Double.parseDouble(noteScenario.getText().toString()),
                        Double.parseDouble(noteRealisation.getText().toString()),
                        Double.parseDouble(noteMusique.getText().toString()),
                        critique.getText().toString());

                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Données enregistrées", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Échec de l'enregistrement", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEnvoyerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Critique du film: " + titreFilm.getText().toString();
                String message = "Date de projection: " + dateProjection.getText().toString() + "\n" +
                        "Heure de projection: " + heureProjection.getText().toString() + "\n" +
                        "Note Scénario: " + noteScenario.getText().toString() + "\n" +
                        "Note Réalisation: " + noteRealisation.getText().toString() + "\n" +
                        "Note Musique: " + noteMusique.getText().toString() + "\n" +
                        "Critique: " + critique.getText().toString();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(emailIntent, "Envoyer l'email..."));
            }
        });

        btnVoirDonnees = findViewById(R.id.btnVoirDonnees);

        btnVoirDonnees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataDisplayActivity.class);
                startActivity(intent);
            }
        });

    }
}
