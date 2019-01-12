$(document).ready(function() {
    if(($('.paginated-filter-main').length == 0) && ($('#inputFilter').val() == '')){
        $('#no-trainings-div').show();
    }
	$.ajax({
		type : "GET",
		url : "/training/usable/all/" + getPage(),
		success: function(data){
		    $.getJSON('/api/training/usable/all', function(data) {
		        var json = '[' + data + ']';
                data = JSON.parse(json);
                var usableTrainingItem = '';
                usableTrainingItem += '<div class="not-paginated-usable-trainings">';
                $.each(data, function(index, usableTraining) {
                    var currentData = data[index];
                    usableTrainingItem += '<div class="filter-main">';
                    if(checkIfAdmin() == true){
                            usableTrainingItem += '<div class="text-lg-center row table-row border rounded border-secondary noselect text-center py-2 px-2 mb-3 shadow-sm"';
                            usableTrainingItem += 'onclick="' + "javascript:rowClicked('/training/usable/" + currentData.id + "');" + '">';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Name</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.name + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Type</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.type + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Location</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.location + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Start date</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.startDate + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">End date</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.endDate + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Minimum</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.lowerBand + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Maximum</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.upperBand + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Counter</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.participantsCount + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Status</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.status + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Coaches</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center">';
                                    usableTrainingItem +=  '<ul class="list-group">';
                                        $.each(currentData.coaches, function (index, value) {
                                            usableTrainingItem +=  '<li style="list-style: none;" class="to-filter">' + value + '</li>';
                                        });
                                    usableTrainingItem +=  '</ul>';
                                usableTrainingItem +=  '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center"></div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">';
                                    usableTrainingItem +=  '<div class="list-group">';
                                        if(currentData.status == 'Upcoming' || currentData.status == 'Ongoing'){
                                            usableTrainingItem += '<a href="/training/usable/' + currentData.id + '/edit/' + getPage() + '">';
                                                usableTrainingItem += '<button class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Edit">';
                                                    usableTrainingItem += '<i class="far fa-edit"></i>';
                                                usableTrainingItem += '</button>';
                                            usableTrainingItem += '</a>';
                                        }
                                        if(currentData.status != 'Rejected'){
                                            usableTrainingItem += '<form action="/training/usable/' + currentData.id + '/reject/' + getPage() + '" method="POST">';
                                                usableTrainingItem += '<button type="submit" class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Reject">';
                                                    usableTrainingItem += '<i class="fas fa-ban"></i>';
                                                usableTrainingItem += '</button>';
                                            usableTrainingItem += '</form>';
                                        }
                                        if(currentData.status == 'Rejected'){
                                            usableTrainingItem += '<a href="/training/usable/' + currentData.id + '/restore/' + getPage() + '">';
                                                usableTrainingItem += '<button class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Restore">';
                                                    usableTrainingItem += '<i class="far fa-arrow-alt-circle-left fa-1x"></i>';
                                                usableTrainingItem += '</button>';
                                            usableTrainingItem += '</a>';
                                        }

                                        if(parseInt(currentData.participantsCount) >= parseInt(currentData.upperBand)){
                                            if(currentData.isParticipantOfThisTraining == 'false' && currentData.isInHopeListOfTraining == 'false'){
                                                usableTrainingItem += '<form method="POST" action="/training/usable/' + currentData.id + '/expressWillingnessToParticipateInTraining">';
                                                    usableTrainingItem += '<button class="btn btn-outline-dark btn-sm my-1 table-button" ';
                                                        usableTrainingItem += 'type="submit" ';
                                                        usableTrainingItem += 'rel="tooltip" ';
                                                        usableTrainingItem += 'data-placement="right" ';
                                                        usableTrainingItem += 'title="Express willingness to participate">';
                                                        usableTrainingItem += '<i class="fab fa-telegram-plane"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                            if(currentData.isInHopeListOfTraining == 'true'){
                                                usableTrainingItem += '<form>';
                                                    usableTrainingItem += '<button class="btn btn-dark btn-sm my-1 table-button" ';
                                                        usableTrainingItem += 'type="button" ';
                                                        usableTrainingItem += 'rel="tooltip" ';
                                                        usableTrainingItem += 'data-placement="right" ';
                                                        usableTrainingItem += 'title="You have already expressed your willingness">';
                                                        usableTrainingItem += '<i class="fab fa-telegram-plane"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                        }

                                        if(currentData.isCoachOfThisTraining == "false" && currentData.status == "Upcoming"){
                                            if(currentData.participantsCount < currentData.upperBand && currentData.isParticipantOfThisTraining == "false"){
                                                usableTrainingItem += '<form action="/training/usable/' + currentData.id + '/signUp" method="POST">';
                                                    usableTrainingItem += '<button type="submit" class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Sign Up">';
                                                        usableTrainingItem += '<i class="fas fa-user-plus"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                            if(currentData.isParticipantOfThisTraining == "true"){
                                                usableTrainingItem += '<form action="/training/usable/' + currentData.id + '/signOff" method="POST">';
                                                    usableTrainingItem += '<button type="submit" class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Sign Off">';
                                                        usableTrainingItem += '<i class="fas fa-user-minus"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                        }

                                    usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '</div>';

                            usableTrainingItem +=  '</div>';
                        usableTrainingItem +=  '</div>';

                    }
                    if(checkIfAdmin() == false){
                        if(currentData.status == "Upcoming" || currentData.isCoachOfThisTraining == "true"){
                            usableTrainingItem += '<div class="text-lg-center row table-row border rounded border-secondary noselect text-center py-2 px-2 mb-3 shadow-sm"';
                            usableTrainingItem += 'onclick="' + "javascript:rowClicked('/training/usable/" + currentData.id + "');" + '">';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Name</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-3 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.name + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Type</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.type + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Location</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.location + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Start date</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.startDate + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">End date</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.endDate + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Status</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">' + currentData.status + '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center">Coaches</div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center">';
                                    usableTrainingItem +=  '<ul class="list-group">';
                                        $.each(currentData.coaches, function (index, value) {
                                            usableTrainingItem +=  '<li style="list-style: none;" class="to-filter">' + value + '</li>';
                                        });
                                    usableTrainingItem +=  '</ul>';
                                usableTrainingItem +=  '</div>';

                                usableTrainingItem +=  '<div class="w-100 my-1 d-lg-none"></div>';

                                usableTrainingItem +=  '<div class="col-md-12 col-sm-12 d-lg-none">';
                                    usableTrainingItem +=  '<div class="font-weight-bold text-md-center text-sm-center"></div>';
                                usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center">';
                                    usableTrainingItem +=  '<div class="list-group">';
                                        if(currentData.isCoachOfThisTraining == "false" && currentData.status == "Upcoming"){
                                            if(currentData.participantsCount < currentData.upperBand && currentData.isParticipantOfThisTraining == "false"){
                                                usableTrainingItem += '<form action="/training/usable/' + currentData.id + '/signUp" method="POST">';
                                                    usableTrainingItem += '<button type="submit" class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Sign Up">';
                                                        usableTrainingItem += '<i class="fas fa-user-plus"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                            if(currentData.isParticipantOfThisTraining == "true"){
                                                usableTrainingItem += '<form action="/training/usable/' + currentData.id + '/signOff" method="POST">';
                                                    usableTrainingItem += '<button type="submit" class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Sign Off">';
                                                        usableTrainingItem += '<i class="fas fa-user-minus"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                        }

                                        if(parseInt(currentData.participantsCount) >= parseInt(currentData.upperBand)){
                                            if(!(currentData.isParticipantOfThisTraining == 'true') && !(currentData.isInHopeListOfTraining == 'true')){
                                                usableTrainingItem += '<form method="POST" action="/training/usable/' + currentData.id + '/expressWillingnessToParticipateInTraining">';
                                                    usableTrainingItem += '<button class="btn btn-outline-dark btn-sm my-1 table-button" ';
                                                        usableTrainingItem += 'type="submit" ';
                                                        usableTrainingItem += 'rel="tooltip" ';
                                                        usableTrainingItem += 'data-placement="right" ';
                                                        usableTrainingItem += 'title="Express willingness to participate">';
                                                        usableTrainingItem += '<i class="fab fa-telegram-plane"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                            if(currentData.isInHopeListOfTraining == 'true'){
                                                usableTrainingItem += '<form>';
                                                    usableTrainingItem += '<button class="btn btn-dark btn-sm my-1 table-button" ';
                                                        usableTrainingItem += 'type="button" ';
                                                        usableTrainingItem += 'rel="tooltip" ';
                                                        usableTrainingItem += 'data-placement="right" ';
                                                        usableTrainingItem += 'title="You have already expressed your willingness">';
                                                        usableTrainingItem += '<i class="fab fa-telegram-plane"></i>';
                                                    usableTrainingItem += '</button>';
                                                usableTrainingItem += '</form>';
                                            }
                                        }

                                        if(currentData.status == "Rejected" && currentData.isCoachOfThisTraining == "true"){
                                            usableTrainingItem += '<a href="/training/usable/' + currentData.id + '/restore/' + getPage() + '">';
                                                usableTrainingItem += '<button class="btn btn-outline-dark btn-sm m-1 table-button" rel="tooltip" data-placement="right" title="Restore">';
                                                    usableTrainingItem += '<i class="far fa-arrow-alt-circle-left fa-1x"></i>';
                                                usableTrainingItem += '</button>';
                                            usableTrainingItem += '</a>';
                                        }

                                    usableTrainingItem +=  '</div>';
                                usableTrainingItem +=  '</div>';

                            usableTrainingItem +=  '</div>';
                            usableTrainingItem +=  '</div>';
                        }
                    }
                });
                usableTrainingItem +=  '</div>';
                $('#not-paginated-usable-trainings').replaceWith(usableTrainingItem);
                $(".not-paginated-usable-trainings").hide();
            });
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});


	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    if(inputValue != ''){
            $(".paginated-usable-trainings").hide();
            $(".not-paginated-usable-trainings").show();
            $("[rel='tooltip']").tooltip();
            $("#signUpStatus").hide();
            $("#signOffStatus").hide();
            $("#rejectStatus").hide();
            $("#restoreStatus").hide();
            $("#editionStatus").hide();
            $("#expressWillingnessStatus").hide();
        }

	    $(".filter-main").filter(function() {
	    	$(this).toggle($(this).find('.to-filter').text().toLowerCase().indexOf(inputValue) > -1)
	    });

	    var anyDisplayed = false;
	    $('.filter-main').each(function() {
            if($(this).css('display') != 'none'){
                anyDisplayed = true;
            }
        });

        if(inputValue == '' || anyDisplayed){
            $('#no-trainings-div').hide();
            $('#pagination-nav').show();
        } else if((inputValue != '' && !anyDisplayed)){
            $('#no-trainings-div').show();
            $('#pagination-nav').hide();
        }

        if(($('.paginated-filter-main').length == 0) && (inputValue == '')){
            $('#no-trainings-div').show();
            $('#pagination-nav').hide();
        }

        if(inputValue == ''){
            $(".paginated-usable-trainings").show();
            $(".not-paginated-usable-trainings").hide();
        }
	});
})