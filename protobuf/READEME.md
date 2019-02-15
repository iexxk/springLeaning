1. 在项目引入maven/gradle依赖`compile 'com.google.protobuf:protobuf-java:3.6.1'`

2. 下载[代码生成工具](https://github.com/protocolbuffers/protobuf/releases)，作用是将`file.proto`文件转换成其他语言（java/C++/GO/Python/C#/Dart）的文件,eg：这里选择window平台，版本和maven版本一致，因此选择[protoc-3.6.1-win32.zip](https://github.com/protocolbuffers/protobuf/releases/download/v3.6.1/protoc-3.6.1-win32.zip),其他操作系统选择对应平台即可,然后解压，在bin目录可以看到`protoc.exe`文件，复制重命名`protoc-3.6.1-win32.exe`为了好区分版本，其他文件用不着

3. 编写一个测试`PersonMsg.proto`文件

4. 使用工具进行java代码生成，执行`.\protobuf\protoc-3.6.1-win32.exe --java_out=.\protobuf\src\main\java\com\exxk\protobuf\  .\protobuf\src\test\protobuf\PersonMsg.proto`

   注意，这里生成的代码`PersonMsg.java`里面是没有包名的，可以手动加入

5. 在`ProtobufApplicationTests.java`编写测试方法

   