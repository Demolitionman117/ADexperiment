package com.itforensik.adexpriment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Saed on 2014-10-15.
 */
public class SDCardRootDataModel {
    private static ArrayList<File> mDirectory = new ArrayList<File>();

    public SDCardRootDataModel(){

    }


    public void setDirectory(File dirname){
        this.mDirectory.add(dirname);

    }

    public static void clearDirectoryList(){
        mDirectory.clear();
    }

    public static ArrayList<File> getDirectoryList(){
        return mDirectory;
    }



}
