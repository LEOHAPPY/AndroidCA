using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WCFService;

/// <summary>
/// Summary description for Test
/// </summary>
public class Test
{
    public Test()
    {
        //
        // TODO: Add constructor logic here
        //
    }
    public static void main(String[] args)
    {
       Service s = new Service();http://localhost/WCFService/App_Code/Model.cs
        Book b = new Book
        {
            id = null,
            title = "title",
            description = "description",
            price = 48,
            cg ="cg",
            quantity = 4,
            image="image"

};
       // s.Update(b);
        
    }
}