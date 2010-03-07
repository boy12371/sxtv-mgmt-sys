function refreshIframe(obj){
	if("" == obj.href || obj.href.indexOf("doLogon.action") != -1) return;
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.src = obj.href;
	return false;
}

function resizeContentDiv(){
	var subTab = document.getElementById("subTabview");
	var contentDiv = document.getElementById("contentDiv");
	var divHeight = document.documentElement.clientHeight - 125 - subTab.offsetHeight;
	contentDiv.style.height = divHeight + "px";
	return false;
}

function resizeIframe(){
	var iframeObj = document.getElementById("contentFrameId");
	var height;
	if(null == iframeObj.contentDocument || "undefine" == typeof(iframeObj.contentDocument)){
		height = iframeObj.Document.body.scrollHeight;
	}else{
		height = iframeObj.contentDocument.body.offsetHeight;
	}
	height += 50;
	// the height can not be lower than the client area height.
	var subTab = document.getElementById("subTabview");
	var minHeight = document.documentElement.clientHeight - 125 - subTab.offsetHeight;
	minHeight -= 40;
	if(height < minHeight) {
		height = minHeight;
	}
	
	iframeObj.style.height = height + "px";
	resizeContentDiv();
}

function showDefaultSubtab(self){
	var pos = self.href.indexOf("#");
	var subId = self.href.substring(pos+1,self.href.length);
	var subDiv = document.getElementById(subId);
	var subLink = subDiv.getElementsByTagName("a")[0];
	subLink.onclick();
}

function highLightSubtab(linkObj){
	var ulObj = linkObj.parentNode.parentNode;
	var links = ulObj.getElementsByTagName("a");
	for(var i=0;i<links.length;i++){
		if("bold"==links[i].style.fontWeight){
			links[i].style.fontWeight="normal";
		}
	}
	linkObj.style.fontWeight="bold";
	return;
}

function resizeTabview(){
	var iframeObj = document.getElementById("contentFrameId");
	var tabview = document.getElementById("tabView");
	var tabOffset = iframeObj.offsetLeft + 100;
	tabview.style.marginLeft = tabOffset + "px";
	
	var subTabview = document.getElementById("subTabview");
	subTabview.style.marginLeft = "-" + tabOffset + "px";
	
	var subULs = document.getElementsByTagName("ul");
	for(var i=0,j=0;i<subULs.length;i++){
		if("subTabUL" == subULs[i].className){
			subULs[i].style.marginLeft = tabOffset + j*76 + "px";
			j++
		}
	}
}

function getBroswer(){
	var browser;
	var ua = navigator.userAgent.toLowerCase();
	if(window.ActiveXObject){
		var pos = ua.indexOf("msie");
		var ver = ua.substring(pos+5,pos+6);
		browser = "IE" + ver;
	}else{
		browser = "FF";
	}
	return browser;
}



$(document).ready(function() { 
	 
    $('#changePassword').click(function() { 
        $.blockUI({ message: $('#passwordForm'), css: { width: '475px',top:'25%',left:'30%' } }); 
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
            		jAlert("密码修改成功", '提示');
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
