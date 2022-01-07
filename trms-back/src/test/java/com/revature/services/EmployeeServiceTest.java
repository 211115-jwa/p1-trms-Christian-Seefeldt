package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private EventTypeDAO eventDao;
	private GradingFormatDAO gradeDao;
	private StatusDAO statDao;
	private ReimbursementDAO remDao;
	private CommentDAO comDao;
	private EmployeeDAO empDao;
	
	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	
	@Test
	public void getRequestOptions() {
		
	}
	
	@Test
	public void submitReimbursementRequest() {
		Reimbursement rem = new Reimbursement();
		Status stat = new Status();
		stat.setStatusId(1);
		
//		when(StatusDAO.getById(1)).thenReturn(stat);
		
		
	}
	
	@Test
	public void getReimbursementRequests() {
		
	}
	
	@Test
	public void getComments() {
		
	}
	
	@Test
	public void addComment() {
		
	}
	
	@Test
	public void getEmployeeById() {
		
	}
}
