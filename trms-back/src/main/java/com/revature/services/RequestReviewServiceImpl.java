package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.data.postgres.ReimbursementPostgres;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	private ReimbursementDAO reqDao = DAOFactory.getReimbursementDAO();
	private EmployeeDAO empDao = DAOFactory.getEmployeeDAO();

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> all = reqDao.getAll();
		Set<Reimbursement> pending= new HashSet<Reimbursement>();
		String rolen= approver.getRole().getName();
		for(Reimbursement r: all) {
			if(r.getStatus().getName().contains("pending")) {
				if(rolen.equals("direct supervisor")) {
					if(r.getStatus().getApprover().equals("direct supervisor"))
						pending.add(r);
				}
				else if(rolen.equals("department head")) {
					if(r.getStatus().getApprover().equals("department head")||r.getStatus().getApprover().equals("direct supervisor"))
						pending.add(r);
				}
				else 
					pending.add(r);
				
			}
		}
		
		return pending;
	}

	@Override
	public void approveRequest(Reimbursement request) {
		Status stat =request.getStatus();
		if(!stat.getName().equals("approved"))
		stat.setName("approved");
		
		request.setStatus(stat);
		reqDao.update(request);
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		Status stat =request.getStatus();
		if(!stat.getName().equals("denied"))
		stat.setName("denied");
		request.setStatus(stat);
		reqDao.update(request);
	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		Status stat =request.getStatus();
		if(!stat.getName().equals("denied"))
		stat.setName("denied");
		request.setStatus(stat);
		reqDao.update(request);
		
	}

	@Override
	public Reimbursement getById(int Id) {
		return reqDao.getById(Id);
	}

}
