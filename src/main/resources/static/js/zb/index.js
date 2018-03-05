$(function(){
    init("1");
});

//init
function init(pageNow){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/zb/initData",
        async: false,
        type: 'post',
        data:{"pageNow":pageNow,"search":$('#search').val()},
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
                    var dz = '';
                    if(e.dz == "D")
                        dz = "带班";
                    if(e.dz == "Z")
                        dz = "值班";

                    var sex = '';
                    if(e.sex == "M")
                        sex = "<font style='color:blue;'>男</font>";
                    if(e.sex == "F")
                        sex = "<font style='color:red;'>女</font>";

                    var h = "<tr><td>"+(index+1)+"</td><td>"+e.name+"</td><td>"+sex+"</td>"
                    +"<td>"+(e.department == null ? '' : e.department)
                    +"</td><td>"+dz+"</td><td>"+e.code+"</td>"
                    +"<td><button type=\"button\" class=\"btn btn-sm btn-primary\" "
                    +" data_id=\""+e.uuid+"\" onclick=\"del(this)\">删除</button></td></tr>";
                    $('#table_data').append(h);
                });
//                分页
                $('#zb_page_last').attr("onclick","init("+req.code+")");
                $('.pageNow').find("font").text(pageNow);
                $('.pageTwo').remove();
                var page_last = '';
                if((Number(pageNow)+1) <= Number(req.code)){
                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+1)+")\">"
                     +"<a><font style=\"vertical-align: inherit;\">"
                     +(Number(pageNow)+1)+"</font></a></li>";
                }
                if((Number(pageNow)+2) <= Number(req.code)){
                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+2)+")\">"
                     +"<a><font style=\"vertical-align: inherit;\">"
                     +(Number(pageNow)+2)+"</font></a></li>";
                }
                if((Number(pageNow)+3) <= Number(req.code)){
                    page_last += "<li class=\"footable-page pageTwo\" onclick=\"init("+(Number(pageNow)+3)+")\">"
                     +"<a><font style=\"vertical-align: inherit;\">"
                     +(Number(pageNow)+3)+"</font></a></li>";
                }
                $('.pageNow').after(page_last);

                $('.pageOne').remove();
                var page_first = '';
                if((Number(pageNow)-3) > 0){
                    page_first = "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-3)+")\">"
                    +"<a><font style=\"vertical-align: inherit;\">"
                    +(Number(pageNow)-3)+"</font></a></li>";
                }
                if((Number(pageNow)-2) > 0){
                    page_first += "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-2)+")\">"
                                        +"<a><font style=\"vertical-align: inherit;\">"
                                        +(Number(pageNow)-2)+"</font></a></li>";
                }
                if((Number(pageNow)-1) > 0){
                    page_first += "<li class=\"footable-page pageOne\" onclick=\"init("+(Number(pageNow)-1)+")\">"
                                        +"<a><font style=\"vertical-align: inherit;\">"
                                        +(Number(pageNow)-1)+"</font></a></li>";
                }
                $('.pageNow').before(page_first);
            }
            $('.htmlBody1').hide();
            $('.htmlBody2').show();
        },
        error: erryFunction
    });
}
//    del
function del(obj){
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url: "/zb/del",
            async: false,
            type: 'get',
            data:{"id":$(obj).attr('data_id')},
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
                $('.htmlBody1').show();  //之前
                $('.htmlBody2').hide();  //之前
            },
            success: function (req) {
                init("1");
            },
            error: erryFunction
        });
    }
}

//    error
function erryFunction() {
    $('.htmlBody1').hide();
    $('.htmlBody2').show();
    alert("错误!");
};