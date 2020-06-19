package com.example.compositionworkmanytomany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CompanyDatabase companyDatabase;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        new DatabaseAsyncTask().execute();
        LiveData<List<Employee>> listLiveData = companyDatabase.employeeHobbyLinkDao().getEmployeesByHobbyLive("Cricket");
        listLiveData.observe(MainActivity.this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                String tempString = "";
                for(Employee employee : employees){
                    tempString += "\nName: " + employee.getName() + "\nEmail: " + employee.getEmail() + "\n\tCity: " + employee.getAddress().getCity() +
                            "\n\tStreet: " + employee.getAddress().getStreet() + "\n\tZipcode: " + employee.getAddress().getZipcode() + "\n************";
                }
                textView.setText(tempString);
            }
        });
    }

    private class DatabaseAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            companyDatabase = CompanyDatabase.getInstance(MainActivity.this);
        }

        @Override
        protected String doInBackground(Void... voids) {
            /*String employeeString = "";
            List<Employee> allEmployees = companyDatabase.employeeDao().getAllEmployees();
            for(Employee employee : allEmployees){
                employeeString += "\nName: " + employee.getName() + "\nEmail: " + employee.getEmail() + "\n\tCity: " + employee.getAddress().getCity() +
                        "\n\tStreet: " + employee.getAddress().getStreet() + "\n\tZipcode: " + employee.getAddress().getZipcode() + "\n************";
            }

            String abstractEmployeeString = "";

            List<AbstractEmployee> abstractEmployees = companyDatabase.employeeDao().getAllAbstractEmployee();
            for(AbstractEmployee abstractEmployee: abstractEmployees){
                abstractEmployeeString += "\nName: " + abstractEmployee.getName() + "\nEmail: " + abstractEmployee.getEmail() + "\n**********";
            }*/
            String tempString = "";
            List<Employee> jazzLovers = companyDatabase.employeeHobbyLinkDao().getEmployeesByHobby("Cricket");
            for(Employee employee : jazzLovers){
                tempString += "\nName: " + employee.getName() + "\nEmail: " + employee.getEmail() + "\n\tCity: " + employee.getAddress().getCity() +
                        "\n\tStreet: " + employee.getAddress().getStreet() + "\n\tZipcode: " + employee.getAddress().getZipcode() + "\n************";
            }
            return tempString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // textView.setText(s);
        }
    }
}
