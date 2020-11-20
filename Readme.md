Utilize spring data Apache solr sample(https://spring.io/projects/spring-data-solr#samples) to create POC.

Configure vscode using the link: https://code.visualstudio.com/docs/java/java-spring-boot


Step to use Data-import-handler of Solr for SQL server.
1. Download the MS SQL JDBC driver https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15. Extract and copy the sql driver library(eg. `mssql-jdbc-8.4.1.jre8.jar`) in the following location `solr-8.5.1\server\lib\`

1. Start solr server by `./bin/solr start -p 8983` in Powershell.
2. Create a solr core by `./bin/solr create -c person`.
3. Add a data import handler in the newly created core by adding following code in `solr-8.5.1\server\solr\person\conf\solrconfig.xml` at appropriate locations.
```
  <lib dir="${solr.install.dir:../../../..}/dist/" regex="solr-dataimporthandler-.*\.jar" />
  <lib dir="${solr.install.dir:../../../..}/dist/" regex="solr-dataimporthandler-extras-\d.*\.jar" />  
   
  <requestHandler name="/dataimport" class="org.apache.solr.handler.dataimport.DataImportHandler">
    <lst name="defaults">
    <str name="config">db-poc-config.xml</str>
    </lst>
  </requestHandler>
```
4. Add a `db-poc-config.xml` file as mentioned in the data import handler at the same location: `solr-8.5.1\server\solr\person\conf`
5. Put the following code in the file `db-poc-config.xml`.
```
<dataConfig>
  <dataSource type="JdbcDataSource" 
    driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
    url="jdbc:sqlserver://KAPILGAUTAMIN;databaseName=AdventureWorks2014;"  
    username="pocuser"
    password="password@123"
  />  
  <document>
    <entity name="Person" query="SELECT FirstName,LastName FROM AdventureWorks2014.Person.Person">
      <field column="FirstName" name="FirstName" />
      <field column="LastName" name="LastName" />
    </entity>
  </document>
</dataConfig>
```

Note: The entity can be direclty modeled similar to the SQL query as you like. Just remember to keep the same name in `db-poc-config.xml` as the name in `managed-schema` file.

6. Add the following fields for the indexed fields from the SQL in `solr-8.5.1\server\solr\person\conf\managed-schema` file
```
<field name="FirstName" type="text_general" multiValued="false" indexed="true" stored="true"/>  
<field name="LastName" type="text_general" indexed="true" stored="true" multiValued="false"/> 
```
7. Go to `http://localhost:8983` or whichever port you started SOLR with, and select the 'person' core option from the dropdown under `Thread Dump`.
8. Go to the `Dataimport` tab. 
8.1. If the tab gives the message: `The solrconfig.xml file for this index does not have an operational DataImportHandler defined!`. Go to the `Core Admin` tab on the left handside between `Logging` and `Java Properties`.  Select the person core and Reload it. Now go back to the  `Dataimport tab` by selecting the `person` core from the dropdown. This time the tab should load, otherwise there is some other problem in your files.
9. Click the execute tab by selecting the `full-import` command. In few seconds all the rows from SQL are read and processed into the SOLR core.
9.1. You may choose to enable options like `Verbose`, `Clean`, `Commit`, `Optimise`, `Debug` depending on your requirement. 
10. Check the indexed documents in the `Overview` tab under the `person` core. 

############################################################################
Read only in case you do not have a SQL server authentication method for for SQL server management studio.
1. You will have a way to login through the Windows authentication way for now. Login using that.
2. Once you have connected to the server, you can now create a SQL authentication method for data-import-handler of Solr.
3. Right click on your server name, and open the last option `Properties`. In the dialogue box that opens, on the left-side pane, go to `Security` page, and select the   `SQL Server and Windows Authentication mode` and click OK.
4. Select OK to the message: `Some of your configuration changes will not take effect until SQL Server is restarted.`
5. Now, again right click on the server name, and choose `Restart`. It will prompt you to restart SQL service. Authorise the prompt. Wait until the service starts again.
6. Now we need to create a SQL username and password authentication. For this, Under the server name there are multiple collapsed options like `Database`, `Security`, `Server Objects`, etc.
7. Expand the `Security` tab and then subsequently the `Logins` tab. Right click on the `Logins` option and select `New Login... `.
8. In the dialogue box that opens up Put in the login name in the text box, and select `SQL server authentication` and put in the password and click OK.
9. Next on the left-side pane in this dialogue box, go to `Server Roles` page and check the `sysadmin` option. For more limited functionality other options can be tested.
10. Now disconnect and try to re-login using the SQL server authentication. This will serve you well for the data-source option for the data-import handler configuration file(`db-poc-config.xml`).

Possible errors while doing above:
1. `A connection was successfully established with the server, but then an error occurred
during the login process. (provider: Shared Memory Provider, error: 0 - No process is
on the other end of the pipe.)`
Refer: 
a.) https://stackoverflow.com/questions/27267658/no-process-is-on-the-other-end-of-the-pipe-sql-server-2012
b.) https://www.mytecbits.com/microsoft/sql-server/where-is-sql-server-configuration-manager
c.) https://stackoverflow.com/questions/33590030/the-tcp-ip-connection-to-the-host-localhost-port-1433-has-failed-error-need-as

Reference articles:
1. https://lucene.apache.org/solr/guide/8_0/uploading-structured-data-store-data-with-the-data-import-handler.html#uploading-structured-data-store-data-with-the-data-import-handler
2. https://docs.microsoft.com/en-us/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver15
3. https://docs.microsoft.com/en-us/sql/database-engine/configure-windows/change-server-authentication-mode?view=sql-server-ver15
3. https://akshaybarve.wordpress.com/2017/11/15/loading-data-from-sql-server-to-solr-with-a-data-import-handler/
############################################################################