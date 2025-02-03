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