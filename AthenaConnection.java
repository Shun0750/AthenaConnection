import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import java.util.Properties;
import com.amazonaws.athena.jdbc.AthenaDriver;

import org.json.JSONObject;
import org.json.JSONArray;

public class AthenaConnection {

  static final String athenaUrl = "jdbc:awsathena://athena.us-east-1.amazonaws.com:443";

  public static JSONArray convertToJSON(ResultSet resultSet) throws Exception {
    JSONArray jsonArray = new JSONArray();
    while (resultSet.next()) {
        int total_rows = resultSet.getMetaData().getColumnCount();
        JSONObject obj = new JSONObject();
        for (int i = 0; i < total_rows; i++) {
            obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                    .toLowerCase(), resultSet.getObject(i + 1));
        }
        jsonArray.put(obj);
    }
    return jsonArray;
  }

  public static void main(String[] args) {

      Connection conn = null;
      Statement statement = null;
      String sql = args[0];

      try {
          Class.forName("com.amazonaws.athena.jdbc.AthenaDriver");
          Properties info = new Properties();
          info.put("s3_staging_dir", "PATH_TO_STAGING_DIR");
          info.put("log_path", "PATH_TO_LOGFILE");
          info.put("user", "YOUR API KEY");
          info.put("password", "YOUR API SECRET");
          String databaseName = "DB NAME";

          conn = DriverManager.getConnection(athenaUrl, info);

          statement = conn.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          JSONArray jsonArray = convertToJSON(rs);
          System.out.println(jsonArray);

          rs.close();
          conn.close();
      } catch (Exception ex) {
          ex.printStackTrace();
      } finally {
          try {
              if (statement != null)
                  statement.close();
          } catch (Exception ex) {

          }
          try {
              if (conn != null)
                  conn.close();
          } catch (Exception ex) {

              ex.printStackTrace();
          }
      }
  }
}
