$(function () {
    $('#chart-pengunjung').highcharts({
        title: {
            text: 'Statistik Pengunjung',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Jumlah'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ' orang'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Kota Yogyakarta',
            data: [51, 48, 58, 60, 70, 81, 82, 101, 126, 150, 180, 225]
        }, {
            name: 'Kab. Bantul',
            data: [21, 6, 30, 37, 44, 49, 52, 68, 80, 91, 83, 80]
        }, {
            name: 'Kab. Sleman',
            data: [18, 8, 15, 20, 30, 50, 49, 101, 97, 91, 98, 120]
        }, {
            name: 'Kab. Kulonprogo',
            data: [5, 3, 10, 13, 40, 50, 55, 60, 70, 68, 70, 61]
        }, {
            name: 'Kab. Gunungkidul',
            data: [1, 1, 5, 10, 20, 25, 30, 41, 55, 49, 60, 50]
        }]
    });
});

$(function () {
    $('#chart-bidang').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Browser market shares at a specific website, 2010'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['Firefox',   45.0],
                ['IE',       26.8],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['Safari',    8.5],
                ['Opera',     6.2],
                ['Others',   0.7]
            ]
        }]
    });
});
    
