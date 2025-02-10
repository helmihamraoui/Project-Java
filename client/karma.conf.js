module.exports = function(config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      'karma-jasmine',
      'karma-chrome-launcher',
      'karma-coverage-istanbul-reporter',
      '@angular-devkit/build-angular/plugins/karma'
    ],
    client: {
      clearContext: false, // leave Jasmine Spec Runner output visible in browser
    },
    coverageIstanbulReporter: {
      dir: require('path').join(__dirname, './coverage'),
      reports: ['html', 'lcovonly', 'text-summary'],
      fixWebpackSourcePaths: true
    },
    files: [
      'src/test.ts'
    ],
    preprocessors: {
      'src/test.ts': ['@angular-devkit/build-angular:karma']
    },
    reporters: ['progress', 'coverage-istanbul'],
    browsers: ['Chrome'],
    singleRun: false,
    restartOnFileChange: true
  });
};

