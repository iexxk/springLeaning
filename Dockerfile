#基础镜像选择alpine 小巧安全流行方便
FROM exxk/java:8-alpine-cst
EXPOSE 8080 50052 5005
# 设置参数
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -XX:NewRatio=1"
ENV DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
#复制固定路径下打包好的jar包(target/*.jar)并重命名到容器跟目录(/app.jar)，或ADD
COPY build/libs/*.jar app.jar
COPY src/main/resources/* /
#健康检查 -s 静默模式，不下载文件
#HEALTHCHECK --start-period=40s --interval=30s --timeout=5s --retries=5 CMD (wget http://localhost:9203/actuator/health -q -O -) | grep OK || exit 1
#启动容器执行的命令 java -jar app.jar ,如果加其他参数加 ,"-参数",
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ${DEBUG_OPTS} /app.jar"]