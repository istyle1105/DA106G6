//TweenMax.to(選擇器, 時間(單位: 秒), {屬性});
//ease型別，Sine加/減速動畫，由快變慢
//easeInOut() 結合 easeIn()進場 和 easeOut()出場

$('#login-button').click(function(){
	$('#login-button').fadeOut("slow",function(){
		$("#container").fadeIn();
		TweenMax.from("#container", .4, { scale: 0, ease:Sine.easeInOut});
		TweenMax.to("#container", .4, { scale: 1, ease:Sine.easeInOut});
	});
});

$(".close-btn").click(function(){
	TweenMax.from("#container", .4, { scale: 1, ease:Sine.easeInOut});
	TweenMax.to("#container", .4, { left:"0px", scale: 0, ease:Sine.easeInOut});
	$("#container, #forgotten-container").fadeOut(800, function(){
		$("#login-button").fadeIn(800);
	});
});

