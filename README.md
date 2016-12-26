# AthenaConnection

AthenaJDBCConnector is an interface for connecting Amazon Athena(https://aws.amazon.com/jp/athena/) with JDBC. You can use the software from shell script.  
 
Please check Amazon's reference too.  
http://docs.aws.amazon.com/athena/latest/ug/connect-with-jdbc.html 

## Support
Java 1.8.0_73

```
Java(TM) SE Runtime Environment (build 1.8.0_73-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)
```

## Setup
Install Java 1.8.0 (CentOS)
```
$ sudo yum install java-1.8.0-openjdk
```

## How to use
### Replace following codes with your parameters
AthenaConnection.java
```
info.put("s3_staging_dir", "PATH_TO_STAGING_DIR");
info.put("log_path", "PATH_TO_LOGFILE");
info.put("user", "YOUR API KEY");
info.put("password", "YOUR API SECRET");
String databaseName = "DB NAME";
```

* s3_staging_dir: The Amazon S3 location to which your query output is written.
* log_path: Local path of the Athena JDBC driver logs.
* user: Your AWS API Key.
* password: Your AWS API Secret.
* databaseName: Athena Database Name. (Please create a database in advance)

### Compile AthenaConnection.java
```
$ javac -cp ".:lib/AthenaJDBC41-1.0.0.jar:lib/java-json-schema.jar" AthenaConnection.java
$ jar cvfm AthenaConnection.jar manifest.mf  AthenaConnection.class
```

### Call jar file
Call AthenaConnection.jar setting your Athena query to the first argument.
```
java -jar AthenaConnection.jar "SELECT * FROM ......."
```

### Get response
AthenaConnection.jar will return query result as JSONArray. 

## License
MIT
