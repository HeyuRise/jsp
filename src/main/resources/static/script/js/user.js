/**
 * Created by LiLU on 2017/8/30.
 */

$(function () {
	LoadingDataListOrderRealItems();
	$('#list').bootstrapTable('hideColumn', 'account');
	//$('#list').bootstrapTable('hideColumn', 'caozuo');
	$('#list').bootstrapTable('hideColumn', 'chongzhi');
    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function (){
    	var name = $('#name').val();
    	var account = $('#account').val();
    	var department = $('#department').val();
    	var roleName = $('#roleName').val();
    	var enable = $('#enable').val();
       $("#list").bootstrapTable('refresh',{url:'/ebes/user/user',
            query: {name: name,account:account, department: department,roleName: roleName,enable: enable,}
        });
    });

    //重置
    $("#btn_reset").click(function (){
    	$('#name').val("")
    	$('#account').val("")
    	$('#department').val("")
    	$('#enable').val("")
    	$("#roleName")[0].selectedIndex = -1; 
    	$("#enable")[0].selectedIndex = -1; 
       $("#list").bootstrapTable('refresh',{url:'/ebes/user/user',
        });
    });
    
    $("#enable")[0].selectedIndex = -1; //启用禁用默认为空
    
});


function LoadingDataListOrderRealItems(){
	$("#list").bootstrapTable({
	   toolbar: '#toolbar',
	   url:'/ebes/user/user',          //请求后台的URL（*）
	   striped: true,                      //是否显示行间隔色
	   cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	   pagination: true,                   //是否显示分页（*）
	   pageNumber:1,                       //初始化加载第一页，默认第一页
	   pageSize: 10,                       //每页的记录行数（*）
	   pageList: [5, 10, 25, 100],        //可供选择的每页的行数（*）
	   columns:[{
	       field:"id",
	       align:"center",
	       title:"序号",
	       formatter: function(value,row,index){
	    	   return "<span id='list"+(index+1)+"'>" + (index+1) +" </span>";
		  }
	    },{
	       field:"account",
	       align:"center",
	       title:"用户名"
	    },{
	       field:"userName",
	       align:"center",
	       title:"姓名"
	    },{
	       field:"department",
	       align:"center",
	       title:"部门"
	    },{
	      field:"role",
	      align:"center",
	      title:"已赋角色",
	      formatter: function(value,row,index){
	    	  return "<span'>"+row.role+"</span>";
	       }
	    },{
	      field:"enable",
	      align:"center",
	      title:"启用/禁用",
	      formatter: function(value,row,index){
	    	  if(row.click==0){
	    		  return "<a   id='"+row.account+"' onclick='enable(this)'>"+row.enable+"</a>";
	    	  }else{
	    		  return "<span>"+row.enable+"</span>";
	    	  }
	    	 
	     }
	    },{
	      field:"account",
	      align:"center",
	      title:"角色配置",
	      formatter: function(value,row,index){
			  return "<a id='"+row.account+"' index='"+row.role+"' name='"+(index+1)+"' onclick='updaterole(this)'data-toggle='modal'>角色配置</a>";
	      	}
	     },
//	     {
//	       field:"caozuo",
//	       align:"center",
//	       title:"操作",
//	       formatter: function(value,row,index){
//	    	   return "<a  id='"+row.account+"' name='"+(index+1)+"' onclick='deleterole(this)'>删除</a>";
//	       }
//	    },
	    {
	      field:"chongzhi",
		  align:"center",
		  title:"重置密码",
		  formatter: function(value,row,index){
			return "<a  id='"+row.account+"' name='"+(index+1)+"' onclick='resetpassword(this)'>重置密码</a>";
		  }
	    }
	  ]
	})
	
}


//角色配置是否显示
$(function(){
	var index=$("#peizhi").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$('#list').bootstrapTable('showColumn', 'account');
			}else{
				$('#list').bootstrapTable('hideColumn', 'account');
			}
		}
	})
})

