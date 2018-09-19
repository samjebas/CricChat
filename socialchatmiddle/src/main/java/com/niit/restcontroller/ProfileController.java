package com.niit.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfileUpdateDAO;
import com.niit.model.Profile;

import com.niit.model.UserDetail;

@RestController
public class ProfileController {
	
	
	@Autowired
	ProfileUpdateDAO profileUpdateDAO;
	
		@PostMapping(value="/doUpload")
	public ResponseEntity<?> uploadPicture(@RequestParam(value="file")CommonsMultipartFile fileupload,HttpSession session)
	{
	
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		
		if(userDetail==null) 
		{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else
		{
			System.out.println("Uploading the picture..");
			Profile profilePicture=new Profile();
			profilePicture.setLoginName(userDetail.getLoginName());
			profilePicture.setImage(fileupload.getBytes());
			profileUpdateDAO.saveProfilePic(profilePicture);
			System.out.println("Successfully uploaded..!!");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{loginName}")
	public @ResponseBody byte[] getProfilePic(@PathVariable("loginName") String loginName, HttpSession session)
	{
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		
		if(userDetail==null)
		{
			return null;
		}
		else
		{
		Profile profilePicture = profileUpdateDAO.getProfilePicture(loginName);
		
		if(profilePicture!= null)
		{
			return profilePicture.getImage();
		}
		else
		{
			return null;
		}
	}
	}
	
}
