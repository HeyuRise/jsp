/**
 * Created by LiLU on 2017/8/28.
 */
var printid;
$(function(){
	$(window).resize(function() {
	    $('#list').bootstrapTable('resetView', {
	        height: tableHeight()
	    })
	})
	
    class BstpTable{
//        constructor(obj) {
//            this.obj=obj;
//        }
        inint(searchArgs){
             //---先销毁表格 ---
             $("#list").bootstrapTable('destroy');   
             $("#list").bootstrapTable({
               toolbar: '#toolbar',
               url:'/ebes/order/express',  
               method: 'get',
               contentType: "application/x-www-form-urlencoded",
                
                
                //【查询设置】
                /* queryParamsType的默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                                  设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber */
                queryParamsType:'', 
                queryParams: function queryParams(params) {  
                  var param = {  
                      pageNumber: params.pageNumber,    
                      pageSize: params.pageSize
                  }; 
                  for(var key in searchArgs){
                      param[key]=searchArgs[key]
                  }  
                  return param;                   
                }, 
                
                //【其它设置】
                locale:'zh-CN',//中文支持
                pagination: true,//是否开启分页（*）
                pageNumber:1,//初始化加载第一页，默认第一页
                pageSize: 10,//每页的记录行数（*）
                pageList: [5,10,25,100],//可供选择的每页的行数（*）
                sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
               
               detailView:true,
               detailFormatter:"detailFormatter",//点击展开预先执行事件
                
                //【设置列】
                columns:[{
               	 field:"orderId",
                    align:"center",
                    title:"订单号"  ,
                    formatter: function(value,row,index){
                    	return "<a target='_blank' href='view-Express.html?"+"orderId="+ row.orderId+"' id='" + row.orderId+"' >" + row.orderId+" </a>";
        			}
               },{
                   field:"mailno",
                   align:"center",
                   title:"运单号"  ,
               },{
                   field:"parcelQuantity",
                   align:"center",
                   title:"包裹数量"
               },{
                   field:"expressType",
                   align:"center",
                   title:"业务类型"
               },{
                   field:"receiveDate",
                   align:"center",
                   title:"揽件时间"
               },{
                   field:"status",
                   align:"center",
                   title:"状态"
               },{
                   field:"sendDate",
                   align:"center",
                   title:"创建日期"
               },{
                   field:"receiverCompany",
                   align:"center",
                   title:"收方公司"
               },{
                   field:"receiverContact",
                   align:"center",
                   title:"收方联系人"
               },{
                   field:"sendCompany",
                   align:"center",
                   title:"寄件公司"
               },{
                   field:"sendContact",
                   align:"center",
                   title:"寄件联系人"
               }
               ]
            })
         }
    }
    
    var bstpTable=new BstpTable($("table"));
    bstpTable.inint({})
    
    $("#btn_search").click(function(){
        var searchArgs={
        		orderId:$("#orderId").val(),
            	mailno : $('#mailno').val(),
            	mailnoChild :$('#mailnoChild').val(),
            	sendCompany:  $('#sendCompany').val(),
            	receiveCompany : $('#receiveCompany').val(),
            	sendContact : $('#sendContact').val(),
            	receiveContact: $('#receiveContact').val(),
            	sendDateBegin:$('#sendDateBegin').val(),
            	sendDateEnd : $('#sendDateEnd').val(),
            	receiveDateBegin:$('#receiveDateBegin').val(),
            	receiveDateEnd : $('#receiveDateEnd').val(),
            	status : $('#status').val(),
        }
        bstpTable.inint(searchArgs)
    })
    
//    $("#list").bootstrapTable({
//        toolbar: '#toolbar',
//        url:'/ebes/order/express',  
//        contentType: "application/x-www-form-urlencoded",
//        queryParamsType:'', 
//        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
//        striped: true,                      //是否显示行间隔色
//        pagination: true,                   //是否显示分页（*）
//        pageNumber:1,                       //初始化加载第一页，默认第一页
//        pageSize: 10,                       //每页的记录行数（*）
//        pageList: [5, 10, 25, 100],        //可供选择的每页的行数（*）
//        sidePagination: "server", 
//        detailView:true,
//        detailFormatter:"detailFormatter",//点击展开预先执行事件
//        columns:[{
//        	 field:"orderId",
//             align:"center",
//             title:"订单号"  ,
//             formatter: function(value,row,index){
//             	return "<a href='view-Express.html?"+"orderId="+ row.orderId+"' id='" + row.orderId+"' >" + row.orderId+" </a>";
// 			}
//        },{
//            field:"mailno",
//            align:"center",
//            title:"运单号"  ,
//        },{
//            field:"parcelQuantity",
//            align:"center",
//            title:"包裹数量"
//        },{
//            field:"expressType",
//            align:"center",
//            title:"业务类型"
//        },{
//            field:"receiveDate",
//            align:"center",
//            title:"揽件时间"
//        },{
//            field:"status",
//            align:"center",
//            title:"状态"
//        },{
//            field:"sendDate",
//            align:"center",
//            title:"创建日期"
//        },{
//            field:"receiverCompany",
//            align:"center",
//            title:"收方公司"
//        },{
//            field:"receiverContact",
//            align:"center",
//            title:"收方联系人"
//        },{
//            field:"sendCompany",
//            align:"center",
//            title:"寄件公司"
//        },{
//            field:"sendContact",
//            align:"center",
//            title:"寄件联系人"
//        }
//        ]
//
//    })
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


//打印按钮是否显示
$(function(){
	var index=$("#btn-print").attr("index")
	$.ajax({
		type:"get",
		dataType:"json",
		url:"/ebes/auth/button?buttonId="+index,
		data:{},
		success:function(data){
			if(data.result==0){
				$("#btn-print").show();
			}else{
				$("#btn-print").hide();
			}
		}
	})
})


/*+号显示详情*/
function detailFormatter(index, row) {
    var str= "<div class=''> ";
    str+= "<p>子运单号：<span>"+row.mailnoChild+"</span></p>";
    str+= "<p>收件公司：<span>"+row.receiverCompany+"</span></p>";
    str+= "<p>收方联系人：<span>"+row.receiverContact+"</span></p>";
    str+= "<p>收方联系电话：<span>"+row.receiverTel+"</span></p>";
    str+= "<p>收方详细地址：<span>"+row.receiverAddress+"</span></p>";
    str+= "<p>寄件公司：<span>"+row.sendCompany+"</span></p>";
    str+= "<p>寄件联系人：<span>"+row.sendContact+"</span></p>";
    str+= "<p>寄件方联系电话：<span>"+row.sendTel+"</span></p>";
    str+= "</div>";
    return str
}


$(function(){
    //添加全局回车响应时间
    document.onkeydown=search;

    //状态加载
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"/ebes//util/status",
		success:function(data){
			var size = data.size;
			var items = data.items;
			load(items,size)
		}
	})
	
	
    var id
    //查询
//    $("#btn_search").click(function(){
//    	var orderId=$("#orderId").val();
//    	var mailno = $('#mailno').val();
//    	var mailnoChild = $('#mailnoChild').val();
//    	var sendCompany = $('#sendCompany').val();
//    	var receiveCompany = $('#receiveCompany').val();
//    	var sendContact = $('#sendContact').val();
//    	var receiveContact = $('#receiveContact').val();
//    	var sendDateBegin = $('#sendDateBegin').val();
//    	var sendDateEnd = $('#sendDateEnd').val();
//    	var receiveDateBegin = $('#receiveDateBegin').val();
//    	var receiveDateEnd = $('#receiveDateEnd').val();
//    	var status = $('#status').val();
//        $("#list").bootstrapTable('refresh',{url:'/ebes/order/express',
//            query: {orderId:orderId,mailno: mailno,mailnoChild:mailnoChild, sendCompany: sendCompany,receiveCompany: receiveCompany,sendContact: sendContact,receiveContact:receiveContact,sendDateBegin:sendDateBegin,sendDateEnd:sendDateEnd,receiveDateBegin:receiveDateBegin,receiveDateEnd:receiveDateEnd,status:status,}
//        });
//    })

    //重置
    $("#btn_reset").click(function (){
    	 window.location.reload();
//		$('#datetimepicker1').datetimepicker('update', new Date());
//		$('#datetimepicker2').datetimepicker('update', new Date());
//		$('#datetimepicker3').datetimepicker('update', new Date());
//		$('#datetimepicker4').datetimepicker('update', new Date());
//		$("#status")[0].selectedIndex = -1; 
//        $("input").val("")
//        $("#list").bootstrapTable('refresh',{url:'/ebes/order/express',
//        });
    });
    
    /*打印*/
    $('#list').on('click-row.bs.table', function (e, row, element) {
        $('.success').removeClass('success');
        $(element).addClass('success');
        $("#num").val(row.parcelQuantity);
        printid=row.mailno;
        return printid
     
    });
})

