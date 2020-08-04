const password = document.getElementById('password');
const rePassword = document.getElementById('repassword');
const form = document.getElementById('form');
const RePasswordError = document.getElementById('repassword-error');
const PasswordError = document.getElementById('password-error');

form.addEventListener('submit', (e) => {
    let Repasswordmessages = [];
    let Passwordmessages = [];

    if (password.value === '' || password.value === null) {
        Passwordmessages.push('Password is required')
    }else if( password.value.length < 6){
        Passwordmessages.push('Password must be longer than 6 characters')
    }

    if (password.value === '' || password.value === null) {
        Repasswordmessages.push('Please Retype password')
    }else if(!matchPasswords(password.value , rePassword.value)){
        Repasswordmessages.push('Password does not match')
    }

    if (Repasswordmessages.length > 0 || Passwordmessages.length > 0) {
        e.preventDefault();
        RePasswordError.innerText = Repasswordmessages.join(',');
        PasswordError.innerText = Passwordmessages.join(',');
    }

})

function matchPasswords(p, r) {
    if(p != r){
        return false;
    }else{
        return true;
    }
}