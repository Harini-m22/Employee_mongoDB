package com.example.employee_api.controller;
import com.example.employee_api.model.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import com.example.employee_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/displayAll";
    }

    @GetMapping("/displayAll")
    public String displayAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/display/{id}")
    public String displayEmployeeById(@PathVariable String id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }


}