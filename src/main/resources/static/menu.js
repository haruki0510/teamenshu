// menu.js
document.addEventListener('DOMContentLoaded', function() {
    const menuIcon = document.getElementById('menu-icon');
    const sideNav = document.getElementById('side-nav');
    const closeBtn = document.getElementById('close-btn');

    menuIcon.addEventListener('click', function() {
        console.log('Menu icon clicked');
        sideNav.style.width = '250px';
    });

    closeBtn.addEventListener('click', function() {
        console.log('Close button clicked');
        sideNav.style.width = '0';
    });

    // サイドナビゲーションのリンクをクリックしてもメニューを閉じる
    sideNav.querySelectorAll('a').forEach(function(link) {
        link.addEventListener('click', function() {
            sideNav.style.width = '0';
        });
    });
});
