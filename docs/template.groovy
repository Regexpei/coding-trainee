import groovy.json.JsonOutput

pipeline {
    // 代理，即执行脚本的机器
    agent { label 'xxx' }
    // 环境变量
    environment {
        key = value
    }
    // 对应步骤
    stages {
        // 对应任务
        stage('xxx') {
            // 若有指定，则在该节点执行脚本
            agent {label 'xxx'}
            steps {
                script {
                    // 对应任务项
                    methodA()
                    methodB()
                }
            }
        }
        stage('yyy') {
            steps {
                script {
                    // 对应任务项
                    methodA()
                    methodB()
                }
            }
        }
    }
}
void methodA() {}
void methodB() {}