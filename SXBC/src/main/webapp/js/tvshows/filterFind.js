var timeoutHnd;
$(document).ready(function() {
	$("#searchFormDiv").accordion({
		collapsible : true,
		active : 0
	});
	// $("#searchFormDiv").accordion("option", "active", true);

	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_inputDateStart_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '起始日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_inputDateEnd_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '结束日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_copyrightFrom_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '起始日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_copyrightTo_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '结束日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_playDateStart_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '起始日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_playDateEnd_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '结束日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	
	
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_ctcInputDateEnd_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '合同录入日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_ctcInputDateStart_id',
		widgetType : 'dijit.form.DateTextBox',
		widgetAttrs : {
			promptMessage : '合同录入日期',
			invalidMessage : '日期格式错误',
			required : false,
			constraints : {
				datePattern : 'yyyy-MM-dd',
				required : false
			},
			datePattern : 'yyyy-MM-dd'
		}
	}));

	
	$("#forcePurchaseCKbox").click(function(){
		$("#forcePurchase").val($(this).attr("checked")=="checked" ? 1 : 0);
	});
	$("#_id_id").keyup(function() {
		if ($.trim($(this).val()).length >= 1) {
			$("#_name_id")[0].disabled = true;
			dijit.byId("_progress_id").set("disabled", true);
			dijit.byId("_company_id").set("disabled", true);
			dijit.byId("_theme_id").set("disabled", true);
			dijit.byId("_projector_id").set("disabled", true);
			dijit.byId("_status_id").set("disabled", true);
			dijit.byId("_recommendChannel_id").set("disabled", true);
			dijit.byId("_recommendLevel_id").set("disabled", true);
			dijit.byId("_inputDateStart_id").set("disabled", true);
			dijit.byId("_inputDateEnd_id").set("disabled", true);
			dijit.byId("_copyrightFrom_id").set("disabled", true);
			dijit.byId("_copyrightTo_id").set("disabled", true);
			dijit.byId("_playDateStart_id").set("disabled", true);
			dijit.byId("_playDateEnd_id").set("disabled", true);
		} else {
			$("#_name_id")[0].disabled = false;
			dijit.byId("_progress_id").set("disabled", false);
			dijit.byId("_company_id").set("disabled", false);
			dijit.byId("_theme_id").set("disabled", false);
			dijit.byId("_projector_id").set("disabled", false);
			dijit.byId("_status_id").set("disabled", false);
			dijit.byId("_recommendChannel_id").set("disabled", false);
			dijit.byId("_recommendLevel_id").set("disabled", false);
			dijit.byId("_inputDateStart_id").set("disabled", false);
			dijit.byId("_inputDateEnd_id").set("disabled", false);
			dijit.byId("_copyrightFrom_id").set("disabled", false);
			dijit.byId("_copyrightTo_id").set("disabled", false);
			dijit.byId("_playDateStart_id").set("disabled", false);
			dijit.byId("_playDateEnd_id").set("disabled", false);
		}
		// dijit.byId("_progress_id").set("value", 2);
	});
	jQuery("#dataList").jqGrid({
		url : '#',
		datatype : "json",
		height : 500,
		colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '剧本来源', '项目负责人', '状态', '录入时间', '评分状态' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 40
		}, {
			name : 'name',
			index : 'name',
			width : 120,
			formatter : formatName,
			sortable : false
		}, {
			name : 'count',
			index : 'count',
			width : 40
		}, {
			name : 'company',
			index : 'company',
			width : 100
		}, {
			name : 'theme',
			index : 'theme',
			width : 100
		}, {
			name : 'scriptSrc',
			index : 'scriptSrc',
			// formatter : formatDirectors,
			width : 130,
			sortable : false
		}, {
			name : 'projector',
			index : 'projector',
			width : 100
		}, {
			name : 'status',
			index : 'status',
			width : 100
		}, {
			name : 'input_date',
			index : 'input_date',
			width : 80
		}, {
			name : 'fullScored',
			index : 'fullScored',
			sortable : false,
			formatter : formatFullScored,
			
		} ],
		altRows : true,
		autowidth : true,
		rowNum : 30,
		rowList : [ 30, 60 ],
		pager : '#pager',
		sortname : 'input_date',
		viewrecords : true,
		sortorder : "desc",
		caption : "检索结果",
		gridComplete : function() {
			$("#searchFormDiv").accordion("option", "active", false);
			// $(".ui-jqgrid-titlebar-close",
			// jQuery("#dataList")[0].grid.cDiv).click();
			var state = jQuery("#dataList").jqGrid('getGridParam', 'gridstate');
			if (state == 'hidden') {
				$(".ui-jqgrid-titlebar-close", jQuery("#dataList")[0].grid.cDiv).click();
			}
			// $(this).jqGrid("hideCol","directors");
			// jQuery("#dataList").jqGrid(
		}

	/*
	 * loadComplete: function(){ }
	 */
	});
	$("#resetBtn").button().click(function() {
		$("#_id_id").val("");
		$("#_name_id")[0].disabled = false;
		$("#_name_id").val("");
		dijit.byId("_progress_id").set("disabled", false);
		dijit.byId("_company_id").set("disabled", false);
		dijit.byId("_theme_id").set("disabled", false);
		dijit.byId("_projector_id").set("disabled", false);
		dijit.byId("_status_id").set("disabled", false);
		dijit.byId("_recommendChannel_id").set("disabled", false);
		dijit.byId("_recommendLevel_id").set("disabled", false);
		dijit.byId("_progress_id").set("value", 0);
		dijit.byId("_company_id").set("value", 0);
		dijit.byId("_theme_id").set("value", 0);
		dijit.byId("_projector_id").set("value", 0);
		dijit.byId("_status_id").set("value", 0);
		dijit.byId("_recommendChannel_id").set("value", 0);
		dijit.byId("_recommendLevel_id").set("value", 0);
		dijit.byId("_inputDateStart_id").set("value", "");
		dijit.byId("_inputDateEnd_id").set("value", "");
		dijit.byId("_copyrightFrom_id").set("value", "");
		dijit.byId("_copyrightTo_id").set("value", "");
		dijit.byId("_playDateStart_id").set("value", "");
		dijit.byId("_playDateEnd_id").set("value", "");
		dijit.byId("_ctcInputDateStart_id").set("value", "");
		dijit.byId("_ctcInputDateEnd_id").set("value", "");
		
		if($("#forcePurchaseCKbox").attr("checked")=="checked"){
			$("#forcePurchaseCKbox").attr("checked",false);
			$("#forcePurchase").val("0");
		}
	});
	$("#submitBtn").button().click(function() {
		var queryValues = [];
		queryValues[0] = $("input[name*=id]");
		queryValues[1] = $("input[name*=name]");
		queryValues[2] = $("input[name*=progress]");
		queryValues[3] = $("input[name*=company]");
		queryValues[4] = $("input[name*=theme]");
		queryValues[5] = $("input[name*=projector]");
		queryValues[6] = $("input[name*=status]");
		queryValues[7] = $("input[name*=recommendChannel]");
		queryValues[8] = $("input[name*=recommendLevel]");
		queryValues[9] = $("input[name*=inputDateStart]");
		queryValues[10] = $("input[name*=inputDateEnd]");
		queryValues[11] = $("input[name*=copyrightFrom]");
		queryValues[12] = $("input[name*=copyrightTo]");
		queryValues[13] = $("input[name*=playDateStart]");
		queryValues[14] = $("input[name*=playDateEnd]");
		queryValues[15] = $("input[name*=forcePurchase]");
		queryValues[16] = $("input[name*=ctcInputDateStart]");
		queryValues[17] = $("input[name*=ctcInputDateEnd]");

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (i != 0 && i != queryValues.length) {
				params += "&";
			}
			if (queryValues[i].value != "") {
				params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
			}
		}

		/*
		 * var inputs = $("input[type=text][name!=]"); var hiddens =
		 * $("[type=hidden]"); for ( var i = 0; i < inputs.length; i++) { if
		 * (inputs[i].value.length != 0) { if (i != 0 && i != inputs.length) {
		 * params += "&"; } if (inputs[i].value != "") { params +=
		 * inputs[i].name + "=" + inputs[i].value; } } } params += "&"; for (
		 * var i = 0; i < hiddens.length; i++) { if (hiddens[i].value.length !=
		 * 0) { if (i != 0 && i < hiddens.length) { params += "&"; } params +=
		 * hiddens[i].name + "=" + hiddens[i].value; } }
		 */
		jQuery("#dataList").jqGrid('setGridParam', {
			url : "./queryTVShows4Json?" + encodeURI(params),
			page : 1
		}).trigger("reloadGrid");
	});
	$(".ui-jqgrid-titlebar-close", jQuery("#dataList")[0].grid.cDiv).click();

});

function formatName(cellvalue, options, rowObject) {
	return "<a href='/SXBC/tvshows/generalInfo/" + rowObject[0] + "'>" + cellvalue + "</a>";
}
function formatFullScored(cellvalue, options, rowObject) {
	if(cellvalue == false){
		return "<FONT color='red'>未完成</FONT>"
	}
	return "<FONT>完成</FONT>"
}


function formatDirectors(cellvalue, options, rowObject) {
	var html = "";
	for ( var i = 0; i < cellvalue.length; i++) {
		if (i != 0 && i < cellvalue.length - 1) {
			html += ", ";
		}
		html += cellvalue[i].name;
	}
	return html;
}

function formatProjector(cellvalue, options, rowObject) {
	return cellvalue.staff;
}

function formatStatus(cellvalue, options, rowObject) {
	return cellvalue.name;
}
