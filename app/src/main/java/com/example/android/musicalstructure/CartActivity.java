package com.example.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Add the icon on the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        /**Here below there are the activities which are launched from linearLayout4 that is the first tab
         * and linearLayout1 is the second tab
         * and linearLayout is the third tab
         */
        LinearLayout playlistActivity = (LinearLayout) findViewById(R.id.linearLayout4);
        // Set a click listener on that View
        playlistActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, PlayListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LinearLayout browserActivity = (LinearLayout) findViewById(R.id.linearLayout1);
        // Set a click listener on that View
        browserActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, FoldersActivity.class);
                startActivity(intent);
                finish();
            }
        });
        LinearLayout shopActivity = (LinearLayout) findViewById(R.id.linearLayout);
        // Set a click listener on that View
        shopActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
