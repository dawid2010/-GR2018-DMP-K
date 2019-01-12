var minimumPercentage = $(".progress-wrap");
if(minimumPercentage.data("minimum") < 100){
    $('.progress-wrap').addClass('progress-wrap-fail');
    $('.progress-wrap-fail').each(function(){
        percent = $(this);
        bar = $(this).children('.progress-bar');
        moveProgressBar(percent, bar);
    });
} else {
    $('.progress-wrap').each(function(){
        percent = $(this);
        bar = $(this).children('.progress-bar');
        moveProgressBar(percent, bar);
    });
}

$('.progress-wrap').each(function(){
    percent = $(this);
    bar = $(this).children('.progress-bar');
    moveProgressBar(percent, bar);
});

  // on browser resize...
  $(window).resize(function() {
    $('.progress-wrap').each(function(){
        percent = $(this);
        bar = $(this).children('.progress-bar');
        moveProgressBar(percent, bar);
    });
  });

  // SIGNATURE PROGRESS
  function moveProgressBar(percent, bar) {
      var getPercent = (percent.data('progress-percent') / 100);
      var getProgressWrapWidth = percent.width();
      var progressTotal = getPercent * getProgressWrapWidth;
      var animationLength = 1000;

      // on page load, animate percentage bar to data percentage length
      // .stop() used to prevent animation queueing
      bar.stop().animate({
          left: progressTotal
      }, animationLength);
  }