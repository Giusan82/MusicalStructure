package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class FoldersActivity extends AppCompatActivity {
    //sound paths variables
    private String soundPath1 = Environment.getExternalStorageDirectory() + "/Music";
    private String soundPath2 = Environment.getExternalStorageDirectory() + "/Sounds";
    private String soundPath3 = Environment.getExternalStorageDirectory() + "/Ringtones";
    private String soundPath4 = System.getenv("SECONDARY_STORAGE") + "/Music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);
        //Add the icon on the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        /**Here below there are the activities which are launched from linearLayout4 that is the first tab
         * linearLayout1 is the second tab
         * linearLayout is the third tab
         */
        LinearLayout playlistActivity = (LinearLayout) findViewById(R.id.linearLayout4);
        // Set a click listener on that View
        playlistActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, PlayListActivity.class);
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
                Intent intent = new Intent(FoldersActivity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /**Here below launch the Player activity setting the file path in the soundPath variable*/
        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        rl1.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, PlayerActivity.class);
                intent.putExtra("soundPath", soundPath1);  // pass the values of soundPath1 and retrieve them in the PlayerActivity using soundPath
                startActivity(intent);
                finish();
            }
        });
        RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        rl2.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, PlayerActivity.class);
                intent.putExtra("soundPath", soundPath2);
                startActivity(intent);
                finish();
            }
        });
        RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.relativeLayout3);
        rl3.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, PlayerActivity.class);
                intent.putExtra("soundPath", soundPath3);
                startActivity(intent);
                finish();
            }
        });
        RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.relativeLayout4);
        rl4.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, PlayerActivity.class);
                intent.putExtra("soundPath", soundPath4);
                startActivity(intent);
                finish();
            }
        });
    }
}
