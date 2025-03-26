const images = [
  '/images/20220818_155357.jpg',
  '/images/interior_img21.jpg'
];

let index = 0;
const background = document.getElementById('background');

function changeBackground() {
  // 페이드아웃
  background.style.opacity = 0;

  setTimeout(() => {
    background.style.backgroundImage = `url('${images[index]}')`;
    background.style.opacity = 1;
    index = (index + 1) % images.length;
  }, 1000); // 페이드아웃 완료 후 이미지 변경 (1초 간격)
}

// 초기에 설정
background.style.backgroundImage = `url('${images[index]}')`;
background.style.opacity = 1;
index = (index + 1) % images.length;

// 5초마다 전환
setInterval(changeBackground, 5000);