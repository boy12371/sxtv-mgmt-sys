/* VARIABLES YOU COULD MODIFY */
	var boxSizeArray = [];	// Array indicating how many items there is rooom
							// for in the right column ULs
	var arrow_offsetX = -5;	// Offset X - position of small arrow
	var arrow_offsetY = 0;	// Offset Y - position of small arrow
	
	var arrow_offsetX_firefox = -6;	// Firefox - offset X small arrow
	var arrow_offsetY_firefox = -13; // Firefox - offset Y small arrow
	
	var verticalSpaceBetweenListItems = 3;	// Pixels space between one <li> and
											// next
											// Same value or higher as margin
											// bottom in CSS for
											// #dhtmlgoodies_dragDropContainer
											// ul li,#dragContent li
	
											
	var indicateDestionationByUseOfArrow = true;	// Display arrow to indicate
													// where object will be
													// dropped(false = use
													// rectangle)

	var cloneSourceItems = false;	// Items picked from main container will be
									// cloned(i.e. "copy" instead of "cut").
	var cloneAllowDuplicates = false;	// Allow multiple instances of an item
										// inside a small box(example: drag
										// Student 1 to team A twice
	
	/* END VARIABLES YOU COULD MODIFY */
	
	var dragDropTopContainer = false;
	var dragTimer = -1;
	var dragContentObj = false;
	var contentToBeDragged = false;	// Reference to dragged <li>
	var contentToBeDragged_src = false;	// Reference to parent of <li> before
										// drag started
	var contentToBeDragged_next = false; 	// Reference to next sibling of <li>
											// to be dragged
	var destinationObj = false;	// Reference to <UL> or <LI> where element is
								// dropped.
	var dragDropIndicator = false;	// Reference to small arrow indicating where
									// items will be dropped
	var ulPositionArray = new Array();
	var mouseoverObj = false;	// Reference to highlighted DIV
	
	var MSIE = navigator.userAgent.indexOf('MSIE')>=0?true:false;
	var navigatorVersion = navigator.appVersion.replace(/.*?MSIE (\d\.\d).*/g,'$1')/1;

	
	var indicateDestinationBox = false;
	function getTopPos(inputObj)
	{		
	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetTop;
	  }
	  return returnValue;
	}
	
	function getLeftPos(inputObj)
	{
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetLeft;
	  }
	  return returnValue;
	}
		
	function cancelEvent()
	{
		return false;
	}
	function initDrag(e)	// Mouse button is pressed down on a LI
	{
		if(document.all)e = event;
		var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		var sl = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
		
		dragTimer = 0;
		dragContentObj.style.left = e.clientX + sl + 'px';
		dragContentObj.style.top = e.clientY + st + 'px';
		contentToBeDragged = this;
		contentToBeDragged_src = this.parentNode;
		contentToBeDragged_next = false;
		if(this.nextSibling){
			contentToBeDragged_next = this.nextSibling;
			if(!this.tagName && contentToBeDragged_next.nextSibling)contentToBeDragged_next = contentToBeDragged_next.nextSibling;
		}
		timerDrag();
		return false;
	}
	
	function timerDrag()
	{
		if(dragTimer>=0 && dragTimer<10){
			dragTimer++;
			setTimeout('timerDrag()',10);
			return;
		}
		if(dragTimer==10){
			
			if(cloneSourceItems && contentToBeDragged.parentNode.id=='allItems'){
				newItem = contentToBeDragged.cloneNode(true);
				newItem.onmousedown = contentToBeDragged.onmousedown;
				contentToBeDragged = newItem;
			}
			dragContentObj.style.display='block';
			dragContentObj.appendChild(contentToBeDragged);
		}
	}
	
	function moveDragContent(e)
	{
		if(dragTimer<10){
			if(contentToBeDragged){
				if(contentToBeDragged_next){
					contentToBeDragged_src.insertBefore(contentToBeDragged,contentToBeDragged_next);
				}else{
					contentToBeDragged_src.appendChild(contentToBeDragged);
				}	
			}
			return;
		}
		if(document.all)e = event;
		var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		var sl = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
		
		
		dragContentObj.style.left = e.clientX + sl + 'px';
		dragContentObj.style.top = e.clientY + st + 'px';
		
		if(mouseoverObj)mouseoverObj.className='';
		destinationObj = false;
		dragDropIndicator.style.display='none';
		if(indicateDestinationBox)indicateDestinationBox.style.display='none';
		var x = e.clientX + sl;
		var y = e.clientY + st;
		var width = dragContentObj.offsetWidth;
		var height = dragContentObj.offsetHeight;
		
		var tmpOffsetX = arrow_offsetX;
		var tmpOffsetY = arrow_offsetY;
		if(!document.all){
			tmpOffsetX = arrow_offsetX_firefox;
			tmpOffsetY = arrow_offsetY_firefox;
		}

		for(var no=0;no<ulPositionArray.length;no++){
			var ul_leftPos = ulPositionArray[no]['left'];	
			var ul_topPos = ulPositionArray[no]['top'];	
			var ul_height = ulPositionArray[no]['height'];
			var ul_width = ulPositionArray[no]['width'];
			
			if((x+width) > ul_leftPos && x<(ul_leftPos + ul_width) && (y+height)> ul_topPos && y<(ul_topPos + ul_height)){
				var noExisting = ulPositionArray[no]['obj'].getElementsByTagName('LI').length;
				if(indicateDestinationBox && indicateDestinationBox.parentNode==ulPositionArray[no]['obj'])noExisting--;
				if(noExisting<boxSizeArray[no-1] || no==0){
					dragDropIndicator.style.left = ul_leftPos + tmpOffsetX + 'px';
					var subLi = ulPositionArray[no]['obj'].getElementsByTagName('LI');
					
					var clonedItemAllreadyAdded = false;
					if(cloneSourceItems && !cloneAllowDuplicates){
						for(var liIndex=0;liIndex<subLi.length;liIndex++){
							if(contentToBeDragged.id == subLi[liIndex].id)clonedItemAllreadyAdded = true;
						}
						if(clonedItemAllreadyAdded)continue;
					}
					
					for(var liIndex=0;liIndex<subLi.length;liIndex++){
						var tmpTop = getTopPos(subLi[liIndex]);
						if(!indicateDestionationByUseOfArrow){
							if(y<tmpTop){
								destinationObj = subLi[liIndex];
								indicateDestinationBox.style.display='block';
								subLi[liIndex].parentNode.insertBefore(indicateDestinationBox,subLi[liIndex]);
								break;
							}
						}else{							
							if(y<tmpTop){
								destinationObj = subLi[liIndex];
								dragDropIndicator.style.top = tmpTop + tmpOffsetY - Math.round(dragDropIndicator.clientHeight/2) + 'px';
								dragDropIndicator.style.display='block';
								break;
							}	
						}					
					}
					
					if(!indicateDestionationByUseOfArrow){
						if(indicateDestinationBox.style.display=='none'){
							indicateDestinationBox.style.display='block';
							ulPositionArray[no]['obj'].appendChild(indicateDestinationBox);
						}
						
					}else{
						if(subLi.length>0 && dragDropIndicator.style.display=='none'){
							dragDropIndicator.style.top = getTopPos(subLi[subLi.length-1]) + subLi[subLi.length-1].offsetHeight + tmpOffsetY + 'px';
							dragDropIndicator.style.display='block';
						}
						if(subLi.length==0){
							dragDropIndicator.style.top = ul_topPos + arrow_offsetY + 'px'
							dragDropIndicator.style.display='block';
						}
					}
					
					if(!destinationObj)destinationObj = ulPositionArray[no]['obj'];
					mouseoverObj = ulPositionArray[no]['obj'].parentNode;
					mouseoverObj.className='mouseover';
					return;
				}
			}
		}
	}
	
	/*
	 * End dragging Put <LI> into a destination or back to where it came from.
	 */	
	function dragDropEnd(e)
	{
		if(dragTimer==-1)return;
		if(dragTimer<10){
			dragTimer = -1;
			return;
		}
		dragTimer = -1;
		if(document.all)e = event;	
		
		
		if(cloneSourceItems && (!destinationObj || (destinationObj && (destinationObj.id=='allItems' || destinationObj.parentNode.id=='allItems')))){
			contentToBeDragged.parentNode.removeChild(contentToBeDragged);
		}else{	
			
			if(destinationObj){
				if(destinationObj.tagName=='UL'){
					destinationObj.appendChild(contentToBeDragged);
				}else{
					destinationObj.parentNode.insertBefore(contentToBeDragged,destinationObj);
				}
				mouseoverObj.className='';
				destinationObj = false;
				dragDropIndicator.style.display='none';
				if(indicateDestinationBox){
					indicateDestinationBox.style.display='none';
					document.body.appendChild(indicateDestinationBox);
				}
				contentToBeDragged = false;
				return;
			}		
			if(contentToBeDragged_next){
				contentToBeDragged_src.insertBefore(contentToBeDragged,contentToBeDragged_next);
			}else{
				contentToBeDragged_src.appendChild(contentToBeDragged);
			}
		}
		contentToBeDragged = false;
		dragDropIndicator.style.display='none';
		if(indicateDestinationBox){
			indicateDestinationBox.style.display='none';
			document.body.appendChild(indicateDestinationBox);
			
		}
		mouseoverObj = false;
		
	}
	
	/*
	 * Preparing data to be saved
	 */
	function saveDragDropNodes()
	{
		var saveString = "";
		var mainContainer = document.getElementById('dhtmlgoodies_mainContainer');
		var form =document.forms[0];
		var uls = mainContainer.getElementsByTagName('UL');
		for(var no=0;no<uls.length;no++){	// LOoping through all <ul>
			var lis = uls[no].getElementsByTagName('LI');
			for(var no2=0;no2<lis.length;no2++){
				if(saveString.length>0)saveString = saveString + ";";
				saveString = saveString + uls[no].id + '=' + lis[no2].id;
			}	
		}		
		document.getElementById("orderString").value=saveString;
		document.getElementById('saveContent').innerHTML = '<h1>Ready to save these nodes:</h1> ' + saveString.replace(/;/g,';<br>') + '<p>Format: ID of ul |(pipe) ID of li;(semicolon)</p><p>You can put these values into a hidden form fields, post it to the server and explode the submitted value there</p>';
		form.submit();
	}
	
	function initDragDropScript()
	{
		dragContentObj = document.getElementById('dragContent');
		dragDropIndicator = document.getElementById('dragDropIndicator');
		dragDropTopContainer = document.getElementById('dhtmlgoodies_dragDropContainer');
		document.documentElement.onselectstart = cancelEvent;;
		var listItems = dragDropTopContainer.getElementsByTagName('LI');	// Get
																			// array
																			// containing
																			// all
																			// <LI>
		var itemHeight = false;
		for(var no=0;no<listItems.length;no++){
			listItems[no].onmousedown = initDrag;
			listItems[no].onselectstart = cancelEvent;
			if(!itemHeight)itemHeight = listItems[no].offsetHeight;
			if(MSIE && navigatorVersion/1<6){
				listItems[no].style.cursor='hand';
			}			
		}
		
		var mainContainer = document.getElementById('dhtmlgoodies_mainContainer');
		var uls = mainContainer.getElementsByTagName('UL');
		itemHeight = itemHeight + verticalSpaceBetweenListItems;
		for(var no=0;no<uls.length;no++){
			uls[no].style.height = itemHeight * boxSizeArray[no]  + 'px';
		}
		
		var leftContainer = document.getElementById('dhtmlgoodies_listOfItems');
		var itemBox = leftContainer.getElementsByTagName('UL')[0];
		
		document.documentElement.onmousemove = moveDragContent;	// Mouse move
																// event -
																// moving
																// draggable div
		document.documentElement.onmouseup = dragDropEnd;	// Mouse move event
															// - moving
															// draggable div
		
		var ulArray = dragDropTopContainer.getElementsByTagName('UL');
		for(var no=0;no<ulArray.length;no++){
			ulPositionArray[no] = new Array();
			ulPositionArray[no]['left'] = getLeftPos(ulArray[no]);	
			ulPositionArray[no]['top'] = getTopPos(ulArray[no]);	
			ulPositionArray[no]['width'] = ulArray[no].offsetWidth;
			ulPositionArray[no]['height'] = ulArray[no].clientHeight;
			ulPositionArray[no]['obj'] = ulArray[no];
		}
		
		if(!indicateDestionationByUseOfArrow){
			indicateDestinationBox = document.createElement('LI');
			indicateDestinationBox.id = 'indicateDestination';
			indicateDestinationBox.style.display='none';
			document.body.appendChild(indicateDestinationBox);

			
		}
	}


