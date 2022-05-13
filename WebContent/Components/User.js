/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
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
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UserAPI",
 type : type,
 data : $("#formUser").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onEmployeeSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).data("ID"));
 $("#userCode").val($(this).closest("tr").find('td:eq(0)').text());
 $("#userName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#userPnumber").val($(this).closest("tr").find('td:eq(2)').text());
 $("#userGmail").val($(this).closest("tr").find('td:eq(3)').text());
 
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "UserAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onUserDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateUserForm()
{
// account number
if ($("#userCode").val().trim() == "")
 {
 return "Insert User code.";
 }
// NAME
if ($("#userName").val().trim() == "")
 {
 return "Insert User Name.";
 } 
if ($("#userPnumber").val().trim() == "")
 {
 return "Insert User phone number.";
 }
// NAME
if ($("#userGmail").val().trim() == "")
 {
 return "Insert User Gmail.";
 } 

return true;
}

function onUserSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidIDSave").val("");
 $("#formEmployee")[0].reset();
}

function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 