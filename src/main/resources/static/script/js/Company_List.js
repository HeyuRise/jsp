/**
 * Created by LiLU on 2017/8/29.
 */

$(function () {
	LoadingDataListOrderRealItems();
		$('#list').bootstrapTable('hideColumn', 'companyId');
}); 
	

function LoadingDataListOrderRealItems(){
	  $("#list").bootstrapTable({
	        toolbar: '#toolbar',
	        url:'/ebes/sysSetting/expressCompany',          //请求后台的URL（*）
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
	            field:"companyId",
	            align:"center",
	            title:"快递公司id",
	        },{
	            field:"companyName",
	            align:"center",
	            title:"快递公司",
	            
	        },{
	            field:"enable",
	            align:"center",
	            title:"启用/禁用"
	        }
	        ]

	    })
}

	
	
  





$(function(){

    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function(){
    	var companyName = $('#companyName').val();
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/expressCompany',
            query: {companyName: companyName,}
        });
    })
    //重置
    $("#btn_reset").click(function (){
    	$("#list").bootstrapTable('refresh',{url:'/ebes/sysSetting/expressCompany',
        });
    });

})

function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};