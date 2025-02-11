/*
 * file:       ProjectCalendarDays.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software 2022
 * date:       2022-05-12
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj;

import java.util.List;

import net.sf.mpxj.common.DateHelper;

/**
 * This class represents a basic working week, with no exceptions.
 */
public abstract class ProjectCalendarDays
{
   /**
    * Calendar name.
    *
    * @param name calendar name
    */
   public void setName(String name)
   {
      m_name = name;
   }

   /**
    * Calendar name.
    *
    * @return calendar name
    */
   public String getName()
   {
      return m_name;
   }

   /**
    * This method retrieves the calendar hours for the specified day.
    * Note that this method only returns the hours specification for the
    * current calendar.If this is a derived calendar, it does not refer to
    * the base calendar.
    *
    * @param day Day instance
    * @return calendar hours
    */
   public ProjectCalendarHours getCalendarHours(Day day)
   {
      return m_hours[day.getValue() - 1];
   }

   /**
    * This is a convenience method used to add a default set of calendar
    * hours to a calendar.
    */
   public void addDefaultCalendarHours()
   {
      for (Day day : Day.values())
      {
         addDefaultCalendarHours(day);
      }
   }

   /**
    * Convenience method to set up a standard working week.
    */
   public void addDefaultCalendarDays()
   {
      setWorkingDay(Day.SUNDAY, false);
      setWorkingDay(Day.MONDAY, true);
      setWorkingDay(Day.TUESDAY, true);
      setWorkingDay(Day.WEDNESDAY, true);
      setWorkingDay(Day.THURSDAY, true);
      setWorkingDay(Day.FRIDAY, true);
      setWorkingDay(Day.SATURDAY, false);
   }

   /**
    * This is a convenience method used to add a default set of calendar
    * hours to a calendar.
    *
    * @param day Day for which to add default hours for
    */
   public void addDefaultCalendarHours(Day day)
   {
      ProjectCalendarHours hours = addCalendarHours(day);

      if (day != Day.SATURDAY && day != Day.SUNDAY)
      {
         hours.add(DEFAULT_WORKING_MORNING);
         hours.add(DEFAULT_WORKING_AFTERNOON);
      }
   }

   /**
    * Used to add working hours to the calendar.
    *
    * @param day target day
    * @return new ProjectCalendarHours instance
    */
   public ProjectCalendarHours addCalendarHours(Day day)
   {
      ProjectCalendarHours bch = new ProjectCalendarHours();
      m_hours[day.getValue() - 1] = bch;
      return bch;
   }

   /**
    * Used to remove working hours from the calendar.
    *
    * @param day target day
    */
   public void removeCalendarHours(Day day)
   {
      m_hours[day.getValue() - 1] = null;
   }

   /**
    * This method allows the retrieval of the actual working day flag,
    * which can take the values DEFAULT, WORKING, or NONWORKING. This differs
    * from the isWorkingDay method as it retrieves the actual flag value.
    * The isWorkingDay method will always refer back to the base calendar
    * to get a boolean value if the underlying flag value is DEFAULT. If
    * isWorkingDay were the only method available to access this flag,
    * it would not be possible to determine that a resource calendar
    * had one or more flags set to DEFAULT.
    *
    * @param day required day
    * @return value of underlying working day flag
    */
   public DayType getCalendarDayType(Day day)
   {
      return m_days[day.getValue() - 1];
   }

   /**
    * This is a convenience method provided to allow the day type
    * to be set as working or non-working by supplying a simple Boolean argument.
    *
    * @param day required day
    * @param working flag indicating if the day is a working day
    */
   public void setWorkingDay(Day day, boolean working)
   {
      setCalendarDayType(day, (working ? DayType.WORKING : DayType.NON_WORKING));
   }

   /**
    * Set the type of a given day.
    *
    * @param day required day
    * @param type day type flag
    */
   public void setCalendarDayType(Day day, DayType type)
   {
      m_days[day.getValue() - 1] = type;

      switch (type)
      {
         case DEFAULT:
         {
            // Default days should not have hours
            removeCalendarHours(day);
            break;
         }

         case NON_WORKING:
         {
            // Non-working days should have an empty list
            List<DateRange> hours = getCalendarHours(day);
            if (hours == null)
            {
               addCalendarHours(day);
            }
            else
            {
               hours.clear();
            }
            break;
         }

         // TODO: Update implementation to derive DayType from hours
         case WORKING:
         {
            //            // Ensure working days have some hours
            //            List<DateRange> hours = getCalendarHours(day);
            //            if (hours == null)
            //            {
            //               hours = addCalendarHours(day);
            //            }
            //
            //            if (hours.isEmpty())
            //            {
            //               hours.add(DEFAULT_WORKING_MORNING);
            //               hours.add(DEFAULT_WORKING_AFTERNOON);
            //            }
         }
      }
   }

   /**
    * Working week name.
    */
   private String m_name;

   /**
    * Working hours for each day.
    */
   private final ProjectCalendarHours[] m_hours = new ProjectCalendarHours[7];

   /**
    * Working/non-working/default flag for each day.
    */
   private final DayType[] m_days = new DayType[7];

   /**
    * Constants representing the default working morning and afternoon hours.
    */
   public static final DateRange DEFAULT_WORKING_MORNING = new DateRange(DateHelper.getTime(8, 0), DateHelper.getTime(12, 0));
   public static final DateRange DEFAULT_WORKING_AFTERNOON = new DateRange(DateHelper.getTime(13, 0), DateHelper.getTime(17, 0));
}
