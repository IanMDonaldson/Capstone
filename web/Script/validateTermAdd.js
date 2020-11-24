validateTermAdd = Function()
{
    let id = document.getElementById("id").value;
    let tYear = document.getElementById("Term Year").value;
    let tName = document.getElementById("Term Name").value;
    let asName = /^[A-Za-z]+$/;
    let numName = /^[0-9]+$/;
    if (tYear === ""||tName===""){
        alert("Year and Term must be filled");

    } else if(!asName.test(tName)||!numName.test(tYear)){
        alert("Names must have only upper or lowercase letters!.");

    } else{
        confirm("Adding new term:\n" +tName+" "+ tYear)
    }
}