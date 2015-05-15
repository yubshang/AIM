<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name="label.list_peo"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.6.0/bootstrap-table.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/redmond/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/application.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.6.0/bootstrap-table.min.js"></script>
    <script src="js/excanvas.min.js"></script>
    <script src="js/jquery.flot.min.js"></script>
    <script src="js/jquery.flot.pie.min.js"></script>
    <script src="js/jquery.flot.resize.min.js"></script>
    <script type="text/javascript">
    $(function(){
        $('#list_peo').on("load-success.bs.table", function(){
            load_list_peo_tb();
        });
        $('.sortable').on('click', function(){
            setTimeout(function(){load_list_peo_tb();}, 10);
        });
    });

    function load_list_peo_tb(){
        var dialog = $('#dialog-form').dialog({
            autoOpen: false,
            height: 550,
            width: 900,
            modal: true
        });
        
        $('.btn-detail').on("click", function(){
            var id = $(this).parent().siblings('.ids').html();
            var target_levels = [];
            var semesters = [];
            $.post("json_peo_peoDetailJ.action", { peoId : id}, function(data){
                $.each(data, function(index, val){
                    switch(index){
                    case "sequenceNumber":
                        $('#dialog_seq_num').text(val);
                        break;
                    case "identifier":
                        $('#dialog_ident').text(val);
                        break;
                    case "shortName":
                        $('#dialog_short_name').text(val);
                        break;
                    case "description":
                        $('#dialog_description').text(val);
                        break;
                    case "peoSemesterTargets":
//                        var html = "<table><tr><th></th><th></th></tr>";
                        for(var i = 0; i < val.length; i++){
                            var obj = val[i];
                            target_levels.push([i, obj["attainmentLevel"]]);
                            semesters.push(obj["semester"]);
                        }
                        break;
                    default:
                    }
                });
                

                // === Make chart === //
                var plot = $.plot(".chart", [{data: target_levels, label: "target attainment level", 
                                  color: "#AED5FC"}], {series: {lines: {show: true}, points: {show: true}},
                                  grid: {hoverable: true}, yaxis: {min: 0, max: 100},
                                  xaxis: {min: 0, minTickSize: 1, tickDecimals: 0,}});
                
                $("<div id='tooltip'></div>").css({
                    position: "absolute", display: "none",
                    border: "1px solid #AED5FC", padding: "2px",
                    "background-color": "#AED5FC", opacity: 0.80,
                    "z-index": 1000
                }).appendTo("body");

                // === Point hover in chart === //
                $(".chart").bind("plothover", function (event, pos, item) {
                    if (item) {
                        var x = item.datapoint[0],
                        y = item.datapoint[1];
                        $("#tooltip").html(semesters[x] + " = " + y + "%")
                            .css({top: item.pageY+5, left: item.pageX+5})
                            .fadeIn(200);
                    } else {
                        $("#tooltip").hide();
                    }
                });	
            });
            
            dialog.bind('dialogclose', function(event) {
            	$("#tooltip").hide();
            });
            dialog.css("overflow-x", "hidden");
            dialog.dialog("open");
        });
        $('.ids').hide();
    }
    
    function operateFormatter(value, row, index) {
        return [
                "<button type='button' class='btn btn-primary btn-xs btn-detail'>view details</button>",
                "&nbsp;&nbsp;&nbsp;",
                "<button type='button' class='btn btn-primary btn-xs'>modify</button>"
        ].join('');
    }
    </script>
</head>
<body>
    <h3 class="title_abet"><s:text name="label.list_peo"/></h3>
    <div class="table_holder_abet">
        <table id="list_peo" data-toggle="table" data-sort-order="desc" data-url="json_peo_listPeoJ.action">
            <thead>
                <tr>
                    <th data-field="peoId" class="ids"></th>
                    <th data-field="sequenceNumber" class="col-sm-1" data-sortable="true"><s:text name="label.sequence_num_short"/></th>
                    <th data-field="identifier" class="col-sm-1" data-sortable="true"><s:text name="label.identifier"/></th>
                    <th data-field="shortName" class="col-sm=1" data-sortable="true"><s:text name="label.short_name"/></th>
                    <th data-field="description" class="col-sm-7" data-sortable="true"><s:text name="label.description"/></th>
                    <th class="col-sm-2" data-sortable="false" data-formatter="operateFormatter"></th>
                </tr>
            </thead>
        </table>
    </div>
    
    <!-- JQuery UI dialog box for displaying PEO details -->
    <div id="dialog-form" title="PEO Details">
        <p>
            <b><s:text name="label.sequence_num_short"/></b>: <span id="dialog_seq_num"></span> &nbsp; &nbsp;
            &nbsp; &nbsp;
            <b><s:text name="label.identifier"/></b>: <span id="dialog_ident"></span> &nbsp; &nbsp;&nbsp; &nbsp;
            <b><s:text name="label.short_name"/></b>: <span id="dialog_short_name"></span>
        </p>
        <p>
            <b><s:text name="label.description"/></b>: <br/>
            <span id="dialog_description"></span>
        </p>
        <p>
            <b><s:text name="label.target"/></b>:
            <div class="row-fluid">
                <div class="span12">
                    <div class="widget-content">
                        <div class="chart" style="height:300px"></div>
                    </div>
                </div>
            </div>
        </p>
    </div>
</body>
</html>