<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日历管理</title>
</head>
<link href="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/demos/css/mainstructure.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/demos/css/maincontent.css" rel="stylesheet" type="text/css" />
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/demos/cupertino/theme.css' />
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/fullcalendar/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/fullcalendar/fullcalendar.print.css'
    media='print' />
<script src="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/jquery/jquery-1.8.2.min.js" type="text/javascript"></script>
<!--<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>-->
<script src="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/jquery/jquery-ui.min1.9.1.js" type="text/javascript"></script>
<!--     <script src="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/jquery/jquery-ui-1.8.23.custom.min.js"></script>-->
<!--<script src="../../devappwithfullcanlendar/js/jquery-ui-1.8.6.custom.min.js"></script>-->
<script src="${pageContext.request.contextPath}/Datemanagement/jQuery-Timepicker-Addon-master/jquery-ui-timepicker-addon.js"></script>
<script src="${pageContext.request.contextPath}/Datemanagement/jQuery-Timepicker-Addon-master/jquery-ui-sliderAccess.js"></script>
<script src="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/jquery/datepicker-zh.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/Datemanagement/jQuery-Timepicker-Addon-master/jquery-ui-timepicker-addon.css"
    rel="stylesheet" />
<!--<link href="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/demos/css/redmond/jquery-ui-1.8.1.custom.css" rel="stylesheet" type="text/css" />-->
<script src="${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/fullcalendar/fullcalendar.js" type="text/javascript"></script>
<!--<script type='text/javascript' src='${pageContext.request.contextPath}/Datemanagement/fullcalendar-1.5.4/fullcalendar/fullcalendar.min.js'></script>-->
<script type='text/javascript'>

    $(document).ready(function () {

        //  $("#hid").timepicker();
        $("#start").timepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'hh:mm', hourMin: 5, hourMax: 24, hourGrid: 3, minuteGrid: 15, timeText: '时间', hourText: '时', minuteText: '分', timeOnlyTitle: '选择时间', onClose: function (dateText, inst) {
            if ($('#start').val() != '') {
                var testStartDate = $('#start').datetimepicker('getDate');
                var testEndDate = $('#end').datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    $('#end').datetimepicker('setDate', testStartDate);
            } else {
                $('#end').val(dateText);
            }
        },
            onSelect: function (selectedDateTime) {
                $('#end').datetimepicker('option', 'minDate', $('#end').datetimepicker('getDate'));
            }
        });
        $("#end").datetimepicker({ dateFormat: 'yy-mm-dd', hourMin: 5, hourMax: 23, hourGrid: 3, minuteGrid: 15, timeText: '时间', hourText: '时', minuteText: '分', onClose: function (dateText, inst) {
            if ($('#start').val() != '') {
                var testStartDate = $('#start').datetimepicker('getDate');
                var testEndDate = $("#end").datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    $('#start').datetimepicker('setDate', testEndDate);
            } else {
                $('#start').val(dateText);
            }
        },
            onSelect: function (selectedDateTime) {
                $('#start').datetimepicker('option', 'maxDate', $("#end").datetimepicker('getDate'));
            }
        });
        $("#addhelper").hide();

        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        $('#calendar').fullCalendar({
            theme: true,
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
         
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
            dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
            today: ["今天"],
            firstDay: 1,
            buttonText: {
                today: '本月',
                month: '月',
                week: '周',
                day: '日',
                prev: '上一月',
                next: '下一月'
            },
            viewDisplay: function (view) {//动态把数据查出，按照月份动态查询
                var viewStart = $.fullCalendar.formatDate(view.start, "yyyy-MM-dd HH:mm");
                var viewEnd = $.fullCalendar.formatDate(view.end, "yyyy-MM-dd HH:mm");
                $("#calendar").fullCalendar('removeEvents');
                $.post("${pageContext.request.contextPath}/findReminds.action", { start: viewStart, end: viewEnd }, function (data) {
            
                    var resultCollection = jQuery.parseJSON(data);
                    $.each(resultCollection, function (index, term) {
                        $("#calendar").fullCalendar('renderEvent', term, true);
                    });

                }); //把从后台取出的数据进行封装以后在页面上以fullCalendar的方式进行显示
            },
            editable: true,//判断该日程能否拖动
            dayClick: function (date, allDay, jsEvent, view) {//日期点击后弹出的jq ui的框，添加日程记录
                var selectdate = $.fullCalendar.formatDate(date, "yyyy-MM-dd");//选择当前日期的时间转换
                $("#end").datetimepicker('setDate', selectdate);//给时间空间赋值
                $("#reservebox").dialog({
                    autoOpen: false,
                    height: 450,
                    width: 400,
                    title: 'Reserve meeting room on ' + selectdate,
                    modal: true,
                    position: "center",
                    draggable: false,
                    beforeClose: function (event, ui) {
                        //$.validationEngine.closePrompt("#meeting");
                        //$.validationEngine.closePrompt("#start");
                        //$.validationEngine.closePrompt("#end");
                    },
                    timeFormat: 'HH:mm{ - HH:mm}',

                    buttons: {
                        "close": function () {
                            $(this).dialog("close");
                        },
                        "reserve": function () {

                            var startdatestr = $("#start").val(); //开始时间
                            var enddatestr = $("#end").val(); //结束时间
                            var confid = $("#title").val(); //标题
                            var det = $("#details").val(); //内容 
                            var cd = $("#chengdu").val(); //重要程度 
                            var id2;
                            var startdate = $.fullCalendar.parseDate(selectdate + "T" + startdatestr);//时间和日期拼接
                            var enddate = $.fullCalendar.parseDate(enddatestr);
                            var schdata = { title: confid, fullname: confid, description: det, confname: cd, confshortname: 'M1', start: selectdate + ' ' + startdatestr, end: enddatestr };
                            $.ajax({
                                type: "POST", //使用post方法访问后台

                                url: "${pageContext.request.contextPath}/addRemind.action", //要访问的后台地址
                                data: schdata, //要发送的数据
                                success: function (data) {
                                    //对话框里面的数据提交完成，data为操作结果
                                    id2 = data;
                                    var schdata2 = { title: confid, fullname: confid, description: det, confname: cd, confshortname: 'M1', start: selectdate + ' ' + startdatestr, end: enddatestr, id: id2 };
                                    $('#calendar').fullCalendar('renderEvent', schdata2, true);
                                    $("#start").val(''); //开始时间
                                    $("#end").val(''); //结束时间
                                    $("#title").val(''); //标题
                                    $("#details").val(''); //内容 
                                    $("#chengdu").val(''); //重要程度 

                                }
                            });


                            $(this).dialog("close");


                        }

                    }
                });
                $("#reservebox").dialog("open");
                return false;
            },
            titleFormat: "yyyyMMMMdddd",
            loading: function (bool) {
                if (bool) $('#loading').show();
                else $('#loading').hide();
            },
            eventAfterRender: function (event, element, view) {//数据绑定上去后添加相应信息在页面上
                var fstart = $.fullCalendar.formatDate(event.start, "HH:mm");
                var fend = $.fullCalendar.formatDate(event.end, "HH:mm");
                //element.html('<a href=#><div>Time: ' + fstart + "-" +  fend + '</div><div>Room:' + event.confname + '</div><div style=color:#E5E5E5>Host:' +  event.fullname + "</div></a>");


                var confbg = '';
                if (event.confid == 1) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else if (event.confid == 2) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else if (event.confid == 3) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else if (event.confid == 4) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else if (event.confid == 5) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else if (event.confid == 6) {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                } else {
                    confbg = confbg + '<span class="fc-event-bg"></span>';
                }

              //  var titlebg = '<span class="fc-event-conf" style="background:' + event.confcolor + '">' + event.confshortname + '</span>';

//                if (event.repweeks > 0) {
//                    titlebg = titlebg + '<span class="fc-event-conf" style="background:#fff;top:0;right:15;color:#3974BC;font-weight:bold">R</span>';
//                }

                if (view.name == "month") {//按月份
                    var evtcontent = '<div class="fc-event-vert"><a>';
                    evtcontent = evtcontent + confbg;
                    evtcontent = evtcontent + '<span class="fc-event-titlebg">' + fstart + " - " + fend + event.fullname + '</span>';
//                    evtcontent = evtcontent + '<span>Room: ' + event.confname + '</span>';
//                    evtcontent = evtcontent + '<span>Host: ' + event.fullname + '</span>';
    //  evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
                    element.html(evtcontent);
                } else if (view.name == "agendaWeek") {//按周
                    var evtcontent = '<a>';
                    evtcontent = evtcontent + confbg;
                    evtcontent = evtcontent + '<span class="fc-event-time">' + fstart + "-" + fend  + '</span>';
                    evtcontent = evtcontent + '<span>'+ event.fullname + '</span>';
                    //evtcontent = evtcontent + '<span>' +  event.fullname + '</span>';
         //  evtcontent = evtcontent + '</a><span class="ui-icon ui-icon-arrowthick-2-n-s"><div class="ui-resizable-handle ui-resizable-s"></div></span>';
                    element.html(evtcontent);
                } else if (view.name == "agendaDay") {//按日
                    var evtcontent = '<a>';
                    evtcontent = evtcontent + confbg;
                    evtcontent = evtcontent + '<span class="fc-event-time">' + fstart + " - " + fend +  '</span>';
    //              evtcontent = evtcontent + '<span>Room: ' + event.confname + '</span>';
  //                evtcontent = evtcontent + '<span>Host: ' + event.fullname + '</span>';
//                    evtcontent = evtcontent + '<span>Topic: ' + event.topic + '</span>';
                 // evtcontent = evtcontent + '</a><span class="ui-icon ui-icon-arrow-2-n-s"><div class="ui-resizable-handle ui-resizable-s"></div></span>';
                    element.html(evtcontent);
                }
            },
            eventMouseover: function (calEvent, jsEvent, view) {
                var fstart = $.fullCalendar.formatDate(calEvent.start, "yyyy/MM/dd HH:mm");
                var fend = $.fullCalendar.formatDate(calEvent.end, "yyyy/MM/dd HH:mm");
                $(this).attr('title', fstart + " - " + fend + " " + "标题" + " : " + calEvent.title);
                $(this).css('font-weight', 'normal');
                $(this).tooltip({
                    effect: 'toggle',
                    cancelDefault: true
                });
            },

            eventClick: function (event) {
                var fstart = $.fullCalendar.formatDate(event.start, "HH:mm");
                var fend = $.fullCalendar.formatDate(event.end, "HH:mm");
                //  var schdata = { sid: event.sid, deleted: 1, uid: event.uid };
                var selectdate = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd");
                $("#start").val(fstart); ;
                $("#end").datetimepicker('setDate', event.end);


                $("#title").val(event.title); //标题
                $("#details").val(event.description); //内容 
                $("#chengdu").val(event.confname); //重要程度 



                $("#reservebox").dialog({
                    autoOpen: false,
                    height: 450,
                    width: 400,
                    title: 'Reserve meeting room on ',
                    modal: true,
                    position: "center",
                    draggable: false,
                    beforeClose: function (event, ui) {
                        //$.validationEngine.closePrompt("#meeting");
                        //$.validationEngine.closePrompt("#start");
                        //$.validationEngine.closePrompt("#end");
                        $("#start").val(''); //开始时间
                        $("#end").val(''); //结束时间
                        $("#title").val(''); //标题
                        $("#details").val(''); //内容 
                        $("#chengdu").val(''); //重要程度 
                    },
                    timeFormat: 'HH:mm{ - HH:mm}',

                    buttons: {
                        "删除": function () {
                            var aa = window.confirm("警告：确定要删除记录，删除后无法恢复！");
                            if (aa) {
                                var para = { id: event.id };


                                $.ajax({
                                    type: "POST", //使用post方法访问后台

                                    url: "${pageContext.request.contextPath}/removeRemind.action", //要访问的后台地址
                                    data: para, //要发送的数据
                                    success: function (data) {
                                        //对话框里面的数据提交完成，data为操作结果


                                        $('#calendar').fullCalendar('removeEvents', event.id);
                                    }


                                });

                            }
                            $(this).dialog("close");
                        },
                        "reserve": function () {

                            var startdatestr = $("#start").val(); //开始时间
                            var enddatestr = $("#end").val(); //结束时间
                            var confid = $("#title").val(); //标题
                            var det = $("#details").val(); //内容 
                            var cd = $("#chengdu").val(); //重要程度 
                            var startdate = $.fullCalendar.parseDate(selectdate + "T" + startdatestr);
                            var enddate = $.fullCalendar.parseDate(enddatestr);

                            event.fullname = confid;
                            event.confname = cd;
                            event.start = startdate;
                            event.end = enddate;
                            event.description = det;
                            var id2;

                            var schdata = { title: confid, fullname: confid, description: det, confname: cd, confshortname: 'M1', start: selectdate + ' ' + startdatestr, end: enddatestr, id: event.id };
                            $.ajax({
                                type: "POST", //使用post方法访问后台

                                url: "${pageContext.request.contextPath}/updateRemind.action", //要访问的后台地址
                                data: schdata, //要发送的数据
                                success: function (data) {
                                    //对话框里面的数据提交完成，data为操作结果

                                    var schdata2 = { title: confid, fullname: confid, description: det, confname: cd, confshortname: 'M1', start: selectdate + ' ' + startdatestr, end: enddatestr, id: event.id };
                                    $('#calendar').fullCalendar('updateEvent', event);
                                }
                            });





                            $(this).dialog("close");
                        }

                    }
                });
                $("#reservebox").dialog("open");
                return false;
            },
            //            events: "../../sr/AccessDate.ashx"
            events: []
        });



        //goto date function
        if ($.browser.msie) {
            $("#calendar .fc-header-right table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" style="border-right:0px;padding:1px 3px 2px;" ><input type="text" id="selecteddate" size="10" style="padding:0px;"></div></td><td><div class="ui-state-default ui-corner-left ui-corner-right"><a><span id="selectdate" class="ui-icon ui-icon-search">goto</span></a></div></td><td><span class="fc-header-space"></span></td>');
        } else {
            $("#calendar .fc-header-right table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" style="border-right:0px;padding:3px 2px 4px;" ><input type="text" id="selecteddate" size="10" style="padding:0px;"></div></td><td><div class="ui-state-default ui-corner-left ui-corner-right"><a><span id="selectdate" class="ui-icon ui-icon-search">goto</span></a></div></td><td><span class="fc-header-space"></span></td>');
        }

        $("#selecteddate").datepicker({
            dateFormat: 'yy-mm-dd',
            beforeShow: function (input, instant) {
                setTimeout(
							function () {
							    $('#ui-datepicker-div').css("z-index", 15);
							}, 100
						);
            }
        });



        $("#selectdate").click(function () {
            var selectdstr = $("#selecteddate").val();
            var selectdate = $.fullCalendar.parseDate(selectdstr, "yyyy-mm-dd");
            $('#calendar').fullCalendar('gotoDate', selectdate.getFullYear(), selectdate.getMonth(), selectdate.getDate());
        });


        // conference function
        $("#calendar .fc-header-left table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" id="selectmeeting"><a><span id="selectdate" class="ui-icon ui-icon-search" style="float: left;padding-left: 5px; padding-top:1px"></span>meeting room</a></div></td><td><span class="fc-header-space"></span></td>');



        //        $(".fc-button-prev").click(function () {
        //            alert("fasdf");
        //        });

    });

 

</script>
<style type='text/css'>
    .ui-datepicker
    {
        width: 23em;
        padding: .2em .2em 0;
        font-size: 70%;
        display: none;
    }
    
    #calendar
    {
        width: 900px;
        margin: 0 auto;
    }
    #loading
    {
        top: 0px;
        right: 0px;
    }
    
    .tooltip
    {
        padding-bottom: 25px;
        padding-left: 25px;
        width: 100px !important;
        padding-right: 25px;
        display: none;
        background: #999;
        height: 70px;
        color: red;
        font-size: 12px;
        padding-top: 25px;
        z-order: 10;
    }
    .ui-timepicker-div .ui-widget-header
    {
        margin-bottom: 8px;
    }
    .ui-timepicker-div dl
    {
        text-align: left;
    }
    .ui-timepicker-div dl dt
    {
        height: 25px;
        margin-bottom: -25px;
    }
    .ui-timepicker-div dl dd
    {
        margin: 0 10px 10px 65px;
    }
    .ui-timepicker-div td
    {
        font-size: 90%;
    }
    .ui-tpicker-grid-label
    {
        background: none;
        border: none;
        margin: 0;
        padding: 0;
    }
    .ui-timepicker-rtl
    {
        direction: rtl;
    }
    .ui-timepicker-rtl dl
    {
        text-align: right;
    }
    .ui-timepicker-rtl dl dd
    {
        margin: 0 65px 10px 10px;
    }
</style>
<!--   		<style type="text/css"> 
			body,img,p,h1,h2,h3,h4,h5,h6,form,table,td,ul,ol,li,dl,dt,dd,pre,blockquote,fieldset,label{
				margin:0;
				padding:0;
				border:0;
			}
			body{ background-color: #777; border-top: solid 10px #7b94b2; font: 90% Arial, Helvetica, sans-serif; padding: 20px; }
			h1,h2,h3{ margin: 10px 0; }
			h1{}
			h2{ color: #f66; }
			h3{ color: #6b84a2; }
			p{ margin: 10px 0; }
			a{ color: #7b94b2; }
			ul,ol{ margin: 10px 0 10px 40px; }
			li{ margin: 4px 0; }
			dl.defs{ margin: 10px 0 10px 40px; }
			dl.defs dt{ font-weight: bold; line-height: 20px; margin: 10px 0 0 0; }
			dl.defs dd{ margin: -20px 0 10px 160px; padding-bottom: 10px; border-bottom: solid 1px #eee;}
			pre{ font-size: 12px; line-height: 16px; padding: 5px 5px 5px 10px; margin: 10px 0; background-color: #e4f4d4; border-left: solid 5px #9EC45F; overflow: auto; }

			.wrapper{ background-color: #ffffff; width: 800px; border: solid 1px #eeeeee; padding: 20px; margin: 0 auto; }
			#tabs{ margin: 20px -20px; border: none; }
			#tabs, #ui-datepicker-div, .ui-datepicker{ font-size: 85%; }
			.clear{ clear: both; }
			
			.example-container{ background-color: #f4f4f4; border-bottom: solid 2px #777777; margin: 0 0 20px 40px; padding: 20px; }
			.example-container input{ border: solid 1px #aaa; padding: 4px; width: 175px; }
		</style> -->
<body>
    <div id="wrap">
        <div id='calendar'>
        </div>
        <div id="reserveinfo" title="Details">
            <div id="revtitle">
            </div>
            <div id="revdesc">
            </div>
        </div>
        <div style="display: none" id="reservebox" title="Reserve meeting room">
            <form id="reserveformID" method="post">
            <div class="sysdesc">
                &nbsp;</div>
            <div class="rowElem">
                <label>
                    标题:</label>
                <input id="title" name="start">
            </div>
            <div class="rowElem">
                <label>
                    重要程度:</label>
                <input id="chengdu" name="start">
            </div>
            <div class="rowElem">
                <label>
                    开始时间:</label>
                <input id="start" name="start">
            </div>
            <div class="rowElem">
                <label>
                    结束时间:</label>
                <input id="end" name="end">
            </div>
            <div class="rowElem">
                <label>
                    备忘内容:</label>
                <textarea id="details" rows="3" cols="43" name="details"></textarea>
            </div>
            <div class="rowElem">
            </div>
            <div class="rowElem">
            </div>
            <div id="addhelper" class="ui-widget">
                <div style="padding-bottom: 5px; padding-left: 5px; padding-right: 5px; padding-top: 5px"
                    class="ui-state-error ui-corner-all">
                    <div id="addresult">
                    </div>
                </div>
            </div>
            </form>
        </div>
       
    </div>
</body>
</html>
