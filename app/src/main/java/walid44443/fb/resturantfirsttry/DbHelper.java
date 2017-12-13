package walid44443.fb.resturantfirsttry;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by walid4444 on 11/23/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    static final String RES_ID = "_id";
    static final String RES_NAME = "res_name";
    static final String RES_ICO = "res_ico";
    static final String RES_X_AXIS = "res_x_axis";
    static final String RES_Y_AXIS = "res_y_axis";
    static final String RES_PHONE = "res_phone";

    static final int DB_VERSION = 1;
    static final String DBTABLE = "resturant";
    static final String DBNAME = "res.db";


    static final String SQL_QUERY = "create table "
            + DBTABLE +"("+RES_ID+" integer primary key autoincrement," +
            RES_NAME+" text not null ," +
            RES_ICO+" text ," +
            RES_X_AXIS+" double not null," +
            RES_Y_AXIS+" double not null," +
            RES_PHONE+" text not null" +
            ");";


    public DbHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_QUERY);
        }catch (SQLException e){
            Log.e("DbHelper",e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DBTABLE);
        onCreate(db);
    }
}
