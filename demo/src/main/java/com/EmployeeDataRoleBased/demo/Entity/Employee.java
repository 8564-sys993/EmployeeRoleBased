package com.EmployeeDataRoleBased.demo.Entity;

public class Employee {

    private Long id;
    private String name;
    private String department;
    private String email;
    private String phone;
    private String role;
    private String password; 

    public Employee(Long id, String name, String department,
                    String email, String phone, String role, String password) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public Long getId()           { return id; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }
    public String getEmail()      { return email; }
    public String getPhone()      { return phone; }
    public String getRole()       { return role; }
    public String getPassword()   { return password; }
}