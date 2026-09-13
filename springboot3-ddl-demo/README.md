# springboot3-ddl-demo

[jkit-sql-auto-spring-boot-3](https://github.com/zhengmingliang/jkit/tree/develop/jkit-sql-auto-spring-boot-3) 的 Spring Boot 3 示例：启动后扫描 JPA 实体，对照现有库表执行 `CREATE TABLE` / `ALTER TABLE ADD` / `CREATE INDEX`。

starter 会在 `ApplicationReadyEvent` 时自动跑一次，**不必**在 `main` 里调 `SqlAuto.run`。数据源用容器里的 `DataSource`（即 `spring.datasource.*`）。

完整配置与行为说明：[自动建表](https://jkit.alianga.com/sql-auto.html)。

## 环境

| 项 | 值 |
| --- | --- |
| JDK | 17+ |
| Spring Boot | 3.5.x |
| 核心依赖 | `com.alianga:jkit-sql-auto-spring-boot-3:2.0.1` |

JDBC 驱动由本示例提供，starter 本身不带驱动。`pom.xml` 里预置了多库验证用的驱动，按 URL 切换即可：

MySQL、PostgreSQL / OpenGauss、Oracle、SQL Server、达梦、GBase 8a、H2、SQLite。

## 快速开始

1. 改 [`src/main/resources/application.yaml`](src/main/resources/application.yaml) 里的库地址和账号。
2. 启动：

```bash
mvn spring-boot:run
```

就绪后看日志里的 DDL（默认 `show-sql: true`）。第一次启动会按实体建表；之后缺列 / 缺索引才会 `ALTER` / `CREATE INDEX`。

本地不想连外部库时，可换成 H2：

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:ddl_demo;DB_CLOSE_DELAY=-1
    username: sa
    password:
```

## 本示例在演示什么

`Springboot3DdlDemoApplication` 只有标准的 `SpringApplication.run`，DDL 全部交给 starter。关键配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/jkit_ddl_demo
    username: root
    password: secret
  jpa:
    hibernate:
      ddl-auto: none          # 只让 jkit-sql-auto 建表，避免和 Hibernate 双重建表，如果使用 MyBatis 可以不用管这个配置

jkit:
  sql:
    auto:
      enabled: true
      mode: update            # 只加表 / 列 / 索引，不改已有列、不删列
      packages: com.alianga.test.springboot3ddldemo.entity
      table-prefix: t_        # 实体 @Table(name="file_storage") → 实际表 t_file_storage
```

要点：

- **`packages` 必配**（或改用 `entities` 列出全限定名），否则日志是 `no entities, skip`。
- **关掉 Hibernate 自动建表**。本示例用 JPA 注解只是给 `jkit-sql-auto` 扫描，不走 `hibernate.hbm2ddl`。
- **方言不用手写**：从 JDBC URL / `DatabaseMetaData` 推断。`jdbc:mysql:` → MySQL，`jdbc:postgresql:` → PostgreSQL，`jdbc:dm:` → 达梦，以此类推。
- **`table-prefix`** 作用于建表、改表、索引、序列、外键目标表。本示例统一加 `t_`。
- **catalog 不用配**：Inspector 会从当前 JDBC 连接取当前库。

`application.yaml` 里还有一段 `spring.datasource1`（OpenGauss），Spring Boot **不会**把它当主数据源；那是切库时的备份写法，真正生效的是 `spring.datasource`。

## 实体怎么写

扫描包是 `com.alianga.test.springboot3ddldemo.entity`。starter 认 JPA（`jakarta.persistence`）注解，**不必**改成 `@SqlTable`。

典型写法（字符串 UUID 主键，**不会**生成 `IDENTITY` / `AUTO_INCREMENT`）：

```java
@Entity
@Table(name = "file_storage")
public class FileStorageEntity extends NewBaseEntity<FileStorageEntity> {
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "source_name")
    private String fileSourceName;
}
```

本示例覆盖的扫描能力：

| 能力 | 例子 |
| --- | --- |
| `@Entity` + `@Table` | 全部业务实体 |
| `@MappedSuperclass` 公共列 | [`NewBaseEntity`](src/main/java/com/alianga/test/springboot3ddldemo/entity/NewBaseEntity.java)（`create_time`、`tenant_id` 等） |
| 字符串 UUID 主键 | `@GeneratedValue(generator="system-uuid")` / `GenerationType.UUID` |
| 未命名 / 命名 / 复合索引 | `data_auth`、`published_subject`、`published_sql_model` |
| `@Lob` | `FileStorageEntity.fields` |
| 枚举列 | `DimEntity` + `DimType` |
| 不继承基类的独立表 | `ConfigParamEntity`、`DesensitizationRuleMappingEntity` |

`NewBaseEntity` 本身不是表。子类与父类若声明了同名列，扫描时只会生成一次，避免 PostgreSQL `column specified more than once`。

配置了 `table-prefix: t_` 后，实际表名如下：

| 实体 | `@Table` | 实际表 |
| --- | --- | --- |
| `ConfigParamEntity` | `config_param` | `t_config_param` |
| `FileStorageEntity` | `file_storage` | `t_file_storage` |
| `MetadataTableEntity` | `metadata_table` | `t_metadata_table` |
| `MetadataColumnEntity` | `metadata_column` | `t_metadata_column` |
| `MetadataRelationEntity` | `metadata_relation` | `t_metadata_relation` |
| `DataAuthEntity` | `data_auth` | `t_data_auth` |
| `DimEntity` | `im_dim` | `t_im_dim` |
| `DimGroupEntity` | `dim_group` | `t_dim_group` |
| `DimensionTableEntity` | `dimension_table` | `t_dimension_table` |
| `DimensionTableFieldEntity` | `dimension_table_field` | `t_dimension_table_field` |
| `DimensionTableGroupEntity` | `dimension_table_group` | `t_dimension_table_group` |
| `FileSourceEntity` | `file_source` | `t_file_source` |
| `FileSourceGroupEntity` | `file_source_group` | `t_file_source_group` |
| `DashBoardOriginEntity` | `dash_board_origin` | `t_dash_board_origin` |
| `PublishedSubjectEntity` | `published_subject` | `t_published_subject` |
| `PublishedSqlModelEntity` | `published_sql_model` | `t_published_sql_model` |
| `ReplaceTableLogEntity` | `replace_table_log` | `t_replace_table_log` |
| `DeleteDateEntity` | `delete_date` | `t_delete_date` |
| `DesensitizationRuleMappingEntity` | `des_rule_mapping` | `t_des_rule_mapping` |

未命名索引会生成 `{table}_{col}_idx`；因为开了 `table-prefix`，派生索引名默认也会带前缀（如 `t_data_auth_resource_id_idx`）。实体里显式写的 `@Index(name=…)`（例如 `published_subject_caption_index`）原样保留。

## 模式与安全边界

`mode: update`（默认，也是本示例的取值）：

- 表不存在 → `CREATE TABLE`
- 缺列 → `ALTER TABLE … ADD`
- 缺索引 → `CREATE INDEX`
- **不改**已有列类型、**不删**多余列/表、不改列名、不迁数据

其它常用项：

```yaml
jkit:
  sql:
    auto:
      enabled: false          # 整段跳过
      mode: validate          # 缺表/缺列就抛错，不改库
      dry-run: true           # 只规划 SQL，不执行
      alter-column: true      # 类型不一致时才 MODIFY
      drop-extra-columns: true
      show-sql: true
```

生产建议保持 `update`。`create` / `create-drop` 会先删托管表再重建，只适合开发库。

## 切库

改 `spring.datasource.url` 即可，对应驱动已在 `pom.xml`。例如：

```yaml
# PostgreSQL / OpenGauss
url: jdbc:postgresql://127.0.0.1:5432/jkit_ddl_demo

# 达梦
url: jdbc:dm://127.0.0.1:5236/JKIT

# Oracle 12c+ 要用 IDENTITY 时显式指定方言（URL 默认推断成经典 ORACLE）
# jkit.sql.auto.dialect: oracle12
url: jdbc:oracle:thin:@127.0.0.1:1521/ORCLPDB1
```

窄产品注意：

- OpenGauss 老版本可加 `jkit.sql.auto` 里对应的 identity 风格（代码 API 为 `postgresIdentityStyle(SERIAL)`）
- GBase 8a 建议关掉外键和自动索引
- H2 内存库若用 `create-drop`，URL 加 `DB_CLOSE_DELAY=-1`，否则连接一关库就没了

## 自己的项目怎么抄

1. 加依赖：

```xml
<dependency>
    <groupId>com.alianga</groupId>
    <artifactId>jkit-sql-auto-spring-boot-3</artifactId>
    <version>2.0.1</version>
</dependency>
```

2. 配 `spring.datasource.*` 和 `jkit.sql.auto.packages`（或 `entities`）。
3. 把 `spring.jpa.hibernate.ddl-auto` 设成 `none`（如果项目里有 JPA）。
4. 实体继续用现有的 `@Entity` / `@Table` / `@Id` / `@Column` / `@Index` 即可。

Boot 2 请改用 [`jkit-sql-auto-spring-boot-2`](https://github.com/zhengmingliang/jkit/tree/develop/jkit-sql-auto-spring-boot-2)；非 Spring 项目用 [`jkit-sql-auto`](https://github.com/zhengmingliang/jkit/tree/develop/jkit-sql-auto) 并在启动入口调 `SqlAuto.run(...)`。
