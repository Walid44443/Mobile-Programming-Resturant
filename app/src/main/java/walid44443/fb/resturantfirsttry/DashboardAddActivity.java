package walid44443.fb.resturantfirsttry;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashboardAddActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText resturant_name,resturant_ico,phone_num,x_axis,y_axis;
    Button choose_location,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_add);

        SQLiteOpenHelper dbHelper = new DbHelper(this);
        db =dbHelper.getWritableDatabase();

        resturant_name = findViewById(R.id.resturant_name);
        resturant_ico = findViewById(R.id.resturant_ico);
        phone_num = findViewById(R.id.resturant_phone);
        x_axis = findViewById(R.id.x_axis);
        y_axis = findViewById(R.id.y_axis);
        choose_location = findViewById(R.id.choose_location);

        Intent i = getIntent();
        if(i.getDoubleExtra("x_axis",0) !=0 && i.getDoubleExtra("y_axis",0) !=0){
            x_axis.setText(i.getDoubleExtra("x_axis",0)+"");
            y_axis.setText(i.getDoubleExtra("y_axis",0)+"");


            resturant_name.setText(i.getStringExtra("resturant_name")+"");
            resturant_ico.setText(i.getStringExtra("resturant_ico")+"");
            phone_num.setText(i.getStringExtra("phone_num")+"");

        }


        choose_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardAddActivity.this,ChooseLocationActivity.class);
                i.putExtra("resturant_name",resturant_name.getText().toString());
                i.putExtra("resturant_ico",resturant_ico.getText().toString());
                i.putExtra("phone_num",phone_num.getText().toString());

                startActivity(i);
            }
        });

        save = findViewById(R.id.insert);
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

                    long in = db.insert(DbHelper.DBTABLE, null, values);
                    if (in > 0) {
                        Toast.makeText(DashboardAddActivity.this, "Item has been inserted !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DashboardAddActivity.this,MainActivity.class));
                    }else
                        Toast.makeText(DashboardAddActivity.this, "error while inserting !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DashboardAddActivity.this, "error, please fill all data to insert",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
