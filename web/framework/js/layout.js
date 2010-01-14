function refreshIframe(obj){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.src = obj.href;
	return false;
}

function resizeIframe(){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.style.height = window.screen.availHeight - 340 + "px";
	return false;
}