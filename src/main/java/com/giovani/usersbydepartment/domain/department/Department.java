package com.giovani.usersbydepartment.domain.department;

import com.giovani.usersbydepartment.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_DEPARTMENT")
public class Department implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false,unique = true,length = 30)
    private String name;
    @OneToMany(mappedBy = "department")
    private Set<User> userDepartment = new HashSet<>();
}
