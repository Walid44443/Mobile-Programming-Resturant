package walid44443.fb.resturantfirsttry;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashboardRemoveActivity extends AppCompatActivity {
    List<ResturantModel> resturantModels = new ArrayList<>();
    SQLiteDatabase db;
    Button Select;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_remove);

        //db create
        SQLiteOpenHelper dbHelper = new DbHelper(this);
        db =dbHelper.getWritableDatabase();

        final Spinner names = findViewById(R.id.spinner_del);
        Select = findViewById(R.id.select_to_delete);
        CursorToList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ResturantToString(resturantModels));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        names.setAdapter(adapter);

        Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(names.getSelectedItemPosition() >= 0) {
                    long in = db.delete(DbHelper.DBTABLE,DbHelper.RES_ID+"= ?",new String[]{String.valueOf(resturantModels.get(names.getSelectedItemPosition()).getId())});
                    if (in > 0) {
                        Toast.makeText(DashboardRemoveActivity.this, "Item has been Deleted !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DashboardRemoveActivity.this,MainActivity.class));
                        CursorToList();
                        adapter = new ArrayAdapter(DashboardRemoveActivity.this, android.R.layout.simple_list_item_1,ResturantToString(resturantModels));
                        names.setAdapter(adapter);
                    }else
                        Toast.makeText(DashboardRemoveActivity.this, "error while deleting !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DashboardRemoveActivity.this, "error, please fill all data to delete",Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private List<String> ResturantToString(List<ResturantModel> resturantModels){
        List<String> names =new ArrayList<>();
        for (ResturantModel resturantModel :resturantModels){
            names.add(resturantModel.getName());
        }
        return names;
    }
}
