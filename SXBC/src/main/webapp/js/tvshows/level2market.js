$(document).ready(function() {
	$("#searchFormDiv").accordion({
		collapsible : true
	});
	$("#printBtn").button().hide();
	$("#printTansBtn").button().hide();

	// $("#searchFormDiv").accordion("option", "active", true);
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
	$("#_id_id").keyup(function() {
		if ($.trim($(this).val()).length >= 1) {
			$("#_name_id").attr("disabled", true);
			$("#_contractNo_id").attr("disabled", true);
			dijit.byId("_progress_id").set("disabled", true);
			dijit.byId("_company_id").set("disabled", true);
			dijit.byId("_theme_id").set("disabled", true);
			dijit.byId("_status_id").set("disabled", true);
			dijit.byId("_projector_id").set("disabled", true);
			dijit.byId("_playChannel_id").set("disabled", true);
			dijit.byId("_copyrightFrom_id").set("disabled", true);
			dijit.byId("_copyrightTo_id").set("disabled", true);
			dijit.byId("_playDateStart_id").set("disabled", true);
			dijit.byId("_playDateEnd_id").set("disabled", true);
			if (!$.isNumeric($(this).val())) {
				$("#_id_id").addClass("ui-state-error").val("请输入数字编号").focus(function() {
					$(this).removeClass("ui-state-error").val("");
				});
				return false;
			} else {
				$(this).removeClass("ui-state-error");
			}
		} else {
			$("#_name_id").attr("disabled", false);
			$("#_contractNo_id").attr("disabled", false);
			dijit.byId("_progress_id").set("disabled", false);
			dijit.byId("_company_id").set("disabled", false);
			dijit.byId("_theme_id").set("disabled", false);
			dijit.byId("_status_id").set("disabled", false);
			dijit.byId("_projector_id").set("disabled", false);
			dijit.byId("_playChannel_id").set("disabled", false);
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
		colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '项目负责人', '状态', '首播频道', '价格', '版权起始', '版权终止' ],
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
			name : 'projector',
			index : 'projector',
			width : 100
		}, {
			name : 'status',
			index : 'status',
			formatter : formatStatus,
			width : 100
		}, {
			name : 'playChannel',
			index : 'playChannel',
			width : 100
		}, {
			name : 'price',
			index : 'price',
			width : 100
		}, {
			name : 'copyrightFrom',
			index : 'copyrightFrom',
			width : 100,
			sortable : false
		}, {
			name : 'copyrightEnd',
			index : 'copyrightEnd',
			width : 100,
			sortable : false
		} ],
		altRows : true,
		autowidth : true,
		rowNum : 30,
		rowList : [ 30, 60 ],
		pager : '#pager',
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		caption : "检索结果",
		// hiddengrid : true,
		// gridstate : "hidden",//or visible
		gridComplete : function() {
			var state = jQuery("#dataList").jqGrid('getGridParam', 'gridstate');
			if (state == 'hidden') {
				$(".ui-jqgrid-titlebar-close", jQuery("#dataList")[0].grid.cDiv).click();
			}
		},
		loadComplete : function(data) {
			$("#printBtn").hide();
			$("#printTansBtn").hide();
			var _svalue = $("input[name*=status]").val();
			if (data.records != 0) {
				if (_svalue == 6 || _svalue == 14 || _svalue == 16 || _svalue == 18) {
					switch (parseInt(_svalue)) {
					case 6:
						$("#printBtn").button("option", "label", "打印二轮待选剧目表").show();
						break;
					case 14:
						$("#printBtn").button("option", "label", "打印三轮待选剧目表").show();
						$("#printTansBtn").button("option", "label", "二轮剧目交易表").show();
						break;
					default:
						$("#printBtn").button("option", "label", "打印三轮后待选剧目表").show();
						$("#printTansBtn").button("option", "label", "三轮剧目交易表").show();
						break;
					}
				}
				if (_svalue == 5 || _svalue == 13 || _svalue == 15 || _svalue == 17) {
					switch (parseInt(_svalue)) {
					case 5:
						//$("#printBtn").button("option", "label", "打印二轮待选剧目表").show();
						break;
					case 13:
						$("#printTansBtn").button("option", "label", "二轮剧目交易表").show();
						break;
					case 15:
						$("#printTansBtn").button("option", "label", "三轮剧目交易表").show();
						break;
					default:
						$("#printTansBtn").button("option", "label", "三轮后剧目交易表").show();
						break;
					}
				}
			}
		}
	// ,toolbar : [ true, "bottom" ]

	/*
	 * loadComplete: function(){ }
	 */
	});

	//$("#t_dataList").css("border-bottom", "none").css("text-align", "right").append("<input type='button' value='打印列表' style='height:20px;font-size:-3'/>");
	$("#printBtn").click(function() {
		var queryValues = [];
		queryValues[0] = $("input[name*=id]");
		queryValues[1] = $("input[name*=name]");
		// queryValues[2] = $("input[name*=contractNo]");
		queryValues[2] = $("input[name*=progress]");
		queryValues[3] = $("input[name*=company]");
		queryValues[4] = $("input[name*=theme]");
		queryValues[5] = $("input[name*=projector]");
		queryValues[6] = $("input[name*=status]");
		queryValues[7] = $("input[name*=playChannel]");
		queryValues[8] = $("input[name*=copyrightFrom]");
		queryValues[9] = $("input[name*=copyrightTo]");
		queryValues[10] = $("input[name*=playDateStart]");
		queryValues[11] = $("input[name*=playDateEnd]");
		queryValues[11] = $("input[name*=playDateEnd]");

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (queryValues[i].val() != "" && queryValues[i].val() != 0) {
				if (params.length == 0) {
					params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				} else {
					params += "&" + queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}
			}
		}
		if (params.length == 0) {
			alert("请选择或填写查询条件");
			return;
		}
		var url = "./queryTVShows4JsonLevel2MarketPrint?istransaction=false&" + params;
		// window.location.href = url;
		window.open(url);

	});
	
	$("#printTansBtn").click(function() {
		var queryValues = [];
		queryValues[0] = $("input[name*=id]");
		queryValues[1] = $("input[name*=name]");
		// queryValues[2] = $("input[name*=contractNo]");
		queryValues[2] = $("input[name*=progress]");
		queryValues[3] = $("input[name*=company]");
		queryValues[4] = $("input[name*=theme]");
		queryValues[5] = $("input[name*=projector]");
		queryValues[6] = $("input[name*=status]");
		queryValues[7] = $("input[name*=playChannel]");
		queryValues[8] = $("input[name*=copyrightFrom]");
		queryValues[9] = $("input[name*=copyrightTo]");
		queryValues[10] = $("input[name*=playDateStart]");
		queryValues[11] = $("input[name*=playDateEnd]");
		queryValues[11] = $("input[name*=playDateEnd]");

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (queryValues[i].val() != "" && queryValues[i].val() != 0) {
				if (params.length == 0) {
					params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				} else {
					params += "&" + queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}
			}
		}
		if (params.length == 0) {
			alert("请选择或填写查询条件");
			return;
		}
		var url = "./queryTVShows4JsonLevel2MarketPrint?istransaction=true&" + params;
		// window.location.href = url;
		window.open(url);

	});
	
	
	$(".ui-jqgrid-titlebar-close", jQuery("#dataList")[0].grid.cDiv).click();

	$("#resetBtn").button().click(function() {
		$("#_id_id").val("");
		$("#_name_id").attr("disabled", false);
		$("#_name_id").val("");
		$("#_contractNo_id").attr("disabled", false);
		$("#_contractNo_id").val("");
		dijit.byId("_progress_id").set("disabled", false);
		dijit.byId("_company_id").set("disabled", false);
		dijit.byId("_theme_id").set("disabled", false);
		dijit.byId("_status_id").set("disabled", false);
		dijit.byId("_projector_id").set("disabled", false);
		dijit.byId("_playChannel_id").set("disabled", false);
		dijit.byId("_copyrightFrom_id").set("disabled", false);
		dijit.byId("_copyrightTo_id").set("disabled", false);
		dijit.byId("_playDateStart_id").set("disabled", false);
		dijit.byId("_playDateEnd_id").set("disabled", false);

		dijit.byId("_progress_id").set("value", 0);
		dijit.byId("_company_id").set("value", 0);
		dijit.byId("_theme_id").set("value", 0);
		dijit.byId("_projector_id").set("value", 0);
		dijit.byId("_status_id").set("value", 0);
		dijit.byId("_playChannel_id").set("value", 0);
		dijit.byId("_copyrightFrom_id").set("value", 0);
		dijit.byId("_copyrightTo_id").set("value", 0);
		dijit.byId("_playDateStart_id").set("value", 0);
		dijit.byId("_playDateEnd_id").set("value", 0);

	});
	$("#submitBtn").button().click(function() {
		var queryValues = [];
		queryValues[0] = $("input[name*=id]");
		queryValues[1] = $("input[name*=name]");
		// queryValues[2] = $("input[name*=contractNo]");
		queryValues[2] = $("input[name*=progress]");
		queryValues[3] = $("input[name*=company]");
		queryValues[4] = $("input[name*=theme]");
		queryValues[5] = $("input[name*=projector]");
		queryValues[6] = $("input[name*=status]");
		queryValues[7] = $("input[name*=playChannel]");
		queryValues[8] = $("input[name*=copyrightFrom]");
		queryValues[9] = $("input[name*=copyrightTo]");
		queryValues[10] = $("input[name*=playDateStart]");
		queryValues[11] = $("input[name*=playDateEnd]");
		queryValues[11] = $("input[name*=playDateEnd]");

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (queryValues[i].val() != "" && queryValues[i].val() != 0) {
				if (params.length == 0) {
					params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				} else {
					params += "&" + queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}
			}
		}
		if (params.length == 0) {
			alert("请选择或填写查询条件");
			return;
		}
		var unexpired = $("input[type=checkbox]").attr("checked") == "checked" ? true : false;
		jQuery("#dataList").jqGrid('setGridParam', {
			url : "./queryTVShows4JsonLevel2Market?" + encodeURI(params) + "&unexpired=" + unexpired,
			page : 1
		}).trigger("reloadGrid");
		$("#searchFormDiv").accordion("option", "active", false);
		// jQuery("#dataList").jqGrid('hiddengrid', 'false');
	});

});

function formatName(cellvalue, options, rowObject) {
	return "<a href='/SXBC/tvshows/generalInfo/" + rowObject[0] + "?level=level2market'>" + cellvalue + "</a>";
}

function formatProjector(cellvalue, options, rowObject) {
	return cellvalue.staff;
}

function formatStatus(cellvalue, options, rowObject) {
	return cellvalue.name;
}
