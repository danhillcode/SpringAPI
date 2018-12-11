package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FirstController {

    private static final String template = "Hola, %s!";
    private final AtomicLong counter = new AtomicLong();

    public static String viewTable2(Connection con) throws SQLException {

        String query = "Select * from company";
        try (Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String salary = rs.getString("salary");
               /* int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");*/
                System.out.println(salary);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        //change this to return the result need to access inside the result set
        return query;
    }


    @RequestMapping("/first")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) throws ClassNotFoundException, SQLException {


        String url = "jdbc:postgresql://localhost:5435/";
        String user = "danielhill";
        String password = "";

        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, password);
        String result = viewTable2(conn);




        return new Greeting(counter.incrementAndGet(),
                String.format(template, result));
    }
}
