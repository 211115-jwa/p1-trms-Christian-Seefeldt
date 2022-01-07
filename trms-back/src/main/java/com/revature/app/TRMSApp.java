package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.RequestsController;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start();
		
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
		});
	}

}

// Login Page
	// all other pages require login
// Reimbursement Page
	// One week before start
	// Optionally include documents
	// Project reimbursement read only
// 
// Approval Page
	// Supervisor and Dept Head
	// May request extra info
	// if denied reason given
	// auto-approved if too long
// BenCo Approval Page
	// ask for details
	// change reimbursement amount
	// if change employee notified
	// not auto approved
	// can exceed limit but give details
// Grade Page
	// Attach Grade
	// BenCo must confirm grade is passing
	// Upon Confirmation amount awarded
