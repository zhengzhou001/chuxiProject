/**
 * Date:2016-06-13<br>
 * 辅助工具类
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * <pre>
 * <OL>
 * 功能支持：
 * <LI>封装ajax调用 为了防止csrf攻击，ajax提交前获取令牌到服务器校验
 * </OL>
 * </pre>
 */


String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, ""); //正则匹配空格  
}
var baseTools = (function () {
      
    return {
    	//重新登录
    	gotoLogin: function (dateStr) {
    		top.location.href=context+"route/login";
        },
    	//解决IE8获取时间失败
        getDate: function (dateStr) {
        	dateStr = dateStr.replace(/\-/g, "/");
         	return new Date(dateStr);
        	 
        },
        //空校验
        isEmpty:function(obj){
            return (typeof obj === 'undefined' || obj === null || obj === "");
        },
        isNotEmpty:function(obj){
            return !this.isEmpty(obj);
        },
        isBlank:function(obj){
            return (typeof obj === 'undefined' || obj === null || obj === "" ||this.trim( obj) === "");
        },
        isNotBlank:function(obj){
            return !this.isBlank(obj);
        },
        //获取url参数
        getUrlParam:function(name){
        	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        	   var r = window.location.search.substr(1).match(reg);
        	   if (r != null) return unescape(r[2]); 
        	   return null;
        },
        trim: function (str) {
        	if(str==null){
        		return "";
        	}
        	return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
    	//字符串结尾函数
        endWith: function (sourceStr,endStr) {
        	var tmp = sourceStr.slice(-endStr.length).toUpperCase();
        	if(tmp==endStr.toUpperCase()){
        		return true;
        	}else{
        		return false;
        	}
        	 
        },
    	//显示遮罩
        showMask: function (msg) {
            var loading = context+'images/load/loading.gif';
             msg = msg ? msg : "数据加载中...";
            $.blockUI({
                message: '<img src="' + loading + '" style="margin-right:6px">' + msg + '</img>',
                css: {
                    border: 'none'                   // 无边界
                    , width: "240px"                     // 中间框框的宽度
                    , height: '50px', backgroundColor: '#eee'
                    , lineHeight:'50px'
                    , verticalAlign:'middle'
                    // ,border: '1px solid red'
                    //, top:"50%",                        // 高居中
                    // left:"50%"                        // 左居中
                    , '-webkit-border-radius': '10px', '-moz-border-radius': '10px', opacity: .8
                }
            });
        },
        //关闭遮罩
        hideMash: function (maskObj) {
        	setTimeout(function(){$.unblockUI();},100);
            
        },
    	/**
         * 在ajax请求时添加其他参数(需要在主体软件中重载)
         * @param xhrArgs ajax请求参数
         */
        getXhrAjaxParams: function (xhrArgs) {
            //添加附加参数
            return xhrArgs.params || {};
        },
        /**
         * 自动组装指定表单数据
         * @param params 附加参数对象数组 可以为空
         * @return 返回值对字符串
         */
        preparePostData: function (params) {
            var result = [];
            //添加附加参数
            if (params) {
                var fjParam = [];
                for (var key in params) {
                    var val = params[key] != undefined ? params[key].toString() : "";
                    fjParam.push(encodeURIComponent(key) + "=" + encodeURIComponent(val));
                }
                result.push(fjParam.join("&"));
            }
            return result.join("&");
        },
    	/**
         * 简化调用版本,根据参数对象中指定的值向服务端提交请求
         * <pre>
         * 例如:
         *  var xhrArgs = {
         dataType:'json',//默认json，可以是xml,html,script,json
         url:"getXT_USERByPage.do",
         type:"POST",   //默认POSt，可以是POSt,GET
         forms:["saveForm"],
         params:params,
         msg:"正在加载..." ,//进度提示文字 可以不写
         //  callBefore:[callBefore],
         callback:[pageFlowControl]
         };
         baseTools.xhrAjax(xhrArgs);
         * </pre>
         * @param xhrArgs
         */
        xhrAjax: function (xhrArgs) {
        	var maskObj = null;
            var bShow = true;
            if (typeof xhrArgs.bShow != 'undefined') {
                bShow = xhrArgs.bShow;
            }
            if (bShow) {
                maskObj = this.showMask(xhrArgs.msg);
            }
            var contentType='application/x-www-form-urlencoded';
            if (xhrArgs.contentType)
            	contentType = xhrArgs.contentType;
            var type = 'POST';
            if (xhrArgs.type)
                type = xhrArgs.type;
            var dataType = 'json';
            if (xhrArgs.dataType)
                dataType = xhrArgs.dataType;
            var async = true;
            if (xhrArgs.async != 'undefined')
                async = xhrArgs.async;
            xhrArgs.params = this.getXhrAjaxParams(xhrArgs);

           // var urlParam = this.preparePostData(xhrArgs.params);
            var urlParam = xhrArgs.params;
            $.ajax({
                url: xhrArgs.url,
                type: type,
                async: async,
                dataType: dataType,
                contentType: contentType,
                //要发送到服务器的数据
                data: urlParam,
                beforeSend: function (XMLHttpRequest) {
                    //使用csrf-token解决csrf安全问题 需要和配置文件中的CSRF_TOKEN_KEY保持一致
                    var val = $.cookie("x-csrf-token");
                    XMLHttpRequest.setRequestHeader("x-csrf-token", (val != null ? val : ""));
                },
                //当请求失败时调用的函数
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    // 调用本次AJAX请求时传递的options参数
                    alert('操作提示\n操作失败原因:' + textStatus + "\n" + errorThrown);
                    if (bShow)
                        baseTools.hideMash(maskObj);
                },
                //当请求成功时调用的函数
                success: function (data, textStatus) {
                    //  this; // 调用本次AJAX请求时传递的options参数
                    if (xhrArgs.callback)
                        for (var i = 0; i < xhrArgs.callback.length; i++)
                            xhrArgs.callback[i](data, xhrArgs);
                    if (bShow)
                        baseTools.hideMash(maskObj);
                },
                //当请求完成时调用的函数
                complete: function (XMLHttpRequest, textStatus) {
                    //需要和配置文件中的CSRF_TOKEN_KEY保持一致
                	//setTimeout(function(){$.unblockUI();},100);
                	var val = new String(XMLHttpRequest.getResponseHeader("x-csrf-token")); 
                	$.cookie("x-csrf-token", val, {path: "/laxt/"});
                	//setTimeout(function(){$.cookie("x-csrf-token", val, {path: "/"});},100);
                }
            });
        }
         
    };
})();