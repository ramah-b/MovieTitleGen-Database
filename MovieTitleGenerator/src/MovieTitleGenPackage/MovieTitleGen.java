package MovieTitleGenPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class MovieTitleGen {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:oracle:thin:system/pasword@localhost:1521/xe";

		// properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", "TestUserDB");
		props.setProperty("password", "password");

		// creating connection to Oracle database using JDBC
		Connection conn = DriverManager.getConnection(url, props);

		System.out.println("Myxyllplyk's Random Movie Title Generator\n");

		String sqlAdj = "SELECT * FROM ( SELECT * FROM Adjectives LEFT OUTER JOIN Nounes on Adjectives.Adjectives != Nounes.Noun ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum =1";

		PreparedStatement preStatement = conn.prepareStatement(sqlAdj);

		ResultSet result = preStatement.executeQuery();
		
		String title="";
		
		while (result.next()) {
			System.out.println("Your movie title is: "
					+ result.getString("Adjectives") + " " + result.getString("Noun"));
			title = result.getString("Adjectives") + result.getString("Noun");
			
		}
	}

}