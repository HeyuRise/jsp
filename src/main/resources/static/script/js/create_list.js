/**
 * Created by LiLU on 2017/8/30.
 */
var jsonstr
var num
var value
var length
$(function(){
	//禁止number输入e
	$('input[type=number]').keypress(function(e) {
		　　if (!String.fromCharCode(e.keyCode).match(/[0-9\.]/)) {
		　　　　return false;
		　　}
	});

    $("#name")[0].selectedIndex = -1; 
})
//寄件人获取省市区
$(function(){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/province",
		success:function(data){
			var size = data.size;
			var items = data.items;
			sendprovince(items,size)
		}
	})
})
function sendprovince(items,size){
	for(var i=0;i<size;i++){
		var provincehtml='<option>'+items[i].province+'</option>'
		$("#sendProvince").append(provincehtml)
	}
	$("#sendProvince")[0].selectedIndex = -1; 
}
//市
function selectcity(){
	var province=$("#sendProvince").val();
	if(province!=""&& province!=null){
		$.ajax({
			type:"GET",
			dataType:"json",
			url:"/ebes/util/city?p_text="+province,
			success:function(data){
				var size = data.size;
				var items = data.items;
				sendloadcity(items,size)
			}
		})
	}
	$("#sendCounty")[0].selectedIndex = -1; 
}
function sendloadcity(items,size){
	$("#sendCity").html("")
	for(var i=0;i<size;i++){
		var cityhtml='<option>'+items[i].city+'</option>'
		$("#sendCity").append(cityhtml)
	}
	$("#sendCity")[0].selectedIndex = -1; 
}
//区
function selectcountry(){
	var province=$("#sendProvince").val();
	var city=$("#sendCity").val();
	if(province!=""&& province!=null && city!=""&& city!=null){
		$.ajax({
			type:"GET",
			dataType:"json",
			url:"/ebes/util/county?p_text="+province+"&c_text="+city,
			success:function(data){
				var size = data.size;
				var items = data.items;
				sendloadcountry(items,size)
			}
		})
	}
}
function sendloadcountry(items,size){
	$("#sendCounty").html("") 
	for(var i=0;i<size;i++){
		var countryhtml='<option>'+items[i].county+'</option>'
		$("#sendCounty").append(countryhtml)
	}
	$("#sendCounty")[0].selectedIndex = -1; 
}




//收件人获取省市区
$(function(){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/province",
		success:function(data){
			var size = data.size;
			var items = data.items;
			receoveprovince(items,size)
		}
	})
})
function receoveprovince(items,size){
	for(var i=0;i<size;i++){
		var provincehtml='<option>'+items[i].province+'</option>'
		$("#receiverProvince").append(provincehtml)
	}
	$("#receiverProvince")[0].selectedIndex = -1; 
}
//市
function receovecity(){
	var province=$("#receiverProvince").val();
	if(province!=""&& province!=null){
		$.ajax({
			type:"GET",
			dataType:"json",
			url:"/ebes/util/city?p_text="+province,
			success:function(data){
				var size = data.size;
				var items = data.items;
				loadcity(items,size)
			}
		})
	}
	$("#receiverCounty")[0].selectedIndex = -1; 
}
function loadcity(items,size){
	$("#receiverCity").html("")
	for(var i=0;i<size;i++){
		var cityhtml='<option>'+items[i].city+'</option>'
		$("#receiverCity").append(cityhtml)
	}
	$("#receiverCity")[0].selectedIndex = -1; 
}
//区
function receovecountry(){
	var province=$("#receiverProvince").val();
	var city=$("#receiverCity").val();
	if(province!=""&& province!=null && city!=""&& city!=null){
		$.ajax({
			type:"GET",
			dataType:"json",
			url:"/ebes/util/county?p_text="+province+"&c_text="+city,
			success:function(data){
				var size = data.size;
				var items = data.items;
				loadcountry(items,size)
			}
		})
	}
}
function loadcountry(items,size){
	$("#receiverCounty").html(""); 
	for(var i=0;i<size;i++){
		var countryhtml='<option>'+items[i].county+'</option>'
		$("#receiverCounty").append(countryhtml)
	}
	$("#receiverCounty")[0].selectedIndex = -1; 
}




