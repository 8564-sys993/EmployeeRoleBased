package com.EmployeeDataRoleBased.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EmployeeDataRoleBased.demo.DummyData.Data;
import com.EmployeeDataRoleBased.demo.Entity.Employee;
import com.EmployeeDataRoleBased.demo.Security.JwtUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private List<Employee> employees = Data.getEmployees();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> body) {

        Long employeeId = Long.valueOf(body.get("employeeId").toString());
        String password = body.get("password").toString();

        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return ResponseEntity.status(401).body("Employee not found");
        }

        if (!employee.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Wrong password");
        }

        String token = jwtUtil.generateToken(employee.getId(), employee.getRole());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "name", employee.getName(),
                "role", employee.getRole()
        ));
    }
}
