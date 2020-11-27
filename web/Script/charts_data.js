google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

//calling java fuction to get database info
//dummy data

var swpData=[2.95,3.5,3.2,2.75];
var swpName=["SWP1","SWP2","SWP3","SWP4"];
var rawList='<%= Session["rawSOData"].ToString() %>';
//select for term
function fillSelectData(target, data){

    for(var i = 0; i < data.length; i++) {
        var opt = document.createElement('option');
        opt.innerHTML = data[i];
        opt.value = data[i];
        target.appendChild(opt);
    }
}
function ready() {
    var selectTerm = document.getElementById("terms");
    var termData = ["Fall 2018","Spring 2019","Summer 2019","Fall 2019"];

    var selectCourse = document.getElementById("courses");
    var coursesData=["Course_1", "Course_2", "Course_3"];

    var selectData = document.getElementById("chartfucntions");
    var dataOutput=["Raw", "Mean","Median"];

    fillSelectData(selectTerm,termData);
    fillSelectData(selectCourse,coursesData);
    fillSelectData(selectData,dataOutput);

}
document.addEventListener("DOMContentLoaded", ready);



function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2004',  1000,      400],
        ['2005',  1170,      460],
        ['2006',  660,       1120],
        ['2007',  1030,      540]
    ]);

    var options = {
        title: 'Student Work Product',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

    chart.draw(data, options);
}