//获取快递公司
$(function(){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/enableExpressCompany",
		success:function(data){
			var size = data.size;
			var items = data.items;
			loadcompanyId(items,size)
		}
	})
})
function loadcompanyId(items,size){
	for(var i=0;i<size;i++){
		var itemshtml='<option value="'+items[i].id+'">'+items[i].name+'</option>'
		$("#companyId").append(itemshtml)
	}
	//$("#companyId")[0].selectedIndex=0; 
}



//获取业务类型
$(function(){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/enableProduct",
		success:function(data){
			var size = data.size;
			var items = data.items;
			loadtype(items,size)
		}
	})
})
function loadtype(items,size){
	for(var i=0;i<size;i++){
		var html='<option value="'+items[i].code+'">'+items[i].name+'</option>'
		$("#expressType").append(html)
	}
	//$("#expressType")[0].selectedIndex = -1; 
}

 




//选择添加增值服务
$(function(){
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/enableService",
		success:function(data){
			var size = data.size;
			var items = data.items;
			loadservice(items,size)
		}
	})
})
//加载增值服务列表
function loadservice(items,size){
	for(var i=0;i<size;i++){
		var servicehtml='<div  index="'+items[i].code+'" style="padding-left:10px;padding-top:3px;padding-bottom:3px;cursor:pointer;" id="list'+items[i].code+'" class="back" onclick="tab(this)" >'+
						'<div class="checkbox checkbox-info"  >'+
        					'<input index="'+items[i].code+'" id="'+items[i].code+'" value="'+items[i].name+'" name="'+items[i].name+'" class="styled" type="checkbox" onclick="content(this)">'+
        					'<label for="'+items[i].code+'"></label>'+
        					'<span id="'+items[i].code+'">'+items[i].name+'</span>'+
        				'</div>'+
        				'</div>'
		$("#modal-right").append(servicehtml)
	}

}
//点击'+'出现模态框
function service(){
	$("#myModal").modal("show")
	if(jsonstr=="" || jsonstr==undefined){
		
	}else{
		$(".back").css("background","#ffffff"); 
		$(".sevice_content").hide();
	}
}
//是否勾选增值服务
function content(obj){
	var name=$(obj).attr("name")
	var index=$(obj).attr("index")
	if($(obj).is(':checked')){
		$(".back").css("background","#ffffff"); 
		$("#list"+index+"").css("background","#ddd")
		content_html(index,event)
	}else{
		$(".back").css("background","#ffffff"); 
		$("#list"+index+"").css("background","#ffffff")
		cancel_html(index,event)
	}
}
//切换隐藏和显示
function tab(obj){
	var id=$(obj).attr("index");
	if($("#service_"+id+"").is(':hidden')){
		$(".sevice_content").hide()
		$("#service_"+id+"").show()
		$(".back").css("background","#ffffff"); 
		$("#list"+id+"").css("background","#ddd")
		
	}else{
		$("#list"+id+"").css("background","#ffffff"); 
		$("#service_"+id+"").hide()
	}
}
//勾选增值服务，右边出现对应的内容
function content_html(index,event){
	//阻止事件冒泡函数
	event.stopPropagation();
	$(".sevice_content").hide()
	$("#service_"+index+"").show()
}
//取消勾选增值服务，右边消失
function cancel_html(index,event){
	//阻止事件冒泡函数
	event.stopPropagation();
	$("#service_"+index+"").hide()
	$("#service_"+index+" input").val("")
}


