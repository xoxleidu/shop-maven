layui.config({
        base : "js/"
    }).use(['form','layer','jquery','layedit','laydate','tree','upload','table'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            layedit = layui.layedit,
            laydate = layui.laydate,
            laytree = layui.tree,
            layupload = layui.upload,
            laytable = layui.table,
            $ = layui.jquery;


    //var time=parseInt(res.data[i]['addtime']);
    //res.data[i]['addtime']=laydate.now(time,'YYYY-MM-DD hh:mm:ss');

    //加载商品数据
    laytable.render({
        elem: '#itemlist'
        ,cols: [[
            {checkbox: true}
            ,{field: 'id', title: 'ID', width:100,sort:true}
            ,{field: 'image', title: '图片', width: 120}
            ,{field: 'cid', title: '分类', width: 80}
            ,{field: 'title', title: '名称', width: 200}
            ,{field: 'sellPoint', title: '简介', width: 200}
            ,{field: 'price', title: '价格', width: 100,sort:true}
            ,{field: 'num', title: '数量', width: 100,sort:true}
            ,{field: 'created', title: '创建时间', width: 150,sort:true}
            ,{field: 'updated', title: '修改时间', width: 150,sort:true}
            ,{field: 'status', title: '状态', width: 80}
            ,{fixed: 'right', width:200, align:'center', toolbar: '#itemlistedit'}
        ]]
        , url: "/item/allList/json"
        , method: 'post'
        , page: true
        , limits: [5,10,20,30]
        , limit: 10 //默认采用60
        , done: function(res, curr, count){
            //setTimeout("alert('x')", 2000);
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);

            //得到当前页码
            console.log(curr);

            //得到数据总量
            console.log(count);
        }
    })

    //加载商品规格数据
    laytable.render({
        elem: '#paramlist'
        ,cols: [[
            {checkbox: true}
            ,{field: 'id', title: 'ID', width:100,sort:true}
            ,{field: 'itemCatId', title: '分类ID', width: 120}
            ,{field: 'paramData', title: '内容', width: 500}
            ,{field: 'created', title: '创建时间', width: 150,sort:true}
            ,{field: 'updated', title: '修改时间', width: 150,sort:true}
            ,{fixed: 'right', width:200, align:'center', toolbar: '#paramlistedit'}
        ]]
        , url: "/param/allList/json"
        , method: 'post'
        , page: true
        , limits: [5,10,20,30]
        , limit: 10 //默认采用60
        // , done: function(res, curr, count){
        //     //如果是异步请求数据方式，res即为你接口返回的信息。
        //     alert(JSON.stringify(res));
        //     //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        //     console.log(res);
        //
        //     //得到当前页码
        //     console.log(curr);
        //
        //     //得到数据总量
        //     console.log(count);
        // }
    })

        //查询
    $(".item_search_btn").click(function(){
        var newArray = [];
        if($(".item_search_input").val() != ''){
            var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                $.ajax({
                    url : "/item/allList/json",
                    type : "get",
                    dataType : "json",
                    success : function(data){
                        if(window.sessionStorage.getItem("addItems")){
                            var addItems = window.sessionStorage.getItem("addItems");
                            newsData = JSON.parse(addItems).concat(data);
                        }else{
                            newsData = data;
                        }
                        for(var i=0;i<newsData.length;i++){
                            var newsStr = newsData[i];
                            var selectStr = $(".item_search_input").val();
                            function changeStr(data){
                                var dataStr = '';
                                var showNum = data.split(eval("/"+selectStr+"/ig")).length - 1;
                                if(showNum > 1){
                                    for (var j=0;j<showNum;j++) {
                                        dataStr += data.split(eval("/"+selectStr+"/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
                                    }
                                    dataStr += data.split(eval("/"+selectStr+"/ig"))[showNum];
                                    return dataStr;
                                }else{
                                    dataStr = data.split(eval("/"+selectStr+"/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/"+selectStr+"/ig"))[1];
                                    return dataStr;
                                }
                            }
                            //文章标题
                            if(newsStr.title.indexOf(selectStr) > -1){
                                newsStr["title"] = changeStr(newsStr.title);
                            }
                            /* alert(1);
                            //发布人
                            if(newsStr.newsAuthor.indexOf(selectStr) > -1){
                                newsStr["newsAuthor"] = changeStr(newsStr.newsAuthor);
                            }
                            alert(2);
                            //审核状态
                            if(newsStr.newsStatus.indexOf(selectStr) > -1){
                                newsStr["newsStatus"] = changeStr(newsStr.newsStatus);
                            }
                            //浏览权限
                            if(newsStr.newsLook.indexOf(selectStr) > -1){
                                newsStr["newsLook"] = changeStr(newsStr.newsLook);
                            }
                            //发布时间
                            if(newsStr.newsTime.indexOf(selectStr) > -1){
                                newsStr["newsTime"] = changeStr(newsStr.newsTime);
                            }
                            if(newsStr.title.indexOf(selectStr)>-1 || newsStr.newsAuthor.indexOf(selectStr)>-1 || newsStr.newsStatus.indexOf(selectStr)>-1 || newsStr.newsLook.indexOf(selectStr)>-1 || newsStr.newsTime.indexOf(selectStr)>-1){
                                newArray.push(newsStr);
                            } */
                            if(newsStr.title.indexOf(selectStr)>-1){
                                newArray.push(newsStr);
                            }
                        }
                        newsData = newArray;
                        newsList(newsData);
                    }
                })

                layer.close(index);
            },2000);
        }else{
            layer.msg("请输入需要查询的内容");
        }
    })

    //添加文章
    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).one("resize",function(){
        $(".add_item_btn").click(function(){
            var index = layui.layer.open({
                title : "添加商品",
                type : 2,
                content : "/item/itemadd",
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

    //添加商品规格
    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).one("resize",function(){
        $(".add_param_btn").click(function(){
            var index = layui.layer.open({
                title : "添加规格",
                type : 2,
                content : "/item/paramadd",
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



    //推荐文章
    $(".recommend").click(function(){
        var $checkbox = $(".news_list").find('tbody input[type="checkbox"]:not([name="show"])');
        if($checkbox.is(":checked")){
            var index = layer.msg('推荐中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                layer.close(index);
                layer.msg("推荐成功");
            },2000);
        }else{
            layer.msg("请选择需要推荐的文章");
        }
    })

    //审核文章
    $(".audit_btn").click(function(){
        var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                for(var j=0;j<$checked.length;j++){
                    for(var i=0;i<newsData.length;i++){
                        if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
                            //修改列表中的文字
                            $checked.eq(j).parents("tr").find("td:eq(3)").text("审核通过").removeAttr("style");
                            //将选中状态删除
                            $checked.eq(j).parents("tr").find('input[type="checkbox"][name="checked"]').prop("checked",false);
                            form.render();
                        }
                    }
                }
                layer.close(index);
                layer.msg("审核成功");
            },2000);
        }else{
            layer.msg("请选择需要审核的文章");
        }
    })

    //批量删除
    $(".batchDel").click(function(){
        var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                setTimeout(function(){
                    //删除数据
                    for(var j=0;j<$checked.length;j++){
                        for(var i=0;i<newsData.length;i++){
                            if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
                                newsData.splice(i,1);
                                newsList(newsData);
                            }
                        }
                    }
                    $('.news_list thead input[type="checkbox"]').prop("checked",false);
                    form.render();
                    layer.close(index);
                    layer.msg("删除成功");
                },2000);
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //全选
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    //通过判断文章是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)",function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
        if(childChecked.length == child.length){
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
        }else{
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
        }
        form.render('checkbox');
    })

    //是否展示
    form.on('switch(isShow)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("展示状态修改成功！");
        },2000);
    })

    //操作
    $("body").on("click",".news_edit",function(){  //编辑
        layer.alert('您点击了文章编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
    })

    $("body").on("click",".news_collect",function(){  //收藏.
        if($(this).text().indexOf("已收藏") > 0){
            layer.msg("取消收藏成功！");
            $(this).html("<i class='layui-icon'>&#xe600;</i> 收藏");
        }else{
            layer.msg("收藏成功！");
            $(this).html("<i class='iconfont icon-star'></i> 已收藏");
        }
    })

    $("body").on("click",".news_del",function(){  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            //_this.parents("tr").remove();
            for(var i=0;i<newsData.length;i++){
                if(newsData[i].newsId == _this.attr("data-id")){
                    newsData.splice(i,1);
                    newsList(newsData);
                }
            }
            layer.close(index);
        });
    })


    //弹出分类菜单(树形)
    $(".itemCat_btn").click(function(){
        //var rdm = Math.ceil(Math.random()*100);
        //alert(1);
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

        $.post("/itemCat", function(data){
            //alert(data.size);
            laytree({
                elem: '#demotree',//+rdm, //传入元素选择器
                nodes:data,
                click: function(node){
                    $("#itemcatbtn").val(node.name);
                    $("#itemcatbtnval").val(node.id);
                    //layer.close(index);
                    $.get("/param/itemcatid/" + node.id,function (data) {
                        if(data.status == 200 && data.data){
                            $(".params").show();

                            var paramData = JSON.parse(data.data.paramData);
                            var html = "<ul>";
                            for(var i in paramData){
                                var pd = paramData[i];
                                html+="<li><table class=\"paramtable\">";
                                html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
                                for(var j in pd.params){
                                    var ps = pd.params[j];
                                    html+="<tr><td class=\"param\"><span>"+ps+"</span>: </td><td><input autocomplete=\"off\" type=\"text\"/></td></tr>";
                                }
                                html+="</li></table>";
                            }
                            html+= "</ul>";
                            $(".paramstd").eq(0).html(html);
                        }else{
                            $(".params").hide();
                            $(".paramstd").eq(0).empty();
                        }
                    });

                    $('.layui-layer-page').remove();
                    $('.layui-layer-shade').remove();

                }
            });
        })
    })



    //弹出分类菜单(树形)
    $(".itemCat_btns").click(function(){
        //var rdm = Math.ceil(Math.random()*100);
        //alert(1);
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

        $.post("/itemCat",function(data){
            //alert(data.size);
            laytree({
                elem: '#demotree',//+rdm, //传入元素选择器
                nodes:data,
                click: function(node){

                    //$(".addGroupTr").hide().find(".param").remove();
                    $.get("/param/itemcatid/" + node.id,function (data) {
                        if(data.status == 200 && data.data){
                            layer.msg('该类目已经添加，请选择其他类目');
                            $("#itemcatbtn").val("选择分类");
                            //$(".itemCat_btns").click();
                            return ;
                        }
                        $("#itemcatbtn").val(node.name);
                        $("#itemcatbtnval").val(node.id);
                        $('.layui-layer-page').remove();
                        $('.layui-layer-shade').remove();
                        $(".addGroupTr").show();
                    })
                }
            });
        })
    })

    $(".addGroup").click(function(){
        var temple = $(".itemParamAddTemplate li").eq(0).clone();
        $(this).parent().parent().append(temple);
        temple.find(".addParam").click(function(){
            var li = $(".itemParamAddTemplate li").eq(2).clone();
            li.find(".delParam").click(function(){
                $(this).parent().remove();
            });
            li.appendTo($(this).parentsUntil("ul").parent());
        });
        temple.find(".delParam").click(function(){
            $(this).parent().remove();
        });
    });

    //创建一个编辑器图片接口
    layedit.set({
        uploadImage: {
            url: '/upload' //上传接口

        }
    });

    //创建一个编辑器
    var editIndex = layedit.build('news_content');

    var addItemsArray = [],addItems;
    form.on("submit(itemadd)",function(data){
        //是否添加过信息
        if(window.sessionStorage.getItem("addItems")){
            addItemsArray = JSON.parse(window.sessionStorage.getItem("addItems"));
        }
        //显示、审核状态
        //var isShow = data.field.show=="on" ? "checked" : "",
            //newsStatus = data.field.shenhe=="on" ? "审核通过" : "待审核";

        addItems = '{"title":"'+$(".title").val()+'",';  //文章名称
        //addItems += '"id":"'+new Date().getTime()+'",';	 //文章id
        addItems += '"price":"'+$(".price").val()+'",'; //
        addItems += '"num":"'+$(".num").val()+'",'; //
        addItems += '"barcode":"'+$(".barcode").val()+'",'; //
        addItems += '"sellPoint":"'+$(".sellPoint").val()+'",'; //
        //addItems += '"status":"'+$(".status option").eq($(".status").val()).text()+'",'; //开放浏览
        addItems += '"status":"'+$(".status").val()+'",'; //开放浏览
        addItems += '"cid":"'+$(".cid").val()+'"}'; //
        //addItems += '"isShow":"'+ isShow +'",';  //是否展示
        //addItems += '"newsStatus":"'+ newsStatus +'"}'; //审核状态
        addItemsArray.unshift(JSON.parse(addItems));
        window.sessionStorage.setItem("item",JSON.stringify(addItemsArray));

        //$("#itemAddForm [name=cid]").val($("#itemcatbtn").val());
        $("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=price]").val()) * 100);
        layedit.sync(editIndex);


        //取商品的规格
        var paramJson = [];
        $("#itemAddForm .paramstd li").each(function(i,e){
            var trs = $(e).find("tr");
            var group = trs.eq(0).text();
            var ps = [];
            for(var i = 1;i<trs.length;i++){
                var tr = trs.eq(i);
                ps.push({
                    "k" : $.trim(tr.find("td").eq(0).find("span").text()),
                    "v" : $.trim(tr.find("input").val())
                });
            }
            paramJson.push({
                "group" : group,
                "params": ps
            });
        });
        //把json对象转换成字符串
        paramJson = JSON.stringify(paramJson);
        $("#itemAddForm [name=itemParams]").val(paramJson);
        //alert(paramJson);
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.post("/item/additem", $("#itemAddForm").serialize(), function(data){
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("文章添加成功！");
                //layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            },2000);
        });
        return false;
    })


    form.on("submit(paramadd)",function(data){
        var params = [];
        var groups = $("#paramAddForm [name=group]");
        groups.each(function(i,e){
            var p = $(e).parentsUntil("ul").parent().find("[name=param]");
            var _ps = [];
            p.each(function(_i,_e){
                var _val = $(_e).val();
                if($.trim(_val).length>0){
                    _ps.push(_val);
                }
            });
            var _val = $(e).val();
            if($.trim(_val).length>0 && _ps.length > 0){
                params.push({
                    "group":_val,
                    "params":_ps
                });
            }
        });
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var url = "/param/save/"+$("#paramAddForm [name=cid]").val();

        $.post(url,{"paramData":JSON.stringify(params)}, function(data){
            if(data.status == 200){
                setTimeout(function(){
                    top.layer.close(index);
                    top.layer.msg("参数添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                },2000);
                return false;
            }

        })
        //return false;
    })

    //上传图片

    layupload.render({
        elem:"#picup",
        url: '/upload' //上传接口
        ,method:'post'
        ,accept:'images'
        ,exts:'jpg'
        ,size:0
        ,auto: false
        ,bindAction: '#picup_yes'

        ,choose: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
                $("#picsee").empty();
                $('#picsee').append('<img style="width:100px;height:100px;" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img imggray" id="itempicyes'+index+'">');
                $('#picup_div').show();
	      });
	    }
	    ,done: function(res,index){
	      //上传完毕
            $("#itempicyes"+index).attr("class","layui-upload-img");
            $("#itemimage").val(res.data.src);
	    }
    })





})