google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

//calling java fuction to get database info
//dummy data

//var swpData=[2.95,3.5,3.2,2.75];
//var swpName=["SWP1","SWP2","SWP3","SWP4"];
//var rawList='<%= Session["rawSOData"].ToString() %>';
//select for term

function ready() {

}
function getMeanData(){
    var meanAry = document.getElementById('meanAry');
    var namesAry = document.getElementById('Terms');

    var meanData = createArrayData(meanAry,namesAry);
    return meanData;
}

function createArrayData(numData, stringAry) {
    var chartData = [];
    //set the first index in chart array
    chartData.push(stringAry);

    //get length of array inside the numData array, all arrays should be same size

    var aryLength = numData.length;

    //creates the number arrays for the the chart
    for (var i = 0; i < aryLength; i++) {
        var newAry = [];
        newAry.push(i + 1);
        chartData.push(newAry);
    }

    //gets data from numData[i][j] and push it to chartData[i]
    for (var i = 0; i < numData.length; i++) {
        for (var j = 0; j < aryLength; j++) {
            var data = numData[i][j]
            chartData[j + 1].push(data);
        }
    }



    return chartData;
}



function drawChart() {
    var chartData = getMeanData();
    var data = google.visualization.arrayToDataTable(chartData);

    var options = {
        title: 'Student Work Product',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart1',));

    chart.draw(data, options);
}