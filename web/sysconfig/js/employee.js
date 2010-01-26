
function initDataTable(){
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href="<a href='./sys/toUpdateEmployee.action?employee.id=";
		href+=sData;
		href+="'>"+sData+"</a>";						
        elCell.innerHTML =href;
    };
    var formatGender = function(elCell, oRecord, oColumn, sData) {       
        var sex = "<span>女</span>";
        if(sData==1){
	        sex= "<span>男</span>";
	     }
        elCell.innerHTML = sex;
    };
	var formatDate=function(elCell, oRecord, oColumn, sData){
		var idx = sData.indexOf("T");
		if(idx!=-1){
			elCell.innerHTML=sData.substring(0,idx);
		}else{
			elCell.innerHTML=sData;
		}
	}	    
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
						{key :"id",label :"编号", sortable :true,formatter:formatUrl}, 
						{key :"name",label :"姓名",sortable :true},
						{key :"gender",label :"性别",sortable :true,formatter:formatGender},
						{key :"contractDate",label :"入职日期",sortable :true,formatter:formatDate},
						{key :"birthday",label :"生日",sortable :true,formatter:formatDate},
						{key :"tel",label :"电话"},
						{key :"comments",label :"备注"},
					];

	 
		
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource(
			"/tv/sys/getEmployees.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : ["id","name","gender","contractDate","birthday","tel","comments"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server response
		}
	};

	// DataTable configuration
	var myConfigs = {			
		initialRequest :"sort=id&dir=asc&startIndex=0&results=2", // Initial request for first page of data
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"id",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {
			rowsPerPage :2
		})
	// Enables pagination 
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata",
			myColumnDefs, myDataSource, myConfigs);
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