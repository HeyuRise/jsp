/**
 * Created by LiLU on 2017/8/29.
 */
$(function () {
	LoadingDataListOrderRealItems();
	//$('#list').bootstrapTable('hideColumn', 'caozuo');
    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function (){
    	var productName = $('#productName').val();
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product',
            query: {productName: productName,}
        });
    });

    //重置
    $("#btn_reset").click(function (){
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product',
        });
    });
})


function LoadingDataListOrderRealItems(){
    $("#list").bootstrapTable({
        toolbar: '#toolbar',
        url:'/ebes/sysSetting/product',          //请求后台的URL（*）
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
				return "<span >" + (index+1) +" </span>";
			}
        },{
            field:"productName",
            align:"center",
            title:"业务类型"
        },{
            field:"expressCompany",
            align:"center",
            title:"快递公司"
        },{
            field:"productCode",
            align:"center",
            title:"产品编号"
        },{
            field:"enable",
            align:"center",
            title:"启用/禁用",
            formatter: function(value,row,index){
            	if(row.click==0){
            		return "<a id='"+row.productId+"' onclick='enable(this)'>"+row.enable+"</a>";
            	}else{
            		return "<span>"+row.enable+"</span>";
            	}
			} 	
        }
//        ,{
//            field:"caozuo",
//            align:"center",
//            title:"操作",
//            formatter: function(value,row,index){
//				return "<a id='"+row.productCode+"' name='"+(index+1)+"' onclick='edittype(this)'>编辑</a>&nbsp;&nbsp;&nbsp;<a id='"+row.productCode+"' name='"+(index+1)+"' onclick='deletetype(this)'>删除</a>";
//			}
//        }
        ]

    })
}

//禁用和启用
function enable(obj){
	var id=$(obj).attr("id");
	$.ajax({
   		type:"PATCH",
   		url:"/ebes/sysSetting/product?productId="+id,
   		contentType: 'application/json', 
   		success:function(data){
   			if(data.result==0){
   				$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product'});   				
   			}else{
   				swal(""+ data.msg+"");
   			}
   		}
   	})
}


//新增，编辑，删除是否显示
$(function(){
	var index=$("#add_btn").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$("#add_btn").show()
				//$('#list').bootstrapTable('showColumn', 'caozuo');
			}else{
				//$('#list').bootstrapTable('hideColumn', 'caozuo');
				$("#add_btn").hide()
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







//新增--初始化
function add(){
	$("#productName_add").val("")
	$("#productCode_add").val("")
	$("#enable_add").val("启用")
	$("#companyId_add")[0].selectedIndex=-1; 
	$(".error").html("");
	$("#myModal").modal("show");
	//获取快递公司
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes/util/enableExpressCompany",
		success:function(data){
			var size = data.size;
			var items = data.items;
			load(items,size)
		}
	})
}
function load(items,size){
	$("#companyId_add").html("")
	$("#companyId_edit").html("")
	for(var i=0;i<size;i++){
		var itemshtml='<option value="'+items[i].id+'">'+items[i].name+'</option>'
		$("#companyId_add").append(itemshtml)
		
		var edithtml='<option value="'+items[i].id+'">'+items[i].name+'</option>'
		$("#companyId_edit").append(edithtml)
	}
	$("#companyId_add")[0].selectedIndex=-1; 
}



$(function(){
	$().ready(function() {
	// 在键盘按下并释放及提交后验证提交表单
		 $("#defaultForm").validate({
		        rules: {
		        	productName_add: {
		        		required: true,
		        	},
		        	productCode_add:  {
		                required: true,
		            },
		            companyId_add: {
		                required: true,
		            }
		        },
		        messages: {
		        	productName_add: {
		        		required: "请输入业务类型",
		 
		        	},
		        	productCode_add: {
		                required: "请输入产品编号",
		             
		            },
		            companyId_add: {
		                required: "请选择快递公司",
		            }
		        },
		        onfocusout: function( element, event ) {
                	//console.log($(element).attr("name"));
        			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
        				this.element(element);
        			}
        		},
			    submitHandler: function() {
		       		var productName=$("#productName_add").val();
		       		var productCode=$("#productCode_add").val();
		       		var enable=$("#enable_add").val();
		       		var companyId=$("#companyId_add").val();
			       	$.ajax({
			       		type:"POST",
			       		url:"/ebes/sysSetting/product?companyId="+companyId+"&productCode="+productCode+"&productName="+productName+"&enable="+enable,
			       		contentType: 'application/json', 
			       		success:function(data){
			       			if(data.result==0){
			       				$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product'});
			       				swal("提交成功!");
			       				$("#myModal").modal("hide");
			       				
			       			}else{
			       				swal(""+ data.msg+"");
			       			}
			       		}
			       	})
			    }
		    });
		});			
//	$.validator.setDefaults({
//
//	});

})


//删除
//function deletetype(obj){
//	var productCode = $(obj).attr("id");
//	var index = $(obj).attr("name");
//	var companyName  = document.getElementById("list").rows[index].cells[2].innerText; 
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
//				url: "/ebes/sysSetting/product?companyName="+companyName+"&productCode="+productCode,
//				type: "DELETE"
//			}).done(function(data) {
//				if(data.result==0){
//					swal("操作成功!", "已成功删除数据！", "success");
//					$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product'});
//				}else{
//					swal("OMG", ""+ data.msg+"", "error");
//				}
//			})
//		});
//}




//编辑
//function edittype(obj){
//	var productCode = $(obj).attr("id");
//	$("#productCode_edit").val(productCode)
//	
//	var index = $(obj).attr("name");
//	var companyName  = document.getElementById("list").rows[index].cells[2].innerText; 
//	$("#companyId_edit option").filter("[text='"+companyName+"']").attr("selected", true);
//	
//	var productName=document.getElementById("list").rows[index].cells[1].innerText; 
//	$("#productName_edit").val(productName)
//	var enable=document.getElementById("list").rows[index].cells[4].innerText; 
//	$("#enable_edit").val(enable)
//	$(".error").html("");
//	$(".error").css("color","##555");
//	$("#myModal1").modal("show");
//}
//$(function(){
//	$().ready(function() {
//	// 在键盘按下并释放及提交后验证提交表单
//	    $("#editForm").validate({
//	        rules: {
//	        	productName_edit: {
//	        		required: true,
//	        	},
//	        	productCode_edit:  {
//	                required: true,
//	            }
//	        },
//	        messages: {
//	        	productName_edit: {
//	        		required: "请输入业务类型",
//	 
//	        	},
//	        	productCode_edit: {
//	                required: "请输入产品编号",
//	             
//	            }
//	        },
//		    submitHandler: function() {
//	       		var productName=$("#productName_edit").val();
//	       		var productCode=$("#productCode_edit").val();
//	       		var enable=$("#enable_edit").val();
//	       		var companyId=$("#companyId_edit").val();
//		       	$.ajax({
//		       		type:"PATCH",
//		       		url:"/ebes/sysSetting/product?companyId="+companyId+"&productCode="+productCode+"&productName="+productName+"&enable="+enable,
//		       		contentType: 'application/json', 
//		       		success:function(data){
//		       			if(data.result==0){
//		       				$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/product'});
//		       				swal("提交成功!");
//		       				$("#myModal1").modal("hide");
//		       				
//		       			}else{
//		       				swal(""+ data.msg+"");
//		       			}
//		       		}
//		       	})
//		    }
//	    });
//	});
//})
