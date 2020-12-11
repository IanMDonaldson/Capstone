<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp"%>
<html>
<head>
    <title>Department Continuous Improvement Application</title>
</head>

<div class="main">
    <form method="post" action="InstructorServlet?action=addStudentsPOST">
        <fieldset class="fname_labels">
            <div class="repeatable"></div>
            <input type="button" value="Add" class="add">
            <input type="submit" value="Submit" class="submit">
        </fieldset>
    </form>
</div>
<script type="text/template" id="fname_labels">
    <div class="field-group">
        <label for="fname_{?}">First Name</label>
        <input type="text" name="fname_labels[{?}][fname]" value="{fname}" id="fname_{?}">

        <label for="lname_{?}">Last Name</label>
        <input type="text" name="fname_labels[{?}][lname]" value="{lname}" id="lname_{?}">

        <input type="button" class="delete" value="Remove">

    </div>
</script>

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
<script>
     $(function() {
             $(".fname_labels .repeatable").repeatable({
                     addTrigger: ".fname_labels .add",
                     deleteTrigger: ".fname_labels .delete",
                     template: "#fname_labels",
                     startWith: 1,
                     max: 20
             });
         });
</script>
</body>
</html>
