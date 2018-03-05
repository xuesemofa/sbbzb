$(function(){
    init();
});

//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/xszbb/queryData",
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
            if(req.db != null){
                $(req.db).each(function(index,e){
                    var html = "<tr><td>"+(index+1)+"</td><td>"+(e.name == null ? "" : e.name)+"</td><td></td>"
                    +"<td></td><td></td><td>"+e.birth+"</td><td>"+getWeek(e.birth)+"</td></tr>";
                    $('#table_data').append(html);
                });
                $('#table_data').find("tr").each(function(index,e){
                    var date = $(e).find('td').eq(5).text();
                    $(req.zb).each(function(index2,e2){
                        if(e2.zbrq == date){
                            $(e).find('td').eq(2).text(e2.amid == 'null' ? "" : e2.amid);
                            $(e).find('td').eq(3).text(e2.pmid == 'null' ? "" : e2.pmid);
                            $(e).find('td').eq(4).text(e2.zwid);
                        }
                    });
                });
            }
            $('.htmlBody1').hide();
            $('.htmlBody2').show();
        },
        error: erryFunction
    });
}

//日期转星期
function getWeek(dateString){
    var date;
    if(dateString == ''){
        date = '';
    }else{
        var dateArray = dateString.split("-");
        date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
    }
    //var weeks = new Array("日", "一", "二", "三", "四", "五", "六");
    //return "星期" + weeks[date.getDay()];
    return "星期" + "日一二三四五六".charAt(date.getDay());
}

//导出excel
function export_data(){
    var name = "值班表";
    var title = "日期,星期,带班,上午,下午,中午晚上";
    var data = datas();

    $('#export_name').val(name);
    $('#export_title').val(title);
    $('#export_data').val(data);
    $('#export_form').submit();
}
//导出数据
function datas(){
    var data = "";
    $('#table_data').find('tr').each(function(i,e){
        data += $(e).find('td:eq(5)').text()+","
            + ($(e).find('td:eq(6)').text() == "" ? "[" : $(e).find('td:eq(6)').text())+","
            + ($(e).find('td:eq(0)').text() == "" ? "[" : $(e).find('td:eq(0)').text())+","
            + ($(e).find('td:eq(1)').text() == "" ? "[" : $(e).find('td:eq(1)').text())+","
            + ($(e).find('td:eq(2)').text() == "" ? "[" : $(e).find('td:eq(2)').text())+","
            + ($(e).find('td:eq(3)').text() == "" ? "[" : $(e).find('td:eq(3)').text())+"]";
    });
    return data;
}

//    error
function erryFunction() {
    $('.htmlBody1').hide();
    $('.htmlBody2').show();
    alert("错误!");
};