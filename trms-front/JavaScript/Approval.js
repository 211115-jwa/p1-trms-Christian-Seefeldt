function showPendingRequests(requests) {
    let requestTable = document.getElementById('requestsID');
    for (var i = requestTable.rows.length - 1; i > 0; i--) {
        requestTable.deleteRow(i);
    }
    for (let request of requests) {
        let rows = document.createElement('tr');
        for (let field in request) {
            let column = document.createElement('td');
            let o = JSON.parse(JSON.stringify(request[field]));
            if (field == 'reqId') {
                column.innerText = o;
                rows.appendChild(column);
            }
            else if (field == 'requestor') {
                column.innerText = o.firstName + ' ' + o.lastName;
                rows.appendChild(column);
            }
            else if (field == 'status') {
                column.innerText = o.name;
                rows.appendChild(column);
                let c2 = document.createElement('tr');
                c2.innerText = o.approver;
                rows.appendChild(c2);
            }
            else if (field == 'description') {

                column.innerText = o;
                rows.appendChild(column);
            }




        }
        requestTable.appendChild(rows);
       
    }
}
async function getPendingRequestByID() {

    let response = await fetch(TRMSAppUrl + 'requests/manage/' + document.getElementById('empid').value);
    if (response.status === 200) {
        let requests = await response.json();
        if (requests.length == 0)
            alert('No pending requests found');
        showPendingRequests(requests);
    }
    else
        alert('something went wrong')
}
async function approveRequest() {
    let stat = getStatus("approved", document.getElementById("role").value)
    let response = await fetch(TRMSAppUrl + 'requests/requestor/approve/' + document.getElementById('reqid').value, {
        method: 'Put',
        body: JSON.stringify(stat),
    });
    if (response.status === 202)
        alert('Approved');
    if (response.status === 406)
        alert('No requests found make sure id is a number');

    
}
async function denyRequest() {
    let stat = getStatus("denied", document.getElementById("role").value);
    let response = await fetch(TRMSAppUrl + 'requests/requestor/deny/' + document.getElementById('reqid').value, {
        method: 'Put',
        body: JSON.stringify(stat),
    });
    if (response.status === 202)
        alert('Denied');
    if (response.status === 406)
        alert('No requests found make sure id is a number');
}
function getStatus(sname,approver) {
    let stat;
    if (sname === "pending approval") {
        if (approver === "direct supervisor")
            stat = {
                "statusId": 1,
                "name": "pending approval",
                "approver": "direct supervisor"
            };
        if (approver === "department head")
            stat = {
                "statusId": 4,
                "name": "pending approval",
                "approver": "department head"
            };
        if (approver === "benefits coordinator")
            stat = {
                "statusId": 7,
                "name": "pending approval",
                "approver": "benefits coordinator"
            };

    }
    else if (sname === "approved") {
        if (approver === "direct supervisor")
            stat = {
                "statusId":2,
                "name": "approved",
                "approver": "direct supervisor"
            };
        if (approver === "department head")
            stat = {
                "statusId": 5,
                "name": "approved",
                "approver": "department head"
            };
        if (approver === "benefits coordinator")
            stat = {
                "statusId": 8,
                "name": "approved",
                "approver": "benefits coordinator"
            };

    }
    else if (sname === "denied") {
        if (approver === "direct supervisor")
            stat = {
                "statusId": 3,
                "name": "denied",
                "approver": "direct supervisor"
            };
        if (approver === "department head")
            stat = {
                "statusId": 6,
                "name": "denied",
                "approver": "department head"
            };
        if (approver === "benefits coordinator")
            stat = {
                "statusId": 9,
                "name": "denied",
                "approver": "benefits coordinator"
            };

    }
    return stat;
}