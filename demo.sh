#!/bin/bash
# 重写 pom.xml 添加 neuxs
cat <<'EOF' > settings.xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
 
  <servers>
    <server>
            <id>lihaorong-maven-test</id>
            <username>${username}</username>
            <password>${password}</password>
        </server>
  </servers>
</settings>
EOF
 
mvn clean deploy -Dusername=$NEXUS_UESRNAME -Dpassword=$NEXUS_PASSWORD -Dmaven.test.skip=true -DskipDocker -s ./settings.xml
result=$? #结果返回值
if [ $result -ne 0 ]; then
  exit 1 #异常退出容器
fi