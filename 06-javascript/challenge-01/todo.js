let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
let currentFilter = "All";

const taskList = document.getElementById("taskList");
const counts = document.getElementById("counts");

// Add Task
function addTask() {
  const text = document.getElementById("taskInput").value.trim();
  const category = document.getElementById("categorySelect").value;

  if (text === "") return;

  tasks.push({
    id: Date.now(),
    text,
    category,
    completed: false
  });

  document.getElementById("taskInput").value = "";
  saveAndRender();
}

// Toggle Complete
function toggleTask(id) {
  tasks = tasks.map(task =>
    task.id === id ? { ...task, completed: !task.completed } : task
  );
  saveAndRender();
}

// Delete Task
function deleteTask(id) {
  tasks = tasks.filter(task => task.id !== id);
  saveAndRender();
}

// Filter Tasks
function filterTasks(category) {
  currentFilter = category;
  renderTasks();
}

// Save + Render
function saveAndRender() {
  localStorage.setItem("tasks", JSON.stringify(tasks));
  renderTasks();
}

// Render Tasks
function renderTasks() {
  taskList.innerHTML = "";

  const filtered =
    currentFilter === "All"
      ? tasks
      : tasks.filter(task => task.category === currentFilter);

  filtered.forEach(task => {
    const li = document.createElement("li");
    if (task.completed) li.classList.add("completed");

    li.innerHTML = `
      <div class="task-left">
        <input type="checkbox" ${task.completed ? "checked" : ""}
          onchange="toggleTask(${task.id})">
        <span class="task-text">${task.text}</span>
        <span class="category">${task.category}</span>
      </div>
      <span class="delete" onclick="deleteTask(${task.id})">✖</span>
    `;

    taskList.appendChild(li);
  });

  updateCounts();
}

// Show Task Counts
function updateCounts() {
  const work = tasks.filter(t => t.category === "Work").length;
  const personal = tasks.filter(t => t.category === "Personal").length;

  counts.textContent = `Work: ${work} | Personal: ${personal} | Total: ${tasks.length}`;
}

// Initial render
renderTasks();
