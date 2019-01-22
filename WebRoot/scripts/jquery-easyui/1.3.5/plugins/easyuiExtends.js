/**
 * From扩展
 * getData 获取数据接口
 *
 * @param {Object} jq
 * @param {Object} params 设置为true的话，会把string型"true"和"false"字符串值转化为boolean型。
 */
$.extend($.fn.form.methods, {
    getData: function(jq, params){
        var formArray = jq.serializeArray();
        var oRet = {};
        for (var i in formArray) {
            if (typeof(oRet[formArray[i].name]) == 'undefined') {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] = formArray[i].value;
                }
            }
            else {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] += "," + formArray[i].value;
                }
            }
        }
        return oRet;
    }
});

var _ajaxLoadEvents = {
    onLoadError : function(XMLHttpRequest) {
        var resp = XMLHttpRequest.responseText;
        if (resp && resp.indexOf("sessionState:0")<=0) {
            if (parent.$ && parent.$.easyui) {
                parent.$.easyui.loaded({topMost: true});
                parent.$.messager.alert('错误', '与服务器连接失败! 如果该错误多次出现, 请联系系统管理员!!', 'error');
                /*parent.$.messager.alert("错误", "登录超时，请重新登录!", "error", function () {
                	var contextPath = document.location.pathname;
        		    var index =contextPath.substr(1).indexOf("/");
        		    contextPath = contextPath.substr(0,index+1);
        		     delete index;
        		     window.parent.location =  contextPath+'/login.html';
                });*/
            } else {
                $.easyui.loaded({topMost: true});
                $.messager.alert('错误', '与服务器连接失败! 如果该错误多次出现, 请联系系统管理员!', 'error');
            }
        }
    }

}

$.extend($.fn.datagrid.defaults, _ajaxLoadEvents);
$.extend($.fn.treegrid.defaults, _ajaxLoadEvents);
$.extend($.fn.tree.defaults, _ajaxLoadEvents);
$.extend($.fn.combogrid.defaults, _ajaxLoadEvents);
$.extend($.fn.combobox.defaults, _ajaxLoadEvents);
$.extend($.fn.form.defaults, _ajaxLoadEvents);

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 *
 * @author 孙宇
 *
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
    onHidePanel : function() {
        var _options = $(this).combogrid('options');
        if (_options.mode == 'remote') {/* 如果是自动补全模式 */
            var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
            var _value = $(this).combogrid('getValue');/* 用户输入的值 */
            var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
            for (var i = 0; i < _data.length; i++) {
                if (_data[i][_options.idField] == _value) {
                    _b = true;
                }
            }
            if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
                $(this).combogrid('setValue', '');
            }
        }
    }
});

$.extend($.fn.validatebox.defaults.rules, {
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }

});

//为date类添加一个format方法
//yyyy 年
//MM 月
//dd 日
//hh 小时
//mm 分
//ss 秒
//qq 季度
//S  毫秒
Date.prototype.formatDate = function (format) //author: meizz
{
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
                RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

//扩展jQuery对json字符串的转换
jQuery.extend({
    /** * @see 将json字符串转换为对象 * @param json字符串 * @return 返回object,array,string等对象 */
    evalJSON: function(strJson) {
        return eval("(" + strJson + ")");
    }
});
jQuery.extend({
    /** * @see 将javascript数据类型转换为json字符串 * @param 待转换对象,支持object,array,string,function,number,boolean,regexp * @return 返回json字符串 */
    toJSON: function(object) {
        var type = typeof object;
        if ('object' == type) {
            if (Array == object.constructor) type = 'array';
            else if (RegExp == object.constructor) type = 'regexp';
            else type = 'object';
        }
        switch (type) {
            case 'undefined':
            case 'unknown':
                return;
                break;
            case 'function':
            case 'boolean':
            case 'regexp':
                return object.toString();
                break;
            case 'number':
                return isFinite(object) ? object.toString() : 'null';
                break;
            case 'string':
                return '"' + object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() {
                    var a = arguments[0];
                    return (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': ""
                }) + '"';
                break;
            case 'object':
                if (object === null) return 'null';
                var results = [];
                for (var property in object) {
                    var value = jQuery.toJSON(object[property]);
                    if (value !== undefined) results.push(jQuery.toJSON(property) + ':' + value);
                }
                return '{' + results.join(',') + '}';
                break;
            case 'array':
                var results = [];
                for (var i = 0; i < object.length; i++) {
                    var value = jQuery.toJSON(object[i]);
                    if (value !== undefined) results.push(value);
                }
                return '[' + results.join(',') + ']';
                break;
        }
    }
});