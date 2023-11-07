package com.giovani.usersbydepartment.controllers;

import com.giovani.usersbydepartment.domain.department.Department;
import com.giovani.usersbydepartment.domain.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping
    public ResponseEntity<Object> saveDepartment(@RequestBody Department department){
        if (departmentService.existsDepartmentByName(department.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Department already exists.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(department));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable (value = "id")UUID id, @RequestBody Department department){
        Optional<Department> departmentOptional = departmentService.findById(id);
        if (departmentOptional.isPresent()){
            department.setId(departmentOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(departmentService.save(department));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not Found.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable (value = "id")UUID id){
        Optional<Department> departmentOptional = departmentService.findById(id);
        if (departmentOptional.isPresent()){
            departmentService.delete(departmentOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Department deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not Found.");
    }
    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartments(@PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findAll(pageable));
    }
}
