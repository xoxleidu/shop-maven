layui.config({
    base : "js/"
}).use(['upload','form','layedit','jquery','layer','laydate'],function() {
    var layupload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laydate = layui.laydate,
        form = layui.form,
        layedit = layui.layedit,
        $ = layui.jquery;

    //创建一个编辑器图片接口
    layedit.set({
        uploadImage: {
            url: '/upload' //上传接口

        }
    });

    //创建一个编辑器
    var editIndex = layedit.build('content_content');


    //访问父页面元素值
    $("#categoryId").val(parent.$("#content_add_btn_val").val());
   //alert(parentId);
    form.on("submit(contentadd)",function(data){

        layedit.sync(editIndex);

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $.post("/content/addcontent", $("#contentAddForm").serialize(), function(data){
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("添加成功！");
                //layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            },2000);
        });
        return false;
    })

    //上传图片

    layupload.render({
        elem:"#picup1",
        url: '/upload' //上传接口
        ,method:'post'
        ,accept:'images'
        ,exts:'jpg'
        ,done: function(res){
            //上传完毕
            $("#picsee1").empty();
            $("#contentpic1").val(res.data.src);
            $('#picsee1').append('<img style="width:100px;height:100px;" src="'+ res.data.src +'" class="layui-upload-img">');

        }
    })

    layupload.render({
        elem:"#picup2",
        url: '/upload' //上传接口
        ,method:'post'
        ,accept:'images'
        ,exts:'jpg'
        ,done: function(res){
            //上传完毕
            $("#picsee2").empty();
            $("#contentpic2").val(res.data.src);
            $('#picsee2').append('<img style="width:100px;height:100px;" src="'+ res.data.src +'" class="layui-upload-img">');

        }
    })


})
