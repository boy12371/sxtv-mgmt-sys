var unArrangeTable;
var arrangeTable;
var selMonth = "";

function selectMonthFunc(self){
	var oState = arrangeTable.getState();
	selMonth = YAHOO.util.Dom.get("selectMonth").value; 
	document.getElementById("month").value = selMonth;	
	var callback = {
			success:arrangeTable.onDataReturnSetRows,
			failure:arrangeTable.onDataReturnSetRows,
			argument:oState,
			scope:arrangeTable
			};
	arrangeTable.getDataSource().sendRequest(
			"sort=playDate&dir=asc&month="+selMonth,
			callback
			);
}

var formatDateArrange = function(elCell, oRecord, oColumn, sData) {
	if(null==sData || ""==sData || sData.indexOf("1000")!=-1){
		elCell.innerHTML = "";
		return;
	}
	var idx = sData.indexOf("T");
	if (idx != -1) {
		elCell.innerHTML = sData.substring(0, idx);
	} else {
		elCell.innerHTML = sData;
	}
};
//unarranged table
function initUnArrangeTable() {
	var formatAddTape = function(elCell, oRecord, oColumn, sData) {
		var img = document.createElement("img");
		img.border = 0;
		img.src="./images/arrange.gif"
		img.onclick = eval("(1,function(){addTapeToArrange(\"" + oRecord.getId() + "\");})");
		img.onmouseover=function(){
			this.src="./images/plus.png";
		}
		img.onmouseout=function(){
			 this.src="./images/arrange.gif";
		}
		elCell.appendChild(img);
		elCell.style.padding="2px";
		elCell.style.width="auto";
		elCell.style.textAlign="center";
	};
	
	var myRowFormatter = function(elTr, oRecord){
		var xData = oRecord.getData();
		var oldCls = elTr.className;
		elTr.title = "";
		if(typeof(xData.orientation)!="undefined" && 0<xData.orientation){
			elTr.className = oldCls + " oriente";
			elTr.title = xData.orientation+"个打分员认为导向有问题。";
		}
		if(typeof(xData.marked)!="undefined" && 9==xData.marked){
			elTr.className = oldCls + " markedRow";
			if("" != elTr.title){
				elTr.title += "\n"+xData.comments;
			}else{
				elTr.title += xData.comments;
			}
		}
		return true;
	};
	
	var sortCustom = function(a, b, desc, field) { 
		if(!YAHOO.lang.isValue(a)) { 
			return (!YAHOO.lang.isValue(b)) ? 0 : 1; 
		} else if(!YAHOO.lang.isValue(b)) { 
			return -1; 
		} 
		//unArrangeTable.sortColumn();
		var comp = YAHOO.util.Sort.compare; 
		var result = comp(a.getData("marked"), b.getData("marked"), true); 
		return (result !== 0) ? result : comp(a.getData(field), b.getData(field), desc); 
	}; 
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"",
		label :"",
		width : 30,
	    formatter :formatAddTape
	}, {
	    key :"id",
	    label :"编号",
	    sortable :true,
	    sortOptions:{sortFunction:sortCustom}
	}, {
		key :"vedioName",
		label :"名称"
	}, {
		key :"subject",
		label :"栏目",
		sortable :true,
		sortOptions:{sortFunction:sortCustom}
	}, {
		key :"topic",
		label :"题材",
		sortable :true,
		sortOptions:{sortFunction:sortCustom}
	}, {
		key :"dateComing",
		label :"收带日期",
		sortable :true,
		formatter :formatDateArrange,
		sortOptions:{sortFunction:sortCustom}
	}, {
		key :"avgScore",
		label :"平均分"
	}, {
		key :"audiScore",
		label :"观众投票(看/不看)"
	}, {
		key :"companyID",
		label :"公司",
		sortable :true,
		sortOptions:{sortFunction:sortCustom}
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getUnarrangedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "subject", "topic", "dateComing", "avgScore", "audiScore", "companyID", "marked", "comments", "orientation"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateComing&dir=asc&startIndex=0", // Initial
		sortedBy : {
			key :"dateComing",
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
		}),
		formatRow: myRowFormatter
	};
	// DataTable instance
	unArrangeTable = new YAHOO.widget.DataTable("unArrangeTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	unArrangeTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	unArrangeTable.subscribe("rowMouseoverEvent", unArrangeTable.onEventHighlightRow);
	unArrangeTable.subscribe("rowMouseoutEvent", unArrangeTable.onEventUnhighlightRow);
	return {
		ds :myDataSource,
		dt :unArrangeTable
	};
}

