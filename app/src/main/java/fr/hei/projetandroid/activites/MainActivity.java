package fr.hei.projetandroid.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.hei.projetandroid.R;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_PATH = "PathPref";
    private static final String DEF_PATH = "/storage/emulated/0/Pictures/";

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        path = preferences.getString(PREF_PATH,DEF_PATH);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(v.getContext(), ListFileActivity.class);
                it.putExtra(ListFileActivity.EXTRA_PATH,path);
                startActivity(it);
            }
        });

    }

    public static void save(String path, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MainActivity.PREF_PATH,path);
        editor.apply();
    }
}
