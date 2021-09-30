$(function() {

	$('[data-toggle=datepicker]').each(function() {
		var target = $(this).data('target') || '';
		if (target) {
			$(target).datepicker({
				showOn: "", // ボタンクリックのみで起動する
				dateFormat: 'yy-mm-dd',
				changeYear: true,
				yearRange: '-100:+0',
				changeMonth: true,
				duration: 300,
				showAnim: 'show'
			});
			$(this).bind("click", function() {
				$(target).datepicker("show");
			});
		}
	});

	//  $(".date-item").on("click", function() {
	//    $("#controlArea").hide(); // div領域を隠す
	//  });

});