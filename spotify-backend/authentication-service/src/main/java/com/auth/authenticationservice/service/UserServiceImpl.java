package com.auth.authenticationservice.service;


import com.auth.authenticationservice.model.UserDetails;
import com.auth.authenticationservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
	private final UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public boolean loginUser(String username, String password) {

		UserDetails user = userRepo.validateUser(username, password);
		if(user!=null)
		{
			log.info(username+"--service--"+password);
			return true;
		}

		return false;
	}

	@Override
	public String getRoleByUserAndPass(String username, String password) {

		String r=userRepo.GetRole(username, password);
		log.info(r+"role---");
		return r;
	}
}














