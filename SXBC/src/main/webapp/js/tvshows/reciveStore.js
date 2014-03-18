$(document).ready(function() {
	$("#searchFormDiv").accordion({
		collapsible : true
	});
	$("#printBtn").button().hide();
	// $("#searchFormDiv").accordion("option", "active", true);
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
		}
	});
	jQuery("#dataList").jqGrid({
		url : '#',
		datatype : "json",
		height : 500,
		colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '剧本来源', '项目负责人', '状态', '录入时间' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 40
		}, {
			name : 'name',
			index : 'name',
			width : 120,
			formatter : formatName
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
			width : 130
		}, {
			name : 'projector',
			index : 'projector',
			width : 100
		}, {
			name : 'status',
			index : 'status',
			width : 100
		}, {
			name : 'inputDate',
			index : 'inputDate',
			width : 80
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
			var state = jQuery("#dataList").jqGrid('getGridParam','gridstate');
			if(state == 'hidden'){
				$(".ui-jqgrid-titlebar-close",jQuery("#dataList")[0].grid.cDiv).click();
			}
		}
	/*
	 * loadComplete: function(){ }
	 */
	});
	
	$("#t_dataList").css("border-bottom","none").css("text-align","right").append("<input type='button' value='打印列表' style='height:20px;font-size:-3'/>");
	$("#t_dataList input").click(function(){
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
				if(params.length == 0){
					params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}else{
					params += "&" + queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}
			}
		}
		if(params.length == 0){
			alert("请选择或填写查询条件");
			return;
		}
		var unexpired =  $("input[type=checkbox]").attr("checked") == "checked" ? true : false;
		
		var url = "./queryTVShows4JsonLevel2MarketPrint?" + params + "&unexpired=" + unexpired;
		// window.location.href = url;
		window.open(url);
		
	});
	$(".ui-jqgrid-titlebar-close",jQuery("#dataList")[0].grid.cDiv).click();
	
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
		
		dijit.byId("_progress_id").set("value", 0);
		dijit.byId("_company_id").set("value", 0);
		dijit.byId("_theme_id").set("value", 0);
		dijit.byId("_projector_id").set("value", 0);
		dijit.byId("_status_id").set("value", 0);
		

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
		

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (queryValues[i].val() != "" && queryValues[i].val() != 0) {
				if(params.length == 0){
					params += queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());	
				}else{
					params += "&" + queryValues[i].attr("name") + "=" + $.trim(queryValues[i].val());
				}
			}
		}
		if(params.length == 0){
			alert("请选择或填写查询条件");
			return;
		}
		var unexpired =  $("input[type=checkbox]").attr("checked") == "checked" ? true : false;
		jQuery("#dataList").jqGrid('setGridParam', {
			url : "./queryTVShows4Json?" + encodeURI(params),
			page : 1
		}).trigger("reloadGrid");
		$("#searchFormDiv").accordion("option", "active", false);
		// jQuery("#dataList").jqGrid('hiddengrid', 'false');
	});

});

function formatName(cellvalue, options, rowObject) {
	return "<a href='/SXBC/tvshows/generalInfo/" + rowObject[0] + "?level=reciveStore'>" + cellvalue + "</a>";
}

function formatProjector(cellvalue, options, rowObject) {
	return cellvalue.staff;
}

function formatStatus(cellvalue, options, rowObject) {
	return cellvalue.name;
}
