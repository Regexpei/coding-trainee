import groovy.json.JsonOutput

pipeline {
    agent {
        label 'xxx'
    }
    environment {
        key = value
    }
    stages {
        stage('xxx') {
            steps {
                script {
                    methodA()
                    methodB()
                    methodC()
                }
            }
        }
    }
}

void methodA() {}

void methodB() {}

void methodC() {}
