$(document).ready(function() {

    $('#edit-customer-button').click(function(){
        $('#edit-customer-form :input').prop('disabled', false);
        $('#update-customer-button').show();
        $('#cancel-edit-button').show();
        $('#edit-customer-button').hide();
    });

    $('#cancel-edit-button').click(function(){
        $('#cancel-edit-button').hide();
        $('#update-customer-button').hide();
        $('#edit-customer-form :input').prop('disabled', true);
        $('#edit-customer-button').show();
    });

});