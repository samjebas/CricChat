package com.niit.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.SortingFocusTraversalPolicy;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

@RestController
public class UserDetailController {

	@Autowired
	UserDetailDAO userDAO;

	// ------------------CheckLogin-----------------
	@PostMapping(value = "/login")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail, HttpSession session) {

		UserDetail tempUser = (UserDetail) userDAO.getUser(userDetail.getLoginName());
		if (tempUser != null) {

			userDAO.updateOnlineStatus("Y", tempUser);

			session.setAttribute("userDetail", tempUser);

			return new ResponseEntity<UserDetail>(tempUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDetail>(userDetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ---------------------RegisterUser----------------------------------//
	@PostMapping(value = "/register")
	public ResponseEntity<UserDetail> registerUser(@Valid @RequestBody UserDetail user, Errors errors) {
if(errors.hasErrors()) {
	System.out.println("Validation Failure");
	return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
}
		user.setIsOnline("N");
		/* user.setLoginName("Samraj"); */
		user.setRole("ROLE_USER");
		if (userDAO.registerUser(user)) {
			return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		}

	}

	// ----------- Update User -----------------------------
	@PutMapping(value = "/updateUser/{loginName}")
	public ResponseEntity<String> updateUser(@PathVariable("loginame") String loginName,
			@RequestBody UserDetail userDetail) {
		System.out.println("In updating user " + loginName);
		UserDetail mUser = userDAO.getUser(loginName);
		if (mUser == null) {
			System.out.println("No user found with loginName " + loginName);
			return new ResponseEntity<String>("No user found", HttpStatus.NOT_FOUND);
		}

		/*
		 * mUser.setEmailId(userDetail.getEmailId());
		 * mUser.setMobileNo(userDetail.getMobileNo());
		 * mUser.setAddress(userDetail.getAddress());
		 */
		mUser.setMobileNo(userDetail.getMobileNo());
		userDAO.updateUser(mUser);
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}

	// --------------------- List Users --------------------------
	@GetMapping(value = "/listUsers")
	public ResponseEntity<List<UserDetail>> listUsers() {
		List<UserDetail> listUsers = userDAO.listUsers();
		if (listUsers.size() != 0) {
			return new ResponseEntity<List<UserDetail>>(listUsers, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDetail>>(listUsers, HttpStatus.NOT_FOUND);
	}

	// --------------------- Get User ----------------------
	@GetMapping(value = "/getUser/{loginName}")
	public ResponseEntity<UserDetail> getUser(@PathVariable("loginName") String loginName) {
		UserDetail user = userDAO.getUser(loginName);
		if (user == null) {
			System.out.println("No user found");
			return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
		}
	}

	// -----------------------Delete user-----------------------
	@DeleteMapping(value = "/deleteUser/{loginName}")
	public ResponseEntity<String> deleteUser(@PathVariable("loginName") String loginName) {
		System.out.println("In delete user" + loginName);
		UserDetail user = userDAO.getUser(loginName);
		if (user == null) {
			System.out.println("No user with LoginName " + loginName + " found to delete");
			return new ResponseEntity<String>("No user found to delete", HttpStatus.NOT_FOUND);
		} else {
			userDAO.deleteuser(user);
			return new ResponseEntity<String>("User with LoginName " + loginName + " deleted successfully",
					HttpStatus.OK);
		}
	}
	// --------------------------------------Logout
	// -----------------------------------------
	/*
	 * @PostMapping(value="/logout") public ResponseEntity<UserDetail>
	 * logout(@RequestBody UserDetail userDetail,HttpSession session) {
	 * if(userDAO.checkUser(userDetail)) { UserDetail
	 * tempUser=(UserDetail)userDAO.getUser(userDetail.getLoginName());
	 * userDAO.updateOnlineStatus("N", tempUser); return new
	 * ResponseEntity<UserDetail>(tempUser,HttpStatus.OK); } else { return new
	 * ResponseEntity<UserDetail>(userDetail,HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
