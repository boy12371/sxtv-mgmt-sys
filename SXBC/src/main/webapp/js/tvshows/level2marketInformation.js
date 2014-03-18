$(document).ready(function() {

	$("#basicInfoDiv").tabs();
	$("info_company").tooltip();

	$("#tvshowData").button();
	$("#contractTerminated").button().click(function() {
		$("#dialog-contractTerminated").dialog("open");
	});
	$("#updateStatus").button().click(function() {
		$("#dialog-updateStatus").dialog("open");
	});

	$("#updateStatusFinish").button().click(function() {
		$("#dialog-updateStatusFinish").dialog("open");
	});

	$("#addLevel2PlayInfo").button();
	$(".delete").click(function(){
		if(confirm("确定要删除吗？")){
			window.location.href= $(this).attr("href");
		}
	});
	if ($("#_playDate_id").length != 0) {
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId : '_playDate_id',
			widgetType : 'dijit.form.DateTextBox',
			widgetAttrs : {
				promptMessage : '选择日期',
				invalidMessage : '日期错误',
				required : true,
				constraints : {
					datePattern : 'yyyy-M-d',
					required : true
				},
				readOnly : false,
				datePattern : 'yyyy-M-d'
			}
		}));
	}

	$("#dialog-contractTerminated").dialog({
		resizable : false,
		autoOpen : false,
		height : 200,
		width : 330,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#contractTerminatedText").val().length == 0) {
					$("#contractTerminatedText").addClass("ui-state-error");
					return;
				}
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/12?reason=" + $("#contractTerminatedText").val();
			},
			"取消" : function() {
				$("#contractTerminatedText").removeClass("ui-state-error");
				$(this).dialog("close");
			}
		}
	});

	$("#dialog-updateStatus").dialog({
		resizable : false,
		autoOpen : false,
		height : 160,
		width : 380,
		modal : true,
		buttons : {
			"确定" : function() {
				var _pdate = $("#_playDate_id").val();
				if (!/^\d{4}-\d{1,2}-\d{1,2}$/.test(_pdate)) {
					$("#_playDate_id").addClass("ui-state-error").val("日期格式错误").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					$(this).dialog("close");
					return false;
				}
				var xx = _pdate.split("-");
				var a = new Date();
				a.setFullYear(xx[0], xx[1] - 1, xx[2]);
				var b = new Date();
				if (a > b) {
					$("#_playDate_id").addClass("ui-state-error").val("播出日期必须早于今天").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					$(this).dialog("close");
					return false;
				}
				var tvid = $("#itemId").val();
				$("#updateStatus").attr("href")
				window.location.href = $("#updateStatus").attr("href")+"&pdate=" + _pdate;
				// $("#itemId").val() + "/6";
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});

	$("#dialog-updateStatusFinish").dialog({
		resizable : false,
		autoOpen : false,
		height : 160,
		width : 280,
		modal : true,
		buttons : {
			"确定" : function() {
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/7?level=level2market";
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("#contractTable br").remove();
});