//勾选确定--创建数组
function content_ok(){
	var sumbit_flag=true;
	$(".three").each(function (){
		  if($(this).val()!="" ){
			  if(!/^-?\d+\.?\d{0,3}$/.test($(this).val()) ||  $(this).val()<="0"){
				  $(this).addClass("red_line")
				  sumbit_flag=false
			  };  
		  }
	})
	
	if(sumbit_flag){
		obj = $("input[type='checkbox']");
		jsonstr=[]; 
		for( k in obj){
	        if(obj[k].checked){ 
	        	  var code=obj.eq(k).attr("index")
	        	  var  txt=$("#service_"+code+"").find(':input');
	        	  var label =$("#service_"+code+"").find('label');
	        	  num = [];
	        	  value=[];
			      for (var i = 0; i < txt.length; i++) {
			    	  num.push({key:label.eq(i).text(),value:txt.eq(i).val()});
			    	  value.push(txt.eq(i).val()); 
			       }
			    
			      var code=$("input[type='checkbox']").eq(k).attr("id")
			      jsonstr.push({code:code,title:obj.eq(k).attr("name"),ky:num,value:value}); 
	        }
		}
		length=jsonstr.length
		//document.write(value);
	    loadok(jsonstr)
	    $("#myModal").modal("hide")
	    return jsonstr;
	}else{
		swal("完善红色框中的内容再提交！");
	}

}
//勾选确定--根据创建的数组，显示相应的值
function loadok(jsonstr){
	$("#addservice").html("")
	for(var i in jsonstr){
		var html=''
		html+='<div class="ok_service" id="index'+jsonstr[i].title+'" style="margin-left:25px;margin-bottom:7px">'
		html+=	'<p class="add_service_title">'+jsonstr[i].title+'</p>'
		for(var j in jsonstr[i].ky){
			html+= 	'<div style="margin-bottom: 3px;">'
			if(""+jsonstr[i].ky[j].key+""==""){
				html+= 		'<span>'+jsonstr[i].ky[j].key+'</span>&nbsp;&nbsp;<span>'+jsonstr[i].ky[j].value+'</span>'
			}else{
				html+= 		'<span>'+jsonstr[i].ky[j].key+'</span>：&nbsp;&nbsp;<span>'+jsonstr[i].ky[j].value+'</span>'
			}
			html+= 	'</div>'
		}
		html+='<div class="ok_service-content">'
		html+=	'<ul class="icon" >'
		html+=		'<li><a  index="'+jsonstr[i].code+'" id="'+jsonstr[i].title+'" onclick="update(this)"><i class="iconfont" style="font-size: 2.5rem">&#xe7d3;</i></a></li>'
		html+=		'<li><a id="'+jsonstr[i].title+'" onclick="delete_add(this)"><i class="iconfont" style="font-size: 2.5rem">&#xe7f5;</i></a></li>'
		html+=	'</ul>'
		html+='</div>'
		html+='</div>'
		$("#addservice").append(html)
	}
}
//勾选修改
function update(obj){
	var indexID=$(obj).attr("index")
	$(".back").css("background","#ffffff"); 
	$("#list"+indexID+"").css("background","#ddd")
	$(".sevice_content").hide()
	$("#service_"+indexID+"").show()
	$("#myModal").modal("show")
}
//勾选删除
function delete_add(obj){
	var name=$(obj).attr("id")
	$("#index"+name+"").remove();
	$("input[name='"+name+"']").attr("checked",false)
	var index=$("input[name='"+name+"']").attr("index")
	$("#service_"+index+" input").val("")
	for(var i=0;i<length;i++){
		var bb=jsonstr[i].title
		if(name==bb){
			jsonstr.splice(i,1);//删除数组的第i行
			length--
			return false;
		}
	}
}
//模态初始化
$(function(){
	$("#myModal").on("hidden.bs.modal", function() {  
		$("input").removeClass("red_line")
		if(length=='0'){
			$("input[type='checkbox']").attr("checked",false)
			$(".modal_left div").hide()
			$(".back").css("background","#ffffff"); 
			$(".modal_left input").val("")
		}else{
			//左边勾选和已选择的要一样
			$("input[type='checkbox']").attr("checked",false)
			for(var i=0;i<length;i++){
				$("input[name='"+jsonstr[i].title+"']").attr("checked",true)
			}
			//右边数据不提交的话要和已提交的一样
			$(".sevice_content").children("input").val("")
			for(var i=0;i<length;i++){
				var size=$("#service_"+jsonstr[i].code+">input").size()
				for(var j=0;j<size;j++){
					$("#service_"+jsonstr[i].code+"").children("input").eq(j).val(""+jsonstr[i].value[j]+"")
				}
			}
		}
	});
})


