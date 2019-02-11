/*登录页面控制脚本*/
$(document).ready(function(){

	var form = {
		forget:function(){
            $(".login").addClass("hide");
			$(".forgetPass").removeClass("hide");
		}
	}
	$(".forget").on("click",form.forget);









});


