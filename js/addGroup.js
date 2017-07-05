function addGroup() {
	let dataToSend = {
			'users':[
				{'email':$('#Name1').val(), 'mobile':$('#tel1').val()},
				{'email':$('#Name2').val(), 'mobile':$('#tel2').val()}
			],
			eventId: '1234', 
			message: $('#messageText').val()
		};

    console.log(dataToSend)
	$.ajax({
		url: './addGroup',
		method: 'POST',
		data: JSON.stringify(dataToSend),
        contentType: 'application/json',
		dataType: 'json'
	});
}