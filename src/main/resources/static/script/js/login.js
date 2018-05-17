  

$(document).ready(function() {
	    //添加全局回车响应时间
	    document.onkeydown=search;
	    
		if(document.referrer!=""){	
	    }else{
	    	load_last()
	    }
    });

	function load_last(){
		  //将获取的值填充入输入框中
        var lastUser = $.cookie('LAST'); //获取cookie中其键名为Last对应的值
        console.log(lastUser)
        var Isre = document.getElementById("Remember");
        var userId = $('#Username');
        var code = $('#PSD');
        if (lastUser != undefined) {
            userId.val($.cookie("UN" + lastUser));//如果对应的键有值，则填充到登录框中，作出响应
            // code.val("****");  应把键名取得特别点，防止跟其他的cookie冲突，要保证名字的唯一性

            var base = new Base64();
            var result2 = base.decode($.cookie("PSD" + lastUser));
            code.val(result2);
            Isre.checked = true;
            if(document.referrer==""){//自动登录
            	login();
            } 
        } else {
            userId.val("");  //否则输入框内容为空
        }
	}


    function webChange() {		
        var user = $("#Username").val();
        var mdcode = $.cookie("PSD" + user);
        var code = $("#PSD");
        var Isrem = document.getElementById("Remember");
        if (mdcode != undefined&&mdcode!="") {   //因为密码经md5加密，不能全部显示到登录框中，所以cookie中记住的密码显示在框中的是****
            // code.val("****");       而不是真正的密码和加密后的密码
            var base = new Base64();
            var result2 = base.decode(mdcode);
            code.val(result2);

            Isrem.checked = true;
        } else {                 //如果cookie中没有相应的信息，未匹配成功，则密码输入框为空
            code.val("");
            Isrem.checked = false;
        }
    }

    function login() {
    	$("#txtPwd").show()
    	$("#txtPwd").val($("#PSD").val())
    	$("#PSD").attr("type","text")
    	$("#PSD").hide()
        //异常判断...
        var json = "";
        var username = $("#Username").val();
        var psd = $("#PSD").val();
        // 对输入框是否有值，进行判断，如果没有，则显示星星，以提示你未填登录信息
        if (username == "") {
           // alert("输入用户名")
            return ;
        }
        if (psd == "") {
           // alert("密码")
            return ;
        }

        //如果填完了信息，则进行如下判断
        if (!(username == "" || psd == "")) {
            var Isre = document.getElementById("Remember");
            if (Isre.checked && $.cookie("IR" + username)) {  //IR+username键名对应保存的信息是真假，用来标记username是否被记住过，即在cookie中是否有存储
                var mycode = $.cookie("PSD" + username);
                var base = new Base64();
                var  renewcode= base.encode(psd);
                $.cookie("IR" + username, 'true', { expires: 7, path: '/' });
                $.cookie("UN" + username, username, { expires: 7, path: '/' });
                //取密码框中的数据，如果是****则说明cookie中有值，不相同的就存储新的。
                if (psd == mycode)
                {
                    $.cookie("PSD" + username, mycode, { expires: 7, path: '/' });
                    json = '{"USERNAME": "' + username + '", "PSD": "' +mycode+'" }';
                }
                else
                {
                    $.cookie("PSD" + username, renewcode, { expires: 7, path: '/' });
                    json = '{"USERNAME": "' + username + '", "PSD": "' +renewcode +'" }';
                }
                $.cookie("LAST", username, { expires: 7, path: '/' });
            } else if (Isre.checked == true&& $.cookie("IR" + username)==undefined) {//如果记住密码的checked为真，且是新用户则继续存储用户名和密码，
                $.cookie("LAST", username, { expires: 7, path: '/' });                //LAST记住的是最近登入的用户名
                $.cookie("IR" + username, 'true', { expires: 7, path: '/' });         //IR记住的是username是否在cookie中
                $.cookie("UN" + username, username, { expires: 7, path: '/' });//UN记住的是用户名
                var base2 = new Base64();
                var  renewcode2= base2.encode(psd);
                $.cookie("PSD" + username,renewcode2, { expires: 7, path: '/' });//PSD记住的是加密后的密码
            } else if(Isre.checked==false&&$.cookie("IR" + username))  //将原有最近的记住的密码取消记住
            {

                json = '{"USERNAME": "' + username + '", "PSD": "' +$.cookie("PSD"+username) +'" }';
                if (username == $.cookie('LAST'))                                               //如果LAST对应的值被登录即最近登录的账号且未点击checkbox，则将cookie中对应user的信息删除
                    $.cookie('LAST', '', { expire: -1, path: '/' });
                    $.cookie("IR" + username, '', { expires: -1, path: '/' });
                    $.cookie("UN" + username, '', { expires: -1, path: '/' });
                    $.cookie("PSD" + username, '', { expires: -1, path: '/' });
            }else                       //新密码且不记住
            {
                //json = '{"USERNAME": "' + username + '", "PSD": "' +$.md5(psd)+'" }';
            }
            document.getElementById("form").submit();
        }

    }

    window.onload = function(){
    	var oInput = document.getElementById("Username");
    	oInput.focus();
    }
    
    
    function search(event) {
        if(event.keyCode == 13) {
           login();
        }
    };
