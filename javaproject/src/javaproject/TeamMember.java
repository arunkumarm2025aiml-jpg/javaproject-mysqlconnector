
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TeamMember extends TeamHead {

    int progress;

    public TeamMember(int id, String name, String email, int progress) {

        super(id, name, email);

        this.progress = progress;
    }

    public void updateProgress(Scanner sc) {

        try {
            System.out.print("Enter Member ID: ");
            int memberId = sc.nextInt();

            System.out.print("Enter Progress (0-100): ");
            progress = sc.nextInt();

            if (progress < 0 || progress > 100) {
                System.out.println("Invalid Progress!");
                return;
            }

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE teammember SET progress=? WHERE memberid=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, progress);
            ps.setInt(2, memberId);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Progress Updated!");
            } else {
                System.out.println("Member Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewMyProject(Scanner sc) {

        try {
            System.out.print("Enter Member ID: ");
            int memberId = sc.nextInt();

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM teammember WHERE memberid=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, memberId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Member ID: " +
                    rs.getInt("memberid"));

                System.out.println("Member Name: " +
                    rs.getString("membername"));

                System.out.println("Project ID: " +
                    rs.getInt("projectid"));

                System.out.println("Progress: " +
                    rs.getInt("progress") + "%");

            } else {
                System.out.println("Member Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
