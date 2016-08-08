package com.example.security;

import com.example.Users.User;
import com.example.Users.UserRepository;
import com.example.Users.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
		this.userRepository = userRepository;
		this.userRolesRepository=userRolesRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userRolesRepository.findRoleByUser(user);
//			List<UserRole> userRoles1=user.getUserRole();
//			List<String> userRoles= (List<String>) userRoles1.get(0);
			return new CustomUserDetails(user,userRoles);
		}
	}

}
