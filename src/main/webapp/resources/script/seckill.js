//javascript模块化
//seckill.detail.init(params);
var seckill={
		//存放秒杀相关的url
		
		URL:{
		   now:function(){
			   return "/seckill/time/now";
		   },
		   exposer:function(seckillId){
			   return "/seckill/"+seckillId+"/exposer";
		   },
		   execute:function(seckillId,md5){
			   return "/seckill/"+seckillId+"/"+md5+"/execution";
		   }
		},
		handleSeckill:function(seckillId,node){
			//秒杀逻辑
			node.hide();//隐藏之前倒计时的节点位置的内容
			node.html('<button id="kilBtn" class="btn btn-primary btn-lg">秒杀</button>');
			$.post(seckill.URL.exposer(seckillId),function(result){
				//获取秒杀地址返回
				if(result && result['success']){
					console.log("成功获取秒杀地址");
					var exposer=result['data'];
					if(exposer && exposer['exposed']){
						var md5=exposer['md5'];
						//获取秒杀地址
						var killurl=seckill.URL.execute(seckillId,md5);
						console.log("kill url:"+killurl);
						//绑定一次点击事件
						$("#kilBtn").one("click",function(){
							//禁用此按钮
							$(this).add("disabled");
							//用户触发购买
							//执行秒杀
							$.get(killurl,{},function(result){
								if(result && result['success']){
									var data=result['data'];
									//var state=data['state'];
									var stateInfo=data['stateInfo'];
									node.html('<span label="label label-success">'+stateInfo+'</span>');
									
								}else{
									console.log("execute result"+result);
								}
							});
						});
						node.show();
					}else{
						//未开启秒杀  服务器和客户端的时间有偏差
						var start=exposer['start'];
						var now=exposer['now'];
						var end=exposer['end'];
						seckill.countdown(seckillId, now, start, end);
					}
				}else{
					console.log("url result:"+result);
				}
			});
		},
		validatePhone:function(phone){
			
			if(phone && phone.length==11 && !isNaN(phone)){
				return true;
			}else{
				return false;
			}
		},
		countdown:function(seckillId,nowTime,startTime,endTime){
			var seckillBox=$("#seckill-box");
			console.log("now:"+nowTime);
			console.log("startTime:"+startTime);
			if(nowTime>endTime){
				
				//秒杀结束
				seckillBox.html("秒杀结束");
			}else if(nowTime<startTime){
				//秒杀未开始
				//seckillBox.html("秒杀未开始");
				//计时
				var killTime=new Date(parseInt(startTime)+1000);
				console.log(killTime);
				//typeError错误有2个地方：1引入的插件不对jquery.countdown，not jquery-countdown
				//方法名是全小写，区分大小写的
				seckillBox.countdown(killTime,function(event){
					//时间格式
					var format=event.strftime('秒杀倒计时:%D天  %H时  %M分  %S秒');
					seckillBox.html(format);
					/*计时完成后的回调事件*/
				}).on("finish.countdown",function(){
					//获取秒杀地址，实现控制逻辑
					seckill.handleSeckill(seckillId,seckillBox);
				});
				
			}else{
				//秒杀开始
				//获取秒杀地址，实现控制逻辑
				seckill.handleSeckill(seckillId,seckillBox);
				
			}
		},
		//详情页
		detail:{
			//详情页的初始化
			init:function(params){
				//手机验证和登录，计时交互
				//规划我们的交互流程
				var killPhone=$.cookie("killPhone");
				
				//验证手机号
				if(!seckill.validatePhone(killPhone)){
					//false绑定手机号
					var killPhoneModal=$("#killPhoneModal");
					//控制输出  
					killPhoneModal.modal({
						show:true,//显示弹出层
						backdrop:'static',//禁止位置关闭
						keyboard:false//关闭键盘时间
					});
					$("#killPhoneBtn").click(function(){
						var inputPhone=$("#killPhoneKey");
						//alert("d"+inputPhone.val());
						//alert("e"+inputPhone.length);
						if(seckill.validatePhone(inputPhone.val())){
						//	alert("f手机号验证通过，写入cookie");
							//phone写入cookie  7天
							$.cookie('killPhone',inputPhone.val(),{expires:7,path:'/seckill'});
							//刷新页面  验证通关过流程
							window.location.reload();
						}else{
							$("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误</label>').show(300);
							
						}
					});
				}
				//已经登录
				//计时交互
				var startTime=params["startTime"];
				var endTime=params["endTime"];
				var seckillId=params["seckillId"];
				console.log(seckill.URL.now());
				$.get(seckill.URL.now(),{},function(result){
					console.log("result1:"+result);
					if(result && result['success']){
						var nowTime=result['data'];
						seckill.countdown(seckillId,nowTime,startTime,endTime);
						//计时判断
					}else{
						console.log('result:'+result);
					}
					
				});
			}
		}
}