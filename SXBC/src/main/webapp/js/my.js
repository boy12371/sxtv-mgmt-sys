$(document).ready(function() {
	$("form").validity(function() {
		$("#_bookName_id").require();
		$("#_pages_id").require().match("integer").range(1, 999999);
	});

	// $.ajax({
	// url : "/tvshows_SXTVS/books/loadStudJsonString",
	// dataType : "json",
	// success : function(json) {
	// $("#_students_id").tokenInput(json, {
	// theme : "facebook",
	// propertyToSearch : "stuName",
	// noResultsText : "无结果",
	// tokenFormatter : function(item) {
	// return "<li><input type='hidden' name='students' value='" + item.id +
	// "'/><p>" + item.stuName + "</p></li>"
	// }
	// });
	//
	// },
	// error : function(xhr) {
	// alert("error");
	// }
	// });

//	jQuery("#list2").jqGrid({
//		url : '/SXBC/books/loadStudJsonString',
//		datatype : "json",
//		colNames : [ 'ID', 'StudentName'],
//		colModel : [ {
//			name : 'id',
//			index : 'id',
//			width : 400
//		}, {
//			name : 'stuName',
//			index : 'stuName',
//			width : 400
//		} ],
//		rowNum : 10,
//		rowList : [ 10, 20, 30 ],
//		pager : '#pager2',
//		sortname : 'id',
//		viewrecords : true,
//		sortorder : "desc",
//		caption : "JSON Example"
//	});
//	jQuery("#list2").jqGrid('navGrid', '#pager2', {
//		edit : false,
//		add : false,
//		del : false
//	});

});
