layui.config({
    base : "js/"
}).use(['table'],function() {
    var laytable = layui.table;


    var setting = {
        async: {
            enable: true,
            url:"/item/category/list",
            autoParam:["id"],
            //otherParam:{"otherParam":"zTreeAsyncTest"},
            //dataFilter: filter

        }
        ,callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };


    function beforeClick(treeId, treeNode, clickFlag) {
        if(treeNode.isParent){
            //alert("当前是子节点，不能操作!")
            return false;
        }
        return true;
    }

    function onClick(event, treeId, treeNode, clickFlag) {
        //加载商品规格数据
        //alert(treeNode.id + " " + treeNode.name);
        laytable.render({
            elem: '#contentlist'
            ,cols: [[
                //{checkbox: true},
                {field: 'id', title: 'ID', width:100,sort:true}
                ,{field: 'categoryId', title: '分类ID', width: 100,sort:true}
                ,{field: 'title', title: '标题1', width: 120}
                ,{field: 'subTitle', title: '标题2', width: 120}
                ,{field: 'titleDesc', title: '标题3', width: 120}
                ,{field: 'url', title: '链接地址', width: 120}
                ,{field: 'pic', title: '图片', width: 120}
                ,{field: 'pic2', title: '图片2', width: 120}
                ,{field: 'content', title: '内容', width: 500}
                ,{field: 'created', title: '创建时间', width: 150,sort:true}
                ,{field: 'updated', title: '修改时间', width: 150,sort:true}
                ,{fixed: 'right', width:200, align:'center', toolbar: '#contentlistedit'}
            ]]
            , url: "/content/listbyid"
            //, method: 'post'
            , page: true
            , limits: [5,10,20,30]
            , limit: 10
            , where: {id:treeNode.id}
        });

        $(".content_add_btn_val").val(treeNode.id);
        $(".content_add_btn").val("添加"+treeNode.name);
        $(".content_search").show();



    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting);
    });


    $(window).one("resize",function(){
        $(".content_add_btn").click(function(){
            //alert(1);
            var index = layui.layer.open({
                title : "添加",
                type : 2,
                content : "/item/contentadd",
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            })
            layui.layer.full(index);
        })
    }).resize();

})
