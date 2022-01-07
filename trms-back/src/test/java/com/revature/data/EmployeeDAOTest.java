package com.revature.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Role;
import com.revature.data.postgres.EmployeePostgres;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class EmployeeDAOTest {
	private EmployeeDAO empDao = new EmployeePostgres();
	
	@BeforeAll
	public static void empSetup() {
		
		Employee emp = new Employee();
		
		Department dept = new Department();
		
		Employee sup = emp;
		
		Role role;
		role = new Role();
		role.setRoleId(2);
		role.setName("Supervisor");
				
		emp.setFirstName("first");
		emp.setLastName("last");
		emp.setUsername("usr");
		emp.setPassword("psw");
		emp.setRole(role);
		emp.setFunds(1000);
		emp.setSupervisor(sup);
		emp.setDepartment(dept);
	}
	

	@Test
	@Order(1)
	public void getAll() {
		Set<Employee> givenOutput = empDao.getAll();
		assertNotNull(givenOutput);
	}
	
	@Test
	@Order(1)
	public void getByIdWhenIdExists() {
		int idInput = 0;
		Employee idOutput = empDao.getById(idInput);
		assertEquals(1, idOutput.getEmpId());
	}
	
	@Test
	@Order(1)
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Employee idOutput = empDao.getById(idInput);
		assertNull(idOutput);
	}
	
	@Test
	@Order(1)
	public void getByUsernameWhenUsernameExists() {
		String usernameInput = "usr";
		Employee empOutput = empDao.getByUsername(usernameInput);
		assertEquals("usr", empOutput.getUsername());
	}
	
	@Test
	@Order(1)
	public void getByUsernameButUsernameDoesNotExist() {
		String usernameInput = "qwertyuiop";
		Employee empOutput = empDao.getByUsername(usernameInput);
		assertNull(empOutput);
	}
	
	@Test
	@Order(2)
	public void addNewEmployee() {
		Employee newEmp = new Employee();
		assertNotEquals(0, empDao.create(newEmp));
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Employee empUp = empDao.getById(0);
		empUp.setFirstName("Bob");
		empDao.update(empUp);
		assertEquals("Bob",empDao.getById(0).getFirstName());
	}
	
	@Test
	@Order(4)
	public void testDelete() {
		Employee empDel = empDao.getById(0);
		empDao.delete(empDel);
		assertNull(empDel);
	}
}
