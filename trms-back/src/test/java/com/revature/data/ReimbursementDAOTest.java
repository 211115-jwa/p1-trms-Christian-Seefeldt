package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class ReimbursementDAOTest {
	ReimbursementDAO remDao = DAOFactory.getReimbursementDAO();
	private static Reimbursement rem;
	
	private static int genId;
	private static Employee emp;
	private static GradingFormat gf;
	private static EventType et;
	private static Status status;
	private static Role role;
	private static Employee sup;
	private static Department dept;
	
	@BeforeAll
	public static void ReimbursementSetup() {
		rem = new Reimbursement();
		rem.setRequestor(emp);
		rem.setEventDate(LocalDate.of(2000,01,01));
		rem.setEventTime(LocalTime.of(11,11,11));
		rem.setLocation("location");
		rem.setDescription("description");
		rem.setCost(1);
		rem.setGradingFormat(gf);
		rem.setEventType(et);
		rem.setStatus(status);
		rem.setSubmittedAt(LocalDateTime.now());
}
	@BeforeAll
	public static void eventTypeSetup() {
		et = new EventType();
		et.setEventId(1);
		et.setName("Other");
		et.setPercentCovered(30.0);
	}
	@BeforeAll
	public static void statusSetup() {
		status = new Status();
		status.setStatusId(1);
		status.setName("Pending Approval");
		status.setApprover("Direct Supervisor");
	}	
	@BeforeAll
	public static void gradingFormatSetup() {
		gf = new GradingFormat();
		gf.setFormatId(1);
		gf.setName("Letter Grade");
		gf.setExample("A");
	}
	
		
	@BeforeAll
	public static void EmployeeSetup() {// mock emp
		emp = new Employee();
		emp.setFirstName("first");
		emp.setLastName("last");
		emp.setUsername("Huffy");
		emp.setPassword("password");
		emp.setRole(role);
		emp.setFunds(1000);
		emp.setSupervisor(sup);
		emp.setDepartment(dept);
	}	
	@BeforeAll
	public static void mockRoleSetup() {// mock
		role = new Role();
		role.setRoleId(19);
		role.setName("fired");
	}
	
	@Test
	@Order(1)
	public void getAll() {
		Set<Reimbursement> givenOutput = remDao.getAll();
		assertNotNull(givenOutput);
	}
	
	@Test
	@Order(1)
	public void getByIdWhenIdExists() {
		int idInput = 1;
		Reimbursement idOutput = remDao.getById(idInput);
		assertEquals(1, idOutput.getReqId());
	}
	
	@Test
	@Order(1)
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Reimbursement idOutput = remDao.getById(idInput);
		assertNull(idOutput);
	}
	
	@Test
	@Order(2)
	public void addNewReimbursement() {
		Reimbursement newRem = new Reimbursement();
		assertNotEquals(0, remDao.create(newRem));
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Reimbursement remUp = remDao.getById(1);
		remUp.setLocation("Bob");
		remDao.update(remUp);
		assertEquals("Bob",remDao.getById(1).getLocation());
	}
	
	@Test
	@Order(4)
	public void testDelete() {
		Reimbursement remDel = remDao.getById(1);
		remDao.delete(remDel);
		assertNull(remDel);
	}
}
