package fr.argouges.persomemo.ui.main;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import fr.argouges.persomemo.MainActivity;

public class NIOFileDeleteExample {
    
    public static Boolean main(String args) throws IOException {
        File mFile = null;
        Boolean success = false;
        if(MainActivity.PATH_DIR==true) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
                mFile = new File(Environment.getExternalStorageDirectory().getPath() + MainActivity.PATH_NAME + MainActivity.PACKAGE_NAME + "/files/" + args);
                if (!mFile.exists()) {
                    success = mFile.delete();
                }
            }
            return success;
        }
        if(MainActivity.PATH_DIR==false) {
            if(MainActivity.PATH_SDCARD==false) {
                mFile = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/" + args);
            } else {
                mFile = new File(MainActivity.PATH_ROOT + "/" + args);
            }
            if (!mFile.exists()) {
                success = mFile.delete();
            }
            return success;
        }
        return false;
    }

}