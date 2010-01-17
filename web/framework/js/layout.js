function refreshIframe(obj){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.src = obj.href;
	return false;
}

function resizeContentDiv(){
	var contentDiv = document.getElementById("contentDiv");
	contentDiv.style.height = window.screen.availHeight - 300 + "px";
	return false;
}

function resizeIframe(){
	var iframeObj = document.getElementById("contentFrameId");
	var height;
	if(null == iframeObj.contentDocument || "undefine" == typeof(iframeObj.contentDocument)){
		height = iframeObj.document.body.scrollHeight;;
	}else{
		height = iframeObj.contentDocument.body.offsetHeight;
	}
	iframeObj.style.height = height + "px";
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
