package com.server.carelink.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosesWithIdDTO {
	  private Long id;
	    private String treatments;
		private String allergies;
		private Long doctors;
}
