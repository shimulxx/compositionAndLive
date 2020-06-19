package com.example.compositionworkmanytomany;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeHobbyLinkDao {
    @Insert
    void insert(EmployeeHobbyLink employeeHobbyLink);
    @Query("select employees._id, employees.name, employees.email, employees.city, employees.street, employees.zip_code " +
    "from employees inner join employee_hobby_link on employees._id = employee_hobby_link.employee_id " +
    "inner join hobbies on employee_hobby_link.hobby_id = hobbies._id where hobbies.name = :hobby_name")
    List<Employee> getEmployeesByHobby(String hobby_name);

    @Query("select employees._id, employees.name, employees.email, employees.city, employees.street, employees.zip_code " +
            "from employees inner join employee_hobby_link on employees._id = employee_hobby_link.employee_id " +
            "inner join hobbies on employee_hobby_link.hobby_id = hobbies._id where hobbies.name = :hobby_name")
    LiveData<List<Employee>> getEmployeesByHobbyLive(String hobby_name);
}
