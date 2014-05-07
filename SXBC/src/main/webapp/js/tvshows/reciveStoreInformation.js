$(document).ready(function() {
	$("#basicInfoDiv").tabs();
	if ($("#_tapeStoreDate_id").length != 0) {
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId : '_tapeStoreDate_id',
			widgetType : 'dijit.form.DateTextBox',
			widgetAttrs : {
				promptMessage : '选择日期',
				invalidMessage : '日期错误',
				required : false,
				constraints : {
					datePattern : 'yyyy-MM-dd',
					required : false
				},
				readOnly : false,
				datePattern : 'yyyy-MM-dd',
			}
		}));
	}

	if ($("#_tapeReciveDate_id").length != 0) {
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId : '_tapeReciveDate_id',
			widgetType : 'dijit.form.DateTextBox',
			widgetAttrs : {
				promptMessage : '选择日期',
				invalidMessage : '日期错误',
				required : false,
				constraints : {
					datePattern : 'yyyy-MM-dd',
					required : false
				},
				readOnly : false,
				datePattern : 'yyyy-MM-dd',
			}
		}));
	}

	if ($("#_showDate_id").length != 0) {
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId : '_showDate_id',
			widgetType : 'dijit.form.DateTextBox',
			widgetAttrs : {
				promptMessage : '选择日期',
				invalidMessage : '日期错误',
				required : false,
				constraints : {
					datePattern : 'yyyy-MM-dd',
					required : false
				},
				readOnly : false,
				datePattern : 'yyyy-MM-dd',
			}
		}));
	}
	$("#updateStatus").button().click(function() {

		if (fields.length != 0) {
			var _url = $("#updateStatus").attr("href");
			for ( var i = 0; i < fields.length; i++) {
				/**
				if (fields[i].required && $("#" + fields[i].element).val().length == 0) {
					$("#" + fields[i].element).addClass("ui-state-error").val("日期格式错误").focus(function() {
						$(this).removeClass("ui-state-error").val("");
					});
					return false;
				}
				if ($("#" + fields[i].element).val().length != 0) {
					if (!/^\d{4}-\d{1,2}-\d{1,2}$/.test($("#" + fields[i].element).val())) {
						$("#" + fields[i].element).addClass("ui-state-error").val("日期格式错误").focus(function() {
							$(this).removeClass("ui-state-error").val("");
						});
						return false;
					}
				}
				*/
				if(fields[i].element == '_tapeStoreDate_id'){
					_url += "&sdate=" + $("#" + fields[i].element).val();
				}
				if(fields[i].element == '_tapeReciveDate_id'){
					_url += "&rdate=" + $("#" + fields[i].element).val();
				}
				if(fields[i].element == '_showDate_id'){
					_url += "&wdate=" + $("#" + fields[i].element).val();
				}

			}
			window.location.href = _url;
		} else {
			return false;
		}

	});
});
