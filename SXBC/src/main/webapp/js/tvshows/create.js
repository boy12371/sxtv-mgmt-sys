$(document).ready(
		function() {
			$("#tvshowCreate").tabs();
			$("input[type=submit], input[type=reset]").button();
			$("#reset").click(function() {
				$("#company_autoComplete").tokenInput("clear");
				$("#theme_autoComplete").tokenInput("clear");
				$("input[name*='actors[']").parent().remove();
				$("input[name*='directors[']").parent().remove();
				$("input[name*='screenwriters[']").parent().remove();
				$("input[name*='publisher[']").parent().remove();
				$("input[name*='producer[']").parent().remove();
			});
			$("#_outline_id").val("");
			// $("#tvshowForm").validate();
			/*Spring.addDecoration(new Spring.ElementDecoration({
				elementId : '_progress_id',
				widgetType : 'dijit.form.FilteringSelect',
				widgetAttrs : {
					hasDownArrow : true
				}
			}));*/

			/*Spring.addDecoration(new Spring.ElementDecoration({
				elementId : '_projector_id',
				widgetType : 'dijit.form.FilteringSelect',
				widgetAttrs : {
					hasDownArrow : true
				}
			}));*/
			/*
			 * Spring.addDecoration(new Spring.ElementDecoration({ elementId :
			 * '_company_id', widgetType : 'dijit.form.FilteringSelect',
			 * widgetAttrs : { hasDownArrow : true } }));
			 */

			// $("#_company_id").combobox();
			$("#company_autoComplete").tokenInput("/SXBC/tvshows/loadCompanyJsonString", {
				tokenLimit : 1,
				noCache : true,
				hintText : "键入并搜索",
				queryParam : "qname",
				onAdd : function(item) {
					$("#_company_id").val(item.id);
				},
				onDelete : function(){
					$("#_company_id").val("");
				}
			});
			
			$("#theme_autoComplete").tokenInput("/SXBC/tvshows/loadThemeJsonString", {
				noCache : true,
				tokenLimit : 1,
				hintText : "键入并搜索",
				queryParam : "qname",
				onAdd : function(item) {
					$("#_theme_id").val(item.id);
				},
				onDelete: function(){
					$("#_theme_id").val("");
				}
			});

			$("#_producer_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						// theme : "facebook",
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "暂无代表作" : item.achievements;
							return "<li><input type='hidden' name='producer[" + $("input[name*='producer']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='producer']"), function(index, el) {
								el.name = "producer[" + index + "]";
							});
						}
					});
			
			$("#_actors_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						// theme : "facebook",
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "暂无代表作" : item.achievements;
							return "<li><input type='hidden' name='actors[" + $("input[name*='actors']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='actors']"), function(index, el) {
								el.name = "actors[" + index + "]";
							});
						}
					});
			$("#_directors_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						// theme : "facebook",
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "暂无代表作" : item.achievements;
							return "<li><input type='hidden' name='directors[" + $("input[name*='directors']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='directors']"), function(index, el) {
								el.name = "directors[" + index + "]";
							});

						}
					});
			$("#_screenwriters_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						// theme : "facebook",
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "暂无代表作" : item.achievements;
							return "<li><input type='hidden' name='screenwriters[" + $("input[name*='screenwriters']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" +_arch+ "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='screenwriters']"), function(index, el) {
								el.name = "screenwriters[" + index + "]";
							});
						}
					});
			$("#_publisher_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						// theme : "facebook",
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "暂无代表作" : item.achievements;
							return "<li><input type='hidden' name='publisher[" + $("input[name*='publisher'][type='hidden']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='publisher'][type='hidden']"), function(index, el) {
								el.name = "publisher[" + index + "]";
							});
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
							name : $("#people_name").val(),
							achievements : $("#achievements").val()
						};
						$("#" + peopleID).tokenInput("add", _data);
						$("#people_name").val("");
						$("#achievements").val("");
						peopleID = "";
						$("#dialog-people-form").dialog("close");
					} //
					$("#forms").replaceWith(html);
				});
				return false;
			});
			$("a[class=peopleAdd]").click(function() {
				peopleID = $(this).attr("name");
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
				$.post($(this).attr("action") + "?actorIDS=" + $("#_actorIDS_id").val(), $(this).serialize(), function(html) {
					if (html.indexOf("SUCCESS") == -1) {
						alert("数据已存在，不能重复添加.");
						return;
					} else {
						var _id = html.substring(html.indexOf("_") + 1, html.length);
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
						var _data = {
							id : _id,
							name : $("#theme_name").val()
						};
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
			$("#tvshowForm").submit(function() {
				
				var res = true;
				if ($("#_name_id").val().length == 0) {
					$("#_name_id").addClass("ui-state-error").val("请输入剧目名称").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				if ($("#_count_id").val().length == 0 || !$.isNumeric($("#_count_id").val())) {
					$("#_count_id").addClass("ui-state-error").val("请输入集数").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				if ($("#_theme_id").val().length == 0 || $("#_theme_id").val() == "0") {
					$("#_theme_id").next().addClass("ui-state-error");
					$("#token-input-theme_autoComplete").css("color", "red").val("请输入题材").focus(function() {
						$(this).css("color", "black").val("");
						$("#_theme_id").next().removeClass("ui-state-error");
					});
					res = false;
				}

				if ($("#_company_id").val().length == 0 || $("#_company_id").val() == "0") {
					$("#_company_id").next().addClass("ui-state-error");
					$("#token-input-company_autoComplete").css("color", "red").val("请输入题材").focus(function() {
						$(this).css("color", "black").val("");
						$("#_company_id").next().removeClass("ui-state-error");
					});
					res = false;
				}
				if ($("#_progress_id").val().length == 0) {
					$("#_progress_id").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}
				if ($("input[name*='actors']").length == 0) {
					$("#_actors_id").prev().addClass("ui-state-error");
					$("#token-input-_actors_id").css("color", "red").val("请输入演员").focus(function() {
						$(this).css("color", "black").val("");
						$("#_actors_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}
				if ($("input[name*='directors']").length == 0) {
					$("#_directors_id").prev().addClass("ui-state-error");
					$("#token-input-_directors_id").css("color", "red").val("请输入演员").focus(function() {
						$(this).css("color", "black").val("");
						$("#_directors_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}
				if ($("input[name*='screenwriters']").length == 0) {
					$("#_screenwriters_id").prev().addClass("ui-state-error");
					$("#token-input-_screenwriters_id").css("color", "red").val("请输入演员").focus(function() {
						$(this).css("color", "black").val("");
						$("#_screenwriters_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}
				/*if ($("input[name*='publisher'][type='hidden']").length == 0) {
					$("#_publisher_id").prev().addClass("ui-state-error");
					$("#token-input-_publisher_id").css("color", "red").val("请输入演员").focus(function() {
						$(this).css("color", "black").val("");
						$("#_publisher_id").prev().removeClass("ui-state-error");
					});
					return false;
				}*/
				
				

				if ($("#_projector_id").val().length == 0) {
					$("#_projector_id	").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}
				
				if(!res){
					return res;
				}
				var _url = $(this).attr("action");
				$.post(_url, $(this).serialize(), function(html) {
					var msg = html.substring(html.indexOf("_") + 1, html.length);
					$('#message').attr("class", "");
					$('#message').html(msg);
					if (html.indexOf("SUCCESS") == -1) {
						if (html.indexOf("DUPLICATE") != -1) {
							$('#message').addClass("error").html("添加剧目失败, 剧目信息重复.");
						} else {
							$('#message').addClass("error").html("添加剧目失败.");
						}
					} else {
						var _tid = html.substring(html.indexOf("_") + 1, html.length);
						$('#message').addClass("success").html("剧目 <a href='/SXBC/tvshows/generalInfo/"+_tid+"'>" + $("#_name_id").val() + "</a> 已添加.");
						$('#reset').click();
						$("#_name_id").focus();
						$("#_outline_id").val("");
					}
				});
				return false;
			});
		});
