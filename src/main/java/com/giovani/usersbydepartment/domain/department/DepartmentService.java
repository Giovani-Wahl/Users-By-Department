package com.giovani.usersbydepartment.domain.department;

import com.giovani.usersbydepartment.domain.user.User;
import com.giovani.usersbydepartment.domain.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserService userService;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, UserService userService) {
        this.departmentRepository = departmentRepository;
        this.userService = userService;
    }
    @Transactional
    public Department save(Department department){
        if (department.getUserDepartment()==null){
            return departmentRepository.save(department);
        }
        return departmentRepository.save(department);
    }
    @Transactional
    public void delete(Department department){
        departmentRepository.delete(department);
    }
    public Page<Department> findAll(Pageable pageable){
        return departmentRepository.findAll(pageable);
    }
    public Optional<Department> findById(UUID id){
        return departmentRepository.findById(id);
    }
    public boolean existsDepartmentByName(String name){
        return departmentRepository.existsDepartmentByName(name);
    }
}
