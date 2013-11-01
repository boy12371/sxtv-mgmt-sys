var timeoutHnd;
$(document).ready(function() {
	$("#searchFormDiv").accordion({
		collapsible : true
	});
	$("#_id_id").keyup(function() {
		if ($.trim($(this).val()).length >= 1 ) {
			$("#_name_id")[0].disabled = true;
			$("#_priceRange_id")[0].disabled = true;
			dijit.byId("_progress_id").set("disabled", true);
			dijit.byId("_company_id").set("disabled", true);
			dijit.byId("_theme_id").set("disabled", true);
			dijit.byId("_inputDate_id").set("disabled", true);
			dijit.byId("_projector_id").set("disabled", true);
			dijit.byId("_status_id").set("disabled", true);
			dijit.byId("_recommendChannel_id").set("disabled", true);
			dijit.byId("_recommendLevel_id").set("disabled", true);
		} else {
			$("#_name_id")[0].disabled = false;
			$("#_priceRange_id")[0].disabled = false;
			dijit.byId("_progress_id").set("disabled", false);
			dijit.byId("_company_id").set("disabled", false);
			dijit.byId("_theme_id").set("disabled", false);
			dijit.byId("_inputDate_id").set("disabled", false);
			dijit.byId("_projector_id").set("disabled", false);
			dijit.byId("_status_id").set("disabled", false);
			dijit.byId("_recommendChannel_id").set("disabled", false);
			dijit.byId("_recommendLevel_id").set("disabled", false);
		}
		//dijit.byId("_progress_id").set("value", 2);
	});
	jQuery("#dataList").jqGrid({
		url : './queryTVShows4Json',
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
			//formatter : formatDirectors,
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
		sortname : 'inputDate',
		viewrecords : true,
		sortorder : "desc",
		caption : "检索结果",
		gridComplete : function() {
			$("#searchFormDiv").accordion("option", "active", false);
			// $(this).jqGrid("hideCol","directors");
			// jQuery("#dataList").jqGrid(
		}

	/*
	 * loadComplete: function(){ }
	 */
	});
	$("#resetBtn").button().click(function(){
		$("#_id_id").val("");
		$("#_name_id")[0].disabled = false;
		$("#_name_id").val("");
		$("#_priceRange_id")[0].disabled = false;
		$("#_priceRange_id").val("");
		dijit.byId("_progress_id").set("disabled", false);
		dijit.byId("_company_id").set("disabled", false);
		dijit.byId("_theme_id").set("disabled", false);
		dijit.byId("_inputDate_id").set("disabled", false);
		dijit.byId("_projector_id").set("disabled", false);
		dijit.byId("_status_id").set("disabled", false);
		dijit.byId("_recommendChannel_id").set("disabled", false);
		dijit.byId("_recommendLevel_id").set("disabled", false);
		dijit.byId("_progress_id").set("value", 0);
		dijit.byId("_company_id").set("value", 0);
		dijit.byId("_theme_id").set("value", 0);
		dijit.byId("_inputDate_id").set("value",0);
		dijit.byId("_projector_id").set("value", 0);
		dijit.byId("_status_id").set("value", 0);
		dijit.byId("_recommendChannel_id").set("value", 0);
		dijit.byId("_recommendLevel_id").set("value", 0);
		
	});
	$("#submitBtn").button().click(function() {
		var queryValues = [];
		queryValues[0] = $("input[name*=id]");
		queryValues[1] = $("input[name*=name]");
		queryValues[2] = $("input[name*=priceRange]");
		queryValues[3] = $("input[name*=progress]");
		queryValues[4] = $("input[name*=company]");
		queryValues[5] = $("input[name*=theme]");
		queryValues[6] = $("input[name*=inputDate]");
		queryValues[7] = $("input[name*=projector]");
		queryValues[8] = $("input[name*=status]");
		queryValues[9] = $("input[name*=recommendChannel]");
		queryValues[10] = $("input[name*=recommendLevel]");

		var params = "";
		for ( var i = 0; i < queryValues.length; i++) {
			if (i != 0 && i != queryValues.length) {
				params += "&";
			}
			if (queryValues[i].value != "") {
				params += queryValues[i].attr("name") + "=" + queryValues[i].val();
			}
		}
		
	/*	var inputs = $("input[type=text][name!=]");
		var hiddens = $("[type=hidden]");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].value.length != 0) {
				if (i != 0 && i != inputs.length) {
					params += "&";
				}
				if (inputs[i].value != "") {
					params += inputs[i].name + "=" + inputs[i].value;
				}

			}
		}
		params += "&";
		for ( var i = 0; i < hiddens.length; i++) {
			if (hiddens[i].value.length != 0) {
				if (i != 0 && i < hiddens.length) {
					params += "&";
				}
				params += hiddens[i].name + "=" + hiddens[i].value;
			}
		}*/
		jQuery("#dataList").jqGrid('setGridParam', {
			url : "./queryTVShows4Json?" + params,
			page : 1
		}).trigger("reloadGrid");
	});

});

function formatName(cellvalue, options, rowObject) {
	return "<a href='/SXBC/tvshows/generalInfo/" + rowObject[0] + "'>" + cellvalue + "</a>";
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
