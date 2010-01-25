<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="application/json; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="../common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="../common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css"
	href="../common/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript"
	src="../common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>

<script type="text/javascript"
	src="../common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript"
	src="../common/yui/build/json/json-min.js"></script>
<script type="text/javascript"
	src="../common/yui/build/element/element-min.js"></script>
<script type="text/javascript"
	src="../common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript"
	src="../common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript"
	src="../common/yui/build/datatable/datatable-min.js"></script>

</head>
<body class="yui-skin-sam">


<div id="dynamicdata"></div>



<script type="text/javascript">
	YAHOO.example.DynamicData = function() {
		// Column definitions
		var myColumnDefs = [ // sortable:true enables sorting
		{
			key :"id",
			label :"ID",
			sortable :true
		}, {
			key :"name",
			label :"Name",
			sortable :true
		} ];

		// DataSource instance
		var myDataSource = new YAHOO.util.DataSource(
				"/tv/sys/getEmployees.action");
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

		myDataSource.responseSchema = {
			resultsList :"records",
			fields : [ {key :"id", parser :"number"}, 
					   {key :"name"} ],
			metaFields : {
				totalRecords :"totalRecords" // Access to value in the server response
			}
		};

		// DataTable configuration
		var myConfigs = {
			initialRequest :"", // Initial request for first page of data
			dynamicData :true, // Enables dynamic server-driven data
			sortedBy : {
				key :"id",
				dir :YAHOO.widget.DataTable.CLASS_ASC
			}, // Sets UI initial sort arrow
			paginator :new YAHOO.widget.Paginator( {
				rowsPerPage :3
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

	}();
</script>

</body>
</html>