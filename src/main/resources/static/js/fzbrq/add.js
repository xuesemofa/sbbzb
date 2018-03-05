$(function(){
	$('.date_picker').date_input();
    $('#fzbrq_add_button').click(function(){
        $('#fzbrq_add_button').attr('disabled','disabled');
        $.ajax({
            url: "/fzbrq/add",
            async: false,
            type: 'post',
            data:$('#fzbrq_add_form').serialize(),
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
                    $('#fzbrq_back_button').click();
                }else{
                    $('#fzbrq_add_button').removeAttr('disabled');
                    alert(req.message);
                }
                $('.htmlBody1').hide();
                $('.htmlBody2').show();
            },
            error: erryFunction
        });
    });
    function erryFunction() {
        $('#fzbrq_add_button').removeAttr('disabled');
        $('.htmlBody1').hide();
        $('.htmlBody2').show();
        alert("错误!");
    };
});