/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.scheduler;
import java.text.ParseException;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.JobDetail;
import org.quartz.CronTrigger;
/**
 *
 * @author sada3260
 */
public class MailSendingScheduler {
    public MailSendingScheduler(){
        try {
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            JobDetail jd = new JobDetail("myjob", scheduler.DEFAULT_GROUP, MailSendingJob.class);

            //Simple trigger is used to do simplified tasks like running a application repeatedly over a
            //certain interval of time
            /*SimpleTrigger trigger=new SimpleTrigger("mytrigger",scheduler.DEFAULT_GROUP,new Date(),
            null,SimpleTrigger.REPEAT_INDEFINITELY,5L*1000L);*/

            CronTrigger trigger = new CronTrigger("cronTrigger", scheduler.DEFAULT_GROUP, "0 15 0 * * ?");
            scheduler.scheduleJob(jd, trigger);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }
}
