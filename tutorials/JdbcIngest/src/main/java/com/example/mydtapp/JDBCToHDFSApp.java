package com.example.mydtapp;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.Context.PortContext;
import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.db.jdbc.JdbcStore;
import com.datatorrent.lib.db.jdbc.*;

@ApplicationAnnotation(name = "JDBCToHDFSApp")
public class JDBCToHDFSApp implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    
    
    JdbcPollInputOperator poller = dag.addOperator("JdbcPoller", new JdbcPollInputOperator());

    JdbcStore store = new JdbcStore();
    poller.setStore(store);

    FileLineOutputOperator writer = dag.addOperator("Writer", new FileLineOutputOperator());
    dag.setInputPortAttribute(writer.input, PortContext.PARTITION_PARALLEL, true);
    writer.setRotationWindows(60);

    dag.addStream("dbupdates", poller.outputPort, writer.input);

  }
}
