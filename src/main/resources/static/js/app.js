const isWorkingBtnTrue = document.querySelector("#isWorkingBtnTrue") || document.createElement('a');
const isWorkingBtnFalse = document.querySelector("#isWorkingBtnFalse") || document.createElement('a');

isWorkingBtnTrue.addEventListener("click", () => {
    let temporalyWork = document.querySelector("#temporalyWork")
    temporalyWork.style.display = 'block';
})


isWorkingBtnFalse.addEventListener("click", () => {
    let temporalyWork = document.querySelector("#temporalyWork")
    temporalyWork.style.display = 'none';
})


function deleteRow(e) {
    let confirmation = confirm('¿Deseas eliminar este registro?')
    if (confirmation) {
        document.querySelector(`form[id$='${e.target.id}']`).submit();
    }
}

