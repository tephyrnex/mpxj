//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2022.12.01 at 11:46:04 AM GMT
//

package net.sf.mpxj.planner.schema;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1 extends XmlAdapter<String, String>
{

   @Override public String unmarshal(String value)
   {
      return (net.sf.mpxj.planner.DatatypeConverter.parseString(value));
   }

   @Override public String marshal(String value)
   {
      return (net.sf.mpxj.planner.DatatypeConverter.printString(value));
   }

}