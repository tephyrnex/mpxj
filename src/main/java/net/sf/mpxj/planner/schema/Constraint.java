//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2022.12.01 at 11:46:04 AM GMT
//

package net.sf.mpxj.planner.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * &lt;p&gt;Java class for anonymous complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 *       &amp;lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "") @XmlRootElement(name = "constraint") public class Constraint
{

   @XmlAttribute(name = "type", required = true) @XmlJavaTypeAdapter(Adapter1.class) protected String type;
   @XmlAttribute(name = "time", required = true) @XmlJavaTypeAdapter(Adapter1.class) protected String time;

   /**
    * Gets the value of the type property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getType()
   {
      return type;
   }

   /**
    * Sets the value of the type property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setType(String value)
   {
      this.type = value;
   }

   /**
    * Gets the value of the time property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getTime()
   {
      return time;
   }

   /**
    * Sets the value of the time property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setTime(String value)
   {
      this.time = value;
   }

}
