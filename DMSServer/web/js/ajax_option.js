//chon giam doc
function onClickManager(x , y) {
    var manager_filter;
    switch(y){
        case "sale":
            manager_filter = "manager-filter-sale.action";
            break;
        case "take":
            manager_filter = "manager-filter.action";
            break;
    }
        
    var giamdocId = x !== "--select--" ? x : "nullid";//$('#giamDoc').val();
    gdID = giamdocId;
    console.log("Ma giam doc: " + giamdocId);
    $.getJSON(manager_filter, {'giamdocId': giamdocId},
    function(data) {

        var divisionList = (data.userListStaff);
        console.log("log: " + divisionList);
        var options = $("#staff");
        options.find('option')
                .remove()
                .end();
        options.append($("<option />").val("-1").text("--select--"));
        $.each(divisionList, function(k, v) {

            options.append($("<option />").val(k).text(v));
        });
        
        //Set customer is null
        var customer = $("#customer");
        customer.find('option')
                .remove()
                .end();
        customer.append($("<option />").val("-1").text("--select--"));
    }
    );
}

//Chon nhan vien
function onClickStaff(x , y) {
    var staff_filter;
    switch(y){
        case "sale":
            staff_filter = "staff-filter-sale.action";
            break;
        case "take":
            staff_filter = "staff-filter.action";
            break;
    }
    
    var staffId = x !== "--select--" ? x : "nullid";//$('#giamDoc').val();
    //gdID = giamdocId;
    console.log("Ma nhan vien: " + staffId);
    $.getJSON(staff_filter, {'nhanvienId': staffId},
    function(data) {

        var divisionList = (data.userListCustomer);
        console.log("log: " + divisionList);
        var options = $("#customer");
        options.find('option')
                .remove()
                .end();
        options.append($("<option />").val("-1").text("--select--"));
        $.each(divisionList, function(k, v) {

            options.append($("<option />").val(k).text(v));
        });
    }
    );
}

//Chon khac hang
function onClickCustomer(x , y) {
    var customer_filter;
    switch(y){
        case "sale":
            customer_filter = "customer-filter-sale.action";
            break;
        case "take":
            customer_filter = "customer-filter.action";
            break;
    }
    
    var staffId = x !== "--select--" ? x : "nullid";//$('#giamDoc').val();
    //gdID = giamdocId;
    console.log("Ma nhan vien: " + staffId);
    $.getJSON(customer_filter, {'khachhangId': staffId},
    function(data) {

        var divisionList = (data.userListStaff);
      
    }
    );
}

function onClickStartDate(x){
    var date = x ;
    console.log("Date: " + date);
    $.getJSON('start-date-filter.action', {'startDate': date},
    function(data) {

        //var divisionList = (data.userListStaff);
      
    }
    );
}

function onClickEndDate(x){
    var date = x ;
    console.log("Date: " + date);
    $.getJSON('end-date-filter.action', {'endDate': date},
    function(data) {

        //var divisionList = (data.userListStaff);
      
    }
    );
}


function changeImage(imageID, customerID){
    
//    console.log("Date: " + customerID); // ,'imageID': imageID
//    $.getJSON('update-image-profile.action', {'customerID': customerID},
//    function(data) {
//        console.log("OK ");
//        var status = (data.statusUpdate);
//        console.log(status);
//      
//    }
//    );
    
    console.log("Ma nhan vien: " + customerID);
    $.getJSON('update-image-profile.action', {'khachhangId': customerID,'imageID': imageID},
    function(data) {

        var status = false;
        status = (data.statusUpdate);
        console.log(status);
        var options = $("#update");
        options.find('span').remove();
        var text;
        
        if(String(status) === "true")
            text ="[Cập nhật thành công]";
        else
            text = "[Cập nhật thất bại]";
        
        
        options.append($("<span/>").append(text));
        alert(text);
    }
    );
}