function getDateFromDataTimePicker(pickerID) {
        var picker = dojo.widget.byId(pickerID);
        // string value
        var stringValue = picker.getValue();
        alert(stringValue);

        // date value
        var dateValue = picker.getDate();
        alert(dateValue);
}
var datatableCallBack = {
        success : function(o) {
                YAHOO.log("RAW JSON DATA: " + o.responseText);
                // Process the JSON data returned from the server
        var records = "";
        try {
                records = YAHOO.lang.JSON.parse(o.responseText);
                initDataTable(records);
        } catch (x) {
                alert("JSON Parse failed!");
                return;
        }
},

failure : function(o) {
        if (!YAHOO.util.Connect.isCallInProgress(o)) {
                alert("Async call failed!");
        }
},
timeout : 3000

};

function initDataSourceForTable(url, callBack) {
        YAHOO.util.Connect.asyncRequest('GET', url, callBack);
}


var fireEvent = function(arg0) {
        var status = YAHOO.util.Dom.get("filter").value;
        request = "/tv/audit/filterVideos.action?sort=dateInput&dir=asc&startIndex=-1&filter="+status;
        initDataSourceForTable(request,datatableCallBack);
}

// DataTable instance

function initDataTable(ds) {

        var formatLink = function(elCell, oRecord, oColumn, sData) {
//              if (oRecord.getData("status").id == 2) {
//                      var href = "<a href='./audit/findVideoByNameOrID.action?optionName=auditing&vid=";
//                      href += sData;
//                      href += "'>" + sData + "</a>";
//                      elCell.innerHTML = href;
//              } else {
//                      elCell.innerHTML = sData;
//              }
                if (oRecord.getData("status") == "待审") {
                        var href = "<a href='./audit/findVideoByNameOrID.action?optionName=auditing&vid=";
                        href += sData;
                        href += "'>" + sData + "</a>";
                        elCell.innerHTML = href;
                } else {
                        elCell.innerHTML = sData;
                }
        
        }

        // Column definitions
        var myColumnDefs = [{
                                key : "id",
                                label : "编号",
                                sortable : true,
                                formatter : formatLink
                        }, {
                                key : "vedioName",
                                label : "剧目名称"
                        }, {
                                key : "topic",
                                label : "题材",
                                sortable : true
                                // ,formatter : formatTopic
                        }, {
                                key : "subject",
                                label : "栏目",
                                sortable : true
                                // ,formatter : formatSubject
                        }, {
                                key : "companyID",
                                label : "影视公司",
                                sortable : true//,formatter : formatCompany
                        }, {
                                key : "dateComing",
                                label : "收带日期",
                                sortable : true,
                                formatter : formatDate
                        }, {
                                key : "status",
                                label : "状态",
                                sortable : true
                                // ,formatter : formatStatus
                        }, {
                                key : "avgScore",
                                label : "综合平均分",
                                sortable : true
                                // ,formatter : formatScroes
                        }, {
                                key : "purchase",
                                label : "购买意见"
                                // ,formatter : formatPurchase
                        }, {
                                key : "award",
                                label : "获奖备选"
                                // ,formatter : formatAward
                        }, {
                                key : "audiScore",
                                label : "观众投票"
                                // ,formatter : formatAudienceScore
                        }, {
                                key : "comments",
                                label : "备注",
                                formatter : formatorComments
                        }];

        // DataSource instance
        // var myDataSource = new
        // YAHOO.util.XHRDataSource("/tv/audit/filterVideos.action?");
        var myDataSource = new YAHOO.util.DataSource(ds);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
                resultsList : "records",
                fields : ["id", "vedioName", "topic", "subject", "companyID",
                                "dateComing", "status", "audiScore", "purchase", "award", "avgScore", "comments"],
                metaFields : {
                        totalRecords : "totalRecords" // Access to value in the server
                        // response
                }
        };
        
        // DataTable configuration
        var myConfigs = {
                sortedBy : {
                        key : "dateComing",
                        dir : YAHOO.widget.DataTable.CLASS_ASC
                }, // Sets UI initial sort arrow
                paginator : new YAHOO.widget.Paginator({
                        rowsPerPage : 25,
                        firstPageLinkLabel : "首页",
                        lastPageLinkLabel : " 尾页",
                        previousPageLinkLabel : " 上一页",
                        nextPageLinkLabel : " 下一页",
                        template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
                        pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
                        rowsPerPageOptions : [25, 50, 100]
                }),
                // generateRequest : requestBuilder
        };

        var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
                        myDataSource, myConfigs);
        // Update totalRecords on the fly with value from server
        myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
                        oPayload) {
                oPayload.totalRecords = oResponse.meta.totalRecords;
                return oPayload;
        };
        myDataSource.subscribe("requestEvent", function() {
                                $.blockUI({
                                                        message : "<h1>数据加载中......</h1>"
                                                });
                        });
        myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
        myDataTable
                        .subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
        myDataTable.subscribe("renderEvent", function() {
                                addColumnsName();
                                $.unblockUI();
                                parent.resizeIframe();
                        });

        
        var columnSet = myDataTable.getColumnSet();
        var showHideColumn = function(e) {
                var column = columnSet.getColumn(this.value);
                if (this.checked) {
                        myDataTable.hideColumn(column);
                } else {
                        myDataTable.showColumn(column);
                }
        }
        var colDiv = YAHOO.util.Dom.get("colDiv");
        var colLink = YAHOO.util.Dom.get("tableOption");
        YAHOO.util.Event.addListener(colLink, "click", function() {
                                colDiv.style.display = colDiv.style.display == "block"
                                                ? "none"
                                                : "block";
                        });
        addColumnsName = function() {
                if (colDiv.innerHTML.length == 0) {
                        for (var i = 0; i < myColumnDefs.length; i++) {
                                var column = myColumnDefs[i];
                                var checkbox = document.createElement("INPUT");
                                checkbox.type = "checkbox";
                                checkbox.name = "colCkbox";
                                checkbox.value = column.key;
                                checkbox.checked = false;
                                colDiv.appendChild(checkbox);
                                var p = document.createElement("SPAN");
                                p.innerHTML = column.label;
                                colDiv.appendChild(p);
                                if (i % 2 == 1) {
                                        var br = document.createElement("BR");
                                        colDiv.appendChild(br);
                                }

                                YAHOO.util.Event.addListener(checkbox, "click", showHideColumn);
                                colDiv.style.display = "none";
                        }
                }
        };
        // var generatePrintTable = function(){
        // //getDateFromDataTimePicker("fromDate");
        // var filter = YAHOO.util.Dom.get('filter');
        //              
        // var from = dojo.widget.byId("fromDate");
        // var to = dojo.widget.byId("fromDate");
        // var fromValue = from.getValue();
        // var toValue = to.getValue();
        //
        // var startDate = fromValue.substring(0,fromValue.indexOf("T"));
        // var endDate = toValue.substring(0,toValue.indexOf("T"));
        // var
        // paramValue="sort=dateInput&dir=asc&startIndex=-1&results=0&sc.status.id="
        // + filter.value + "&sc.startDate="+ startDate + "&sc.endDate=" + endDate;
        // //escape(paramValue);
        // var queryString =
        // "/tv/search/toPrintVideosReport.action?query="+escape(paramValue);
        // $.unblockUI();
        // window.open(queryString, "打印预览");
        //          
        // }
        // var yesBtn = YAHOO.util.Dom.get("yes");
        // YAHOO.util.Event.addListener(yesBtn, "click", generatePrintTable);
        //      
        // var printBtn = new YAHOO.widget.Button({
        // type : "button",
        // id : "topicBtn",
        // label : "打印",
        // container : "printBtn"
        // });
        //      
        // printBtn.on("click",function(e){
        // $.blockUI({ message: $('#printDate'), css: { width:
        // '400px',top:'25%',left:'30%',cursor:'auto' } });
        // var filter = YAHOO.util.Dom.get('filter');
        // var url =
        // myDataSource.liveData+"sort=dateInput&dir=asc&startIndex=-1&results=0&filter="+filter.value;
        //               
        //              
        // // Define the callbacks for the asyncRequest
        // var callbacks = {
        // success : function (o) {
        // YAHOO.log("RAW JSON DATA: " + o.responseText);
        // // Process the JSON data returned from the server
        // var jsonObj;
        // try {
        // jsonObj = YAHOO.lang.JSON.parse(o.responseText);
        // }
        // catch (x) {
        // alert("JSON Parse failed!");
        // return;
        // }
        // var cset = myDataTable.getColumnSet();
        // var cLen = cset.getDefinitions().length;
        // for(var k=0; k< cLen; k++){
        // var c = cset.getColumn(k);
        // alert(c.label);
        // }
        // var records = jsonObj.records;
        // var len = records.length;
        // for (var i = 0; i < len; i++) {
        // var rc = records[i];
        // alert(rc.vedioName);
        // }
        // },
        //
        // failure : function (o) {
        // if (!YAHOO.util.Connect.isCallInProgress(o)) {
        // alert("Async call failed!");
        // }
        // },
        // timeout : 3000
        // }
        // // Make the call to the server for JSON data
        // YAHOO.util.Connect.asyncRequest('GET',url, callbacks);

        // });
        return {
                ds : myDataSource,
                dt : myDataTable
        };

}

