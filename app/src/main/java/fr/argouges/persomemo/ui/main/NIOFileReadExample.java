package fr.argouges.persomemo.ui.main;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import fr.argouges.persomemo.MainActivity;

public class NIOFileReadExample {

    public static String main(String args) throws IOException {
        File mFile = null;
        int value;
        if(MainActivity.PATH_DIR==true) {
            mFile = new File(Environment.getExternalStorageDirectory().getPath() + MainActivity.PATH_NAME + MainActivity.PACKAGE_NAME + "/files/" + args);
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                StringBuffer lu = new StringBuffer();
                FileInputStream input = new FileInputStream(mFile);
                if (mFile.exists()) {
                    while ((value = input.read()) != -1)
                        lu.append((char) value);
                    String lecture = lu.toString();
                    String encodedWithISO88591 = lecture;
                    String decodedToUTF8 = new String(encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8");
                    if (input != null)
                        input.close();
                    return decodedToUTF8;
                } else {
                    return null;
                }
            } else {
                return "Erreur de lecture du fichier...";
            }
        }
        if(MainActivity.PATH_DIR==false) {
            if(MainActivity.PATH_SDCARD==false) {
                mFile = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/" + args);
            } else {
                mFile = new File(MainActivity.PATH_ROOT + "/" + args);
            }
                StringBuffer lu = new StringBuffer();
                FileInputStream input = new FileInputStream(mFile);
                if (mFile.exists()) {
                    while ((value = input.read()) != -1)
                        lu.append((char) value);
                    String lecture = lu.toString();
                    String encodedWithISO88591 = lecture;
                    String decodedToUTF8 = new String(encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8");
                    if (input != null)
                        input.close();
                    return decodedToUTF8;
                } else {
                    return null;
                }
        }
        return "Erreur de lecture du fichier...";
    }

}
