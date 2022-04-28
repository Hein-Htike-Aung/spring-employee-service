package com.example.employeeservice.resources;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeService service;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.service.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable("id") Long id
    ) {
        Employee employee = this.service.findEmployeeId(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee
    ) {
        Employee e = this.service.addEmployee(employee);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable("id") Long id
    ) {
        employee.setId(id);
        Employee e = this.service.updateEmployee(employee);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable("id") Long id
    ) {
        this.service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
