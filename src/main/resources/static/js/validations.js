const NAMES = 'áéíóúÁÉÍÓÚabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ. ';
const CURP = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
const DIGITS = '0123456789';
const EMAIL = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._';
const TEXT = '0123456789áéíóúÁÉÍÓÚabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ.,;()\'\'- #/';
const DECIMALS = '0123456789.';
const DATES = '0123456789-';

setInputs();

function setInputs() {
    let attribute = ['onkeyup', 'onkeypress', 'onkeydown'];
    let inputs = document.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++)
        for (let j = 0; j < attribute.length; j++)
            inputs[i].setAttribute(attribute[j], 'this.value = remove(this.value, this.name)');
}

function remove(value, inputName) {
    let VALIDATE_TO = '';
    let output = '';
    switch (inputName) {
        case 'name':
        case 'firstName':
        case 'secondName':
        case 'city':
            VALIDATE_TO = NAMES;
            break;
        case 'curp':
            value = value.toUpperCase();
            VALIDATE_TO = CURP;
            break;
        case 'birthday':
            VALIDATE_TO = DATES;
            break;
        case 'age':
        case 'phone':
        case 'zip_code':
            VALIDATE_TO = DIGITS;
            break;
        case 'address':
            VALIDATE_TO = TEXT;
            break;
        case 'email':
            VALIDATE_TO = EMAIL;
            break;
        case 'salary':
            VALIDATE_TO = DECIMALS;
            break;
    }

    for (let i = 0; i < value.length; i++)
        if (VALIDATE_TO.indexOf(value.charAt(i)) != -1)
            output += value.charAt(i);

    return output;
}
