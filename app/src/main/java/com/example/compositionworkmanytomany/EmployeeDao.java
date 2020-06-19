package com.example.compositionworkmanytomany;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    void insert (Employee employee);

    @Query("select *from employees")
    List<Employee> getAllEmployees();

    @Query("select name, email from employees")
    List<AbstractEmployee> getAllAbstractEmployee();

    @Query("select _id from employees where name=:name")
    int getEmployeeIdByName(String name);
}
