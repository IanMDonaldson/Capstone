<!DOCTYPE html>
<html>

<head>
    <title> Department Countinous Improvement Application</title>
    <style>
        body {margin:0;
          font-family: Arial, Helvetica, sans-serif
        }

/* Navagation/header styles */    
.topnav {
  overflow: hidden;
  background-color: #363740;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #363740;
  color: white;
}
h1 {
  text-align: center;
}

.topnav-right {
  float: right;
}
h1{
    color:#363740;
    text-align: center;

}
.title_cennter{
    
    margin:auto;
}

/* style the container */
.container {
  position: relative;
  border-radius: 5px;
  padding: 20px 0 30px 0;
} 
form{

margin-left: 20%;
margin-right:20%;
}

/* style inputs and link buttons */
input, select {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  margin: 5px 0;
  opacity: 0.85;
  display: inline-block;
  font-size: 17px;
  line-height: 20px;
  text-decoration: none; /* remove underline from anchors */
}
select{
  width: 105%;
}
.registation_btn{
  width: 105%;
}
input:hover,
.btn:hover {
  opacity: 1;
}


/* style the submit button */
input[type=submit] {
  background-color: #363740;
  color: white;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #474a69;
}

/* Two-column layout */
.col {
  float: start;
  width: 50%;
  margin: auto;
  padding: 0 50px;
  margin-top: 6px;
}
.rol{
  display: block;
}
.rol input{
  width: 50%;
}
 h1 .title_pg{
  text-align:center
 }
/* hide some text on medium and large screens */
.hide-md-lg {
  display: none;
}
  /* hide the vertical line */
  .vl {
    display: none;
  }
  /* show the hidden text on small screens */
  .hide-md-lg {
    display: block;
    text-align: center;
  }


        </style>
</head>
<body style="background-color:#F7F8FC;">
    

  <div class="topnav">
    <a class="active" href="home_page.jsp">Home</a>
    <div class="topnav-right">
      <a href="LoginServlet?action=loginGET">Login</a>
      <a href="RegisterServlet?action=registerGET">Register</a>
    </div>
  </div>


  <div class="container">
    <form action="Register?action=registerPOST" method="post">

        <h1 class="title_pg" >DCIA Register</h1>

        <div class="col">
          <div class="row">
          <input type="text" name="first_name" placeholder="First Name" required>
          <input type="text" name="last_name" placeholder="Last Name" required>
        </div>
          <input type="text" name="email" placeholder="Enter Email" required>
            <label for="access_level">Access Level: </label>
          <select name="access_level" id="access_level" required>
            <option value="admin">Admin</option>
            <option value="instructor">Instructor</option>
          </select>
            <label for="username">Username: </label>
            <input type="text" id="username" name="username" placeholder="Username" required>
            <label for="password">Password: </label>
          <input type="password" name="password" id="password" placeholder="Password" required>
            <label for="confirm_password">Confirm Password: </label>
          <input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" required>

          <input class="registation_btn" type="submit" value="Register">
        </div>
        
    
    </form>
  </div>
  
<script>
    $('#password, #confirm_password').on('keyup', function () {
        if ($('#password').val() === $('#confirm_password').val()) {
            $('#message').html('Matching').css('color', 'green');
        } else
            $('#message').html('Not Matching').css('color', 'red');
    });
</script>
  

</body>
</html>