pipeline {
  agent {
  	label 'maven'
  }
  stages {
    stage('Create BuildConfig') {
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
               '{"apiVersion": "build.openshift.io/v1","kind": "BuildConfig","metadata": {"annotations": {"description": "Defines how to build the application"},"labels": {"app": "config-svc"},"name": "config-svc-buildconfig","namespace": "egis"},"spec": {"failedBuildsHistoryLimit": "5","output": {"to": {"kind": "ImageStreamTag","name": "config-svc:latest"}},"postCommit": {"script": ""}, "runPolicy": "Serial","source": {"git": {"uri": "https://github.com/INTEGRITY-One/egis-config.git"},"type": "Git"},"strategy": {"type": "Source","sourceStrategy": {"from": {"kind": "ImageStream","namespace": "openshift","name": "java"}}},"successfulBuildsHistoryLimit": "5"}}'
            )
          }
        }
      }
    }
    stage('Execute Build') {
      steps {
        script {
          openshift.withCluster() {
            openshift.selector("bc", "config-svc-buildconfig").startBuild("--wait")
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