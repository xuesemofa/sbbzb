$(function(){

	$('.date_picker').date_input();

    $('#people_add_button').click(function(){
        $('#people_add_button').attr('disabled','disabled');
        $.ajax({
            url: "/people/add",
            async: false,
            type: 'post',
            data:$('#people_add_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
                $('.htmlBody1').show();
                $('.htmlBody2').hide();
            },
            success: function (req) {
                if(req){
                    alert('成功');
                    $('#people_back_button').click();
                }else{
                    alert('失败');
                    $('#people_add_button').removeAttr('disabled');
                }
                $('.htmlBody1').hide();
                $('.htmlBody2').show();
            },
            error: erryFunction
        });
    });
    function erryFunction() {
        $('.htmlBody1').hide();
        $('.htmlBody2').show();
        alert("错误!");
    };
});