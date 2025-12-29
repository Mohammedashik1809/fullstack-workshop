let score = 0;

let q1 = prompt("1. What is 5 + 3?");
if (q1 === "8") {
  score++;
}

let q2 = prompt("What is the capital of France?");
if (q2.toLowerCase() == "Paris"){
    score++
}

let q3 = prompt("what colour is a sky?");
if (q3.toLowerCase() == "Blue"){
    score++;
}

alert("Quiz completed\nYour final score is: " + score + " / 3");


