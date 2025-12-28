const accordionData = [
  {
    title: 'What is JavaScript?',
    content: 'JavaScript is a programming language used to create dynamic and interactive web content.'
  },
  {
    title: 'What is the DOM?',
    content: 'The DOM (Document Object Model) represents the structure of a web page and allows JavaScript to manipulate HTML.'
  },
  {
    title: 'What are events?',
    content: 'Events are actions like clicks, key presses, or mouse movements that JavaScript can respond to.'
  }
];

const accordion = document.getElementById('accordion');
const allowMultipleOpen = false; // change to true if you want multiple open

accordionData.forEach((item, index) => {
  const accordionItem = document.createElement('div');
  accordionItem.className = 'accordion-item';

  accordionItem.innerHTML = `
    <div class="accordion-header">
      <span>${item.title}</span>
      <span class="accordion-arrow">▶</span>
    </div>
    <div class="accordion-content">
      <p>${item.content}</p>
    </div>
  `;

  accordion.appendChild(accordionItem);
});

accordion.addEventListener('click', (e) => {
  const header = e.target.closest('.accordion-header');
  if (!header) return;

  const item = header.parentElement;
  const content = item.querySelector('.accordion-content');

  if (!allowMultipleOpen) {
    document.querySelectorAll('.accordion-item').forEach(otherItem => {
      if (otherItem !== item) {
        otherItem.classList.remove('active');
        otherItem.querySelector('.accordion-content').style.height = 0;
      }
    });
  }

  if (item.classList.contains('active')) {
    content.style.height = 0;
    item.classList.remove('active');
  } else {
    content.style.height = content.scrollHeight + 'px';
    item.classList.add('active');
  }
});
