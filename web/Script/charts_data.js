type="text/javascript"
google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);
src="data.js"


    function getChartData() {
        var rawAry = document.getElementById("rawAry");
        var meanAry = document.getElementById("meanAry").value;
        var medianAry = document.getElementById("medianAry").value;
        var namesAry = document.getElementById("namesAry").text;

        //displayFunction();
        //var selectedData = document.getElementById("#chartFunctions");
        // var selectedD =selectedData.options[selectedData.selectedIndex].value;

        // if (selectedD === "Raw") {
        return createArrayData(rawAry, namesAry);

        //}
        // else if (selectedD === "Mean") {
        // return createArrayData(meanAry, namesAry);
        // }
        //else if (selectedD === "Median") {
        // return createArrayData(medianAry, namesAry);
        //}
        //else{
        // return [["SO", "SO1", "SO2"], [1, 3, 4], [2, 2, 0], [3, 1, 4]];
        //}
    }




    function createArrayData(numData, stringAry) {
        var chart_Data = [];

        //set the first index in chart array
        chart_Data.push(stringAry);

        //get length of array inside the numData array, a ll arrays should be same size
        var aryLength = numData[i].length;

        //creates the number arrays for the the chart
        for (var i = 0; i < aryLength; i++) {
            var newAry = [];
            newAry.push(i + 1);
            chart_Data.push(newAry);
        }

        //gets data from numData[i][j] and push it to chartData[i]
        for (var i = 0; i < numData.length; i++) {
            for (var j = 0; j < aryLength; j++) {
                var data = numData[i][j];
                chart_Data[j + 1].push(data);
            }
        }

        return chart_Data;
    }
//Google Chart Function
    // document.getElementById("#submitBtn").addEventListener("click",




function drawChart() {
    var soArray=['SO','SO1','SO2'];
    var dataArray=Term_1;
    var testData=createArrayData(dataArray[1],soArray);
   // var someChartData = getChartData();
    ///var dummieData=
    var data = google.visualization.arrayToDataTable(testData);

    // var data = google.visualization.arrayToDataTable($.parseJSON(getChartData));
    var options = {
        title: 'Student Work Product',
        curveType: 'none',
        legend: {position: 'bottom'}
    };

    var chart = new google.visualization.LineChart(document.getElementById('#curve_chart'));

    chart.draw(data, options);
}
drawChart();







