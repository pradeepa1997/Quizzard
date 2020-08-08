const email = document.getElementById('email');
const form = document.getElementById('form');
const EmailError = document.getElementById('email-error');

form.addEventListener('submit', (e) => {
    let Emailmessages = [];

    if (email.value === '' || email.value === null) {
        Emailmessages.push('Email is required')
    } else if (!validateEmail(email.value)) {
        Emailmessages.push('Invalid email format')
    }

    if (Emailmessages.length > 0) {
        e.preventDefault();
        EmailError.innerText = Emailmessages.join(',');
    }

})

function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(re.test(String(email).toLowerCase()));
    return re.test(String(email).toLowerCase());
}