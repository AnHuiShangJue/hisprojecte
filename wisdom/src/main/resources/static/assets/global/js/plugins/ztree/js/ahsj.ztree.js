var ztree;
/**
 * ztree 创建
 */
$.fn.createTree = function(options){
	options = $.extend(true,{},{
		_expandAll : false,//['1','2']
		_ajax : {
			url:"",
			data:{}
		},
		_selectId : "",
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: null
			}
		},
		callback: {
			onClick: function(event, treeId, treeNode){
				console.log(treeNode);
			},
			onRightClick: function(event, treeId, treeNode){
				console.log(treeNode);
			}
		}
	},options);
	var _this = $(this);
	$.ajax({
		type: "post",
		url:  options._ajax.url,
		data: options._ajax.data,
        headers:{
            Authorization:"Bearer "+TOKEN
        },
		dataType : "json",
		async: false,
		success : function(data){
			//初始化
			ztree = $.fn.zTree.init(_this, options, data);
			//展开全部节点或指定节点
			if(typeof options._expandAll == "boolean"){
				ztree.expandAll(options._expandAll);
			}else{
				for (var int = 0; int < options._expandAll.length; int++) {
					var node = ztree.getNodeByParam("id",options._expandAll[int]);
					ztree.expandNode(node,true);
				}
			}
			//初始化默认选中根节点(1)
			ztree.selectNode(ztree.getNodeByParam("id",options._selectId));
		}
	});
}
