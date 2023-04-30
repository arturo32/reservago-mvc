# reservago-mvc
Projeto feito em Java com Spring para a matéria de Arquiteturas Reativas.

## How to initialize the environment
You will need the following technologies installed in your computer:
- Maven
- PostgreSQL or Docker
- Java 20

### Adding `reservago-base` to maven

First, you'll need to add the `reservago-base` to your local maven repository. You can do that by running the following command in the `reservago-base` directory:
```
mvn clean install
```

Obs: If you installed the JDK 20 using your IDE, you'll need to put its path in the `JAVA_HOME` environment variable. If you are using Linux this can be done temporarily by running this command:
```
export JAVA_HOME=path_to_your_jdk
```

### Configuring the database
If you already have PostgreSQL installed in your computer, you may have to overwrite the username and password in the `application.properties` file in the `admin` project. After that, just create a database named `admin`.

If you have docker you can just run the bash script to build the image described in the `Dockerfile` of the `admin` project: `create_img-db.sh`. After the image is built, run it with the bash script `run-db.sh`.
