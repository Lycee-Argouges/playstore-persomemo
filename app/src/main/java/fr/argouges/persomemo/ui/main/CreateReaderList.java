package fr.argouges.persomemo.ui.main;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import fr.argouges.persomemo.MainActivity;

public class CreateReaderList {

    public static Integer counter() {
        File mFile = null;
        Integer count = null;
        int BlankFile = 1 ;
        int flag = 0;
        for (int i=1; i<50 ;i++) {
            if(MainActivity.PATH_DIR==true) {
                mFile = new File(Environment.getExternalStorageDirectory().getPath() + MainActivity.PATH_NAME + MainActivity.PACKAGE_NAME + "/files/PersoMemo" + i + ".txt");
            } else {
                if(MainActivity.PATH_SDCARD==false) {
                    mFile = new File(Environment.getDataDirectory() + "/user/0/" + MainActivity.PACKAGE_NAME + "/files/PersoMemo" + i + ".txt");
                } else {
                    mFile = new File(MainActivity.PATH_ROOT + "/PersoMemo" + i + ".txt");
                }
            }
            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || MainActivity.PATH_DIR==false) {
                if (mFile.exists()) {
                    boolean exist = reader(i);
                    if (exist) {
                        if (flag != 0) {
                            String TransFile = null;
                            try {
                                TransFile = NIOFileReadExample.main("PersoMemo" + i + ".txt");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                NIOFileWriteExample.main("PersoMemo" + BlankFile + ".txt", TransFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                NIOFileWriteExample.main("PersoMemo" + i + ".txt", "");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        BlankFile++;
                    } else {
                        flag++;
                    }
                } else {
                    count = i;
                    break;
                }
            }
        }
        return BlankFile;
    }

    public static Boolean reader(int args) {
        String NoteN = null;
        String NOTE = "PersoMemo" + args + ".txt";
        try {
            NoteN = NIOFileReadExample.main(NOTE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("".equals(NoteN)) {
            return false;
        } else {
            return true;
        }
    }
}
