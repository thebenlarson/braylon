$(document).ready(function(){
var $tablerows = $('#visitTable tr');
$('#visitSearch').keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
    $tablerows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});
})
