package template.core;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/26/12
 * Time: 1:13 PM
 * Logging related info class.  For standard info about: created by, created time, last modified by
 * last modified time and obsolete time
 */
public class LogInfo {
    public LogInfo() {
    }

    private Date createdTime;
    private Date lastModifiedTime;
    private User createdUser;  // mapping to `created by`
    private User lastModifiedUser; // mapping to `last modified by`
    private Date obsoleteTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(final Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        if (lastModifiedTime == null)
            lastModifiedTime = new Date();
        return lastModifiedTime;
    }

    public void setLastModifiedTime(final Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }


    public User getCreatedUser() {
        return createdUser;
    }

    public LogInfo setCreatedUser(final User createdUser) {
        this.createdUser = createdUser;
        return this;
    }

    public User getLastModifiedUser() {
        if(lastModifiedUser == null){
            lastModifiedUser = new User();
        }
        return lastModifiedUser;
    }

    public LogInfo setLastModifiedUser(final User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
        return this;
    }

    public Date getObsoleteTime() {
        return obsoleteTime;
    }

    public void setObsoleteTime(final Date obsoleteTime) {
        this.obsoleteTime = obsoleteTime;
    }
}
