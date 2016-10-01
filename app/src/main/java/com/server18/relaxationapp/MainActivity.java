package com.server18.relaxationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView listView;
    Myadapter arrayAdapter;


    ArrayList<ModalData> arrayList = new ArrayList<>();
    ArrayList<ModalData> arrayListAddData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.favorate_txt);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("Contact_list", (Serializable) arrayListAddData);
                startActivity(intent);
            }
        });

        findViewById(R.id.back_music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arrayAdapter = new Myadapter(this,R.layout.myadapter,arrayList);
         listView =(ListView)findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setAdapter(arrayAdapter);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = arrayAdapter.getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                ModalData selecteditem = (ModalData) arrayAdapter.getItem(selected.keyAt(i));
                                // Remove selected items following the ids

                                arrayListAddData.add(selecteditem);
                               // arrayAdapter.remove(selecteditem);
                            }
                        }


                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }



            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                arrayAdapter.removeSelection();
            }

            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = listView.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                arrayAdapter.toggleSelection(position);
            }


        });

          audiolist();
 /*       findViewById(R.id.ply_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playaudio();
            }
        });

        findViewById(R.id.download_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           new DownloadFile().execute("http://videos1.djmazadownload.com/music/Singles/Dillagi%20-%20Rahat%20Fateh%20Ali%20Khan%20-190Kbps%20[DJMaza.Link].mp3");

            }
        });*/



        // getVideoThumbnail(this, arrayList);
     /*   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ModalData modelData = (ModalData) parent.getItemAtPosition(position);
               *//* try {
                    String s = modelData.getVideo_id();

                    MediaPlayer player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(s);
                    player.prepare();
                    player.start();

                } catch (Exception e) {
                    // TODO: handle exception
                }*//*

                String s = modelData.getVideo_id();
                Intent intent = new Intent(getApplicationContext(), AudioPlayerActivity.class);
                intent.putExtra("S", s);
                startActivity(intent);
            }

        });*/





    }



    private class DownloadFile extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            int count;

            try {

                URL url = new URL(urls[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conexion.getContentLength();

                // downlod the file
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream("/sdcard/nameofthefile.mp3");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }


   public void playaudio(){

        try {


            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource("http://videos1.djmazadownload.com/music/Singles/Dillagi%20-%20Rahat%20Fateh%20Ali%20Khan%20-190Kbps%20[DJMaza.Link].mp3");
            player.prepare();
            player.start();

        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }


    public  void audiolist() {


        //File directory = new File("/mnt/sdcard/folder");
        //  File imgFile = new  File("/sdcard/Images/test_image.jpg");

        File path = Environment.getExternalStorageDirectory();
        File addpath = new File(path.getAbsolutePath() + "/New Folder");


        try {

            File[] files = addpath.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].getAbsolutePath().contains(".mp3")) {

                    String s = (files[i].getAbsolutePath()).toString();

                   // arrayList.add(new ModalData(s));

                    String Str = new String(s);
                    System.out.println("Return Value :" );


                    String ggg[]  =  Str.split("/");
                    arrayList.add(new ModalData(ggg[4]));


                    //arrayList.add(new ModalData(files[i].getAbsolutePath()));
                }


            }

        }catch (Exception e){

        }

      //  ModalData modalData = new ModalData();


    /*    for(ModalData modalData : arrayList){

            String s = modalData.getVideo_id();

        }
*/
    }


    private class Myadapter extends ArrayAdapter {
        Context context;
        List<ModalData> mylist ;
        private SparseBooleanArray mSelectedItemsIds;
        public Myadapter(Context context, int resource,List list) {
            super(context, resource, list);
            this.context = context;
            this.mylist = list;
            mSelectedItemsIds = new SparseBooleanArray();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder ;
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.myadapter,null);
                holder = new ViewHolder();
                holder. textView =(TextView)convertView.findViewById(R.id.textview_video_id);
               holder. imageView =(ImageView)convertView.findViewById(R.id.video_imag);

                convertView.setTag(holder);
            }else {

                holder=  (ViewHolder)convertView.getTag();
            }



            ModalData data = mylist.get(position);
            holder. textView.setText(data.getVideo_id());


         /*   Uri uri =  Uri.parse(data.getVideo_id());
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(context, uri);
            Bitmap bitmap = retriever.getFrameAtTime(1000
                    , MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
            holder.imageView.setImageBitmap(bitmap);*/
            return  convertView;
        }

        class ViewHolder{
            TextView textView ;
            ImageView imageView;

        }

        public void remove(ModalData object) {
            mylist.remove(object);
            notifyDataSetChanged();
        }

        public List<ModalData> getWorldPopulation() {
            return mylist;
        }

        public void toggleSelection(int position) {
            selectView(position, !mSelectedItemsIds.get(position));
        }

        public void removeSelection() {
            mSelectedItemsIds = new SparseBooleanArray();
            notifyDataSetChanged();
        }

        public void selectView(int position, boolean value) {
            if (value)
                mSelectedItemsIds.put(position, value);
            else
                mSelectedItemsIds.delete(position);
            notifyDataSetChanged();
        }

        public int getSelectedCount() {
            return mSelectedItemsIds.size();
        }

        public SparseBooleanArray getSelectedIds() {
            return mSelectedItemsIds;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
