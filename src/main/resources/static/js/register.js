$("input").css("-webkit-box-shadow", "0 0 0px red");
$("input").css('border', '1px solid #CED4DA');
$("input").css('outline', '0px');

//$("input").on("keyup", function() {
//    var inputValue = $(this).val().toLowerCase();
//    if(inputValue != ''){
//        $(this).css('-webkit-box-shadow', '0 0 4px red');
//    }
//    if(inputValue == ''){
//        $(this).css('-webkit-box-shadow', '0 0 0px red');
//    }
//});

$("#login").on("keyup", function() {
    var inputValue = $(this).val();
    var isSignum = /^([a-z]){7}$/.test(inputValue);
    if(inputValue != ''){
        if(isSignum){
            $(this).css('-webkit-box-shadow', '0 0 4px green');
        } else {
            $(this).css('-webkit-box-shadow', '0 0 4px red');
        }
    }
    if(inputValue == ''){
        $(this).css('-webkit-box-shadow', '0 0 0px red');
    }
});

$("#password").on("keyup", function() {
    var inputValue = $(this).val();
    if(inputValue.length == 8){
        $(this).css('-webkit-box-shadow', '0 0 4px green');
    } else {
        $(this).css('-webkit-box-shadow', '0 0 4px red');
    }

    if(inputValue == ''){
        $(this).css('-webkit-box-shadow', '0 0 0px red');
    }
});

$("#match_password").on("keyup", function() {
    var inputValue = $(this).val();
    if(inputValue != '' && $("#password").val() == inputValue ){
        $(this).css('-webkit-box-shadow', '0 0 4px green');
    } else if(inputValue != '' && $("#password").val() != inputValue ){
        $(this).css('-webkit-box-shadow', '0 0 4px red');
    }

    if(inputValue == ''){
        $(this).css('-webkit-box-shadow', '0 0 0px red');
    }
});