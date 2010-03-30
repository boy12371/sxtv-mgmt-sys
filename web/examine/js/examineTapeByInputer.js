var myDataTable;

function initDataTable() {
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key :"examiner",
		label :"打分人员"
	}, {
		key :"storyScore",
		label :"情节",
		editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})
	}, {
		key :"techScore",
		label :"技术",
		editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})
	}, {
		key :"performScore",
		label :"表演",
		editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})
	}, {
		key :"innovateScore",
		label :"创新",
		editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})
	}, {
		key :"award",
		label :"获奖情况"
	}, {
		key :"purchase",
		label :"购买意见"
	}, {
		key :"orientation",
		label :"导向"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource( []);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "examiner", "storyScore", "techScore", "performScore", "innovateScore", "award", "purchase", "orientation"]
	};

	myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, {});
	// Update totalRecords on the fly with value from server
	
	myDataTable.subscribe("renderEvent", function() { 
		parent.resizeIframe();
	});
	
	myDataSource.subscribe("dataErrorEvent", function(request,callback){
		displayErrorMsg(eval(request.response.responseText));
	});
	var highlightEditableCell = function(oArgs) { 
		var elCell = oArgs.target; 
		if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) { 
			this.highlightCell(elCell); 
		} 
	}; 
	myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell); 
	myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell); 
	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
	
	myDataSource.subscribe("requestEvent", function(request,callback){
		clearErrorMsg();
	});
	
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function getData(){
	var examiner=document.getElementById("examiners").value;
	var storyScore=document.getElementById("storyScore").value;
	var techScore=document.getElementById("techScore").value;
	var performScore=document.getElementById("performScore").value;
	var innovateScore=document.getElementById("innovateScore").value;
	var purchase=document.getElementById("purchase").value;
	var award=document.getElementById("award").value;
	var orientation=document.getElementById("orientation").value;
	var data={
		examiner:examiner,
		storyScore:storyScore,
		techScore:techScore,
		performScore:performScore,
		innovateScore:innovateScore,
		purchase:purchase,
		award:award,
		orientation:orientation
	};
	return data;
}

function addData(){
	var data = getData();
	myDataTable.addRow(data,0);
}

