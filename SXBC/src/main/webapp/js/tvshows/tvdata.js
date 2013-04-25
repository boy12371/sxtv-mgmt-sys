$(document).ready(function() {
	$("#TVShowData_").validate();
	$("#proceed").button();
});

function getPath(obj) {
	if (obj) {
		if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
			obj.select();
			return document.selection.createRange().text;
		} else {
			alert("请使用IE浏览器读取Excel文件.");
			return;
		}
	}
}

Date.prototype.format = function(format) {
	/*
	 * format="yyyy-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

function isEmpty(val) {
	if (val == "undefined") {
		return true;
	}
}

function readExcel() {
	var filePath = "";
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		document.getElementById("excelFile").select();
		filePath = document.selection.createRange().text;
	} else {
		alert("请使用IE浏览器读取Excel文件.");
		return;
	}
	try {
		var test = new ActiveXObject("Excel.Application");
		// var oWB = oXL.Workbooks.open(filePath);
		test.Quit();
		CollectGarbage();
	} catch (e) {
		alert('请将您浏览器Internet选项中的"对没有标记为安全的ActiveX控件进行初始化和脚本运行"设置为"启用"或"提示"！/n/n然后刷新本页登陆！');
	}

	var oXL = new ActiveXObject("Excel.Application");
	var oWB = oXL.Workbooks.open(filePath);
	var oSheet = oWB.ActiveSheet;
	var rows = oSheet.usedrange.rows.count;// 使用的行数
	var cols = oSheet.usedrange.columns.count;
	var tdata = [];
	var rowCount = 0;
	for ( var i = 2; i <= rows; i++) {
		var row = $("<tr></tr>");
		var x = [];
		for ( var j = 1; j <= cols; j++) {

			if (isEmpty(oSheet.Cells(i, j).value)) {
				alert("数据不能为空");
				oXL.Quit();
				CollectGarbage();
				return;
			}
			var td1 = $("<td></td>");
			if (typeof (oSheet.Cells(i, j).value) == "date") {
				var dd = new Date(oSheet.Cells(i, j).value);
				td1.html(dd.format('yyyy-MM-dd'));
			} else {
				td1.html(oSheet.Cells(i, j).value);
			}

			row.append(td1);
			x[j - 1] = oSheet.Cells(i, j).value;
		}
		tdata[rowCount] = x;
		rowCount++;
		$("#excelTable").append(row);
	}
	$("#excelTable").parent().show();
	$("#tdata").val(tdata.join(";"));
	oSheet = null;
	oWB.close();
	oXL.Quit();
	CollectGarbage();
	oXL = null;
}
