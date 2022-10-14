pipeline {
    agent {
        docker {
            image 'python:3.8'
        }
    }
    // triggers{ cron('H/15 * * * *') }
    stages {
        stage('Pull The Task') {
            steps {
                echo "Pull The Task"
                echo sh(returnStdout: true, script: "python --version").trim()
                echo sh(returnStdout: true, script: "git --version").trim()
            }
        }
        stage('Execute The Task') {
            steps {
                echo "Execute The Task"
                sh "python app/main.py"
            }
        }
    }
}
