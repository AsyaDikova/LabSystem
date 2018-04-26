package org.softuni.laboratory.employee.services;

import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
    boolean save(RegisterEmployeeBindingModel user);

    boolean employeeExists(String username);

    Employee findByUsername(String username);

    List<String> getAllEmployeeByUsername();
}
