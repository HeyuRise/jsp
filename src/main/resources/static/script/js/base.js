
$(function(){
	$.ajax({  
	    type: "get",  
	    url: "/jsp/auth/userDetail",  
	    contentType: 'application/json', 
	    success: function (data) {  
			var menu = data.data.menu;
			var account = data.data.account;
			loadmenu(menu,account);
		}
	})  
	$("#footer").html('<span class="no-margin" style="text-align: center;display: block">Copyright@1999-2017无锡市同步电子有限公司</span>')
})
//加载菜单
function loadmenu(menu,account){
	var html="";
	for(var x in menu){
		html+='<li class="openable bg-palette2">';
		html+=	'<a onclick="sas(this)">';
		html+=		'<span class="menu-content block">';
		html+=			'<span class="menu-icon"><i class="block iconfont">'+menu[x].param+'</i></span>';
		html+=			'<span class="text m-left-sm">'+menu[x].tile+'</span>';
		html+=  		'<span class="submenu-icon"></span>';
		html+=  	'</span>'
		html+=      '<span class="menu-content-hover block">'+menu[x].tile+' </span>';
		html+=  '</a>';
		html+=  '<ul class="submenu bg-palette2">';
		var imgs = menu[x].menuItems;
		for(var y in imgs){
			html+='<li>';
			html+=	'<a  href="'+imgs[y].url+'" dst_url="'+imgs[y].url+'" onclick="var url = $(this).attr("dst_url");window.location = url;"><span class="submenu-label">'+imgs[y].tile+'</span></a>';
			html+='</li>';
		}
		html+='</ul>';
		html+='</li>';
	}
	$(".menu-header").after(html)
	
	var headhtml='<div class="top-nav-inner">'+
	 				'<div class="nav-header">'+
						'<span style="line-height: 52px;font-size: 15px;"><i class="iconfont" style="font-size: 2rem;">&#xe657;</i>&nbsp;&nbsp;&nbsp;面单电子化管理系统</span>'+
					'</div>'+
					'<div class="nav-container">'+
						
						'<div class="pull-right m-right-sm">'+
							'<span style="float: left;padding: 16px 0;margin-right: 20px;">说明：若有问题，请联系连金平</span>'+	
							'<div class="user-block hidden-xs">'+
							 	'<a href="#" id="userToggle" data-toggle="dropdown">'+
					               ' <div class="user-detail inline-block">'+
					                	'<span id="name_acccount">'+account+'</span>'+
					                	'<i class="fa fa-angle-down"></i>'+
					                '</div>'+
					           ' </a> '+
					           ' <div class="panel border dropdown-menu user-panel">'+
					                '<form  class="panel-body paddingTB-sm" action="" >'+
						               ' <ul>'+
							                ' <li> <a href="/jsp/logout">'+
								              '  <button type="button" style="outline: none;border: 0;border: 0;background: white;">'+
									             '  <i class="fa fa-power-off fa-lg">'+
									              '  </i><span class="m-left-xs">退出</span>'+
								                '</button>'+
								               ' </a>'+
							               ' </li>'+
							                '<li> <a href=""  data-toggle="modal" data-target="#myModal3">'+
								               	'<button type="button" style="outline: none;border: 0;border: 0;background: white;">'+
									             '  <i class="iconfont">&#xe7d3;'+
									              '  </i><span class="m-left-xs">修改密码</span>'+
								               '</button>'+
								               ' </a>'+
							               ' </li>'+
					                    ' </ul>'+
					                 '</form>'+
					           ' </div>'+
					         '</div>'+
					    '  </div>'+
					 ' </div>'+
					'</div>'
	$("#header").html(headhtml)
	$("#name-acccount").html($("#name_acccount").html())
	loction()
}

