package com.example.android.musicalstructure;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ShopActivity extends AppCompatActivity {
    private Button search = null;
    private TextView play = null;
    private TextView addCart = null;
    private TextView play1 = null;
    private TextView addCart1 = null;
    private TextView play2 = null;
    private TextView addCart2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        //Add the icon on the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //Set the Awesome Font
        Typeface fontAW = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        search = (Button) findViewById(R.id.search);
        search.setTypeface(fontAW);
        play = (TextView) findViewById(R.id.play);
        play.setTypeface(fontAW);
        addCart = (TextView) findViewById(R.id.addcart);
        addCart.setTypeface(fontAW);
        play1 = (TextView) findViewById(R.id.play1);
        play1.setTypeface(fontAW);
        addCart1 = (TextView) findViewById(R.id.addcart1);
        addCart1.setTypeface(fontAW);
        play2 = (TextView) findViewById(R.id.play2);
        play2.setTypeface(fontAW);
        addCart2 = (TextView) findViewById(R.id.addcart2);
        addCart2.setTypeface(fontAW);
        /**Here below there are the activities which are launched from linearLayout4 that is the first tab
         * and linearLayout1 is the second tab
         */
        LinearLayout foldersActivity = (LinearLayout) findViewById(R.id.linearLayout1);
        // Set a click listener on that View
        foldersActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, FoldersActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LinearLayout playlistActivity = (LinearLayout) findViewById(R.id.linearLayout4);
        // Set a click listener on that View
        playlistActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, PlayListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button goToCart = (Button) findViewById(R.id.button2);
        // Set a click listener on that View
        goToCart.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
