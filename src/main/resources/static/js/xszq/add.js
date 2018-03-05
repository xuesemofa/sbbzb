$(function(){

	$('.date_picker').date_input();

    $('#xszq_button').click(function(){
        $('#xszq_button').attr('disabled','disabled');
        $.ajax({
            url: "/xszq/add",
            async: false,
            type: 'post',
            data:$('#xszq_form').serialize(),
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
                    $('#xszq_back_button').click();
                }else{
                    $('#xszq_button').removeAttr('disabled');
                    alert(req.message);
                }
                $('.htmlBody1').hide();
                $('.htmlBody2').show();
            },
            error: erryFunction
        });
    });
    function erryFunction() {
        $('#xszq_button').removeAttr('disabled');
        $('.htmlBody1').hide();
        $('.htmlBody2').show();
        alert("错误!");
    };
});