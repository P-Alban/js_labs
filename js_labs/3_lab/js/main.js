// task 1
let head = document.getElementsByTagName('head')[0].innerHTML;
alert(head);

// task 2
let users_div = document.getElementById('list-header');
users_div.innerText += '10';
users_div.style.fontWeight = 'bold';

//task 3
let items = document.getElementsByTagName('li');
items[1].innerText = 'Ярослав Панькович';

//task 4
for (let i = 0; i < items.length; i++) {
    items[i].style.color = '#555';
}
