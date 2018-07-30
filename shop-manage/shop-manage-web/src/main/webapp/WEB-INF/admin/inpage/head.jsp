<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/css/font.css" media="all" />
	<script type="text/javascript" src="/layui/layui.js"></script>
	<style>
		.childrenBody{
			padding:20px;
		}
		.imggray {
			-webkit-filter: grayscale(100%);
			-moz-filter: grayscale(100%);
			-ms-filter: grayscale(100%);
			-o-filter: grayscale(100%);

			filter: grayscale(100%);

			filter: gray;
		}
        .hide{
            display:none;
        }
		.ztreeDiv{
			padding:20px;
			border:1px solid #dedede;
	width:100%;
		}
	.categorydiv{
		padding:20px;
	}
	.ztreeDiv div.ztreeDivlist {
		float:left;
	padding-right:20px;
	width:10%;
	}
	.ztreeDiv div.ztreeDivtable {
	width:80%;
	float:right;
	padding-left:20px;
	border-left:1px solid #dedede;
	}
	</style>
</head>
