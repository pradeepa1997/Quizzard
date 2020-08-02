const email = document.getElementById('email');
const password = document.getElementById('password');
const form = document.getElementById('login-form');
const EmailError = document.getElementById('email-error');
const PasswordError = document.getElementById('password-error')

form.addEventListener('submit', (e) => {
    let Emailmessages = [];
    let Passwordmessages = [];

    if (email.value === '' || email.value === null) {
        Emailmessages.push('Email is required')
    } else if (!validateEmail(email.value)) {
        Emailmessages.push('Invalid email format')
    }
    if (password.value === '' || password.value === null) {
        Passwordmessages.push('Password is required')
    }else if( password.value.length < 6){
        Passwordmessages.push('Invalid password')
    }

    if (Emailmessages.length > 0 || Passwordmessages.length > 0) {
        e.preventDefault();
        EmailError.innerText = Emailmessages.join(',');
        PasswordError.innerText = Passwordmessages.join(',');
    }

})

function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(re.test(String(email).toLowerCase()));
    return re.test(String(email).toLowerCase());
}