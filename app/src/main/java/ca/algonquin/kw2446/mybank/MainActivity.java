package ca.algonquin.kw2446.mybank;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.algonquin.kw2446.mybank.util.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    NavController navController;
    ImageView ivDeposit;

    private static final int PROFILE_REQUEST_CODE = 30;
    private static final int ACCOUNT_REQUEST_CODE = 31;
    private static final int DEPOSIT_REQUEST_CODE = 10;
    private static final int TRANSFER_REQUEST_CODE = 20;
    NavigationView navigationView;
    private  static final int UNIQUE_REQUEST_CODE=29389;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v->startActivity(new Intent(this,AccountListActivity.class)));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setProfileInHeader();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



       navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //navController = NavHostFragment.findNavController(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment));
        //navController = Navigation.findNavController(View)

                mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_history, R.id.nav_faq,
                R.id.nav_setting, R.id.nav_deposit, R.id.nav_transfer)
                .setDrawerLayout(drawer)
                .build();
       // mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        checkPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                navController.navigate(R.id.nav_home);
                break;
            case R.id.action_settings:
                navController.navigate(R.id.nav_setting);
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


//    public void fragmentNavigate(int fragmeint_id) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
//        navController.navigate(fragmeint_id);
//
//    }

    public void setProfileInHeader(){
        View header=navigationView.getHeaderView(0);
        TextView tvClientName=header.findViewById(R.id.tvClientName);
        TextView tvClientEmail=header.findViewById(R.id.tvClientEmail);

        tvClientName.setText(PreferenceManager.getString(this,"Name"));
        tvClientEmail.setText(PreferenceManager.getString(this,"Email"));
    }

    public void checkPermission(){
        //boolean hasPermission=PreferenceManager.getBoolean(getApplicationContext(),"Permission");
        boolean write=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED;
        boolean read=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED;
        boolean intent=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED;
        boolean network=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE)==PackageManager.PERMISSION_GRANTED;
        if(!write || !read || !intent || !network) getPermission();
    }
    public void getPermission() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE}, UNIQUE_REQUEST_CODE);
        }
        else{
            Toast.makeText(MainActivity.this,"Permission granted! Thank you", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==UNIQUE_REQUEST_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Tahnk you! Permission granted",Toast.LENGTH_SHORT).show();
            }
            else if(grantResults[0]==PackageManager.PERMISSION_DENIED){

                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                    AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                    dialog.setMessage("This permission is important to save a file the phone! Please permit it!")
                            .setTitle("Required Permission");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, UNIQUE_REQUEST_CODE);
                        }
                    });

                    dialog.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"Can't be done!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
                else{
                    Toast.makeText(MainActivity.this,"We will never show this to you again!",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }


}
