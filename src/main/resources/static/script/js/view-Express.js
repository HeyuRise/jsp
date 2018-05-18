var mail;
$(function(){
	var url=window.location.href;
	var loc= url.substring(url.lastIndexOf('=')+1, url.length);
	loaddetail(loc)
})

//加载详情
function loaddetail(loc){
	$.ajax({  
	    type: "get",  
	    url: "/ebes/order/detail?orderId="+loc,  
	    contentType: 'application/json', 
	    success: function (data) {  
			var expressType = data.detail.expressType;
			var mailnos = data.detail.mailnos;
			var express = data.detail.express;
			var sender = data.detail.sender;
			var receiver = data.detail.receiver;
			var payInfo = data.detail.payInfo;
			var packageInfo = data.detail.packageInfo;
			var serviceInfos = data.detail.serviceInfos;
			detai(loc,expressType,mailnos,express,sender,receiver,payInfo,packageInfo,serviceInfos)
		}
	})  
}
function detai(loc,expressType,mailnos,express,sender,receiver,payInfo,packageInfo,serviceInfos){
	for(var i in mailnos){
		var html='<option>'+mailnos[i]+'</option>'
		$("#mail").append(html)
	}
	mail=$("#mail").val()
	loadrote(mail)
	
	$("#mailno").html(express.mailno)
	for(var i in express.mailnoChild){
		var html=''
			html='<span>'+express.mailnoChild[i]+'</span>&nbsp;&nbsp;&nbsp;'
		$("#mailnoChild").append(html)
	}
	//$("#mailnoChild").html(express.mailnoChild)
	$("#receiver").html(express.receiver)
	$("#receiveDate").html(express.receiveDate)
	$("#status").html(express.status)
//	$("#printCount").html(express.printCount)
	
	$("#company").html(sender.company)
	$("#contact").html(sender.contact)
	$("#tel").html(sender.tel)
	$("#mobile").html(sender.mobile)
	$("#address").html(sender.address)
	
	$("#receivercompany").html(receiver.company)
	$("#receivercontact").html(receiver.contact)
	$("#receivertel").html(receiver.tel)
	$("#receivermobile").html(receiver.mobile)
	$("#receiveraddress").html(receiver.address)
	
	$("#expressType").html(expressType)
	
	$("#custId").html(payInfo.custId)
	$("#payTypeName").html(payInfo.payTypeName)
	
	$("#weight").html(packageInfo.weight)
	$("#money").html(packageInfo.money)
	$("#count").html(packageInfo.count)
	$("#payWeight").html(packageInfo.payWeight)
	$("#name").html(packageInfo.name)
	$("#parcelQuantity").html(packageInfo.parcelQuantity)
	$("#orderId").html(packageInfo.orderId)
	
	if(serviceInfos!=""){
		loadserviceInfos(serviceInfos)
	}else{
		$("#service").remove()
	}
	
	
}

//加载路由
function loadrote(mail){
	if(mail==""){
		$("#rote").append("暂无运单号，不可查询物流信息")
	}else{
		$.ajax({  
	   		type: "get",  
	    	url: "/ebes/order/route?mailno="+mail,  
	    	contentType: 'application/json', 
	    	success: function (data) {  
				var expressRoute = data.expressRoute;
				var result = data.result;
				rotecontent(expressRoute,result);
			}
		})
	} 
}
function rotecontent(expressRoute,result){
	$("#rote").html("")
	var html=""

	if(expressRoute==null || expressRoute.length==0){
		html="<p>暂无物流消息</p>"
		$("#rote").append(html)	
	}else{
		if(expressRoute[0].routes==""){
			html="<p>暂无物流消息</p>"
			$("#rote").append(html)
		}else{
			for(var i=0;i<expressRoute[0].routes.length;i++){
				html='<li>'+
							'<i class="node-icon"></i>'+
							'<span class="txt">'+expressRoute[0].routes[i].acceptDate+'&nbsp;&nbsp;&nbsp;'+expressRoute[0].routes[i].acceptTime+'</span>'+
							'<span class="time">'+expressRoute[0].routes[i].accrptAddress+':&nbsp;&nbsp;&nbsp;'+expressRoute[0].routes[i].remark+'</span>'+
						'</li>'
				$("#rote").append(html)
			}
		}
	}

}
//切换路由
function tab(){
	mail=$("#mail").val()
	loadrote(mail)
}



//加载增值服务
function loadserviceInfos(serviceInfos){
	$("#service_content").html("")
	for(var i in serviceInfos){
		var html=''
		html+='<div class="ok_service" style="margin-right: 20px;">'
		html+=	'<p class="add_service_title">'+serviceInfos[i].serviceName+'</p>'
		for(var j=0;j<serviceInfos[i].key.length;j++ ){
			html+= 	'<div style="margin-bottom: 3px;">'
			html+= 		'<span>'+serviceInfos[i].key[j]+'</span>:&nbsp;&nbsp;<span>'+serviceInfos[i].value[j]+'</span>'
			html+='</div>'
		}
		html+='</div>'
		$("#service_content").append(html)
			
	}
}