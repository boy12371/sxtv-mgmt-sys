function initDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	
	var formatComments = function(elCell, oRecord, oColumn, sData) {
		sData =sData.substring(0,20);
		elCell.innerHTML=sData;
	}
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"id",
		label :"编号",
		sortable :true,
		formatter :formatUrl
	}, {
		key :"companyName",
		label :"公司名称"		
	}, {
		key :"registrationNo",
		label :"注册号"		
	}, {
		key :"phone",
		label :"电话"
	}, {
		key :"contactPerson",
		label :"联系人"
	}, {
		key :"comments",
		label :"备注",
		formatter :formatComments
	}, ];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getCompanies.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "companyName", "registrationNo", "phone", "contactPerson", "comments" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
											// response
	}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=id&dir=asc&startIndex=0&results=10", // Initial
																	// request
																	// for first
																	// page of
																	// data
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

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
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