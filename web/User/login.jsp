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

.title_pg{
  color: #363740;
  text-align:center
}

form{
padding-top: 8%;
margin-left: 20%;
margin-right:20%;
}

/* style inputs and link buttons */
input {
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

.login_btn {
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
  background-color: #ddd;
  color: black;
  border-color: #363740;
}

/* Two-column layout */
.col {
  float: start;
  width: 50%;
  margin: auto;
  padding: 0 50px;
  padding-top: 10px;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
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
    <a class="active" href="home_Page.html">Home</a>
    <div class="topnav-right">
      <a href="login.html">Login</a>
      <a href="register.html">Register</a>
    </div>
  </div>


  <div class="container">
    <form action="Login?action=loginPOST" method="post">
      <div class="row">
        <h1 class="title_pg">DCIA Login</h1>

        <div class="col">
          <label for="username"><b>Username:</b></label>
          <input type="text" name="username" id="username" placeholder="Enter Username" required>
          <label for="password"><b>Password:</b></label>
          <input type="password" name="password" id="password" placeholder="Enter Password" required>
          <a href="home_page.jsp" style="float:right;">Forgotten Password?</a>
          <input class="login_btn" type="submit" value="Login">
        </div>
        
      </div>
    </form>
  </div>

</body>
</html>