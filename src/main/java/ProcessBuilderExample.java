import java.io.*;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 28/3/17
 */
public class ProcessBuilderExample {
    public static void main(String[] args) throws InterruptedException,
            IOException {
//        -m "pmv" -d "host:localhost;user:root;password:itcm123;db_name:itcm_database;table_name:data_sample"
        ProcessBuilder pb = new ProcessBuilder("python", "main.py", "-m", "pmv", "-d",
                "MYSQL;host:localhost;user:root;password:itcm123;db_name:itcm_database;table_name:data_sample");
        pb.directory(new File("src/main/python"));
        System.out.println("Run echo command");
        Process process = pb.start();
        int errCode = process.waitFor();
        System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
        System.out.println("Echo Output:\n" + output(process.getInputStream()));
    }

    private static String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}
