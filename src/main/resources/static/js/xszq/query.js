$(function(){
    init();
});

//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/xszq/queryData",
        async: false,
        type: 'post',
        data:{"id":$('#table_data').attr('data_id')},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
            $('.htmlBody1').show();  //之前
            $('.htmlBody2').hide();  //之前
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = "<tr><td>"+(index+1)+"</td><td>"+e.name+"</td><td>"+(e.sex == "M" ? "男" : (e.sex == "F" ? "女" : ""))+"</td>"
                    +"<td>"+e.birth+"</td></tr>";
                    $('#table_data').append(h);
                });
            }
            $('.htmlBody1').hide();
            $('.htmlBody2').show();
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    $('.htmlBody1').hide();
    $('.htmlBody2').show();
    alert("错误!");
};