const NAMES = 'áéíóúÁÉÍÓÚabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ. ';
const CURP = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
const DIGITS = '0123456789';
const EMAIL = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._';
const TEXT = '0123456789áéíóúÁÉÍÓÚabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ.,;()\'\'- #/';
const DECIMALS = '0123456789.';
const DATES = '0123456789-';
let API_KEY = 'https://api.valida-curp.com.mx/curp/obtener_datos/?token=pruebas&curp='

setInputs();

let counter = 0;

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
            if (value.length === 18 && counter === 0) {
                validateCURP(value);
                counter++;
            } else if (value.length < 18) {
                counter = 0;
            }
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

function validateCURP(value) {
    fetch(`${API_KEY}${value}`)
        .then(response => response.json())
        .then(data => {
            fillInputs(data)
        })
        .catch(err => console.error(err))
}


function fillInputs({response}) {
    const {Solicitante: {ApellidoMaterno, ApellidoPaterno, Nombres, FechaNacimiento, ClaveSexo, Nacionalidad}} = response
    document.querySelector('#name').value = Nombres;
    document.querySelector('#firstName').value = ApellidoPaterno;
    document.querySelector('#secondName').value = ApellidoMaterno;
    document.querySelector('#birthday').value = formatDate(FechaNacimiento);
    document.querySelector('#gender').value = ClaveSexo === 'H' ? 'Masculino' : 'Femenino';
    document.querySelector('#region').value = Nacionalidad === 'MEX' ? 'México' : '';
}

function formatDate(FechaNacimiento) {
    let sppliter = FechaNacimiento.split('/');
    return `${sppliter[2]}-${sppliter[1]}-${sppliter[0]}`
}
