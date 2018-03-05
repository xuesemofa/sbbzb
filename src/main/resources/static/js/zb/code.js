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
        data:{"pageNow":pageNow,"search":$('.sex').attr('sex')},
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
                    var h = "<tr><td>"+(index+1)+"</td><td>"+e.name+"</td><td data_id=\""+e.uuid+"\">"+e.code+"</td>"
                    +"<td><button type=\"button\" class=\"btn btn-sm btn-primary\" "
                    +" onclick=\"yd(this,1)\">上移</button>"
                    +"<button type=\"button\" class=\"btn btn-sm btn-primary\" "
                    +" onclick=\"yd(this,2)\" style=\"margin-left:15px\">下移</button>"
                    +"<button type=\"button\" class=\"btn btn-sm btn-primary nadd\" style=\"margin-left:15px\" "
                    +"onclick=\"yd3(this)\">更改顺序号</button></td></tr>";
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
//上下移动
function yd(obj,x){
    var i = $(obj).parents('tr').index();
    if(x==1){
//    上移
        if(i>0){
            var tr = $(obj).parents('tr');
            $(tr).prev().before(tr);
        }
    }else if(x==2){
//    下移
        if(i<$('#table_data').find('tr').length){
            var tr = $(obj).parents('tr');
            $(tr).next().after(tr);
        }
    }
    var k = $(obj).parents('tr').index();
    $('#table_data').find('tr').each(function(index,e){
        $(e).find('td').eq(0).text(index+1);
        $(e).find('td').eq(2).text(index+1);
    });
}
//指定顺序
function yd3(obj){
    $('#n-add_box').fadeToggle();
    var i = $(obj).parents('tr').index();
    $('#xxh').attr("i",i);
}
function yd2(obj){
    $('#n-add_box').fadeToggle();
    var i = $(obj).prev('div').find('input').val();
    var ival = parseInt(i);//如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
    if(!isNaN(ival)){
        var i2 = $(obj).prev('div').find('input').attr("i");

        if(i<0){
            i=0;
            var tr2 = $('#table_data').find('tr').eq(i2);
            $('#table_data').find('tr').eq(0).before(tr2);
        }
        if(i>= $('#table_data').find('tr').length){
            var tr2 = $('#table_data').find('tr').eq(i2);
            $('#table_data').append(tr2);
        }else{
            if(i == i2){
                i = i-1;
            }
            var tr = $('#table_data').find('tr').eq(i);
            var tr2 = $('#table_data').find('tr').eq(i2);
            $(tr).before(tr2);
        }
        $('#table_data').find('tr').each(function(index,e){
            $(e).find('td').eq(0).text(index+1);
            $(e).find('td').eq(2).text(index+1);
        });
    } else{
        alert(i +"不是数字");
    }
}

//提交
function sub(){
    var data = "";
    $('#table_data').find('tr').each(function(index,e){
        data += $(e).find('td').eq(2).attr("data_id")+"-"+$(e).find('td').eq(2).text()+",";
    });

    $.ajax({
    url: "/zb/setCodeSave",
    async: false,
    type: 'post',
    data:{"code":data},
    dataType: 'json',
    timeout: 1000,
    cache: false,
    beforeSend:function(XMLHttpRequest){
        $('.htmlBody1').show();  //之前
        $('.htmlBody2').hide();  //之前
    },
    success: function (req) {
        if(req.success){
            $('#zb_back_button').click();
        }else{
            alert(req.message);
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