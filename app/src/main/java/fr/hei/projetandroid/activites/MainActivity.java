package fr.hei.projetandroid.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import fr.hei.projetandroid.R;


import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_PATH = "PathPref";
    private static final String DEF_PATH = "/storage/emulated/0/Pictures/";

    private String path;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        path = preferences.getString(PREF_PATH,DEF_PATH);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(v.getContext(), ListFileActivity.class);
                it.putExtra(ListFileActivity.EXTRA_PATH,path);
                startActivity(it);
            }
        });

        button1 = (Button) findViewById(R.id.action_save);
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(MainActivity.this, button1);

                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_save) {
                            save("");
                        }
                        return true;
                    }
                });


                popup.show();

            }
        });




    }

    public void save(String path){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_PATH,path);
        editor.commit();
        editor.apply();
    }
}
