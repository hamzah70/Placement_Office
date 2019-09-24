import java.util.ArrayList;
import java.util.Scanner;

class input {
    static Scanner inp;
    input() {
        inp=new Scanner(System.in);
    }

}

class Company {
    protected String name;
    protected int criteria;
    ArrayList<String> criteria_courses = new ArrayList<>();
    protected int req_students;
    protected boolean status;
    ArrayList<Float> marks = new ArrayList<>();
    ArrayList<Integer> roll_no_eligible = new ArrayList<>();


    Company(String name, int criteria, ArrayList<String> courses, int no_of_stud, ArrayList<Student> stud, int N){
        this.name=name;
        this.criteria=criteria;
        System.out.println(courses);
        for(int i=0; i<criteria; i++){
            System.out.println(i);
            this.criteria_courses.add(courses.get(i));
        }
        this.req_students=no_of_stud;
        this.status=true;

        System.out.println(this.name);
        System.out.println("Course Criteria");
        for(int i=0;i<criteria-1;i++){
            System.out.println(this.criteria_courses.get(i));
        }
        System.out.println("No of required students = "+this.req_students);
        if(this.status==true){
            System.out.println("Application Status = OPEN");
        }
        else{
            System.out.println("Application Status = CLOSED");
        }

        System.out.println("Enter scores for the technical round.");
        for(int i=0;i<stud.size();i++){
            int flag=0;
            Student s=stud.get(i);
            for(int j=0;j<criteria_courses.size();j++){
                if(s.course.equals(criteria_courses.get(j))){
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                this.roll_no_eligible.add(s.roll_no);
                System.out.println("Enter score for Roll no. "+s.roll_no);
                s.applied_companies.add(name);
                float marks=input.inp.nextInt();
                this.marks.add(marks);
            }
        }
        for(int i=0; i<this.marks.size();i++){
            for(int j=0; j<this.marks.size()-1;j++){
                if(this.marks.get(j)>this.marks.get(j+1)){
                    float temp_marks=this.marks.get(j);
                    this.marks.set(j,this.marks.get(j+1));
                    this.marks.set(j+1, temp_marks);
                    int temp_roll_no = this.roll_no_eligible.get(j);
                    this.roll_no_eligible.set(j,this.roll_no_eligible.get(j+1));
                    this.roll_no_eligible.set(j+1, temp_roll_no);
                }
                else if(this.marks.get(j).equals(this.marks.get(j + 1))){
                    Student s1= stud.get(this.roll_no_eligible.get(j)-1);
                    Student s2=stud.get(this.roll_no_eligible.get(j+1)-1);
                    if(s1.cgpa>s2.cgpa){
                        float temp_marks=this.marks.get(j);
                        this.marks.set(j,this.marks.get(j+1));
                        this.marks.set(j+1, temp_marks);
                        int temp_roll_no = this.roll_no_eligible.get(j);
                        this.roll_no_eligible.set(j,this.roll_no_eligible.get(j+1));
                        this.roll_no_eligible.set(j+1, temp_roll_no);
                    }
                }
            }
        }
    }

    void display_closed_companies(ArrayList<Company> company){
        for(int i=0;i<company.size();i++){
            Company c= company.get(i);
            if(c.status==false){
                System.out.println(c.name);
            }
        }
    }

    void display_application_open(ArrayList<Company> company){
        int count=0;
        for(int i=0;i<company.size();i++){
            Company c = company.get(0);
            if(c.status==true){
                System.out.println(c.name);
            }
        }
    }

    void display_details_name(String name, ArrayList<Company> company){
        for(int i=0;i<company.size(); i++){
            Company c=company.get(i);
            if(c.name.equals(name)){
                System.out.println("Company Name: "+ c.name);
                System.out.println("Course Criteria");
                for(int j=0; j<c.criteria_courses.size();j++){
                    System.out.println(criteria_courses.get(j));
                }
                System.out.println("No of required students = " + c.req_students);
                if(c.status=true){
                    System.out.println("Application Status = OPEN");
                }
                else{
                    System.out.println("Application Status = CLOSED");
                }
            }
        }
    }
}


class Student {
    protected float cgpa;
    protected String course;
    protected int roll_no;
    protected boolean status;
    String company="";
    ArrayList<String> applied_companies = new ArrayList<>();

    Student(float cg, String cou, int roll){
        this.cgpa=cg;
        this.course=cou;
        this.roll_no = roll;
        this.status=false;
    }

