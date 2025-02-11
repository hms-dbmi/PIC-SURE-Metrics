pipeline {
    agent any

    parameters {
        string(name: 'DOCKER_REGISTRY', description: 'Docker registry URL (e.g., ECR URL)', defaultValue: 'hms-dbmi')
        string(name: 'REPOSITORY_NAME', description: 'Docker repository name', defaultValue: 'metrics')
        booleanParam(name: 'DEPLOY', description: 'Deploy the image to the registry', defaultValue: true)
        booleanParam(name: 'ERROR_FILE', description: 'Errors will be sent to a different file than standard metrics.', defaultValue: true)

        // Standard log file settings
        string(name: 'LOG_PATH', description: 'Log file location.', defaultValue: '/home/logs/metrics')
        string(name: 'FILE_NAME', description: 'Log file name.', defaultValue: 'pic-sure-metrics.json')
        choice(name: 'SHOULD_ROLL_FILE', choices: ['ROLLING_FILE', 'SINGLE_FILE'], description: 'Choose log file mode')
        string(name: 'MAX_FILE_SIZE', description: 'If rolling over, this is the size (in MB) each file should be at its max.', defaultValue: '100')
        string(name: 'RETENTION_TIME', description: 'Log file retention time in days.', defaultValue: '90')

        // Error log file settings (separate file)
        string(name: 'ERROR_PATH', description: 'Error log file location.', defaultValue: '/home/logs/errors')
        string(name: 'ERROR_FILE_NAME', description: 'Error log file name.', defaultValue: 'pic-sure-errors.json')
        choice(name: 'ERROR_SHOULD_ROLL_FILE', choices: ['SINGLE_FILE', 'ROLLING_FILE'], description: 'Choose error log file mode')
        string(name: 'ERROR_MAX_FILE_SIZE', description: 'If rolling over, this is the size (in MB) each error log file should be at its max.', defaultValue: '100')
        string(name: 'ERROR_RETENTION_TIME', description: 'Error log file retention time in days.', defaultValue: '90')
    }

    environment {
        GIT_BRANCH_SHORT = sh(script: 'echo ${GIT_BRANCH} | cut -d "/" -f 2', returnStdout: true).trim()
        GIT_COMMIT_SHORT = sh(script: 'echo ${GIT_COMMIT} | cut -c1-7', returnStdout: true).trim()
        IMAGE_TAG = "${GIT_BRANCH_SHORT}_${GIT_COMMIT_SHORT}"
        LATEST_TAG = "LATEST"
    }

    stages {
        stage('build') {
            steps {
                sh "set -e"
                sh "docker network ls || echo 'Failed to list Docker networks'"
                sh "echo \"Building the Docker image\""
                sh "docker build -t ${params.DOCKER_REGISTRY}/${params.REPOSITORY_NAME}:${IMAGE_TAG} ."
                sh "echo \"Tagging the Docker image\""
                sh "docker tag ${params.DOCKER_REGISTRY}/${params.REPOSITORY_NAME}:${IMAGE_TAG} ${params.DOCKER_REGISTRY}/${params.REPOSITORY_NAME}:${LATEST_TAG}"
            }
        }
        stage('deploy') {
            steps {
                script {
                    if (params.DEPLOY) {
                        sh "set -e"
                        sh "echo \"Tagging the Docker image\""
                        sh "docker stop metrics || true"
                        sh "docker rm metrics || true"
                        sh "echo \"Running the Docker image\""
                        sh """docker run --name=metrics --restart always \
                                --network=picsure --network=hpdsNet \
                                -v \"$DOCKER_CONFIG_DIR\"log/metrics:/home/logs/metrics/ \
                                -e LOG_PATH=${params.LOG_PATH} \
                                -e FILE_NAME=${params.FILE_NAME} \
                                -e MODE=${params.SHOULD_ROLL_FILE} \
                                -e MAX_FILE_SIZE=${params.MAX_FILE_SIZE} \
                                -e RETENTION_TIME=${params.RETENTION_TIME} \
                                -e ERROR_LOG_PATH=${params.ERROR_PATH} \
                                -e ERROR_FILE_NAME=${params.ERROR_FILE_NAME} \
                                -e ERROR_MODE=${params.ERROR_SHOULD_ROLL_FILE} \
                                -e ERROR_MAX_FILE_SIZE=${params.ERROR_MAX_FILE_SIZE} \
                                -e ERROR_RETENTION_TIME=${params.ERROR_RETENTION_TIME} \
                                -e SEPARATE_ERROR_LOG=${params.ERROR_FILE} \
                                -d ${params.DOCKER_REGISTRY}/${params.REPOSITORY_NAME}:${IMAGE_TAG}"""
                   }
                }
            }
        }
    }
}