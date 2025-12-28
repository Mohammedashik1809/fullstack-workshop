let count = 0;
let step = 1;

const countDisplay = document.getElementById("count");
const stepButtons = document.querySelectorAll(".steps button");

function updateDisplay() {
  countDisplay.textContent = count;

  if (count > 0) {
    countDisplay.style.color = "green";
  } else if (count < 0) {
    countDisplay.style.color = "red";
  } else {
    countDisplay.style.color = "black";
  }
}

function increment() {
  count += step;
  updateDisplay();
}

function decrement() {
  if (count - step >= 0) {
    count -= step;
  }
  updateDisplay();
}

function reset() {
  count = 0;
  updateDisplay();
}

function setStep(value) {
  step = value;

  stepButtons.forEach(btn => btn.classList.remove("active"));
  event.target.classList.add("active");
}
