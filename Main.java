import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class Enrollment {
        int courseId;
        int studentId;

        Enrollment(int courseId, int studentId) {
            this.courseId = courseId;
            this.studentId = studentId;
        }
    }

    static ArrayList<Student> samnegi_studentL = new ArrayList<>();
    static ArrayList<Courses> samnegi_courseL = new ArrayList<>();
    static HashMap<Integer, ArrayList<Integer>> samnegi_enro = new HashMap<>();

    static class InvalidChoiceException extends Exception {
        public InvalidChoiceException(String message) {
            super(message);
        }
    }

    static class EnrollmentProcessor implements Runnable {
        private int samnegi_sid;
        private int samnegi_cid;

        EnrollmentProcessor(int samnegi_sid, int samnegi_cid) {
            this.samnegi_sid = samnegi_sid;
            this.samnegi_cid = samnegi_cid;
        }

        public void run() {
            try {
                String samnegi_sname = "";
                String samnegi_cname = "";

                for (int i = 0; i < samnegi_studentL.size(); i++) {
                    if (samnegi_studentL.get(i).studentId == samnegi_sid) {
                        samnegi_sname = samnegi_studentL.get(i).name;
                    }
                }
                for (int i = 0; i < samnegi_courseL.size(); i++) {
                    if (samnegi_courseL.get(i).courseId == samnegi_cid) {
                        samnegi_cname = samnegi_courseL.get(i).courseName;
                    }
                }

                System.out.println("\n-------------------------------------");
                System.out.println("   Processing enrollment for: " + samnegi_sname);
                System.out.println("   Course: " + samnegi_cname);
                System.out.println("-------------------------------------");
                Thread.sleep(2000);
                System.out.println("\n-------------------------------------");
                System.out.println("   Enrollment Processed Successfully!");
                System.out.println("   Student : " + samnegi_sname);
                System.out.println("   Course  : " + samnegi_cname);
                System.out.println("-------------------------------------\n");

            } catch (InterruptedException e) {
                System.out.println("Enrollment processing interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }

    static void addStudent() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                System.out.println("\n-------------------------------------");
                System.out.print("        Enter the student ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("-------------------------------------");
                System.out.print("       Enter the student name: ");
                String name = sc.nextLine();
                System.out.println("-------------------------------------");
                System.out.print("       Enter the student's email ID: ");
                String email = sc.nextLine();

                samnegi_studentL.add(new Student(id, name, email));

                System.out.println("\n-------------------------------------");
                System.out.println("            New Student Added !!");
                System.out.println("-------------------------------------\n");

                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void addCourse() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n-------------------------------------");
                System.out.print("        Enter the Course ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("-------------------------------------");
                System.out.print("        Enter the Course name: ");
                String name = sc.nextLine();
                System.out.println("-------------------------------------");
                System.out.print("        Enter the Course Fee: ");
                double fee = sc.nextDouble();
                sc.nextLine();

                samnegi_courseL.add(new Courses(id, name, fee));

                System.out.println("\n-------------------------------------");
                System.out.println("            New Course Added !!");
                System.out.println("-------------------------------------\n");
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void addEnrollment() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                System.out.println("\n-------------------------------------");
                System.out.print("      Enter the student ID to enroll: ");
                int samnegi_sid = sc.nextInt();
                sc.nextLine();

                boolean samnegi_sfound = false;
                for (int i = 0; i < samnegi_studentL.size(); i++) {
                    if (samnegi_studentL.get(i).studentId == samnegi_sid) {
                        samnegi_sfound = true;
                    }
                }
                if (!samnegi_sfound) {
                    throw new InvalidChoiceException("      Student ID not found!!");
                }

                System.out.println("-------------------------------------");
                System.out.print("   Enter the course ID to enroll in: ");
                int samnegi_cid = sc.nextInt();
                sc.nextLine();

                boolean samnegi_cfound = false;
                for (int i = 0; i < samnegi_courseL.size(); i++) {
                    if (samnegi_courseL.get(i).courseId == samnegi_cid) {
                        samnegi_cfound = true;
                    }
                }
                if (!samnegi_cfound) {
                    throw new InvalidChoiceException("      Course ID not found!!");
                }

                if (!samnegi_enro.containsKey(samnegi_sid)) {
                    samnegi_enro.put(samnegi_sid, new ArrayList<>());
                }
                samnegi_enro.get(samnegi_sid).add(samnegi_cid);

                System.out.println("\n-------------------------------------");
                System.out.println("            New Enrollment Added !!");
                System.out.println("-------------------------------------\n");
                break;

            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void viewStudents() {
        if (samnegi_studentL.isEmpty()) {
            System.out.println("\n-------------------------------------");
            System.out.println("       No Students added yet!!");
            System.out.println("-------------------------------------");
        } else {
            System.out.println("\n-------------------------------------");
            System.out.println("         ID\tName\tEmail");
            System.out.println("-------------------------------------");
            for (int i = 0; i < samnegi_studentL.size(); i++) {
                Student s1 = samnegi_studentL.get(i);
                System.out.println(s1.studentId + "\t" + s1.name + "\t" + s1.studentEmail);
            }
        }
    }

    static void viewEnrollments() {
        if (samnegi_enro.isEmpty()) {
            System.out.println("\n-------------------------------------");
            System.out.println("      No enrollments added yet!!");
            System.out.println("-------------------------------------");
        } else {
            System.out.println("\n-------------------------------------");
            System.out.println("Student ID\tStudent Name\tCourse Name");
            System.out.println("-------------------------------------");

            for (Integer samnegi_sid : samnegi_enro.keySet()) {
                String samnegi_sname = "";
                for (int i = 0; i < samnegi_studentL.size(); i++) {
                    if (samnegi_studentL.get(i).studentId == samnegi_sid) {
                        samnegi_sname = samnegi_studentL.get(i).name;
                    }
                }
                ArrayList<Integer> samnegi_cids = samnegi_enro.get(samnegi_sid);
                for (int j = 0; j < samnegi_cids.size(); j++) {
                    String samnegi_cname = "";
                    for (int k = 0; k < samnegi_courseL.size(); k++) {
                        if (samnegi_courseL.get(k).courseId == samnegi_cids.get(j)) {
                            samnegi_cname = samnegi_courseL.get(k).courseName;
                        }
                    }
                    System.out.println(samnegi_sid + "\t\t" + samnegi_sname + "\t\t" + samnegi_cname);
                }
            }
            System.out.println("-------------------------------------");
        }
    }

    static void processEnrollment() {
        if (samnegi_enro.isEmpty()) {
            System.out.println("\n-------------------------------------");
            System.out.println("      No enrollments to process!!");
            System.out.println("-------------------------------------");
            return;
        }

        System.out.println("\n-------------------------------------");
        System.out.println("     Starting Enrollment Processing...");
        System.out.println("-------------------------------------");

        for (Integer samnegi_sid : samnegi_enro.keySet()) {
            ArrayList<Integer> samnegi_cids = samnegi_enro.get(samnegi_sid);
            for (int j = 0; j < samnegi_cids.size(); j++) {
                EnrollmentProcessor samnegi_ep = new EnrollmentProcessor(samnegi_sid, samnegi_cids.get(j));
                Thread samnegi_t = new Thread(samnegi_ep);
                samnegi_t.start();
                try {
                    samnegi_t.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    static void mainMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n-------------------------------------");
            System.out.println("              Main Menu");
            System.out.println("-------------------------------------");
            System.out.println("         [ 1.] Add Student");
            System.out.println("         [ 2.] Add Course");
            System.out.println("         [ 3.] Enroll Student");
            System.out.println("         [ 4.] View Students");
            System.out.println("         [ 5.] View Enrollments");
            System.out.println("         [ 6.] Process Enrollment");
            System.out.println("         [ 7.] Exit");
            System.out.println("-------------------------------------");
            System.out.print("        Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("-------------------------------------");

                if (choice == 1) {
                    addStudent();
                } else if (choice == 2) {
                    addCourse();
                } else if (choice == 3) {
                    addEnrollment();
                } else if (choice == 4) {
                    viewStudents();
                } else if (choice == 5) {
                    viewEnrollments();
                } else if (choice == 6) {
                    processEnrollment();
                } else if (choice == 7) {
                    System.out.println("-------------------------------------");
                    System.out.println("             Bye bye !!");
                    System.out.println("-------------------------------------\n");
                    break;
                } else {
                    throw new InvalidChoiceException("      Invalid choice!!");
                }

            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}