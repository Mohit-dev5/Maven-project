/*def buildJar() {
    echo "building the application..."
    sh 'mvn -B -DskipTests clean package' 
    //sh 'mvn clean package'
} 

def testReport(){
    echo 'Generated Test report...'
    sh 'mvn test'
}

def buildImage() {
    echo "building the docker image..."
        //withCredentials([usernamePassword(credentialsId: 'ACR', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        //sh 'docker build -t advisingbank/demo-app .'
        //sh "echo $PASS | docker login -u $USER --password-stdin"
        //sh 'docker push nanajanashia/demo-app:jma-2.0'
        dockerImage = docker.build registryName + ":$BUILD_NUMBER"
    //}
    } 

def pushImage() {
    echo 'Pushing the build image...'
         docker.withRegistry( "http://${registryUrl}", registryCredential ) {
         dockerImage.push()
         }
} 
*/

def destroy() {
       echo 'removing previously build container'
        sh 'docker ps -f name=mymavenContainer -q | xargs --no-run-if-empty docker container stop'
        sh 'docker container ls -a -fname=mymavenContainer -q | xargs -r docker container rm'        
}

def runImage() {
    echo 'running the application in a container...'
    sh 'docker run -d -p 8096:8080 --rm --name mymavenContainer ${registryUrl}/${registryName}:$BUILD_NUMBER'
} 

def deleteImage() {
    echo 'deleting the build image after saving in ACR'
    sh "docker rmi $registryUrl/$registryName:$BUILD_NUMBER"
    sh "docker rmi $registryName:$BUILD_NUMBER"
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
