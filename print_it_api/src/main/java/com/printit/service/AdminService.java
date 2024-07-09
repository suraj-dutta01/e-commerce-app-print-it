package com.printit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printit.dao.AdminDao;
import com.printit.dto.AdminRequest;
import com.printit.dto.AdminResponse;
import com.printit.dto.EmailConfiguration;
import com.printit.dto.ResponseStructure;
import com.printit.exceptions.AdminNotFoundException;
import com.printit.model.Admin;
import com.printit.util.AccountStatus;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private PrintitApiMailSenderService mailService;
	@Autowired
	private LinkGenerationService linkGenerationService;
	@Autowired
	private EmailConfiguration emailConfiguration;
	
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest,HttpServletRequest request){
	       ResponseStructure<AdminResponse> structure=new ResponseStructure<>();
	       Admin admin=mapToAdmin(adminRequest);
	       admin.setStatus(AccountStatus.IN_ACTIVE.toString());
	       admin=adminDao.saveAdmin(admin);
	       String activation_link=linkGenerationService.getAdminActivationLink(admin, request);
	       emailConfiguration.setToAddress(admin.getEmail());
	       emailConfiguration.setSubject("ACTIVATE YOUR ACCOUNT");
	       emailConfiguration.setText("Dear Admin, Please activate your account by clicking the link : "+activation_link);
	       structure.setMessage(mailService.sendMail(emailConfiguration));
	       structure.setData(mapToAdminResponse(admin));
	       structure.setStatusCode(HttpStatus.CREATED.value());
	       return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest,long id){
		ResponseStructure<AdminResponse>structure=new ResponseStructure<>();
		Optional<Admin> resAdmin=adminDao.findById(id);
		if(resAdmin.isPresent()) {
			Admin dbAdmin=resAdmin.get();
			dbAdmin.setName(adminRequest.getName());
			dbAdmin.setEmail(adminRequest.getEmail());
			dbAdmin.setPhone(adminRequest.getPhone());
			dbAdmin.setPassword(adminRequest.getPassword());
			structure.setMessage("Admin Updated");
			structure.setData(mapToAdminResponse(adminDao.saveAdmin(dbAdmin)));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AdminNotFoundException("Admin Id Is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<AdminResponse>> findById(long id){
		ResponseStructure<AdminResponse>structure=new ResponseStructure<>(); 
		Optional<Admin> resAdmin=adminDao.findById(id);
		if(resAdmin.isPresent()) {
			structure.setMessage("Admin Found");
			structure.setData(mapToAdminResponse(resAdmin.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(long id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Admin> resAdmin=adminDao.findById(id);
		if(resAdmin.isPresent()) {
			adminDao.deleteAdmin(id);
			
			structure.setMessage("Admin Deleted");
			structure.setData("Admin is deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Admin id is Invalid");
		
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> verify(String email,String password){
		ResponseStructure<AdminResponse>structure=new ResponseStructure<>(); 
		Optional<Admin> resAdmin=adminDao.verifyAdmin(email, password);
		if(resAdmin.isPresent()) {
			structure.setMessage("Admin vetification successfull");
			structure.setData(mapToAdminResponse(resAdmin.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid email and password");
	} 
	public ResponseEntity<ResponseStructure<AdminResponse>> verify(long phone,String password){
		ResponseStructure<AdminResponse>structure=new ResponseStructure<>(); 
		Optional<Admin> resAdmin=adminDao.verifyAdmin(phone, password);
		if(resAdmin.isPresent()) {
			structure.setMessage("Admin vetification successfull");
			structure.setData(mapToAdminResponse(resAdmin.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid phone and password");
	} 
	
	public String activateAccount(String token) {
		Optional<Admin> resAdmin=adminDao.findByToken(token);
		if(resAdmin.isPresent()) {
			Admin dbAdmin=resAdmin.get();
			dbAdmin.setStatus(AccountStatus.ACTIVE.toString());
			dbAdmin.setToken(null);
			adminDao.saveAdmin(dbAdmin);
			return "Your Account has been Activated";
		}
		throw new AdminNotFoundException("Invalid Token");
	}
	
	private Admin mapToAdmin(AdminRequest adminRequest) {
		return Admin.builder().name(adminRequest.getName()).email(adminRequest.getEmail())
		      .password(adminRequest.getPassword()).phone(adminRequest.getPhone()).build();
	}
	private AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder().id(admin.getId()).name(admin.getName()).email(admin.getEmail())
				.password(admin.getPassword()).phone(admin.getPhone()).build();
	}

}
