package fr.hei.projetandroid.activites;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import fr.hei.projetandroid.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ListFileActivity extends ListActivity {

    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = getIntent();
        String path  = it.hasExtra(EXTRA_PATH) ? it.getStringExtra(EXTRA_PATH) :  "/";
        File f = new File(path);

        if(f.canRead()){
            ArrayList<HashMap<String,String>> files = getFiles(f);
            this.setListAdapter(new SimpleAdapter(this,files,R.layout.fragment_listview,new String[] {"img", "nom", "taille"}, new int[] {R.id.img, R.id.txtnom, R.id.txttaille,}));
            //robz
        }

    }

    private ArrayList<HashMap<String,String>> getFiles(File f) {
        File[] childs = f.listFiles();
        ArrayList<HashMap<String,String>> ret = new ArrayList<>();
        for(int i=0; i< childs.length; i++){
            HashMap<String,String> fichier = new HashMap<>();
            fichier.put("img", String.valueOf(childs[i].isDirectory() ? R.drawable.dossieric : R.drawable.fichieric));
            fichier.put("nom",childs[i].getName());
            fichier.put("taille", String.valueOf(childs[i].length()));
            ret.add(fichier);
        }
        return ret;

    }
}
