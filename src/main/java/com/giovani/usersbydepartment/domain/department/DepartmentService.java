package com.giovani.usersbydepartment.domain.department;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Transactional
    public Department save(Department department){
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
}
