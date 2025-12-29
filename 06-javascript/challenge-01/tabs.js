const tabsData = [
  { title: "Overview", content: "Overview content here..." },
  { title: "Features", content: "Features content here..." },
  { title: "Pricing", content: "Pricing content here..." }
];

const tabsContainer = document.getElementById("tabs");
const contentContainer = document.getElementById("tabContent");

let activeIndex = 0;


tabsData.forEach((tab, index) => {
  const button = document.createElement("button");
  button.className = "tab";
  button.textContent = tab.title;
  button.setAttribute("role", "tab");
  button.setAttribute("tabindex", index === 0 ? "0" : "-1");

  button.addEventListener("click", () => activateTab(index));
  button.addEventListener("keydown", (e) => handleKeyNavigation(e, index));

  tabsContainer.appendChild(button);
});


function activateTab(index) {
  const tabs = document.querySelectorAll(".tab");

  tabs.forEach((tab, i) => {
    tab.classList.toggle("active", i === index);
    tab.setAttribute("tabindex", i === index ? "0" : "-1");
  });

  contentContainer.textContent = tabsData[index].content;
  activeIndex = index;
}


function handleKeyNavigation(event, index) {
  let newIndex = index;

  if (event.key === "ArrowRight") {
    newIndex = (index + 1) % tabsData.length;
  }

  if (event.key === "ArrowLeft") {
    newIndex = (index - 1 + tabsData.length) % tabsData.length;
  }

  if (newIndex !== index) {
    activateTab(newIndex);
    document.querySelectorAll(".tab")[newIndex].focus();
  }
}


activateTab(0);
