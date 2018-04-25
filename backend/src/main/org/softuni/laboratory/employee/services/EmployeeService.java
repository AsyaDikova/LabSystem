package org.softuni.laboratory.employee.services;

import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {
    boolean save(RegisterEmployeeBindingModel user);

    boolean employeeExists(String username);

    Employee findByUsername(String username);
}
