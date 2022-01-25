package com.example.toDoApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages={"com.example.toDoApp.repository"})
public class MyCouchbaseConfig extends AbstractCouchbaseConfiguration {

//    @Override
//    protected List<String> getBootstrapHosts() {
//        return Arrays.asList("127.0.0.1");
//    }
/*    protected List<String> getBootstrapHosts() {
        return Arrays.asList("cb.jesccpnskke5uc5c.cloud.couchbase.com");
    }*/

    @Override
    public String getConnectionString() {
      //  return "couchbase://127.0.0.1";
        return "127.0.0.1";
       // return "cb.jesccpnskke5uc5c.cloud.couchbase.com";
    }

    @Override
    public String getUserName() {
        //return "toDoApp";
        //return "toDoApp";
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "toDo_123";
    }

    @Override
    public String getBucketName() {
        return "toDoApp";
    }

   /* @Override
    protected String getBucketName() {
        return "toDoApp";
    }

    @Override
    protected String getBucketPassword() {
        return "toDo_123";
    }
*/
//    @Override
//    protected String getUsername() {
//        return "toDoApp";
//    }

}