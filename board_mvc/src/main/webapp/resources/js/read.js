/**
 * read.jsp 연관된 스크립트
 */
$(function(){
	
	let form = $("#actionForm");
	
	// list 클릭 -> 전체 리스트 보여주기
	$(".btn-info").onclick(function(){
		location.href="/board/list";
	})
	
	// modify 클릭 -> actionForm 보내기
	// /board/modify + get
	$(".btn-default").onclick(function(){
		form.attr("action", "/board/modify");
		form.submit();
	})
})