package com.server.carelink.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosesDTO {
    private Long id;
    private String treatments;
	private String allergies;
	private List<Long> doctors;
}