//初始化侧边栏本页面展开
function loction(){
	var nav = document.getElementById("navi");
	var links = nav.getElementsByClassName("openable bg-palette2");
	var lilen = nav.getElementsByTagName("a");
	var url=window.location.href;
	var loc = url.substring(url.lastIndexOf('/')+1, url.length);
	var loct = url.substring(url.lastIndexOf('?')+1, url.length);
	if(loc=="create_list.html"){
		links[0].className = "open"
	}else if(loc=="Express_List.html" || loct.indexOf('orderId')!=-1){
		links[1].className = "open"
			
	}else if(loc=="Sender_List.html" || loc=="Sender_Address_List.html" || loc=="Recipients_List.html"){
		links[2].className = "open"
	}else if(loc=="Map_List.html" || loc=="Business_Type.html" || loc=="Company_List.html" || loc=="Parcel_Content.html"){
		links[3].className = "open"
	}else if(loc=="role.html" || loc=="user.html"){
		links[4].className = "open"
	}else{
		
	}
}



$(function	()	{
	$('.scrollable-sidebar').slimScroll({
		height: '100%',
		size: '0px'
	});
	if(!$('.sidebar-menu').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 767px)'))	{
		$('.openable.open').children('.submenu').slideDown(200);
	}
})

//侧边栏点击
function sas(obj){
	if(!$('aside').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 991px)'))	{
		if( $(obj).parent().children('.submenu').is(':hidden') ) {
			$(obj).parent().siblings().removeClass('open').children('.submenu').slideUp(200);
			$(obj).parent().addClass('open').children('.submenu').slideDown(200);
		}
		else{
			$(obj).parent().removeClass('open').children('.submenu').slideUp(200);
		}
	}
	return false;
}

//修改密码--校验
$(function(){
	$( '#myModal3' ).on( 'hidden.bs.modal' ,function(e){
		$("#oldpass").removeClass("error");
		$("#oldpass").val("");
		$("#newpass").removeClass("error");
		$("#newpass").val("");
		$("#newpass2").removeClass("error");
		$("#newpass2").val("");
		$(".error").html("")
	});
	
	// 在键盘按下并释放及提交后验证提交表单
    $("#Form").validate({
    	rules: {
      	   oldpass: {
      		   required: true,
           },
           newpass:  {
               required: true,
           },
           newpass2:  {
	           required: true,
	           equalTo:"#newpass",
	        }
        },
        messages: {
        	oldpass: {
    		required: "请输入您的旧密码",
        	},
    	newpass: {
            required: "请输入您的新密码",
    		},
    	newpass2:  {
    		required: "请再次输入新密码",
    		equalTo:"两次输入密码不同"
        	}
        },
        onfocusout: function( element, event ) {
        	console.log($(element).attr("name"));
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
				this.element(element);
			}
		},
	    submitHandler: function() {
     	   var account=$("#name-acccount").html();
     	   var oldPassword =$("#oldpass").val();
     	   var newPassword =$("#newpass").val();
           $.ajax({
        	   type:"PATCH",
        	   dataType:"json",
        	   url:"/ebes/auth/password?account="+account+"&oldPassword="+oldPassword+"&newPassword="+newPassword,
        	   data:{},
        	   contentType: 'application/json', 
        	   success:function(data){
        		   if(data.result==0){
        			   swal({
        					title: "提交成功!", 
        					showConfirmButton:true,
        					}, function() {
        	        			$("#myModal3").modal("hide");
        	        			window.location.href="/jsp/logout"
        						
        					})
        		   }else{
        			   swal(""+ data.msg+"");
        		   }
        	   }
           })
	    }
    });
})


$(window).load(function() {
	$('body').removeClass('overflow-hidden');
	//Enable animation
	$('.wrapper').removeClass('preload');
	//Chat Notification on top navigation
	setTimeout(function() {
		$('.chat-notification').find('.badge').addClass('active');
		$('.chat-alert').addClass('active');
	}, 3000);
	setTimeout(function() {
		$('.chat-alert').removeClass('active');
	}, 8000);
});