# java_base

###  java 单文件执行 
```shell script
cd simple && javac Helloworld.java && java Helloworld 
```   

###  java 文件 带包名

```shell script
  cd simple
              
  javac -d . *.java  
## -d 生成包结构
##  . 当前目录
##
  java com.slyang.test.HelloPkg
## 执行main方法（运行字节码文件）
```

###  指定classpath