    static void display_roll_no(int r_no, ArrayList<Student> stud){
        Student s=stud.get(r_no-1);
        System.out.println("Roll No: "+s.roll_no);
        System.out.println("CGPA: "+s.cgpa);
        System.out.println("course: "+s.course);
        if(s.status==false){
            System.out.println("Placement Status: Not Placed");
        }
        else{
            System.out.println("Placement Status: Not Placed");
            System.out.println(s.company);
        }
    }

    void display_company(String company_name, ArrayList<Student> stud, ArrayList<Company> company){
        Company c=company.get(0);
        for(int i=0;i<company.size();i++){
            c=company.get(0);
            if(c.name==company_name)
                break;
        }
        System.out.println("Roll Number of Selected Students");
        int i=c.roll_no_eligible.size()-1;
        int j=c.req_students;
        while(j>0 && i>0){
            Student s=stud.get(c.roll_no_eligible.get(i)-1);
            if(s.status==false){
                System.out.println(c.roll_no_eligible.get(i));
                s.status=true;
                c.req_students-=1;
                j--;
            }
            i--;
        }
        if(c.req_students==0){
            c.status=false;
        }

    }

    void display_placed_students(ArrayList<Student> stud){
        for(int i=0; i<stud.size(); i++){
            Student s = stud.get(i);
            if(s.status==true){
                System.out.println(s.roll_no);
            }
        }
    }

    int display_unplaced_count(ArrayList<Student> stud){
        int count=0;
        for(int i=0; i<stud.size(); i++){
            Student s=stud.get(i);
            if(s.status==false){
                count++;
            }
        }
        return count;
    }

    void query_9(int roll_no, ArrayList<Student> stud, ArrayList<Company> company){
        Student s=stud.get(roll_no-1);
        if(s.status){
            System.out.println("No student with the given roll number has an account.");
        }
        else{
            for(int i=0;i <s.applied_companies.size();i++){
                String company_name = s.applied_companies.get(i);
                Company c = company.get(0);
                for(int j=0;j<company.size();j++){
                    c = company.get(j);
                    if(c.name.equals(company_name)){
                        break;
                    }
                }
                float marks=0;
                for(int j=0;j<c.roll_no_eligible.size();j++){
                    if(c.roll_no_eligible.get(j)==roll_no){
                        marks=c.marks.get(j);
                    }
                }
                System.out.println(s.applied_companies.get(i)+" "+marks);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        input temp=new input();
        int N=input.inp.nextInt();

        ArrayList<Student> students = new ArrayList<>();

        for(int i=0; i<N; i++){
            float val1=input.inp.nextFloat();
            String val2=input.inp.next();
            Student s = new Student(val1, val2, i+1);
            students.add(s);
        }


        System.out.println("---- students registered ----");


        ArrayList<Company> company = new ArrayList<>();
        int count=N;
        while(count>0){
            int query = input.inp.nextInt();

            if(query==1){
                String name = input.inp.next();
                System.out.print("No of eligible courses = ");
                int criteria = input.inp.nextInt();
                ArrayList<String> courses = new ArrayList<>();
                for(int i=0;i<criteria;i++){
                    String val = input.inp.next();
                    courses.add(val);
                }
                System.out.print("No of required students = ");
                int req_students = input.inp.nextInt();
                Company c = new Company(name, criteria, courses, req_students, students, N);
                company.add(c);
            }

            else if(query==2){
                System.out.println("Accounts removed for");
                students.get(0).display_placed_students(students);
            }

            else if(query==3){
                System.out.println("Accounts removed for");
                company.get(0).display_closed_companies(company);
            }

            else if(query==4){
                int ans = students.get(0).display_unplaced_count(students);
                System.out.println(ans+" students left");
            }

            else if(query==5){
                company.get(0).display_application_open(company);
            }

            else if(query==6){
                String name=input.inp.next();
                students.get(0).display_company(name, students, company);
            }

            else if(query==7){
                String name = input.inp.next();
                company.get(0).display_details_name(name, company);
            }

            else if(query==8){
                int r_no=input.inp.nextInt();
                Student.display_roll_no(r_no, students);
            }

            else{
                int r_no = input.inp.nextInt();
                students.get(0).query_9(r_no, students, company);
            }
            count=students.get(0).display_unplaced_count(students);
        }
    }
}
