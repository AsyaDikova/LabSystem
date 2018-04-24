package org.softuni.laboratory.employee.services;

import org.modelmapper.ModelMapper;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.softuni.laboratory.employee.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.findByUsername(username);

        if(employee == null) {
            throw new UsernameNotFoundException("Employee was not found.");
        }

        return employee;
    }

    @Override
    public boolean employeeExists(String username) {
        return this.employeeRepository.findByUsername(username) != null;
    }

    @Override
    public boolean save(RegisterEmployeeBindingModel employeeBindingModel) {
        Employee employee = this.modelMapper.map(employeeBindingModel, Employee.class);

        employee.setPassword(this.bCryptPasswordEncoder.encode(employee.getPassword()));

        this.employeeRepository.save(employee);

        return true;
    }
}
