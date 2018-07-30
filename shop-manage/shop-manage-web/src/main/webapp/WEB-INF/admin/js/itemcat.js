layui.define(["layer","jquery","tree"],function(exports) {


    var MELAYUI = {
        // 初始化选择类目组件
        initItemCat: function (data) {




            //弹出分类菜单(树形)
            $(".itemCat_btn").click(function(){
                //var rdm = Math.ceil(Math.random()*100);
                //alert(rdm);
                //var ulid = '<ul id="demotree'+ rdm +'"></ul>';
                var index = layui.layer.open({
                    title : "选择分类",
                    type : 1,
                    area: ['300px', '500px'],
                    content : '<ul id="demotree"></ul>',//ulid,
                    success : function(layero, index){
                        setTimeout(function(){
                            layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500)
                    }
                })

                $.post("/item/itemCat", function(data){
                    //alert(data.size);
                    laytree({
                        elem: '#demotree',//+rdm, //传入元素选择器
                        nodes:data,
                        click: function(node){
                            $("#itemcatbtn").val(node.name);
                            $("#itemcatbtnval").val(node.id);
                            //layer.close(index);
                            $('.layui-layer-page').remove();
                            $('.layui-layer-shade').remove();
                        }
                    });
                })
            })












            $(".selectItemCat").each(function (i, e) {
                var _ele = $(e);
                if (data && data.cid) {
                    _ele.after("<span style='margin-left:10px;'>" + data.cid + "</span>");
                } else {
                    _ele.after("<span style='margin-left:10px;'></span>");
                }
                _ele.unbind('click').click(function () {
                    $("<div>").css({padding: "5px"}).html("<ul>")
                        .window({
                            width: '500',
                            height: "450",
                            modal: true,
                            closed: true,
                            iconCls: 'icon-save',
                            title: '选择类目',
                            onOpen: function () {
                                var _win = this;
                                $("ul", _win).tree({
                                    url: '/item/cat/list',
                                    animate: true,
                                    onClick: function (node) {
                                        if ($(this).tree("isLeaf", node.target)) {
                                            // 填写到cid中
                                            _ele.parent().find("[name=cid]").val(node.id);
                                            _ele.next().text(node.text).attr("cid", node.id);
                                            $(_win).window('close');
                                            if (data && data.fun) {
                                                data.fun.call(this, node);
                                            }
                                        }
                                    }
                                });
                            },
                            onClose: function () {
                                $(this).window("destroy");
                            }
                        }).window('open');
                });
            });
        }
    }
})