

//calling java fuction to get database info
//dummy data

//var swpData=[2.95,3.5,3.2,2.75];
//var swpName=["SWP1","SWP2","SWP3","SWP4"];
//var rawList='<%= Session["rawSOData"].ToString() %>';



//select for term
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
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);



    var selectTerm = document.getElementById("terms");
    var termData = [];

    var selectCourse = document.getElementById("courses");
    var coursesData = [];

    //var selectData = document.getElementById("chartfucntions");
    //var dataOutput = ["Raw", "Mean", "Median"];

    fillSelectData(selectTerm, termData);
    fillSelectData(selectCourse, coursesData);
    // fillSelectData(selectData, dataOutput);

    // var updateChartData=getChartData();
    document.getElementById("chartfucntions").onchange=getChartData();
    function getChartData() {
        var rawAry = document.getElementById("rawAry").value;
        var meanAry = document.getElementById("meanAry").value;
        var medianAry = document.getElementById("medianAry").value;
        var namesAry = document.getElementById("namesAry");


        //var selectedData = document.getElementById("chartfucntions");

        //document.getElementById("chartfucntions").onchange = selectedData;


        if (this.value === "Raw") {
            var chartData = createArrayData(rawAry, namesAry);
            return chartData;

        }
        else if (this.value === "Mean") {
            var chartData = createArrayData(meanAry, namesAry);
            return chartData;
        }
        else if (this.value === "Median") {
            var chartData = createArrayData(medianAry, namesAry);
            return chartData;
        }
        else{
            var chartData=[["SO","SO1","SO2"],[1,3,4],[2,2,0],[3,1,4]]
            return chartData;
        }



    }



    function createArrayData(numData, stringAry) {
        var chartData = [];

        //set the first index in chart array
        chartData.push(stringAry);

        //get length of array inside the numData array, all arrays should be same size
        var aryLength = numData[i].length;

        //creates the number arrays for the the chart
        for (var i = 0; i < aryLength; i++) {
            var newAry = [];
            newAry.push(i + 1);
            chartData.push(newAry);
        }

        //gets data from numData[i][j] and push it to chartData[i]
        for (var i = 0; i < numData.length; i++) {
            for (var j = 0; j < aryLength; j++) {
                var data = numData[i][j];
                chartData[j + 1].push(data);
            }
        }

        return chartData;
    }


//Google Chart Function
    function drawChart() {
        var someChartData = getChartData();
        ///var dummieData=
        //var data = google.visualization.arrayToDataTable(someChartData);

        var data = google.visualization.arrayToDataTable($.parseJSON(getChartData));
        var options = {
            title: 'Student Work Product',
            curveType: 'none',
            legend: {position: 'bottom'}
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
    }
}
document.addEventListener("DOMContentLoaded", ready);


//var button=document.getElementById("submitBtn")
//button.onclick=getChartData();


