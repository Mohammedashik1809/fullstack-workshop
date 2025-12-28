function createModal({ title, content, buttons }) {
  const overlay = document.createElement("div");
  overlay.className = "modal-overlay";

  const modal = document.createElement("div");
  modal.className = "modal";

  modal.innerHTML = `
    <div class="modal-header">
      <h3>${title}</h3>
      <button class="close-btn">&times;</button>
    </div>
    <div class="modal-body">${content}</div>
    <div class="modal-footer"></div>
  `;

  const footer = modal.querySelector(".modal-footer");

  buttons.forEach(btn => {
    const button = document.createElement("button");
    button.textContent = btn.text;
    button.className = `btn-${btn.type}`;

    button.addEventListener("click", () => {
      btn.onClick();
      close();
    });

    footer.appendChild(button);
  });

  overlay.appendChild(modal);
  document.body.appendChild(overlay);

  function open() {
    overlay.classList.add("active");
    document.body.classList.add("no-scroll");
  }

  function close() {
    overlay.classList.remove("active");
    document.body.classList.remove("no-scroll");
    setTimeout(() => overlay.remove(), 300);
  }

  // Close actions
  overlay.addEventListener("click", e => {
    if (e.target === overlay) close();
  });

  modal.querySelector(".close-btn").addEventListener("click", close);

  document.addEventListener("keydown", function escHandler(e) {
    if (e.key === "Escape") {
      close();
      document.removeEventListener("keydown", escHandler);
    }
  });

  return { open, close };
}

/* ---------- Example Usage ---------- */

document.getElementById("openModalBtn").addEventListener("click", () => {
  const modal = createModal({
    title: "Confirm Delete",
    content: "<p>Are you sure you want to delete this item?</p>",
    buttons: [
      {
        text: "Cancel",
        type: "secondary",
        onClick: () => console.log("Cancelled")
      },
      {
        text: "Delete",
        type: "danger",
        onClick: () => console.log("Item deleted")
      }
    ]
  });

  modal.open();
});
