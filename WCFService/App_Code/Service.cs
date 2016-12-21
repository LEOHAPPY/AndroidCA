using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using WCFService;


// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service" in code, svc and config file together.
public class Service : IService
{

    public WCFBook GetBook(String id)
    {
        
        Book c = Data.GetBook(id);
        return WCFBook.Make(c.id, c.title, c.description, c.price,
        c.cg, c.image, c.quantity);
    }
    public List<WCFBook> GetBookByCg(String cg)

    {
        List<WCFBook> wb=new List<WCFBook>();
        List<Book> b= Data.GetBookByCg(cg); 
        for (int i=0;i<b.Count;i++)
        {
            Book c = b[i];
            wb.Add(WCFBook.Make(c.id, c.title, c.description, c.price,
            c.cg, c.image, c.quantity));
        }
        return wb;
    }

    public string[] List()
    {
        return Data.ListBooks().ToArray<String>();
    }

    
        public string[] ListChoice()
    {
        return Data.ListBookChoice().ToArray<String>();
    }


    public void Update(WCFBook c)
    {
        
        Book b = new Book
        {
            id =c.Id ,
            title = c.Title,
            description = c.Description,
            price = c.Price,
            cg = c.Cg,
            quantity = c.Quantity,
            image=c.Image
            
        };
        Data.UpdateBook(b);
    }
    public void Insert(WCFBook c)
    {
        string[] idList = Data.ListBooks().ToArray<String>();
        int newID = 0;
        for (int i = 0; i < idList.Length; i++)
        {

            if (newID < Convert.ToInt32(idList[i]))
                newID = Convert.ToInt32(idList[i]);
        }
        newID = newID + 1;
        Book b = new Book
        {
            id = newID.ToString(),
            title = c.Title,
            description = c.Description,
            price = c.Price,
            cg = c.Cg,
            quantity = c.Quantity,
            image = c.Image

        };
        Data.InsertBook(b);
    }
}