function initMontSelections(select){
	
	var month = select.value;

	var today =new Date();
	var year = today.getFullYear();	
	var orderDate= new Date(year, month, 0);
	var dateNo = orderDate.getDate();	
	
	var smonth = month.length==2?mounth:"0"+month;
	var playerDateID = year+""+smonth;

	var mainContainer = document.getElementById('dhtmlgoodies_mainContainer');
	var htmlString ="";
	
	for(var i=0; i< dateNo; i++){
		var d = (i+1)>10?(i+1):"0"+(i+1);		
		var innerHtml = "<div><p>"+year+"年"+month+"月"+(i+1)+"日</p><ul id='"+ playerDateID+d +"'></ul></div>";		
		htmlString += innerHtml;
		boxSizeArray[i] = 1;
	}	
	mainContainer.innerHTML = htmlString;
	initDragDropScript();
	
}




function initDataTable(month) {

	var formatLink = function(elCell, oRecord, oColumn, sData) {
			elCell.innerHTML = sData;	
	}

	var formatCompany = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.companyName;
	}
	var formatTopic = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.topicName;
	}
	var formatSubject = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.subjectName;
	}
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	}
	var formatStatus = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.status;
	}
	// Column definitions
	var myColumnDefs = [{
				key : "vedioID.id",
				label : "编号",
				sortable : true,
				formatter : formatLink
			}, {
				key : "vedioID.vedioName",
				label : "剧目名称"
			}, {
				key : "vedioID.topic.topicName",
				label : "题材",
				sortable : true,
				formatter : formatTopic
			}, {
				key : "vedioID.subject.subjectName",
				label : "栏目",
				sortable : true,
				formatter : formatSubject
			}, {
				key : "vedioID.companyID.companyName",
				label : "影视公司",
				sortable : true,
				formatter : formatCompany
			}, {
				key : "vedioID.dateInput",
				label : "收带日期",
				sortable : true
			}, {
				key : "vedioID.status.status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "playDate",
				label : "待播時間"
			},{
				key : "arrangeDate",
				label : "編排時間"
			},	{
				key : "auditor.employee.name",
				label : "編排人"
			},{
				key : "comments",
				label : "备注"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/order/getOrderListForMonth.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : [{key:"vedioID.id"}, {key:"vedioID.vedioName"}, {key:"vedioID.topic.topicName"}, {key:"vedioID.subject.subjectName"}, {key:"vedioID.companyID.companyName"},{key:"vedioID.dateInput"},{key:"vedioID.status.status"},
				"playDate", "arrangeDate", {key:"auditor.employee.name"},"comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	requestBuilder = function(oState, oSelf) {
		/*
		 * We aren't initializing sort and dir variables. If you are using
		 * column sorting built into the DataTable, use this set of variable
		 * initializers. var sort, dir, startIndex, results;
		 */
		var startIndex, results, sort, dir;

		oState = oState || {
			pagination : null,
			sortedBy : null
		};
		var monthValue = YAHOO.util.Dom.get("monthFilter").value;
		/*
		 * If using column sorting built into DataTable, these next two lines
		 * will properly set the current _sortedBy_ column and the
		 * _sortDirection_ sort = (oState.sortedBy) ? oState.sortedBy.key :
		 * oSelf.getColumnSet().keys[0].getKey(); dir = (oState.sortedBy &&
		 * oState.sortedBy.dir === DataTable.CLASS_DESC) ? "desc" : "asc";
		 */
		startIndex = (oState.pagination) ? oState.pagination.recordOffset : 0;
		results = (oState.pagination) ? oState.pagination.rowsPerPage : null;
		sort = (oState.sortedBy)
				? oState.sortedBy.key
				: oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy != null && oState.sortedBy.dir == YAHOO.widget.DataTable.CLASS_DESC)
				? "desc"
				: "asc";
		return "&results=" + results + "&startIndex=" + startIndex + "&sort="
				+ sort + "&dir=" + dir + "&month=" + monthValue;
	}
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=50&month="+month,
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "dateInput",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 50
				}), // Enables pagination
		generateRequest : requestBuilder
	};

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	var fireEvent = function(resetRecordOffset) {
		var oState = myDataTable.getState(), request, oCallback;

		/*
		 * We don't always want to reset the recordOffset. If we want the
		 * Paginator to be set to the first page, pass in a value of true to
		 * this method. Otherwise, pass in false or anything falsy and the
		 * paginator will remain at the page it was set at before.
		 */
		if (resetRecordOffset) {
			oState.pagination.recordOffset = 0;
		}

		/*
		 * If the column sort direction needs to be updated, that may be done
		 * here. It is beyond the scope of this example, but the
		 * DataTable::sortColumn() method has code that can be used with some
		 * modification.
		 */

		/*
		 * This example uses onDataReturnSetRows because that method will clear
		 * out the old data in the DataTable, making way for the new data.
		 */
		oCallback = {
			success : myDataTable.onDataReturnSetRows,
			failure : myDataTable.onDataReturnSetRows,
			argument : oState,
			scope : myDataTable
		};

		// Generate a query string
		request = myDataTable.get("generateRequest")(oState, myDataTable);

		// Fire off a request for new data.
		myDataSource.sendRequest(request, oCallback);
	}

	// DataTable instance
	var filter = YAHOO.util.Dom.get("monthFilter");
	YAHOO.util.Event.addListener(filter, "change", fireEvent);
	return {
		ds : myDataSource,
		dt : myDataTable
	};

}
