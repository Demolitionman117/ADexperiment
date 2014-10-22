package com.itforensik.adexpriment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;


public class SDcardDirListFragment extends ListFragment {
   public static ArrayList<File> DirectoryFilesListFiles = new ArrayList<File>();
    private ArrayList<String> DirecotryFileListString = new ArrayList<String>();
    ArrayAdapter adapter;
    public SDcardDirListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (SDCardDataMediaModel.getFilesList().isEmpty()) {

            DirectoryFilesListFiles = SDCardDataModel.getDirectoryList();
            //Konvertera file array till string för att visa i listan
            if (DirectoryFilesListFiles != null) {
                for (int i = 0; i < DirectoryFilesListFiles.size(); i++) {
                    DirecotryFileListString.add(DirectoryFilesListFiles.get(i).toString());

                }
            }
            } else {
            DirectoryFilesListFiles = SDCardDataMediaModel.getFilesList();
            //Konvertera file array till string för att visa i listan
            if (DirectoryFilesListFiles != null) {
                for (int i = 0; i < DirectoryFilesListFiles.size(); i++) {
                    DirecotryFileListString.add(DirectoryFilesListFiles.get(i).getName());

                }
            }
        }






                // TODO: Change Adapter to display your content
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, DirecotryFileListString);
                setListAdapter(adapter);
                adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        SDCardSubDataModel subdata = new SDCardSubDataModel();
       SDCardDataMediaModel MediaFile = new SDCardDataMediaModel();

        //skålar namnet på den valda dir.

        if (!DirectoryFilesListFiles.isEmpty()) {
            Toast.makeText(getActivity(), DirecotryFileListString.get(position).toString(),Toast.LENGTH_SHORT).show();
            File subdir = DirectoryFilesListFiles.get(position);
            File sublist[] = subdir.listFiles();

            if (sublist != null) {
                //spara listan i en array och i modellen.
                for (int i = 0; i < sublist.length; i++) {
                    if (sublist[i].isDirectory()) {
                        subdata.setDirectory(sublist[i]);
                    } else {
                        MediaFile.setFiles(sublist[i]);
                    }

                }
            }

            if(!subdir.isDirectory()){
                String type = null;
                URI fileuri = DirectoryFilesListFiles.get(position).toURI();
                String extension = MimeTypeMap.getFileExtensionFromUrl(fileuri.toString());
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                if(extension !=null){
                    type =  mime.getMimeTypeFromExtension(extension);
                }

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse(fileuri.toString()), type);
                if(i.resolveActivity(getActivity().getPackageManager()) != null) {

                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(),"No App to handle this file" + DirectoryFilesListFiles.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            }else {



                //Starta och Visa subir.
                Intent i = new Intent(getActivity(), SubDirnamesListActivity.class);
                startActivity(i);
            }
        }





    }


}