//arrange table
function initArrangeTable() {
	var formatRemoveTape = function(elCell, oRecord, oColumn, sData) {
		var img = document.createElement("img");
		img.border = 0;
		if(oRecord.getData().marked == -1){
			img.src="./images/remove_disabled.png"
		}else{
			img.src="./images/remove.png"
			img.onclick = eval("(1,function(){removeTapeFromArrange(\"" + oRecord.getId() + "\");})");
			img.onmouseover=function(){
				this.src="./images/minus.png";
			}
			img.onmouseout=function(){
				this.src="./images/remove.png";
			}
		}
		elCell.appendChild(img);
		elCell.style.padding="2px";
		elCell.style.width="auto";
		elCell.style.textAlign="center";
	};
	var myRowStatusChange = function(elTr, oRecord){
		var xData = oRecord.getData();
		var oldCls = elTr.className;
		elTr.title = "";
		if(typeof(xData.marked)!="undefined" && xData.marked==-3){
			elTr.className = oldCls + " markedStatusChange";
			if("" != elTr.title){
				elTr.title += "\n"+xData.comments;
			}else{
				elTr.title += xData.comments;
			}
		}
		return true;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"",
		label :"",
		formatter :formatRemoveTape,
		width:30
	}, {
	    key :"id",
	    label :"编号"
	}, {
		key :"vedioName",
		label :"名称"
	}, {
		key :"playDate",
		label :"播放日期",
		formatter :formatDateArrange
	}, {
		key :"subject",
		label :"栏目"		
	}, {
		key :"topic",
		label :"题材"		
	}, {
		key :"dateComing",
		label :"收带日期",
		formatter :formatDateArrange
	}, {
		key :"avgScore",
		label :"平均分"
	}, {
		key :"audiScore",
		label :"观众投票(看/不看)"
	}, {
		key :"companyID",
		label :"公司"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getArrangedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "playDate", "subject", "topic", "dateComing", "avgScore", "audiScore", "companyID", "marked", "comments"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=playDate&dir=asc&month="+selMonth, // Initial
		dynamicData :true, // Enables dynamic server-driven data
		formatRow: myRowStatusChange,
		sortedBy : {
			key :"playDate",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}// Sets UI initial sort arrow
	};
	// DataTable instance
	arrangeTable = new YAHOO.widget.DataTable("arrangeTableDiv", myColumnDefs, myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	arrangeTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	};
	arrangeTable.subscribe("rowMouseoverEvent", arrangeTable.onEventHighlightRow);
	arrangeTable.subscribe("rowMouseoutEvent", arrangeTable.onEventUnhighlightRow);
	return {
		ds :myDataSource,
		dt :arrangeTable
	};
}