//校验--提交
function btn_ok(){
	var flag=true
	//件数为正整数校验
	$(".num").each(function (){
		 var badnumcheck=/^\+?[1-9]\d*$/;
		 $(this).removeClass("red_line") ;
		  if($(this).val()=="" ){
			  $(this).addClass("red_line")
			  flag=false
		  };
		  if($(this).val()!="" ){
			  if(!badnumcheck.test($(this).val()) ){
				  $(this).addClass("red_line")
				  flag=false
			  };  
		  }
	})
	
	//不为空
	$(".input").each(function(){
		$(this).removeClass("red_line") 
		if($(this).val()==""){
			$(this).addClass("red_line")
			flag=false
		}
	})

	//如果是扫码下单则select变input，手动下单还是select（要校验）
	if($("#auto_input").is(":hidden") || $("#autosend_input").is(":hidden")){
		$(".select2-selection__rendered").each(function(){
			$(this).parent("span").removeClass("red_line") 
			if($(this).html()==""){
				$(this).parent("span").addClass("red_line")
				flag=false
			}
		})
	}

	//付款方式不为空
	if($('input[type="radio"]').is(':checked')) {
		$(".label_radio").removeClass("red_line")
	}else{
		$(".label_radio").addClass("red_line")
		flag=false
	}
	
	//重量校验
	$("#weight").removeClass("red_line")
	if($("#weight").val()!=""){
		if(!/^-?\d+\.?\d{0,3}$/.test($("#weight").val()) ||  $("#weight").val()<="0"){
			$("#weight").addClass("red_line")
			flag=false
		}
	}
	
	
	//手机号
	$("#sendMobile").removeClass("red_line")
	if($("#sendMobile").val()!=""){
		if(! /^(\d{11},)*\d{11},?$/.test($("#sendMobile").val())){
			$("#sendMobile").addClass("red_line")
			flag=false
		}
	}
	$("#receiverMobile").removeClass("red_line")
	if($("#receiverMobile").val()!=""){
		if(! /^(\d{11},)*\d{11},?$/.test($("#receiverMobile").val())){
			$("#receiverMobile").addClass("red_line")
			flag=false
		}
	}
	
	
	//手动第三方付--填写月结卡号
	$("#custId").removeClass("red_line")
	if($("input[name='payMethod']:checked").val()=="3"){
		if($("#custId").val()==""){
			$("#custId").addClass("red_line")
			flag=false
		}else{
			$("#custId").removeClass("red_line")
		}
	}
	
	if(flag){
		var json= "";
		var cargo={}
		var companyId=$("#companyId").val()
		cargo.count=$("#count").val();
		cargo.name=$("#name").val();
		cargo.weight=$("#weight").val();
		var addedServices=[]
		for(var i=0;i<$("#addservice>div").size();i++){
			addedServices[i]={}	
			addedServices[i].appearValue=jsonstr[i].value
			addedServices[i].name=jsonstr[i].code
			addedServices[i].value=jsonstr[i].value
		}
		//扫码下单提交
		if($('#tiaoma').attr("readonly")=="readonly"){
			json = JSON.stringify({
				sendCompany:$("#sendCompany").val(),
				sendContact:$("#sendContact").val(),
				sendTel:$("#sendTel").val(),
				sendProvince:$("#sendProvince_input").val(),
				sendCity:$("#sendCity_input").val(),
				sendCounty:$("#sendCounty_input").val(),
				sendAddress:$("#sendAddress").val(),
				sendMobile:$("#sendMobile").val(),
				
				receiverCompany:$("#receiverCompany").val(),
				receiverContact:$("#receiverContact").val(),
				receiverTel:$("#receiverTel").val(),
				receiverProvince:$("#receiverProvince_input").val(),
				receiverCity:$("#receiverCity_input").val(),
				receiverCounty:$("#receiverCounty_input").val(),
				receiverAddress:$("#receiverAddress").val(),
				receiverMobile:$("#receiverMobile").val(),
				
				custId:$("#custId").val(),
				payMethod:$("input[name='payMethod']:checked").val(),
				parcelQuantity:$("#parcelQuantity").val(),
				expressType:$("#expressType").val(),
				orderId:$("#orderId").val(),
				cargo:cargo,
				addedServices:addedServices,
			})
		}else{
			//手动下单提交
			json = JSON.stringify({
				sendCompany:$("#sendCompany").val(),
				sendContact:$("#sendContact").val(),
				sendTel:$("#sendTel").val(),
				sendProvince:$("#sendProvince").val(),
				sendCity:$("#sendCity").val(),
				sendCounty:$("#sendCounty").val(),
				sendAddress:$("#sendAddress").val(),
				sendMobile:$("#sendMobile").val(),
				
				receiverCompany:$("#receiverCompany").val(),
				receiverContact:$("#receiverContact").val(),
				receiverTel:$("#receiverTel").val(),
				receiverProvince:$("#receiverProvince").val(),
				receiverCity:$("#receiverCity").val(),
				receiverCounty:$("#receiverCounty").val(),
				receiverAddress:$("#receiverAddress").val(),
				receiverMobile:$("#receiverMobile").val(),
				
				custId:$("#custId").val(),
				payMethod:$("input[name='payMethod']:checked").val(),
				parcelQuantity:$("#parcelQuantity").val(),
				expressType:$("#expressType").val(),
				orderId:$("#orderId").val(),
				cargo:cargo,
				addedServices:addedServices,
			})
		}
		$("#tijiao").attr("disabled",true)//再次点击提交无效
	   	$.ajax({
	   		type:"POST",
	   		url:"/ebes/sfExpress/order?companyId="+companyId,
	   		contentType: 'application/json', 
	   		dataType:"json",
	   		data:json,
	   		success:function(data){
	   			if(data.result==0){//下单成功
	   				var mailno=data.mailno;
	   				var resultAppear=data.resultAppear;
	   				var parcelQuantity=data.parcelQuantity;
	   				btn_reset()//提交后重置
	   				//是否打印
	   				if(resultAppear==0){
	   					swal({
	   						title: "您要打印此快递单吗？", 
	   						text: "信息已提交成功", 
	   						type: "success",
	   						showCancelButton: true,
	   						closeOnConfirm: false,
	   						confirmButtonText: "是的，我要打印",
	   						confirmButtonColor: "#85c875"
   							}, function() {
   								var height=Number(parcelQuantity)*1050+'px'
   								console.log(height)
   								var pring_content="<iframe id='iframe' src='../print/print.html?id="+mailno+"' style='width:500px;height:"+height+";' frameborder='no'></iframe>"          
   								$("#ss").html(pring_content)
   								swal.close() 
   								setTimeout(function () { 
   								    $("#ss").jqprint({});
   								}, 500);
   							});
   					
	   				}else{
	   					swal({
	   						title: "信息已提交成功", 
	   						text: "信息已提交成功", 
	   						type: "success",
	   					});
	   				}
	   			}else if(data.result==1){//无运单号无法打印
	   				swal(""+ data.msg+"");
	   				btn_reset()//提交后重置
	   			}else{
	   				$("#tijiao").attr("disabled",false)//修改后可再次点击提交
	   				swal(""+ data.msg+"");
	   			}
	   		}
	   	})
	}else{
		swal("完善红色框中的内容再提交！");
	}
}
	


