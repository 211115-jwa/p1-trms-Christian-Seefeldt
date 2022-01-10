package com.revature.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.data.postgres.ReimbursementPostgres;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	private static EmployeeService empServ = new EmployeeServiceImpl();

	private static EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	private static ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	private static StatusDAO statDAO = DAOFactory.getStatusDAO();
	private static CommentDAO comDAO = DAOFactory.getCommentDAO();

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> sup = new HashSet<>();

		if (approver.getRole().getRoleId() == 2) {
			Set<Status> stats = statDAO.getByName("Pending approval:supervisor");
			List<Status> s = new ArrayList<>(stats);
			sup = remDAO.getByStatus(s.get(0));
			
		} else if (approver.getRole().getRoleId() == 3) {
			Set<Status> stats = statDAO.getByName("Pending approval:department head");
			List<Status> s = new ArrayList<>(stats);
			sup = remDAO.getByStatus(s.get(0));
			
		} else if (approver.getRole().getRoleId() == 4) {
			Set<Status> stats = statDAO.getByName("Pending approval:benefits coordinator");
			List<Status> s = new ArrayList<>(stats);
			sup = remDAO.getByStatus(s.get(0));
		}

		Set<Reimbursement> sts = new HashSet<>(sup);
		return sts;
	}
	
	@Override
	public void approveRequest(Reimbursement request) {
		Comment com = new Comment();

		com.setApprover(request.getRequestor().getSupervisor());
		com.setRequest(request);
		com.setSentAt(LocalDateTime.now());

		String approver = getApproverString(request);

		if (1 == request.getStatus().getStatusId() || request.getStatus().getStatusId() == 4 || request.getStatus().getStatusId() == 7) {
			com.setCommentText(" ****Approved****\r\n" + " *****System Generated*****\r\n " + "****Approved by "
					+ approver + "****\r\n");
			request.getStatus().setStatusId(request.getStatus().getStatusId() + 3);
			remDAO.update(request);
		} else if (request.getStatus().getStatusId() == 8) {
			com.setCommentText(" ****Error****\r\n" + " *****System Generated*****\r\n "
					+ "****Submitted for Approval Duplicate****");

		} else {
			com.setCommentText(" ****Rejected Reimburesment****\r\n" + " *****System Generated*****\r\n "
					+ "****not available for Aprroval****");
		}

		if (request.getRequestor().getRole().getName().contains("Benefits Coordinator")
				&& request.getStatus().getName().contains("Benefits Coordinator")) {
			request.getStatus().setStatusId(8);
			remDAO.update(request);
		}
		double fund = request.getRequestor().getFunds() - costCalc(request);
		Employee e = new Employee();
		e = empServ.getEmployeeById(request.getRequestor().getEmpId());
		
		e.setFunds(fund);
		empDAO.update(e);
		comDAO.create(com);
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		
		Comment com = new Comment();

		com.setApprover(null);
		com.setRequest(request);
		com.setSentAt(LocalDateTime.now());

		String approver = getApproverString(request);

		com.setCommentText(" ****Rejected by " + approver + "****\r\n" + " *****System Generated*****\r\n "
				+ "****Must Resubmit****\r\n" + com.getCommentText());

		com.setApprover(request.getRequestor().getSupervisor());
		Reimbursement r = new Reimbursement();
		r = remDAO.getById(request.getReqId());
		
		if (request.getRequestor().getRole().getName().contains("Benefits Coordinator")) {
		r.getStatus().setStatusId(9); 
		}
		
		else if (request.getRequestor().getRole().getName().contains("Department Head")) {
				r.getStatus().setStatusId(6);
		}
		
		else if (request.getRequestor().getRole().getName().contains("Supervisor")) {
			r.getStatus().setStatusId(3);
	}
		remDAO.update(r);
		comDAO.create(com);

	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		request.getStatus().setStatusId(5);
		String approver = getApproverString(request);
		comment.setRequest(request);
		comment.setCommentText(" ****Rejected by " + approver + "****\r\n" + " *****System Generated*****\r\n "
				+ "****Must Resubmit****\r\n" + comment.getCommentText());
		comDAO.create(comment);
	}

//	@Override
//	public void approveRequest(Reimbursement request) {
//		Status stat =request.getStatus();
//		if(!stat.getName().equals("approved"))
//		stat.setName("approved");
//		
//		request.setStatus(stat);
//		remDAO.update(request);
//	}
//
//	@Override
//	public void rejectRequest(Reimbursement request) {
//		Status stat =request.getStatus();
//		if(!stat.getName().equals("denied"))
//		stat.setName("denied");
//		request.setStatus(stat);
//		remDAO.update(request);
//	}
//
//	@Override
//	public void rejectRequest(Reimbursement request, Comment comment) {
//		Status stat =request.getStatus();
//		if(!stat.getName().equals("denied"))
//		stat.setName("denied");
//		request.setStatus(stat);
//		remDAO.update(request);
//		
//	}
private static String getApproverString(Reimbursement request) {
	String approver = "";
	if (request.getStatus().getName().contains("Sup"))
		approver = "Supervisor";
	else if (request.getStatus().getName().contains("DH"))
		approver = "Dept. Head";
	else if (request.getStatus().getName().contains("BC"))
		approver = "BenCo";

	return approver;
}

private static double costCalc(Reimbursement r) {
	double c = r.getCost();
	int etp = r.getEventType().getEventId();
	
	if (etp == 1 ){
	    c = c * .80;
	}else if (etp == 2){
	    c = c * .6;
	}else if (etp == 3){
	    c = c * .75;
	}else if (etp == 4){
	    c = c * 1;
	}else if (etp == 5){
	    c = c * .9;
	}else 
	    c = c * .3;
	return c;
}


	@Override
	public Reimbursement getById(int Id) {
		return remDAO.getById(Id);
	}

}
