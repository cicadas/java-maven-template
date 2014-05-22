package template.core;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/26/12
 * Time: 11:25 AM
 * Name structure for segment
 */
public class Name {
    private String originalName;

    /* In old TWS, the full name is something like SRT_PIXEL_PREFIX + segment.getName()
    *  SRT_PIXEL_PREFIX is the a static string: "Yahoo/Search Retargeting/API"
    *  which related with Yinst settings: miners_targeting_ws_segment__srt_pixel_prefix
    *  */
    private String fullName;
    private String displayName;

    public Name(String name) {
        this(name, name, name);
    }

    public Name(String originalName, String fullName, String displayName) {

        this.originalName = originalName;
        this.fullName = fullName;
        this.displayName = displayName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void set(String name) {
        displayName = name;
        originalName = name;
        fullName = name;
    }
}
