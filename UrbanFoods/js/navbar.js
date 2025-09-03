document.addEventListener('DOMContentLoaded', function() {
    // Set active link based on current page
    const currentPage = window.location.pathname.split('/').pop();
    const navLinks = document.querySelectorAll('.nav-links a');
    
    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPage) {
            link.classList.add('active');
        }
    });

    // Button event listeners
    const signupBtn = document.querySelector('.btn-signup');
    const createBtn = document.querySelector('.btn-create');
    
    signupBtn.addEventListener('click', () => {
        window.location.href = 'signup.html';
    });
    
    createBtn.addEventListener('click', () => {
        window.location.href = 'create-event.html';
    });
});