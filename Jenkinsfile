node {
   def mvnHome
   stage('Preparation') {
      git 'https://github.com/Raheekhan/SideProject.git'
      mvnHome = tool 'M3'
   }
   stage('Build') {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean test"
   }
}
