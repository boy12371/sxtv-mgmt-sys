var unArrangeTable;
var arrangeTable;
var selMonth = "";

function selectMonthFunc(self){
	selMonth = YAHOO.util.Dom.get("selectMonth").value; 
	document.getElementById("month").value = selMonth;	
	var callback = {
			success:arrangeTable.onDataReturnInitializeTable,
			failure:arrangeTable.onDataReturnInitializeTable,
			argument:arrangeTable.getState(),
			scope:arrangeTable
			};
	arrangeTable.getDataSource().sendRequest(
			"sort=playDate&dir=asc&month="+selMonth,
			callback
			);
}

var formatDate = function(elCell, oRecord, oColumn, sData) {
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
		img.src="./images/arrange.png"
		img.onclick = eval("(1,function(){addTapeToArrange(\"" + oRecord.getId() + "\");})");
		elCell.appendChild(img);
		elCell.style.padding="2px";
		elCell.style.width="20px";
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"",
		label :"",
		width : 30,
	    formatter :formatAddTape
	}, {
	    key :"vedioID",
	    label :"影带编号",
	    sortable :true
	}, {
		key :"name",
		label :"影带名称"
	}, {
		key :"subject",
		label :"栏目",
		sortable :true
	}, {
		key :"topic",
		label :"题材",
		sortable :true
	}, {
		key :"dateComing",
		label :"收带日期",
		sortable :true,
		formatter :formatDate
	}, {
		key :"company",
		label :"公司",
		sortable :true
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getUnarrangedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vedioID", "name", "subject", "topic", "dateComing", "company" ],
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
		paginator :new YAHOO.widget.Paginator( {rowsPerPage:10})
	};
	// DataTable instance
	unArrangeTable = new YAHOO.widget.DataTable("unArrangeTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	unArrangeTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
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
		img.src="./images/delete.png"
		img.onclick = eval("(1,function(){removeTapeFromArrange(\"" + oRecord.getId() + "\");})");
		elCell.appendChild(img);
		elCell.style.padding="2px";
		elCell.style.width="20px";
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"",
		label :"",
		formatter :formatRemoveTape,
		width:30
	}, {
	    key :"vedioID",
	    label :"影带编号"
	}, {
		key :"name",
		label :"影带名称"
	}, {
		key :"playDate",
		label :"播放日期",
		formatter :formatDate
	}, {
		key :"subject",
		label :"栏目"		
	}, {
		key :"topic",
		label :"题材"		
	}, {
		key :"dateComing",
		label :"收带日期",
		formatter :formatDate
	}, {
		key :"company",
		label :"公司"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getArrangedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vedioID", "name", "playDate", "subject", "topic", "dateComing", "company", "marked"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=playDate&dir=asc&month="+selMonth, // Initial
		dynamicData :true, // Enables dynamic server-driven data
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
				var temp;
				temp = this.srcData.vedioID;
 				arrangeTable.updateCell(srcRecord, "vedioID" ,this.destData.vedioID);
 				arrangeTable.updateCell(destRecord, "vedioID" ,temp);
 				temp = this.srcData.name;
 				arrangeTable.updateCell(srcRecord, "name" ,this.destData.name);
 				arrangeTable.updateCell(destRecord, "name" ,temp);
 				temp = this.srcData.subject;
 				arrangeTable.updateCell(srcRecord, "subject" ,this.destData.subject);
 				arrangeTable.updateCell(destRecord, "subject" ,temp);
 				temp = this.srcData.topic;
 				arrangeTable.updateCell(srcRecord, "topic" ,this.destData.topic);
 				arrangeTable.updateCell(destRecord, "topic" ,temp);
 				temp = this.srcData.company;
 				arrangeTable.updateCell(srcRecord, "company" ,this.destData.company);
 				arrangeTable.updateCell(destRecord, "company" ,temp);
 				
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
	arrangeTable.subscribe("initEvent", function() { 
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
		var x = rSet[i].getData("vedioID");
		if(null == x || "" == x) return i;
	}
	return -1;
}

function addTapeToArrange(rID){
	var xData = unArrangeTable.getRecord(rID).getData();
	unArrangeTable.deleteRow(rID);
	var pos = getFirstVoidRecord(arrangeTable);
	var newRecord = arrangeTable.getRecord(pos);
	arrangeTable.updateCell(newRecord, "vedioID" ,xData.vedioID);
	arrangeTable.updateCell(newRecord, "name" ,xData.name);
	arrangeTable.updateCell(newRecord, "subject" ,xData.subject);
	arrangeTable.updateCell(newRecord, "topic" ,xData.topic);
	arrangeTable.updateCell(newRecord, "company" ,xData.company);
	arrangeTable.updateCell(newRecord, "dateComing" ,xData.dateComing);
	newRecord.getData().marked=1;
	
}

function removeTapeFromArrange(rID){
	var xRecord = arrangeTable.getRecord(rID);
	var xData = xRecord.getData();
	var nData = {};
	nData.vedioID = xData.vedioID;
	nData.name = xData.name;
	nData.subject = xData.subject;
	nData.topic = xData.topic;
	nData.company = xData.company;
	nData.dateComing = xData.dateComing;
	if(null==xData.vedioID || ""==xData.vedioID) return;
	arrangeTable.updateCell(xRecord, "vedioID", "");
	arrangeTable.updateCell(xRecord, "name", "");
	arrangeTable.updateCell(xRecord, "subject", "");
	arrangeTable.updateCell(xRecord, "topic", "");
	arrangeTable.updateCell(xRecord, "company" , "");
	arrangeTable.updateCell(xRecord, "dateComing", "");
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
		if( null != xData.vedioID && "" != xData.vedioID){
			submitArray[submitArray.length] = xData;
		}
	}
	var newResult = document.getElementById("newResult");
	newResult.value = YAHOO.lang.JSON.stringify(submitArray);
	document.forms[0].submit();
}
