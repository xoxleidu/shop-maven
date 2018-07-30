<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/inpage/head.jsp" %>
<style>

	.cat-param-li{
	width:245px;
	margin:20px;
	padding:20px;
	background-color: #eee;
	float:left;
	}
	li.cat-param-li ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	}
	li.cat-param-li ul li {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	clear:both;
	height:45px;
	line-height:45px\9;
	}
	li.cat-param-li ul li input{
	width:130px;
	float: left;
	margin: 0px;
	padding: 0px;
	}

	li.cat-param-li ul li a{
	width:60px;

	margin: 3px 0px;
	padding: 0px;
	}
	li.cat-param-li ul li label{
	float: left;
	margin: 1px;
	padding: 0px;
	border-left:#aaa 1px solid;
	height:45px;
	line-height:38px;
	color:#aaa;
	}
	.itemParam{
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	position: relative;
	clear:both;
	}

	</style>
<body class="childrenBody">
	<table id="paramAddForm" class="itemParam">
	<tr><td>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">商品分类</label>
				<div class="layui-inline">
					<input class="layui-btn itemCat_btns cid" type="button" value="选择分类" id="itemcatbtn" />
					<input type="hidden" id="itemcatbtnval" lay-verify="required" name="cid"/>
				</div>
			</div>
		</div>

		<div class="layui-form-item hide addGroupTr">
			<label class="layui-form-label">规格参数:</label>
			<div class="layui-input-block">
				<a href="javascript:void(0)" class="layui-btn layui-btn-warm addGroup">添加分组</a>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="paramadd">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</td></tr>
	</table>

	<div  class="itemParamAddTemplate" style="display: none;" >
		<li class="cat-param-li param">
			<ul>
				<li>
					<input type="text" class="layui-input" style="width:183px;" name="group"/>
					<a href="javascript:void(0)" class="layui-btn layui-btn-small layui-btn-normal addParam"  title="添加参数">添加参数</a>
				</li>
				<li>
					<label>----------</label>
					<input type="text" class="layui-input" name="param"/>
					<a href="javascript:void(0)" class="layui-btn layui-btn-small layui-btn-danger delParam" title="删除">删除</a>
				</li>
			</ul>
		</li>
	</div>

	<script type="text/javascript" src="../js/item.js"></script>
  
<%@ include file="/WEB-INF/admin/inpage/bottom.jsp" %> 