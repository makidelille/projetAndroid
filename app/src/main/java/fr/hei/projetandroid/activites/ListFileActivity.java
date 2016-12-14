package fr.hei.projetandroid.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;

import fr.hei.projetandroid.R;

public class ListFileActivity extends AppCompatActivity {

    public static final String EXTRA_PATH = "path";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            save(path);
        }
        return true;
    }

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView lv = (ListView) this.findViewById(R.id.listView);

        Intent it = getIntent();
        path  = it.hasExtra(EXTRA_PATH) ? it.getStringExtra(EXTRA_PATH) :  "/";
        this.setTitle(path);
        File f = new File(path);
        ArrayList<HashMap<String,String>> files = getFiles(f);
        lv.setAdapter(new SimpleAdapter(this,files,R.layout.fragment_listview,new String[] {"img", "nom", "taille"}, new int[] {R.id.img, R.id.txtnom, R.id.txttaille,}));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> fichier = (HashMap<String, String>) lv.getItemAtPosition(position);
                String path = fichier.get("path");

                File f = new File(path);
                if(f.isDirectory()) {
                    Intent it = new Intent(parent.getContext(), ListFileActivity.class);
                    it.putExtra(EXTRA_PATH, path);
                    parent.getContext().startActivity(it);
                }else{
                    Intent it = new Intent(parent.getContext(), ZoomActivity.class);
                    it.putExtra(ZoomActivity.EXTRA_IMG, path);
                    parent.getContext().startActivity(it);
                }



            }
        });
    }

    private ArrayList<HashMap<String,String>> getFiles(File f) {
        ArrayList<HashMap<String,String>> ret = new ArrayList<>();
        HashMap<String,String> fichier = new HashMap<>();
        fichier.put("img", String.valueOf(R.drawable.dossieric));
        fichier.put("nom", "..");
        fichier.put("taille", ".");
        fichier.put("path",f.getParent());
        ret.add(fichier);
        if(f.canRead()) {
            FileFilter imgFilter = new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    String filename = pathname.getName();
                    return filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".png") || filename.toLowerCase().endsWith(".jpeg") || pathname.isDirectory();
                }

            };
            File[] childs = f.listFiles(imgFilter);
            for (int i = 0; i < childs.length; i++) {
                fichier = new HashMap<>();
                fichier.put("img", String.valueOf(childs[i].isDirectory() ? R.drawable.dossieric : R.drawable.fichieric));
                fichier.put("nom", childs[i].getName());
                fichier.put("taille", childs[i].isDirectory() ? "." : String.valueOf(childs[i].length()));
                fichier.put("path",childs[i].getAbsolutePath());
                ret.add(fichier);
            }
        }


        return ret;

    }


    public void save(String path){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MainActivity.PREF_PATH,path);
        editor.commit();
        editor.apply();
    }
}
