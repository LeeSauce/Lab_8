package com.example.lab_8;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupToolbar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void setupToolbar(){
        //Instantiates a toolbar object
        Toolbar tb = findViewById(R.id.myToolbar);

        //calls the inflated method
        setSupportActionBar(tb);

        //Drawer Stuff
        DrawerLayout drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggleDrawer = new ActionBarDrawerToggle(this, drawer, tb,
                R.string.open,R.string.close);

        drawer.addDrawerListener(toggleDrawer);
        toggleDrawer.syncState();

        NavigationView nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message = null;
        if(item.getItemId() == R.id.bunnyItem){
            message = getResources().getString(R.string.bunnyMsg);

        } else if (item.getItemId() == R.id.catItem) {
            message = getResources().getString(R.string.catMsg);
        }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // the switch case in the module example could not be used
        //switches need a constant value... and recourses are technically not constant

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.joke){
            intent = new Intent(this, DadJoke.class);
            startActivity(intent);
        }else if(menuItem.getItemId() == R.id.exit){
            finishAffinity();
        }
        return false;
    }
}