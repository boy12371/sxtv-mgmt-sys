function initStatusTable() {	
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"id",
		label :"编号",
		sortable :true,		
	}, {
		key :"status",
		label :"状态"		
	}, {
		key :"comments",
		label :"说明"		
	}
	];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getStatuses.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "status", "comments" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
											// response
	}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=id&dir=asc&startIndex=0&results=10", 
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"id",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {
			rowsPerPage :10
		})
	// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioStatus", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() { 
		parent.resizeIframe();
	});
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}


function initTopicTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	var formatOperation = function(elCell, oRecord, oColumn, sData) {		
		elCell.innerHTML = href;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"id",
		label :"编号",
		sortable :true,
		formatter :formatUrl
	}, {
		key :"topicName",
		label :"题材"		
	}, {
		key :"comments",
		label :"备注"		
	},{
		key:"select",
		label:"操作",
		formatter:"dropdown", 
		dropdownOptions:[{label:"选择",value:"none"},{label:"修改",value:"modify"},{label:"删除",value:"delete"}]
		                 
	}
	];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getTopices.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "topicName", "comments", {key:"select",parser:"string"} ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
											// response
	}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=id&dir=asc&startIndex=0&results=10", 
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"id",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {
			rowsPerPage :10
		})
	// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioTopic", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() { 
		parent.resizeIframe();
	});
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	 myDataTable.subscribe("dropdownChangeEvent", function(oArgs){
         var elDropdown = oArgs.target;
         var oRecord = this.getRecord(elDropdown);
         var opt = elDropdown.value;
         if(opt == "none"){
        	 elDropdown.selectIndex=0;
         }else if(opt == "modify"){
        	 elDropdown.selectedIndex=0;
        	 $.blockUI({ message: $('#hiddenDiv'), css: { width: '475px',top:'45%',left:'25%',cursor:'auto' } });
        	 var subName = YAHOO.util.Dom.get("subName");
        	 var subComments = YAHOO.util.Dom.get("subComments");
        	 var subId = YAHOO.util.Dom.get("subId");
        	 subName.value="";
        	 subComments.value="";
        	 subId.value="";
        	 var yesBtn = YAHOO.util.Dom.get("yes");
        	 YAHOO.util.Event.addListener(yesBtn, "click", function(){
        		 subId.value=oRecord.getData("id");
        		 document.forms[0].submit();        		 
        	 });        	         	 
         }else{
        	 window.location="/tv/";
         }         
     });
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}


function initSubjectTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"id",
		label :"编号",
		sortable :true,
		formatter :formatUrl
	}, {
		key :"subjectName",
		label :""		
	}, {
		key :"comments",
		label :"备注"		
	}
	];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getSubjects.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "subjectName", "comments" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
											// response
	}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=id&dir=asc&startIndex=0&results=10", 
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"id",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {
			rowsPerPage :10
		})
	// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioSubject", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() { 
		parent.resizeIframe();
	});
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}