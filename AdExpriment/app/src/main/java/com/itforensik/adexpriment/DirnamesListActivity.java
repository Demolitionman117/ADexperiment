package com.itforensik.adexpriment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;


public class DirnamesListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirnames_list);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SDcardDirListFragment())
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(SDCardDataMediaModel.getFilesList().size() != 0) {
            SDCardDataMediaModel.clearFileList();
            SDcardDirListFragment.DirectoryFilesListFiles = SDCardDataModel.getDirectoryList();
            Log.e("Information","Clearing mediamodel");

        }else {
            Log.e("Information","Clearing DataModel");
            SDCardDataModel.clearDirectoryList();
       }

    }

}
