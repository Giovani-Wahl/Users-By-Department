package com.giovani.usersbydepartment.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;

    public UserRequestDto(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        registrationDate = entity.getRegistrationDate();
        updateDate = entity.getUpdateDate();
    }
}
