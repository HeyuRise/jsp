/**
 * Created by LiLU on 2017/8/29.
 */
$(function(){
    $("#list").bootstrapTable({
        url:'/ebes/sysSetting/service',          //请求后台的URL（*）
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
            formatter: function (value, row, index) {
                return index+1;
            }
        },{
            field:"serviceName",
            align:"center",
            title:"增值服务名称"
        },{
            field:"enable",
            align:"center",
            title:"启用/禁用",
            formatter: function(value,row,index){
            	if(row.click==0){
            		return "<a  id='"+row.serviceName+"' onclick='enable(this)'>"+row.enable+"</a>";
            	}else{
            		return "<span>"+row.enable+"</span>";
            	}
            	
			}
        }
        ]

    })


})

//禁止启用切换
function enable(obj) {
	var serviceName = $(obj).attr("id");
	$.ajax({
		type:"PATCH",
		dataType:"json",
		url:"/ebes/sysSetting/serviceEnable?serviceName="+serviceName,
		data:{},
		success:function(data){
				if(data.result==0){
					$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/service'});
				}else{
					swal(""+ data.msg+"");
				}
		}
	})
}



$(function(){
    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function(){
    	var serviceName = $('#serviceName').val();
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/service',
            query: {serviceName: serviceName,}
        });
    })
  //重置
    $("#btn_reset").click(function (){
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/service',
        });
    });

})

function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};