function validateForm() {
	var userId = document.forms["loginForm"]["userId"].value;
	var password = document.forms["loginForm"]["password"].value;
	if (userId == "") {
		document.getElementById("userIdError").innerHTML = "Please provide UserId!";
		document.getElementById("userId").focus();
		return false;
	} else if (password == "") {
		document.getElementById("passwordError").innerHTML = "Please provide Password!";
		document.getElementById("password").focus();
		return false;
	}
}

function checkUserIdValid() {
	var userId = document.forms["loginForm"]["userId"].value;
	if (userId != "") {
		document.getElementById("userIdError").innerHTML = "";
	} else if (userId == "") {
		document.getElementById("userIdError").innerHTML = "Please provide UserId!";
	}
}
function checkPasswordValid() {
	var password = document.forms["loginForm"]["password"].value;
	if (password != "") {
		document.getElementById("passwordError").innerHTML = "";
	} else if (password == "") {
		document.getElementById("passwordError").innerHTML = "Please provide Password!";
	}
}