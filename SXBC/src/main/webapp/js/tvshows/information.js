$(document).ready(function() {

	$("#basicInfoDiv").tabs();
	$("#createPrjCommentsLink").button();
	$("#createScoreLink").button();
	$("info_company").tooltip();
	$("#contractTable br").remove();
	$("#createDeptCommentsLink").button().click(function() {
		if ($("#dialog-scores").length != 0) {
			$("#dialog-scores").dialog("open");
		} else {
			window.location.href = "/SXBC/front/deptcommentses/create/" + $("#itemId").val() + "/3?toCreate";
		}

	});
	$("#rejectTVShowLink").button().click(function() {
		$("#dialog-rejectTVShowLink").dialog("open");
		return false;
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

	if ($("#createDeptCommentsLinkA").length != 0) {
		$("#createDeptCommentsLinkA").button();
	}
	if ($("#updateDeptCommentsLinkA").length != 0) {
		$("#updateDeptCommentsLinkA").button();
		$("#channelRecommendFromA").button();
	}

	if ($("#createDeptCommentsLinkB").length != 0) {
		$("#createDeptCommentsLinkB").button();
	}
	if ($("#updateDeptCommentsLinkB").length != 0) {
		$("#updateDeptCommentsLinkB").button();
		$("#channelRecommendFromB").button();
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
