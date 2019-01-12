$(document).ready(function() {
    if(($('.paginated-filter-main').length == 0) && ($('#inputFilter').val() == '')){
        $('#no-trainings-div').show();
    }
	$.ajax({
		type : "GET",
		url : "/user/trainings/" + getPage(),
		success: function(data){
		    $.getJSON('/api/user/trainings', function(data) {
		        var json = '[' + data + ']';
                data = JSON.parse(json);
                var historyItem = '';
                historyItem += '<div class="not-paginated-user-trainings-list">';
                $.each(data, function(index, item) {
                var currentData = data[index];
                    historyItem += '<div class="filter-main">';
                    historyItem += '<div class="row table-row border rounded border-secondary noselect py-2 px-2 mt-3 shadow-sm text-lg-center" style="cursor:default;">';
                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">Name</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.name+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">Type</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.type+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">Start date</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.startDate+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">End date</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.endDate+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>'

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">Status</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.status+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center">Attendance status</div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-2 col-md-12 col-sm-12 my-auto text-md-center text-sm-center to-filter">'+currentData.attendance+'</div>';

                        historyItem += '<div class="w-100 my-1 d-lg-none"></div>';

                        historyItem += '<div class="col-md-12 col-sm-12 d-lg-none">';
                            historyItem += '<div class="font-weight-bold text-md-center text-sm-center"></div>';
                        historyItem += '</div>';
                        historyItem += '<div class="col-lg-1 col-md-12 col-sm-12 my-auto text-md-center text-sm-center">';
                            if(currentData.urlAndName != null){
                            historyItem += '<button class="btn btn-outline-dark btn-sm my-1 shadow-sm" ';
                                    historyItem += 'type="submit" ';
                                    historyItem += 'style="cursor: pointer;" ';
                                    historyItem += 'rel="tooltip" ';
                                    historyItem += 'data-placement="right" ';
                                    historyItem += 'title="Attachments" ';
                                    historyItem += 'data-toggle="collapse" ';
                                    historyItem += 'data-target="#training-id-' + currentData.id + '" ';
                                    historyItem += 'aria-expanded="false" ';
                                    historyItem += 'aria-controls="training-id-' + currentData.id + '">';
                                historyItem += '<i class="fas fa-paperclip"></i>';
                            historyItem += '</button>';
                            } else {
                                historyItem += '<button class="btn btn-dark btn-sm my-1 shadow-sm" ';
                                        historyItem += 'type="button" ';
                                        historyItem += 'style="cursor: default; visibility: hidden;" ';
                                        historyItem += 'rel="tooltip" ';
                                        historyItem += 'data-placement="right" ';
                                        historyItem += 'title="No attachments here" ';
                                        historyItem += 'data-toggle="collapse" ';
                                        historyItem += 'aria-expanded="false">';
                                    historyItem += '<i class="fas fa-paperclip"></i>';
                                historyItem += '</button>';
                            }
                        historyItem += '</div>';

                    historyItem += '</div>';

                    historyItem += '<div class="collapse" id="training-id-' + currentData.id + '">';
                        historyItem += '<div class="card card-body shadow-sm" style="background-color:#F1F1F1;">';
                            historyItem += '<div class="d-flex justify-content-around flex-wrap text-center">';
                            $.each(currentData.urlAndName, function(index, item) {
                                    historyItem += '<div class="pt-2">';
                                        historyItem += '<a target="_blank" href="' +  item.split(" ")[0] + '">';
                                            historyItem += '<img src="/svg/microsoft-sharepoint-logo.svg" width="28" height="28">';
                                            historyItem += '<p class="p-0 m-0">' + item.split(" ")[1] + '</p>';

                                        historyItem += '</a>';
                                    historyItem += '</div>';
                            });
                            historyItem += '</div>';
                        historyItem += '</div>';
                    historyItem += '</div>';
                    historyItem += '</div>';

                });
                historyItem += '</div>';
                $('#not-paginated-user-trainings-list').replaceWith(historyItem);
                $(".not-paginated-user-trainings-list").hide();
            });
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});


	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    if(inputValue != ''){
            $(".paginated-user-trainings-list").hide();
            $(".not-paginated-user-trainings-list").show();
            $("[rel='tooltip']").tooltip();
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
            $(".paginated-user-trainings-list").show();
            $(".not-paginated-user-trainings-list").hide();
        }
	});
})