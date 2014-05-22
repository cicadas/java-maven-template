package template.test.util;

import java.io.*;

public class FileHelper {

    public static boolean writeStringToFile(String filepath, String content) {
        try {
            File file = new File(filepath);
            if (file.exists())
                file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Failed to write content to file: " + filepath);
            e.printStackTrace();
            return false;
        }
    }

    public static String getContentFromFileInJar(String path) throws IOException {
        BufferedReader reader = getBufferedReaderInJar(path);
        String line;
        String content = "";
        // remove empty lines.
        while ((line = reader.readLine()) != null) {
            if (!line.trim().equals(""))
                content += line;
        }
        return content;
    }

    private static BufferedReader getBufferedReaderInJar(String pathInJar) {
        InputStream input = FileHelper.class.getResourceAsStream(pathInJar);
        return new BufferedReader(new InputStreamReader(input));
    }
}
