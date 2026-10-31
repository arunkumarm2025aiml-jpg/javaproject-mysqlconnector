
package javaproject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TeamHead head = new TeamHead(1, "Arun", "arun@gmail.com");

        TeamMember member = new TeamMember(0, "", "", 0);

        int choice = 0;

        do {

            System.out.println("\n===== PROJECT COLLABORATION =====");
            System.out.println("1. Create Project");
            System.out.println("2. Add Team Member");
            System.out.println("3. View All Projects");
            System.out.println("4. View Project Details");
            System.out.println("5. Update Member Progress");
            System.out.println("6. View Member Project");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    head.createProject(sc);
                    break;

                case 2:
                    head.addMember(sc);
                    break;

                case 3:
                    head.viewProjects();
                    break;

                case 4: {
                    System.out.print("Enter Project ID: ");
                    int id = sc.nextInt();

                    Project.viewProjectDetails(id);
                    break;
                }

                case 5:
                    member.updateProgress(sc);
                    break;

                case 6:
                    member.viewMyProject(sc);
                    break;

                case 0:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
