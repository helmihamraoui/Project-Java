function showStep(stepNumber) {
    const steps = document.querySelectorAll('.form-step');
    steps.forEach(step => {
      step.classList.remove('step-active', 'step-prev');
      if (step.id !== `step${stepNumber}`) {
        step.classList.add(stepNumber > parseInt(step.id.replace('step', '')) ? 'step-prev' : 'step-next');
      }
    });
    document.getElementById(`step${stepNumber}`).classList.add('step-active');
  }





  // Enable auto-sliding for the carousel
$(document).ready(function () {
  $('#carouselExample2Controls').carousel({
    interval: 3000, // Set the interval in milliseconds (e.g., 3000ms = 3 seconds)
    pause: "hover" // Pause on hover
  });
});
