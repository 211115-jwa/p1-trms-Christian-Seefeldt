package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.RequestsController;
import com.revature.controllers.EmployeesController;
import com.revature.controllers.UsersController;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start();
		
		app.before("/employees/*", ctx -> {
			if (!ctx.method().equals("OPTIONS")) {
				ctx.header("Access-Control-Allow-Headers", "Token");
			    ctx.header("Access-Control-Expose-Headers", "Token");
				
				String token = ctx.header("Token");
				if (token==null) ctx.status(HttpCode.UNAUTHORIZED);
			}
		});

		app.routes(() -> {
			path("/employees", () -> {
				get(EmployeesController::viewAllEmployees);

				path("/id/{empId}", () -> {
					get(EmployeesController::viewEmployeeById);
		
				});
			});
			
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
			
			path("/users", () -> {
				post(UsersController::register); // register
				path("/auth", () -> {
					post(UsersController::logIn); // login
				});
				path("/{id}", () -> {
					get(UsersController::getUserById); // get user by id
					put(UsersController::updateUser); // update user
					path("/auth", () -> {
						get(UsersController::checkLogin); // check login
					});
				});
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
