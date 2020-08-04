const email = document.getElementById('email');
const password = document.getElementById('password');
const username = document.getElementById('username');
const form = document.getElementById('register-form');
const EmailError = document.getElementById('email-error');
const PasswordError = document.getElementById('password-error');
const rePassword = document.getElementById('repassword');
const RePasswordError = document.getElementById('repassword-error');
const UsernameError = document.getElementById('username-error');

form.addEventListener('submit', (e) => {
    let Emailmessages = [];
    let Passwordmessages = [];
    let Repasswordmessages = [];
    let Usernamemessage = [];

    if (email.value === '' || email.value === null) {
        Emailmessages.push('Email is required')
    } else if (!validateEmail(email.value)) {
        Emailmessages.push('Invalid email format')
    }
    if (username.value === '' || username.value === null) {
        Usernamemessage.push('Username is required')
    }
    if (password.value === '' || password.value === null) {
        Passwordmessages.push('Password is required')
    }else if( password.value.length < 6){
        Passwordmessages.push('Invalid password')
    }

    if (rePassword.value === '' || rePassword.value === null) {
        Repasswordmessages.push('Please Retype password')
    }else if(!matchPasswords(password.value , rePassword.value)){
        Repasswordmessages.push('Password does not match')
    }

    if (Emailmessages.length > 0 || Passwordmessages.length > 0) {
        e.preventDefault();
        EmailError.innerText = Emailmessages.join(',');
        RePasswordError.innerText = Repasswordmessages.join(',');
        PasswordError.innerText = Passwordmessages.join(',');
        UsernameError.innerText = Usernamemessage.join(',');
    }

})

function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(re.test(String(email).toLowerCase()));
    return re.test(String(email).toLowerCase());
}

function matchPasswords(p, r) {
    if(p != r){
        return false;
    }else{
        return true;
    }
}