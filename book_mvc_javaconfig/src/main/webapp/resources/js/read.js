/**
 * read.jsp
 */
$(function(){
	
	let form = $("#actionForm");
	
	// 목록버튼 클릭 시 목록화면 보여주기
	$(".btn-secondary").click(function(){
		location.href="/book/list";
	})
	
	// 삭제 버튼 클릭 시 폼을 전송시키기(get)
	$(".btn-danger").click(function(){
		form.attr("action","/book/remove");
		form.submit();
	})
	
	// 수정 버튼 클릭 시 폼 전송(get))
	$(".btn-primary").click(function(){
		form.attr("action","/book/modify");
		form.submit();
		
	})
})