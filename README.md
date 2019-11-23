##baiyao
##资料
[Spring文档](https://spring.io/guides)<br>
[Springweb文档](https://spring.io/guides/gs/serving-web-content/)<br>
[Spring文档](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)<br>
[githubapps文档](https://github.com/settings/apps)<br>
[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor) <br>
[Markdown 插件](http://editor.md.ipandao.com/)

##工具
[GIT](https://git-scm.com/)<br>
[FlywayMigration官方文档](https://flywaydb.org/getstarted/firststeps/maven#creating-the-first-migration)<br>
[lombok](https://projectlombok.org/setup/maven)<br>
[jquery ajax.post方法](https://api.jquery.com/jQuery.post/)
##脚本
```sql
create table user
(
	id INT auto_increment,
	account_id VARCHAR(100),
	name VARCHAR(50),
	token CHAR(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	constraint user_pk
		primary key (id)
);
```
使用数据库的FlywayMigratio执行脚本
```shell script
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```