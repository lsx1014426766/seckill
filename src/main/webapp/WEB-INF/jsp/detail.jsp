<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>秒杀详情页</title>
<%@include file="common/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="pannel-heading">
				<h1>${seckill.name}</h1>
			</div>

			<div class="pannel-body">
				<h2 class="text-danger">
					<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 展示倒计时 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 手机号登录弹出框 -->
	<div id="killPhoneModal" class="modal fade">
	   <div class="modalDialog">
	     <div class="modal-content">
	        <div class="modal-header">
	           <h3 class="modal-title text-center">
	             <span class="glyphicon glyphicon-phone"></span>秒杀电话：
	           </h3>
	        </div>
	         <div class="modal-body">
	           <div class="row">
	             <div class="col-xs-8 col-xs-offset-2 ">
	               <input type="text" id="killPhoneKey" name="killPhone" class="form-control" placeholder="请输入手机号"/>
	             </div>
	           </div>
	         </div>
	         <div class="modal-footer">
	           <span class="glyphicon" id="killPhoneMessage"></span>
	           <button class="btn btn-success" type="button" id="killPhoneBtn" >
	             <span class="glyphicon glyphicon-phone" ></span>
	             Submit
	           </button>
	         </div>
	     </div>
	   </div>
	
	</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	
	<!-- 使用CDN获取公用的Jquery http://www.bootcdn.com/ -->
	<!-- jquery cookie插件  countDown插件 -->
	<script type="text/javascript" src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
	<script type="text/javascript" src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="/resources/script/seckill.js"></script>
 
    <script type="text/javascript">
    //使用el表达式传入参数  el加上引号才不报错，虽然不加也不影响运行
	     $(function(){
	  	  seckill.detail.init({
	  		seckillId :${seckill.seckillId} ,
	  		 startTime : ${seckill.startTime.time},
	  		  endTime : ${seckill.endTime.time}
	  	  });
	    }); 
	  </script>
</html>
