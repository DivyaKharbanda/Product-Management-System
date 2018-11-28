package javaapplication1;

public class Product 
{
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    
    public Product(int pid, String pname, float pprice, String paddDate, byte[] ppicture )
    {
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.addDate = paddDate;
        this.picture = ppicture;       
    }
    public int getid()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public float getPrice()
    {
        return price;
    }
    public String getAddDate()
    {
        return addDate;
    }
    public byte[] getPicture()
    {
        return picture;
    }
}
