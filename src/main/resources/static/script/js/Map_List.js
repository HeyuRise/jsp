/**
 * Created by LiLU on 2017/8/29.
 */
$(function(){
    $("#list").bootstrapTable({
    	toolbar: '#toolbar',
        url:'/ebes/address/address',          //请求后台的URL（*）
        striped: true,                      //是否显示行间隔色
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [5, 10, 25, 100],        //可供选择的每页的行数（*）
        columns:[{
            field:"province",
            align:"center",
            title:"省",
        },{
            field:"city",
            align:"center",
            title:"市"
        },{
            field:"county",
            align:"center",
            title:"区/县"
        }
        ]

    })
})


$(function(){
    //添加全局回车响应时间
    document.onkeydown=search;
    //查询
    $("#btn_search").click(function(){
    	var province = $('#province').val();
    	var city = $('#city').val();
    	var county = $('#country').val();
    	 $("#list").bootstrapTable('refresh',{url:'/ebes/address/address',
            query: {province: province,city:city, county: county,}
        });
    })
    
    //重置
    $("#btn_reset").click(function (){
    	 $("input").val("")
    	 $("#list").bootstrapTable('refresh',{url:'/ebes/address/address',
        });
    });

})

function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};
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

//导出
function exportxls(){  
	var province = $('#province').val();
	var city = $('#city').val();
	var county = $('#country').val(); 
    var form = $("<form>");  
	       form.attr('style', 'display:none');  
	       form.attr('target', '');  
	       form.attr('method', 'get');  
	       form.attr('action', '/ebes/address/addressExport');  
	       var input1 = $('<input>');  
	       input1.attr('type', 'hidden');  
	       input1.attr('name', 'province');  
	       input1.attr('value', province);   
	       var input2 = $('<input>');  
	       input2.attr('type', 'hidden');  
	       input2.attr('name', 'city');  
	       input2.attr('value', city);   
	       var input3 = $('<input>');  
	       input3.attr('type', 'hidden');  
	       input3.attr('name', 'county');  
	       input3.attr('value', county);   
	       $('body').append(form);  
	       form.append(input1);  
	       form.append(input2); 
	       form.append(input3);  
	       form.submit();  
	       form.remove();  
} 

////获取省市区
//$(function(){
//	$.ajax({
//		type:"GET",
//		dataType:"json",
//		url:"/ebes/util/province",
//		success:function(data){
//			var size = data.size;
//			var items = data.items;
//			province(items,size)
//		}
//	})
//})
//function province(items,size){
//	for(var i=0;i<size;i++){
//		var provincehtml='<option>'+items[i].province+'</option>'
//		$("#province").append(provincehtml)
//	}
//	$("#province")[0].selectedIndex = -1; 
//}
////市
//function selectcity(){
//	var province=$("#province").val();
//	if(province!=""&& province!=null){
//		$.ajax({
//			type:"GET",
//			dataType:"json",
//			url:"/ebes/util/city?p_text="+province,
//			success:function(data){
//				var size = data.size;
//				var items = data.items;
//				loadcity(items,size)
//			}
//		})
//	}else{
//		alert("请先选择省")
//	}
//	$("#country")[0].selectedIndex = -1; 
//}
//function loadcity(items,size){
//	$("#city").html("")
//	for(var i=0;i<size;i++){
//		var cityhtml='<option>'+items[i].city+'</option>'
//		$("#city").append(cityhtml)
//	}
//	$("#city")[0].selectedIndex = -1; 
//}
////区
//function selectcountry(){
//	var province=$("#province").val();
//	var city=$("#city").val();
//	if(province!=""&& province!=null && city!=""&& city!=null){
//		$.ajax({
//			type:"GET",
//			dataType:"json",
//			url:"/ebes/util/county?p_text="+province+"&c_text="+city,
//			success:function(data){
//				var size = data.size;
//				var items = data.items;
//				loadcountry(items,size)
//			}
//		})
//	}else{
//		alert("请先选择市")
//	}
//}
//function loadcountry(items,size){
//	$("#country").html("")
//	for(var i=0;i<size;i++){
//		var countryhtml='<option>'+items[i].county+'</option>'
//		$("#country").append(countryhtml)
//	}
//	$("#country")[0].selectedIndex = -1; 
//}