//重置
function btn_reset(){
	var oInput = document.getElementById("tiaoma");
	oInput.focus();
//	window.location.reload();
	$("#tijiao").show()
	$("input").attr("readonly",false);
	$("input").removeClass("red_line")
	$("input[type='radio']").attr("disabled",false);
	$("select").attr("disabled",false);
	$("span").removeClass("red_line")
	$("p").removeClass("red_line")
	$("#parcelQuantity").attr("readonly",false);
	
	
	$("input").val("")
	$("input[type='radio']").eq(0).val("1");
	$("input[type='radio']").eq(1).val("2");
	$("input[type='radio']").eq(2).val("3");
	
	$("#parcelQuantity").val("1");
	$("#expressType")[0].selectedIndex=0; 
	$(":radio").attr("checked",false)
    $("#companyId")[0].selectedIndex=0; 
	
	$("#receiverProvince").val(null).trigger("change");
	$("#receiverCity").val(null).trigger("change");
	$("#receiverCounty").val(null).trigger("change");
	$("#sendProvince").val(null).trigger("change");
	$("#sendCity").val(null).trigger("change");
	$("#sendCounty").val(null).trigger("change");
	
    $("#name")[0].selectedIndex = -1; 
    $("#tijiao").attr("disabled",false)
	//二维码扫码
	$("#auto_input").hide()
	$("#auto_select").show()
	$("#autosend_select").show()
	$("#autosend_input").hide()
	
	$("#addservice").html("")
	$(":checkbox").attr("checked",false)
	$(".back").css("background","#ffffff");
	
	//右边增值服务为隐藏
	$(".modal_left div").hide()
	
	//扫描增值服务移除需重新添加
	$("#asadsd").show()
	$(".add_service").show()
	jsonstr=[]; 
}


