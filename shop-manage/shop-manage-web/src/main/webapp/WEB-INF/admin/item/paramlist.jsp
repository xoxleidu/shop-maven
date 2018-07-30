<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/inpage/head.jsp" %>

<body class="childrenBody">
	<blockquote class="layui-elem-quote param_search">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input param_search_input">
		    </div>
		    <a class="layui-btn search_param_btn">查询规格参数</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal add_param_btn">添加规格</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel">批量删除</a>
		</div>
    </blockquote>
    <table id="paramlist"></table>
    <script type="text/html" id="paramlistedit">
        <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
    </script>
	<script type="text/javascript" src="../js/item.js"></script>

<!-- 内容主体区域 -->
  
<%@ include file="/WEB-INF/admin/inpage/bottom.jsp" %> 