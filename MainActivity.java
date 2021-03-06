package com.zpanel.anderson.iat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(i);
                //I wanna put a fucking registration intent here
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(MainActivity.this, Help.class);
            MainActivity.this.startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //contains the campuses logic
            // Handle the camera action
           /* Help help=new Help();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, help, help.getTag()).commit();
            Intent intent=new Intent(MaintActivity.this,Camp);
*/
            Intent intent=new Intent(MainActivity.this,Campuses.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            //containts the courses login
            Intent intent=new Intent(MainActivity.this,Courses.class);
            MainActivity.this.startActivity(intent);

        }
        else if(id==R.id.enquiries){
            Uri uri = Uri.parse( "http://www.iat.ac.ke/index.php/all-courses-new" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.site){
            Uri uri = Uri.parse( "http://www.iat.ac.ke/neweb" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.timetable){
            Uri uri = Uri.parse( "https://www.zebaki.co.ke/timetable.php" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.facebook){
            Uri uri = Uri.parse( "https://www.facebook.com/IATCollege/" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.instagram){
            Uri uri = Uri.parse( "https://www.instagram.com" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.twitter){
            Uri uri = Uri.parse( "https://twitter.com/IATCollege" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.linkedin){
            Uri uri = Uri.parse( "https://www.linkedin.com/in/iat-ins-of-adv-tech-88a73185?trk=hp-identity-name" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        else if(id==R.id.youtube){
            Uri uri = Uri.parse( "https://www.youtube.com" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }

        /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab4(), "Enquiries");
        adapter.addFragment(new tab2(), "ambassador");
        adapter.addFragment(new tab3(), "profile");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



//app:srcCompat="@drawable/iatlauncher"///////////////////////overridden resource in xml file




}
