
document.getElementById(`submitbutton`).onclick = submitRequest;

async function submitRequest() {

        
    let eventTypeId = document.getElementById("eventtype").value;
    let eventType = {
        "eventId": eventTypeId
    }
    let status = {
        "statusId": 4,
        "name": "Pending Sup",
        "approver": "Supervisor"
    };
    let gradeFormatId =  document.getElementById("gformat").value;
    let gradeFormat  = {
        "formatId":  gradeFormatId
    }
                
    let edate = document.getElementById("eventdate").value;
    let etime = String(document.getElementById("etime").value);
    etime = etime + ":01";
    let local = document.getElementById("street").value + ", "+
                document.getElementById("city").value + ", "+
                document.getElementById("state").value;//
    let desc = document.getElementById("descBox").value;//
    let cost = document.getElementById("cost").value;//
    
    let request = {
        "requestor": loggedInPerson,
        "eventDate": edate,
        "eventTime": etime,
        "location": local,
        "description": desc,
        "cost": cost,
        "gradingFormat": gradeFormat,
        "eventType": eventType,
        "status":status,
        "submittedAt": ''
    };
    console.log(request);
    if(request.address === "" || request.city === ""|| request.city === "" || request.desc === "" || request.cost === "" ||request.gradeFormat === "" || request.eventType === "" || request.status === ""){
        alert("Please Enter Proper Form Data")
    }
    else{
        
        let response = await fetch(TRMSAppUrl + 'requests', {
        method: 'POST',
        body: JSON.stringify(request),
        });
        if (response.status === 201) {
            cost = loggedInPerson.funds - costCalc(cost);
        alert("Request has been sent\r\n Pending Request is approved the \r\n balance of $"
        + cost + " will be left from your account\r\n  for the rest of the year.");
        }
        else
            alert("Something went wrong");
    }    
}
 function costCalc(c){
    let eventTypeId = document.getElementById("eventtype").value;

if (eventTypeId === 1 ){
    c = c * .80;
}else if (eventTypeId === 2){
    c = c * .6;
}else if (eventTypeId === 3){
    c = c * .75;
}else if (eventTypeId === 4){
    c = c * 1;
}else if (eventTypeId === 5){
    c = c * .9;
}else 
    c = c * .3;


return c;
 }