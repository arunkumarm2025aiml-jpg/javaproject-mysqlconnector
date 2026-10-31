
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Project {

    int projectId;
    String projectName;
    String details;

    ArrayList<TeamMember> members = new ArrayList<>();

    public Project(int projectId, String projectName, String details) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.details = details;
    }

    public void addMember(TeamMember member) {

        members.add(member);
    }

    public void displayProject() {

        System.out.println("Project ID: " + projectId);
        System.out.println("Project Name: " + projectName);
        System.out.println("Project Details: " + details);

        System.out.println("Team Members:");

        for (TeamMember member : members) {

            System.out.println(member.name);
            System.out.println("Progress: " + member.progress + "%");
        }
    }

    public static void viewProjectDetails(int id) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM teamhead WHERE projectid=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Project p = new Project(
                    rs.getInt("projectid"),
                    rs.getString("projectname"),
                    rs.getString("projectdetails")
                );

                String memberSql =
                    "SELECT * FROM teammember WHERE projectid=?";

                PreparedStatement ms = con.prepareStatement(memberSql);

                ms.setInt(1, id);

                ResultSet mr = ms.executeQuery();

                while (mr.next()) {

                    TeamMember member = new TeamMember(
                        mr.getInt("memberid"),
                        mr.getString("membername"),
                        "",
                        mr.getInt("progress")
                    );

                    p.addMember(member);
                }

                p.displayProject();

            } else {
                System.out.println("Project Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
