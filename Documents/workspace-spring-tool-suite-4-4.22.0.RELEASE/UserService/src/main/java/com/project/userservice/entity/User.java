package com.project.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Builder
@Table(name = "micro_users")
@ToString
public class User {

	@Id
	private String user_id;
	
	private String name;
	
	private String email;
	
	private String about;
	
	@Transient
	private List<Rating> ratings;

}
