

function autoCompleteVideoName() {
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/search/autoCompleteForVideoName.action?");
	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vname" ]
	};

	// Instantiate AutoComplete
	var myAutoComp = new YAHOO.widget.AutoComplete("searchinput",
			"searchcontainer", myDataSource);
	myAutoComp.queryMatchContains = true;
	myAutoComp.queryQuestionMark = false;
	myAutoComp.useShadow = true;

	// Keeps container centered
	/*myAutoComp.doBeforeExpandContainer = function(oTextbox, oContainer, sQuery,
			aResults) {
		var pos = YAHOO.util.Dom.getXY(oTextbox);
		pos[1] += YAHOO.util.Dom.get(oTextbox).offsetHeight + 2;
		YAHOO.util.Dom.setXY(oContainer, pos);
		return true;
	};*/

	return {
		oDS :myDataSource,
		oAC :myAutoComp
	}
}


$(document).ready(function() { 
	 
    $('#changePassword').click(function() { 
        $.blockUI({ message: $('#passwordForm'), css: { width: '475px' } }); 
    }); 

    $('#yes').click(function() { 

    	var oldPass = $('#oldPwd').val();
    	var newPass = $('#newPwd').val();
    	var confirmPass = $('#confirmPwd').val();
    	
		if(oldPass.length==0){
			jAlert('请输入原密码', '错误');
			return;
		}
		if(newPass.length==0){
			jAlert('请输入新密码', '错误');
			return;
		}
		if(confirmPass.length==0){
			jAlert('请确认新密码', '错误');
			return;
		}
		if(newPass != confirmPass){
			jAlert('密码不一致', '错误');
			return;
		}
		       
        // update the block message 
        $.blockUI({ message: "<h1>处理中......</h1>" });
        $.ajax({
            async: true, 
            url: '/tv/sys/doChangePassword.action', 
            cache: false,
            data:"password="+oldPass+"&newPass="+newPass+"&confirmPass="+confirmPass,
        	success: function(message){
        		if(message.indexOf("SUCCESS") == -1){
        			jAlert(message, '错误');
            	}else{
            		jAlert(message, '提示');
            		 $.unblockUI();
                }
        	},
        	complete: function() { 
                // unblock when remote call returns 
                $.unblockUI(); 
            } 
        }); 
    }); 

    $('#cancel').click(function() { 
        $.unblockUI(); 
        return false; 
    }); 

});
