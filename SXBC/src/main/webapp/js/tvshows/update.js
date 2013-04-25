$(document).ready(function() {

	$("#TVShow_").validate();

	$.ajax({
		url : "/SXBC/tvshows/loadPeopleJsonString",
		dataType : "json",
		success : function(json) {
			$("#_actors_id").tokenInput(json, {
				theme : "facebook",
				propertyToSearch : "name",
				preventDuplicates : true,
				hintText : "键入并搜索",
				prePopulate : preActors,
				tokenFormatter : function(item) {
					var ahtml = "<li><input type='hidden' name='actors[" + aCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
					aCount++;
					return ahtml;
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
				hintText : "键入并搜索",
				prePopulate : preDirs,
				tokenFormatter : function(item) {
					var dhtml = "<li><input type='hidden' name='directors[" + dCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
					dCount++;
					return dhtml;
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
				hintText : "键入并搜索",
				prePopulate : preSWs,
				tokenFormatter : function(item) {
					var shtml = "<li><input type='hidden' name='screenwriters[" + sCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>";
					sCount++;
					return shtml;
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
				hintText : "键入并搜索",
				prePopulate : prePubs,
				tokenFormatter : function(item) {
					var phtml = "<li><input type='hidden' name='publisher[" + pCount + "]' value='" + item.id + "'/><p>" + item.name + "</p></li>"; 
					pCount++;
					return phtml;
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
			alert("errordddd");
		}
	});
});
