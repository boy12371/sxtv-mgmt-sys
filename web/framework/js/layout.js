function refreshIframe(obj){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.src = obj.href;
	return false;
}

function resizeIframe(){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.style.height = window.screen.availHeight - 300 + "px";
	return false;
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
