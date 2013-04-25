ValidateUtil = {};

ValidateUtil.showError = function(el, message) {
	var _isUL = el.attr("id").indexOf("token") != -1;
	var _el = ValidateUtil.element(el);
	if (_el.next("label").length == 0) {
		_el.after("<label for='" + el.attr("id") + " ' class=\'error\'>"
				+ message + "\</label>");
	} else {
		_el.next("label").text(message);
		_el.next("label").show();
	}
	if(_isUL){
		_el.css("display","inline-block");
		_el.css("margin","0px");
		_el.parent().css("margin-top","15px");
		_el.parent().css("margin-top","15px");
	}
	_el.addClass("ui-state-error");
};
ValidateUtil.element = function(el) {
	if (el.attr("id").indexOf("token") != -1) {
		return el.parent().parent();
	} else {
		return el;
	}
};
ValidateUtil.hideError = function(el) {
	var _isUL = el.attr("id").indexOf("token") != -1;
	var _el = ValidateUtil.element(el);
	if (_el.next("label").length != 0) {
		_el.next("label").hide();
		if(_isUL){
			_el.css("display","");
/*			_el.css("margin-bottom","10px");
			_el.css("margin-top","10px");
*/			_el.parent().css("margin-top","15px");
			_el.parent().css("margin-top","15px");
		}
	}
	_el.removeClass("ui-state-error");
};

ValidateUtil.required = function(el) {
	var empty = $.trim(el.val()).length == 0;
	if (empty) {
		ValidateUtil.showError(el, "必填字段");
		el.keyup(function() {
			$.trim(el.val()).length == 0 ? ValidateUtil.showError(el)
					: ValidateUtil.hideError(el);
			// el.val($.trim(el.val()));
		});
		return false;
	}
	return true;
};

ValidateUtil.requiredDigits = function(el) {
	var valid = ValidateUtil.required(el);
	if (valid) {
		if (!/^\d+$/.test($.trim(el.val()))) {
			ValidateUtil.showError(el, "请输入数字");
			valid = false;
		}
	}
	if (!valid) {
		el.keyup(function() {
			if (!/^\d+$/.test($.trim(el.val()))) {
				ValidateUtil.showError(el, "请输入数字");
			} else {
				ValidateUtil.hideError(el);
			}
		});
	}
	return valid;
};

ValidateUtil.requiredPattern = function(el) {
	var valid = ValidateUtil.required(el);
	if (valid) {
		if (!/^\d+-\d+$/.test($.trim(el.val()))) {
			ValidateUtil.showError(el, "请输入有效格式.例:1000-2000");
			valid = false;
		}
	}
	if (!valid) {
		el.keyup(function() {
			if (!/^\d+-\d+$/.test($.trim(el.val()))) {
				ValidateUtil.showError(el, "请输入有效格式.例:1000-2000");
			} else {
				ValidateUtil.hideError(el);
			}
		});
	}
	return valid;
};
