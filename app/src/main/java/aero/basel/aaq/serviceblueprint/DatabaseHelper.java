package aero.basel.aaq.serviceblueprint;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DatabaseHelper extends SQLiteOpenHelper{

    // путь к базе данных вашего приложения
    public static final String DB_PATH= "/data/data/aero.basel.aaq.serviceblueprint/databases/";
    public static final String DB_NAME = "basel.db";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase myDataBase;
    private final Context mContext;
    public static final String DATABASE_USERS_TABLE = "users";
    public static final String USERNAME_USERS_COLUMN = "username";
    public static final String PASSWORD_USERS_COLUMN = "password";
    public static final String REALNAME_USERS_COLUMN = "realname";
    public static final String DESCRIPTION_USERS_COLUMN = "description";

    public static final String DATABASE_AGENTS_TABLE = "agents";
    public static final String NAME_AGENTS_COLUMN = "name";
    public static final String SHIFT_AGENTS_COLUMN = "shift";
    public static final String AIRPORT_AGENTS_COLUMN = "airport";
    public static final String SERVICE_AGENTS_COLUMN = "service";


    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     *
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if(!dbExist){
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //база еще не существует
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     * */
    private void copyDataBase() throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(mContext, "Обновляемся с версии " + oldVersion + " на версию " + newVersion, Toast.LENGTH_LONG).show();
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);
        mContext.deleteDatabase("basel.db");
        onCreate(db);
        }



    // Здесь можно добавить вспомогательные методы для доступа и получения данных из БД
    // вы можете возвращать курсоры через "return myDataBase.query(....)", это облегчит их использование
    // в создании адаптеров для ваших view
}