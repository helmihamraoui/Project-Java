package com.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String emergContact;
    //impport on use the userDto sof id
    /*private UserDTOWithoutId user;*/
}
