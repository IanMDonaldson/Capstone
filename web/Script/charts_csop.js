// shows the mean SWP scores by Course over time
//user selects all of {Range of Term, course}
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);


//fills dropdown select with data
function fillSelectData(target, data){

    for(var i = 0; i < data.length; i++) {
        var opt = document.createElement('option');
        opt.innerHTML = data[i];
        opt.value = data[i];
        target.appendChild(opt);
    }
}
//function to used for eventListener
function ready() {
    var selectTerm = document.getElementById("terms");
    var termData = [];

    var selectCourse = document.getElementById("courses");
    var coursesData=[];

    fillSelectData(selectTerm,termData);
    fillSelectData(selectCourse,coursesData);




}
document.addEventListener("DOMContentLoaded", ready);

function createArrayData(numData,stringAry){
    var chartData=[];

    //set the first index in chart array
    chartData.push(stringAry);

    //get length of array inside the numData array, all arrays should be same size
    var aryLength = numData[i].length;

    //creates the number arrays for the the chart
    for(var i=0;i<aryLength;i++){
        var newAry= new Array;
        newAry.push(i+1);
        chartData.push(newAry);
    }
    //gets data from numData[i][j] and push it to chartData[i]
    for(var i=0;i<numData.length;i++){
        for(var j=0;j<aryLength;j++){
            var data=numData[i][j];
            chartData[j+1].push(data);
        }
    }
    return chartData;
}

//Google Chart:
function drawChart(aryData) {

    var data = google.visualization.arrayToDataTable(aryData);

    var options = {
        title: 'Student Work Product',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

    chart.draw(data, options);
}