# Example Product Service

## 开发指南

### 技术栈
- Java 8
- Spring Boot 2.1.3.RELEASE

### 架构和代码结构
本项目代码结构参考 Onion Architecture，参考以下介绍
* [The Onion Architecture : part 1](https://jeffreypalermo.com/2008/07/the-onion-architecture-part-1/)
* [The Onion Architecture : part 2](https://jeffreypalermo.com/2008/07/the-onion-architecture-part-2/)
* [The Onion Architecture : part 3](https://jeffreypalermo.com/2008/08/the-onion-architecture-part-3/)
* [The Onion Architecture : part 4](https://jeffreypalermo.com/2013/08/onion-architecture-part-4-after-four-years/)

测试策略和结构参考
* [Testing Strategies in a Microservices Architecture](https://martinfowler.com/articles/microservice-testing)
### 本地构建
```
./gradlew clean build
```

### 本地运行
```
./gradlew bootRun
```

### 用Intellij IDEA打开项目
```
./gradlew idea
open leasing-brand.ipr
```

### 提交代码
代码提交加入了 `pre-commit` 钩子，每次提交会执行 `./gradlew check`, 详情参考 `gradle/git-hook.gradle`