function print() {
    if($("#list tr").hasClass('success')){
    	if(printid=="" || printid=="undefined"){
    		 swal("无运单号，无法打印!", "", "error");
    	}else{
        	var num= $("#num").val();
            var height=Number(num)*1050+'px'
            console.log(height)
            var pring_content="<iframe id='iframe' src='../print/print.html?id="+printid+"' style='width:500px;height:"+height+";' frameborder='no'></iframe>"          
            $("#print").html(pring_content)
            setTimeout(function () { 
            	$("#print").jqprint();
            }, 400);
            //$("#print").jqprint();
    	}
    	//alert(printid)
    	//window.location.href="../print/print.html?id="+printid+""
    }else{
        swal("请在列表中选中需要打印的记录", "", "error");
    }
}

function search(event) {
    if(event.keyCode == 13) {
        $('#btn_search').click();
    }
};

//状态加载
function load(items,size){
	$("#status").html("")
	for(var i=0;i<size;i++){
		var html='<option>'+items[i].name+'</option>'
		$("#status").append(html)
	}
	$("#status")[0].selectedIndex = -1; 
}

//开始时间小于结束时间
$(function () {
    $('#datetimepicker1').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",
        language: 'zh-CN',
        autoclose:true,
    }).on('changeDate', function(ev){
        if(ev.date){
            $("#datetimepicker2").datetimepicker('setStartDate', new Date(ev.date.valueOf()))
        }else{
            $("#datetimepicker2").datetimepicker('setStartDate',null);
        }
    })
    $('#datetimepicker2').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",
        language: 'zh-CN',
        autoclose:true,
    }).on('changeDate', function(ev){
        if(ev.date){
            $("#datetimepicker1").datetimepicker('setEndDate', new Date(ev.date.valueOf()))
        }else{
            $("#datetimepicker1").datetimepicker('setEndDate',new Date());
        }

    })

    $('#datetimepicker3').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",
        language: 'zh-CN',
        autoclose:true,
    }).on('changeDate', function(ev){
        if(ev.date){
            $("#datetimepicker4").datetimepicker('setStartDate', new Date(ev.date.valueOf()))
        }else{
            $("#datetimepicker4").datetimepicker('setStartDate',null);
        }
    })
    $('#datetimepicker4').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",
        language: 'zh-CN',
        autoclose:true,
    }).on('changeDate', function(ev){
        if(ev.date){
            $("#datetimepicker3").datetimepicker('setEndDate', new Date(ev.date.valueOf()))
        }else{
            $("#datetimepicker3").datetimepicker('setEndDate',new Date());
        }

    })
});


