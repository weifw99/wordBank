function toAddSuggest(){
	//alert(11);
	$.ajax({
	   type: "GET",
	   url: "/word/suggest/toAdd",
	   success: function(html){
		   $("#myModal > div > div").html(html);
		   $('#myModal').modal('show');
	   }
	});
	
}
function addSuggest(){
	//alert(11);
	$('#myModal').children("form")
	$.ajax({
		   type: "POST",
		   url: "/word/suggest/add",
		   data: "userWordName="+$('#userWordName').val()+"&wordPinyin="+$('#wordPinyin').val(),
		   success: function(msg){
		     //alert( "Data Saved: " + msg );
			   if(!msg){
				   alert("保存失败");
			   }
		   }
		});
}

