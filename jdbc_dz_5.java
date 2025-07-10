import java.sql.*;

public class jdbc_dz_5 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "12345";

        try (Connection connection = DriverManager.getConnection(
                url,user,password
        )){
            Statement statement = connection.createStatement();
            String contragentNumber = """
                    SELECT COUNT(*) FROM organisation WHERE inn_id > 0;
                    """;
            String resultTable = """
                    SELECT COUNT(ogrn) AS ogrn_count,name,inn_id FROM organisation GROUP BY ogrn,name,inn_id;  
                    """;
            String selectAllTable = """
                    SELECT inn_id, name, adress, contacts FROM organisation;
                    """;
            ResultSet resultSetCount = statement.executeQuery(contragentNumber);
            while (resultSetCount.next()) {
                int organisationCount = resultSetCount.getInt(1);
                System.out.println("Всего в базе контрагентов(организаций) содержится "+organisationCount+" записей.");
            }
            ResultSet resultSetTable = statement.executeQuery(resultTable);
            while (resultSetTable.next()) {
                int jurickTable = resultSetTable.getInt(1);
                String name = resultSetTable.getString("name");
                int inn_id = resultSetTable.getInt(3);
                System.out.print("Сортировка юрлиц: " + jurickTable + " Юрлицо :" + name + " ИНН: " + inn_id + "\n");
            }
            ResultSet resultSetAll = statement.executeQuery(selectAllTable);
            while (resultSetAll.next()) {
                int inn = resultSetAll.getInt(1);
                String name = resultSetAll.getString("name");
                String adress = resultSetAll.getString("adress");
                String contacts = resultSetAll.getString("contacts");
                System.out.println(inn + " " + name + " " + adress + " " + contacts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