function initScoreDataTable(videoID) {
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
                                key : "vedioName",
                                label : "剧目名称"
                        }, {
                                key : "score",
                                label : "综合得分",
                                sortable : true
                        }, {
                                key : "storyScore",
                                label : "故事",
                                sortable : true
                        }, {
                                key : "techScore",
                                label : "技术",
                                sortable : true
                        }, {
                                key : "performScore",
                                label : "表演",
                                sortable : true
                        }, {
                                key : "innovateScore",
                                label : "创新",
                                sortable : true
                        }, {
                                key : "dateExamine",
                                label : "评分日期",
                                sortable : true,
                                formatter : formatDate
                        }, {
                                key : "award",
                                label : "获奖",
                                sortable : true
                        }, {
                                key : "purchase",
                                label : "购买意见",
                                sortable : true
                        }, {
                                key : "examiner",
                                label : "评分人"
                        }];

        // DataSource instance
        var myDataSource = new YAHOO.util.XHRDataSource("/tv/audit/getVideoScores.action?&videoID="
                        + YAHOO.util.Dom.get("videoID").value + "&");
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
                resultsList : "records",
                fields : ["vedioName", "score", "storyScore", "techScore",
                                "performScore", "innovateScore", "dateExamine", "award",
                                "purchase", "examiner"],
                metaFields : {
                        totalRecords : "totalRecords" // Access to value in the server
                        // response
                }
        };

        // DataTable configuration
        var myConfigs = {
                initialRequest : "sort=score&dir=asc&startIndex=0&results=25",
                dynamicData : true, // Enables dynamic server-driven data
                sortedBy : {
                        key : "score",
                        dir : YAHOO.widget.DataTable.CLASS_ASC
                }, // Sets UI initial sort arrow
                paginator : new YAHOO.widget.Paginator({
                        rowsPerPage : 25,
                        firstPageLinkLabel : "首页",
                        lastPageLinkLabel : " 尾页",
                        previousPageLinkLabel : " 上一页",
                        nextPageLinkLabel : " 下一页",
                        template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
                        pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
                        rowsPerPageOptions : [25, 50, 100]
                }),
                width:"auto"

        };

        var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
                        myDataSource, myConfigs);
        // Update totalRecords on the fly with value from server
        myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
                        oPayload) {
                oPayload.totalRecords = oResponse.meta.totalRecords;
                return oPayload;
        }
        myDataSource.subscribe("requestEvent", function() {
                                $.blockUI({
                                                        message : "<h1>数据加载中......</h1>"
                                                });
                        });
        myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
        myDataTable
                        .subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
        myDataTable.subscribe("renderEvent", function() {
                                $.unblockUI();
                                parent.resizeIframe();
                        });
        return {
                ds : myDataSource,
                dt : myDataTable
        };

}
