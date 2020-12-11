google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);

//calling java fuction to get database info
//dummy data

//var swpData=[2.95,3.5,3.2,2.75];
//var swpName=["SWP1","SWP2","SWP3","SWP4"];
//var rawList='<%= Session["rawSOData"].ToString() %>';

//
function displayFunction(){
    var gt=document.getElementById("charFunctions")
    var selectD=gt.options[d.selectedIndex].text;
    document.getElementById("test").innerText=selectD;
}

//select for term
//function fillSelectData(target, data){

   // for(var i = 0; i < data.length; i++) {
      //  var opt = document.createElement('option');
      //  opt.innerHTML = data[i];
      //  opt.value = data[i];
       // target.appendChild(opt);
    //}
//}

//function to used for eventListener
window.onload=function (){
    function ready() {

        var selectTerm = document.getElementById("terms");
        //var termData =

        var selectCourse = document.getElementById("courses");
        // var coursesData = [];

        var selectData = document.getElementById("chartFunctions");
// var dataOutput = ["Raw", "Mean", "Median"];

        // fillSelectData(selectTerm, termList);
        // fillSelectData(selectCourse, coursesList);
        //fillSelectData(selectData, dataOutput);

    }
    document.addEventListener("DOMContentLoaded", ready);





document.getElementById("#chartFunctions").addEventListener("change",function testSelectedData(){
        var selectData = document.getElementById("#chartFunctions");
        var value=selectData.options[selectData.selectedIndex].value;//get selected option value
        var text=selectData.options[selectData.selectedIndex].text;
        document.getElementById("#changed").innerHTML=text;
        drawChart()
    });


    function getChartData() {
        var rawAry = document.getElementById("#rawAry").value;
        var meanAry = document.getElementById("#meanAry").value;
        var medianAry = document.getElementById("#medianAry").value;
        var namesAry = document.getElementById("#namesAry").text;

        //displayFunction();
        var selectedData = document.getElementById("#chartFunctions");
        var selectedD =selectedData.options[selectedData.selectedIndex].value;

        if (selectedD === "Raw") {
            var chartData = createArrayData(rawAry, namesAry);
            return chartData;

        }
        else if (selectedD === "Mean") {
            var chartData = createArrayData(meanAry, namesAry);
            return chartData;
        }
        else if (selectedD === "Median") {
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

        //get length of array inside the numData array, a ll arrays should be same size
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




    var chartF= document.getElementById("#chartFunctions")
    if(chartF){
        chartF.addEventListener("change", function(){
            //This input has changed
            console.log('This Value is', this.value);
        });

    }
    //Google Chart Function
    function drawChart() {
        var someChartData = getChartData();
        ///var dummieData=
        var data = google.visualization.arrayToDataTable(someChartData);

        // var data = google.visualization.arrayToDataTable($.parseJSON(getChartData));
        var options = {
            title: 'Student Work Product',
            curveType: 'none',
            legend: {position: 'bottom'}
        };

        var chart = new google.visualization.LineChart(document.getElementById('#curve_chart'));

        chart.draw(data, options);
    }
}


