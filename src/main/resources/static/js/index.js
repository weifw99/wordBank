$(function(){
	
	$("#leftNav").children("a").bind("click", function(){
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		var id=$(this).attr("id");
		if("suggest"== id){
			suggestList(1,10);
		}
	});

	
	
	toSuggestIndex();
	


	function toSuggestIndex(){
		//alert(11);
		$.ajax({
			type: "GET",
			url: "/word/suggest/toIndex",
			success: function(html){
				$("#contentContainer").html(html);
				//加载推荐词
				suggestList(1,10);
			}
		});
		
	}

});


//翻页
function setPage(a){
	suggestList($(a).attr("value"), 10)
}

function suggestList(pageNum, pageSize){
	$(".main").find("table > tbody").empty();
	
	$.ajax({
	   type: "POST",
	   url: "/word/suggest/list",
	   data: "pageNum="+pageNum+"&pageSize="+pageSize,
	   success: function(msg){
		   //var msgJson =  eval("(" +msg+")");
		   var list = msg.list;
		   //alert( "Data Saved: " + msg.list );
		   for (var i = 0; i < list.length; i++) {
			   
			   //alert(list[i].id);
			   var tr = '<tr><td>#</td><td>'+list[i].userWordName+
			   		'</td><td>'+list[i].wordName+'</td><td>'+list[i].wordPinyin+
			   		'</td><td>'+list[i].dataStatus+'</td>'
			   		+ '<td>'+list[i].createdDate+'</td>'
			   		+ '<td>操作</td>'
			   		+'</tr>';
			   
			   
			   $(".main").find("table > tbody").append(tr);
		   }
		   
		   var prePage = msg.prePage;
		   var nextPage = msg.nextPage;
		   var hasPreviousPage = msg.hasPreviousPage;
		   var hasNextPage = msg.hasNextPage;
		   var navigatepageNums = msg.navigatepageNums;
		   //拼装分页信息
		   var ul = '<ul class="pagination" style="margin: 5px 0;">';
		   
		   if(hasPreviousPage){//有前一页
			   ul += '<li><a value="'+prePage+'" onclick="setPage(this)" href="#">&laquo;</a></li>';
		   }
		   for (var i = 0; i < navigatepageNums.length; i++) {
			
			   ul += '<li><a value="'+navigatepageNums[i]+'" onclick="setPage(this)" href="#">'+navigatepageNums[i]+'</a></li>';
		   }
		   
		   if(hasNextPage){//有后一页
			   ul += '<li><a value="'+nextPage+'" onclick="setPage(this)" href="#">&raquo;</a></li>';
		   }
		   ul += '</ul>';
		   $("#footerTablePage").html(ul);
	
	   }
	});
	
	//$(".main").find("table > tbody").html();

}
