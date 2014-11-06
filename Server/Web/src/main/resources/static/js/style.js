$(function() {
    $('#error_login').puigrowl();
    $('#error_login').puigrowl('show', [{severity: 'warn', summary: 'Login Failed', detail: 'Invalid Username or Password!'}]);
    $('#logout').puigrowl();
    $('#logout').puigrowl('show', [{severity: 'info', summary: 'Logout', detail: 'You Have Been Logged Out'}]);
    $('#password').puipassword();
});
