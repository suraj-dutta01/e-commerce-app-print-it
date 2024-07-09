package com.printit.service;
import static com.printit.util.ApplicationConstants.ADMIN_VERIFY_LINK;
import static com.printit.util.ApplicationConstants.USER_VERIFY_LINK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.printit.dao.AdminDao;
import com.printit.dao.UserDao;
import com.printit.model.Admin;
import com.printit.model.User;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class LinkGenerationService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;

	public String getAdminActivationLink(Admin admin,HttpServletRequest servletRequest) {
		admin.setToken(RandomString.make(45));
		adminDao.saveAdmin(admin);
		String setUrl=servletRequest.getRequestURL().toString();
		return setUrl.replace(servletRequest.getServletPath(),ADMIN_VERIFY_LINK+admin.getToken());	
	}
	public String getUserActivationLink(User user,HttpServletRequest servletRequest) {
		user.setToken(RandomString.make(45));
        userDao.saveUser(user);
		String setUrl=servletRequest.getRequestURL().toString();
		return setUrl.replace(servletRequest.getServletPath(),USER_VERIFY_LINK+user.getToken());	
	}
}