//条码扫描读数据
window.onload = function(){
	var oInput = document.getElementById("tiaoma");
	oInput.focus();
}
document.onkeydown=search;
function search(event) {
	var str=$("#tiaoma").val();
	console.log(str)
	var regex=/^\[.*\]$/;
	var a=regex.test(str);
	if(a==true){
		if(event.keyCode == 13) {
			tiaoma_ok();
	    }
	}
};
function tiaoma_ok(){
	//回车后先重置
	$("span").removeClass("red_line")
	$("p").removeClass("red_line")
	$("input").removeClass("red_line")
	$("#sendMobile").val("")
	$("#receiverMobile").val("")
	$("#weight").val("")
	$("#count").val("")
	
	var text=$("#tiaoma").val()
	$.ajax({
   		type:"GET",
   		url:"/ebes/sfExpress/expressInfo?text="+text,
   		contentType: 'application/json', 
   		success:function(data){
   			if(data.result==0){
		   		var expressInfo=data.expressInfo;
   		   		loadtiaoma(expressInfo)	
   				if(data.msg!=""){
   					swal(""+ data.msg+"");
   					$("#tijiao").hide()
   				}

   			}else{
   				swal(""+ data.msg+"");
   			}
   		}
   	})
}
function loadtiaoma(expressInfo){
	$(".add_service").hide()
	$("#asadsd").hide()
	$("#addservice").html("")
	$("input").attr("readonly","readonly");
	$("input[type='radio']").attr("disabled",true);
	$("select").attr("disabled","disabled");
	$("#parcelQuantity").attr("readonly",false);

	$("#expressType").val("1")
	$("#orderId").val(""+expressInfo.orderId+"")
	
	
	if(""+expressInfo.cargoName+""==""){
		$("#name").val("文件")
		$("#name").attr("readonly",false);
	}else{
		$("#name").val(""+expressInfo.cargoName+"")
	}
	
	$("#sendCompany").val(""+expressInfo.senderInfo.company+"")
	$("#sendContact").val(""+expressInfo.senderInfo.contact+"")
	$("#sendMobile").val(""+expressInfo.senderInfo.moblie+"")
	if(""+expressInfo.senderInfo.tel+""==""){
		$("#sendTel").val(""+expressInfo.senderInfo.moblie+"")
	}else{
		$("#sendTel").val(""+expressInfo.senderInfo.tel+"")
	}
	
	$("#receiverCompany").val(""+expressInfo.receiveInfo.company+"")
	$("#receiverContact").val(""+expressInfo.receiveInfo.contact+"")
	$("#receiverMobile").val(""+expressInfo.receiveInfo.moblie+"")
	if(""+expressInfo.receiveInfo.tel+""==""){
		$("#receiverTel").val(""+expressInfo.receiveInfo.moblie+"")
	}else{
		$("#receiverTel").val(""+expressInfo.receiveInfo.tel+"")
	}
	
	$("#autosend_select").hide()
	$("#autosend_input").show()
	$("#sendProvince_input").val(""+expressInfo.senderInfo.province+"")
	$("#sendCity_input").val(""+expressInfo.senderInfo.city+"")
	$("#sendCounty_input").val(""+expressInfo.senderInfo.county+"")
	$("#sendAddress").val(""+expressInfo.senderInfo.address+"")
	

	
	$("#auto_input").show()
	$("#auto_select").hide()
	$("#receiverProvince_input").val(""+expressInfo.receiveInfo.province+"")
	$("#receiverCity_input").val(""+expressInfo.receiveInfo.city+"")
	$("#receiverCounty_input").val(""+expressInfo.receiveInfo.county+"")
	$("#receiverAddress").val(""+expressInfo.receiveInfo.address+"")
	

	//付款方式，月结卡号
	var sendaddress=$("#sendAddress").val()
	if(sendaddress.indexOf("华东大厦")!=-1){
		$("#custId").val("5101203392")
	    $("input[type='radio'][name='payMethod'][value='1']").attr("checked",true);  
	}else if(sendaddress.indexOf("石柏南大街181号鹿岛V谷科技工业园32号楼3层")!=-1){
		$("#custId").val("3113128913")
	    $("input[type='radio'][name='payMethod'][value='1']").attr("checked",true);  
	}
	else{ 
		$("#custId").val("5101203392")
		$("input[type='radio'][name='payMethod'][value='3']").attr("checked",true); 
	}

}


////付款方式选择
//function check_enable(obj){
//	if($(obj).prop("checked")){
//		var value=$(obj).attr("value")
//		if(value=="1"){
//			$("#custId").val("5101203392")
//			$("#custId").attr("readonly",false)
//		}else{
//			$("#custId").val("")
//			$("#custId").attr("readonly",true)
//		}
//	}
//}