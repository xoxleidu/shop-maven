<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/inpage/head.jsp" %>
<body class="childrenBody">
	<form class="layui-form" id="contentAddForm">
		<div class="layui-form-item">
			<label class="layui-form-label">商品标题</label>
			<div class="layui-input-block">
				<input type="hidden" id="categoryId" name="categoryId"/>
				<input type="text" class="layui-input title" lay-verify="required" name="title" placeholder="请输入商品名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品副标题</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input subTitle" lay-verify="required" name="subTitle" placeholder="请输入商品名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品简介</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea titleDesc" lay-verify="required" name="titleDesc" placeholder="请输入商品简介"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品链接</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input url" lay-verify="required" name="url" placeholder="请输入商品名称">
			</div>
		</div>
		<div class="layui-form-item">
		<label class="layui-form-label">商品图片1</label>
			<div class="layui-upload">
				<button type="button" class="layui-btn pic_btn" id="picup1">选择图片</button>
				<blockquote class="layui-elem-quote layui-quote-nm" style="margin:10px auto;width:87%;">
					预览图：
					<div class="layui-upload-list" id="picsee1"></div>
				</blockquote>
				<input type="hidden" id="contentpic1" name="pic"/>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">商品图片2</label>
			<div class="layui-upload">
				<button type="button" class="layui-btn pic_btn" id="picup2">选择图片</button>
				<blockquote class="layui-elem-quote layui-quote-nm" style="margin:10px auto;width:87%;">
					预览图：
					<div class="layui-upload-list" id="picsee2"></div>
				</blockquote>
				<input type="hidden" id="contentpic2" name="pic2"/>
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide content" lay-verify="content" name="content" id="content_content"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="contentadd">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	
	<script type="text/javascript" src="../js/contentadd.js"></script>

<!-- 内容主体区域 -->

<%@ include file="/WEB-INF/admin/inpage/bottom.jsp" %>