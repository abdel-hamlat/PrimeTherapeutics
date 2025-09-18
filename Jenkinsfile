pipeline {
  agent any
  tools { jdk 'jdk17'; maven 'maven3' }
  options { timestamps() }
  environment {
    PLAYWRIGHT_BROWSERS_PATH = '.playwright'
    MVN_COMMON = '-B -Dmaven.repo.local=.m2/repo --no-transfer-progress'
  }
  stages {
    stage('Checkout') {
      steps {
        // If you configure "Shallow clone" in the Git section of the job UI, that helps too
        checkout scm
      }
    }

    stage('Build (no tests)') {
      steps {
        // Parallelize Maven: -T 1C uses one thread per CPU core
        bat "mvn %MVN_COMMON% -T 1C -DskipTests clean install"
      }
    }

    stage('Install Playwright browsers (if needed)') {
      when {
        expression { return !fileExists('.playwright') || !fileExists('.playwright/registry.lock') }
      }
      steps {
        bat 'mvn %MVN_COMMON% exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps"'
      }
    }

    stage('Test (headless on CI)') {
      steps {
        bat 'mvn %MVN_COMMON% test -Dheadless=true'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/**', fingerprint: true
    }
  }
}
