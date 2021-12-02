/**
 * 
 */
$(function(){
	$(".move").click(function(e){
		// a태그 속성 중지
		e.preventDefault();
		// 제목을 클릭하면 href 값 가져오기
		let code = $(this).attr('href');
		
		// $.ajax(기본), $.getJSON() 가져올 타입 명시
		$.getJSON({
			url:code,
			success:function(data){
				// console.log(data);
				
				let str = '<ul>';
				
				str += "<li>code :"+data.code+"</li>";
				str += "<li>title :"+data.title+"</li>";
				str += "<li>writer :"+data.writer+"</li>";
				str += "<li>price :"+data.price+"</li>";
				str += "</ul>";
				
				$("#result").html(str);
			}
		})
	})
})