package DataBase;
public class Company {
      private int Company_ID;
    private String Company_phone;
    private String Company_name;
    private String Typesof_product;



    public Company(int company_ID, String company_phone, String company_name, String typesof_product) {
        Company_ID = company_ID;
        Company_phone = company_phone;
        Company_name = company_name;
        Typesof_product = typesof_product;
    }


    public int getCompany_ID() {
        return Company_ID;
    }

    public String getCompany_phone() {
        return Company_phone;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public String getTypesof_product() {
        return Typesof_product;
    }

    public void setCompany_ID(int company_ID) {
        Company_ID = company_ID;
    }

    public void setCompany_phone(String company_phone) {
        Company_phone = company_phone;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public void setTypesof_product(String typesof_product) {
        Typesof_product = typesof_product;
    }
}
