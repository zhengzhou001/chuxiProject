chcp 65001
title admin
C:/Java/jdk1.8.0_111/bin/java -Dfile.encoding=UTF-8 -Dspring.profiles.active=test  -server -Xms512m -Xmx512m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m -jar adminService-1.0.jar