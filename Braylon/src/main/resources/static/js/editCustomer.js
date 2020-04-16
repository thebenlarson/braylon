$(document).ready(function() {

    $('#edit-customer-button').click(function(){
        $('#new-customer-form :input').prop('disabled', false);
        $('#update-customer-button').show();
        $('#reset-customer-button').show();
    });

    $('#reset-customer-button').click(function(){
        $('#reset-customer-button').hide();
        $('#update-customer-button').hide();
        $('#new-customer-form :input').prop('disabled', true);


    });

});