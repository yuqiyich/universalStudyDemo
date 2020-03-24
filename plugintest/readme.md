## gradle 插件
### 插件实现的3种类型
- 直接编写xxx.gradle 文件，然后通过 apply from:xxx.gradle的方式应用插件
- 在项目的 buildSrc 目录下的插件，这个项目里的所有（子）项目都可以使用，不好做迁移
- 独立的二级制插件开发，这个项目可以进行依赖其他插件，并且这个项目可以打包发布
  JAR，提供给其他任何项目使用。
### 独立二进制的jar插件开发
- 开发语言 Java ，Kotlin，groovy都可以，下面利用android
  studio，groovy为例来开发一个插件
- 新建一个android 工程，然后再new 一个 android library moudle
  删除下面的main下面所有东西，新建groovy文件夹。
- 修改build.gradle文件
```groovy
apply plugin: 'groovy'
dependencies {
    compile gradleApi()
    compile localGroovy()
    compile 'com.android.tools.build:gradle:3.4.1'//对应项目版本
}

repositories {
    jcenter()
    google()
    mavenCentral()
}
```
- 在groovy的文件下新建xxxx.groovy文件
```groovy
class FirstPlugin implements Plugin<Project>{//一定要集成Plugin类
    @Override
    void apply(Project project) {
        project.task("testTask") doLast{
                println("我的第一个gradle插件")
        }
    }
}
```
- 新建res/META-INF/gradle-plugins/xxx.xxx.prepertes文件
```properties
implementation-class=com.yich.FirstPlugin #这个类就是上面定义插件入口类
```
- 在plugin工程下运行 assemble命令，生成jar，将jar文件拷贝到根工程然后 通过
  ```groovy
  classpath files('xxx/xxxx/xxxplugin.jar'）
  ```
  本地依赖的方式来注入。
- 使用就直接在要用的工程里面加入该脚本
```groovy
apply plugin: com.yich.FirstPlugin //class name 为入口的名字
```
- 最后在 控制台就可以检测你写的插件的task了我的就是
```shell script
 ./gradlew testTask    //mac电脑的用 ./  testTask 为插件里的任务名字
```
#### 你也可以发布插件到maven 仓库，在你的插件moudle里面去引用maven插件即可