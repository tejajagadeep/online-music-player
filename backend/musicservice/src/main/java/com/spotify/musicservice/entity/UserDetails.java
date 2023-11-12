package com.jts.entity;

import java.io.Serializable;


@Entity
@Table(name = "USER_DETAILS")
public class UserDetails implements Serializable {
	private static final long serialVersionUID = 3937414011943770889L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ACCESS_TOKEN")
	private String accessToken;

	@Column(name = "REFRESH_TOKEN")
	private String refreshToken;

	@Column(name = "REF_ID")
	private String refId;
	
	// More information as per your need

}
