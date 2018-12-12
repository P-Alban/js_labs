// task 1
let name = 'Панькович Ярослав';
alert(name);

// task 2
a = 10;
b = 4;
c = a - b;
d = 7;
result = c + d;
alert(result);

// task 3
function check(value) {
    return (value % 2 === 0) ? value + 7 : value + 3
}

let numb = parseInt(prompt('Номер вашого варіанту:'));
if (numb >= 7) {
    alert('Вірно');
} else {
    alert('Невірно');
}
alert(check(numb));

// task 4
for (let i = 1; i <= a + 10; i++) {
    document.write(Math.pow(i, 2).toString() + '<br>');
}

// task 5
document.write('<h1>Prime</h1>');

function is_prime(value) {
    for (let i = 2; i < value; i++) {
        if (value % i === 0) {
            return false
        }
    }
    return true
}

for (let i = 2; i <= a + 10; i++) {
    if (is_prime(i)) {
        document.write(i.toString() + '<br>')
    }
}