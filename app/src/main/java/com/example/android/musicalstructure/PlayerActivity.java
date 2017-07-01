
package com.example.android.musicalstructure;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class PlayerActivity extends AppCompatActivity {

    private ArrayList<File> fileList = new ArrayList<File>();
    private LinearLayout view;
    private MediaPlayer mp = null;
    private Handler handler = new Handler();
    private double startTime = 0;
    private SeekBar sk = null;
    private TextView tvProgress = null; //time progress of the song
    private TextView tvDuration = null; //duration of the song
    private File filePath = null;
    private int numberOfFiles = 0; //this is the total number of files in that folder
    private int iSound = 0; //This is the current number of song playing or selected
    private Boolean activeRandom = false;
    private Boolean activeLoop = false;
    private Boolean activePlay = false;
    private Button stop = null;
    private Button retweet = null;
    private Button forward = null;
    private Button random = null;
    private Button playPause = null;
    private Button backward = null;
    private TextView textPlaying = null;
    private TextView soundSelectedId = null;
    private Button songDetailsTW = null;
    private String pathString = null;
    private File listFile[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //Add the icon on the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //Set the Awesome Font
        Typeface fontAW = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        random = (Button) findViewById(R.id.random);
        random.setTypeface(fontAW);
        stop = (Button) findViewById(R.id.stop);
        stop.setTypeface(fontAW);
        forward = (Button) findViewById(R.id.forward);
        forward.setTypeface(fontAW);
        playPause = (Button) findViewById(R.id.playPause);
        playPause.setTypeface(fontAW);
        retweet = (Button) findViewById(R.id.retweet);
        retweet.setTypeface(fontAW);
        backward = (Button) findViewById(R.id.backward);
        backward.setTypeface(fontAW);
        songDetailsTW = (Button) findViewById(R.id.songDetails);
        songDetailsTW.setTypeface(fontAW);
        //Find the id for the TextView that will show current name of song playing
        textPlaying = (TextView) findViewById(R.id.textPlaying);
        //Find the id of TextView that shows the time progress of the song
        tvProgress = (TextView) findViewById(R.id.progress);
        //Find the id of TextView that shows the duration of the song
        tvDuration = (TextView) findViewById(R.id.duration);
        //Find the id of SeekBar
        sk = (SeekBar) findViewById(R.id.bar);
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
                Intent intent = new Intent(PlayerActivity.this, PlayListActivity.class);
                startActivity(intent);
                mp_stop();
                finish();
            }
        });
        LinearLayout browserActivity = (LinearLayout) findViewById(R.id.linearLayout1);
        // Set a click listener on that View
        browserActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerActivity.this, FoldersActivity.class);
                startActivity(intent);
                mp_stop();
                finish();
            }
        });
        LinearLayout shopActivity = (LinearLayout) findViewById(R.id.linearLayout);
        // Set a click listener on that View
        shopActivity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerActivity.this, ShopActivity.class);
                startActivity(intent);
                mp_stop();
                finish();
            }
        });
        /**Here the are the control button of the Media Player**
         * songDetailsTW shows the Song Details using the MediaMetadataRetriever used in the infoMedia()
         */
        songDetailsTW.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                if (listFile.length != 0) {
                    infoMedia(iSound);
                }
            }
        });
        retweet.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                if (activeLoop == false) {
                    //mp.setLooping(true);
                    activeLoop = true;
                    retweet.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    //mp.setLooping(false);
                    activeLoop = false;
                    retweet.setTextColor(getResources().getColor(R.color.colorText));
                }
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                if (activeRandom == false) {
                    activeRandom = true;
                    random.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    activeRandom = false;
                    random.setTextColor(getResources().getColor(R.color.colorText));
                }
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                if (listFile.length != 0) {
                    if (iSound > numberOfFiles) {
                        iSound = 0;
                    }
                    soundSelectedId = (TextView) findViewById(iSound);
                    soundSelectedId.setTextColor(getResources().getColor(R.color.colorText));
                    if (iSound == 0) {
                        iSound = numberOfFiles;
                    } else {
                        iSound = --iSound;
                    }
                    if (mp != null) {
                        mp.stop();
                    }
                    File soundFile = getfile(filePath).get(iSound);
                    mp = MediaPlayer.create(getApplicationContext(), Uri.parse(soundFile.toString()));
                    mp_play(soundFile);
                }
            }
        });
        playPause.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                if (listFile.length != 0) {
                    if (activePlay == false) {
                        if (mp == null) {
                            File soundFile = getfile(filePath).get(0);
                            mp = MediaPlayer.create(getApplicationContext(), Uri.parse(soundFile.toString()));
                            mp_play(soundFile);
                        } else {
                            File soundFile = getfile(filePath).get(iSound);
                            mp_play(soundFile);
                        }
                    } else {
                        mp.pause();
                        activePlay = false;
                        playPause.setText(R.string.icon_play);
                    }
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                forwardMethod();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                mp_stop();
            }
        });
        //Getting the file path
        pathString = Environment.getExternalStorageDirectory().toString() + "/Music";
        //Here the file path is set by FolderActivity
        if (getIntent() != null && getIntent().getExtras() != null) {
            pathString = getIntent().getExtras().getString("soundPath");
        }
        filePath = new File(pathString);
        getfile(filePath);
        //Showing the list of files in that folder
        view = (LinearLayout) findViewById(R.id.view); //this finds the LinearLayout ID
        //Create the list showed as TextView
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
            numberOfFiles = i;
            final int fileClicked = numberOfFiles;
            //Here happen when a TextView on the list generated is touched
            TextView soundId = (TextView) findViewById(i); //This find the Id of all TextView generated
            //Set a click listener on that View
            soundId.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the colors View is clicked on.
                @Override
                public void onClick(View view) {
                    if (iSound > numberOfFiles) {
                        iSound = 0;
                    }
                    //This set the color of the previous song
                    soundSelectedId = (TextView) findViewById(iSound);
                    soundSelectedId.setTextColor(getResources().getColor(R.color.colorText));
                    iSound = fileClicked; //set iSound on the number of file touched
                    //stop an eventual media player active
                    if (mp != null) {
                        mp.stop();
                        mp.reset();
                    }
                    File soundFile = getfile(filePath).get(iSound); //get the file path of the file touched
                    mp = MediaPlayer.create(getApplicationContext(), Uri.parse(soundFile.toString()));
                    mp_play(soundFile);
                }
            });
        }
    }

    /**
     * Generate a list of file
     * The information was found here: http://stacktips.com/tutorials/android/listing-all-sdcard-filenames-with-extensions-in-android
     *
     * @param dir is the directory path
     * @return
     */
    public ArrayList<File> getfile(File dir) {
        listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            Arrays.sort(listFile);
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".mp3")
                            || listFile[i].getName().endsWith(".ogg")
                            || listFile[i].getName().endsWith(".m4a")
                            || listFile[i].getName().endsWith(".wav")) {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }
        return fileList;
    }

    //this set the position of the seekbar and the textview in function of the time progress of the song
    private Runnable updateBar = new Runnable() {
        public void run() {
            int timeElapsed = mp.getCurrentPosition();
            int timeDuration = mp.getDuration();
            startTime = mp.getCurrentPosition();
            //shows the time progress
            tvProgress.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed), TimeUnit.MILLISECONDS.toSeconds((long) timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed))));
            tvDuration.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) timeDuration), TimeUnit.MILLISECONDS.toSeconds((long) timeDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeDuration))));
            sk.setProgress((int) startTime);
            handler.postDelayed(this, 100);
        }
    };

    //This function stop the playing
    private void mp_stop() {
        if (listFile.length != 0) {
            if (mp != null) {
                mp.pause();
                mp.seekTo(0);
                activePlay = false;
                playPause.setText(R.string.icon_play);
            }
        }
    }

    private int randomMethod() {
        Random random = new Random();
        int randomSound = random.nextInt(numberOfFiles);
        return randomSound;
    }

    private void forwardMethod() {
        if (listFile.length != 0) {
            if (mp != null) {
                mp.stop();
            }
            if (iSound > numberOfFiles) {
                iSound = 0;
            }
            soundSelectedId = (TextView) findViewById(iSound);
            soundSelectedId.setTextColor(getResources().getColor(R.color.colorText));
            if (activeRandom == false) {
                File soundFile = getfile(filePath).get(++iSound);
                mp = MediaPlayer.create(getApplicationContext(), Uri.parse(soundFile.toString()));
                mp_play(soundFile);
            } else {
                iSound = randomMethod();
                File soundFile = getfile(filePath).get(iSound);
                mp = MediaPlayer.create(getApplicationContext(), Uri.parse(soundFile.toString()));
                mp_play(soundFile);
            }
        }
    }

    /**
     * Information about how to build the Media Player was found here: http://www.html.it/pag/50106/media-player-riprodurre-risorse-multimediali/
     **/
    private void mp_play(File soundFile) {
        mp.start();
        sk.setMax(mp.getDuration());
        //this allows to set the progress of song dragging the seekbar
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        handler.postDelayed(updateBar, 100);
        activePlay = true;
        playPause.setText(R.string.icon_pause);
        textPlaying.setText(soundFile.getName().replaceFirst("[.][^.]+$", ""));
        if (iSound > numberOfFiles) {
            iSound = 0;
        }
        soundSelectedId = (TextView) findViewById(iSound);
        soundSelectedId.setTextColor(getResources().getColor(R.color.colorAccent));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp_stop();
                if (activeLoop == false && iSound == numberOfFiles) {
                    mp_stop();
                } else {
                    forwardMethod();
                }
            }
        });
    }

    /**
     * Information about MediaMetadataRetriever was found here: https://stackoverflow.com/questions/9031865/how-to-get-artist-album-in-media-player-for-api-level-2-2
     **/
    private void infoMedia(int iSound) {
        File soundFile = getfile(filePath).get(iSound);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getApplicationContext(), Uri.parse(soundFile.toString()));
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (title == null) {
            title = getString(R.string.unknown);
        }
        String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        if (album == null) {
            album = getString(R.string.unknown);
        }
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (artist == null) {
            artist = getString(R.string.unknown);
        }
        String message = "<b>" + getString(R.string.song_detail) + "</b><br><br>";
        message += "<b>" + getString(R.string.title) + " </b>" + title + "<br>";
        message += "<b>" + getString(R.string.file_name) + " </b>" + soundFile.getName() + "<br>";
        message += "<b>" + getString(R.string.album_name) + " </b>" + album + "<br>";
        message += "<b>" + getString(R.string.artist_name) + " </b>" + artist;
        new AlertDialog.Builder(this).setMessage(Html.fromHtml(message)).show();
    }
}
