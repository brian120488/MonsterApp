package com.example.monsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {

    public TextView pokemonName;
    public ProgressBar progressBar;
    public ImageView pokemonImage;
    public TextView pokemonLevel;
    public ImageView rareCandy;
    public int currentPokemon = 0;
    int maxLevel = 99;
    public Pokemon[] pokemonArray = {
            new Pokemon(new String[]{"Bulbasaur", "Ivysaur", "Venusaur"}, new int[]{16,32},new int[]{R.drawable.bulbasaur, R.drawable.ivysaur, R.drawable.venusaur}, R.drawable.venusaurite, R.drawable.megavenusaur),
            new Pokemon(new String[]{"Charmander", "Charmeleon", "Charizard"}, new int[]{16,36},new int[]{R.drawable.charmander, R.drawable.charmeleon, R.drawable.charizard}, R.drawable.charizarditey, R.drawable.megacharizardy),
            new Pokemon(new String[]{"Squirtle", "Wartortle", "Blastoise"}, new int[]{16,36},new int[]{R.drawable.squirtle, R.drawable.wartortle, R.drawable.blastoise}, R.drawable.blastoisinite, R.drawable.megablastoise)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonName = findViewById(R.id.pokemonName);
        progressBar = findViewById(R.id.progressBar);
        pokemonImage = findViewById(R.id.pokemonImage);
        pokemonLevel = findViewById(R.id.pokemonLevel);
        rareCandy = findViewById(R.id.rareCandy);

        newPokemon();

        new CountDownTimer(100000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Pokemon pokemon = pokemonArray[currentPokemon];
                if(progressBar.getProgress() >= progressBar.getMax() && pokemon.level != maxLevel) {
                    pokemon.level++;
                    progressBar.setProgress(0);
                    progressBar.setMax(pokemon.level*10);
                    pokemonLevel.setText("Level: " + pokemon.level);
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() + progressBar.getMax()/10);
                }
                if(pokemon.isEvolving()) {
                    pokemon.currentImage++;
                    pokemonImage.setImageResource(pokemon.pokemonImages[pokemon.currentImage]);
                    pokemonName.setText(pokemon.names[pokemon.currentImage]);
                }
                if(pokemon.level == maxLevel) {
                    progressBar.setProgress(progressBar.getMax());
                    progressBar.getProgressDrawable().setColorFilter(
                            rgb(255,215,0), android.graphics.PorterDuff.Mode.SRC_IN);

                    rareCandy.setImageResource(pokemon.megaItem);
                    pokemon.isMax = true;
                }
            }

            @Override
            public void onFinish() {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }.start();
    }

    public void click(View view) {
        progressBar.setProgress(progressBar.getProgress() + progressBar.getMax()/5);
    }

    public void feed(View view) {
        Pokemon pokemon = pokemonArray[currentPokemon];
        if(pokemon.isMax) {
            pokemonImage.setImageResource(pokemon.megaImage);
            pokemon.isMega = true;
            if(!(pokemonName.getText().toString().contains("Mega"))) {
                pokemonName.setText("Mega " + pokemonName.getText());
            }
        }
        if(pokemon.level != maxLevel) {
            pokemon.level++;
            progressBar.setProgress(0);
            progressBar.setMax(pokemon.level*10);
            pokemonLevel.setText("Level: " + pokemon.level);
        }
        if(pokemon.isEvolving()) {
            pokemon.currentImage++;
            pokemonImage.setImageResource(pokemon.pokemonImages[pokemon.currentImage]);
            pokemonName.setText(pokemon.names[pokemon.currentImage]);
        }
        if(pokemon.level == maxLevel) {
            progressBar.setProgress(progressBar.getMax());
            progressBar.getProgressDrawable().setColorFilter(
                    rgb(255,215,0), android.graphics.PorterDuff.Mode.SRC_IN);

            rareCandy.setImageResource(pokemon.megaItem);
            pokemon.isMax = true;
        }
    }

    public void newPokemon() {
        Pokemon pokemon = pokemonArray[currentPokemon];
        pokemonName.setText(pokemon.names[pokemon.currentImage]);
        pokemonImage.setImageResource(pokemon.pokemonImages[pokemon.currentImage]);
        pokemonLevel.setText("Level: " + pokemon.level);
        progressBar.setProgress(0);
        progressBar.setMax(pokemon.level * 10);
        if(pokemon.level != maxLevel) {
            progressBar.getProgressDrawable().setColorFilter(
                    Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
            rareCandy.setImageResource(R.drawable.rare_candy);
        }
        else {
            progressBar.setProgress(progressBar.getMax());
            progressBar.getProgressDrawable().setColorFilter(
                    rgb(255,215,0), android.graphics.PorterDuff.Mode.SRC_IN);
            rareCandy.setImageResource(pokemon.megaItem);
            if(pokemon.isMega) {
                pokemonImage.setImageResource(pokemon.megaImage);
            }
            else {
                pokemonImage.setImageResource(pokemon.pokemonImages[pokemon.currentImage]);
            }
            pokemonName.setText("Mega " + pokemonName.getText());
        }
    }

    //previous pokemon
    public void moveLeft(View view) {
        if(currentPokemon != 0) {
            currentPokemon--;
        }
        else {
            currentPokemon = pokemonArray.length-1;
        }
        newPokemon();
    }

    //next pokemon
    public void moveRight(View view) {
        if(currentPokemon != pokemonArray.length-1) {
            currentPokemon++;
        }
        else {
            currentPokemon = 0;
        }
        newPokemon();
    }
}
