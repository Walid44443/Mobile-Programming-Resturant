package walid44443.fb.resturantfirsttry;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashboardUpdate2Activity extends AppCompatActivity {


    SQLiteDatabase db;
    EditText resturant_name,resturant_ico,phone_num,x_axis,y_axis,id;
    Button choose_location,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_update2);

        final SQLiteOpenHelper dbHelper = new DbHelper(this);
        db =dbHelper.getWritableDatabase();

        resturant_name = findViewById(R.id.resturant_name_update);
        resturant_ico = findViewById(R.id.resturant_ico_update);
        phone_num = findViewById(R.id.resturant_phone_update);
        x_axis = findViewById(R.id.x_axis_update);
        y_axis = findViewById(R.id.y_axis_update);
        id = findViewById(R.id.resturant_id_update);

        choose_location = findViewById(R.id.choose_location_update);

        Intent i = getIntent();
        if(i.hasExtra("x_axis") && i.hasExtra("y_axis")){
            x_axis.setText(i.getDoubleExtra("x_axis",0)+"");
            y_axis.setText(i.getDoubleExtra("y_axis",0)+"");


            resturant_name.setText(i.getStringExtra("resturant_name")+"");
            resturant_ico.setText(i.getStringExtra("resturant_ico")+"");
            phone_num.setText(i.getStringExtra("phone_num")+"");
            id.setText(i.getIntExtra("id",0)+"");

        }


        choose_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardUpdate2Activity.this,ChooseLocationActivity.class);
                i.putExtra("resturant_name",resturant_name.getText().toString());
                i.putExtra("resturant_ico",resturant_ico.getText().toString());
                i.putExtra("phone_num",phone_num.getText().toString());
                i.putExtra("id",Integer.parseInt(id.getText().toString()));

                startActivity(i);
            }
        });

        save = findViewById(R.id.update);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resturant_name.getText().toString().isEmpty() && !x_axis.getText().toString().isEmpty()&& !y_axis.getText().toString().isEmpty()) {
                    ContentValues values = new ContentValues();
                    values.put(DbHelper.RES_NAME, resturant_name.getText().toString());
                    values.put(DbHelper.RES_ICO, resturant_ico.getText().toString());
                    values.put(DbHelper.RES_PHONE, phone_num.getText().toString());

                    values.put(DbHelper.RES_X_AXIS, Double.parseDouble(x_axis.getText().toString()));
                    values.put(DbHelper.RES_Y_AXIS, Double.parseDouble(y_axis.getText().toString()));

                    long in = db.update(DbHelper.DBTABLE, values,DbHelper.RES_ID+"= ?",new String[]{id.getText().toString()});
                    if (in > 0) {
                        Toast.makeText(DashboardUpdate2Activity.this, "Item has been updated !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DashboardUpdate2Activity.this,MainActivity.class));
                    }else
                        Toast.makeText(DashboardUpdate2Activity.this, "error while inserting !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DashboardUpdate2Activity.this, "error, please fill all data to insert",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
