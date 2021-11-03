$(function() {

	$("#loadBtn").click(function() {
		$.ajax({
			type: "GET",
			url: "http://localhost:8050/unicorn-front/dev/v1.0/mpa/sample/ajaxFragment",
			data: {
			},
			dataType: "html"
		}).done((data, textStatus, jqXHR) => {
			console.log(data);
			console.log(textStatus);
			console.log(jqXHR);
			$('#cardList').append(data);
		}).fail((jqXHR, textStatus, errorThrown) => {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}).always(() => {
			console.log('always');
		});
	});

});
