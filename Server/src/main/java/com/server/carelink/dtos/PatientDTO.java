package com.server.carelink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String emergContact;
    private UserDTO user;
    private DiagnosesDTO diagnose;
}
