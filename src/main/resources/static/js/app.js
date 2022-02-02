const isWorkingBtnTrue = document.querySelector("#isWorkingBtnTrue");
const isWorkingBtnFalse = document.querySelector("#isWorkingBtnFalse");

isWorkingBtnTrue.addEventListener("click", () => {
    let temporalyWork = document.querySelector("#temporalyWork")
    temporalyWork.style.display = 'block';
})


isWorkingBtnFalse.addEventListener("click", () => {
    let temporalyWork = document.querySelector("#temporalyWork")
    temporalyWork.style.display = 'none';
})


