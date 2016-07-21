/**
 * Copyright (c) 2015 DataTorrent, Inc.
 * All rights reserved.
 */

package com.example.mydtapp;

import com.datatorrent.lib.io.fs.AbstractFileOutputOperator;

public class FileLineOutputOperator extends AbstractFileOutputOperator<Object>
{
  @Override
  protected String getFileName(Object input)
  {
    return context.getId()+"_"+"op.dat";
  }

  @Override
  protected byte[] getBytesForTuple(Object input)
  {
    return (input.toString()+"\n").getBytes();
  }
}
