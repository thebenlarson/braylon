$(document).ready(function() {

    $('#edit-customer-button').click(function(){
        $('#edit-customer-form :input').prop('disabled', false);
        $('#update-customer-button').show();
        $('#reset-customer-button').show();
        $('#edit-customer-button').hide();
    });

    $('#reset-customer-button').click(function(){
        $('#reset-customer-button').hide();
        $('#update-customer-button').hide();
        $('#new-customer-form :input').prop('disabled', true);
        $('#edit-customer-button').show();
    });

    $('#update-customer-button').click(function(){
        $('#edit-customer-button').show();
    })
});