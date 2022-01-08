package com.revature.services;

import java.util.Set;
import java.util.stream.Collectors;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.data.postgres.ReimbursementPostgres;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;

public class UserServiceImpl implements UserService {
	private EmployeeDAO personDao = new EmployeePostgres();
	private ReimbursementDAO reimbursementDao = new ReimbursementPostgres();

	@Override
	public Employee register(Employee newUser) throws UsernameAlreadyExistsException {
		int newId = personDao.create(newUser);
		if (newId > 0) {
			newUser.setEmpId(newId);
			return newUser;
		} else if (newId == -1) {
			throw new UsernameAlreadyExistsException();
		}
		return null;
	}

	@Override
	public Employee logIn(String username, String password) throws IncorrectCredentialsException {
		Employee personFromDatabase = personDao.getByUsername(username);
		if (personFromDatabase != null && personFromDatabase.getPassword().equals(password)) {
			return personFromDatabase;
		} else {
			throw new IncorrectCredentialsException();
		}
	}
	
	@Override
	public Employee getUserById(int id) {
		return personDao.getById(id);
	}
	
	@Override
	public Employee updateUser(Employee userToUpdate) {
		if (personDao.getById(userToUpdate.getEmpId()) != null) {
			personDao.update(userToUpdate);
			userToUpdate = personDao.getById(userToUpdate.getEmpId());
			return userToUpdate;
		}
		return null;
	}

	@Override
	public Set<Reimbursement> viewReimbursementsByEmpId(Employee requestor) {
		return reimbursementDao.getByRequestor(requestor);
	}

//	@Override
	public Set<Reimbursement> searchReimbursementsByStatus(Status status) {
//		Set<Reimbursement> reimbursements = reimbursementDao.getByStatus(status);
//	
//		reimbursements = reimbursements.stream()
//				.filter(status -> status.getName().toLowerCase.contains(status.toLowerCase()))
//				.collect(Collectors.toSet());
//		
//		return reimbursements;
	return null;
	}
}
