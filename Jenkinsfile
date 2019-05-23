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
            openshift.createResource(
               "apiVersion: build.openshift.io/v1
kind: BuildConfig
metadata:
  annotations:
    description: Defines how to build the application
  labels:
    app: config-svc
  name: config-svc-buildconfig
  namespace: egis
spec:
  failedBuildsHistoryLimit: 5
  output:
    to:
      kind: ImageStreamTag
      name: 'config-svc:latest'
  postCommit:
    script: 
  runPolicy: Serial
  source:
    dockerFile:
      uri: 'https://github.com/INTEGRITY-One/egis-config.git'
    type: Git
  strategy:
    dockerStrategy:
      type: Docker
  successfulBuildsHistoryLimit: 5
  triggers:
    - imagechange:
      type: ImageChange",
            )
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