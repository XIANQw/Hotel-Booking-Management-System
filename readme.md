## Système du management hôtelier

Cette application sépare deux côtés, le client et propriétaire. Les client peuvent réserver la chambre en fonction de la période du séjour et le destination du voyage.\
Les propriétaires peuvent inscrire leur chambres ou maisons, et les gérer par ce système,

## Structure
- src
 - bean : la classe de chaque l'objet
 - dao  : le lien entre bean et sql
 - servlet : les fonctions sur le serveur
- lib : package externe
`../apache-tomcat-9.0.33/webapps/microproject/WEB-INF/lib`
- static
  - css
  - js
  - view : les fichiers page web
- web.xml : le fichier de maven


## Développement
Pour chaque nouveau servlet class, il faut enregistrer dans web.xml. Par exemple si j'ai ajouté un class Signup pour réaliser l'inscription d'une compte.
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
Modifier le chemin de tomcat dans makefile
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
