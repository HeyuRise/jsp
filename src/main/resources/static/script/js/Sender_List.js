/**
 * Created by LiLU on 2017/8/29.
 */
$(function(){
    $("#list").bootstrapTable({
        toolbar: '#toolbar',
        url:'/ebes/contact/sender',          //请求后台的URL（*）
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        striped: true,                      //是否显示行间隔色
        pagination: true,                   //是否显示分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [5, 10, 25, 100],        //可供选择的每页的行数（*）
        columns:[{
            field:"id",
            align:"center",
            title:"序号",
            formatter: function (value, row, index) {
                return index+1;
            }
        },{
            field:"contact",
            align:"center",
            title:"联系人"
        },{
            field:"telephone",
            align:"center",
            title:"联系电话"
        },{
            field:"mobile",
            align:"center",
            title:"手机"
        }
        ]

    })


})

//导出按钮是否显示
$(function(){
	var index=$("#export").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$("#export").show();
			}else{
				$("#export").hide();
			}
		}
	})
})


$(function(){

    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function(){
    	var contact = $('#contact').val();
    	$("#list").bootstrapTable('refresh',{url:'/ebes/contact/sender',
            query: {contact: contact,}
        });
    })
    //重置
    $("#btn_reset").click(function (){
    	$("#list").bootstrapTable('refresh',{url:'/ebes/contact/sender',
        });
    });
})

function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};

//导出
function exportxls(){  
	var contact = $('#contact').val();
    var form = $("<form>");  
	       form.attr('style', 'display:none');  
	       form.attr('target', '');  
	       form.attr('method', 'get');  
	       form.attr('action', '/ebes/contact/senderExport');  
	       var input1 = $('<input>');  
	       input1.attr('type', 'hidden');  
	       input1.attr('name', 'contact');  
	       input1.attr('value', contact);   
	       $('body').append(form);  
	       form.append(input1);  
	       form.submit();  
	       form.remove();  
} 

