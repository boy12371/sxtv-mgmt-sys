<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="../common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="../common/css/common.css" />

<script type="text/javascript" src="../common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="../common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="../common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="../common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="../common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="../common/yui/build/button/button-min.js"></script>
</head>

<body class="yui-skin-sam">

<form id="form1" name="form1" method="post" action="">

<h1>Sign-up form</h1>
<p>This is the basic look of my form without table</p>
<table>
	<tr>
		<td><label>Tape ID</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>
		<td><label>Tape Name</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>
	</tr>
	<tr>
		<td><label>Company Name</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>
		<td><label>Topic</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>
	</tr>
	<tr>
		<td><label>Theme</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>
		<td><label>Date</label></td>
		<td><input type="text" name="textfield" id="textfield" /></td>

	</tr>
	<tr>
		<td><label>Remarks</label></td>
		<td><textarea name="textfield" id="textfield"></textarea>
		</td>
		
		<td></td>
		<td></td>

	</tr>
	<tr>
		<td colspan="4" align="center">
		<span id="go" class="yui-button yui-push-button">
	        <span class="first-child">
	            <button type="button">Go!</button>
	        </span>
        </span>
		</td>
	</tr>
</table>

<h1>Tape information</h1>
<p>This is the basic look of my form without table</p>
<div id="cellediting" align="center"></div>

</form>

<script type="text/javascript">
YAHOO.example.Data = {
		 addresses: [
		             {name:"John A. Smith", address:"1236 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
		             {name:"Joan B. Jones", address:"3271 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
		             {name:"Bob C. Uncle", address:"9996 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
		             {name:"John D. Smith", address:"1623 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
		             {name:"Joan E. Jones", address:"3217 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
		             {name:"Bob F. Uncle", address:"9899 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
		             {name:"Bob R. Uncle", address:"9989 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"}
		         ],
		         
		         stateAbbrs: [
		             "AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL","GA","HI",
		             "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS",
		             "MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR",
		             "PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
		         ]
}

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.InlineCellEditing = function() {
     
        var myColumnDefs = [
            
            {key:"address",editor: new YAHOO.widget.TextareaCellEditor()},
            {key:"city", editor: new YAHOO.widget.TextboxCellEditor({disableBtns:true})},
            {key:"state", editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:YAHOO.example.Data.stateAbbrs,disableBtns:true})},
            {key:"amount", editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})},
            {key:"active", editor: new YAHOO.widget.RadioCellEditor({radioOptions:["yes","no","maybe"],disableBtns:true})},
            {key:"colors", editor: new YAHOO.widget.CheckboxCellEditor({checkboxOptions:["red","yellow","blue"]})},
            {key:"fruit", editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["apple","banana","cherry"]})},
            {key:"last_login", formatter:YAHOO.widget.DataTable.formatDate, editor: new YAHOO.widget.DateCellEditor()}
        ];

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.addresses);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["address","city","state","amount","active","colors","fruit",{key:"last_login",parser:"date"}]
        };

        var myDataTable = new YAHOO.widget.DataTable("cellediting", myColumnDefs, myDataSource, {});

        // Set up editing flow
        var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };
        myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
        myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
        myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);


        getData = function() { 
       		var allData = [];               
            allData.push({address:"aaaaa", city:"xi'an", state:"China",amount:"15",active:"Yes",colors:"black",fruit:"sss",last_login:"04/09/2009"});
            return allData;
           
        };

        var handleClick = function() {            
        	myDataTable.addRow(getData(), 0);            
        }
        
        var btn = new YAHOO.widget.Button("go");
        btn.on("click", handleClick);


        
        
        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});
</script>

</body>

</html>