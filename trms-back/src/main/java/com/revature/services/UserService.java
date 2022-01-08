package com.revature.services;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;

public interface UserService {
	// services represent business logic - actual user activities.
	// what can a user do?
	public Employee register(Employee newUser) throws UsernameAlreadyExistsException;
	public Employee logIn(String username, String password) throws IncorrectCredentialsException;
	public Employee getUserById(int id);
	public Employee updateUser(Employee userToUpdate);
	public Set<Reimbursement> viewReimbursementsByEmpId(Employee requestor);
	public Set<Reimbursement> searchReimbursementsByStatus(Status status);
}
