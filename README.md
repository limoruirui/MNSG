# 批量下载mnsg游戏静态资源
## 项目说明
- 仅供用于测试以及获取编写游戏wiki所需的静态资源
- 目前为单线程下载, 后续可能更新多线程 
- 目前只抓取人物立绘。 游戏背景图、各种游戏内图标、头像等均排除.

## 使用说明
- 只在 jdk17 进行了测试
- 运行 src/main/java/org/mnsg/resource/App.java即可 结果输出在 src/main/result 文件夹内

## 未来预期更新
- (已更新)目前resourceJP.json及resourceTW.json为hook前端加密方法返回的, 后续若解决了java的AES/CTR/NoPadding 则会更新为自动获取(第一次接触到CTR模式, java(javax)和python(pycryptodome)的结果互通,可用密钥相互加解密, 但和前端js(crypto-js)的结果不同, 后续将CTR模式弄懂了再更新)