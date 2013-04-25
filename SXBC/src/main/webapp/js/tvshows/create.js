$(document).ready(function() {
	$("#tvshowCreate").tabs();
	$("input[type=submit], input[type=reset]").button();
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_company_id',
		widgetType : 'dijit.form.FilteringSelect',
		widgetAttrs : {
			hasDownArrow : true
		}
	}));
	
	Spring.addDecoration(new Spring.ElementDecoration({
		elementId : '_theme_id',
		widgetType : 'dijit.form.FilteringSelect',
		widgetAttrs : {
			hasDownArrow : true
		}
	}));
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

	$.ajax({
		url : "/SXBC/tvshows/loadPeopleJsonString",
		dataType : "json",
		success : function(json) {
			$("#_actors_id").tokenInput(json, {
				theme : "facebook",
				propertyToSearch : "name",
				preventDuplicates : true,
				hintText:"键入并搜索",
				tokenFormatter : function(item) {
					return "<li><input type='hidden' name='actors[" + aCount + "].id' value='" + item.id + "'/><p>" + item.name + "</p></li>";
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
			$("#_directors_id").tokenInput(json, {
				theme : "facebook",
				propertyToSearch : "name",
				preventDuplicates : true,
				hintText:"键入并搜索",
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
			$("#_screenwriters_id").tokenInput(json, {
				theme : "facebook",
				propertyToSearch : "name",
				preventDuplicates : true,
				hintText:"键入并搜索",
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
			$("#_publisher_id").tokenInput(json, {
				theme : "facebook",
				propertyToSearch : "name",
				preventDuplicates : true,
				hintText:"键入并搜索",
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
		},
		error : function(xhr) {
			alert("error");
		}
	});
	$("#_projectorComments_id").val(" ");
	$("#_outline_id").val(" ");
	$('#tvshowForm').submit(function() {
		
		var result = [];
		
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
		result[result.length] = ValidateUtil.requiredPattern($("#_priceRange_id"));
		result[result.length] = ValidateUtil.required($("#_publishSchedule_id"));
		result[result.length] = ValidateUtil.required($("#_outline_id"));
		
		for ( var i = 0; i < result.length; i++) {
			if (!result[i]) {
				return false;
			}
		}
	});
});

function eventFuncObj(newValue){
	alert(newValue);
}