package com.itforensik.adexpriment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Saed on 2014-10-15.
 */
public class SDCardDataMediaModel {
    private static ArrayList<File> mDirectory = new ArrayList<File>();

    public SDCardDataMediaModel(){

    }


    public void setFiles(File dirname){
        this.mDirectory.add(dirname);

    }

    public static void clearFileList(){
        mDirectory.clear();
    }

    public static ArrayList<File> getFilesList(){
        return mDirectory;
    }



}