//导出
function exportxls(){  
	var orderId=$("#orderId").val();
	var mailno = $('#mailno').val();
	var mailnoChild = $('#mailnoChild').val();
	var sendCompany = $('#sendCompany').val();
	var sendContact = $('#sendContact').val();
	var receiveCompany = $('#receiveCompany').val();
	var receiveContact = $('#receiveContact').val();  
	
	var sendDateBegin = $('#sendDateBegin').val();
	var sendDateEnd = $('#sendDateEnd').val();
	var receiveDateBegin = $('#receiveDateBegin').val();
	var receiveDateEnd = $('#receiveDateEnd').val();
	var status = $('#status').val();  
    var form = $("<form>");  
	       form.attr('style', 'display:none');  
	       form.attr('target', '');  
	       form.attr('method', 'get');  
	       form.attr('action', '/ebes/order/expressExport');  
	       var input1 = $('<input>');  
	       input1.attr('type', 'hidden');  
	       input1.attr('name', 'mailno');  
	       input1.attr('value', mailno);   
	       var input2 = $('<input>');  
	       input2.attr('type', 'hidden');  
	       input2.attr('name', 'mailnoChild');  
	       input2.attr('value', mailnoChild);   
	       var input3 = $('<input>');  
	       input3.attr('type', 'hidden');  
	       input3.attr('name', 'sendCompany');  
	       input3.attr('value', sendCompany);   
	       var input4 = $('<input>');  
	       input4.attr('type', 'hidden');  
	       input4.attr('name', 'sendContact');  
	       input4.attr('value', sendContact); 
	       var input5 = $('<input>');  
	       input5.attr('type', 'hidden');  
	       input5.attr('name', 'receiveCompany');  
	       input5.attr('value', receiveCompany);   
	       var input6 = $('<input>');  
	       input6.attr('type', 'hidden');  
	       input6.attr('name', 'receiveContact');  
	       input6.attr('value', receiveContact); 
	       
	       
	       var input7 = $('<input>');  
	       input7.attr('type', 'hidden');  
	       input7.attr('name', 'sendDateBegin');  
	       input7.attr('value', sendDateBegin);   
	       var input8 = $('<input>');  
	       input8.attr('type', 'hidden');  
	       input8.attr('name', 'sendDateEnd');  
	       input8.attr('value', sendDateEnd);   
	       var input9 = $('<input>');  
	       input9.attr('type', 'hidden');  
	       input9.attr('name', 'receiveDateBegin');  
	       input9.attr('value', receiveDateBegin);   
	       var input10 = $('<input>');  
	       input10.attr('type', 'hidden');  
	       input10.attr('name', 'receiveDateEnd');  
	       input10.attr('value', receiveDateEnd); 
	       var input11 = $('<input>');  
	       input11.attr('type', 'hidden');  
	       input11.attr('name', 'status');  
	       input11.attr('value', status); 
	       var input12 = $('<input>');  
	       input12.attr('type', 'hidden');  
	       input12.attr('name', 'orderId');  
	       input12.attr('value', orderId); 
	       $('body').append(form);  
	       form.append(input1);  
	       form.append(input2); 
	       form.append(input3);  
	       form.append(input4);
	       form.append(input5);  
	       form.append(input6);
	       form.append(input7);  
	       form.append(input8); 
	       form.append(input9);  
	       form.append(input10);
	       form.append(input11);  
	       form.append(input12);  
	       form.submit();  
	       form.remove();  
} 


