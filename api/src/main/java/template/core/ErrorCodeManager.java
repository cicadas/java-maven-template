package template.core;

import au.com.bytecode.opencsv.CSVReader;
import template.util.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;

/**
 * @author zhouzh
 */
public class ErrorCodeManager {
    private static final String ERROR_FILE = "/error_code.csv";
    private Hashtable<Integer, ErrorCode> list = new Hashtable<>();

    private ErrorCodeManager() {
        CSVReader reader = new CSVReader(new InputStreamReader(Config.class.getResourceAsStream(ERROR_FILE)));
        try {
            List<String[]> entities = reader.readAll();
            for (int i = 1; i < entities.size(); i++) {
                Integer key = Integer.valueOf(entities.get(i)[1]);
                list.put(key, new ErrorCode(Integer.valueOf(entities.get(i)[0]), key, entities.get(i)[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error("Failed to load error code CSV file.");
        }

    }

    private static final ErrorCodeManager instance = new ErrorCodeManager();

    public static ErrorCodeManager instance() {
        return instance;
    }

    public ErrorCode get(int code) {
        return list.get(code);
    }
}
