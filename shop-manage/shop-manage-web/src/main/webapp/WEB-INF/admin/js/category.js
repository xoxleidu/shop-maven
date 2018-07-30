var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false,
        showLine: false,
        showIcon: false
    },
    async: {
        enable: true,
        url:"/item/category/list",
        autoParam:["id"],
        //otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: showRemoveBtn
        //,showRenameBtn: showRenameBtn
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        beforeRename: beforeRename,
        onRemove: onRemove,
        onRename: onRename
    }
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

var log, className = "dark";
function beforeDrag(treeId, treeNodes) {
    return false;
}
function beforeEditName(treeId, treeNode) {
    className = (className === "dark" ? "":"dark");
    showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    setTimeout(function() {
        if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
            setTimeout(function() {
                zTree.editName(treeNode);
            }, 0);
        }
    }, 0);
    return false;
}
function beforeRemove(treeId, treeNode) {
    className = (className === "dark" ? "":"dark");
    showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
    showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
}
function beforeRename(treeId, treeNode, newName, isCancel) {
    className = (className === "dark" ? "":"dark");
    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
    if (newName.length == 0) {
        setTimeout(function() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.cancelEditName();
            alert("节点名称不能为空.");
        }, 0);
        return false;
    }
    return true;
}
function onRename(e, treeId, treeNode, isCancel) {
    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
}
function showRemoveBtn(treeId, treeNode) {
    return !treeNode.isFirstNode;
}
function showRenameBtn(treeId, treeNode) {
    return !treeNode.isLastNode;
}
function showLog(str) {
    if (!log) log = $("#log");
    log.append("<li class='"+className+"'>"+str+"</li>");
    if(log.children("li").length > 8) {
        log.get(0).removeChild(log.children("li")[0]);
    }
}
function getTime() {
    var now= new Date(),
        h=now.getHours(),
        m=now.getMinutes(),
        s=now.getSeconds(),
        ms=now.getMilliseconds();
    return (h+":"+m+":"+s+ " " +ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);

    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");


        layui.config({
            base : "js/"
        }).use(['layer'],function() {
            var layer = parent.layer === undefined ? layui.layer : parent.layer;

            var index = layer.open({
                title : "添加节点",
                type : 1,
                closeBtn: 0,
                area: ['300px', '200px'],
                content : '<div class="categorydiv">' +
                '<input type="text" class="layui-input categoryin" lay-verify="required" id="categoryin" placeholder="请输入节点名称">' +
                '</div>'//ulid,
                ,btn: ['确定']
                ,yes: function(index, layero){
                    var name = layero.find('#categoryin').val();
                    if (name.length == 0) {
                        setTimeout(function() {
                            //var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                            zTree.cancelEditName();
                            alert("节点名称不能为空.");
                        }, 0);
                        return false;
                    }
                    $.get("/item/category/add", {"name":name,"parentId":treeNode.id}, function(data){
                        zTree.addNodes(treeNode, {id:data.id, name:name,isParent:data.isParent});
                        return false;
                    });
                    layer.close(index);
                }
            })


        })


        /*$.layer({
            type: 2,
            shade: [0],
            fix: false,
            title: '新增',
            maxmin: true,
            iframe: {src : '<%=basePath %>/func/addNewFun.jsp?parentId='+treeNode.id},
            area: ['400px' , '240px'],
            closeBtn: [0, true],
            close: function(index){
                var name = layer.getChildFrame('#name', index).val();

                $.ajax({
                    type: "POST",
                    url: "<%=basePath %>/fun/add.do",
                    data: {"name":name,"parentId":treeNode.id},
                    dataType : "json",
                    success:function(data){
                        data= JSON.parse(data);
                        if(data.flag){
                            zTree.addNodes(treeNode, {id:data.msg, pId:treeNode.id, name:name});
                            return false;
                        }
                    }
                });


            }
        });*/

        //var nodes = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new2 node" + (newCount++)});
        //alert(JSON.stringify(nodes));
        //alert(nodes.getIndex());
        //beforeEditName(nodes[0].tId,nodes[0]);
        //return false;
    });
};
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};
function selectAll() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
}

$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting);
    $("#selectAll").bind("click", selectAll);
});