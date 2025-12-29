let choice = prompt(
  "Choose conversion type:\n" +
  "1. Celsius to Fahrenheit\n" +
  "2. Fahrenheit to Celsius"
);


let temp= parseFloat(prompt("Enter the temperature value:"));
if (choice === "1") {
  // Celsius to Fahrenheit
  let fahrenheit = (temp * 9 / 5) + 32;
  alert("Temperature in Fahrenheit: " + fahrenheit);
} 
else if (choice === "2") {
  // Fahrenheit to Celsius
  let celsius = (temp - 32) * 5 / 9;
  alert("Temperature in Celsius: " + celsius);
} 
else {
  alert("Invalid choice!");
}