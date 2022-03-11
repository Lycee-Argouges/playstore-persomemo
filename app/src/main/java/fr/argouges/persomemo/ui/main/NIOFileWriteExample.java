package fr.argouges.persomemo.ui.main;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.argouges.persomemo.MainActivity;

public class NIOFileWriteExample {

    public static String main(String args, String save) throws IOException {
        File mFile = null;
        if(MainActivity.PATH_DIR==true) {
            mFile = new File(Environment.getExternalStorageDirectory().getPath() + MainActivity.PATH_NAME + MainActivity.PACKAGE_NAME + "/files/" + args);
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
                mFile.createNewFile();
                FileOutputStream output = new FileOutputStream(mFile);
                output.write(save.getBytes());
                if (output != null) {
                    output.close();
                    return "La note a été enregistrée !";
                } else {
                    return "Un problème est apparu. Configurez l'emplacement de stockage des notes.";
                }
            } else {
                return "Un problème est apparu lors de l'enregistrement";
            }
        }
        if(MainActivity.PATH_DIR==false) {
            if(MainActivity.PATH_SDCARD==false) {
                mFile = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/" + args);
            } else {
                mFile = new File(MainActivity.PATH_ROOT + "/" + args);
            }
            mFile.createNewFile();
            FileOutputStream output = new FileOutputStream(mFile);
            output.write(save.getBytes());
            if (output != null) {
                output.close();
                return "La note a été enregistrée !";
            } else {
                return "Un problème est apparu. Configurez l'emplacement de stockage des notes.";
            }
        }
        return "Un problème est apparu lors de l'enregistrement";
    }
}
