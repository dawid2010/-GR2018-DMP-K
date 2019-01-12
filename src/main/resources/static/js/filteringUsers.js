$(document).ready(function() {
    if(($('.paginated-filter-main').length == 0) && ($('#inputFilter').val() == '')){
        $('#no-people-div').show();
    }
	$.ajax({
		type : "GET",
		url : "/user/all/" + getPage(),
		success: function(data){
		    $.getJSON('/api/user/all', function(data) {
		        var json = '[' + data + ']';
                data = JSON.parse(json);
                var userItem = '';
		userItem += '<div class="not-paginated-users">';
                $.each(data, function(index, user) {
                var currentData = data[index];
                var modulesString = "";

		userItem += '<div class="filter-main">';
                    userItem += '<div id="not-paginated-users-row" class="row table-row border rounded border-secondary noselect text-center p-2 mb-3 shadow-sm text-lg-center" ';
                    userItem += 'onclick="' + "javascript:rowClicked('/user/" + currentData.id + "/profile/administrate/" + getPage() + "');" + '">';
                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">Signum</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-1 col-md-12 col-sm-12 text-md-center text-sm-center to-filter">'+currentData.login+'</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">First name</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-2 col-md-12 col-sm-12 text-md-center text-sm-center to-filter">'+currentData.firstName+'</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">Last name</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-1 col-md-12 col-sm-12 text-md-center text-sm-center to-filter">'+currentData.lastName+'</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">E-mail address</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-3 col-md-12 col-sm-12 pl-4 text-md-center text-sm-center to-filter">'+currentData.email+'</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">Account status</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-1 col-md-12 col-sm-12 text-md-center text-sm-center to-filter">';

                        if(currentData.accountStatus == 'true'){
                            userItem += '<i class="fas fa-lock-open" rel="tooltip" data-placement="bottom" title="Activated"></i>';
                        }
                        else{
                            userItem += '<i class="fas fa-lock" rel="tooltip" data-placement="bottom" title="Not activated"></i>';
                        }
                        userItem += '</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">Registration date</div>';
                        userItem += '</div>';
                        userItem += '<div class="col-lg-2 col-md-12 col-sm-12 text-md-center text-sm-center to-filter">'+currentData.registrationDate+'</div>';

                        userItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        userItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            userItem += '<div class="font-weight-bold text-md-center text-sm-center">Access module</div>';
                        userItem += '</div>';

                        userItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto mx-auto text-md-center text-sm-center">';
                            userItem += '<div class="row justify-content-center justify-content-lg-start">';
                                    $.each(currentData.accessModules, function (index, value) {
                                        userItem += '<div class="col-4">';
                                            switch(value){
                                                case 'ROLE_USER':
                                                    userItem += '<i class="fas fa-user"';
                                                        userItem += ' rel="tooltip"';
                                                        userItem += ' data-placement="bottom"';
                                                        userItem += ' title="User"></i>';
                                                    break;

                                                case 'ROLE_COACH':
                                                    userItem += '<i class="fas fa-chalkboard-teacher"';
                                                        userItem += ' rel="tooltip"';
                                                        userItem += ' data-placement="bottom"';
                                                        userItem += ' title="Coach"></i>';
                                                    break;
                                                case 'ROLE_ADMIN':
                                                    userItem += '<i class="fas fa-user-cog"';
                                                        userItem += ' rel="tooltip"';
                                                        userItem += ' data-placement="bottom"';
                                                        userItem += ' title="Administrator"></i>';
                                                    break;
                                            }
                                        userItem += '</div>';
                                    });
                            userItem += '</div>';
                        userItem += '</div>';

                    userItem += '</div>';
                userItem += '</div>';
                });
		        userItem += '</div>';
                $('#not-paginated-users').replaceWith(userItem);
                $(".not-paginated-users").hide();
            });
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});


	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    if(inputValue != ''){
            $(".paginated-users").hide();
            $(".not-paginated-users").show();
            $("[rel='tooltip']").tooltip();
            $("#editProfileSuccessfully").hide();
        }
	    $(".filter-main").filter(function() {
	    	$(this).toggle($(this).find('.to-filter').text().toLowerCase().indexOf(inputValue) > -1)
	    });

	    var anyDisplayed = false;
        $('.filter-main').each(function() {
            if($(this).css('display') != 'none'){
                console.log('true');
                anyDisplayed = true;
            }
        });
        if(inputValue == '' || anyDisplayed){
            $('#no-people-div').hide();
            $('#pagination-nav').show();
        } else if((inputValue != '' && !anyDisplayed)){
            $('#no-people-div').show();
            $('#pagination-nav').hide();
        }


        if(($('.paginated-filter-main').length == 0) && (inputValue == '')){
            $('#no-people-div').show();
            $('#pagination-nav').hide();
        }


        if(inputValue == ''){
            $(".paginated-users").show();
            $(".not-paginated-users").hide();
        }
	});


})