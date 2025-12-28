const images = [
  { thumb: 'https://picsum.photos/id/1015/300/200', full: 'https://picsum.photos/id/1015/1200/800', caption: 'Sunset' },
  { thumb: 'https://picsum.photos/id/1016/300/200', full: 'https://picsum.photos/id/1016/1200/800', caption: 'Mountains' },
  { thumb: 'https://picsum.photos/id/1025/300/200', full: 'https://picsum.photos/id/1025/1200/800', caption: 'Dog' },
  { thumb: 'https://picsum.photos/id/1035/300/200', full: 'https://picsum.photos/id/1035/1200/800', caption: 'Forest' },
  { thumb: 'https://picsum.photos/id/1043/300/200', full: 'https://picsum.photos/id/1043/1200/800', caption: 'Ocean' }
];

const gallery = document.getElementById('gallery');
const lightbox = document.getElementById('lightbox');
const lightboxImage = document.getElementById('lightboxImage');
const caption = document.getElementById('caption');
const counter = document.getElementById('counter');

const closeBtn = document.getElementById('closeBtn');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');

let currentIndex = 0;

/* ===== Render Thumbnails ===== */
images.forEach((img, index) => {
  const image = document.createElement('img');
  image.src = img.thumb;
  image.alt = img.caption;
  image.loading = 'lazy';

  image.addEventListener('click', () => openLightbox(index));
  gallery.appendChild(image);
});

/* ===== Lightbox Functions ===== */
function openLightbox(index) {
  currentIndex = index;
  updateLightbox();
  lightbox.classList.add('active');
  document.body.style.overflow = 'hidden';
}

function closeLightbox() {
  lightbox.classList.remove('active');
  document.body.style.overflow = '';
}

function updateLightbox() {
  const img = images[currentIndex];
  lightboxImage.src = img.full;
  lightboxImage.alt = img.caption;
  caption.textContent = img.caption;
  counter.textContent = `${currentIndex + 1} of ${images.length}`;
}

function showNext() {
  currentIndex = (currentIndex + 1) % images.length;
  updateLightbox();
}

function showPrev() {
  currentIndex = (currentIndex - 1 + images.length) % images.length;
  updateLightbox();
}

/* ===== Event Listeners ===== */
closeBtn.addEventListener('click', closeLightbox);
nextBtn.addEventListener('click', showNext);
prevBtn.addEventListener('click', showPrev);

lightbox.addEventListener('click', (e) => {
  if (e.target === lightbox) closeLightbox();
});

/* ===== Keyboard Navigation ===== */
document.addEventListener('keydown', (e) => {
  if (!lightbox.classList.contains('active')) return;

  if (e.key === 'Escape') closeLightbox();
  if (e.key === 'ArrowRight') showNext();
  if (e.key === 'ArrowLeft') showPrev();
});
