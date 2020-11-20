Utilize spring data Apache solr sample(https://spring.io/projects/spring-data-solr#samples) to create POC.

Configure vscode using the link: https://code.visualstudio.com/docs/java/java-spring-boot


Step to use Data-import-handler of Solr for SQL server.
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
6. Add the following fields for the indexed fields from the SQL in `solr-8.5.1\server\solr\person\conf\managed-schema` file
```
<field name="FirstName" type="text_general" multiValued="false" indexed="true" stored="true"/>  
<field name="LastName" type="text_general" indexed="true" stored="true" multiValued="false"/> 
```
7. Go to `http://localhost:8983` or whichever port you started SOLR with, and select the 'person' core option from the dropdown under `Thread Dump`.
8. Go to the `Dataimport` tab. 
8.1. If the tab gives the message: `The solrconfig.xml file for this index does not have an operational DataImportHandler defined!`. Go to the `Core Admin` tab on the left handside between `Logging` and `Java Properties`.  Select the person core and Reload it. Now go back to the  `Dataimport tab` by selecting the `person` core from the dropdown. This time the tab should load, otherwise there is some other problem in your files.
9. Click the execute tab, In few seconds all the rows from SQL are read and processed into the SOLR core.
10. Check the indexed documents in the `Overview` tab under the `person` core. 
