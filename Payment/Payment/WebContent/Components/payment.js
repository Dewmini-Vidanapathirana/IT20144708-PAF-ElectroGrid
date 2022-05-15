$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	var status = validatePayForm();
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
 	{ 
		 url : "PaymentServlet", 
		 type : type, 
		 data : $("#formPay").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
 		{ 
 			onPaySaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onPaySaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divPayGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
 		 } 
 	} 
    else if (status == "error") 
 	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } 
	 else
  	 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
 	 }
	 $("#hididSave").val(""); 
	 $("#formPay")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
	{ 
		 $("#hididSave").val($(this).data("id")); 
		 $("#cusName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#cusAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#cusAccount").val($(this).closest("tr").find('td:eq(2)').text());  
		$("#time").val($(this).closest("tr").find('td:eq(3)').text()); 
		$("#date").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
	{ 
		 $.ajax( 
		 { 
		 	url : "PaymentServlet", 
			type : "DELETE", 
		 	data : "id=" + $(this).data("id"),
		 	dataType : "text", 
		 	complete : function(response, status) 
		 	{ 
		 		onPayDeleteComplete(response.responseText, status); 
		 	} 
	 }); 
});

function onPayDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divPayGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	} 
	else if (status == "error") 
	{ 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} 
	else
	{ 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	 } 
}

// CLIENT-MODEL================================================================
function validatePayForm()
{
	// NAME------------------------
	if ($("#cusName").val().trim() == ""){
		return "Insert Customer Name.";
	}
	// ADDRESS------------------------
	if ($("#cusAddress").val().trim() == ""){
		return "Insert Customer Address.";
	}
	// ACCOUNT------------------------
	if ($("#cusAccount").val().trim() == ""){
		return "Insert Customer Account.";
	}
	// EMAIL
	if ($("#userEmail").val().trim() == ""){
		return "Insert User_Email.";
	}
	// TIME------------------------
	if ($("#time").val().trim() == ""){
		return "Insert Time.";
	}
	// DATE------------------------
	if ($("#date").val().trim() == ""){
		return "Insert Date.";
	}
	return true;
}
