package fr.hei.projetandroid.activites;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import fr.hei.projetandroid.R;
import fr.hei.projetandroid.activites.adapters.FileAdapter;
import fr.hei.projetandroid.utils.FileItem;

import java.io.File;

public class ListFileActivity extends ListActivity {

    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it = getIntent();
        String path  = it.hasExtra(EXTRA_PATH) ? it.getStringExtra(EXTRA_PATH) :  "/";
        File f = new File(path);

        if(f.canRead()){
            FileItem[] files = getFiles(f);
            this.setListAdapter(new FileAdapter(this,R.layout.fragment_listview,files));
        }




    }

    private FileItem[] getFiles(File f) {
        File[] childs = f.listFiles();
        FileItem[] ret = new FileItem[childs.length];
        for(int i=0; i< ret.length; i++){
            ret[i] = new FileItem(childs[i].getName(),childs[i].length(),childs[i].isDirectory());
        }

        return ret;

    }
}
