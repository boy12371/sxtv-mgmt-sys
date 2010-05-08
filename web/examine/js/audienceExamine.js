var myDataTable;
var cXData;
function initDataTable() {
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};

	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"audience",
	    label :"观众名称",
	    sortable :true
	}, {
		key :"tapeName",
		label :"影带名称"
	}, {
		key :"result",
		label :"评价结果",
		editor: new YAHOO.widget.RadioCellEditor({radioOptions:["看&nbsp;&nbsp;","不看&nbsp;"],disableBtns:true})		
	}, {
		key :"dateExamine",
		label :"评价日期",  
		sortable :true,
		formatter :formatDate
	} ];

	// DataSource instance
	var vedioID = document.getElementById("vedioID").innerHTML;
	var myDataSource = new YAHOO.util.DataSource("/tv/examine/getAudienceExamine.action?tape.id=" + vedioID + "&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : ["id", "tapeID", "audience", "tapeName", "result", "dateExamine", "marked"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateExamine&dir=asc&startIndex=0&results=10", // Initial
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"dateExamine",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"首页",
			lastPageLinkLabel :" 尾页",
			previousPageLinkLabel :" 上一页",
			nextPageLinkLabel :" 下一页",
			template :"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
			pageReportTemplate :"Showing items {startIndex} - {endIndex} of {totalRecords}",
			rowsPerPageOptions : [25, 50,100 ]
		})
		// Enables pagination
	};

	// DataTable instance

	myDataTable = new YAHOO.widget.DataTable("cellediting", myColumnDefs, myDataSource, myConfigs);
	var highlightEditableCell = function(oArgs) { 
			var elCell = oArgs.target; 
			if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) { 
			this.highlightCell(elCell); 
		} 
	}; 
	myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell); 
	myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell); 
	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor); 
	
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	
	myDataTable.subscribe("renderEvent", function() { 
		parent.resizeIframe();
	});
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function addAction(){
	var tapeID = document.getElementById("vedioID").innerHTML;
	var tapeName = document.getElementById("vedioName").innerHTML;
	var audienceName = document.getElementById("audienceName").value;
	if(null==audience || ""==audience){
		jAlert("请输入观众姓名。");
		return;
	}
	if(!document.getElementById("look").checked && !document.getElementById("unlook").checked){
		jAlert("请选择观众评价。");
		return;
	}
	var isAudience = false;
	for(var i=0;i<audience.length;i++){
		if(audienceName == audience[i]){
			isAudience = true;
			break;
		}
	}
	if(!isAudience){
		jAlert(audienceName + "不是一个观众。");
		return;
	}
	var result = document.getElementById("look").checked?"看":"不看";
	var xData = {
		tapeID:tapeID,
		tapeName:tapeName,
		audience:audienceName,
		result:result,
		dateExamine:"",
		//mark this row is new need to be submit. 1:insert 2:update
		marked:1
	};
	cXData = xData;
	var pos = getRecordFormTable(xData);
	if(null != pos){
//		jConfirm(
//			"该观众已经评价过此影带，如果确定，原来的评价将被更新，否则请取消。",
//			"确认",
//			function(r){
//			if(r){
//				var id = myDataTable.getRecord(pos).getData("id");
//				cXData.id = id;
//				myDataTable.deleteRow(pos);  
//				myDataTable.addRow(xData,0);
//			}else{
//				return;
//			}	
//		});
		jAlert(audienceName+"已经输入过评价，若要修改，请在下面表格中修改。", "输入错误");
		return false;
	}else{
		myDataTable.addRow(xData,0);
		parent.resizeIframe();
//		markRow(0);
	}
}

function submitAction(){
	var submitArray = new Array();
	var records = myDataTable.getRecordSet().getRecords();
	for(var i=0;i<records.length;i++){
		var xData = records[i].getData();
		if(xData.result.length>=3 || (typeof(xData.marked)!="undefined" && 1<=xData.marked)){
			if(xData.result.length>=3){
				xData.result = xData.result.replace(/(^\s*)|(\s*$)/g, "");
				xData.dateExamine = "";
			}
			submitArray[submitArray.length] = xData;
		}
	}
	var newResult = document.getElementById("newResult");
	newResult.value = YAHOO.lang.JSON.stringify(submitArray);
	document.forms[0].submit();
}

function getRecordFormTable(xData){
	for(var i=0,record=myDataTable.getRecord(i); null != record; i++,record=myDataTable.getRecord(i)){
		var tData = record.getData();
		if(xData.audience == tData.audience && xData.tapeName == tData.tapeName){
			return i;
		}
	}
	return null;
}
