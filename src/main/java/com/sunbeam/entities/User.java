package com.sunbeam.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User extends BaseEntity {
	@Column(length = 20)
	public String name;
	@Column(length = 30)
	public String email;
	@Column(length = 20)
	public String password;
	@Column(length = 10, name = "mobile_no")
	private String mobileNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 30, name = "user_role")
	public Roles user;		
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings;

}
