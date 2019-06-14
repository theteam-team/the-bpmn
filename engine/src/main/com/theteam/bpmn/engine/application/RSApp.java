package com.theteam.bpmn.engine.application;  

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.theteam.bpmn.engine.api.Load;
import com.theteam.bpmn.engine.api.StartWorkflow;

//import io.swagger.v3.oas.integration.SwaggerConfiguration;

/** Add classes manually to end point /api */
//@ApplicationPath( "api" )
public class RSApp extends Application
{
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        
        resources.add(Load.class);
        resources.add(StartWorkflow.class);

        //resources.add( ApiListingResource.class );
        //resources.add( ApiDeclarationProvider.class );
        //resources.add( ApiListingResourceJSON.class );
        //resources.add( ResourceListingProvider.class );
        
        //swaggerConfiguration();

        return resources;
    }

    private void swaggerConfiguration( )
   {
      //SwaggerConfiguration swaggerConfig = new SwaggerConfiguration();
      //swaggerConfig.

      //ConfigFactory.setConfig( swaggerConfig );

      //swaggerConfig.












      //swaggerConfig.setApiVersion( "0.0.1" ); 
      //swaggerConfig.setBasePath( "http://localhost:8080/engine/api" );

      //ScannerFactory.setScanner( new DefaultJaxrsScanner( ) );
      //ClassReaders.setReader( new DefaultJaxrsApiReader( ) );
   }
}