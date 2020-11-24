<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
body {
  font-family: "Lato", sans-serif;
  margin:0;
}
        .topnav {
          margin: 0;
          padding-left: 201px;
          background-color: #F7F8FC;
          position: fixed;
          width: 87%;
        }
        
      

        .topnav a  {
          display: block;
          color: #363740;
          text-align: center;
          padding: 14px 16px;
          text-decoration: none;
          float: left;
          
          }
          
          .topnav-right {
          float: right;
         
          }
          .btn {
              background-color:transparent;
              border: none;
              color: #363740;
              padding: 12px 16px;
              font-size: 16px;
              cursor: pointer;
              
              }
          .btn i {
            padding-right: 4px;
          }
     /*top nav drop down*/
     .dropdown {
      float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: rgb(0, 0, 0);
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}



.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
        
/* Fixed sidenav, full height */
.sidenav {
  height: 100%;
  width: 200px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #363740;
  overflow-x: hidden;
  padding-top: 20px;
}

/* Style the sidenav links and the dropdown button */
.sidenav a, .dropdown-btn {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 16px;
  color: #818181;
  display: block;
  border: none;
  background: none;
  width: 100%;
  text-align: left;
  cursor: pointer;
  outline: none;
  display: inline-block;
  word-break: break-word;

}

/* On mouse-over */
.sidenav a:hover, .dropdown-btn:hover {
  color: #f1f1f1;
}

/* Main content */
.main {
  margin-left: 200px; /* Same as the width of the sidenav */
  font-size: 20px; /* Increased text to enable scrolling */
  padding: 0px 10px;
}

/* Add an active class to the active dropdown button */
.active {
  background-color: rgb(45, 45, 53);
  color: white;
}

/* Dropdown container (hidden by default). Optional: add a lighter background color and some left padding to change the design of the dropdown content */
.dropdown-container {
  display: none;
  background-color: #363740;
  padding-left: 6px;
  
}

/* Optional: Style the caret down icon */
.fa-caret-down {
  float: right;
  padding-right: 8px;
}

/* Some media queries for responsiveness */
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

</style>
</head>
<body>
  <div class="topnav">
    
      <a  href="#page_name">Instructor</a>
   <div class="topnav-right">
      <button class="btn"> <i class="fas fa-plus-circle"></i>Add Instructor</button>
      <button class="btn"><i class="fas fa-edit"></i>Edit</button>
       <button class="btn"><i class="fa fa-trash"></i>Delete</button>

      <button class="btn">Admin</button>
      <button class="btn">Logout</button>
    </div>
      
  </div>

  <div class="sidenav">
    <a id="noClick">Fall 2019</a>
   <button class="dropdown-btn">Analysis
     <i class="fa fa-caret-down"></i>
   </button>
   <div class="dropdown-container">
    <a href="analyisis_swp.jsp">SWP-Row, Mean, & Median Scores</a>
     <a href="analylisis_CSOP.jsp">Course SO Performance</a>
     <a href="analysis_ISOP.jsp">Instructor SO Performance</a>
     <a href="analysiis_ALL_CSOP_byTerm.jsp">All Courses SO Performance by Term</a>
     <a href="analysis_CSOP_byTerm.jsp">Courses SO Performance by Term</a>
   </div>
   <a href="Setup_Term_Year.jsp">Setup Terms & Years</a>
   <a href="instuctors.jsp">Instructor</a>

 
 </div> 


<div class="main">
  
</div>

<script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>

</body>
</html> 