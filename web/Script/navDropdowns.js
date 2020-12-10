
    /* Loop through all navDropdowns buttons to toggle between hiding and showing its navDropdowns content - This allows the user to have multiple dropdowns without any conflict */
    var navDropdowns = document.getElementsByClassName("dropdown-btn");
    var i;

    for (i = 0; i < navDropdowns.length; i++) {
    navDropdowns[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
