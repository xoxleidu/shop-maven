<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/inpage/head.jsp" %>
<style>
	.cat-param-ul ul li {
	margin: 10px;
	padding: 10px;
	float: left;
	background-color: #eee;
	}
	.paramtable td {
	padding: 10px;
	}
	.paramtable .group{
	text-align: center;
	background-color: #009f95;
	color: #ffffff;
	}
	.paramtable input{
	height: 26px;
	}
</style>
<body class="childrenBody">
	<form class="layui-form" action="" id="itemAddForm" method="post">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">商品分类</label>
				<div class="layui-inline">
					<input class="layui-btn itemCat_btn cid" type="button" value="选择分类" id="itemcatbtn" />
					<input type="hidden" id="itemcatbtnval" value="70" name="cid"/>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input title" lay-verify="required" name="title" placeholder="请输入商品名称">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品简介</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea sellPoint" lay-verify="required" name="sellPoint" placeholder="请输入商品简介"></textarea>
			</div>
		</div>
		<div class="layui-form-item  params">
			<label class="layui-form-label">商品规格 <input type="hidden" name="itemParams"/></label>
			<div class="cat-param-ul paramstd">
			</div>
		</div>
		<div class="layui-form-item">
		<label class="layui-form-label">商品图片</label>
			<div class="layui-upload">
				<button type="button" class="layui-btn pic_btn" id="picup">选择图片</button>
				<blockquote class="layui-elem-quote layui-quote-nm" style="margin:10px auto;width:87%;">
					预览图：
					<div class="layui-upload-list" id="picsee"></div>
					<div style="display:none;" id="picup_div"><button type="button" class="layui-btn" id="picup_yes">开始上传</button></div>
				</blockquote>
				<input type="hidden" id="itemimage" name="image"/>
			</div>
		</div>
		<div class="layui-form-item">
			<!--<div class="layui-inline">
				<label class="layui-form-label">自定义属性</label>
				<div class="layui-input-block">
					<input type="checkbox" name="tuijian" class="tuijian" title="推荐">
					<input type="checkbox" name="shenhe" class="newsStatus" title="审核">
					<input type="checkbox" name="show" class="isShow" title="展示">
				</div>
			</div>-->
			<div class="layui-inline">
				<label class="layui-form-label">价格</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input price" name="price" lay-verify="required|number" placeholder="请输入商品价格">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">数量</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input num" name="num" lay-verify="required|number" placeholder="请输入商品数量">
				</div>
				<!--<div class="layui-input-inline">
					<input type="text" class="layui-input num" lay-verify="required|date" onclick="layui.laydate({elem:this})">
				</div>-->
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">浏览</label>
				<div class="layui-input-inline">
					<select name="status" class="newsLook status" lay-filter="browseLook">
				        <option value="1" selected="selected">开放浏览</option>
				        <option value="0">会员浏览</option>
				    </select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">二维码</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input barcode" name="barcode" lay-verify="number" placeholder="一串数字">
			</div>
		</div>
	<!--<div class="layui-form-item">
			<label class="layui-form-label">内容摘要</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容摘要" class="layui-textarea sellpoint"></textarea>
			</div>
		</div>-->
		<div class="layui-form-item">
			<label class="layui-form-label">文章内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide sellpoint" lay-verify="content" name="desc" id="news_content"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="itemadd">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

	<script type="text/javascript" src="../js/item.js"></script>
  
<%@ include file="/WEB-INF/admin/inpage/bottom.jsp" %> 