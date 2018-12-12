function cube(val) {
    return Math.pow(val, 3);
}

console.log(cube(10));

function calculate(first, second, separator) {
    return (first + second) / separator;
}

console.log(calculate(1, 2, 5));

week_days = ['Понеділок', 'Вівторок', 'Середа', 'Четвер', 'Пятниця', 'Субота', 'Неділя'];

function get_week_day_by_number(number) {
    return week_days[-1 + number];
}

let lambda = function(number) {return week_days[-1 + number];};

console.log(lambda(7));
console.log(get_week_day_by_number(7));

// recursive factorial
function fact(val) {
    if (val <= 2) {return val}
    return val * fact(val-1);
}

console.log(fact(5));