//操作是否显示
//$(function(){
//	var index=$("#caozuo").attr("index")
//	$.ajax({
//		type:"get",
//		dataType:"json",
//		url:"/ebes/auth/button?buttonId="+index,
//		data:{},
//		success:function(data){
//			if(data.result==0){
//				$('#list').bootstrapTable('showColumn', 'caozuo');
//			}else{
//				$('#list').bootstrapTable('hideColumn', 'caozuo');
//			}
//		}
//	})
//})


//重置密码是否显示
$(function(){
	var index=$("#chongzhi").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$('#list').bootstrapTable('showColumn', 'chongzhi');
			}else{
				$('#list').bootstrapTable('hideColumn', 'chongzhi');
			}
		}
	})
})


//回车键查询
function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};

//新增按钮是否显示
$(function(){
	var index=$("#add_btn").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$("#add_btn").show();
			}else{
				$("#add_btn").hide();
			}
		}
	})
})


//禁止启用切换
function enable(obj) {
	var enableId=$(obj).attr("id")
	$.ajax({
		type:"PATCH",
		dataType:"json",
		url:"/ebes/user/userEnable?account="+enableId,
		data:{},
		success:function(data){
		if(data.result==0){
			$("#list").bootstrapTable('refresh',{url:'/ebes/user/user'});
		}else{
			swal(""+ data.msg+"");
		}
	}
	})
}

//重置密码
function resetpassword(obj){
	var account=$(obj).attr("id")
	swal({
		title: "您确定要重置密码吗？", 
		text: "您确定要重置这条数据？", 
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false,
		confirmButtonText: "是的，我要重置",
		confirmButtonColor: "#ec6c62"
		}, function() {
			$.ajax({
				type:"PATCH",
				dataType:"json",
				url:"/ebes/user/password?account="+account,
			}).done(function(data) {
				if(data.result==0){
					$("#list").bootstrapTable('refresh',{url:'/ebes/user/user'});
					swal({
						title: ""+ data.msg+"", 
						type: "success",
					});
				}else{
					swal(""+ data.msg+"", "", "error");
				}
			})
		});
}



$(function(){	
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/userRoleMap",
		success:function(data){
			var size = data.size;
			var roles = data.roles;
			load(roles,size)
		}
	})
})




//新增用户--初始化
function add(){
	$("#account_add").val("")
	$("#username_add").val("")
	$("#departmentCode").val("")
	$("#enable_add").val("启用")
	$("#roleIds")[0].selectedIndex = -1; 
	$(".error").html("")
	$("#myModal").modal("show");
	//获取角色
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/userRoleMap",
		success:function(data){
			var size = data.size;
			var roles = data.roles;
			load(roles,size)
		}
	})
}
function load(roles,size){
	$("#role_content").html("")
	$("#roleName").html("")
	$("#roleIds").html("")
	for(var i=0;i<size;i++){
		var html='<div style="margin-top:15px;">'+
				  	'<div class="radio radio-success">'+
				  	 	'<input type="radio"  name="roleName"  id="'+roles[i].id+'" value="'+roles[i].roleName+'">'+
				  	 	'<label for="'+roles[i].id+'">'+roles[i].roleName+'</label>'+
					'</div>'+
				'</div>'
		$("#role_content").append(html)
		
		var rolehtml='<option value="'+roles[i].id+'">'+roles[i].roleName+'</option>'
		$("#roleIds").append(rolehtml)
		$("#roleIds")[0].selectedIndex = -1; 
		
		var rolesearch='<option >'+roles[i].roleName+'</option>'
		$("#roleName").append(rolesearch)
		$("#roleName")[0].selectedIndex = -1; 
	}
	$('input[type="radio"]').removeAttr('checked'); 
}
//角色配置
function updaterole(obj){
	$('input[type="radio"]').removeAttr('checked'); 
	var Id = $(obj).attr("id");
	$("#account_role").val(Id)
	var index = $(obj).attr("name");
	//var role_name =  document.getElementById("list").rows[index].cells[4].innerText; 
	var role_name=$(obj).attr("index");
	$("input[type='radio'][value='"+role_name+"']").prop("checked",true)
	$("#myModal1").modal("show");
}
//角色配置--提交
function roleOK(){
	var account=$("#account_role").val()
	var roleId=$("input[type='radio']:checked").attr("id");
	$.ajax({
		type:"PATCH",
		dataType:"json",
		url:"/ebes/user/userRole?account="+account+"&roleId="+roleId,
		success:function(data){
			if(data.result==0){
				$("#list").bootstrapTable('refresh',{url:'/ebes/user/user'});
				swal("提交成功!");
				$("#myModal1").modal("hide");
				
			}else{
				swal(""+ data.msg+"");
			}
		}
	})
}





