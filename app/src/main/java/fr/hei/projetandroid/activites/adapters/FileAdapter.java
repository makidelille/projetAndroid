package fr.hei.projetandroid.activites.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import fr.hei.projetandroid.utils.FileItem;

/**
 * Created by julien on 09/12/16.
 */
public class FileAdapter extends ArrayAdapter<FileItem> {

    public FileAdapter(Context context, int resource, FileItem[] objects) {
        super(context, resource, objects);
        //robin
    }
}
