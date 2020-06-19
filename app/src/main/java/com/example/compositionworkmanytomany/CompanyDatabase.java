package com.example.compositionworkmanytomany;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Employee.class, Hobby.class, EmployeeHobbyLink.class}, version = 1)
public abstract class CompanyDatabase extends RoomDatabase {
    private static final String TAG = "CompanyDatabase";
    public abstract EmployeeDao employeeDao();
    public abstract HobbiesDao hobbiesDao();
    public abstract EmployeeHobbyLinkDao employeeHobbyLinkDao();

    public static CompanyDatabase instance;

    public static synchronized CompanyDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, CompanyDatabase.class, "company_database")
                    .fallbackToDestructiveMigration().addCallback(initialCallBack).build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialAsyncTask(instance).execute();
        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void>{

        private EmployeeDao employeeDao;
        private HobbiesDao hobbiesDao;
        private EmployeeHobbyLinkDao employeeHobbyLinkDao;

        public InitialAsyncTask(CompanyDatabase db){
            employeeDao = db.employeeDao();
            hobbiesDao = db.hobbiesDao();
            employeeHobbyLinkDao = db.employeeHobbyLinkDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            employeeDao.insert(new Employee("Shimul", "shimul6680@gmail.com", new Address("Joypurhat", "Sabujnagar", "5000")));
            employeeDao.insert(new Employee("Ranjit Kumar PK", "ranjit@gmail.com", new Address("Rajshahi", "Kalibari Road", "2100")));
            employeeDao.insert(new Employee("Umme Farhana Sultana Polli", "polli6680@gmail.com", new Address("Jessore", "Jhekorgacha", "1900")));

            hobbiesDao.insert(new Hobby(10, "Soccer"));
            hobbiesDao.insert(new Hobby(5, "Cricket"));

            employeeHobbyLinkDao.insert(new EmployeeHobbyLink(employeeDao.getEmployeeIdByName("Shimul"), hobbiesDao.getHobbiesIdByName("Soccer")));
            employeeHobbyLinkDao.insert(new EmployeeHobbyLink(employeeDao.getEmployeeIdByName("Ranjit Kumar PK"), hobbiesDao.getHobbiesIdByName("Cricket")));
            employeeHobbyLinkDao.insert(new EmployeeHobbyLink(employeeDao.getEmployeeIdByName("Umme Farhana Sultana Polli"), hobbiesDao.getHobbiesIdByName("Cricket")));

            return null;
        }
    }
}
