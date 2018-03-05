$(function(){
	$('.date_picker').date_input();
    $('#dbzbrssz_add_button').click(function(){
        $('#dbzbrssz_add_button').attr('disabled','disabled');
        $.ajax({
            url: "/dbzbrssz/add",
            async: false,
            type: 'post',
            data:$('#dbzbrssz_add_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
                $('.htmlBody1').show();
                $('.htmlBody2').hide();
            },
            success: function (req) {
                if(req.success){
                    alert('成功');
                    $('#dbzbrssz_back_button').click();
                }else{
                    $('#dbzbrssz_add_button').removeAttr('disabled');
                    alert(req.message);
                }
                $('.htmlBody1').hide();
                $('.htmlBody2').show();
            },
            error: erryFunction
        });
    });
    function erryFunction() {
        $('#dbzbrssz_add_button').removeAttr('disabled');
        $('.htmlBody1').hide();
        $('.htmlBody2').show();
        alert("错误!");
    };
});