/* 
 * Copyright (c) 2017 Nourreddine HOUARI (houarinourredine@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hin.trackrepository.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hin.trackrepository.message.TrackMessage;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author nhouari
 *
 */
public class TrackDeSerializer implements Deserializer<TrackMessage>{

  @Override
  public void close() {}

  @Override
  public void configure(Map<String, ?> arg0, boolean arg1) {}

  @Override
  public TrackMessage deserialize(String arg0, byte[] arg1) {
    try {
      return TrackMessage.parseFrom(arg1);
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
      throw new RuntimeException("Received unparseable track " + e.getMessage(), e);
    }
  }

}
