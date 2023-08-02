function drawPieChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Puesto');
    data.addColumn('number', 'Cantidad');
    Object.keys(real_data).forEach(function (key) {
        data.addRow([key, real_data[key]]);
    });

    var options = {
        title: 'Estadisticas de puesto',
        colors: ['#4e9dfc', '#ff6969', '#ffab61', '#93db8c', '#e1aef2'],
        sliceVisibilityThreshold: 0,
        pieSliceTextStyle: { color: 'white' },
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(data, options);
}

function drawColumnChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Rango de Salario');
    data.addColumn('number', 'Cantidad');

    var ordenRangos = [
        "Menor a 2000",
        "Entre 2000 a 4000",
        "Entre 4000 a 6000",
        "Entre 6000 a 8000",
        "Entre 8000 a 10000",
        "Mayor a 10000"
    ];

    ordenRangos.forEach(function (rango) {
        var cantidad = salarioData[rango];
        data.addRow([rango, cantidad]);
    });

    var options = {
        title: 'Cantidad de empleados por rango de salario',
        colors: ['#42caff'],
        chartArea: { width: '50%' },
        hAxis: {
            title: 'Rango de Salario',
            minValue: 0
        },
        vAxis: {
            title: 'Cantidad de empleados'
        }
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_div'));
    chart.draw(data, options);
}

function drawPieChartGenero() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Genero');
    data.addColumn('number', 'Cantidad');
    Object.keys(generoData).forEach(function (key) {
        data.addRow([key, generoData[key]]);
    });

    var options = {
        title: 'Estadisticas de genero',
        colors: ['#4e9dfc', '#fcc5f9', '#bfbfbf'],
        sliceVisibilityThreshold: 0,
        pieSliceTextStyle: { color: 'white' },
    };

    var chart = new google.visualization.PieChart(document.getElementById('pieChartGenero'));
    chart.draw(data, options);
}

function drawBarChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Ciudad');
    data.addColumn('number', 'Cantidad');
    Object.keys(bar_chart_data).forEach(function (key) {
        data.addRow([key, bar_chart_data[key]]);
    });

    var options = {
        title: 'Cantidad de empleados por ciudad',
        colors: ['#8945ff'],
        sliceVisibilityThreshold: 0,
        pieSliceTextStyle: { color: 'white' },
        chartArea: { width: '50%' },
        hAxis: {
            title: 'Cantidad de empleados',
            minValue: 0
        },
        vAxis: {
            title: 'Ciudad'
        }
    };

    var chart = new google.visualization.BarChart(document.getElementById('bar_chart_div'));
    chart.draw(data, options);
}