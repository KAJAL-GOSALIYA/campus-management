package com.android.collegemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SaveSharedPreference.getUserName(getApplicationContext()).length() == 0)
        {
            // call Login Activity
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
//            MainActivity.this.finish();
            Log.d("MainActivity", "Login complete");
        }
        else
        {
            setContentView(R.layout.activity_main);
            Log.d("MainActivity","setContentView");
            // Stay at the current activity.
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


 /*            radioGroup.addView((RadioButton)findViewById(R.id.rdbtn_atten));
            radioGroup.addView(findViewById(R.id.rdbtn_in));
            radioGroup.addView(findViewById(R.id.rdbtn_lap_in));
            radioGroup.addView(findViewById(R.id.rdbtn_lap_out));
            radioGroup.addView(findViewById(R.id.rdbtn_out));*/

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Log.d("MainActivity","Drawer Closed");
        } else {
            Log.d("MainActivity","Back");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Log.d("MainActivity","onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("MainActivity","onOptionsItemSelected");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("MainActivity","action_settings");
            return true;
        }

        else if(id==R.id.action_logout){
            Log.d("MainActivity","action_settings");
            SaveSharedPreference.clearUserName(getApplicationContext());
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_qr) {
            QRFragment qrFragment = new QRFragment();
            getFragmentManager().beginTransaction().replace(R.id.contentMain,qrFragment).commit();
//            qrFragment.setImageView(R.drawable.ic_menu_qr);
        } else if (id == R.id.nav_gallery) {

            ContactsFragment contactsFragment = new ContactsFragment();
            getFragmentManager().beginTransaction().replace(R.id.contentMain,contactsFragment).commit();

        } else if (id == R.id.nav_slideshow) {
            CanteenFragment canteenFragment = new CanteenFragment();
            getFragmentManager().beginTransaction().replace(R.id.contentMain,canteenFragment).commit();


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
