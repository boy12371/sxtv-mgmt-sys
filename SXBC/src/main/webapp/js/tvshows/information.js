$(document).ready(function() {

	$("#basicInfoDiv").tabs();
	$("#createPrjCommentsLink").button();
	$("#createScoreLink").button();
	$("#createScoreLink2").button();
	$("info_company").tooltip();
	$("#contractTable br").remove();
	$("a[name*=createDeptCommentsLink]").button();
	$("a[name*=rejectTVShowLink]").button().click(function() {
		$("#dialog-rejectTVShowLink").dialog("open");
	});

	$("#dialog-rejectTVShowLink").dialog({
		resizable : false,
		autoOpen : false,
		height : 200,
		width : 320,
		modal : true,
		buttons : {
			"淘汰" : function() {
				if ($("#rejectText").val().length == 0) {
					$("#rejectText").addClass("ui-state-error");
					return;
				}
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/9?reason=" + $("#rejectText").val();
			},
			"取消" : function() {
				$("#rejectText").removeClass("ui-state-error");
				$(this).dialog("close");
			}
		}
	});

	$('a[name="prjRecommendForm"]').button();
	if ($("#confirmationPurchase").length != 0) {
		$("#dialog-confirmationPurchase").dialog({
			resizable : false,
			autoOpen : false,
			height : 180,
			width : 400,
			modal : true,
			buttons : {
				"定片" : function() {
					$(this).dialog("close");
					window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/4";
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			}
		});
		$("#dialog-rejectPurchase").dialog({
			resizable : false,
			autoOpen : false,
			height : 200,
			width : 320,
			modal : true,
			buttons : {
				"淘汰" : function() {
					if ($("#rejectText").val().length == 0) {
						$("#rejectText").addClass("ui-state-error");
						return;
					}
					window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/10?reason=" + $("#rejectText").val();
				},
				"取消" : function() {
					$("#rejectText").removeClass("ui-state-error");
					$(this).dialog("close");
				}
			}
		});
		$("#confirmationPurchase").button().click(function() {
			$("#dialog-confirmationPurchase").dialog("open");
			return false;
		});

		$("#rejectPurchase").button().click(function() {
			$("#dialog-rejectPurchase").dialog("open");
			return false;
		});
	}

	$("#createContract").button();
	$("#contractReject").button().click(function() {
		$("#dialog-contractReject").dialog("open");

	});
	$("#tvshowData").button();
	$("#contractTerminated").button().click(function() {
		$("#dialog-contractTerminated").dialog("open");
	});
	$("#updateStatus").button().click(function() {
		$("#dialog-updateStatus").dialog("open");
	});

	$("#updateStatusFinish").button().click(function() {
		$("#dialog-updateStatusFinish").dialog("open");
	});

	$("#dialog-contractTerminated").dialog({
		resizable : false,
		autoOpen : false,
		height : 200,
		width : 330,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#contractTerminatedText").val().length == 0) {
					$("#contractTerminatedText").addClass("ui-state-error");
					return;
				}
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/12?reason=" + $("#contractTerminatedText").val();
			},
			"取消" : function() {
				$("#contractTerminatedText").removeClass("ui-state-error");
				$(this).dialog("close");
			}
		}
	});

	$("#dialog-updateStatus").dialog({
		resizable : false,
		autoOpen : false,
		height : 160,
		width : 280,
		modal : true,
		buttons : {
			"确定" : function() {
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/6";
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-updateStatusFinish").dialog({
		resizable : false,
		autoOpen : false,
		height : 160,
		width : 280,
		modal : true,
		buttons : {
			"确定" : function() {
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/7";
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-contractReject").dialog({
		resizable : false,
		autoOpen : false,
		height : 200,
		width : 320,
		modal : true,
		buttons : {
			"淘汰" : function() {
				if ($("#contractRejectText").val().length == 0) {
					$("#contractRejectText").addClass("ui-state-error");
					return;
				}
				window.location.href = "/SXBC/tvshows/confirmPurchase/" + $("#itemId").val() + "/11?reason=" + $("#contractRejectText").val();
			},
			"取消" : function() {
				$("#contractRejectText").removeClass("ui-state-error");
				$(this).dialog("close");
			}
		}
	});

	$("#dialog-recommend2other").dialog({
		resizable : false,
		autoOpen : false,
		height : "auto",
		width : 280,
		modal : true,
		buttons : {
			"确定" : function() {
				if ($("#recommendChannel").val().length == 0) {
					alert("请选择频道.");
					return;
				}
				var _url = "/SXBC/front/deptcommentses/create/" + $("#itemId").val() + "/" + $("#recommendChannel").val() + "?toCreate";
				window.location.href = _url;
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("a[name*=updateDeptCommentsLink]").button();
	$("a[name*=channelRecommendFrom]").button();
	
	if ($("a[name*=createMoreDeptComments]").length != 0) {
		$("a[name*=createMoreDeptComments]").button().click(function() {
			$("#recommendChannel option").remove();
			var _url = $(this).attr("href");
			$.getJSON(_url, function(data) {
				if (data.length != 0) {
					for ( var i = 0; i < data.length; i++) {
						$("#recommendChannel").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
					}
					$("#dialog-recommend2other").dialog("open");
				} else {
					alert("其他平台还没有评分，需要先评分才能推荐！");
				}
			});
			return false;
		});

	}

	if ($("#createMoreDeptComments").length != 0) {
		$("#createMoreDeptComments").button().click(function() {
			alert("其他平台还没有评分，需要先评分才能推荐！");
			return false;
		});
	}

	if ($("#div_scores").length != 0) {
		if ($("#dialog-scores").length != 0) {
			$("#dialog-scores").dialog({
				resizable : false,
				autoOpen : false,
				height : 170,
				width : 400,
				modal : true,
				buttons : {
					"推荐" : function() {
						$(this).dialog("close");
						window.location.href = "/SXBC/front/deptcommentses/create/" + $("#itemId").val() + "/3?toCreate";
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	}

});
