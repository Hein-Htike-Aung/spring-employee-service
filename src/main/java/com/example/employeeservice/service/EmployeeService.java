package com.example.employeeservice.service;

import com.example.employeeservice.exception.UserNotFoundException;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @PostConstruct
    private void addEmployees(){
        Employee e1 = new Employee();
        e1.setName("lucifer");
        e1.setEmail("lu@gmail.com");
        e1.setJobTitle("Tech Lead");
        e1.setPhone("09442342323424");
        e1.setImageUrl("https://bootdey.com/img/Content/avatar/avatar7.png");

        Employee e2 = new Employee();
        e2.setName("Mike");
        e2.setEmail("mike@gmail.com");
        e2.setJobTitle("PM");
        e2.setPhone("0933342323424");
        e2.setImageUrl("https://bootdey.com/img/Content/avatar/avatar3.png");

        addEmployee(e1);
        addEmployee(e2);
    }

    public Employee addEmployee(Employee employee){

        employee.setEmployeeCode(UUID.randomUUID().toString());
        return this.employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return this.employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return this.employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        this.employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployeeId(Long id){
        return this.employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
}
