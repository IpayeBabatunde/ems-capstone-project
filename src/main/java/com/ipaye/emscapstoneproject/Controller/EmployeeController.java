package com.ipaye.emscapstoneproject.Controller;

import com.ipaye.emscapstoneproject.Exception.EmployeeNotFoundException;
import com.ipaye.emscapstoneproject.Model.Employee;
import com.ipaye.emscapstoneproject.Repository.EmployeeRepository;
import com.ipaye.emscapstoneproject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeController(){
//        employeeList.add(new Employee(1L, "Ipaye", "james", "ipaye@gmail.com", "civil engineer"));
//        employeeList.add(new Employee(2L, "mariam", "eleanor", "ipaye@gmail.com", "civil engineer"));
//        employeeList.add(new Employee(3L, "Ricky", "joe", "ipaye@gmail.com", "civil engineer"));
//        employeeList.add(new Employee(4L, "Stella", "Marris", "ipaye@gmail.com", "civil engineer"));
//        employeeList.add(new Employee(5L, "Peter", "joe", "ipaye@gmail.com", "civil engineer"));


    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("Retrieved Employees: " + employees);
        return employees;

    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();

    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee createdEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        // replace the entire user resources

        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> partiallyUpdateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates){

        return ResponseEntity.ok(employeeService.partialUpdateUser(id, updates));
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee with ID " +id+ " has been deleted successfully.");
    }



    

}