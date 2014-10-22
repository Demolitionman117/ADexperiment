package com.itforensik.adexpriment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FragmentMain extends Fragment {
    private Button mReadSD;
    private Button mReadCon;
    private Button mReadSms;
    private ArrayList<SDCardDataModel> Direcotylist = new ArrayList<SDCardDataModel>();
    private SDCardRootDataModel Dirname;
        public FragmentMain() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mReadSD = (Button)rootView.findViewById(R.id.btn_read_sd);
            mReadCon = (Button)rootView.findViewById(R.id.btn_read_contacts);
            mReadSms = (Button)rootView.findViewById(R.id.btn_read_sms);
            Dirname = new SDCardRootDataModel();

            //läs SDkort data, clicklinstner
            mReadSD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                   File sdroot = new File("/");
                   File test[] = sdroot.listFiles();


                    //spara listan i en array och i modellen.
                    for(int i = 0; i < test.length; i++) {

                        Dirname.setDirectory(test[i]);

                    }

                    //satarta listan med namnen på directorys ->
                    Intent i = new Intent(getActivity(),SdCardDirRootListActivity.class);
                    startActivity(i);
                 }

            });

           //läs Kontakt data, clicklistnener.
           mReadCon.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // create command array to execute in shell as superuser.
                   String commands[] = {"su","-c","cat","/data/data/com.android.providers.contacts/databases/contacts2.db-journal"};

                   new background_task_contact().execute(commands);
                   Toast.makeText(getActivity(),"Reading contactdata",Toast.LENGTH_SHORT).show();


               }
           });

          //läs sms data, clicklistener.
          mReadSms.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   // create command array to execute in shell as superuser.
                  String commands[] = {"su","-c","cp","/data/data/com.android.providers.telephony/databases/mmssms.db","/mnt/sdcard/mmsms.db"};
                  new background_task_sms().execute(commands);
                  Toast.makeText(getActivity(),"Reading smsdata",Toast.LENGTH_SHORT).show();



              }
          });

            return rootView;
        }

public static class background_task_contact extends AsyncTask<String,String,Void>{


    @Override
    protected Void doInBackground(String... params) {

        shell Shell = new shell();
        String output =  Shell.sendShellCommand(params);

        Log.e("OutputShell",output);


        return null;
    }
}



    public static class background_task_sms extends AsyncTask<String,String,Void>{


        @Override
        protected Void doInBackground(String... params) {

            shell Shell = new shell();
            String output =  Shell.sendShellCommand(params);

            Log.e("OutputShell",output);


            return null;
        }
    }


    }

