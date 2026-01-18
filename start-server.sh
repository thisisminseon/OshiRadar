#!/bin/bash

# Tomcat 경로 설정
TOMCAT_HOME="/opt/homebrew/Cellar/tomcat@9/9.0.111/libexec"
PROJECT_DIR="/Users/parkminseon/Desktop/jspStudy/myProject"
PROJECT_NAME="myProject"

echo "=========================================="
echo "Tomcat 서버 시작 스크립트"
echo "=========================================="

# Tomcat이 실행 중인지 확인
if [ -f "$TOMCAT_HOME/bin/catalina.sh" ]; then
    echo "✓ Tomcat 경로 확인: $TOMCAT_HOME"
else
    echo "✗ Tomcat을 찾을 수 없습니다: $TOMCAT_HOME"
    echo "Tomcat 경로를 확인해주세요."
    exit 1
fi

# 프로젝트 빌드 (클래스 파일 컴파일)
echo ""
echo "프로젝트 빌드 중..."
cd "$PROJECT_DIR"
mkdir -p build/classes

# Java 파일 컴파일
find src/main/java -name "*.java" -exec javac -cp "$PROJECT_DIR/src/main/webapp/WEB-INF/lib/*:$TOMCAT_HOME/lib/*" -d build/classes {} \;

if [ $? -eq 0 ]; then
    echo "✓ 빌드 완료"
else
    echo "✗ 빌드 실패"
    exit 1
fi

# Tomcat webapps에 프로젝트 배포 (선택사항)
# echo ""
# echo "프로젝트 배포 중..."
# cp -r "$PROJECT_DIR/src/main/webapp" "$TOMCAT_HOME/webapps/$PROJECT_NAME"
# cp -r "$PROJECT_DIR/build/classes" "$TOMCAT_HOME/webapps/$PROJECT_NAME/WEB-INF/"

echo ""
echo "=========================================="
echo "Eclipse에서 서버를 실행하는 것을 권장합니다."
echo ""
echo "1. Eclipse에서 프로젝트 열기"
echo "2. Servers 탭 → New → Server → Tomcat v9.0"
echo "3. 프로젝트를 서버에 추가"
echo "4. 서버 시작"
echo ""
echo "또는 Tomcat을 직접 실행하려면:"
echo "  $TOMCAT_HOME/bin/startup.sh"
echo ""
echo "접속 URL: http://localhost:8080/$PROJECT_NAME/furima/list.do"
echo "=========================================="
