function initDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var data = encodeURIComponent(oRecord.getData().vedioID);
		var href = "<a href='./examine/toExamineTape.action?tapeScore.vedioID=";
		href += data;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};
	var formatSelect = function(elCell, oRecord, oColumn, sData) {
		var data = encodeURIComponent(oRecord.getData().vedioID);
		var sel = document.createElement("select");
		sel.id = "scoreSel_" + data;
		sel.options.add(new Option("选择评价类型","0")); 
		sel.options.add(new Option("专业人员打分","1")); 
		sel.options.add(new Option("普通观众评价","2"));
		sel.onchange = eval("(1,function(){selFunc(\"" + data + "\");})");
		elCell.appendChild(sel);
	};
	function selFunc(vedioID){
		var sel = document.getElementById("scoreSel_" + vedioID);
		var index=sel.selectedIndex;
		var val = sel.options[index].value;
		if("1" == val){
			window.location="/tv/examine/toExamineTape.action?tapeScore.vedioID=" + vedioID;
		}else{
			window.location="/tv/examine/toAudienceExamine.action?tape.vedioID=" + vedioID;
		}
	}
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"vedioID",
	    label :"影带编号",
	    formatter :formatUrl
	}, {
		key :"name",
		label :"影带名称",
		formatter :formatUrl
	}, {
		key :"subject",
		label :"栏目"		
	}, {
		key :"topic",
		label :"题材"		
	}, {
		key :"dateComing",
		label :"收带日期",
		sortable :true,
		formatter :formatDate
	}, {
		key :"status",
		label :"状态"
	}, {
		key :"company",
		label :"公司"
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
		fields : [ "vedioID", "name", "subject", "topic", "dateComing", "status", "company" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateComing&dir=asc&startIndex=0&results=10", // Initial
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"dateComing",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {rowsPerPage :10})
		// Enables pagination
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	return {
		ds :myDataSource,
		dt :myDataTable
	};
}