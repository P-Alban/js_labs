$(document).ready(function(){
    // task 1
    alert($('head')[0].outerHTML);

    // task 2
    let items = $("ul *");
    items.css('color', '#555');

    // task 3
    let user = $('#list-header');
    user.css('font-weight', 'bold');
    user.append('10');

    // task 4
    $('ul').children('li').eq(1).text('Панькович Ярослав')
});

