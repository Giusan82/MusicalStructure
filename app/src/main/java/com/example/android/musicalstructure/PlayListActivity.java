package com.example.android.musicalstructure;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class PlayListActivity extends AppCompatActivity {
    private String pathString = null;
    private ArrayList<File> fileList = new ArrayList<File>();
    private LinearLayout view;
    File filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        //Add the icon on the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        /**Here below there are the activities which are launched from linearLayout1 that is the second tab
         * and linearLayout is the third tab
         */
        LinearLayout FoldersActivity = (LinearLayout) findViewById(R.id.linearLayout1);
        // Set a click listener on that View
        FoldersActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayListActivity.this, FoldersActivity.class);
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
                Intent intent = new Intent(PlayListActivity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Showing the list of files in that folder
        view = (LinearLayout) findViewById(R.id.view); //this finds the LinearLayout ID
        pathString = Environment.getExternalStorageDirectory().toString() + "/Playlists";
        filePath = new File(pathString);
        getfile(filePath);
        for (int i = 0; i < fileList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 1);
            TextView textView = new TextView(this);
            textView.setLayoutParams(params);
            textView.setPadding(10, 15, 10, 15);
            String nameFileExt = fileList.get(i).getName(); //this needs to be declared final to be available for other class
            String nameFile = nameFileExt.replaceFirst("[.][^.]+$", ""); //it will remove the file extension
            textView.setText(nameFile);
            textView.setTextSize(16);
            textView.setMaxLines(1);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            textView.setTextColor(getResources().getColor(R.color.colorText));
            textView.setId(i);
            view.addView(textView);
        }
    }

    //Generate a list of file
    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            Arrays.sort(listFile);
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".pla")) {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }
        return fileList;
    }
}
