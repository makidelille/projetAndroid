package fr.hei.projetandroid.activites;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import fr.hei.projetandroid.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class ListFileActivity extends AppCompatActivity {

    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView lv = (ListView) this.findViewById(R.id.listView);

        Intent it = getIntent();
        String path  = it.hasExtra(EXTRA_PATH) ? it.getStringExtra(EXTRA_PATH) :  "/";
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
}
