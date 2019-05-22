pipeline {
  agent {
  	label 'maven'
  }
  stages {
    stage('Build App') {
      steps {
        sh "mvn clean install"
      }
    }
    stage('Create Image Builder') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector("bc", "config-svc-buildconfig").exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.newBuild("--name=config-svc-buildconfig", "--image-stream=redhat-openjdk18-openshift:1.1", "--binary")
          }
        }
      }
    }
    stage('Build Image') {
      steps {
        script {
          openshift.withCluster() {
            openshift.selector("bc", "config-svc-buildconfig").startBuild("--from-file=target/config-svc.jar", "--wait")
          }
        }
      }
    }
    stage('Promote to DEV') {
      steps {
        script {
          openshift.withCluster() {
            openshift.tag("config-svc:latest", "config-svc:dev")
          }
        }
      }
    }
    stage('Create DEV') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('dc', 'config-svc-dev').exists()
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.newApp("config-svc:latest", "--name=config-svc-dev").narrow('svc').expose()
          }
        }
      }
    }
    stage('Promote STAGE') {
      steps {
        script {
          openshift.withCluster() {
            openshift.tag("config-svc:dev", "config-svc:stage")
          }
        }
      }
    }
    stage('Create STAGE') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('dc', 'config-svc-stage').exists()
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.newApp("config-svc:stage", "--name=config-svc-stage").narrow('svc').expose()
          }
        }
      }
    }
  }
}