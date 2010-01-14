function resizeIframe(obj){
	var iframeObj = document.getElementById("contentFrameId");
	iframeObj.src = obj.href;
	return false;
}