$(document).ready(function() {
	$("#proceed").button();
	$("#dialog-people-form").dialog({
		resizable : false,
		autoOpen : false,
		height : 200,
		width : 360,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#people_name").val().length == 0) {
					$("#people_name").addClass("ui-state-error");
					return;
				}
				$("#peopleAddForm").submit();
			},
			"取消" : function() {
				$("#people_name").removeClass("ui-state-error");
				$("#people_name").val("");
				$("#achievements").val("");
				$(this).dialog("close");
			}
		}
	});

	$("#peopleAddForm").submit(function() {
		$.post($(this).attr("action"), $(this).serialize(), function(html) {
			if (html.indexOf("SUCCESS") == -1) {
				alert("数据已存在，不能重复添加.");
				return;
			} else {
				var _id = html.substring(html.indexOf("_") + 1, html.length);
				var _data = {
					id : _id,
					name : $("#people_name").val()
				};
				// $("#_actors_id").tokenInput("add", _data);
				$("#people_name").val("");
				$("#achievements").val("");
				$("#dialog-people-form").dialog("close");
			}
			// $("#forms").replaceWith(html);
		});
		return false;
	});
	$("a[class=peopleAdd]").click(function() {
		$("#dialog-people-form").dialog("open");
		return false;
	});

	$("#dialog-company-form").dialog({
		resizable : false,
		autoOpen : false,
		height : 300,
		width : 360,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#company_name").val().length == 0) {
					$("#company_name").addClass("ui-state-error");
					return;
				}
				$("#companyAddForm").submit();
			},
			"取消" : function() {
				$("#company_name").removeClass("ui-state-error");
				$("#company_name").val("");
				$("#company_publisher").val("");
				$("#company_telephone").val("");
				$("#company_achievements").val("");
				$(this).dialog("close");
			}
		}
	});

	$("#companyAddForm").submit(function() {
		$.post($(this).attr("action"), $(this).serialize(), function(html) {
			if (html.indexOf("SUCCESS") == -1) {
				alert("数据已存在，不能重复添加.");
				return;
			} else {
				var _id = html.substring(html.indexOf("_") + 1, html.length);
				var varItem = new Option($("#company_name").val(), _id);
				var _comSel = document.getElementById("_company_id");
				_comSel.options.add(varItem);
				var _data = {
					id : _id,
					name : $("#company_name").val()
				};
				var obj = $("#company_autoComplete").tokenInput("get");
				$("#company_autoComplete").tokenInput("remove", obj[0]);
				$("#company_autoComplete").tokenInput("add", _data);
				$("#dialog-company-form").dialog("close");
			}
		});
		return false;
	});

	$("#addCompanyLink").click(function() {
		$("#dialog-company-form").dialog("open");
		return false;
	});

	$("#dialog-theme-form").dialog({
		resizable : false,
		autoOpen : false,
		height : 150,
		width : 300,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#theme_name").val().length == 0) {
					$("#theme_name").addClass("ui-state-error");
					return;
				}
				$("#themeAddForm").submit();
			},
			"取消" : function() {
				$("#theme_name").removeClass("ui-state-error");
				$("#theme_name").val("");
				$(this).dialog("close");
			}
		}
	});

	$("#themeAddForm").submit(function() {
		$.post($(this).attr("action"), $(this).serialize(), function(html) {
			if (html.indexOf("SUCCESS") == -1) {
				alert("数据已存在，不能重复添加.");
				return;
			} else {
				var _id = html.substring(html.indexOf("_") + 1, html.length);
				var varItem = new Option($("#theme_name").val(), _id);
				var _themeSel = document.getElementById("_theme_id");
				_themeSel.options.add(varItem);
				var _data = {
					id : _id,
					name : $("#theme_name").val()
				};
				// $("#_actors_id").tokenInput("add", _data);
				$("#theme_name").val("");
				var obj = $("#theme_autoComplete").tokenInput("get");
				$("#theme_autoComplete").tokenInput("remove", obj[0]);
				$("#theme_autoComplete").tokenInput("add", _data);
				$("#dialog-theme-form").dialog("close");
			}
			// $("#forms").replaceWith(html);
		});
		return false;
	});
	$("#addThemeLink").click(function() {
		$("#dialog-theme-form").dialog("open");
		return false;
	});

	var companyOption = document.getElementById("_company_id");
	var _c = {
		id : companyOption.options[companyOption.selectedIndex].value,
		name : companyOption.options[companyOption.selectedIndex].text
	};
	$("#company_autoComplete").tokenInput(companyItems, {
		tokenLimit : 1,
		hintText : "键入并搜索",
		prePopulate : [_c],
		onAdd : function(item) {
			var _comSel = document.getElementById("_company_id");
			for ( var int = 0; int < _comSel.options.length; int++) {
				if (item.id == _comSel.options[int].value) {
					_comSel.options[int].selected = true;
					break;
				}
			}
			ValidateUtil.hideError($('#token-input-company_autoComplete'));
		},
		onDelete : function(item) {
			ValidateUtil.showError($('#token-input-company_autoComplete'), "必填字段");
		}
	});

	var themeOption = document.getElementById("_theme_id");
	var _t = {
		id : themeOption.options[themeOption.selectedIndex].value,
		name : themeOption.options[themeOption.selectedIndex].text
	};
	$("#theme_autoComplete").tokenInput(themeItems, {
		tokenLimit : 1,
		hintText : "键入并搜索",
		prePopulate : [_t],
		onAdd : function(item) {
			var _comSel = document.getElementById("_theme_id");
			for ( var int = 0; int < _comSel.options.length; int++) {
				if (item.id == _comSel.options[int].value) {
					_comSel.options[int].selected = true;
					break;
				}
			}
			ValidateUtil.hideError($('#token-input-theme_autoComplete'));
		},
		onDelete : function(item) {
			ValidateUtil.showError($('#token-input-theme_autoComplete'), "必填字段");
		}
	});
	
	$("#tvshowForm").submit(function(){
		if(isNaN($("#_publicPrice_id").val())){
			ValidateUtil.showError($('#_publicPrice_id'), "必须是数字");
			return false;
		}
		return true;
	});
	$("#_publicPrice_id").keydown(function(event){
		ValidateUtil.hideError($('#_publicPrice_id'));
	});
	
	$("#tvshowForm").validate();
});
