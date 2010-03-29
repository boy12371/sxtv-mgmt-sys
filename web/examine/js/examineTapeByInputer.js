function initDataTable() {
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"examiner",
		label :"打分人员"
	}, {
		key :"storyScore",
		label :"情节"
	}, {
		key :"techScore",
		label :"技术"
	}, {
		key :"performScore",
		label :"表演"
	}, {
		key :"innovateScore",
		label :"创新"
	}, {
		key :"award",
		label :"获奖情况"
	}, {
		key :"purchase",
		label :"购买意见"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource( []);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "examiner", "storyScore", "techScore", "performScore", "innovateScore", "award", "purchase"]
	};

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	
	myDataTable.subscribe("renderEvent", function() { 
		parent.resizeIframe();
	});
	
	myDataSource.subscribe("dataErrorEvent", function(request,callback){
		displayErrorMsg(eval(request.response.responseText));
	});
	
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	
	myDataSource.subscribe("requestEvent", function(request,callback){
		clearErrorMsg();
	});
	
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}