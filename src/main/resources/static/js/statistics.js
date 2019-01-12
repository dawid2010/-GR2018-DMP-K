$("#coachAndTrainingsPresence").change(function(){
    if($("#coachAndTrainingsPresence").is(':checked')){
        $("#selectCoachAndTrainingPresence").fadeIn();
    }
    else {
        $("#selectCoachAndTrainingPresence").fadeOut();
    }
});

$("#coachAndCountOfTrainingsPresence").change(function(){
    if($("#coachAndCountOfTrainingsPresence").is(':checked')){
        $("#selectCoachAndCountOfTrainingPresence").fadeIn();
    }
    else {
        $("#selectCoachAndCountOfTrainingPresence").fadeOut();
    }
});

$("#participantWithTrainingsPresence").change(function(){
    if($("#participantWithTrainingsPresence").is(':checked')){
        $("#selectParticipantWithTrainingPresence").fadeIn();
    }
    else {
        $("#selectParticipantWithTrainingPresence").fadeOut();
    }
});

$("#participantAndCountOfTrainingsPresence").change(function(){
    if($("#participantAndCountOfTrainingsPresence").is(':checked')){
        $("#selectparticipantAndCountOfTrainingPresence").fadeIn();
    }
    else {
        $("#selectparticipantAndCountOfTrainingPresence").fadeOut();
    }
});

$("#selectedCheckBoxDateFromTo").change(function(){
    if($("#selectedCheckBoxDateFromTo").is(':checked')){
        $("#dateFromTo").fadeIn();
        $("#quarterSelection").fadeOut();
    }
    else {
        $("#dateFromTo").fadeOut();
        $("#quarterSelection").fadeIn();
    }
});