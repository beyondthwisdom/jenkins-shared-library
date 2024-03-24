#!user/bin/env groovy

def call(){
    sh 'echo Prod Building...'
    withCredentials([usernamePassword(credentialsId: 'docker-credential', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'echo Derleme ve image build ve push işlemi başlatılıyor'
        sh "./mvnw package -Pprod -DskipTests jib:build -Djib.to.image=btwdevops/jenkinsders5-prod:pipeline-${params.VERSION}"
    }
}