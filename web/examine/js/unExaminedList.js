var myDataTable;

function initDataTable() {
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};
	var formatSelect = function(elCell, oRecord, oColumn, sData) {
		var data = encodeURIComponent(oRecord.getData().id);
		var sel = document.createElement("select");
		sel.id = "scoreSel_" + data;
		sel.options.add(new Option("选择评价类型","0")); 
		sel.options.add(new Option("专业人员打分","1")); 
		sel.options.add(new Option("普通观众评价","2"));
		if(parent.userInfo.strRoles.indexOf("2")!=-1){
			sel.options.add(new Option("打分完成","3"));
		}
		sel.onchange = eval("(1,function(){selFunc(\"" + data + "\");})");
		elCell.appendChild(sel);
	};
	function selFunc(vedioID){
		var sel = document.getElementById("scoreSel_" + vedioID);
		var index=sel.selectedIndex;
		var val = sel.options[index].value;
		if("1" == val){
			window.location="/tv/examine/toExamineTape.action?tapeScore.vedioID=" + vedioID;
		}else if("2" == val){
			window.location="/tv/examine/toAudienceExamine.action?tape.id=" + vedioID;
		}else{
			window.location="/tv/examine/completeExamine.action?tapeScore.vedioID=" + vedioID;
		}
	}
	
	var formatProgress = function(elCell, oRecord, oColumn, sData) {
		var infoDiv = document.createElement("div");
		infoDiv.align="center";
		
		var examinedNames = oRecord.getData("examinedEmployees");
		var unexaminedNames = oRecord.getData("unexaminedEmployees");
		var numBar = document.createElement("div");
		numBar.innerHTML = examinedNames.length + " / " + (examinedNames.length+unexaminedNames.length);
		infoDiv.appendChild(numBar);
		
		var progressBar = document.createElement("div");
		infoDiv.appendChild(progressBar);
		progressBar.className = "progressBar";
		progressBar.align="left";
		
		var okBar = document.createElement("div");
		progressBar.appendChild(okBar);
		okBar.className = "okBar";
		
		var percent = examinedNames.length/(examinedNames.length+unexaminedNames.length)*100;
		okBar.style.width = percent + "%";
		
		var title = "已打分人员：";
		for(var i=0;i<examinedNames.length;i++){
			title += examinedNames[i] + ", "
		}
		if("," == title.charAt(title.length-2)){
			title = title.substring(0,title.length-2);
		}
		title += "    \n未打分人员：";
		for(var i=0;i<unexaminedNames.length;i++){
			title += unexaminedNames[i] + ", "
		}
		if("," == title.charAt(title.length-2)){
			title = title.substring(0,title.length-2);
		}

		elCell.title = title;
		elCell.appendChild(infoDiv);
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"id",
	    label :"影带编号",
	    sortable :true
	}, {
		key :"vedioName",
		label :"影带名称",
		sortable :true
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
		key :"status",
		label :"打分进度",
		formatter : formatProgress
	}, {
		key :"companyID",
		label :"公司",
		sortable :true
	}, {
		key :"",
		lable :"影带评价",
		formatter :formatSelect
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/examine/getUnExaminedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "subject", "topic", "dateComing", "status", "companyID", "examinedEmployees", "unexaminedEmployees" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateComing&dir=asc&startIndex=0&results=25", // Initial
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"dateComing",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"第一页",
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

	myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	};
	myDataTable.subscribe("renderEvent", function() { 
		parent.resizeIframe();
	});
	
	myDataSource.subscribe("dataErrorEvent", function(request,callback){
		displayErrorMsg(eval(request.response.responseText));
	});
	
	myDataSource.subscribe("requestEvent", function(request,callback){
		clearErrorMsg();
	});
	
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function filterFunc(){
	var vid = YAHOO.util.Dom.get("vid").value; 
	var vname= YAHOO.util.Dom.get("searchinput").value;
	var callback = {
			success:myDataTable.onDataReturnInitializeTable,
			failure:myDataTable.onDataReturnInitializeTable,
			argument:myDataTable.getState(),
			scope:myDataTable
			};
	myDataTable.getDataSource().sendRequest(
			"sort=dateComing&dir=asc&startIndex=0&results=10&vid="+vid+"&vname="+vname,
			callback
			);
}