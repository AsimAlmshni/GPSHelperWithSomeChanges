package com.najah.edu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pranay.rxloginsignup.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class map_page extends AppCompatActivity {

    private GoogleApiClient client;
    //    private Context mContext ;
    Spinner CountrySp ;
    ArrayAdapter<CharSequence> adapter;
    int pos ;
//    final Spinner querySpinnerText = (Spinner) findViewById(R.id.select_spinner);
//    Button goButton = (Button) findViewById(R.id.search_btn);



    String GPS="" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        setTitle(HomePage.page_name);
        ImageButton ib = (ImageButton) findViewById(R.id.map_imageID);


        CountrySp = (Spinner) findViewById(R.id.select_spinner);
        adapter = ArrayAdapter.createFromResource
                (this, R.array.Country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CountrySp.setAdapter(adapter);
        CountrySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
//                store string in GPS
                GPS = (String) parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPS.replace(" ", "+");
                Uri uri = Uri.parse("geo:0,0?q=" + GPS);
                Intent intent = new Intent();
                intent.setPackage("com.google.android.apps.maps");
                intent.setAction(intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);

                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    Toast.makeText(getBaseContext(), "Package does not exist", Toast.LENGTH_SHORT);



            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }





    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("map_page Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
