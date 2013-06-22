$(document).ready(function() {
	$("#tvshowCreate").tabs();
	$("input[type=submit], input[type=reset]").button();
	/*
	 * Spring.addDecoration(new Spring.ElementDecoration({ elementId :
	 * '_company_id', widgetType : 'dijit.form.FilteringSelect', widgetAttrs : {
	 * hasDownArrow : true } }));
	 */

	// $("#_company_id").combobox();
	$("#company_autoComplete").tokenInput(companyItems, {
		noCache: true,
		tokenLimit : 1,
		hintText : "键入并搜索",
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
			ValidateUtil.showError($('#token-input-company_autoComplete'),"必填字段");
		}
	});
	/*
	 * Spring.addDecoration(new Spring.ElementDecoration({ elementId :
	 * '_theme_id', widgetType : 'dijit.form.FilteringSelect', widgetAttrs : {
	 * hasDownArrow : true } }));
	 */
	$("#theme_autoComplete").tokenInput(themeItems, {
		noCache: true,
		tokenLimit : 1,
		hintText : "键入并搜索",
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
			ValidateUtil.showError($('#token-input-theme_autoComplete'),"必填字段");
		}
	});
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_progress_id',
		widgetType : 'dijit.form.FilteringSelect',
		widgetAttrs : {
			hasDownArrow : true
		}
	}));

	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_projector_id',
		widgetType : 'dijit.form.FilteringSelect',
		widgetAttrs : {
			hasDownArrow : true
		}
	}));

	$("#_actors_id").tokenInput("/SXBC/tvshows/loadPeopleJsonString", {
		noCache: true,
		theme : "facebook",
		propertyToSearch : "name",
		queryParam : "pname",
		preventDuplicates : true,
		hintText : "键入并搜索",
		tokenFormatter : function(item) {
			return "<li><input type='hidden' name='actors[" + aCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
		},
		onAdd : function(item) {
			aCount++;
			if (aCount != 0) {
				ValidateUtil.hideError($("#token-input-_actors_id"));
			}

		},
		onDelete : function(item) {
			aCount--;
			if (aCount != 0) {
				ValidateUtil.hideError($("#token-input-_actors_id"));
			} else {
				ValidateUtil.showError($("#token-input-_actors_id"), "必填字段");
			}
		}
	});
	$("#_directors_id").tokenInput("/SXBC/tvshows/loadPeopleJsonString", {
		noCache: true,
		theme : "facebook",
		propertyToSearch : "name",
		queryParam : "pname",
		preventDuplicates : true,
		hintText : "键入并搜索",
		tokenFormatter : function(item) {
			return "<li><input type='hidden' name='directors[" + dCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
		},
		onAdd : function(item) {
			dCount++;
			if (dCount != 0) {
				ValidateUtil.hideError($("#token-input-_directors_id"));
			}

		},
		onDelete : function(item) {
			dCount--;
			if (dCount != 0) {
				ValidateUtil.hideError($("#token-input-_directors_id"));
			} else {
				ValidateUtil.showError($("#token-input-_directors_id"), "必填字段");
			}
		}
	});
	$("#_screenwriters_id").tokenInput("/SXBC/tvshows/loadPeopleJsonString", {
		noCache: true,
		theme : "facebook",
		propertyToSearch : "name",
		queryParam : "pname",
		preventDuplicates : true,
		hintText : "键入并搜索",
		tokenFormatter : function(item) {
			return "<li><input type='hidden' name='screenwriters[" + sCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
		},
		onAdd : function(item) {
			sCount++;
			if (sCount != 0) {
				ValidateUtil.hideError($("#token-input-_screenwriters_id"));
			}

		},
		onDelete : function(item) {
			sCount--;
			if (sCount != 0) {
				ValidateUtil.hideError($("#token-input-_screenwriters_id"));
			} else {
				ValidateUtil.showError($("#token-input-_screenwriters_id"), "必填字段");
			}
		}
	});
	$("#_publisher_id").tokenInput("/SXBC/tvshows/loadPeopleJsonString", {
		noCache: true,
		theme : "facebook",
		propertyToSearch : "name",
		queryParam : "pname",
		preventDuplicates : true,
		hintText : "键入并搜索",
		tokenFormatter : function(item) {
			return "<li><input type='hidden' name='publisher[" + pCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
		},
		onAdd : function(item) {
			pCount++;
			if (pCount != 0) {
				ValidateUtil.hideError($("#token-input-_publisher_id"));
			}

		},
		onDelete : function(item) {
			pCount--;
			if (pCount != 0) {
				ValidateUtil.hideError($("#token-input-_publisher_id"));
			} else {
				ValidateUtil.showError($("#token-input-_publisher_id"), "必填字段");
			}
		}
	});

	$("#_projectorComments_id").val(" ");
	$("#_outline_id").val(" ");
	$('#tvshowForm').submit(function() {
		var result = [];
		var test = document.getElementById("_theme_id").selectedIndex != 0;
		if (!test) {
			ValidateUtil.showError($("#token-input-theme_autoComplete"), "必填字段");
		}
		test = document.getElementById("_company_id").selectedIndex != 0;
		if (!test) {
			ValidateUtil.showError($("#token-input-company_autoComplete"), "必填字段");
		}
		var test = $('input[name*="actors["]').length != 0;
		if (!test) {
			ValidateUtil.showError($("#token-input-_actors_id"), "必填字段");
		}

		test = $('input[name*="directors["]').length != 0;

		if (!test) {
			ValidateUtil.showError($("#token-input-_directors_id"), "必填字段");
		}

		test = $('input[name*="screenwriters["]').length != 0;
		if (!test) {
			ValidateUtil.showError($("#token-input-_screenwriters_id"), "必填字段");
		}
		test = $('input[name*="publisher["]').length != 0;
		if (!test) {
			ValidateUtil.showError($("#token-input-_publisher_id"), "必填字段");
		}

		result[result.length] = test;
		result[result.length] = ValidateUtil.required($("#_name_id"));
		result[result.length] = ValidateUtil.requiredDigits($("#_count_id"));
		// result[result.length] =
		// ValidateUtil.requiredPattern($("#_priceRange_id"));
		// result[result.length] =
		// ValidateUtil.required($("#_publishSchedule_id"));
		result[result.length] = ValidateUtil.required($("#_outline_id"));

		for ( var i = 0; i < result.length; i++) {
			if (!result[i]) {
				return false;
			}
		}
	});

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
				peopleID = "";
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
				$("#"+peopleID).tokenInput("add", _data);
				$("#people_name").val("");
				$("#achievements").val("");
				peopleID = "";
				$("#dialog-people-form").dialog("close");
			}
			// $("#forms").replaceWith(html);
		});
		return false;
	});
	$("a[class=peopleAdd]").click(function() {
		peopleID =  $(this).attr("name");
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

});