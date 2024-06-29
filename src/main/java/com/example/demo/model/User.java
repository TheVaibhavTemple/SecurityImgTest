package com.example.demo.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "MyUsers")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
//	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	Integer id;
	String name;
	String address;
	Integer age;
	Long phoneNo;
	String email;
	String password;
	
	 @Lob
	 @Column(length = 1000)
	 private byte[] imageData;
	 
	 @ElementCollection(fetch = FetchType.EAGER)
		@CollectionTable(name="rolestab",
		joinColumns = @JoinColumn(name="id"))
		@Column(name="urole")
		private Set<String> usrRole;
//	@ElementCollection
//	List<String> stuLang;
	
}
