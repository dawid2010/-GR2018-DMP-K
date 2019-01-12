$('#username').focus(function () {
    $("#username-label").css('visibility', 'visible');
    $("#username-label").animate({opacity: 0}, 0);
    if($("#username-label").hasClass('non-active')){
        $("#username-label").animate({padding: '10px'}, { duration: 200, queue: false });
        $("#username-label").animate({opacity: 1}, { duration: 250, queue: false });
        $("#username-label").removeClass('non-active');
        $("#username-label").addClass('active');
    }
});
$(document).click(function(e) {
    if(!$(e.target).is('#username') && $("#username-label").hasClass('active')){
        $("#username-label").animate({padding: '0px'}, { duration: 200, queue: false });
        $("#username-label").animate({opacity: 0}, {duration: 300, queue: false});
        $("#username-label").removeClass('active');
        $("#username-label").addClass('non-active');
    }
});
$('#password').focus(function () {
    $("#password-label").css('visibility', 'visible');
    $("#password-label").animate({opacity: 0}, 0);
    if($("#password-label").hasClass('non-active')){
        $("#password-label").animate({padding: '10px'}, { duration: 200, queue: false });
        $("#password-label").animate({opacity: 1}, { duration: 250, queue: false });
        $("#password-label").removeClass('non-active');
        $("#password-label").addClass('active');
    }
});
$(document).click(function(e) {
    if(!$(e.target).is('#password') && $("#password-label").hasClass('active')){
        $("#password-label").animate({padding: '0px'}, { duration: 200, queue: false });
        $("#password-label").animate({opacity: 0}, { duration: 300, queue: false });
        $("#password-label").removeClass('active');
        $("#password-label").addClass('non-active');
    }
});
$('.button-on').click(function () {
    $(this).hide();
    $(".loader-off").show();
});