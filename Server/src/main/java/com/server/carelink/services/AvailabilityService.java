package com.server.carelink.services;

import java.util.List;
import java.util.stream.Collectors;

import com.server.carelink.dtos.AvailabilityDTO;
import com.server.carelink.models.Availability;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.server.carelink.repositories.AvailabilityRepo;
import com.server.carelink.repositories.DoctorRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityService {
	private final ModelMapper modelMapper;
	@Autowired
	AvailabilityRepo avRepo;
	@Autowired
	JwtService jwtService;
	@Autowired
	DoctorRepo doctorRepo;
	
	public List<AvailabilityDTO> getAll(){
		return avRepo.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}


	public AvailabilityDTO getOneAv(Long id) {
		return avRepo.findByDoctorId(id)
				.map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Availability not found"));
	}
	
	
	public AvailabilityDTO createAv(@RequestBody Availability av, Long id ) {
		if(doctorRepo.findById(id).isPresent()) {
			av.setDoctor(doctorRepo.findById(id).get());
			return convertToDTO(avRepo.save(av));
		}
		
		throw new RuntimeException("Doctor not found");
	}
	
	
	
	
	public  AvailabilityDTO convertToDTO(Availability av) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(av, AvailabilityDTO.class);
	}
	
	
	
}
