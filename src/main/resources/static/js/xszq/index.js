$(function(){
    init("1");
});

//init
function init(pageNow){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/xszq/initData",
        async: false,
        type: 'get',
        data:{"pageNow":pageNow},
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
                    var h = "<tr><td>"+(index+1)+"</td><td>"+e.strDate+"</td><td>"+e.endDate+"</td>"
                    +"<td>"
//                    +"<button type=\"button\" class=\"btn btn-sm btn-primary\" "
//                    +"onclick=\"window.parent.cli(this)\" data-target=\"/xszq/query?id="+e.uuid+"\">查看</button>"
                    +"&nbsp;<button type=\"button\" class=\"btn btn-sm btn-primary\" "
                    +"onclick=\"del(this)\" data_id=\""+e.uuid+"\">删除</button>"
                    +"</td></tr>";
                    $('#table_data').append(h);
                });
//                分页
                $('#db_page_last').attr("onclick","init("+req.code+")");
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
    $.ajax({
        url: "/xszq/del",
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

//    error
function erryFunction() {
    $('.htmlBody1').hide();
    $('.htmlBody2').show();
    alert("错误!");
};