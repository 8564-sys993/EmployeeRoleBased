package com.EmployeeDataRoleBased.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeDataRoleBased.demo.DummyData.Data;
import com.EmployeeDataRoleBased.demo.Entity.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private List<Employee> employees = Data.getEmployees();

    @GetMapping("/{targetId}")
    public ResponseEntity<?> getEmployeeProfile(
            @PathVariable Long targetId,
            Authentication authentication) { 

        Long requesterId = (Long) authentication.getPrincipal();
        String requesterRole = authentication.getAuthorities()
                .iterator().next()
                .getAuthority()
                .replace("ROLE_", "");

        Employee target = findById(targetId);
        if (target == null) {
            return ResponseEntity.notFound().build();
        }

        if (requesterRole.equals("MANAGER")) {
            return ResponseEntity.ok(target);
        }

        
        if (requesterRole.equals("EMPLOYEE")) {
            if (!requesterId.equals(targetId)) {
                return ResponseEntity.status(403)
                        .body("Access Denied: You can only view your own profile.");
            }
            return ResponseEntity.ok(target);
        }

        return ResponseEntity.status(403).body("Access Denied");
    }

    private Employee findById(Long id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }
}