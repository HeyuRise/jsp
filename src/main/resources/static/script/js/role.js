/**
 * Created by LiLU on 2017/8/29.
 */
$(function(){
	var $table = $("#list")
    $("#list").bootstrapTable({
        url:'/ebes/user/role',          //请求后台的URL（*）
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
            formatter: function(value,row,index){
				return "<span >" + (index+1) +" </span>";
			}
        },{
            field:"name",
            align:"center",
            title:"角色名称"
        }
        ]

    })
    
    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function (){
    	var roleName = $('#roleName').val();
        $table.bootstrapTable('refresh',{url:'/ebes/user/role',
            query: {roleName: roleName}
        });
    });

    //重置
    $("#btn_reset").click(function (){
        $table.bootstrapTable('refresh',{url:'/ebes/user/role',
        });
    });
    
})



function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};