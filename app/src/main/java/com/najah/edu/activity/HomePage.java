package com.najah.edu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pranay.rxloginsignup.R;
import com.najah.edu.DatABasE.DB_helper;
import com.najah.edu.fileuser.edit_info;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {

    ImageButton police;
    ImageButton ambulance;
    ImageButton firefighter;
    public static String page_name = "";


    TextView show_email ;
    private DB_helper cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        final Button camera = (Button)findViewById(R.id.nav_camera);
        police =(ImageButton)findViewById(R.id.imageButton);
        police.setOnClickListener(this);
        ambulance =(ImageButton)findViewById(R.id.imageButton2);
        ambulance.setOnClickListener(this);
        firefighter =(ImageButton)findViewById(R.id.imageButton3);
        firefighter.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cc = new DB_helper(this);

        Toast.makeText(getBaseContext(), cc.get_email1  ,Toast.LENGTH_LONG).show();
       // show_email = ((TextView)findViewById(R.id.textView));
       // show_email.setText(dd.fffemail);

//
//        camera.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v)
//            {
//
//            }
//        });
//



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_page, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent cameraIntent = new Intent();
            //"android.media.action.IMAGE_CAPTURE"
            cameraIntent.
                    setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent , 0);

            // Handle the camera action
        } else if (id == R.id.account_view) {



        } else if (id == R.id.edit_account) {
            Intent intent = new Intent(HomePage.this,edit_info.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if ( id == R.id.imageButton ){
            Intent n = new Intent( HomePage.this , map_page.class ) ;
            page_name = "Police" ;
            startActivity( n );
        }
        if ( id == R.id.imageButton2 ){
            Intent n = new Intent( HomePage.this , map_page.class ) ;
            page_name = "Ambulance" ;
            startActivity( n );
        }
        if ( id == R.id.imageButton3 ){
            Intent n = new Intent( HomePage.this , map_page.class ) ;
            page_name = "Firefighter";
            startActivity( n );
        }
    }

}