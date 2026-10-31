
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TeamHead {

    int id;
    String name;
    String email;

    public TeamHead(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void createProject(Scanner sc) {

        try {
            System.out.print("Enter Project ID: ");
            int projectId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Project Name: ");
            String projectName = sc.nextLine();

            System.out.print("Enter Project Details: ");
            String details = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO teamhead " +
                "(headname, projectid, projectname, projectdetails) " +
                "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, projectId);
            ps.setString(3, projectName);
            ps.setString(4, details);

            ps.executeUpdate();

            System.out.println("Project Created Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMember(Scanner sc) {

        try {
            System.out.print("Enter Project ID: ");
            int projectId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Member Name: ");
            String memberName = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO teammember " +
                "(projectid, membername, progress) VALUES (?, ?, 0)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, projectId);
            ps.setString(2, memberName);

            ps.executeUpdate();

            System.out.println("Member Added Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewProjects() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM teamhead";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Project ID: " +
                    rs.getInt("projectid"));

                System.out.println("Project Name: " +
                    rs.getString("projectname"));

                System.out.println("Project Details: " +
                    rs.getString("projectdetails"));

                System.out.println("--------------------");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
