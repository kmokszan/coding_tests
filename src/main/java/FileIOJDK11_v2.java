import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIOJDK11_v2 {
    public static void main(String[] args) {
        // creating path
        try

        {
            // reading file from given path
            String content = Files.readString(Paths.get("src/main/java/FileIOJDK11_v2.java"));
            String a = content.replaceAll("FileIOJDK11_v2", "FileIOJDK11_v2_v2");
            Files.writeString(Paths.get("src/main/java/FileIOJDK11_v2_v2.java"), a);
            // printing the content
            System.out.println(content);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
