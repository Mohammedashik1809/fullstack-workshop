const username = document.getElementById("username");
const email = document.getElementById("email");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");
const submitBtn = document.getElementById("submitBtn");

const fields = [username, email, password, confirmPassword];

// Regex patterns
const usernameRegex = /^[a-zA-Z0-9]{3,15}$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

// Validate Username
function validateUsername() {
  if (!usernameRegex.test(username.value)) {
    showError(username, "Username must be 3-15 alphanumeric characters");
    return false;
  }
  showSuccess(username);
  return true;
}

// Validate Email
function validateEmail() {
  if (!emailRegex.test(email.value)) {
    showError(email, "Enter a valid email address");
    return false;
  }
  showSuccess(email);
  return true;
}

// Validate Password
function validatePassword() {
  if (!passwordRegex.test(password.value)) {
    showError(
      password,
      "Password must be 8+ chars, 1 uppercase, 1 number, 1 special char"
    );
    return false;
  }
  showSuccess(password);
  return true;
}

// Validate Confirm Password
function validateConfirmPassword() {
  if (confirmPassword.value !== password.value || confirmPassword.value === "") {
    showError(confirmPassword, "Passwords do not match");
    return false;
  }
  showSuccess(confirmPassword);
  return true;
}

// Helper functions
function showError(input, message) {
  const error = document.getElementById(input.id + "Error");
  const check = input.parentElement.querySelector(".check");

  input.classList.add("invalid");
  input.classList.remove("valid");
  error.textContent = message;
  check.style.display = "none";
}

function showSuccess(input) {
  const error = document.getElementById(input.id + "Error");
  const check = input.parentElement.querySelector(".check");

  input.classList.add("valid");
  input.classList.remove("invalid");
  error.textContent = "";
  check.style.display = "inline";
}

// Enable submit only if all valid
function checkFormValidity() {
  const isValid =
    validateUsername() &&
    validateEmail() &&
    validatePassword() &&
    validateConfirmPassword();

  submitBtn.disabled = !isValid;
  submitBtn.classList.toggle("enabled", isValid);
}

// Blur validation
username.addEventListener("blur", () => {
  validateUsername();
  checkFormValidity();
});

email.addEventListener("blur", () => {
  validateEmail();
  checkFormValidity();
});

password.addEventListener("blur", () => {
  validatePassword();
  checkFormValidity();
});

confirmPassword.addEventListener("blur", () => {
  validateConfirmPassword();
  checkFormValidity();
});

// Prevent submission if invalid
document.getElementById("registerForm").addEventListener("submit", function (e) {
  e.preventDefault();
});
