package template.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/26/12
 * Time: 1:08 PM
 * Class for segment id.  SegmentID and CategoryID are the same thing in Targeting.
 * PixelID is from RMX.  AptID is from APT.  Both of them need mapping to CategoryID in Targeting.
 * Category_id is number(38,0) in database, need add same logical check in TWS.
 */
public class Id implements Serializable {
    @JsonProperty("id")
    private String value; // In Targeting DB, it is number(38,0)

    public Id() {
    }

    public Id(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public long toLong() {
        return Long.valueOf(getValue());
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
