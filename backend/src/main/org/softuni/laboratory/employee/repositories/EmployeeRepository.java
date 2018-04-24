package org.softuni.laboratory.employee.repositories;

import org.softuni.laboratory.employee.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    Employee findByUsername(String username);
}
