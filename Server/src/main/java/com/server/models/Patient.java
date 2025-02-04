package com.server.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="patients")
public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotEmpty
	@Size(min=8,max=8,message="the emerg contact is not valid")
	private String emergContact;
 @OneToOne
 @JoinColumn(name = "user_id", referencedColumnName = "id")
 private User user; // Relationship with User entity

 @OneToOne
 @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
 private Diagnoses diagnose;
<<<<<<< HEAD
}
=======
 
 @OneToMany(fetch=FetchType.LAZY,mappedBy="patient")
	private List<Appointment> appointments;

}
 
>>>>>>> 834a20af0fade937d480243e315905c3d35ca4d8
