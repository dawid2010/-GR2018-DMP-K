var firstVisit = true;
$('#return-to-top').animate({opacity: 0}, 0);
$(window).scroll(function() {
//    var width = screen.width;
//    var height = screen.height;
//    var scroll = $(window).scrollTop();
//    if (width >= 1600 && window.innerWidth == screen.width) {
        if(firstVisit){
            firstVisit = false;
        } else {
            if(($('#return-to-top').css('opacity') == 0) || ($('#return-to-top').css('opacity') == 1)){
                if($(this).scrollTop() >= 200) {
                    $('#return-to-top').css("height", "50px");
                    $('#return-to-top').css("width", "50px");
                    $('#return-to-top').animate({opacity: 1}, 15);
                } else {
                    $('#return-to-top').css("height", "50px");
                    $('#return-to-top').css("width", "50px");
                    $('#return-to-top').animate({opacity: 0}, 15);
                }
            }
        }
//    } else {
//        $('#return-to-top').css("height", "50px");
//        $('#return-to-top').css("width", "50px");
//        $('#return-to-top').animate({opacity: 0}, 15);
//    }
});

$(window).resize(function(){
    var width = screen.width;
    var height = screen.height;
    var scroll = $(window).scrollTop();
    if (!(width >= 1600 && window.innerWidth == screen.width)) {
        $('#return-to-top').css("height", "50px");
        $('#return-to-top').css("width", "50px");
        $('#return-to-top').animate({opacity: 0}, 15);
    }
});

$('#return-to-top').click(function() {
    $('body,html').animate({
        scrollTop : 0
    }, 500);
});
