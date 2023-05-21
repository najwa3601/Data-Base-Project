package DataBase;
public class Employee {
    private int E_ID;
    private String E_name;
    private String Address;
    private String job_title;
    private int  Working_hours;
    private String Phone;
    private String Birth_date;
    private String marital_status;
    private String  Monthdate;
    private int  Salary;
    private String  E_Password;
    private int  Branch_ID;

    public Employee(int E_ID,String E_name, String Address, String job_title, int Working_hours,
                    String Phone, String Birth_date, String marital_status, String Monthdate, int Salary, String  E_Password , int Branch_ID) {

        this.E_ID = E_ID;
        this.E_name = E_name;
        this.Address = Address;
        this.job_title = job_title;
        this.Working_hours =Working_hours ;
        this.Phone = Phone;
        this.Birth_date = Birth_date;
        this.marital_status = marital_status;
        this.Monthdate= Monthdate;
        this.Salary=Salary;
        this.E_Password= E_Password;
        this.Branch_ID=Branch_ID;



    }

    public String getE_name() {
        return E_name;
    }

    public void setE_name(String e_name) {
        E_name = e_name;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getE_ID() {
        return E_ID;
    }

    public void setE_ID(int e_ID) {
        E_ID = e_ID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBirth_date() {
        return Birth_date;
    }

    public void setBirth_date(String birth_date) {
        Birth_date = birth_date;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    public int getWorking_hours() {
        return Working_hours;
    }

    public void setWorking_hours(int working_hours) {
        Working_hours = working_hours;
    }
    public String getMonthdate() {
        return Monthdate;
    }

    public void setMonthdate(String monthdate) {
        Monthdate = monthdate;}

        public int getSalary() {
            return Salary;
        }

        public void setHSalary(int hourly_rate) {
            Salary = hourly_rate;
        }
    public String getPassword() {
        return E_Password;
    }

    public void setPassword(String password) {
        E_Password = password;}
    public int getBranch_ID() {
        return Branch_ID;
    }

    public void setBranch_ID(int branch_ID) {
        Branch_ID =branch_ID;
    }
    }



