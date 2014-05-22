package template.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.lang3.StringUtils;
import template.util.JsonHelper;
import template.util.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzh
 *         The class for API configuration.
 */
@ThreadSafe
public class Config {

    static final String CONFIG_FILE = "/config.json";
    static ConcurrentHashMap<String, String> configs = new ContextMap<>();
    public static final Config INSTANCE = new Config();

    private Config() {
        try {
            setConfigFile(Config.class.getResourceAsStream(CONFIG_FILE));
        } catch (IOException e) {
            Logger.error("Cannot found default configuration file.");
            e.printStackTrace();
        }
    }

    public void setConfigFile(InputStream inputStream) throws IOException {
        ObjectMapper mapper = JsonHelper.getMapper();
        JsonNode rootNode = mapper.readTree(inputStream);
        Iterator<JsonNode> it = rootNode.elements();
        configs.clear();
        while (it.hasNext()) {
            ObjectNode node = (ObjectNode) it.next();
            String key = node.get("key").asText();
            String value = node.get("value").asText();
            configs.put(key, value);
        }
    }

    public Config set(String key, String value) {
        Logger.debug("Set key:" + key + " value:" + value);
        if (StringUtils.isNotEmpty(key)) {
            configs.put(key, value);
        }
        return this;
    }

    public String get(String key) {
        String result = StringUtils.EMPTY;
        Logger.debug("Get key:" + key);
        if (StringUtils.isNotEmpty(key)) {
            String sysValue = System.getenv(key);
            if (StringUtils.isNotEmpty(sysValue))
                result = sysValue;
            else if (configs.get(key) == null)
                result = null;
            else
                result = configs.get(key);
        }
        return result;
    }


    // domain level report path setting
    public final static String DOMAIN_REPORT_DAILY_PATH = "domain_report_daily_path";
    public final static String DOMAIN_REPORT_MONTHLY_PATH = "domain_report_monthly_path";
    public final static String DOMAIN_REPORT_RAW_PATH = "domain_report_raw_path";
    public final static String DOMAIN_REPORT_RETRIEVE_REPORT_INTERVAL = "domain_report_retrieve_report_interval";
    public final static String BOW_PIXEL_PREFIX = "bow_pixel_prefix";

    //Metadata Search settings
    public final static String METADATA_INDEX_DIR = "metadata_index_dir";

    //Segment Search Settings
    public final static String SEGMENT_INDEX_DIR = "segment_index_dir";
    public final static String SEGMENT_DATA_FILE = "segment_data";
    public final static String SEGMENT_INDEX_TIMESTAMP_FILE = "segment_index_timestamp_file";
    public final static String SEGMENT_INDEX_UPDATE_INTERVAL = "segment_index_update_interval";
    public final static String SEGMENT_INDEX_JOB_START = "segment_index_job_start";

    //AEX setting
    public final static String AEX_COOKIE_LIST_PATH = "aex_cookielist_path";

    public final static String BOUNCER_URL = "bouncer_url";

    //MetaGraph Settings
    public final static String METAGRAPH_URL = "metagraph_url";
    public final static String METAGRAPH_INDEXING_URLS = "metagraph_indexing_urls";
    public final static String METAGRAPH_RESOURCE_TYPES = "metagraph_resource_types";

    //CatsysETL Settings
    public final static String ADVERTISER_ID_LENGTH = "catsysetl_advertiser_id_length";
    public final static String CREATIVE_ID_LENGTH = "catsysetl_creative_id_length";
    public final static String LIB_CRTV_ID_LENGTH = "catsysetl_lib_crtv_id_length";

    //InstantReach Settings

    public final static String INSTANT_REACH_HOSTNAME = "BOW_INSTANT_REACH_HOST_NAME";
    public final static String INSTANT_REACH_HTTPSCHEME = "BOW_INSTANT_REACH_HTTP_SCHEME";
    public final static String INSTANT_REACH_PORT = "BOW_INSTANT_REACH_PORT";
    public final static String INSTANT_REACH_APIPATH = "BOW_INSTANT_REACH_API_PATH";
    public final static String INSTANT_REACH_YCA_COMMAND = "BOW_INSTANT_REACH_YCA_COMMAND";
    public final static String INSTANT_REACH_YCA_ROLE = "BOW_INSTANT_REACH_YCA_COMMAND";


    // Taxo related
    public final static String TAXO_SCHEMA = "taxonomy_schema_prefix";


}
