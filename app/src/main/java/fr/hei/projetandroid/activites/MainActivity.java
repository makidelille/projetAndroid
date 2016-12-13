package fr.hei.projetandroid.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.hei.projetandroid.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_PATH = "PathPref";
    private static final String DEF_PATH = "/";

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(v.getContext(), ListFileActivity.class);
                it.putExtra(ListFileActivity.EXTRA_PATH,path);
                startActivity(it);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ZoomActivity.class);
                it.putExtra(ZoomActivity.EXTRA_IMG,"/storage/3331-3234/DCIM/100ANDRO/DSC_0010.JPG");
                startActivity(it);
            }
        });


        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        path = preferences.getString(PREF_PATH,DEF_PATH);



    }
}
