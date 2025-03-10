package com.project.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString
public class Hotel {

	private String id;
	
	private String name;
	
	private String location;
	
	private String about;
}
