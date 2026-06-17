package com.reaplm.classifiedsapi.model;


import java.security.Timestamp;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="users", schema = "auth")
public class User implements UserDetails {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(name = "password_hash", nullable = false)
	private String password;
	
	@Column(name = "is_email_verified")
	private boolean isEmailVerified;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "failed_login_attempts")
	private int failedLoginAttempts;
	
	@Column(name = "last_login_at")
	private OffsetDateTime lastLoginAt;
	
	@Column(name = "created_at")
	private OffsetDateTime createdAt;
	
	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;

	
	@Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
	@Override public String getPassword() { return password; }
	@Override public String getUsername() { return username; }
	@Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
