# jkit-example

[jkit](https://github.com/zhengmingliang/jkit) 的示例项目集合。每个子目录是一个独立可运行的示例，演示 jkit 某个模块的用法。

> jkit 主仓库：<https://github.com/zhengmingliang/jkit>
> 在线文档：<https://jkit.alianga.com>

## 示例列表

| 目录 | 演示模块 | 说明 |
| --- | --- | --- |
| [springboot3-ddl-demo](springboot3-ddl-demo/) | `jkit-sql-auto-spring-boot-3` | Spring Boot 3 自动建表：启动后扫描 JPA 实体，对照现有库表执行 `CREATE TABLE` / `ALTER TABLE ADD` / `CREATE INDEX`，支持表名前缀、多库方言自动推断 |

## 快速体验

```bash
# 克隆
git clone https://github.com/zhengmingliang/jkit-example.git
cd jkit-example

# 进入感兴趣的示例
cd springboot3-ddl-demo

# 改 src/main/resources/application.yaml 里的库地址（或切成 H2 内存库），然后
mvn spring-boot:run
```

每个示例的 README 里有详细的配置说明和使用方式。

## jkit 模块速览

| 坐标 | 用途 |
| --- | --- |
| `com.alianga:jkit` | 核心工具库（零第三方依赖，JDK 8+） |
| `com.alianga:jkit-sql` | 零依赖 SQL 解析器：格式化、表列统计、跨方言分页改写 |
| `com.alianga:jkit-sql-auto` | 按实体自动建表 / 更新表结构（非 Spring，需手动调 `SqlAuto.run`） |
| `com.alianga:jkit-sql-auto-spring-boot-2` | Spring Boot 2.x 自动配置 starter |
| `com.alianga:jkit-sql-auto-spring-boot-3` | Spring Boot 3.x 自动配置 starter（JDK 17+） |
| `com.alianga:jkit-curl-codegen` | curl 命令转 OkHttp / fetch / requests 等多语言源码 |
| `com.alianga:jkit-notify` | 消息通知：钉钉 / 企微 / 飞书 / Server酱 / Bark / Webhook / 邮件 |
| `com.alianga:jkit-notify-extra` | 扩展渠道：Slack / Telegram / ntfy / 短信（阿里云、腾讯云等） |

## License

Apache License 2.0 — 与 jkit 主仓库一致。
