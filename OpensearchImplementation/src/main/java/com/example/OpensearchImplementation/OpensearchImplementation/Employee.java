package com.example.OpensearchImplementation.OpensearchImplementation;

public class Employee {

    public String firstName;
    public String lastName;
 
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
 
    public String name() {
        return this.firstName + " " + this.lastName;
    }
 
    
}