//删除
//function deleterole(obj){
//	var account = $(obj).attr("id");
//	var index = $(obj).attr("name");
//	swal({
//		title: "您确定要删除吗？", 
//		text: "您确定要删除这条数据？", 
//		type: "warning",
//		showCancelButton: true,
//		closeOnConfirm: false,
//		confirmButtonText: "是的，我要删除",
//		confirmButtonColor: "#ec6c62"
//		}, function() {
//			$.ajax({
//				url: "/ebes/user/user?account="+account,
//				type: "DELETE"
//			}).done(function(data) {
//				if(data.result==0){
//					swal("操作成功!", "已成功删除数据！", "success");
//					$("#list").bootstrapTable('refresh',{url:'/ebes/user/user'});
//				}else{
//					swal("OMG", ""+ data.msg+"", "error");
//				}
//			})
//		});
//}



$(function(){
	 $.validator.setDefaults({
            submitHandler: function() {
	           	var jsonstr=""
	           	var roleIds = [$("#roleIds").val()];
	           	jsonstr = JSON.stringify({
	           		account:$("#account_add").val(),
	           		username:$("#username_add").val(),
	           		departmentCode:$("#departmentCode").val(),
	           		enable:$("#enable_add").val(),
	           		roleIds:roleIds,
	           	 })
	           	 
	           	$.ajax({
	           		type:"POST",
	           		dataType:"json",
	           		url:"/ebes/user/user",
	           		data:jsonstr,
	           		contentType: 'application/json', 
	           		success:function(data){
	           			if(data.result==0){
	           				$("#list").bootstrapTable('refresh',{url:'/ebes/user/user'});
	           				swal("提交成功!");
	           				$("#myModal").modal("hide");
	           				
	           			}else{
	           				swal(""+ data.msg+"");
	           			}
	           		}
	           	})
            }
        });
        $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
            $("#defaultForm").validate({
                rules: {
                	account_add: {
                		required: true,
                		checkname:true
                	},
                	username_add:  {
                        required: true,
                        check_user:true,
                    },
                    departmentCode: {
                        required: true,
                        check_department:true,
                    },
                    roleIds: {
                        required: true,
                    }
                },
                messages: {
                	account_add: {
                		required: "请输入您的用户名",
                	},
                	username_add: {
                        required: "请输入姓名",
                    },
                	departmentCode: {
                        required: "请输入部门",
                    },
                    roleIds: {
                        required: "请输入角色",
                    }
                },
                onfocusout: function( element, event ) {
                	console.log($(element).attr("name"));
        			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
        				this.element(element);
        			}
        		}
            });
            
            $.validator.addMethod("checkname",function(value,element,params){  
                var checkname = /^(?![^a-zA-Z]+$)(?!\D+$)(?![^_]+$).{1,1600}$/;  
                return this.optional(element)||(checkname.test(value));  
            },"用户名由小写字母，下划线，数字组成！");  
            $.validator.addMethod("check_user",function(value,element,params){  
                var check_user =  /^[\u4e00-\u9fa5]{2,5}$/;;  
                return this.optional(element)||(check_user.test(value));  
            },"请输入2至5个汉字！");  
            $.validator.addMethod("check_department",function(value,element,params){  
                var check_department =  /^[\u4e00-\u9fa5]{1,20}$/;;  
                return this.optional(element)||(check_department.test(value));  
            },"请输入1至20个汉字！");  
            
        });
})

