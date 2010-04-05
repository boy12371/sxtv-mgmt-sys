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
		editor: new YAHOO.widget.TextboxCellEditor({validator:scoreValidate})
	}, {
		key :"award",
		label :"获奖情况",
		editor: new YAHOO.widget.RadioCellEditor({radioOptions:["推荐","不推荐"],disableBtns:true})
	}, {
		key :"purchase",
		label :"购买意见",
		editor: new YAHOO.widget.RadioCellEditor({radioOptions:["购买","不购买"],disableBtns:true})
	}, {
		key :"orientation",
		label :"导向",
		editor: new YAHOO.widget.RadioCellEditor({radioOptions:["无问题","有问题"],disableBtns:true})
	}];
	
	var scoreValidate = function(inputValue, currentValue, editorInstance){
		var exp=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if(!exp.test(techScore) || parseFloat(techScore, 10)>100){
			return;
		}
		return inputValue;
	}
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
	var purchaseObj=document.getElementById("purchase");
	var purchase= purchaseObj.checked?"购买":"不购买";
	var awardObj=document.getElementById("award");
	var award= awardObj.checked?"推荐":"不推荐";
	var orientationObj=document.getElementById("orientation");
	var orientation= orientationObj.checked?"不合格":"合格";
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
	if(!okAction()) return;
	var data = getData();
	myDataTable.addRow(data,0);
}

function okAction(){
	var storyScore = document.getElementById("storyScore").value;
	var techScore = document.getElementById("techScore").value;
	var performScore = document.getElementById("performScore").value;
	var innovateScore = document.getElementById("innovateScore").value;
	var exp=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	var msg="";
	if(!exp.test(storyScore) || parseFloat(storyScore, 10)>100){
		msg+="故事得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(techScore) || parseFloat(techScore, 10)>100){
		msg+="技术得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(performScore) || parseFloat(performScore, 10)>100){
		msg+="表演得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(innovateScore) || parseFloat(innovateScore, 10)>100){
		msg+="创新得分必须是一个介于0和100之间的数字！<br>";
	}
	if(""!=msg){
		jAlert(msg, "输入错误");
		return false;
	}
	var isExaminer = false;
	var e=document.getElementById("examiners").value;
	for(var i=0;i<examiners.length;i++){
		if(e == examiners[i]){
			isExaminer = true;
			break;
		}
	}
	if(!isExaminer){
		jAlert(e+"不是打分人员。", "输入错误");
		return false;
	}
	if(getExaminerFormTable(e)!=null){
		jAlert(e+"已经输入过打分，若要修改，请在下面表格中修改。", "输入错误");
		return false;
	}
	
	return true;
}

function getExaminerFormTable(name){
	for(var i=0,record=myDataTable.getRecord(i); null != record; i++,record=myDataTable.getRecord(i)){
		var xData = record.getData();
		if(xData.examiner == name){
			return i;
		}
	}
	return null;
}

function submitData(){
	var submitArray = new Array();
	var records = myDataTable.getRecordSet().getRecords();
	for(var i=0;i<records.length;i++){
		var xData = records[i].getData();
		xData.purchase = xData.purchase=="购买"?1:0;
		xData.award = xData.award=="推荐"?1:0;
		xData.orientation = xData.orientation=="不合格"?1:0;
		submitArray[submitArray.length] = xData;
	}
	var newResult = document.getElementById("newResult");
	newResult.value = YAHOO.lang.JSON.stringify(submitArray);
	document.forms[0].submit();
}