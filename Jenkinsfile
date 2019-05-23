pipeline {
  agent any
  stages {
    stage('Build') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('bc', 'config-svc-buildconfig').exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.newApp('redhat-openjdk18-openshift:1.1~https://github.com/INTEGRITY-One/egis-config.git')
          }
        }
      }
    }
  }
}