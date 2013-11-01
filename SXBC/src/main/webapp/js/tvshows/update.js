$(document).ready(
		function() {
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
				$.post($(this).attr("action"), $(this).serialize(), function(html) {
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

			var companyOption = document.getElementById("_company_id");

			$("#company_autoComplete").tokenInput("/SXBC/tvshows/loadCompanyJsonString", {
				tokenLimit : 1,
				noCache : true,
				hintText : "键入并搜索",
				prePopulate : [ _c ],
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
				prePopulate : [ _t ],
				queryParam : "qname",
				onAdd : function(item) {
					$("#_theme_id").val(item.id);
				},
				onDelete: function(){
					$("#_theme_id").val("");
				}
			});

			$("#_actors_id").tokenInput(
					"/SXBC/tvshows/loadPeopleJsonString",
					{
						noCache : true,
						prePopulate : _preActors,
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "<font color='red'>暂无代表作</font>" : item.achievements;
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
						prePopulate : _preDirs,
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "<font color='red'>暂无代表作</font>" : item.achievements;
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
						prePopulate : _preWriters,
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "<font color='red'>暂无代表作</font>" : item.achievements;
							return "<li><input type='hidden' name='screenwriters[" + $("input[name*='screenwriters']").length + "]' value='" + item.id + "'/><p>" + item.name
									+ "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
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
						prePopulate : _prePubs,
						propertyToSearch : "name",
						queryParam : "pname",
						preventDuplicates : true,
						hintText : "键入并搜索",
						tokenFormatter : function(item) {
							var _arch = (item.achievements == null || item.achievements == "") ? "<font color='red'>暂无代表作</font>" : item.achievements;
							return "<li><input type='hidden' name='publisher[" + $("input[name*='publisher'][type='hidden']").length + "]' value='" + item.id + "'/><p>"
									+ item.name + "&nbsp;&nbsp;&nbsp;&nbsp;[" + _arch + "]</p></li>";
						},
						onDelete : function(item) {
							$.each($("input[name*='publisher'][type='hidden']"), function(index, el) {
								el.name = "publisher[" + index + "]";
							});
						}
					});

			Spring.addDecoration(new Spring.ElementDecoration({
				elementId : '_inputDate_id',
				widgetType : 'dijit.form.DateTextBox',
				widgetAttrs : {
					promptMessage : '选择日期',
					invalidMessage : '日期错误',
					required : true,
					constraints : {
						datePattern : 'yyyy-MM-dd',
						required : true
					},
					readOnly : false,
					datePattern : 'yyyy-MM-dd'
				}
			}));

			$("textarea").css("width", "500");

			/*
			 * $("#_inputDate_id").focus(function() { $(this).val(""); });
			 * $("#_inputter_id").focus(function() { $(this).val(""); });
			 * $("#_projector_id").focus(function() { $(this).val(""); });
			 * $("#_status_id").focus(function() { $(this).val(""); });
			 * $("#_progress_id").focus(function() { $(this).val(""); });
			 */
			$("#tvshowForm").submit(function() {
				var res = true;
				if ($("#_count_id").val().length == 0 || !$.isNumeric($("#_count_id").val())) {
					$("#_count_id").addClass("ui-state-error").val("请输入集数").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				if ($("#_ratings_id").val().length != 0) {
					if (!$.isNumeric($("#_ratings_id").val())) {
						$("#_ratings_id").addClass("ui-state-error").val("请输入数字").focus(function() {
							$(this).removeClass("ui-state-error").val("0");
						});
						res = false;
					}
				}
				if ($("#_marketShare_id").val().length != 0) {
					if (!$.isNumeric($("#_marketShare_id").val())) {
						$("#_marketShare_id").addClass("ui-state-error").val("请输入数字").focus(function() {
							$(this).removeClass("ui-state-error").val("0");
						});
						res = false;
					}
				}

				if ($("#_ranking_id").val().length != 0) {
					if (!$.isNumeric($("#_ranking_id").val())) {
						$("#_ranking_id").addClass("ui-state-error").val("请输入数字").focus(function() {
							$(this).removeClass("ui-state-error").val("0");
						});
						res = false;
					}
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

				if ($("input[name*='actors[']").length == 0) {
					$("#_actors_id").prev().addClass("ui-state-error");
					$("#token-input-_actors_id").css("color", "red").val("请输入演员").focus(function() {
						$(this).css("color", "black").val("");
						$("#_actors_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}
				if ($("input[name*='directors']").length == 0) {
					$("#_directors_id").prev().addClass("ui-state-error");
					$("#token-input-_directors_id").css("color", "red").val("请输入导演").focus(function() {
						$(this).css("color", "black").val("");
						$("#_directors_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}
				if ($("input[name*='screenwriters']").length == 0) {
					$("#_screenwriters_id").prev().addClass("ui-state-error");
					$("#token-input-_screenwriters_id").css("color", "red").val("请输入编剧").focus(function() {
						$(this).css("color", "black").val("");
						$("#_screenwriters_id").prev().removeClass("ui-state-error");
					});
					res = false;
				}

				if ($("#_status_id").val().length == 0) {
					$("#_status_id").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				if ($("#_progress_id").val().length == 0) {
					$("#_progress_id").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				var _pdate = $("#_inputDate_id").val();
				if (!/^\d{4}-\d{1,2}-\d{1,2}$/.test(_pdate)) {
					$("#_inputDate_id").addClass("ui-state-error").val("日期格式错误").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}

				if ($("#_inputter_id").val().length == 0) {
					$("#_inputter_id").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}
				if ($("#_projector_id").val().length == 0) {
					$("#_projector_id	").addClass("ui-state-error").val("请选择").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					res = false;
				}
				
				return res;
				// if ($("input[name*='publisher'][type='hidden']").length == 0)
				// {
				// $("#_publisher_id").prev().addClass("ui-state-error");
				// $("#token-input-_publisher_id").css("color",
				// "red").val("请输入").focus(function() {
				// $(this).css("color", "black").val("");
				// $("#_publisher_id").prev().removeClass("ui-state-error");
				// });
				// return false;
				// }
			});

		});
