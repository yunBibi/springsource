/**
 * read.jsp 연관된 스크립트
 */
$(function(){
	
	let form = $("#actionForm");
	
	// list 클릭 -> 전체 리스트 보여주기
	$(".btn-info").click(function(){
		// actionForm에서 bno 제거
		form.find("input[name='bno']").remove();
		
		// actionForm action은 수정 /board/list
		form.attr("action", "/board/list");
		
		// actionForm 전송
		form.submit();
		
	})
	
	// modify 클릭 -> actionForm 보내기
	// /board/modify + get
	$(".btn-default").click(function(){
		form.attr("action", "/board/modify");
		form.submit();
	})
})