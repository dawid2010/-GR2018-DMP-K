$("#bronze-button").click(function() {
        if ($('#all-button').hasClass('btn-outline-checked')){
            $('#all-button').removeClass("btn-outline-checked");
            $('#all-button').addClass("btn-outline-secondary");
        }
        if ($('#gold-button').hasClass('btn-outline-checked')){
            $('#gold-button').removeClass("btn-outline-checked");
            $('#gold-button').addClass("btn-outline-secondary");
        }
        if ($('#silver-button').hasClass('btn-outline-checked')){
            $('#silver-button').removeClass("btn-outline-checked");
            $('#silver-button').addClass("btn-outline-secondary");
        }
        if ($(this).hasClass('btn-outline-secondary')){
            $(this).removeClass("btn-outline-secondary");
            $(this).addClass("btn-outline-checked");
        }
        $(".table-row").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf("żywność") > -1)
        });
});

$("#silver-button").click(function() {
        if ($('#all-button').hasClass('btn-outline-checked')){
            $('#all-button').removeClass("btn-outline-checked");
            $('#all-button').addClass("btn-outline-secondary");
        }
        if ($('#gold-button').hasClass('btn-outline-checked')){
            $('#gold-button').removeClass("btn-outline-checked");
            $('#gold-button').addClass("btn-outline-secondary");
        }
        if ($('#bronze-button').hasClass('btn-outline-checked')){
            $('#bronze-button').removeClass("btn-outline-checked");
            $('#bronze-button').addClass("btn-outline-secondary");
        }
        if ($(this).hasClass('btn-outline-secondary')){
            $(this).removeClass("btn-outline-secondary");
            $(this).addClass("btn-outline-checked");
        }
        $(".table-row").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf("transport") > -1)
        });
});

$("#gold-button").click(function() {
        if ($('#all-button').hasClass('btn-outline-checked')){
            $('#all-button').removeClass("btn-outline-checked");
            $('#all-button').addClass("btn-outline-secondary");
        }
        if ($('#silver-button').hasClass('btn-outline-checked')){
            $('#silver-button').removeClass("btn-outline-checked");
            $('#silver-button').addClass("btn-outline-secondary");
        }
        if ($('#bronze-button').hasClass('btn-outline-checked')){
            $('#bronze-button').removeClass("btn-outline-checked");
            $('#bronze-button').addClass("btn-outline-secondary");
        }
        if ($(this).hasClass('btn-outline-secondary')){
            $(this).removeClass("btn-outline-secondary");
            $(this).addClass("btn-outline-checked");
        }
        $(".table-row").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf("chemia") > -1)
        });
});

$("#all-button").click(function() {
        if ($('#gold-button').hasClass('btn-outline-checked')){
            $('#gold-button').removeClass("btn-outline-checked");
            $('#gold-button').addClass("btn-outline-secondary");
        }
        if ($('#silver-button').hasClass('btn-outline-checked')){
            $('#silver-button').removeClass("btn-outline-checked");
            $('#silver-button').addClass("btn-outline-secondary");
        }
        if ($('#bronze-button').hasClass('btn-outline-checked')){
            $('#bronze-button').removeClass("btn-outline-checked");
            $('#bronze-button').addClass("btn-outline-secondary");
        }
        if ($(this).hasClass('btn-outline-secondary')){
            $(this).removeClass("btn-outline-secondary");
            $(this).addClass("btn-outline-checked");
        }
        $(".table-row").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf("") > -1)
        });
});

