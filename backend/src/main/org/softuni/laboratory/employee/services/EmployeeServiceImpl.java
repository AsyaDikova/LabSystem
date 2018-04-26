package org.softuni.laboratory.employee.services;

import org.modelmapper.ModelMapper;
import org.softuni.laboratory.core.repositories.RoleRepository;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.softuni.laboratory.employee.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
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

        employee.setRoles(List.of(roleRepository.findByAuthority("ROLE_EMPLOYEE")));

        employee.setPassword(this.bCryptPasswordEncoder.encode(employee.getPassword()));

        this.employeeRepository.save(employee);

        return true;
    }

    @Override
    public Employee findByUsername(String username){
        return this.employeeRepository.findByUsername(username);
    }

    @Override
    public List<String> getAllEmployeeByUsername() {
        List<Employee> allEmployee = this.employeeRepository.findAll();
        List<String> allEmployeeUsername = allEmployee.stream().map( e -> e.getUsername()).collect(Collectors.toList());
        return allEmployeeUsername;
    }
}