function initArrangeReorderEvent(){
	var Dom = YAHOO.util.Dom;
	var Event = YAHOO.util.Event; 
	var DDM = YAHOO.util.DragDropMgr;
	var myDTDrags = {};
	
	initArrangeTable();
	
	var DDRows = function(id, sGroup, config) { 
		DDRows.superclass.constructor.call(this, id, sGroup, config); 
		Dom.addClass(this.getDragEl(),"custom-class"); 
		this.goingUp = false; 
		this.lastY = 0; 
	}; 
	
	YAHOO.extend(DDRows, YAHOO.util.DDProxy, { 
		proxyEl: null, 
		srcEl:null, 
		destEl:null,
		srcData:null, 
		destData:null,
		srcIndex:null, 
		destIndex:null,			 
		startDrag: function(x, y) { 
			var proxyEl = this.proxyEl = this.getDragEl(), 
				srcEl = this.srcEl = this.getEl(); 		 
			this.srcData = arrangeTable.getRecord(this.srcEl).getData(); 
			this.srcIndex = srcEl.sectionRowIndex; 
			// Make the proxy look like the source element 
			Dom.setStyle(srcEl, "visibility", "hidden"); 
			proxyEl.innerHTML = "<table><tbody>"+srcEl.innerHTML+"</tbody></table>"; 
		}, 		 
		endDrag: function(x,y) { 
			var position;
			if (null != this.destEl && this.destEl.nodeName.toLowerCase() === "tr") { 
				//exchange data of 2 records
				var srcRecord = arrangeTable.getRecord(this.srcIndex);
				var destRecord = arrangeTable.getRecord(this.destIndex);
				if(srcRecord.getData().marked!=-1 && destRecord.getData().marked!=-1){
				var temp;
				temp = this.srcData.id;
 				arrangeTable.updateCell(srcRecord, "id" ,this.destData.id);
 				arrangeTable.updateCell(destRecord, "id" ,temp);
 				temp = this.srcData.vedioName;
 				arrangeTable.updateCell(srcRecord, "vedioName" ,this.destData.vedioName);
 				arrangeTable.updateCell(destRecord, "vedioName" ,temp);
 				temp = this.srcData.subject;
 				arrangeTable.updateCell(srcRecord, "subject" ,this.destData.subject);
 				arrangeTable.updateCell(destRecord, "subject" ,temp);
 				temp = this.srcData.topic;
 				arrangeTable.updateCell(srcRecord, "topic" ,this.destData.topic);
 				arrangeTable.updateCell(destRecord, "topic" ,temp);
 				temp = this.srcData.companyID;
 				arrangeTable.updateCell(srcRecord, "companyID" ,this.destData.companyID);
 				arrangeTable.updateCell(destRecord, "companyID" ,temp);
 				temp = this.srcData.avgScore;
 				arrangeTable.updateCell(srcRecord, "avgScore" ,this.destData.avgScore);
 				arrangeTable.updateCell(destRecord, "avgScore" ,temp);
 				temp = this.srcData.audiScore;
 				arrangeTable.updateCell(srcRecord, "audiScore" ,this.destData.audiScore);
 				arrangeTable.updateCell(destRecord, "audiScore" ,temp);
 				
 				var srcTemp = this.srcData.dateComing;
 				var destTemp = this.destData.dateComing;
 				//if set value as null or "", the action would not take effect.
 				if(null == srcTemp || "" == srcTemp){
 					srcTemp = "1000-01-01";
 				}
 				if(null == destTemp || "" == destTemp){
 					destTemp = "1000-01-01";
 				}
 				arrangeTable.updateCell(srcRecord, "dateComing" ,destTemp);
 				arrangeTable.updateCell(destRecord, "dateComing" ,srcTemp);
 				
 				srcRecord.getData().marked=1;
 				destRecord.getData().marked=1;
				DDM.refreshCache(); 
				}
			} 
			 
			Dom.setStyle(this.proxyEl, "visibility", "hidden"); 
			Dom.setStyle(this.srcEl, "visibility", ""); 
		}, 		 
		onDrag: function(e) { 
			// Keep track of the direction of the drag for use during onDragOver 
			var y = Event.getPageY(e); 
			if (y < this.lastY) { 
				this.goingUp = true; 
			} else if (y > this.lastY) { 
				this.goingUp = false; 
			} 
			this.lastY = y; 
		}, 		
		onDragOver: function(e, id) { 
			// Reorder rows as user drags 
			this.destEl = Dom.get(id);
			this.destIndex = this.destEl.sectionRowIndex;
			this.destData = arrangeTable.getRecord(this.destEl).getData(); 
		} 
	}); 
	arrangeTable.subscribe("renderEvent", function() { 
		var i, id, 
		allRows = this.getTbodyEl().rows; 
		for(i=0; i<allRows.length; i++) { 
			id = allRows[i].id; 
			// Clean up any existing Drag instances 
			if (myDTDrags[id]) { 
				myDTDrags[id].unreg(); 
				delete myDTDrags[id]; 
			} 
			// Create a Drag instance for each row 
			myDTDrags[id] = new DDRows(id); 
		} 
		parent.resizeIframe();
	});
	arrangeTable.subscribe("rowAddEvent",function(e){ 
		var id = e.record.getId(); 
		myDTDrags[id] = new DDRows(id); 
	});
}
//Get the 1st record that has no data in table.
function getFirstVoidRecord(dataTable){
	var rSet = dataTable.getRecordSet().getRecords();
	if(null == rSet) return -1;
	for(var i=0;i<rSet.length;i++){
		var x = rSet[i].getData("id");
		var marked = rSet[i].getData("marked");
		if((null == x || "" == x) && marked != -1) return i;
	}
	return -1;
}

