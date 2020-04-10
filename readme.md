## Système du management hôtelier

Cette application sépare deux côtés, le client et propriétaire. Les client peuvent réserver la chambre en fonction de la période du séjour et le destination du voyage.\
Les propriétaires peuvent inscrire leur chambres ou maisons, et les gérer par ce système,

## Structure
- src
 - bean : Object的实体类, 比如UserBean就只是User本身
 - dao  : 连接数据库和实体类, UserDao就封装了数据库和UserBean之间的联系
 - servlet : 业务逻辑类
- lib : 连接servlet和mysql的包, 复制到\
`../apache-tomcat-9.0.33/webapps/microproject/WEB-INF/lib`
- static
  - css
  - js
  - view : 网页文件
- web.xml : 项目描述文件


## 开发
每次新建一个servlet class, 需要在web.xml里面注册. 比如我添加了帐号注册功能, 这个功能由`Signup`类实现
```
<servlet>
  <servlet-name>Signup</servlet-name>
  <servlet-class>jar.servlet.Signup</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>Signup</servlet-name>
  <url-pattern>/Signup</url-pattern>
</servlet-mapping>
```

## Compilation
根据你自己的tomcat目录改一下makefile里的路径, 不然用不了
- Compiler les fichiers Java
```
mvn compile
```
- Mettre à jour sur le serveur
```
make install
```
- Supprimer les fichier class local
```
mvn clean
```
- Supprimer les fichier class sur le serveur
```
make clean
```

## Test
Accès cet url sur un navigateur
```
http://localhost:8080/microproject/static/view/accueil.jsp
```
