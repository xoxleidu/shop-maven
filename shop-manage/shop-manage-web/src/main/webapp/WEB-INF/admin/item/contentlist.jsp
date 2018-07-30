        <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/inpage/head.jsp" %>
        <link rel="stylesheet" href="/js/ztree/zTreeStyle.css" type="text/css">
        <script type="text/javascript" src="/js/ztree/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="/js/ztree/ztree.js"></script>
        <script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.js"></script>
        <script type="text/javascript" src="/js/ztree/jquery.ztree.exedit.js"></script>
<body class="childrenBody">

    <div class="ztreeDiv">
        <div class="ztreeDivlist">
                <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="ztreeDivtable">
                <blockquote class="layui-elem-quote content_search hide">

                <div class="layui-inline">
                        <input class="layui-btn content_add_btn" type="button" value="" id="content_add_btn" />
                        <input class="layui-btn content_add_btn_val" type="hidden" value="" id="content_add_btn_val" />
                </div>

                </blockquote>
                <table id="contentlist"></table>
        </div>
        <div style="clear:both;"></div>
    </div>
        <script type="text/html" id="contentlistedit">
        <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
        </script>
	<script type="text/javascript" src="../js/content.js"></script>

<!-- 内容主体区域 -->

<%@ include file="/WEB-INF/admin/inpage/bottom.jsp" %>