function addTapeToArrange(rID){
	var xData = unArrangeTable.getRecord(rID).getData();
	unArrangeTable.deleteRow(rID);
	var pos = getFirstVoidRecord(arrangeTable);
	if(-1 == pos) return;
	var newRecord = arrangeTable.getRecord(pos);
	arrangeTable.updateCell(newRecord, "id" ,xData.id);
	arrangeTable.updateCell(newRecord, "vedioName" ,xData.vedioName);
	arrangeTable.updateCell(newRecord, "subject" ,xData.subject);
	arrangeTable.updateCell(newRecord, "topic" ,xData.topic);
	arrangeTable.updateCell(newRecord, "companyID" ,xData.companyID);
	arrangeTable.updateCell(newRecord, "dateComing" ,xData.dateComing);
	arrangeTable.updateCell(newRecord, "avgScore" ,xData.avgScore);
	arrangeTable.updateCell(newRecord, "audiScore" ,xData.audiScore);
	newRecord.getData().marked=1;
	
}

function removeTapeFromArrange(rID){
	var xRecord = arrangeTable.getRecord(rID);
	var xData = xRecord.getData();
	var nData = {};
	nData.id = xData.id;
	nData.vedioName = xData.vedioName;
	nData.subject = xData.subject;
	nData.topic = xData.topic;
	nData.companyID = xData.companyID;
	nData.dateComing = xData.dateComing;
	nData.avgScore = xData.avgScore;
	nData.audiScore = xData.audiScore;
	if(null==xData.id || ""==xData.id) return;
	arrangeTable.updateCell(xRecord, "id", "");
	arrangeTable.updateCell(xRecord, "vedioName", "");
	arrangeTable.updateCell(xRecord, "subject", "");
	arrangeTable.updateCell(xRecord, "topic", "");
	arrangeTable.updateCell(xRecord, "companyID" , "");
	arrangeTable.updateCell(xRecord, "dateComing", "");
	arrangeTable.updateCell(xRecord, "avgScore" , "");
	arrangeTable.updateCell(xRecord, "audiScore", "");
	xData.marked = 0;
	unArrangeTable.addRow(nData,0);
	
	//resort unArrange table
	var sortColumn = unArrangeTable.getState().sortedBy.column;
	var dir = unArrangeTable.getState().sortedBy.dir;
	unArrangeTable.sortColumn(sortColumn, dir);
}

function submitAction(){
	var selObj = document.getElementById("selectMonth");
	var index=selObj.selectedIndex;
	var selMonth = selObj.options[index].value;
	document.getElementById("month").value = selMonth;
	
	var submitArray = new Array();
	var records = arrangeTable.getRecordSet().getRecords();
	for(var i=0;i<records.length;i++){
		var xData = records[i].getData();
		if(typeof(xData.marked)=="undefined"){
			xData.marked = 0;
		}
		if( null != xData.id && "" != xData.id){
			submitArray[submitArray.length] = xData;
		}
	}
	var newResult = document.getElementById("newResult");
	newResult.value = YAHOO.lang.JSON.stringify(submitArray);
	document.forms[0].submit();
}
