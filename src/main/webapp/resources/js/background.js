document.addEventListener('DOMContentLoaded', function () {
  const images = [
    '/images/20220818_155357.jpg',
    '/images/interior_img21.jpg'
  ];

  let currentIndex = 0;
  const slideImg = document.getElementById('slide-img');

  setInterval(() => {
    // 먼저 opacity를 0으로 줄여서 사라지게
    slideImg.style.opacity = 0;

    // 1초 후 이미지 바꾸고 다시 나타나게
    setTimeout(() => {
      currentIndex = (currentIndex + 1) % images.length;
      slideImg.src = images[currentIndex];
      slideImg.style.opacity = 1;
    }, 1000);
  }, 5000); // 5초마다
});