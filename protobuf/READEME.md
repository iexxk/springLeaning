1. 在项目引入maven/gradle依赖`compile 'com.google.protobuf:protobuf-java:3.6.1'`

2. 下载[代码生成工具](https://github.com/protocolbuffers/protobuf/releases)，作用是将`file.proto`文件转换成其他语言（java/C++/GO/Python/C#/Dart）的文件,eg：这里选择window平台，版本和maven版本一致，因此选择[protoc-3.6.1-win32.zip](https://github.com/protocolbuffers/protobuf/releases/download/v3.6.1/protoc-3.6.1-win32.zip),其他操作系统选择对应平台即可,然后解压，在bin目录可以看到`protoc.exe`文件，复制重命名`protoc-3.6.1-win32.exe`为了好区分版本，其他文件用不着

3. 编写一个测试`PersonMsg.proto`文件

4. 使用工具进行java代码生成，执行`.\protobuf\protoc-3.6.1-win32.exe --java_out=.\protobuf\src\main\java\  .\protobuf\src\test\protobuf\PersonMsg.proto`

   注意，如果没有只当在proto指定包名需要手动加入

5. 在`ProtobufApplicationTests.java`编写测试方法

### protoc gradle插件

插件地址：[google/protobuf-gradle-plugin](https://github.com/google/protobuf-gradle-plugin#latest-version)

1. 在父级`build.gradle`添加

   ```groovy
   buildscript {
     repositories {
       mavenCentral()
     }
     dependencies {
       classpath 'com.google.protobuf:protobuf-gradle-plugin:0.8.8'
     }
   }
   ```

2. 在子项目`build.gradle`添加

   ```groovy
   apply plugin: 'com.google.protobuf'
   
   dependencies {
       compile 'com.google.protobuf:protobuf-java:3.6.1'
   }
   
   sourceSets {
       main {
           proto {
               // In addition to the default 'src/main/proto'
               //proto输入目录
               srcDir 'src/main/protobuf'
               srcDir 'src/main/protocolbuffers'
               srcDir 'src/main/protocol buffers'
               // In addition to '**/*.proto' (use with caution).
               // Using an extension other than 'proto' is NOT recommended, because when
               // proto files are published along with class files, we can only tell the
               // type of a file from its extension.
               include '**/*.protodevel'
           }
       }
       test {
           proto {
               // In addition to the default 'src/test/proto'
               srcDir 'src/test/protocolbuffers'
           }
       }
   }
   protobuf {
       //输出目录
       generatedFilesBaseDir = "$projectDir/src"
       protoc {
           //protoc编译版本
           artifact = 'com.google.protobuf:protoc:3.0.0'
       }
   }
   ```

3. 然后点击右侧gradle`protobuf->Tasks->other->generateProto`编译proto文件生成java文件