package org.softuni.laboratory.employee.services;

import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {
    boolean save(RegisterEmployeeBindingModel user);

    boolean employeeExists(String username);
}
