package walid44443.fb.resturantfirsttry;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    RecyclerView resturant_recycler;
    SQLiteDatabase db;
    List<ResturantModel> resturantModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //db create
        SQLiteOpenHelper dbHelper = new DbHelper(this);
        db =dbHelper.getWritableDatabase();

        resturantModels = new ArrayList<>();
        try {
            CursorToList();
        }catch (Exception e){

        }


        RecyclerView recyclerView = findViewById(R.id.resturant_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ResturantRecyclerView adapter = new ResturantRecyclerView(resturantModels);
        recyclerView.setAdapter(adapter);


    }

    private void CursorToList() {
        resturantModels.clear();
        Cursor mCursor = db.query(true, DbHelper.DBTABLE,null,null,null,null,null,null,null);
        if(mCursor.getCount() > 0){
            mCursor.moveToFirst();
            do {
                ResturantModel resturantModel = new ResturantModel();
                resturantModel.setId(mCursor.getInt(mCursor.getColumnIndex(DbHelper.RES_ID)));
                resturantModel.setName(mCursor.getString(mCursor.getColumnIndex(DbHelper.RES_NAME)));
                resturantModel.setIco(mCursor.getString(mCursor.getColumnIndex(DbHelper.RES_ICO)));
                resturantModel.setPhone(mCursor.getString(mCursor.getColumnIndex(DbHelper.RES_PHONE)));
                resturantModel.setX_axis(mCursor.getDouble(mCursor.getColumnIndex(DbHelper.RES_X_AXIS)));
                resturantModel.setY_axis(mCursor.getDouble(mCursor.getColumnIndex(DbHelper.RES_Y_AXIS)));
                resturantModels.add(resturantModel);
            }while (mCursor.moveToNext());
        }
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
        if (id == R.id.action_add_resturant) {
            startActivity(new Intent(MainActivity.this,DashboardAddActivity.class));
        }else  if (id == R.id.action_remove_resturant) {
            startActivity(new Intent(MainActivity.this,DashboardRemoveActivity.class));
        }else  if (id == R.id.action_update_resturant) {
            startActivity(new Intent(MainActivity.this, DashboardUpdateActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_add_resturant) {
            startActivity(new Intent(MainActivity.this,DashboardAddActivity.class));
        } else if (id == R.id.nav_update_resturant) {
            startActivity(new Intent(MainActivity.this, DashboardUpdateActivity.class));
        } else if (id == R.id.nav_remove_resturant) {
            startActivity(new Intent(MainActivity.this,DashboardRemoveActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
