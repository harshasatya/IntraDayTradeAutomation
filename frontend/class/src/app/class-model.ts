export class ClassModel {
    Email: String;
    Password: string;
    Cardnumber: number;
    Expiry_date: number;

    constructor(email,password,cardnumber,expiry_date)
    {
        this.Email=email;
        this.Password= password;
        this.Cardnumber= cardnumber;
        this.Expiry_date= expiry_date;
    }
}