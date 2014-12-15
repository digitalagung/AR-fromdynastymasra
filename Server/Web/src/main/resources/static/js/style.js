$(function() {
    $('#error_login').puigrowl();
    $('#error_login').puigrowl('show', [{severity: 'warn', summary: 'Login Failed', detail: 'Invalid Username or Password!'}]);
    $('#password').puipassword({inline: true});
});
