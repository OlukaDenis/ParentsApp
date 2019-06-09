package com.dentech.parentsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dentech.parentsapp.common.Common;
import com.dentech.parentsapp.navigationview.FinanceFragment;
import com.dentech.parentsapp.navigationview.MarksFragment;
import com.dentech.parentsapp.navigationview.ProfileFragment;

public class ParentHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener{
    FinanceFragment financeFragment = new FinanceFragment();
    MarksFragment marksFragment = new MarksFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    BottomNavigationView navigation;
    NavigationView navigationView;
    String name = Common.current_student.getName();
    String student_number = Common.current_student.getStudent_number();
    TextView studentNo, username;
    private boolean allowBackButtonExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Toast.makeText(this, Common.current_student.getName(), Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //showing the users full name on the header
        View headerView = navigationView.getHeaderView(0);

        username = (TextView) headerView.findViewById(R.id.tv_current_user);
        username.setText(name);
        studentNo = (TextView) headerView.findViewById(R.id.tv_current_stuNo);
        studentNo.setText(student_number);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_finance);//default navigation item
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!allowBackButtonExit) {
                allowBackButtonExit = true;
                Toast.makeText(this, "Press again to close app.", Toast.LENGTH_SHORT).show();
                CountDownTimer timer = new CountDownTimer(1000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        allowBackButtonExit = false;
                    }
                }.start();
            } else {
                System.exit(0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent_home, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.navigation_finance){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, financeFragment).commit();
            return true;
        }

        if(id == R.id.navigation_marks){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, marksFragment).commit();
            return true;
        }

        if(id == R.id.navigation_profile){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, profileFragment).commit();
            return true;
        }

        if (id == R.id.nav_settings) {
            // Handle the camera action
        }  else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {
           Intent intent = new Intent(ParentHomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
