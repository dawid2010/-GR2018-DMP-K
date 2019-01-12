$(function() {
    var header = $(".navbar");

    $(window).scroll(function() {
//        var width = screen.width;
//        var height = screen.height;
        var scroll = $(window).scrollTop();
//        if (width >= 1600 && window.innerWidth == screen.width) {
            if (scroll >= 110) {
                header.removeClass("navbar-static");
                header.addClass("navbar-scrolled");
            } else {
                header.removeClass("navbar-scrolled");
                header.addClass("navbar-static");
            }
//        } else {
//             if($(".navbar").hasClass('navbar-scrolled')){
//                $(".navbar").removeClass("navbar-scrolled");
//                $(".navbar").addClass("navbar-static");
//             }
//        }
    })});


    var previousScroll = 0;
    var headerOrgOffset = 700;
    $(window).scroll(function () {
    var currentScroll = $(this).scrollTop();
    if (currentScroll > headerOrgOffset) {
        if (currentScroll > previousScroll) {
            $('.navbar').css("top", -100);
        } else {
            $('.navbar').css("top", 0);
        }
    }
    previousScroll = currentScroll;
});

//$(window).resize(function(){
//    var width = screen.width;
//    var height = screen.height;
//    var scroll = $(window).scrollTop();
//    if (!(width >= 1600 && window.innerWidth == screen.width)) {
//         if($(".navbar").hasClass('navbar-scrolled')){
//            $(".navbar").removeClass("navbar-scrolled");
//            $(".navbar").addClass("navbar-static");
//         }
//    }
//});