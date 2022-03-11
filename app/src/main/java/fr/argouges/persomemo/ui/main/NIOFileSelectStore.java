package fr.argouges.persomemo.ui.main;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import fr.argouges.persomemo.MainActivity;

public class NIOFileSelectStore {
    public static Boolean main(String datapref) throws IOException {
        File mFile = null;
        File root = null;
        File folder = null;
        File subfolder = null;
        String NOTE = "PersoMemo.txt";
        if(MainActivity.PATH_DIR==true) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
                root = new File(Environment.getExternalStorageDirectory().getPath() + datapref);
                if (!root.exists()) {
                    boolean success = root.mkdir();
                }
                folder = new File(Environment.getExternalStorageDirectory().getPath() + datapref + MainActivity.PACKAGE_NAME);
                if (!folder.exists()) {
                    boolean success = folder.mkdir();
                }
                subfolder = new File(Environment.getExternalStorageDirectory().getPath() + datapref + MainActivity.PACKAGE_NAME + "/files/");
                if (!subfolder.exists()) {
                    boolean success = subfolder.mkdir();
                }
                mFile = new File(Environment.getExternalStorageDirectory().getPath() + datapref + MainActivity.PACKAGE_NAME + "/files/" + NOTE);
                mFile.createNewFile();
                if (!mFile.exists()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
        if(MainActivity.PATH_DIR==false) {
            //root = new File(Environment.getDataDirectory() + datapref);
            root = new File(Environment.getDataDirectory() + "/user/0/");
            if (!root.exists()) {
                boolean success = root.mkdir();
            }
            folder = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME);
            if (!folder.exists()) {
                boolean success = folder.mkdir();
            }
            subfolder = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/");
            if (!subfolder.exists()) {
                boolean success = subfolder.mkdir();
            }
            mFile = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/" + NOTE);
            mFile.createNewFile();
            if (!mFile.exists()) {
                mFile = new File(MainActivity.PATH_ROOT + "/" + NOTE);
                mFile.createNewFile();
                if (!mFile.exists()) {
                    return false;
                } else {
                    MainActivity.PATH_SDCARD = true;
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static Boolean select(String storepref) throws IOException {
        Boolean statut = main(storepref) ;
        if(!statut) {
            return false;
        } else {
            return true;
        }
    }
}
