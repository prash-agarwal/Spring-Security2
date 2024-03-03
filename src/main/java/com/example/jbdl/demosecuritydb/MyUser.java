package com.example.jbdl.demosecuritydb;
import org.springframework.data.annotation.Immutable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class MyUser implements UserDetails {
	
	private static final String delimiter = ",";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private String authorities;// authority we take input as COMMA separated  example -   DEVOPS_AUTH, DEVELOPER_AUTH

//	public static List<GrantedAuthority> createAuthorityList(String... authorities) {
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
//		for (String authority : authorities) {
//			grantedAuthorities.add(new SimpleGrantedAuthority("DEVOPS_AUTH"));
//		}
//		return grantedAuthorities;
//	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String authorityList[] = this.authorities.split(delimiter);
		return Arrays.stream(authorityList).map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
